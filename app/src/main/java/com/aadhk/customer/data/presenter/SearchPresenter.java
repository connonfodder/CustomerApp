package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.SearchRequest;
import com.aadhk.customer.data.contract.SearchContract;
import com.aadhk.library.rx.RxSubscriber;

import java.util.List;

/**
 * Created by jack on 06/12/2016.
 */

public class SearchPresenter extends SearchContract.Presenter {

    @Override
    public void fetchHotAndHistroySearchRequest() {
        mRxManage.add(mModel.fetchHotAndHistroySearch().subscribe(new RxSubscriber<List<Search>>(mContext, false) {
            @Override
            protected void _onNext(List<Search> searches) {
                mView.fetchHotAndHistroySearchResult(searches);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }

    @Override
    public void searchRequest(final SearchRequest request) {
        mRxManage.add(mModel.search(request).subscribe(new RxSubscriber<List<Company>>(mContext, false) {

            @Override
            protected void _onNext(List<Company> entities) {
                mView.searchResult(entities, request.getType());
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.stopLoading();
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void deleteRecordRequest(long userId) {
        mRxManage.add(mModel.deleteRecord(userId).subscribe(new RxSubscriber<List<Search>>(mContext, true) {
            @Override
            protected void _onNext(List<Search> searches) {
                mView.fetchHotAndHistroySearchResult(searches);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                mView.stopLoading();
            }
        }));
    }
}
