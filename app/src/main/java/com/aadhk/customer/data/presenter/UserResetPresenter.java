package com.aadhk.customer.data.presenter;

import com.aadhk.customer.data.contract.UserResetContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 29/11/2016.
 */

public class UserResetPresenter extends UserResetContract.Presenter {

    @Override
    public void resetRequest(String email) {
        mRxManage.add(mModel.reset(email).subscribe(new RxSubscriber<Object>(mContext, true) {

            @Override
            protected void _onNext(Object o) {
                mView.resetResult();
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
