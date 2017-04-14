package com.aadhk.customer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;

/**
 * Created by jack on 06/12/2016.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView tvItemName;
    TextView tvPrice;
    RelativeLayout layAdapterItem;
    ImageView ivSubtract;
    ImageView ivAdd;
    TextView tvQty;
    TextView tvIntroduce;
    ImageView ivLogo;

    public ItemViewHolder(View itemView) {
        super(itemView);
        tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
        tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        layAdapterItem = (RelativeLayout) itemView.findViewById(R.id.layAdapterItem);
        ivSubtract = (ImageView) itemView.findViewById(R.id.ivSubtract);
        ivAdd = (ImageView) itemView.findViewById(R.id.ivAdd);
        tvQty = (TextView) itemView.findViewById(R.id.tvQty);
        ivLogo = (ImageView) itemView.findViewById(R.id.ivLogo);
        tvIntroduce = (TextView) itemView.findViewById(R.id.tvIntroduce);
    }
}