package com.aadhk.customer.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.contract.WithDrawAddContract;
import com.aadhk.customer.data.model.WithDrawAddModel;
import com.aadhk.customer.data.presenter.WithDrawAddPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.acra.ACRA;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class WithDrawAddFragment extends BaseFragment<WithDrawAddPresenter, WithDrawAddModel> implements WithDrawAddContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.etAmount)
    MaterialEditText etAmount;
    @InjectView(R.id.etCardNumber)
    EditText etCardNumber;
    @InjectView(R.id.btnConfirm)
    ButtonRectangle btnConfirm;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_withdraw;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
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

    @Override
    public void withDrawResult() {
        getActivity().onBackPressed();
    }

    @Override
    public void showLoading(String title) {}

    @Override
    public void stopLoading() {}

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong(msg);
    }

    @OnClick(R.id.btnConfirm)
    public void onClick() {
        String amountStr = etAmount.getText().toString();
        if(FormatUtil.isEmpty(amountStr)) {
            etAmount.setError(getString(R.string.errorEmpty));
            return;
        }
        String cardNumber = etCardNumber.getText().toString();
        if(FormatUtil.isEmpty(cardNumber)){
            etCardNumber.setError(getString(R.string.errorEmpty));
            return;
        }
        try {
            double amount = Double.parseDouble(amountStr);
            if(amount>0){
                User user = CustomerApplication.getInstance().getUser();
                if(amount > user.getAccountBalance()){
                    etAmount.setError(getString(R.string.errorBeyond));
                    return;
                }
                UserWithdrawals userWithdrawals = new UserWithdrawals(user.getId(), user.getUserName(), amount, cardNumber, Constant.ORDER_STATUS_REFUNDING);
                mPresenter.withDrawRequest(userWithdrawals);
            }
        }catch (Exception e){
            etAmount.setError(getString(R.string.errorFormat));
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
    }
}
