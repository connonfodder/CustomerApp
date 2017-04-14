package com.aadhk.customer.data.contract;


import com.aadhk.customer.bean.Address;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 08/12/2016.
 */

public interface LocationEditContract {

    interface Model extends BaseModel {
        Observable<Object> saveAddress(Address bean);
        Observable<Object> deleteAddress(long addressId);
    }

    interface View extends BaseView {
        void saveAddressResult();
        void deleteAddressResult();
    }

    abstract class Presenter extends BasePresenter<LocationEditContract.View, LocationEditContract.Model> {
        public abstract void saveAddressRequest(Address bean);
        public abstract void deleteAddressRequest(long addressId);
    }
}
