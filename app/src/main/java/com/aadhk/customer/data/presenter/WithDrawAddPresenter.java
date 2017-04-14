package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.contract.WithDrawAddContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 15/12/2016.
 */

public class WithDrawAddPresenter extends WithDrawAddContract.Presenter {

    @Override
    public void withDrawRequest(UserWithdrawals userWithdrawals) {
        mRxManage.add(mModel.withDraw(userWithdrawals).subscribe(new RxSubscriber<Object>(mContext, true) {
            @Override
            protected void _onNext(Object o) {
                mView.withDrawResult();
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
