package com.aadhk.customer.ui.activity;

import android.os.Bundle;

import com.aadhk.customer.R;
import com.aadhk.customer.ui.fragment.UserLoginFragment;
import com.aadhk.customer.ui.fragment.UserResetFragment;
import com.aadhk.customer.ui.fragment.UserSignupFragment;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 28/11/2016.
 */

public class UserActivity extends BaseActivity {
    private UserLoginFragment loginFragment;
    private UserSignupFragment signupFragment;
    private UserResetFragment resetFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.lay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        goLogoin();
    }

    public void goLogoin() {
//        addFragment(loginFragment == null ? loginFragment = new UserLoginFragment() : loginFragment);
        addFragment(  new UserLoginFragment()  );
    }

    public void goSignup() {
//        addFragment(signupFragment == null ? signupFragment = new UserSignupFragment() : signupFragment);
        addFragment( new UserSignupFragment()  );
    }

    public void goReset(){
//        addFragment(resetFragment == null ? resetFragment = new UserResetFragment() : resetFragment);
        addFragment( new UserResetFragment()  );
    }
}
