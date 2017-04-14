package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Order;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 06/12/2016.
 */

public interface TakeOrderContract {

    interface Model extends BaseModel {
        Observable<Order> takeOrder(Order order);
    }

    interface View extends BaseView {
        void takeOrderRequestResult(Order order);
    }

    abstract class Presenter extends BasePresenter<TakeOrderContract.View, TakeOrderContract.Model> {
        public abstract void takeOrderRequest(Order order);
    }
}
