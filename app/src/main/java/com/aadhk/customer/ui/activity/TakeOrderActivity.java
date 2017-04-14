package com.aadhk.customer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.ui.fragment.TakeOrderingFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 01/12/2016.
 */

public class TakeOrderActivity extends BaseActivity {
    private TakeOrderingFragment orderingFragment;
    private long companyId, tableId;
    private String tableName;

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
        Bundle bundle = getIntent().getExtras();
        companyId = bundle.getLong(Constant.BUNDLE_TAKR_ORDER_COMPANY_ID);
        tableId = bundle.getLong(Constant.BUNDLE_TAKR_ORDER_TABLE_ID);
        tableName = bundle.getString(Constant.BUNDLE_TAKR_ORDER_TABLE_NAME);
        goOrdering();
    }

    private void goOrdering() {
        addFragment(orderingFragment == null ? orderingFragment = new TakeOrderingFragment() : orderingFragment);
    }

    public long getCompanyId() {
        return companyId;
    }

    public long getTableId() {
        return tableId;
    }

    public String getTableName() {return tableName;}
}
