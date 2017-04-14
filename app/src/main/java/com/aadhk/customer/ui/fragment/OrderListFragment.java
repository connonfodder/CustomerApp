package com.aadhk.customer.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.contract.OrderListContract;
import com.aadhk.customer.data.model.OrderListModel;
import com.aadhk.customer.data.presenter.OrderListPresenter;
import com.aadhk.customer.ui.activity.AmountOperateActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.OrderDetailActivity;
import com.aadhk.customer.ui.adapter.OrderAdapter;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.NetWorkUtils;
import com.gc.materialdesign.widgets.Dialog;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import rx.functions.Action1;

/**
 * Created by jack on 28/11/2016.
 */
public class OrderListFragment extends BaseFragment<OrderListPresenter, OrderListModel> implements OrderListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @InjectView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<Order> data;
    private static int mStartPage = 1;
    private OrderAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshListener(this);
        mAdapter = new OrderAdapter(getContext());
        recyclerView.setAdapterWithProgress(mAdapter);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                mAdapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                mAdapter.resumeMore();
            }
        });
        mAdapter.setOnMyItemClickListener(new OrderAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, int type, Order order) {
                switch (type) {
                    case Constant.TYPE_VIEW:
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Constant.BUNDLE_DETAIL_ORDER, data.get(position));
                        startActivity(OrderDetailActivity.class, bundle);
                        break;
                    case Constant.TYPE_DELETE:
                        final long orderId = order.getId();
                        Dialog dialog = new Dialog(getActivity(), getString(R.string.delete_order), getString(R.string.delete_order_hint));
                        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.deleteOrderRequest(orderId);
                            }
                        });
                        dialog.show();
                        break;
                    case Constant.TYPE_REFUND:
                        Bundle b = new Bundle();
                        b.putParcelable(Constant.BUNDLE_AMOUNT_OPERATE_ORDER, order);
                        startActivity(AmountOperateActivity.class, b);
                        break;
                    case Constant.TYPE_CANCEL:
                        final long orderid = order.getId();
                        Dialog dialogC = new Dialog(getActivity(), getString(R.string.cancel_order), getString(R.string.cancel_order_hint));
                        dialogC.setOnAcceptButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.cancelOrderRequest(orderid);
                            }
                        });
                        dialogC.show();
                        break;
                }

            }
        });
        mRxManager.on(Constant.RXBUS_FRESH_ORDER_LIST, new Action1<Object>() {
            @Override
            public void call(Object result) {
                onRefresh();
            }
        });
        load();
    }

/*    @Override
    public void onResume() {
        super.onResume();
        if (CustomerApplication.getInstance().getUser() != null) {
            long userId = CustomerApplication.getInstance().getUser().getId();
            load(userId, mStartPage);
        } else {
            mAdapter.clear();
            recyclerView.setRefreshing(false);
            showErrorTip(getString(R.string.please_login_first));
        }
    }*/

    @Override
    public void deleteResult() {
        onRefresh();
    }

    @Override
    public void cancelResult() {
        onRefresh();
    }

    @Override
    public void returnOrderListData(List<Order> datas) {
        if (mStartPage == 1) {
            data.clear();
            mAdapter.clear();
        } else if (datas.size() == 0) {
            mAdapter.stopMore();
            return;
        }
        data.addAll(datas);
        mAdapter.addAll(datas);
        if (data.size() == 0) showErrorTip(getString(R.string.empty));
    }

    private void load(){
        if (CustomerApplication.getInstance().getUser() != null) {
            long userId = CustomerApplication.getInstance().getUser().getId();
            load(userId, mStartPage);
        } else {
            mAdapter.clear();
            recyclerView.setRefreshing(false);
            showErrorTip(getString(R.string.please_login_first));
        }
    }

    @Override
    public void onRefresh() {
        mStartPage = 1;
        mAdapter.clear();
        load();
    }

    @Override
    public void onLoadMore() {
        if (CustomerApplication.getInstance().getUser() != null) {
            long userId = CustomerApplication.getInstance().getUser().getId();
            mStartPage++;
            load(userId, mStartPage);
        }
    }

    private void load(long userId, int startPage) {
        if (!NetWorkUtils.isNetConnected(getContext())) {
            mAdapter.clear();
            showErrorTip(getString(R.string.error_network));
            return;
        }
        mPresenter.getOrderListRequest(userId, startPage);
    }

    @Override
    public void showLoading(String title) {
        recyclerView.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        recyclerView.setRefreshing(false);
    }

    @Override
    public void showErrorTip(String msg) {
        View view = recyclerView.getEmptyView();
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(msg);
        recyclerView.showEmpty();
    }
}
