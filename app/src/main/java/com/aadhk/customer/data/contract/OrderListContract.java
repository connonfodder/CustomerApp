package com.aadhk.customer.data.contract;

import com.aadhk.customer.bean.Order;
import com.aadhk.library.ui.BaseModel;
import com.aadhk.library.ui.BasePresenter;
import com.aadhk.library.ui.BaseView;

import java.util.List;

import rx.Observable;


public interface OrderListContract {

    //实现这个接口的只负责    取数据
    interface Model extends BaseModel {
        Observable<List<Order>> loadOrderList(long userId, int page);
        Observable<Object> deleteOrder(long orderId);
        Observable<Object> cancelOrder(long orderId);
    }

    //实现这个接口的只负责    改动UI
    interface View extends BaseView {
        void returnOrderListData(List<Order> data);
        void deleteResult();
        void cancelResult();
    }

    //实现这个接口的只负责    使用RxBus获取Model的数据来调用View的方法
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getOrderListRequest(long userId, int page);
        public abstract void deleteOrderRequest(long orderId);
        public abstract void cancelOrderRequest(long orderId);
    }
}
