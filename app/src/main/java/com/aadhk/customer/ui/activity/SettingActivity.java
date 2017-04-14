package com.aadhk.customer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.ui.fragment.SettingCategoryFragment;
import com.aadhk.customer.ui.fragment.SettingDetailFragment;
import com.aadhk.customer.ui.fragment.SettingPasswordFragment;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 29/11/2016.
 */

public class SettingActivity extends BaseActivity {
    private SettingCategoryFragment categoryFragment;
    private SettingPasswordFragment pwdFragment;
    private SettingDetailFragment detailsFragment;

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
        goCategory();
    }

    public void goCategory() {
//        addFragment(categoryFragment == null ? categoryFragment = new SettingCategoryFragment() : categoryFragment);
        addFragment(new SettingCategoryFragment());
    }

    public void goDetail() {
//        addFragment(detailsFragment == null ? detailsFragment = new SettingDetailFragment() : detailsFragment);
        addFragment(new SettingDetailFragment());
    }

    public void goPassword() {
//        addFragment(pwdFragment == null ? pwdFragment = new SettingPasswordFragment() : pwdFragment);
        addFragment(new SettingPasswordFragment());
    }


    public void goLocations() {
        Intent intent = new Intent(SettingActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}
