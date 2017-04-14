package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.UserInfoContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 30/11/2016.
 */

public class UserInfoModel implements UserInfoContract.Model {

    @Override
    public Observable<Object> updateInfo(User user) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", user);
        return API.getDefault().updateinfo(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }
}
