package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.util.money.MathUtil;
import com.aadhk.library.utils.ToastUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LocationListAdapter extends RecyclerArrayAdapter<Address> {

    private Context context;
    public OnMyItemClickListener mOnItemClickListener;
    private double longitude, latitude, maxDistance;

    public LocationListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null ) {  //
                    if((v.getContentDescription() != null && v.getContentDescription().equals("disable"))){
                        ToastUtil.showShort(context.getString(R.string.beyond_delivery_arrange));
                    }else{
                        mOnItemClickListener.onItemClick(position, holder);
                    }
                }
            }
        });
    }

    public void disableUnServiceAddress(double longitude, double latitude, double maxDistance) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.maxDistance = maxDistance;
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class LocationViewHolder extends BaseViewHolder<Address> {
        @InjectView(R.id.layAdapterItem)
        View layAdapterItem;
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvPhone)
        TextView tvPhone;
        @InjectView(R.id.tvAddress)
        TextView tvAddress;
        @InjectView(R.id.tvEmail)
        TextView tvEmail;
        @InjectView(R.id.tvZipCode)
        TextView tvZipCode;
        @InjectView(R.id.tvLabel)
        TextView tvLabel;

        public LocationViewHolder(ViewGroup parent) {
            super(parent, R.layout.adapter_location_item);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(Address data) {
            super.setData(data);
            tvName.setText(data.getUserName());
            tvPhone.setText(data.getPhone());
            tvAddress.setText(data.getAddress() + " " + data.getAddressLine());
            tvEmail.setText(data.getEmail());
            tvZipCode.setText(context.getString(R.string.ZipCodeM) + data.getZipCode());
            if(data.getLabel()==0){
                tvLabel.setVisibility(View.GONE);
            }else{
                tvLabel.setText(getLabel(data.getLabel()));
            }
            if (longitude != 0 && latitude != 0) {
                double distance = MathUtil.earthDistance(longitude, latitude, data.getLongitude(), data.getLatitude());
//                Log.d("location", "data=" + data.toString());
//                Log.d("location", "longitude=" + longitude + ", latitude="+latitude);
//                Log.d("location", "distance=" + distance/1000 + "km");  // sds ds
                if (distance > maxDistance) {
                    itemView.setContentDescription("disable");
                    tvName.setTextColor(context.getResources().getColor(R.color.disable));
                    tvAddress.setTextColor(context.getResources().getColor(R.color.disable));
                }
            }
        }

        private int getLabel(int type) {
            int[] res = {R.string.lbNo, R.string.lbHome, R.string.lbSchool, R.string.lbCompany};
            return res[type];
        }
    }
}