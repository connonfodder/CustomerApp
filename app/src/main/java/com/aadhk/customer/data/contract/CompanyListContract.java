package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.CompanyRequest;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import java.util.List;

import rx.Observable;


public interface CompanyListContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<List<Company>> loadCompanyData(CompanyRequest request);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void returnCompanyListData(List<Company> data);
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getCompanyListDataRequest(CompanyRequest request);
    }
}
