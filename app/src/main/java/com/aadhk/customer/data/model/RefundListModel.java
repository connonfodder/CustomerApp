package com.aadhk.customer.data.model;

import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.RefundListContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public class RefundListModel implements RefundListContract.Model {
    @Override
    public Observable<Object> refund(long orderId, String reason) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("orderId", orderId);
        body.put("reason", reason);
        return API.getDefault().refundOrder(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }
}
