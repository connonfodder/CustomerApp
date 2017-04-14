/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.SearchRequest;
import com.aadhk.customer.data.model.SearchModel;
import com.aadhk.customer.ui.fragment.SearchFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SearchPresenterTest extends BasePresenterTest<SearchModel, SearchFragment, SearchPresenter> {

    @Override
    public void init() {
        model = spy(SearchModel.class);
        view = mock(SearchFragment.class);
        presenter = spy(SearchPresenter.class);
    }

    @Test
    public void fetchHotAndHistroySearchRequest() throws Exception {
        presenter.fetchHotAndHistroySearchRequest();
        verify(model).fetchHotAndHistroySearch();
        verify(view).fetchHotAndHistroySearchResult(ArgumentMatchers.<Search>anyList());
    }

    @Test
    public void searchRequest() throws Exception {
        SearchRequest request = new SearchRequest();
        presenter.searchRequest(request);
        verify(model).search(request);
        verify(view).searchResult(ArgumentMatchers.<Company>anyList(), anyInt());
    }

    @Test
    @Ignore
    public void deleteRecordRequest() throws Exception {
        presenter.deleteRecordRequest(TestConstant.UserPwdTest_USER_ID);
        verify(model).deleteRecord(TestConstant.UserPwdTest_USER_ID);
        verify(view).fetchHotAndHistroySearchResult(ArgumentMatchers.<Search>anyList());
    }

}