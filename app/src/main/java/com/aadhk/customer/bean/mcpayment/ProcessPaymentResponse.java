package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class ProcessPaymentResponse {

    /**
     * Currency : SGD
     * Amount : 4.51
     * Email : pisv.viet.duong@gmail.com
     * TerminalId : 3115060001
     * FgKey : e49d35bf3be3b224308dbb8a4814596a
     * CustomerId : 84f3780f-c8b9-4048-9feb-ad7095c41485
     * Reference : 13Aut121
     * StatusUrl : https://maptest.mcpayment.net:8888/StatusURL.aspx
     * TransId : 674613440
     * AuthCode : null
     * Stan : null
     * CardCo : null
     * ResDt : null
     * ResCode : 1320
     * ResMsg : Card declined - Invalid Customer or expired Token 500
     * Version : 1.0
     * TimeStamp : 2016-12-30T10:10:27+08:00
     */

    private String Currency;
    private double Amount;
    private String Email;
    private String TerminalId;
    private String FgKey;
    private String CustomerId;
    private String Reference;
    private String StatusUrl;
    private String TransId;
    private String AuthCode;
    private String Stan;
    private String CardCo;
    private String ResDt;
    private String ResCode;
    private String ResMsg;
    private String Version;
    private String TimeStamp;

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

    public String getFgKey() {
        return FgKey;
    }

    public void setFgKey(String FgKey) {
        this.FgKey = FgKey;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getStatusUrl() {
        return StatusUrl;
    }

    public void setStatusUrl(String StatusUrl) {
        this.StatusUrl = StatusUrl;
    }

    public String getTransId() {
        return TransId;
    }

    public void setTransId(String TransId) {
        this.TransId = TransId;
    }

    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String AuthCode) {
        this.AuthCode = AuthCode;
    }

    public String getStan() {
        return Stan;
    }

    public void setStan(String Stan) {
        this.Stan = Stan;
    }

    public String getCardCo() {
        return CardCo;
    }

    public void setCardCo(String CardCo) {
        this.CardCo = CardCo;
    }

    public String getResDt() {
        return ResDt;
    }

    public void setResDt(String ResDt) {
        this.ResDt = ResDt;
    }

    public String getResCode() {
        return ResCode;
    }

    public void setResCode(String ResCode) {
        this.ResCode = ResCode;
    }

    public String getResMsg() {
        return ResMsg;
    }

    public void setResMsg(String ResMsg) {
        this.ResMsg = ResMsg;
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
        return "ProcessPaymentResponse{" +
                "Currency='" + Currency + '\'' +
                ", Amount=" + Amount +
                ", Email='" + Email + '\'' +
                ", TerminalId='" + TerminalId + '\'' +
                ", FgKey='" + FgKey + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", Reference='" + Reference + '\'' +
                ", StatusUrl='" + StatusUrl + '\'' +
                ", TransId='" + TransId + '\'' +
                ", AuthCode='" + AuthCode + '\'' +
                ", Stan='" + Stan + '\'' +
                ", CardCo='" + CardCo + '\'' +
                ", ResDt='" + ResDt + '\'' +
                ", ResCode='" + ResCode + '\'' +
                ", ResMsg='" + ResMsg + '\'' +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }
}
