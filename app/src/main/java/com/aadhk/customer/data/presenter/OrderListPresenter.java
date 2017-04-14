package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.contract.OrderListContract;
import com.aadhk.library.rx.RxSubscriber;

import java.util.List;


/**
 * Created by jack on 25/11/2016.
 */

public class OrderListPresenter extends OrderListContract.Presenter {

    @Override
    public void cancelOrderRequest(long orderId) {
        mRxManage.add(mModel.cancelOrder(orderId).subscribe(new RxSubscriber<Object>(mContext, true) {

            @Override
            protected void _onNext(Object o) {
                mView.cancelResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }

    @Override
    public void getOrderListRequest(long userId, int page) {
        mRxManage.add(mModel.loadOrderList(userId, page).subscribe(new RxSubscriber<List<Order>>(mContext, false) {

            @Override
            protected void _onNext(List<Order> resultsEntities) {
                mView.returnOrderListData(resultsEntities);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }

    @Override
    public void deleteOrderRequest(long orderId) {
        mRxManage.add(mModel.deleteOrder(orderId).subscribe(new RxSubscriber<Object>(mContext, true) {

            @Override
            protected void _onNext(Object o) {
                mView.deleteResult();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }
}
