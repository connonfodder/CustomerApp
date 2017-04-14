package com.aadhk.customer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.ui.fragment.SearchFragment;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 05/12/2016.
 */

public class SearchActivity extends BaseActivity {

    private SearchFragment searchFragment;

    @Override
    protected int getContentViewId() {return R.layout.activity_fragment;}

    @Override
    protected int getFragmentContentId() {return R.id.lay;}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        goSearch();
    }

    private void goSearch() {
        addFragment(searchFragment == null ? searchFragment = new SearchFragment() : searchFragment);
    }
}
