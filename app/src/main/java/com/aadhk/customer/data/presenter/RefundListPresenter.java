package com.aadhk.customer.data.presenter;

import com.aadhk.customer.data.contract.RefundListContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 15/12/2016.
 */

public class RefundListPresenter extends RefundListContract.Presenter {

    @Override
    public void refundRequest(long orderId, String reason) {
        mRxManage.add(mModel.refund(orderId, reason).subscribe(new RxSubscriber<Object>(mContext, true) {
            @Override
            protected void _onNext(Object o) {
                mView.refundResult();
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
