package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Order;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public interface PaymentContract {

    interface Model extends BaseModel {
        Observable<Object> pay(Order order);
    }

    interface View extends BaseView {
        void payResult();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void payRequest(Order order);
    }
}
