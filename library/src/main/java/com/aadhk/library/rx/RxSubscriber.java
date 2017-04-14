package com.aadhk.library.rx;

import android.content.Context;
import android.util.Log;

import com.aadhk.library.R;
import com.aadhk.library.utils.NetWorkUtils;
import com.aadhk.library.widget.LoadingDialog;

import rx.Subscriber;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************
 * 使用例子
 ********************/
    /*_apiService.login(mobile, verifyCode)
            .//省略
            .subscribe(new RxSubscriber<User user>(mContext,false) {
        @Override
        public void _onNext(User user) {
                // 处理user
                }

        @Override
        public void _onError(String msg) {
                ToastUtil.showShort(mActivity, msg);
                });*/
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, context.getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, context.getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog) LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading(mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog) LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        if (!NetWorkUtils.isNetConnected(mContext)) { //网络
            _onError(mContext.getString(R.string.no_net));
        } else if (e instanceof ServerException) {              //服务器
            _onError(e.getMessage());
        } else {
            Log.e("jack", "onError: "+ e );
            _onError(mContext.getString(R.string.net_error));   //其它
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
