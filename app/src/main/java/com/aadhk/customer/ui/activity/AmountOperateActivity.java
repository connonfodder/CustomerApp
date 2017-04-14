package com.aadhk.customer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.ui.fragment.RefundListFragment;
import com.aadhk.customer.ui.fragment.WithDrawAddFragment;
import com.aadhk.customer.ui.fragment.WithDrawListFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 15/12/2016.
 */

public class AmountOperateActivity extends BaseActivity {
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
        order = getIntent().getParcelableExtra(Constant.BUNDLE_AMOUNT_OPERATE_ORDER);
        if (order != null) {
            goRefund();     //退款
        } else {
            goWithDrawList();
        }
    }

    private void goWithDrawList() {addFragment(new WithDrawListFragment());}

    public void goRefund() {
        addFragment(new RefundListFragment());
    }

    public void addWithDraw() {
        addFragment(new WithDrawAddFragment());
    }

    public Order getOrder() {
        return order;
    }
}
