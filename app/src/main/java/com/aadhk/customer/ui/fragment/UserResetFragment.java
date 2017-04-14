package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.data.contract.UserResetContract;
import com.aadhk.customer.data.model.UserResetModel;
import com.aadhk.customer.data.presenter.UserResetPresenter;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 28/11/2016.
 */

public class UserResetFragment extends BaseFragment<UserResetPresenter, UserResetModel> implements UserResetContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tvHint)
    TextView tvHint;
    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.btnReset)
    ButtonRectangle btnReset;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_reset;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void resetResult() {
        etEmail.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        tvHint.setText(R.string.reset_succeed);
    }

    @Override
    public void showLoading(String title) {}

    @Override
    public void stopLoading() {}

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong("" + msg);
    }

    @Override
    protected void initView() {
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    String email;

    private boolean validate() {
        email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.errorEmpty));
            return false;
        }

        if (FormatUtil.isEmail(email)) {
            etEmail.setError(getString(R.string.errorEmail));
            return false;
        }
        return true;
    }


    @OnClick(R.id.btnReset)
    public void onClick() {
        if (validate())
            mPresenter.resetRequest(email);
    }
}
