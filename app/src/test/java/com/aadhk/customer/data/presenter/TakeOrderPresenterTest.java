/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.model.TakeOrderModel;
import com.aadhk.customer.ui.fragment.FinishOrderFragment;
import com.aadhk.customertest.FileUtil;
import com.aadhk.customertest.TestConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TakeOrderPresenterTest extends BasePresenterTest<TakeOrderModel, FinishOrderFragment, TakeOrderPresenter> {

    @Override
    public void init() {
        model = spy(TakeOrderModel.class);
        view = mock(FinishOrderFragment.class);
        presenter = spy(TakeOrderPresenter.class);
    }

    @Test
    public void takeOrderRequest() throws Exception {
        String jsonFullPath = getClass().getResource(TestConstant.JSON_ROOT_PATH).toURI().getPath();
        String result = FileUtil.readFile(jsonFullPath + "order.json", "UTF-8").toString();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Order>() {
        }.getType();
        Order order = gson.fromJson(result, collectionType);
        presenter.takeOrderRequest(order);
        verify(model).takeOrder(order);
        verify(view).takeOrderRequestResult(any(Order.class));
    }

}