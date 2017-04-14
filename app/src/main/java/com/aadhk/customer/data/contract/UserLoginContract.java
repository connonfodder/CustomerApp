package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.User;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public interface UserLoginContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<User> vertifyUser(User user);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void vertifyUserResult(User user);
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<UserLoginContract.View, UserLoginContract.Model> {
        public abstract void vertifyUserRequest(User user, boolean loading);
    }
}
