package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class RetrieveCheckOutDataTokenRequest {

    /**
     * MerchantId : ed0dY0KKjizn5pp3KW0s
     * TerminalId : 3115060001
     * OAuthtoken : 8759f09fc207cb0ac1a1884a34fa7cfca99da298
     * OAuthverifier : 793744baa5763288c9ed0101fd28d9ff4b3f455a
     * CheckoutResourceUrl : https://sandbox.api.mastercard.com/masterpass/v6/checkout/674613440
     * Version : 1.0
     * TimeStamp : 2015­05­26T11:20:04+08:00
     */

    private String MerchantId;
    private String TerminalId;
    private String OAuthtoken;
    private String OAuthverifier;
    private String CheckoutResourceUrl;
    private String Version;
    private String TimeStamp;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String MerchantId) {
        this.MerchantId = MerchantId;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String TerminalId) {
        this.TerminalId = TerminalId;
    }

    public String getOAuthtoken() {
        return OAuthtoken;
    }

    public void setOAuthtoken(String OAuthtoken) {
        this.OAuthtoken = OAuthtoken;
    }

    public String getOAuthverifier() {
        return OAuthverifier;
    }

    public void setOAuthverifier(String OAuthverifier) {
        this.OAuthverifier = OAuthverifier;
    }

    public String getCheckoutResourceUrl() {
        return CheckoutResourceUrl;
    }

    public void setCheckoutResourceUrl(String CheckoutResourceUrl) {
        this.CheckoutResourceUrl = CheckoutResourceUrl;
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

    @Override
    public String toString() {
        return "RetrieveCheckOutDataTokenRequest{" +
                "MerchantId='" + MerchantId + '\'' +
                ", TerminalId='" + TerminalId + '\'' +
                ", OAuthtoken='" + OAuthtoken + '\'' +
                ", OAuthverifier='" + OAuthverifier + '\'' +
                ", CheckoutResourceUrl='" + CheckoutResourceUrl + '\'' +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }
}
