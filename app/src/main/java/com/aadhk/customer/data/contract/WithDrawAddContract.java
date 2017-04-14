package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public interface WithDrawAddContract {

    interface Model extends BaseModel {
        Observable<Object> withDraw(UserWithdrawals userWithdrawals);
    }

    interface View extends BaseView {
        void withDrawResult();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void withDrawRequest(UserWithdrawals userWithdrawals);
    }
}
