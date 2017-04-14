package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserPwdContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 30/11/2016.
 */

public class UserPwdPreseneter extends UserPwdContract.Presenter {

    @Override
    public void updatePwdRequest(User user) {
        mRxManage.add(mModel.updatePwd(user).subscribe(new RxSubscriber<Object>(mContext, false) {

            @Override
            protected void _onNext(Object o) {
                System.out.println("---------_onNext------");
                mView.updateResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                System.out.println("---------_onError------"+message);
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }
}
