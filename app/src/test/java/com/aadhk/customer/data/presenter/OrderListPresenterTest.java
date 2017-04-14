/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.model.OrderListModel;
import com.aadhk.customer.ui.fragment.OrderListFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderListPresenterTest extends BasePresenterTest<OrderListModel, OrderListFragment, OrderListPresenter> {

    @Override
    public void init() {
        model = spy(OrderListModel.class);
        view = mock(OrderListFragment.class);
        presenter = spy(OrderListPresenter.class);
    }

    @Test
    public void a_getOrderListRequest() throws Exception {
        presenter.getOrderListRequest(TestConstant.UserPwdTest_USER_ID, 1);
        verify(model).loadOrderList(TestConstant.UserPwdTest_USER_ID, 1);
        verify(view).returnOrderListData(ArgumentMatchers.<Order>anyList());
    }

    @Test
    public void cancelOrderRequest() throws Exception {
        presenter.cancelOrderRequest(TestConstant.OrderListTest_CANCEL_ORDER_ID);
        verify(model).cancelOrder(TestConstant.OrderListTest_CANCEL_ORDER_ID);
        verify(view).cancelResult();
    }

    @Test
    public void deleteOrderRequest() throws Exception {
        presenter.deleteOrderRequest(TestConstant.OrderListTest_DELETE_ORDER_ID);
        verify(model).deleteOrder(TestConstant.OrderListTest_DELETE_ORDER_ID);
        verify(view).deleteResult();
    }
}