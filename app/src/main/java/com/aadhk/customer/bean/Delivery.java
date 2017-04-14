package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *	@ClassName: Delivery.java
 *	@author:    zsf
 *	@version:   V1.0 
 *	@Date:      2016年10月26日 下午3:56:51
 */
public class Delivery implements Parcelable {
	private Integer id;
	private Integer companyId;
	private String distance;
	private String deliveryFee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	@Override
	public String toString() {
		return "Delivery{" +
				"id=" + id +
				", companyId=" + companyId +
				", distance='" + distance + '\'' +
				", deliveryFee='" + deliveryFee + '\'' +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.id);
		dest.writeValue(this.companyId);
		dest.writeString(this.distance);
		dest.writeString(this.deliveryFee);
	}

	public Delivery() {
	}

	protected Delivery(Parcel in) {
		this.id = (Integer) in.readValue(Integer.class.getClassLoader());
		this.companyId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.distance = in.readString();
		this.deliveryFee = in.readString();
	}

	public static final Parcelable.Creator<Delivery> CREATOR = new Parcelable.Creator<Delivery>() {
		@Override
		public Delivery createFromParcel(Parcel source) {
			return new Delivery(source);
		}

		@Override
		public Delivery[] newArray(int size) {
			return new Delivery[size];
		}
	};
}
