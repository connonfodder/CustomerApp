/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.model.LocationListModel;
import com.aadhk.customer.ui.fragment.LocationListFragment;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class LocationListPresenterTest extends BasePresenterTest<LocationListModel, LocationListFragment, LocationListPresenter> {

    @Override
    public void init() {
        model = spy(LocationListModel.class);
        view = mock(LocationListFragment.class);
        presenter = spy(LocationListPresenter.class);
    }

    @Test
    public void fetchAllLocationRequest() throws Exception {
        presenter.fetchAllLocationRequest();
        verify(model).fetchAllLocation();
        verify(view).fetchAllLocationResult(ArgumentMatchers.<Address>anyList());
    }

}