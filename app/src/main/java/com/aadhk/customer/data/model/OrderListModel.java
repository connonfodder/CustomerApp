package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.OrderListContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by jack on 25/11/2016.
 */

public class OrderListModel implements OrderListContract.Model {

    @Override
    public Observable<Object> cancelOrder(long orderId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("orderId", orderId);
        return API.getDefault().cancelOrder(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }

    @Override
    public Observable<List<Order>> loadOrderList(long userId, int page) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("page", page);
        return API.getDefault().queryOrder(body)
                .compose(RxSchedulers.<Response<List<Order>>>io_main())
                .compose(RxHelper.<List<Order>>handleResult());
    }

    @Override
    public Observable<Object> deleteOrder(long orderId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("orderId", orderId);
        return API.getDefault().deleteOrder(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }

 /*   private List<Order> getData() {
        List<Order> data = new ArrayList<>();
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_NO, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p0.meituan.net/350.214/deal/db03b779cb75e8b3795479a67cb93040379560.jpg@0_61_2000_1212a%7C388h_640w_2e_90Q", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_FINISH, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p1.meituan.net/350.214/deal/__18266532__1756691.jpg.webp", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_NO, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p1.meituan.net/350.214/deal/d2765123024933f4673d59d1c6733fcf106604.jpg.webp", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DINEIN, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_FINISH, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p0.meituan.net/350.214/deal/db03b779cb75e8b3795479a67cb93040379560.jpg@0_61_2000_1212a%7C388h_640w_2e_90Q", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_NO, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p1.meituan.net/350.214/deal/c190c2576f46b97a7d8e60378799d411101457.jpg.webp", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_ING, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p1.meituan.net/350.214/deal/__18266532__1756691.jpg.webp", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_TAKEOUT, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_NO, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p0.meituan.net/350.214/deal/db03b779cb75e8b3795479a67cb93040379560.jpg@0_61_2000_1212a%7C388h_640w_2e_90Q", "千滋百味自助烤肉火锅", "12312213213"));
        data.add(new Order(getItems(), Constant.TYPE_DELIVERY, "2016-12-08 16:44", "JACK", Constant.DELIVERY_STATUS_FINISH, 232, 23, "TIANDIYUAN EAST BLOCK 603", "http://p1.meituan.net/350.214/deal/d2765123024933f4673d59d1c6733fcf106604.jpg.webp", "千滋百味自助烤肉火锅", "12312213213"));
        return data;
    }

    private List<OrderItem> getItems() {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("itemName", 12, 3));
        items.add(new OrderItem("wer", 14, 1));
        items.add(new OrderItem("itemName", 22, 5));
        items.add(new OrderItem("dsfs", 6, 1));
        items.add(new OrderItem("dd", 8, 1));
        items.add(new OrderItem("ggg", 34, 12));
        items.add(new OrderItem("nnn", 2, 1));
        return items;
    }*/
}
