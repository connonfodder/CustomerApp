package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.User;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public interface UserAddressContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<User> operate(User user, int operation);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void operateResult(User user);
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<UserAddressContract.View, UserAddressContract.Model> {
        public abstract void operateRequest(Address address, int operation);
    }
}
