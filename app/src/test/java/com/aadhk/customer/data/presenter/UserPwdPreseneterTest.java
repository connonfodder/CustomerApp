/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.model.UserPwdModel;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.fragment.SettingPasswordFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UserPwdPreseneterTest extends BasePresenterTest<UserPwdModel, SettingPasswordFragment, UserPwdPreseneter> {
    @Spy
    CustomerApplication application;
    @Override
    public void init() {
        model = spy(UserPwdModel.class);
        view = mock(SettingPasswordFragment.class);
        presenter = spy(UserPwdPreseneter.class);
    }

    @Test
    public void updatePwdRequest() throws Exception {
        User user = new User(TestConstant.UserPwdTest_USER_ID, TestConstant.UserPwdTest_USER_PWD);
        presenter.updatePwdRequest(user);
        verify(model).updatePwd(user);
        verify(view).updateResult();
    }
}