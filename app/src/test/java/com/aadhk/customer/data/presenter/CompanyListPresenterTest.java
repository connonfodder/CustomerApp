/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.CompanyRequest;
import com.aadhk.customer.data.model.CompanyListModel;
import com.aadhk.customer.ui.fragment.CompanyListFragment;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CompanyListPresenterTest extends BasePresenterTest<CompanyListModel, CompanyListFragment, CompanyListPresenter> {

    @Override
    public void init() {
        model = spy(CompanyListModel.class);
        view = mock(CompanyListFragment.class);
        presenter = spy(CompanyListPresenter.class);
    }

    @Test
    public void getCompanyListDataRequest() throws Exception {
        CompanyRequest request = new CompanyRequest();
        request.setPage(1);
        request.setCount(10);
        request.setRestaurantType(7);
        presenter.getCompanyListDataRequest(request);
        verify(model).loadCompanyData(request);
        verify(view).returnCompanyListData(ArgumentMatchers.<Company>anyList());
    }
}