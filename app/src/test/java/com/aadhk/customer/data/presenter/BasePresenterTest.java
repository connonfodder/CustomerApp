/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import android.content.Context;

import com.aadhk.customertest.RxUnitTestTools;
import com.aadhk.library.ui.BasePresenter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public abstract class BasePresenterTest<M, V, P extends BasePresenter> {
    protected M model;
    protected V view;
    @Mock
    protected Context context;
    protected P presenter;

    @Before
    public void setUp() throws Exception {
        RxUnitTestTools.openRxTools();
        init();
        presenter.setVM(view, model, context);
    }

    public abstract void init();
}
