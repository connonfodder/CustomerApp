/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.data.model.UserResetModel;
import com.aadhk.customer.ui.fragment.UserResetFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UserResetPresenterTest extends BasePresenterTest<UserResetModel, UserResetFragment, UserResetPresenter> {

    @Override
    public void init() {
        model = spy(UserResetModel.class);
        view = mock(UserResetFragment.class);
        presenter = spy(UserResetPresenter.class);
    }

    @Test
    public void resetRequest() throws Exception {
        presenter.resetRequest(TestConstant.UserLoginTest_USER_EMAIL);
        verify(model).reset(TestConstant.UserLoginTest_USER_EMAIL);
        verify(view).resetResult();
    }
}