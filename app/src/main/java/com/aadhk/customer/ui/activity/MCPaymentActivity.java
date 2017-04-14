package com.aadhk.customer.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.mcpayment.CheckOutInitRequest;
import com.aadhk.customer.bean.mcpayment.CheckOutInitResponse;
import com.aadhk.customer.bean.mcpayment.MasterPassItem;
import com.aadhk.customer.bean.mcpayment.PaymentData;
import com.aadhk.customer.bean.mcpayment.ProcessPaymentRequest;
import com.aadhk.customer.bean.mcpayment.ProcessPaymentResponse;
import com.aadhk.customer.bean.mcpayment.RetrieveCheckOutDataTokenRequest;
import com.aadhk.customer.bean.mcpayment.RetrieveCheckOutDataTokenResponse;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.util.URLUtil;
import com.aadhk.library.utils.LogUtil;

import org.acra.ACRA;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MCPaymentActivity extends AppCompatActivity {

    @InjectView(R.id.contentPanel)
    WebView webView;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private CheckOutInitResponse checkOutInitResponse;

    private final static String CODE_SUCCESS = "200";
    private final static String MERCHANT_ID = "ed0dY0KKjizn5pp3KW0s";
    private final static String PROCESS_TYPE = "STANDARD";
    private final static String CALLBACK_URL = "https://maptest.mcpayment.net/MasterPass/CallBackURL";
    private final static String SECURITY_KEY = "nQP6J1qJe4DUeXAEwiPghtM9CVa2E6aSe7Ys6wu1";
    private final static String STATUS_URL = "https://maptest.mcpayment.net:8888/StatusURL.aspx";
    private final static String CURRENCY = "SGD";
    private final static String TERMINALID = "3115060001";
    private final static String VERSION = "1.0";
    private final static String TIME_FORMAT = "yyyy­MM­dd'T'hh:mm:ss+hh:mm";
    private final static SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);

    private double AMOUNT;
    private String REFERENCE;
    private String checkout_resource_url;
    private PaymentData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_payment);
        ButterKnife.inject(this);

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.YELLOW, Color.RED);
        //1. 常用设置
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        /**LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据。
         LOAD_DEFAULT: 根据cache-control决定是否从网络上取数据。
         LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式。
         LOAD_NO_CACHE: 不使用缓存，只从网络获取数据。
         LOAD_CACHE_ELSE_NETWORK：只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。 */
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 JavaScript 来操作这些数据。）
        settings.setDomStorageEnabled(true);
        //设置数据库缓存路径
//        settings.setDatabasePath(cacheDirPath);
        //设置Application Caches缓存目录
