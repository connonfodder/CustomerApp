package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.User;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public interface UserSignupContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<User> signup(User user);
//        Observable<User> login(User user);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void signupResult();
//        void loginResult(User user);
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<UserSignupContract.View, UserSignupContract.Model> {
        public abstract void signupRequest(User user);
//        public abstract void loginRequest(User user);
    }
}
