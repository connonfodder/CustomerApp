package com.aadhk.customer.data.model;


import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.UserResetContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 29/11/2016.
 */

public class UserResetModel implements UserResetContract.Model {

    @Override
    public Observable<Object> reset(String email) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("email", email);
        return API.getDefault().reset(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
               .compose(RxHelper.handleResult());
    }
}
