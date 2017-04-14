package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 16-11-10.
 */
public class CategoryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Category> mMenuList;
    private int mSelectedNum;
    private List<onItemSelectedListener> mSelectedListenerList;
    private int selection = 0;

    public interface onItemSelectedListener {
        void onLeftItemSelected(int postion, Category menu);
    }

    public void addItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null)
            mSelectedListenerList.add(listener);
    }

    public void removeItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty())
            mSelectedListenerList.remove(listener);
    }

    public CategoryAdapter(Context mContext, List<Category> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
        this.mSelectedNum = -1;
        this.mSelectedListenerList = new ArrayList<>();
        if (mMenuList.size() > 0)
            mSelectedNum = 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_left_category, parent, false);
        return new LeftMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Category category = mMenuList.get(position);
        LeftMenuViewHolder viewHolder = (LeftMenuViewHolder) holder;
        viewHolder.menuName.setText(category.getName());
        boolean isSelected = mSelectedNum == position;
        viewHolder.line.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        viewHolder.menuLayout.setSelected(isSelected);
        /*if (position == selection) {
            viewHolder.menuName.setBackgroundResource(R.drawable.rec_red_left_stroke);
            viewHolder.menuName.setTextColor(mContext.getResources().getColor(R.color.black));
        } else {
            viewHolder.menuName.setBackgroundResource(R.drawable.empty);
            viewHolder.menuName.setTextColor(context.getResources().getColor(R.color.gray));
        }*/
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public void setSelectedNum(int selectedNum) {
        if (selectedNum < getItemCount() && selectedNum >= 0) {
            this.mSelectedNum = selectedNum;
            notifyDataSetChanged();
        }
    }

    public Category getClikedCategory(){
        return mMenuList.get(selection);
    }

    public int getSelectedNum() {
        return mSelectedNum;
    }

    private class LeftMenuViewHolder extends RecyclerView.ViewHolder {
        View line;
        TextView menuName;
        RelativeLayout menuLayout;

        public LeftMenuViewHolder(final View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.line);
            menuName = (TextView) itemView.findViewById(R.id.tvLeftMenu);
            menuLayout = (RelativeLayout) itemView.findViewById(R.id.layLeftMenu);
            menuLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selection = getAdapterPosition();
                    notifyItemSelected(selection);
                }
            });
        }
    }

    private void notifyItemSelected(int position) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
            for (onItemSelectedListener listener : mSelectedListenerList) {
                listener.onLeftItemSelected(position, mMenuList.get(position));
            }
        }
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

}
