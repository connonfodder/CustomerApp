package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.DateUtil;
import com.aadhk.customer.util.FormatUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WithDrawListAdapter extends RecyclerArrayAdapter<UserWithdrawals> {
    private Context context;
    private AppSettings settings;
    public OnMyItemClickListener mOnItemClickListener;

    public WithDrawListAdapter(Context context) {
        super(context);
        this.context = context;
        settings = CustomerApplication.getSetting();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, holder);
                }
            }
        });
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class LocationViewHolder extends BaseViewHolder<UserWithdrawals> {

        @InjectView(R.id.tvAmount)
        TextView tvAmount;
        @InjectView(R.id.tvApplicationTime)
        TextView tvApplicationTime;
        @InjectView(R.id.etInfo)
        EditText etInfo;
        @InjectView(R.id.tvStatus)
        TextView tvStatus;

        public LocationViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_withdraw_item);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(UserWithdrawals data) {
            super.setData(data);
            tvAmount.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), data.getWithdrawal(), settings.getCurrencyStr()));
            tvApplicationTime.setText(DateUtil.displayDate(data.getApplicationTimes()));
            etInfo.setText(data.getPayMethod());
            tvStatus.setText(getLabel(data.getStatus()));
        }

        private int getLabel(int type) {
            switch (type) {
                case Constant.STATUS_WITHRRAW_HANDLEING:
                    return R.string.lbInProcess;
                case Constant.STATUS_WITHRRAW_FINISHED:
                    return R.string.lbFinished;
                case Constant.STATUS_WITHRRAW_REJECTED:
                    return R.string.lbRefused;
                default:
                    return R.string.lbUnknow;
            }
        }
    }
}