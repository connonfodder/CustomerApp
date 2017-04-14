package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class CheckOutInitResponse {

    /**
     * ResCode : 200
     * ResMsg : Success.
     * CallbackUrl : https://maptest.mcpayment.net/MasterPass/CallBackURL
     * RedirectUrl : https://maptest.mcpayment.net/MasterPass/RedirectURL?requestToken=d4d2dfbfe588348bcb8b61df88a798d9b3b9fdff&checkoutIdentifier=a4a6w4vd3oxx6ih55dqw41ih75m4p81vew&callbackUrl=https://maptest.mcpayment.net/MasterPass/CallBackURL
     * Version : 1.0
     * TimeStamp : 2016-12-30T09:50:09+08:00
     */

    private String ResCode;
    private String ResMsg;
    private String CallbackUrl;
    private String RedirectUrl;
    private String Version;
    private String TimeStamp;

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

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String CallbackUrl) {
        this.CallbackUrl = CallbackUrl;
    }

    public String getRedirectUrl() {
        return RedirectUrl;
    }

    public void setRedirectUrl(String RedirectUrl) {
        this.RedirectUrl = RedirectUrl;
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
        return "CheckOutInitResponse{" +
                "ResCode='" + ResCode + '\'' +
                ", ResMsg='" + ResMsg + '\'' +
                ", CallbackUrl='" + CallbackUrl + '\'' +
                ", RedirectUrl='" + RedirectUrl + '\'' +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }
}