//        settings.setAppCachePath(cacheDirPath);
        //设置默认编码
        settings.setDefaultTextEncodingName("utf-8");
        //将图片调整到适合webview的大小
        settings.setUseWideViewPort(false);
        //支持缩放
        settings.setSupportZoom(true);
      /*  //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);*/
        //多窗口
        settings.supportMultipleWindows();
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(true);
        //支持通过JS打开新窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(false);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);

        //2. 常用回调
        //  WebViewClient主要用来辅助WebView处理各种通知、请求等事件，通过setWebViewClient方法设置
        //WebChromeClient主要用来辅助WebView处理Javascript的对话框、网站图标、网站标题以及网页加载进度等。通过WebView的setWebChromeClient()方法设置。
        webView.setWebViewClient(new WebViewClient() {

            //在网页跳转时调用，这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                LogUtil.d("------------shouldOverrideUrlLoading----------------" + url);
                if (checkOutInitResponse != null && url.contains(checkOutInitResponse.getCallbackUrl())) {  //表示已经获取了pair的token
                    Map<String, String> mapRequest = URLUtil.URLRequest(url);
                    String mpstatus = mapRequest.get("mpstatus");
                    String oauth_verifier = mapRequest.get("oauth_verifier");
                    String oauth_token = mapRequest.get("oauth_token");
                    try {
                        checkout_resource_url = URLDecoder.decode(mapRequest.get("checkout_resource_url"), "utf-8");
                    } catch (Exception e) {
                        LogUtil.d("---------checkout init fail---URL Decoder------");
                        return false;
                    }
                    LogUtil.d("----mpstatus------" + mpstatus);
                    LogUtil.d("----oauth_verifier------" + oauth_verifier);
                    LogUtil.d("----oauth_token------" + oauth_token);
                    LogUtil.d("----checkout_resource_url------" + checkout_resource_url);
                    if (!mpstatus.equals("success")) {
//                        Toast.makeText(getBaseContext(), "---------checkout init fail---------", Toast.LENGTH_LONG).show();
                        LogUtil.d("---------checkout init fail---------" + mpstatus);
                    } else {
                        Toast.makeText(getBaseContext(), "---------u have succeed got token--------", Toast.LENGTH_LONG).show();
                        LogUtil.d("---------u have succeed got token--------" + mpstatus);
                        retrieveCheckOutDataToken(oauth_token, oauth_verifier, checkout_resource_url);
                        return true;
                    }
                } else {
                    view.loadUrl(url);
                }
                //return true，则在打开新的url时WebView就不会再加载这个url了，所有处理都需要在WebView中操作，包含加载；
                // return false，则系统就认为上层没有做处理，接下来还是会继续加载这个url的；
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                LogUtil.d("--------------shouldInterceptRequest--------------request="+request);
                WebResourceResponse response = null;
                if (Build.VERSION.SDK_INT >= 21) {

                }
                return response;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //在页面加载结束时调用。同样道理，我们知道一个页面载入完成，于是我们可以关闭loading 条，切换程序动作。
            @Override
            public void onPageFinished(WebView view, String url) {
//                LogUtil.d("-------------onPageFinished---------------url=" + url);
                //https://maptest.mcpayment.net/MasterPass/PairingCallBackURL?mpstatus=cancel&oauth_token=e58123f01a867e756a90e34b5b117a4b420079b3
                super.onPageFinished(view, url);
            }

            //报告错误信息
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                LogUtil.d("------------onReceivedError----------------");
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                LogUtil.d("--------------onReceivedSslError--------------");
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                LogUtil.d("-------------onReceivedHttpError---------------");
                super.onReceivedHttpError(view, request, errorResponse);
            }

            //应用程序重新请求网页数据
            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
//                LogUtil.d("-------------onFormResubmission---------------");
                super.onFormResubmission(view, dontResend, resend);
            }

            //获取返回信息授权请求
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
//                LogUtil.d("------------onReceivedHttpAuthRequest----------------host=" + host + ", realm=" + realm);
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }
        });

        data = CustomerApplication.getInstance().getPaymentData();
        if (data == null) finish();
        AMOUNT = data.getAmount();
        checkOutInit(data.getItemList());
    }

    //step 1
    private void checkOutInit(List<MasterPassItem> items) {
        CheckOutInitRequest request = new CheckOutInitRequest();
        request.setMerchantId(MERCHANT_ID);
        request.setProcessType(PROCESS_TYPE);
        request.setCallbackUrl(CALLBACK_URL);
        request.setItems(items);
        request.setCurrency(CURRENCY);
        request.setVersion(VERSION);
        request.setTimeStamp(formatter.format(new Date()));
        API.getMCApi().checkOutInit(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CheckOutInitResponse>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        swipeRefreshLayout.setRefreshing(true);
                        LogUtil.d("-----CheckOutInit----onStart-----");
                    }

                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----CheckOutInit----onCompleted-----");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("----CheckOutInit-----onError-----" + e.toString());
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(CheckOutInitResponse response) {
                        LogUtil.d("----CheckOutInit-----onNext-----" + response);
                        //200 is success and 500 is for error
                        if (!response.getResCode().equals(CODE_SUCCESS)) {
//                            Toast.makeText(getBaseContext(), "------fail in checkOutInit------" + response.getResMsg(), Toast.LENGTH_LONG).show();
                            LogUtil.d("----fail in checkOutInit------");
                            return;
                        }
                        checkOutInitResponse = response;
                        webView.loadUrl(response.getRedirectUrl());  //重定向页面
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    //step 2  RetrieveCheckOutDataToken
    private void retrieveCheckOutDataToken(String OAuthtoken, String OAuthverifier, String CheckoutResourceUrl) {
        RetrieveCheckOutDataTokenRequest request = new RetrieveCheckOutDataTokenRequest();
        request.setMerchantId(MERCHANT_ID);
        request.setTerminalId(TERMINALID);
        request.setOAuthtoken(OAuthtoken);
        request.setOAuthverifier(OAuthverifier);
        request.setCheckoutResourceUrl(CheckoutResourceUrl);
        request.setVersion(VERSION);
        request.setTimeStamp(formatter.format(new Date()));
        LogUtil.d("-----retrieveCheckOutDataToken------request------" + request);
        API.getMCApi().retrieveCheckOutDataToken(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrieveCheckOutDataTokenResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        swipeRefreshLayout.setRefreshing(true);
                        LogUtil.d("-----retrieveCheckOutDataToken----onStart-----");
                    }

                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----retrieveCheckOutDataToken----onCompleted-----");
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----retrieveCheckOutDataToken----onError-----");
                    }

                    @Override
                    public void onNext(RetrieveCheckOutDataTokenResponse response) {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----retrieveCheckOutDataToken----onNext-----" + response);
                        // 需要校验信息 "ResCode": "200", "ResMsg": "Success.",
                        if (!response.getResCode().equals(CODE_SUCCESS)) {
//                            Toast.makeText(getBaseContext(), "------fail in retrieveCheckOutDataToken------" + response.getResMsg(), Toast.LENGTH_LONG).show();
                            LogUtil.d("----fail in retrieveCheckOutDataToken------");
                            return;
                        }
                        //这一步就算得到客户的大致信息，下一步进行付款操作
                        ProcessPaymentRequest request = new ProcessPaymentRequest();
                        request.setMerchantId(MERCHANT_ID);
                        request.setCurrency(CURRENCY);
                        request.setAmount(AMOUNT);
                        request.setEmail(response.getCheckoutData().getContact().getEmailAddress());
                        request.setTerminalId(TERMINALID);
                        REFERENCE = getReference();
                        request.setReference(REFERENCE);
                        request.setCustomerId(response.getCustomerId());
                        request.setCheckoutResourceUrl(checkout_resource_url);
                        request.setTransactionId(response.getCheckoutData().getTransactionId());
                        try {
                            request.setFgKey(GetMD5Hash(SECURITY_KEY, MERCHANT_ID, TERMINALID, REFERENCE, CURRENCY, AMOUNT + ""));
                        } catch (Exception e) {
                            //TODO : 不能为空
							ACRA.getErrorReporter().handleException(e);
                        }
                        request.setStatusUrl(STATUS_URL);
                        request.setVersion(VERSION);
                        request.setTimeStamp(formatter.format(new Date()));
                        paymentProcess(request);
                    }
                });
    }

    //step 3 PaymentProcess
    private void paymentProcess(ProcessPaymentRequest request) {
        LogUtil.d("-----paymentProcess----request=" + request);
        API.getMCApi().processPayment(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProcessPaymentResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        swipeRefreshLayout.setRefreshing(true);
                        LogUtil.d("-----paymentProcess----onStart-----");
                    }

                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----paymentProcess----onCompleted-----");
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----paymentProcess----onError-----");
                    }

                    @Override
                    public void onNext(ProcessPaymentResponse response) {
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("-----paymentProcess----onNext-----" + response);
                        if (!response.getResCode().equals(CODE_SUCCESS)) {
//                            Toast.makeText(getBaseContext(), "------fail in paymentProcess------" + response.getResMsg(), Toast.LENGTH_LONG).show();
                            LogUtil.d("----fail in paymentProcess------");
                            return;
                        }
                        //这一步完成付款啦
                        Toast.makeText(getBaseContext(), "------finally you get here-----", Toast.LENGTH_LONG).show();
                        LogUtil.d("----finally you get here------");
                        data.setFinished(true);
                        CustomerApplication.getInstance().setPaymentData(data);
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                });
    }


    public static String GetMD5Hash(String secretkey, String merchantid, String terminalid, String reference, String currency, String amount) throws Exception {
        String password = String.format("%s?merchantid=%s&terminalid=%s&reference=%s&currency=%s&amount=%s", secretkey, merchantid, terminalid, reference, currency, amount);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private static String getReference() {
        long userid = CustomerApplication.getInstance().getUser().getId();
        return System.currentTimeMillis() + "+" + userid;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //其中webView.canGoBack()在webView含有一个可后退的浏览记录时返回true
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
