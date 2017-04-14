/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.data.model.TakeOrderingModel;
import com.aadhk.customer.ui.fragment.TakeOrderingFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TakeOrderingPresenterTest extends BasePresenterTest<TakeOrderingModel, TakeOrderingFragment, TakeOrderingPresenter> {

    @Override
    public void init() {
        model = spy(TakeOrderingModel.class);
        view = mock(TakeOrderingFragment.class);
        presenter = spy(TakeOrderingPresenter.class);
    }

    @Test
    public void chooseAddressRequest() throws Exception {
        presenter.chooseAddressRequest(TestConstant.UserPwdTest_USER_ID, TestConstant.TAKEORDERINGTest_COMPANYID, TestConstant.UserPwdTest_LONGTITUDE, TestConstant.UserPwdTest_LATITUDE);
    }

    @Test
    public void companyDetailRequest() throws Exception {
        presenter.companyDetailRequest(TestConstant.TAKEORDERINGTest_COMPANYID);
        verify(model).companyDetail(TestConstant.TAKEORDERINGTest_COMPANYID);
        verify(view).returnCompanyDetailData(any(Company.class));
    }
}