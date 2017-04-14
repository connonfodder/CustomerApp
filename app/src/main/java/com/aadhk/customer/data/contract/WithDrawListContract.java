package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public interface WithDrawListContract {

    interface Model extends BaseModel {
        Observable<List<UserWithdrawals>> query(long userId);
    }

    interface View extends BaseView {
        void queryResult(List<UserWithdrawals> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryRequest(long userId);
    }
}
