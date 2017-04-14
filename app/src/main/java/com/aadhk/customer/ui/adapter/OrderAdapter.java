package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.DateUtil;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.LogUtil;
import com.gc.materialdesign.views.ButtonFlat;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.aadhk.customer.util.Constant.ORDER_STATUS_FINISH;
import static com.aadhk.customer.util.Constant.ORDER_STATUS_HANDLING;

/**
 * adapter
 */
public class OrderAdapter extends RecyclerArrayAdapter<Order> {

    private static Context context;
    public OnMyItemClickListener mOnItemClickListener;
    private AppSettings settings;

    public OrderAdapter(Context context) {
        super(context);
        OrderAdapter.context = context;
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
                    mOnItemClickListener.onItemClick(position, Constant.TYPE_VIEW, null);
                }
            }
        });
//        AnimationUtil.start(holder.itemView);
        final Order data = getItem(position);
        ((RestaurantListViewHolder) holder).btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(0, Constant.TYPE_CANCEL, data);
            }
        });

        ((RestaurantListViewHolder) holder).btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(0, Constant.TYPE_DELETE, data);
                }
            }
        });

        ((RestaurantListViewHolder) holder).btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(0, Constant.TYPE_REFUND, data);
            }
        });
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, int type, Order order);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private static String getTypeName(int type) {
        switch (type) {
            case Constant.ORDER_TYPE_DELIVERY_CUSTOMER:
                return context.getString(R.string.lbDelivery);
            case Constant.ORDER_TYPE_TAKEOUT_CUSTOMER:
                return context.getString(R.string.lbTakeout);
            case Constant.ORDER_TYPE_DINEIN_CUSTOMER:
                return context.getString(R.string.lbDineIn);
        }
        return "";
    }

    // 2  1
    private static String getStatus(int status, int orderStatus, int deliveryStatus) {
        if (status == Constant.STATUS_ORDER_NEW) {
            return context.getString(R.string.unpaid);
        }
        switch (orderStatus) {
            case Constant.ORDER_STATUS_REQUEST:
                return context.getString(R.string.lbHandling);
            case ORDER_STATUS_HANDLING:
                switch (deliveryStatus) {
                    case Constant.DELIVERY_STATUS_NO:
                        return context.getString(R.string.lbOrdered);
                    case Constant.DELIVERY_STATUS_ING:
                        return context.getString(R.string.lbDeliverying);
                    case Constant.DELIVERY_STATUS_FINISH:
                        return context.getString(R.string.lbFinish);
                }
            case Constant.ORDER_STATUS_REJECTED:
                return context.getString(R.string.lbRefused);
            case Constant.ORDER_STATUS_REFUNDING:
                return context.getString(R.string.lbRefunding);
            case Constant.ORDER_STATUS_REFUNDED:
                return context.getString(R.string.lbRefunded);
            case Constant.ORDER_STATUS_REFUND_REJECTED:
                return context.getString(R.string.lbRefundRefused);
            case Constant.ORDER_STATUS_CANCEL:
                return context.getString(R.string.lbCancelled);
            case ORDER_STATUS_FINISH:
                return context.getString(R.string.lbFinish);
        }
        return "";
    }

    class RestaurantListViewHolder extends BaseViewHolder<Order> {
        @InjectView(R.id.ivLogo)
        ImageView ivLogo;
        @InjectView(R.id.tvCompanyName)
        TextView tvRestaurantName;
        @InjectView(R.id.tvType)
        TextView tvType;
        @InjectView(R.id.tvStatus)
        TextView tvStatus;
        @InjectView(R.id.tvTime)
        TextView tvTime;
        @InjectView(R.id.btnCancel)
        ButtonFlat btnCancel;
        @InjectView(R.id.btnDelete)
        ButtonFlat btnDelete;
        @InjectView(R.id.btnRefund)
        ButtonFlat btnRefund;
        @InjectView(R.id.layItems)
        LinearLayout layItems;

        public RestaurantListViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_item_order);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(final Order data) {
            super.setData(data);
            LogUtil.d("--------setData-------------" + data.toString());
            ImageLoaderUtils.displayCircle(context, ivLogo, Constant.URL + data.getLogoPath());
            tvRestaurantName.setText(data.getCompanyName());
            tvType.setText(getTypeName(data.getOrderType()));
            tvStatus.setText(getStatus(data.getStatus(), data.getCustomerOrderStatus(), data.getDeliveryStatus()));
            tvTime.setText(DateUtil.displayDate(data.getOrderTime()));
            layItems.removeAllViews();
            int count = 0;
            for (OrderItem item : data.getOrderItems()) {
                if (count++ > 2) break;
                View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_ordered, null);
                TextView tvName = (TextView) view.findViewById(R.id.tvName);
                TextView tvAmount = (TextView) view.findViewById(R.id.tvAmount);
                double amount = item.getQty() * item.getPrice();
                tvName.setText(item.getItemName());
                tvAmount.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), amount, settings.getCurrencyStr()));
                layItems.addView(view);
            }

            int orderStatus = data.getCustomerOrderStatus();
            if ((orderStatus == ORDER_STATUS_FINISH || orderStatus == ORDER_STATUS_HANDLING || orderStatus == Constant.ORDER_STATUS_REFUND_REJECTED) && data.getOrderType() != Constant.ORDER_TYPE_DINEIN_CUSTOMER) {
                btnRefund.setVisibility(View.VISIBLE);
            } else {
                btnRefund.setVisibility(View.GONE);
            }

            if (orderStatus == Constant.ORDER_STATUS_REQUEST) {
                btnCancel.setVisibility(View.VISIBLE);
            } else {
                btnCancel.setVisibility(View.GONE);
            }

            if (orderStatus == Constant.ORDER_STATUS_REQUEST || orderStatus == ORDER_STATUS_HANDLING) {
                btnDelete.setVisibility(View.GONE);
            } else {
                btnDelete.setVisibility(View.VISIBLE);
            }

            int status = data.getStatus();
            if (status == Constant.STATUS_ORDER_PRE) {
                btnDelete.setVisibility(View.VISIBLE);
            }

            int orderType = data.getOrderType();
            if (orderType == Constant.ORDER_TYPE_DINEIN_CUSTOMER && orderStatus == Constant.ORDER_STATUS_FINISH) {
                btnDelete.setVisibility(View.GONE);
            }
        }
    }
}
