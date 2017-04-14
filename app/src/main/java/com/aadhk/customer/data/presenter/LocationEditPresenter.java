package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.contract.LocationEditContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 08/12/2016.
 */

public class LocationEditPresenter extends LocationEditContract.Presenter {

    @Override
    public void saveAddressRequest(Address bean) {
        mRxManage.add(mModel.saveAddress(bean).subscribe(new RxSubscriber<Object>(mContext, false) {
            @Override
            protected void _onNext(Object o) {
                mView.saveAddressResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.stopLoading();
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void deleteAddressRequest(long addressId) {
        mRxManage.add(mModel.deleteAddress(addressId).subscribe(new RxSubscriber<Object>(mContext, false) {
            @Override
            protected void _onNext(Object s) {
                mView.deleteAddressResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.stopLoading();
                mView.showErrorTip(message);
            }
        }));
    }
}
