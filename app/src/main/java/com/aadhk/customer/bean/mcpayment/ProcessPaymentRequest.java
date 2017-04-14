package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class ProcessPaymentRequest {

    /**
     * MerchantId : ed0dY0KKjizn5pp3KW0s
     * Currency : SGD
     * Amount : 4.51
     * Email : pisv.viet.duong@gmail.com
     * TerminalId : 3115060001
     * Reference : 13Aut121
     * CustomerId : e69aca57-af28-41c2-8922-de9e44eb863c
     * CheckoutResourceUrl : https://sandbox.api.mastercard.com/masterpass/v6/checkout/674558614
     * TransactionId : 674558614
     * FgKey : e09e08ba755ec001c30653a5d9d385cc
     * StatusUrl : https://maptest.mcpayment.net:8888/StatusURL.aspx
     * Version : 1.0
     * TimeStamp : 2015­05­26T11:23:18+08:00
     */

    private String MerchantId;
    private String Currency;
    private double Amount;
    private String Email;
    private String TerminalId;
    private String Reference;
    private String CustomerId;
    private String CheckoutResourceUrl;
    private String TransactionId;
    private String FgKey;
    private String StatusUrl;
    private String Version;
    private String TimeStamp;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String MerchantId) {
        this.MerchantId = MerchantId;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String TerminalId) {
        this.TerminalId = TerminalId;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCheckoutResourceUrl() {
        return CheckoutResourceUrl;
    }

    public void setCheckoutResourceUrl(String CheckoutResourceUrl) {
        this.CheckoutResourceUrl = CheckoutResourceUrl;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String TransactionId) {
        this.TransactionId = TransactionId;
    }

    public String getFgKey() {
        return FgKey;
    }

    public void setFgKey(String FgKey) {
        this.FgKey = FgKey;
    }

    public String getStatusUrl() {
        return StatusUrl;
    }

    public void setStatusUrl(String StatusUrl) {
        this.StatusUrl = StatusUrl;
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
        return "ProcessPaymentRequest{" +
                "MerchantId='" + MerchantId + '\'' +
                ", Currency='" + Currency + '\'' +
                ", Amount=" + Amount +
                ", Email='" + Email + '\'' +
                ", TerminalId='" + TerminalId + '\'' +
                ", Reference='" + Reference + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", CheckoutResourceUrl='" + CheckoutResourceUrl + '\'' +
                ", TransactionId='" + TransactionId + '\'' +
                ", FgKey='" + FgKey + '\'' +
                ", StatusUrl='" + StatusUrl + '\'' +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }
}
