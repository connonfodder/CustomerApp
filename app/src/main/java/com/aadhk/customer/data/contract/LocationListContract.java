package com.aadhk.customer.data.contract;


import com.aadhk.customer.bean.Address;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by jack on 08/12/2016.
 */

public interface LocationListContract {

    interface Model extends BaseModel {
        Observable<List<Address>> fetchAllLocation();
    }

    interface View extends BaseView {
        void fetchAllLocationResult(List<Address> result);
    }

    abstract class Presenter extends BasePresenter<LocationListContract.View, LocationListContract.Model> {
        public abstract void fetchAllLocationRequest();
    }
}
