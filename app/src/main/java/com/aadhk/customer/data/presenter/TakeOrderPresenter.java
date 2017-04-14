package com.aadhk.customer.data.presenter;

import android.util.Log;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.contract.TakeOrderContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 06/12/2016.
 */

public class TakeOrderPresenter extends TakeOrderContract.Presenter {
    
    @Override
    public void takeOrderRequest(Order order) {

        Log.e("jack", "onClick:3 come here" );
        mRxManage.add(mModel.takeOrder(order).subscribe(new RxSubscriber<Order>(mContext, false) {
            @Override
            protected void _onNext(Order o) {
                mView.takeOrderRequestResult(o);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }
}
