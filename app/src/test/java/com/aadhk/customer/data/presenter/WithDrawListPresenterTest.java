/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.model.WithDrawListModel;
import com.aadhk.customer.ui.fragment.WithDrawListFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class WithDrawListPresenterTest extends BasePresenterTest<WithDrawListModel, WithDrawListFragment, WithDrawListPresenter> {

    @Override
    public void init() {
        model = spy(WithDrawListModel.class);
        view = mock(WithDrawListFragment.class);
        presenter = spy(WithDrawListPresenter.class);
    }

    @Test
    public void queryRequest() throws Exception {
        presenter.queryRequest(TestConstant.UserPwdTest_USER_ID);
        verify(model).query(TestConstant.UserPwdTest_USER_ID);
        verify(view).queryResult(ArgumentMatchers.<UserWithdrawals>anyList());
    }

}