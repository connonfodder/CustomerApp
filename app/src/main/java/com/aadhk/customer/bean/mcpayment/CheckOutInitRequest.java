package com.aadhk.customer.bean.mcpayment;

import java.util.List;

/**
 * Created by jack on 30/12/2016.
 */

public class CheckOutInitRequest {

    /**
     * MerchantId : ed0dY0KKjizn5pp3KW0s
     * ProcessType : STANDARD
     * CallbackUrl : https://maptest.mcpayment.net/MasterPass/CallBackURL
     * Currency : SGD
     * Items : [{"item":"Iphone Cover","price":2.51,"quantity":1,"imageurl":""},{"item":"Instant Noodle","price":2,"quantity":1,"imageurl":""}]
     * Version : 1.0
     * TimeStamp : 2016­05­27T09:44:17+07:00
     */

    private String MerchantId;
    private String ProcessType;
    private String CallbackUrl;
    private String Currency;
    private String Version;
    private String TimeStamp;
    private List<MasterPassItem> Items;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String MerchantId) {
        this.MerchantId = MerchantId;
    }

    public String getProcessType() {
        return ProcessType;
    }

    public void setProcessType(String ProcessType) {
        this.ProcessType = ProcessType;
    }

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String CallbackUrl) {
        this.CallbackUrl = CallbackUrl;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public List<MasterPassItem> getItems() {
        return Items;
    }

    public void setItems(List<MasterPassItem> Items) {
        this.Items = Items;
    }

    @Override
    public String toString() {
        return "CheckOutInitRequest{" +
                "MerchantId='" + MerchantId + '\'' +
                ", ProcessType='" + ProcessType + '\'' +
                ", CallbackUrl='" + CallbackUrl + '\'' +
                ", Currency='" + Currency + '\'' +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                ", Items=" + Items +
                '}';
    }
}
