/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {

	//old
	private int id;
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private String zipCode;
	private String tel;
	private String email;
	private double expenseAmount;
	/**
	 * 会员类型与会员类型表的id关联(0表示不是会员)
	 */
	private int memberTypeId;
	private double prepaidAmount;
	private double rewardPoint;
//	private MemberType memberType;

	private String company;
	private String checkNum;
	private String phone;
	private boolean hasOrder;
	private boolean enable = true;
	
//	private MemberPrepaidLog memberPrepaidLog;
//	private MemberRewardLog memberRewardLog;
//	private List<MemberRewardLog> memberRewardLogList;
//	private List<MemberGiftLog> memberGiftLogList;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public int getMemberTypeId() {
		return memberTypeId;
	}

	public void setMemberTypeId(int memberTypeId) {
		this.memberTypeId = memberTypeId;
	}

	public double getPrepaidAmount() {
		return prepaidAmount;
	}

	public void setPrepaidAmount(double prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}

	public double getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(double rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isHasOrder() {
		return hasOrder;
	}

	public void setHasOrder(boolean hasOrder) {
		this.hasOrder = hasOrder;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeString(this.address1);
		dest.writeString(this.address2);
		dest.writeString(this.address3);
		dest.writeString(this.zipCode);
		dest.writeString(this.tel);
		dest.writeString(this.email);
		dest.writeDouble(this.expenseAmount);
		dest.writeInt(this.memberTypeId);
		dest.writeDouble(this.prepaidAmount);
		dest.writeDouble(this.rewardPoint);
		dest.writeString(this.company);
		dest.writeString(this.checkNum);
		dest.writeString(this.phone);
		dest.writeByte(this.hasOrder ? (byte) 1 : (byte) 0);
		dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
	}

	public Customer() {
	}

	protected Customer(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.address1 = in.readString();
		this.address2 = in.readString();
		this.address3 = in.readString();
		this.zipCode = in.readString();
		this.tel = in.readString();
		this.email = in.readString();
		this.expenseAmount = in.readDouble();
		this.memberTypeId = in.readInt();
		this.prepaidAmount = in.readDouble();
		this.rewardPoint = in.readDouble();
		this.company = in.readString();
		this.checkNum = in.readString();
		this.phone = in.readString();
		this.hasOrder = in.readByte() != 0;
		this.enable = in.readByte() != 0;
	}

	public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() {
		@Override
		public Customer createFromParcel(Parcel source) {
			return new Customer(source);
		}

		@Override
		public Customer[] newArray(int size) {
			return new Customer[size];
		}
	};
}
