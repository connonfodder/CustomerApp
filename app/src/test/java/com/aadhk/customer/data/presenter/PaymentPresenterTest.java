/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import android.util.Log;

import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.model.PaymentModel;
import com.aadhk.customer.ui.fragment.PaymentFragment;
import com.aadhk.customertest.FileUtil;
import com.aadhk.customertest.TestConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PaymentPresenterTest extends BasePresenterTest<PaymentModel, PaymentFragment, PaymentPresenter> {

    @Override
    public void init() {
        model = spy(PaymentModel.class);
        view = mock(PaymentFragment.class);
        presenter = spy(PaymentPresenter.class);
    }

    @Test
    public void payRequest() throws Exception {
        String jsonFullPath = getClass().getResource(TestConstant.JSON_ROOT_PATH).toURI().getPath();
        String result = FileUtil.readFile(jsonFullPath + "order.json", "UTF-8").toString();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Order>() {}.getType();
        Order order = gson.fromJson(result, collectionType);

        presenter.payRequest(order);
        verify(model).pay(order);
        verify(view).payResult();
    }
}