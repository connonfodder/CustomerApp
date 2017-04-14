package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.contract.WithDrawListContract;
import com.aadhk.library.rx.RxSubscriber;

import java.util.List;

/**
 * Created by jack on 15/12/2016.
 */

public class WithDrawListPresenter extends WithDrawListContract.Presenter {

    @Override
    public void queryRequest(long userId) {
        mRxManage.add(mModel.query(userId).subscribe(new RxSubscriber<List<UserWithdrawals>>(mContext, true) {
            @Override
            protected void _onNext(List<UserWithdrawals> list) {
                mView.queryResult(list);
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
