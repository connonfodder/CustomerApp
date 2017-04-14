package com.aadhk.customer.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Item;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.StringUtils;

import java.util.List;

/**
 * Created by fengyongge on 2016/5/24 0024.
 */

/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    ItemAdapter itemAdapter;
    private Activity activity;
    private Order order;
    private List<OrderItem> dataList;
    private AppSettings settings ;
    public ProductAdapter(Activity activity, ItemAdapter itemAdapter, List<OrderItem> orderingList, Order order) {
        this.activity = activity;
        this.itemAdapter = itemAdapter;
        this.dataList = orderingList;
        this.order = order;
        settings = CustomerApplication.getSetting();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.adapter_item_product, null);
            viewholder = new Viewholder();
            viewholder.tvName = (TextView) view.findViewById(R.id.tvName);
            viewholder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            viewholder.ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
            viewholder.ivRemove = (ImageView) view.findViewById(R.id.ivRemove);
            viewholder.tvCount = (TextView) view.findViewById(R.id.tvCount);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        final OrderItem bean = dataList.get(position);
        StringUtils.filtNull(viewholder.tvName, bean.getItemName());        //商品名称
        StringUtils.filtNull(viewholder.tvPrice, FormatUtil.displayAmount( settings.getCurrencyPosition(), settings.getDecimalPlace(), bean.getPrice(), settings.getCurrencyStr())); //商品价格
        viewholder.tvCount.setText(String.valueOf(bean.getQty()));          //商品数量
        viewholder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.handleData(true, new Item(bean.getItemId()));
                notifyDataSetChanged();
                itemAdapter.notifyDataSetChanged();
            }
        });
        viewholder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.handleData(false, new Item(bean.getItemId()));
                notifyDataSetChanged();
                itemAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    class Viewholder {
        TextView tvPrice;
        TextView tvName;
        ImageView ivAdd, ivRemove;
        TextView tvCount;
    }
}