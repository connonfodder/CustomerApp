/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PromotionDiscount implements Parcelable {

	private long id;
	private long restaurantId;
	private String name;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private boolean sun;
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean enable;
	private int discountItemType;
	private List<Long> itemIds;
	private String itemNames;

	private List<Long> itemDiscountIds;
	private String itemDiscountNames;

	private double amtRate;
	private double requireQuantity;
	private int discountType;
	private int promotionType;

	public double getRequireQuantity() {
		return requireQuantity;
	}

	public void setRequireQuantity(double requireQuantity) {
		this.requireQuantity = requireQuantity;
	}

	public List<Long> getItemDiscountIds() {
		return itemDiscountIds;
	}

	public void setItemDiscountIds(List<Long> itemDiscountIds) {
		this.itemDiscountIds = itemDiscountIds;
	}

	public String getItemDiscountNames() {
		return itemDiscountNames;
	}

	public void setItemDiscountNames(String itemDiscountNames) {
		this.itemDiscountNames = itemDiscountNames;
	}

	public int getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isSun() {
		return sun;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public boolean isMon() {
		return mon;
	}

	public void setMon(boolean mon) {
		this.mon = mon;
	}

	public boolean isTue() {
		return tue;
	}

	public void setTue(boolean tue) {
		this.tue = tue;
	}

	public boolean isWed() {
		return wed;
	}

	public void setWed(boolean wed) {
		this.wed = wed;
	}

	public boolean isThu() {
		return thu;
	}

	public void setThu(boolean thu) {
		this.thu = thu;
	}

	public boolean isFri() {
		return fri;
	}

	public void setFri(boolean fri) {
		this.fri = fri;
	}

	public boolean isSat() {
		return sat;
	}


	public void setSat(boolean sat) {
		this.sat = sat;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public double getAmtRate() {
		return amtRate;
	}

	public void setAmtRate(double amtRate) {
		this.amtRate = amtRate;
	}

	public List<Long> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Long> itemIds) {
		this.itemIds = itemIds;
	}

	public String getItemNames() {
		return itemNames;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	
	public int getDiscountItemType() {
		return discountItemType;
	}

	public void setDiscountItemType(int discountItemType) {
		this.discountItemType = discountItemType;
	}

	@Override
	public String toString() {
		return "PromotionDiscount [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime + ", sun=" + sun + ", mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thu=" + thu + ", fri=" + fri + ", sat=" + sat + ", enable=" + enable + ", discountItemType=" + discountItemType + ", itemIds=" + itemIds + ", itemNames=" + itemNames + ", itemDiscountIds=" + itemDiscountIds + ", itemDiscountNames=" + itemDiscountNames + ", amtRate=" + amtRate + ", requireQuantity=" + requireQuantity + ", discountType=" + discountType + ", promotionType=" + promotionType + "]";
	}

	public PromotionDiscount() {
		super();
		itemIds = new ArrayList<>();
		itemDiscountIds = new ArrayList<>();
	}

	protected PromotionDiscount(Parcel in) {
		id = in.readLong();
		name = in.readString();
		startDate = in.readString();
		endDate = in.readString();
		startTime = in.readString();
		endTime = in.readString();
		itemNames = in.readString();
		itemDiscountNames = in.readString();
		sun = in.readByte() != 0x00;
		mon = in.readByte() != 0x00;
		tue = in.readByte() != 0x00;
		wed = in.readByte() != 0x00;
		thu = in.readByte() != 0x00;
		fri = in.readByte() != 0x00;
		sat = in.readByte() != 0x00;
		enable = in.readByte() != 0x00;
		if (in.readByte() == 0x01) {
			itemIds = new ArrayList<>();
			in.readList(itemIds, Long.class.getClassLoader());
		} else {
			itemIds = null;
		}

		if (in.readByte() == 0x01) {
			itemDiscountIds = new ArrayList<>();
			in.readList(itemDiscountIds, Long.class.getClassLoader());
		} else {
			itemIds = null;
		}

		amtRate = in.readDouble();
		requireQuantity = in.readDouble();
		discountType = in.readInt();
		promotionType = in.readInt();
		discountItemType = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(startDate);
		dest.writeString(endDate);
		dest.writeString(startTime);
		dest.writeString(endTime);
		dest.writeString(itemNames);
		dest.writeString(itemDiscountNames);
		dest.writeByte((byte) (sun ? 0x01 : 0x00));
		dest.writeByte((byte) (mon ? 0x01 : 0x00));
		dest.writeByte((byte) (tue ? 0x01 : 0x00));
		dest.writeByte((byte) (wed ? 0x01 : 0x00));
		dest.writeByte((byte) (thu ? 0x01 : 0x00));
		dest.writeByte((byte) (fri ? 0x01 : 0x00));
		dest.writeByte((byte) (sat ? 0x01 : 0x00));
		dest.writeByte((byte) (enable ? 0x01 : 0x00));
		if (itemIds == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(itemIds);
		}
		if (itemDiscountIds == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(itemDiscountIds);
		}
		dest.writeDouble(amtRate);
		dest.writeDouble(requireQuantity);
		dest.writeInt(discountType);
		dest.writeInt(promotionType);
		dest.writeInt(discountItemType);
	}

	@SuppressWarnings("unused")
	public static final Creator<PromotionDiscount> CREATOR = new Creator<PromotionDiscount>() {
		@Override
		public PromotionDiscount createFromParcel(Parcel in) {
			return new PromotionDiscount(in);
		}

		@Override
		public PromotionDiscount[] newArray(int size) {
			return new PromotionDiscount[size];
		}
	};

}
