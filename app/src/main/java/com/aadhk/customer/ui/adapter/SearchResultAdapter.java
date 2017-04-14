package com.aadhk.customer.ui.adapter;

import android.content.Context;
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

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * adapter
 */
public class SearchResultAdapter extends RecyclerArrayAdapter<Company> {

    private Context context;
    public OnMyItemClickListener mOnItemClickListener;
    private AppSettings settings;

    public SearchResultAdapter(Context context) {
        super(context);
        this.context = context;
        settings = CustomerApplication.getSetting();
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
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class RestaurantListViewHolder extends BaseViewHolder<Company> {

        @InjectView(R.id.iv)
        ImageView iv;
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvDeliveryPrice)
        TextView tvDeliveryPrice;

        public RestaurantListViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_item_search_result);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(Company data) {
            super.setData(data);
            tvName.setText(data.getName());
            iv.setBackground(null);
            if (data.getType() == Constant.TYPE_NONE) {
                iv.setBackground(context.getResources().getDrawable(R.mipmap.ic_search));
                tvDeliveryPrice.setVisibility(View.GONE);
            } else {
//                iv.setBackground(context.getResources().getDrawable(R.mipmap.ic_restaurant));
//                LogUtil.d("logoPath="+ Constant.URL + data.getLogoPath());
                ImageLoaderUtils.displayCircle(getContext(), iv, Constant.URL +data.getLogoPath());
                tvDeliveryPrice.setText(context.getString(R.string.delivery) + FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), data.getAveCharge(), settings.getCurrencyStr()));
            }
        }
    }
}
