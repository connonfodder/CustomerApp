package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * adapter
 */
public class CompanyAdapter extends RecyclerArrayAdapter<Company> {

    private Context context;
    public OnMyItemClickListener mOnItemClickListener;
    private AppSettings settings;

    public CompanyAdapter(Context context) {
        super(context);
        this.context = context;
        settings = CustomerApplication.getSetting();
//        LogUtil.d("settings="+settings);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantListViewHolder(parent);
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
//        AnimationUtil.start(holder.itemView);
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class RestaurantListViewHolder extends BaseViewHolder<Company> {

        @InjectView(R.id.ivLogo)
        ImageView ivLogo;
        @InjectView(R.id.tvCompanyName)
        TextView tvRestaurantName;
        @InjectView(R.id.tvOrderNumber)
        TextView tvMinimumCharge;
        @InjectView(R.id.tvTag1)
        TextView tvTag1;
        @InjectView(R.id.tvTag2)
        TextView tvTag2;
        @InjectView(R.id.tvTag3)
        TextView tvTag3;
        @InjectView(R.id.tvDistance)
        TextView tvDistance;
        @InjectView(R.id.tvTypeDelivery)
        TextView tvTypeDelivery;
        @InjectView(R.id.tvTypeDineIn)
        TextView tvTypeDineIn;
        @InjectView(R.id.tvTypeTakeOut)
        TextView tvTypeTakeOut;

        public RestaurantListViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_item_company);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(Company data) {
            super.setData(data);
//            LogUtil.d(data.toString());
            ImageLoaderUtils.display(context, ivLogo, Constant.URL + data.getLogoPath());
            //TODO: 传参有问题
            if (data.isBusinessStatus()) {
                tvRestaurantName.setText(data.getName() + "( " + context.getString(R.string.lbRest) + " )");
            } else {
                tvRestaurantName.setText(data.getName());
            }
            if (data.getAveCharge() > 0){
                tvMinimumCharge.setText(String.valueOf(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), data.getAveCharge(), settings.getCurrencyStr()) + "/person"));
            }else{
                tvMinimumCharge.setText("");
            }
            List<String> styles = data.getStyles();
            tvTag1.setText("");
            tvTag2.setText("");
            tvTag3.setText("");
            TextView[] ets = new TextView[]{tvTag1, tvTag2, tvTag3};
            if (styles != null && styles.size() > 0) {
                for (int i = 0; i < styles.size() && i < ets.length; i++) {
                    String style = styles.get(i);
                    ets[i].setText(TextUtils.isEmpty(style) ? "" : style);
                }
            }
            if (data.getDistance() > 1000) {
                tvDistance.setText(String.valueOf(FormatUtil.displayQty(data.getDistance() / 1000) + "km"));
            } else {
                tvDistance.setText(String.valueOf(FormatUtil.displayQty(data.getDistance()) + "m"));
            }
            tvTypeDelivery.setVisibility(((data.getType() & Constant.TYPE_DELIVERY_VALUE) == Constant.TYPE_DELIVERY_VALUE) ? View.VISIBLE : View.GONE);
            tvTypeTakeOut.setVisibility(((data.getType() & Constant.TYPE_TAKEOUT_VALUE) == Constant.TYPE_TAKEOUT_VALUE) ? View.VISIBLE : View.GONE);
            tvTypeDineIn.setVisibility(((data.getType() & Constant.TYPE_DINEIN_VALUE) == Constant.TYPE_DINEIN_VALUE) ? View.VISIBLE : View.GONE);
        }
    }
}
