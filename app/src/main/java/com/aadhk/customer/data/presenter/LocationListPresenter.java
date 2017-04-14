package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.contract.LocationListContract;
import com.aadhk.library.rx.RxSubscriber;

import java.util.List;

/**
 * Created by jack on 08/12/2016.
 */

public class LocationListPresenter extends LocationListContract.Presenter {

    @Override
    public void fetchAllLocationRequest() {
        mRxManage.add(mModel.fetchAllLocation().subscribe(new RxSubscriber<List<Address>>(mContext, false) {
            @Override
            protected void _onNext(List<Address> addresses) {
                mView.fetchAllLocationResult(addresses);
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
