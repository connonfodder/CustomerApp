package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.User;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 30/11/2016.
 */

public interface UserPwdContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<Object> updatePwd(User user);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void updateResult();
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void updatePwdRequest(User user);
    }
}
