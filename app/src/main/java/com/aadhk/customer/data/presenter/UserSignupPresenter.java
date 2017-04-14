package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserSignupContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 29/11/2016.
 */

public class UserSignupPresenter extends UserSignupContract.Presenter {

    @Override
    public void signupRequest(User user) {
        mRxManage.add(mModel.signup(user).subscribe(new RxSubscriber<Object>(mContext, true) {
            @Override
            protected void _onNext(Object o) {
                mView.signupResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }

  /*  @Override
    public void loginRequest(User user) {
        mRxManage.add(mModel.login(user).subscribe(new RxSubscriber<User>(mContext, true) {
            @Override
            protected void _onNext(User user) {
                mView.loginResult(user);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }*/
}
