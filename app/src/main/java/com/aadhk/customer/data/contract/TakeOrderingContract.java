package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.Company;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;


public interface TakeOrderingContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<Company> companyDetail(long id);
        Observable<Address> chooseAddress(long userId, long companyId, double lng, double lat);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void returnCompanyDetailData(Company data);
        void returnChooseAddress(Address address);
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void companyDetailRequest(long id);
        public abstract void chooseAddressRequest(long userId, long companyId, double lng, double lat);
    }
}
