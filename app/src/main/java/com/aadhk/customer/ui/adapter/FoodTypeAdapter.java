package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.FoodStyles;
import com.aadhk.library.animation.AnimationUtil;

import java.util.List;

/**
 * adapter
 * TODO: 改进  和历史搜索一样
 */
public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater mInflater;
    public OnMyItemClickListener mOnItemClickListener;
    private List<FoodStyles> data;
    private boolean[] record;

    public FoodTypeAdapter(Context context, List<FoodStyles> data) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.data = data;
        record = new boolean[data.size()];  //pic_default false
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.adapter_item_food_style, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getFoodstyle());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null) mOnItemClickListener.onItemClick(position, holder);
                if (record[position]) {  //选中 -> 原形
                    v.setBackground(context.getResources().getDrawable(R.drawable.item_bg_flow_round));
                    ((TextView)v).setTextColor(context.getResources().getColor(R.color.fiter_normal));
                } else {                 //原形 -> 选中
                    v.setBackground(context.getResources().getDrawable(R.drawable.item_bg_flow_round_opposite));
                    ((TextView)v).setTextColor(context.getResources().getColor(R.color.fiter_selected));
                }
                record[position] = !record[position];
            }
        });
        AnimationUtil.start(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, ViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public boolean[] getRecord() {
        return record;
    }
}
