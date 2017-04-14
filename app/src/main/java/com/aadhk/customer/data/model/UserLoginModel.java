package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.UserLoginContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public class UserLoginModel implements UserLoginContract.Model {

    @Override
    public Observable<User> vertifyUser(User user) {
        return API.getDefault().login(user)
                .compose(RxSchedulers.<Response<User>>io_main())
                .compose(RxHelper.<User>handleResult());
    }
}
