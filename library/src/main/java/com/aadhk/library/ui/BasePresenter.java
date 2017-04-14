package com.aadhk.library.ui;

import android.content.Context;

import com.aadhk.library.rx.RxManager;


/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T, E> {
    public Context mContext;
    public T mView;
    public E mModel;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void setVM(T v, E m, Context context) {
        this.mView = v;
        this.mModel = m;
        this.mContext = context;
        this.onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManage.clear();
    }

}
