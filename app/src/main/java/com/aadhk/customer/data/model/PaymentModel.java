package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.PaymentContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public class PaymentModel implements PaymentContract.Model {

    @Override
    public Observable<Object> pay(Order order) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("order", order);
        return API.getDefault().payOrder(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }
}
