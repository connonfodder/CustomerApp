/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.model.UserInfoModel;
import com.aadhk.customer.ui.fragment.SettingDetailFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UserInfoPersenterTest extends BasePresenterTest<UserInfoModel, SettingDetailFragment, UserInfoPersenter> {

    @Override
    public void init() {
        model = spy(UserInfoModel.class);
        view = mock(SettingDetailFragment.class);
        presenter = spy(UserInfoPersenter.class);
    }

    @Test
    public void updateInfoRequest() throws Exception {
        User user = new User();
        user.setId(TestConstant.UserPwdTest_USER_ID);
        user.setContactAddress1("test me");
        user.setTelephone("123123123");
        presenter.updateInfoRequest(user);
        verify(model).updateInfo(user);
        verify(view).updateInfoResult();
    }
}