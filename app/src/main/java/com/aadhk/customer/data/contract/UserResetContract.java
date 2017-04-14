package com.aadhk.customer.data.contract;


import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public interface UserResetContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<Object> reset(String email);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void resetResult();
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<UserResetContract.View, UserResetContract.Model> {
        public abstract void resetRequest(String email);
    }
}
