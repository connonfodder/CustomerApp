package com.aadhk.customer.data.contract;

import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public interface RefundListContract {

    interface Model extends BaseModel {
        Observable<Object> refund(long orderId, String reason);
    }

    interface View extends BaseView {
        void refundResult();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void refundRequest(long orderId, String reason);
    }
}
