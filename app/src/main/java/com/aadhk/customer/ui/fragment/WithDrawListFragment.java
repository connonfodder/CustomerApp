package com.aadhk.customer.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.contract.WithDrawListContract;
import com.aadhk.customer.data.model.WithDrawListModel;
import com.aadhk.customer.data.presenter.WithDrawListPresenter;
import com.aadhk.customer.ui.activity.AmountOperateActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.adapter.WithDrawListAdapter;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class WithDrawListFragment extends BaseFragment<WithDrawListPresenter, WithDrawListModel> implements WithDrawListContract.View {

    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.btnAdd)
    ImageView btnAdd;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rlv)
    EasyRecyclerView rlv;

    private List<UserWithdrawals> data;
    private WithDrawListAdapter mAdapter;
    private long userId = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.queryRequest(userId);
    }

    @Override
    public void queryResult(List<UserWithdrawals> result) {
        if (result == null && result.size() == 0) {
            mAdapter.stopMore();
            return;
        }
        data.addAll(result);
        mAdapter.addAll(result);
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong(msg);
    }

    @Override
    protected void initView() {
        User user = CustomerApplication.getInstance().getUser();
        if(user!=null) userId = user.getId();
        tvTitle.setText(R.string.withdrawal_history);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(layoutManager);
        mAdapter = new WithDrawListAdapter(getContext());
        rlv.setAdapter(mAdapter);
        mAdapter.setOnMyItemClickListener(new WithDrawListAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
//                ((AmountOperateActivity) getActivity()).addWithDraw();   查看详细 不考虑，信息太少没必要
            }
        });
    }

    @OnClick(R.id.btnAdd)
    public void onClick() {
        ((AmountOperateActivity) getActivity()).addWithDraw();
    }
}
