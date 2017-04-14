/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.model.WithDrawAddModel;
import com.aadhk.customer.ui.fragment.WithDrawAddFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.DateUtil;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class WithDrawAddPresenterTest extends BasePresenterTest<WithDrawAddModel, WithDrawAddFragment, WithDrawAddPresenter> {

    @Override
    public void init() {
        model = spy(WithDrawAddModel.class);
        view = mock(WithDrawAddFragment.class);
        presenter = spy(WithDrawAddPresenter.class);
    }

    @Test
    public void withDrawRequest() throws Exception {
        UserWithdrawals userWithdrawals = new UserWithdrawals();
        userWithdrawals.setUserName("jack");
        userWithdrawals.setUserId(TestConstant.UserPwdTest_USER_ID);
        userWithdrawals.setWithdrawal(12);
        userWithdrawals.setStatus(Constant.STATUS_WITHRRAW_HANDLEING);
        userWithdrawals.setApplicationTimes(DateUtil.getDate());
        presenter.withDrawRequest(userWithdrawals);
        verify(model).withDraw(userWithdrawals);
        verify(view).withDrawResult();
    }

}