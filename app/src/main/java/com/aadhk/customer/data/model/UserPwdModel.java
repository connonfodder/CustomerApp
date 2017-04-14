package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.UserPwdContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 30/11/2016.
 */

public class UserPwdModel implements UserPwdContract.Model {

    @Override
    public Observable<Object> updatePwd(User user) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", user.getId());
        body.put("password", user.getPassword());
        return API.getDefault().upadtepwd(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }
}
