package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.UserSignupContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public class UserSignupModel implements UserSignupContract.Model {

    @Override
    public Observable<User> signup(User user) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", user);
        return API.getDefault().signup(body)
                .compose(RxSchedulers.<Response<User>>io_main())
                .compose(RxHelper.<User>handleResult());
    }
/*
    @Override
    public Observable<User> login(User user) {
        if (CustomerApplication.getInstance().getDevelopeMode()) {
            Observable<User> observable = Observable.just(new User("chenjian@qq.com", "jack", "15211213", "123"))
                    .compose(RxSchedulers.<User>io_main());
            return observable;
        } else {
            return API.getDefault().login(user)
                    .compose(RxSchedulers.<Response<User>>io_main())
                    .compose(RxHelper.<User>handleResult());
        }
    }*/
}
