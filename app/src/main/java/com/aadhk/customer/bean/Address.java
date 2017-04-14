package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 29/11/2016.
 "success":"true",
 "msg":"查询成功",
 "data":[
     {
     "id":"1001",
     "userName","jack",
     "phone":"12312321",
     "address":"shenzhenfutian",
     "addressLine":"tiandiyuan 603",
     "zipCode":"58001",
     "email":"jack@gmail.com",
     "label":"0",
     "latitude":"34.12312",
     "longitude":"112.311321"
 },
     {
     "id":"1001",
     "userName","Tom",
     "phone":"12321312",
     "address":"usc Ohi",
     "addressLine":"Mca CX",
     "zipCode":"12001",
     "email":"tomcat@gmail.com",
     "label":"2",
     "latitude":"44.12312",
     "longitude":"212.311321"
     }
 .......
 ]
 */

public class Address implements Parcelable {
    private long id;
    private long userId;
    private String userName;
    private String phone;
    private String address;
    private String addressLine;
    private String zipCode;
    private String email;
    private int label;
    public double longitude;
    public double latitude;

    public Address(long userId) {
        this.userId = userId;
    }

    public Address(long userId, String userName, String phone, String address, String addressLine, String zipCode, String email, int label) {
        this(userName, phone, address, addressLine, zipCode, email, label);
        this.userId = userId;
    }

    public Address( String userName, String phone, String address, String addressLine, String zipCode, String email, int label) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.email = email;
        this.label = label;
    }

    public Address(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                ", label=" + label +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
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
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.addressLine);
        dest.writeString(this.zipCode);
        dest.writeString(this.email);
        dest.writeInt(this.label);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    protected Address(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.userName = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.addressLine = in.readString();
        this.zipCode = in.readString();
        this.email = in.readString();
        this.label = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
