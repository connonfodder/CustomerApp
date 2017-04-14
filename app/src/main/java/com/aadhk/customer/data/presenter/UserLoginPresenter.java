package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserLoginContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 29/11/2016.
 */

public class UserLoginPresenter extends UserLoginContract.Presenter {

    @Override
    public void vertifyUserRequest(User user, boolean loading) {
        mRxManage.add(mModel.vertifyUser(user).subscribe(new RxSubscriber<User>(mContext, loading) {

            @Override
            protected void _onNext(User user) {
                System.out.println("------------_onNext---------------user="+user);
                mView.vertifyUserResult(user);
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
