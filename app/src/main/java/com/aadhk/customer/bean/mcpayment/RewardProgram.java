package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */
public class RewardProgram {
    /**
     * RewardNumber : null
     * RewardId : null
     * RewardName : null
     * ExpiryMonthSpecified : false
     * ExpiryYearSpecified : false
     */

    private String RewardNumber;
    private String RewardId;
    private String RewardName;
    private boolean ExpiryMonthSpecified;
    private boolean ExpiryYearSpecified;

    public String getRewardNumber() {
        return RewardNumber;
    }

    public void setRewardNumber(String RewardNumber) {
        this.RewardNumber = RewardNumber;
    }

    public String getRewardId() {
        return RewardId;
    }

    public void setRewardId(String RewardId) {
        this.RewardId = RewardId;
    }

    public String getRewardName() {
        return RewardName;
    }

    public void setRewardName(String RewardName) {
        this.RewardName = RewardName;
    }

    public boolean isExpiryMonthSpecified() {
        return ExpiryMonthSpecified;
    }

    public void setExpiryMonthSpecified(boolean ExpiryMonthSpecified) {
        this.ExpiryMonthSpecified = ExpiryMonthSpecified;
    }

    public boolean isExpiryYearSpecified() {
        return ExpiryYearSpecified;
    }

    public void setExpiryYearSpecified(boolean ExpiryYearSpecified) {
        this.ExpiryYearSpecified = ExpiryYearSpecified;
    }

    @Override
    public String toString() {
        return "RewardProgram{" +
                "RewardNumber='" + RewardNumber + '\'' +
                ", RewardId='" + RewardId + '\'' +
                ", RewardName='" + RewardName + '\'' +
                ", ExpiryMonthSpecified=" + ExpiryMonthSpecified +
                ", ExpiryYearSpecified=" + ExpiryYearSpecified +
                '}';
    }
}
