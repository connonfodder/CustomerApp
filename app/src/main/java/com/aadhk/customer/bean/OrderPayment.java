/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderPayment implements Parcelable {

	private long id;
	private long orderId;
	private double amount;
	private String paymentTime;
	private String paymentMethodName;
	private int paymentMethodType;		//对应付款对象的ID
	private String cashierName;			//操作员
	private double changeAmt;
	private double paidAmt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public int getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(int paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public double getChangeAmt() {
		return changeAmt;
	}

	public void setChangeAmt(double changeAmt) {
		this.changeAmt = changeAmt;
	}

	public double getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(double paidAmt) {
		this.paidAmt = paidAmt;
	}

	@Override
	public String toString() {
		return "OrderPayment [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", paymentTime=" + paymentTime + ", paymentMethodName=" + paymentMethodName + ", paymentMethodType=" + paymentMethodType + ", cashierName=" + cashierName + ", changeAmt=" + changeAmt + ", paidAmt=" + paidAmt + "]";
	}

	public OrderPayment() {
		super();
	}

	protected OrderPayment(Parcel in) {
		id = in.readLong();
		orderId = in.readLong();
		amount = in.readDouble();
		paymentTime = in.readString();
		paymentMethodName = in.readString();
		paymentMethodType = in.readInt();
		cashierName = in.readString();
		changeAmt = in.readDouble();
		paidAmt = in.readDouble();
	}

	public OrderPayment clone() {
		Parcel p = Parcel.obtain();
		p.writeValue(this);
		p.setDataPosition(0);
		OrderPayment newItem = (OrderPayment) p.readValue(OrderPayment.class.getClassLoader());
		p.recycle();
		return newItem;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(orderId);
		dest.writeDouble(amount);
		dest.writeString(paymentTime);
		dest.writeString(paymentMethodName);
		dest.writeInt(paymentMethodType);
		dest.writeString(cashierName);
		dest.writeDouble(changeAmt);
		dest.writeDouble(paidAmt);
	}

	@SuppressWarnings("unused")
	public static final Creator<OrderPayment> CREATOR = new Creator<OrderPayment>() {
		@Override
		public OrderPayment createFromParcel(Parcel in) {
			return new OrderPayment(in);
		}

		@Override
		public OrderPayment[] newArray(int size) {
			return new OrderPayment[size];
		}
	};
}
