package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 28/11/2016.
 * "success":"true",
 * "msg":"查询成功",
 * "data":[
 * {
 * "id":"1001",
 * "userName","jack",
 * "password":"123456mima",
 * "email":"jack@gmail.com",
 * "telephone":"12312321",
 * "contactAddress1":"shenzhen",
 * "contactAddress2":"tiandiyuan",
 * "contactAddress3":"shengtang 603",
 * "accountBalance":"0",
 * "masterAccount":"XXXXFFFF1213"
 * },
 * {
 * "id":"1001",
 * "userName","jack",
 * "password":"123456mima",
 * "email":"jack@gmail.com",
 * "telephone":"12312321",
 * "contactAddress1":"shenzhen",
 * "contactAddress2":"tiandiyuan",
 * "contactAddress3":"shengtang 603",
 * "accountBalance":"0",
 * "masterAccount":"XXXXFFFF1213"
 * },
 * .......
 * ]
 */

public class User implements Parcelable {
    private long id;
    private String userName;            //用户名
    private String password;            //密码
    private String email;               //联系邮箱
    private String telephone;        //联系电话
    private String contactAddress1;     //联系地址
    private String contactAddress2;     //联系地址
    private String contactAddress3;     //联系地址
    private double accountBalance;      //账户金额
    private String masterAccount;

    public User( ) {

    }

    public User(String email, String pwd) {
        this.email = email;
        this.password = pwd;
    }
    public User(long id,  String pwd) {
        this.id = id;
        this.password = pwd;
    }

    public User(String email, String name, String telephone, String pwd) {
        this.email = email;
        this.userName = name;
        this.telephone = telephone;
        this.password = pwd;
    }

    public User(String userName, String password, String telephone, String contactAddress1, String contactAddress2, String contactAddress3, String email) {
        this.userName = userName;
        this.password = password;
        this.telephone = telephone;
        this.contactAddress1 = contactAddress1;
        this.contactAddress2 = contactAddress2;
        this.contactAddress3 = contactAddress3;
        this.email = email;
    }

    public User(String userName, String telephone, String contactAddress1, String contactAddress2, String contactAddress3, String email) {
        this.userName = userName;
        this.telephone = telephone;
        this.contactAddress1 = contactAddress1;
        this.contactAddress2 = contactAddress2;
        this.contactAddress3 = contactAddress3;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContactAddress1() {
        return contactAddress1;
    }

    public void setContactAddress1(String contactAddress1) {
        this.contactAddress1 = contactAddress1;
    }

    public String getContactAddress2() {
        return contactAddress2;
    }

    public void setContactAddress2(String contactAddress2) {
        this.contactAddress2 = contactAddress2;
    }

    public String getContactAddress3() {
        return contactAddress3;
    }

    public void setContactAddress3(String contactAddress3) {
        this.contactAddress3 = contactAddress3;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(String masterAccount) {
        this.masterAccount = masterAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", contactAddress1='" + contactAddress1 + '\'' +
                ", contactAddress2='" + contactAddress2 + '\'' +
                ", contactAddress3='" + contactAddress3 + '\'' +
                ", accountBalance=" + accountBalance +
                ", masterAccount='" + masterAccount + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeString(this.email);
        dest.writeString(this.telephone);
        dest.writeString(this.contactAddress1);
        dest.writeString(this.contactAddress2);
        dest.writeString(this.contactAddress3);
        dest.writeDouble(this.accountBalance);
        dest.writeString(this.masterAccount);
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.userName = in.readString();
        this.password = in.readString();
        this.email = in.readString();
        this.telephone = in.readString();
        this.contactAddress1 = in.readString();
        this.contactAddress2 = in.readString();
        this.contactAddress3 = in.readString();
        this.accountBalance = in.readDouble();
        this.masterAccount = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
