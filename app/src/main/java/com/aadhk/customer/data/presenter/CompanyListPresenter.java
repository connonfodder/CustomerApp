package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.CompanyRequest;
import com.aadhk.customer.data.contract.CompanyListContract;
import com.aadhk.library.rx.RxSubscriber;

import java.util.List;


/**
 * Created by jack on 25/11/2016.
 */

public class CompanyListPresenter extends CompanyListContract.Presenter {

    @Override
    public void getCompanyListDataRequest(CompanyRequest request) {
        mRxManage.add(mModel.loadCompanyData(request).subscribe(new RxSubscriber<List<Company>>(mContext, false) {

            @Override
            protected void _onNext(List<Company> resultsEntities) {
                mView.returnCompanyListData(resultsEntities);
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
