package com.aadhk.customer.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Item;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.util.StringUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jack on 05/12/2016.
 */

public class ShopCarAdapter extends RecyclerArrayAdapter<OrderItem> {

    ItemAdapter itemAdapter;
    private Activity activity;
    private Order order;
    private List<OrderItem> dataList;

    public ShopCarAdapter(Context context, Activity activity, ItemAdapter itemAdapter, List<OrderItem> orderingList, Order order) {
        super(context);
        this.activity = activity;
        this.itemAdapter = itemAdapter;
        this.dataList = orderingList;
        this.order = order;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopCarViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        final OrderItem bean = getItem(position);
        ShopCarViewHolder shopCarViewHolder = (ShopCarViewHolder) holder;
        shopCarViewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.handleData(true, new Item(bean.getItemId()));
                notifyItemChanged(position);
                itemAdapter.notifyDataSetChanged();
            }
        });
        shopCarViewHolder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.handleData(false, new Item(bean.getItemId()));
                notifyItemChanged(position);
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    class ShopCarViewHolder extends BaseViewHolder<OrderItem> {

        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvPrice)
        TextView tvPrice;
        @InjectView(R.id.ivAdd)
        ImageView ivAdd;
        @InjectView(R.id.tvCount)
        TextView tvCount;
        @InjectView(R.id.ivRemove)
        ImageView ivRemove;
        @InjectView(R.id.layLeft)
        RelativeLayout layLeft;
        @InjectView(R.id.layRight)
        LinearLayout layRight;

        public ShopCarViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_item_product);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(OrderItem data) {
            super.setData(data);
            StringUtils.filtNull(tvName, data.getItemName());//商品名称
            StringUtils.filtNull(tvPrice, "$" + data.getPrice());//商品价格
            tvCount.setText(String.valueOf(data.getQty()));//商品数量
        }
    }
}
