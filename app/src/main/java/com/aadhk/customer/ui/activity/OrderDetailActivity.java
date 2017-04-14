package com.aadhk.customer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.ui.fragment.OrderDetailFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 08/12/2016.
 */

public class OrderDetailActivity extends BaseActivity {
    private OrderDetailFragment orderDetailFragment;
    private Order order;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.lay;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        order = getIntent().getParcelableExtra(Constant.BUNDLE_DETAIL_ORDER);
        goDetail();
    }

    private void goDetail() {
        addFragment(orderDetailFragment == null ? orderDetailFragment = new OrderDetailFragment() : orderDetailFragment);
    }

    public Order getOrder(){ return this.order; }
}
