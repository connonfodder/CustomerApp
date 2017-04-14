package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.data.contract.TakeOrderingContract;
import com.aadhk.library.rx.RxSubscriber;

/**
 * Created by jack on 25/11/2016.
 */

public class TakeOrderingPresenter extends TakeOrderingContract.Presenter {

    @Override
    public void chooseAddressRequest(long userId, long companyId, double lng, double lat) {
        mRxManage.add(mModel.chooseAddress(userId, companyId, lng, lat).subscribe(new RxSubscriber<Address>(mContext, true) {
            @Override
            protected void _onNext(Address address) {
                mView.returnChooseAddress(address);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.returnChooseAddress(null);
            }
        }));
    }

    @Override
    public void companyDetailRequest(long id) {
        mRxManage.add(mModel.companyDetail(id).subscribe(new RxSubscriber<Company>(mContext, true) {
            @Override
            protected void _onNext(Company company) {
                mView.returnCompanyDetailData(company);
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
