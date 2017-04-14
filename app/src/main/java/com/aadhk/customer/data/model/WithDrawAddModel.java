package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.WithDrawAddContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public class WithDrawAddModel implements WithDrawAddContract.Model {

    @Override
    public Observable<Object> withDraw(UserWithdrawals userWithdrawals) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userWithdrawals", userWithdrawals);
        return API.getDefault().addWithDraw(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }

}
