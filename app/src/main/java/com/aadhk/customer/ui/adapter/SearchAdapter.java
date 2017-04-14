package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Search;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * adapter
 */
public class SearchAdapter extends RecyclerArrayAdapter<Search> {

    private Context context;
    public OnMyItemClickListener mOnItemClickListener;

    public final static int TYPE_NORMAL = 1;
    public final static int TYPE_CATEGORY = 2;

    public final static short ID_HOT = -1;
    public final static short ID_HISTROY = -2;

    public SearchAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            return new CategoryViewHolder(parent);
        }
        return new SearchItemViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        if (getViewType(position) == TYPE_CATEGORY) {
            ((CategoryViewHolder) holder).ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position, holder, TYPE_CATEGORY);
                    }
                }
            });
        } else {
            ((SearchItemViewHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position, holder, TYPE_NORMAL);
                    }
                }
            });
        }
    }

    @Override
    public int getViewType(int position) {
        int id = getItem(position).getCustomerId();
        if (id == ID_HOT || id == ID_HISTROY) {
            return TYPE_CATEGORY;
        }
        return TYPE_NORMAL;
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder, int type);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class SearchItemViewHolder extends BaseViewHolder<Search> {
        TextView tv;

        public SearchItemViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_item_search);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        @Override
        public void setData(Search data) {
            super.setData(data);
            tv.setText(data.getKeyWord());
        }
    }

    private class CategoryViewHolder extends BaseViewHolder<Search> {
        TextView tvName;
        ImageView ivDelete;

        public CategoryViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_search_category);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
        }

        @Override
        public void setData(Search data) {
            super.setData(data);
            tvName.setText(data.getKeyWord());
            if (data.getCustomerId() == ID_HOT) ivDelete.setVisibility(View.GONE);
        }
    }
}
