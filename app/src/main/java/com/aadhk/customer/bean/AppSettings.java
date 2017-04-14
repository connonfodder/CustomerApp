package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 30/11/2016.
 */

public class AppSettings implements Parcelable {

    private int id;
    private int currency;             //货币种类	1:¥  2:$  3:S$
    private String currencyStr;
    private int currencyPosition;     //货币格式	int	1:$100   2:100$   3:$ 100    4:100 $
    private int decimalPlace;         //小数点设置
    private int withdrawalInterval;   //退款申请间隔
    private double withdrawalMinimim; //最低提现金额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCurrencyPosition() {
        return currencyPosition;
    }

    public void setCurrencyPosition(int currencyPosition) {
        this.currencyPosition = currencyPosition;
    }
    //  // 1:¥  2:$
    public String getCurrencyStr() {
        return  getCurrency() == 1 ? "¥" : "$";
    }

    public int getDecimalPlace() {
        return decimalPlace;
    }

    public void setDecimalPlace(int decimalPlace) {
        this.decimalPlace = decimalPlace;
    }

    public int getWithdrawalInterval() {
        return withdrawalInterval;
    }

    public void setWithdrawalInterval(int withdrawalInterval) {
        this.withdrawalInterval = withdrawalInterval;
    }

    public double getWithdrawalMinimim() {
        return withdrawalMinimim;
    }

    public void setWithdrawalMinimim(double withdrawalMinimim) {
        this.withdrawalMinimim = withdrawalMinimim;
    }

    @Override
    public String toString() {
        return "AppSettings{" +
                "id=" + id +
                ", currency=" + currency +
                ", currencyPosition=" + currencyPosition +
                ", decimalPlace=" + decimalPlace +
                ", withdrawalInterval=" + withdrawalInterval +
                ", withdrawalMinimim=" + withdrawalMinimim +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.currency);
        dest.writeInt(this.currencyPosition);
        dest.writeInt(this.decimalPlace);
        dest.writeInt(this.withdrawalInterval);
        dest.writeDouble(this.withdrawalMinimim);
    }

    public AppSettings() {
    }

    protected AppSettings(Parcel in) {
        this.id = in.readInt();
        this.currency = in.readInt();
        this.currencyPosition = in.readInt();
        this.decimalPlace = in.readInt();
        this.withdrawalInterval = in.readInt();
        this.withdrawalMinimim = in.readDouble();
    }

    public static final Parcelable.Creator<AppSettings> CREATOR = new Parcelable.Creator<AppSettings>() {
        @Override
        public AppSettings createFromParcel(Parcel source) {
            return new AppSettings(source);
        }

        @Override
        public AppSettings[] newArray(int size) {
            return new AppSettings[size];
        }
    };
}
