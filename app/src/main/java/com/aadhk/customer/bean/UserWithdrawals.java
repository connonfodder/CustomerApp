package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserWithdrawals implements Parcelable {
    private long id;
    private long userId;
    private String userName;
    private double withdrawal;          //金额
    private String payMethod;           //支付方式(交互的时候提醒客户输入 渠道+ 账号  例如: 支付宝 XXXXXXX )
    private String applicationTimes;    //申请时间
    private String operationTimes;      //操作时间
    private int status;                 //0 待处理  1 已退款  2 已拒绝


    public UserWithdrawals() {
    }

    public UserWithdrawals(double withdrawal, String payMethod, String applicationTimes, int status) {
        this.withdrawal = withdrawal;
        this.payMethod = payMethod;
        this.applicationTimes = applicationTimes;
        this.status = status;
    }

    public UserWithdrawals(long userId, String userName, double withdrawal, String payMethod, int status) {
        this.userId = userId;
        this.userName = userName;
        this.withdrawal = withdrawal;
        this.payMethod = payMethod;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getApplicationTimes() {
        return applicationTimes;
    }

    public void setApplicationTimes(String applicationTimes) {
        this.applicationTimes = applicationTimes;
    }

    public String getOperationTimes() {
        return operationTimes;
    }

    public void setOperationTimes(String operationTimes) {
        this.operationTimes = operationTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.userId);
        dest.writeString(this.userName);
        dest.writeDouble(this.withdrawal);
        dest.writeString(this.payMethod);
        dest.writeString(this.applicationTimes);
        dest.writeString(this.operationTimes);
        dest.writeInt(this.status);
    }

    protected UserWithdrawals(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.userName = in.readString();
        this.withdrawal = in.readDouble();
        this.payMethod = in.readString();
        this.applicationTimes = in.readString();
        this.operationTimes = in.readString();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<UserWithdrawals> CREATOR = new Parcelable.Creator<UserWithdrawals>() {
        @Override
        public UserWithdrawals createFromParcel(Parcel source) {
            return new UserWithdrawals(source);
        }

        @Override
        public UserWithdrawals[] newArray(int size) {
            return new UserWithdrawals[size];
        }
    };
}
