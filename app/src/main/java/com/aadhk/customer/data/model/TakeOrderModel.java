package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.TakeOrderContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 06/12/2016.
 */

public class TakeOrderModel implements TakeOrderContract.Model {

    @Override
    public Observable<Order> takeOrder(Order order) {
        HashMap<String ,Object> body = new HashMap<>();
        body.put("order", order);
        return API.getDefault().initOrder(body)
                .compose(RxSchedulers.<Response<Order>>io_main())
                .compose(RxHelper.<Order>handleResult());
    }

   /* private Order getOrder(Order order) {
        order.setId(1001);
        order.setOrderTime(new Date().toString());
        order.setCustomerName("aha~");
        order.setAmount(777);
        order.setOrderNum("xxxx11111xxx");
        order.setMasterpassAccount("FSX10212312XCX");
        return order;
    }*/
}
