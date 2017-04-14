package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Item;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.ShopCartImp;
import com.aadhk.library.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by cheng on 16-11-10.
 * 这个适配器有点特殊, 因为考虑到性能，就不多增加一个Recyclerview
 * 所有的下单数据都存放在ItemAdapter中, 下单页面的核心就是ItemAdapter, 里面包含一个Map对象用来存放已经下单的各种数据
 * 因为需要搜索所以需要新的适配器来筛选数据，ItemAdapter里面的判断太复杂了，为了减轻特此增加了这个适配器
 * 只需要筛选后的Item集合，其余的操作均在ItemAdapter中，保持了数据的同一性
 * author: jack chen
 * last-modify: 2016-12-06 21:41:59
 */
public class ItemSearchAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Item> itemList;
    private ShopCartImp shopCartImp;
    private ItemAdapter itemAdapter;
    private AppSettings settings;
    public ItemSearchAdapter(Context mContext, List<Item> itemList, ItemAdapter itemAdapter) {
        this.mContext = mContext;
        this.itemList = itemList;
        this.itemAdapter = itemAdapter;
        settings = CustomerApplication.getSetting();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_right_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        if (itemHolder != null) {
            final Item item = itemList.get(position);
            itemHolder.tvItemName.setText(item.getName());
            itemHolder.tvPrice.setText(settings.getCurrencyStr() + item.getPrice());
            itemHolder.layAdapterItem.setContentDescription(position + "");
            ImageLoaderUtils.display(mContext, itemHolder.ivLogo, item.getLogoPath());
            itemHolder.tvIntroduce.setText(item.getDescription());
            int count = itemAdapter.orderItemMap.containsKey(item.getId()) ? (int) itemAdapter.orderItemMap.get(item.getId()).getQty() : 0;
            if (count <= 0) {
                itemHolder.ivSubtract.setVisibility(View.GONE);
                itemHolder.tvQty.setVisibility(View.GONE);
            } else {
                itemHolder.ivSubtract.setVisibility(View.VISIBLE);
                itemHolder.tvQty.setVisibility(View.VISIBLE);
                itemHolder.tvQty.setText(String.valueOf(count));
            }
            itemHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemAdapter.handleData(true, item)) {
                        notifyItemChanged(position);
                        if (shopCartImp != null) shopCartImp.add(view, position);  //动画效果展示
                    }
                }
            });

            itemHolder.ivSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemAdapter.handleData(false, item)) {
                        notifyItemChanged(position);
                        if (shopCartImp != null) shopCartImp.remove(view, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setShopCartImp(ShopCartImp shopCartImp) {
        this.shopCartImp = shopCartImp;
    }
}
