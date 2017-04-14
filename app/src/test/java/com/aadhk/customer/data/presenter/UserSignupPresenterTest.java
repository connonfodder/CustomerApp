/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.model.UserSignupModel;
import com.aadhk.customer.ui.fragment.UserSignupFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UserSignupPresenterTest extends BasePresenterTest<UserSignupModel, UserSignupFragment, UserSignupPresenter> {

    @Override
    public void init() {
        model = spy(UserSignupModel.class);
        view = mock(UserSignupFragment.class);
        presenter = spy(UserSignupPresenter.class);
    }

    @Test
    public void signupRequest() throws Exception {
        User user = new User();
        user.setEmail(TestConstant.SIGNUPTest_EMAIL);
        user.setTelephone("0000000000000");
        user.setContactAddress1("tessasat");
        user.setPassword(TestConstant.UserLoginTest_USER_PWD);
        user.setUserName("tessat");
        presenter.signupRequest(user);
        verify(model).signup(user);
        verify(view).signupResult();
    }
}