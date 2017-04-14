package com.aadhk.customer.ui.activity;


import android.os.StrictMode;

import com.aadhk.customer.BuildConfig;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.FoodStyles;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.bean.mcpayment.PaymentData;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.HockeySender;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.customer.util.SucceedCallback;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.ui.BaseApplication;
import com.aadhk.library.utils.LogUtil;
import com.aadhk.library.utils.ToastUtil;
import com.antfortune.freeline.FreelineCore;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

@ReportsCrashes(formUri = Constant.HOCKEYAPP_APPID)
public class CustomerApplication extends BaseApplication {
    private static CustomerApplication mApplication;
    private static PreferenceUtil prefUtil;
    private User user;
    private Address address;
    private static AppSettings settings;
    public static short flag;
    public static  boolean developeMode = false;
    private List<FoodStyles> foodStylesList;
    private PaymentData paymentData;

    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
        mApplication = this;
        LogUtil.isDebug = true;
        ToastUtil.isShow = true;
        prefUtil = new PreferenceUtil(mApplication);
        ACRA.init(this);
        ACRA.getErrorReporter().setReportSender(new HockeySender());

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectAll() // or .detectAll(), detectNetwork for ALL detectable problems
                    .penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        initSetting();
        initUserInfo();
        initFoodStyles(null);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //可能为空
    public List<FoodStyles> getFoodStylesList() {return foodStylesList;}

    public static CustomerApplication getInstance() {return mApplication;}

    /**
     * 将设置存在本地，如果为空则使用默认的，每次进app时都会重新更新一次设置
     */
    public static AppSettings getSetting(){
        if(settings == null){
            initSetting();
        }
        settings = prefUtil.getSetting();
        return settings;
    }


    /**
     * TODO: 这种做法十分的不安全 也十分的不牢靠
     *  不能将登陆信息随便可获取,　也不要存信息在手机上
     */
    public User getUser() {
        if(user == null){
            user = prefUtil.getUser();
        }
        return user;
    }


    /**
     * the first is store session & cookie info in locale
     * the second is store user info in locale
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        prefUtil.saveUser(user);
    }

    //异步登录  这种SB的做法确实很SB 不过服务器session只能保持30min, 在知道登陆失效的情况下进行再登陆
    private void initUserInfo(){
        User user = prefUtil.getUser();
        if(user ==null) return;
        User temp = new User(user.getEmail(), user.getPassword());
        API.getDefault().login(temp)
                .compose(RxHelper.<User>handleResult())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(User user) {
                        CustomerApplication.this.user = user;
                    }
                });
    }

    public void initFoodStyles(final SucceedCallback callback) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("maxCount", 20);
        API.getDefault().queryFoodstyles(body)
                .compose(RxHelper.<List<FoodStyles>>handleResult())
                .subscribe(new Subscriber<List<FoodStyles>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(List<FoodStyles> foodStyles) {
                        CustomerApplication.this.foodStylesList = foodStyles;
                        if(callback!=null) callback.succeed();
                    }
                });
    }

    //初始化app设置
    private static void initSetting() {
        API.getDefault().getSetting()
                .compose(RxHelper.<AppSettings>handleResult())
                .subscribe(new Subscriber<AppSettings>() {
                    @Override
                    public void onCompleted() {
//                        LogUtil.d("=====================onCompleted================");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        LogUtil.d("==================onError===================");
                    }

                    @Override
                    public void onNext(AppSettings appSettings) {
//                        LogUtil.d("====================================="+appSettings.toString());
                        prefUtil.saveSettings(appSettings);
                    }
                });
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }


}
