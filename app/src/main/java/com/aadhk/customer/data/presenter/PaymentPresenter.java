package com.aadhk.customer.data.presenter;

import android.util.Log;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.contract.PaymentContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 15/12/2016.
 */

public class PaymentPresenter extends PaymentContract.Presenter {

    @Override
    public void payRequest(Order order) {
        mRxManage.add(mModel.pay(order).subscribe(new RxSubscriber<Object>(mContext, true) {
            @Override
            protected void _onNext(Object o) {
                Log.e("jack", "_onNext: " + " come here 1" );
                mView.payResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                Log.e("jack", "_onError: message = " + message );
                Log.e("jack", "_onNext: " + " come here 2" );
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }
}
