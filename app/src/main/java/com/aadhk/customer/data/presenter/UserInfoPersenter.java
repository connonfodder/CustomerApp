package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserInfoContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 30/11/2016.
 */

public class UserInfoPersenter extends UserInfoContract.Presenter {
    @Override
    public void updateInfoRequest(User user) {
        mRxManage.add(mModel.updateInfo(user).subscribe(new RxSubscriber<Object>(mContext, false) {
            @Override
            protected void _onNext(Object o) {
                mView.updateInfoResult();
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
