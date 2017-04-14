package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class AuthenticationOptions {
    /**
     * AuthenticateMethod : null
     * CardEnrollmentMethod : null
     * CAvv : null
     * EciFlag : null
     * MasterCardAssignedID : null
     * PaResStatus : null
     * SCEnrollmentStatus : null
     * SignatureVerification : null
     * Xid : null
     */

    private String AuthenticateMethod;
    private String CardEnrollmentMethod;
    private String CAvv;
    private String EciFlag;
    private String MasterCardAssignedID;
    private String PaResStatus;
    private String SCEnrollmentStatus;
    private String SignatureVerification;
    private String Xid;

    public String getAuthenticateMethod() {
        return AuthenticateMethod;
    }

    public void setAuthenticateMethod(String AuthenticateMethod) {
        this.AuthenticateMethod = AuthenticateMethod;
    }

    public String getCardEnrollmentMethod() {
        return CardEnrollmentMethod;
    }

    public void setCardEnrollmentMethod(String CardEnrollmentMethod) {
        this.CardEnrollmentMethod = CardEnrollmentMethod;
    }

    public String getCAvv() {
        return CAvv;
    }

    public void setCAvv(String CAvv) {
        this.CAvv = CAvv;
    }

    public String getEciFlag() {
        return EciFlag;
    }

    public void setEciFlag(String EciFlag) {
        this.EciFlag = EciFlag;
    }

    public String getMasterCardAssignedID() {
        return MasterCardAssignedID;
    }

    public void setMasterCardAssignedID(String MasterCardAssignedID) {
        this.MasterCardAssignedID = MasterCardAssignedID;
    }

    public String getPaResStatus() {
        return PaResStatus;
    }

    public void setPaResStatus(String PaResStatus) {
        this.PaResStatus = PaResStatus;
    }

    public String getSCEnrollmentStatus() {
        return SCEnrollmentStatus;
    }

    public void setSCEnrollmentStatus(String SCEnrollmentStatus) {
        this.SCEnrollmentStatus = SCEnrollmentStatus;
    }

    public String getSignatureVerification() {
        return SignatureVerification;
    }

    public void setSignatureVerification(String SignatureVerification) {
        this.SignatureVerification = SignatureVerification;
    }

    public String getXid() {
        return Xid;
    }

    public void setXid(String Xid) {
        this.Xid = Xid;
    }

    @Override
    public String toString() {
        return "AuthenticationOptions{" +
                "AuthenticateMethod='" + AuthenticateMethod + '\'' +
                ", CardEnrollmentMethod='" + CardEnrollmentMethod + '\'' +
                ", CAvv='" + CAvv + '\'' +
                ", EciFlag='" + EciFlag + '\'' +
                ", MasterCardAssignedID='" + MasterCardAssignedID + '\'' +
                ", PaResStatus='" + PaResStatus + '\'' +
                ", SCEnrollmentStatus='" + SCEnrollmentStatus + '\'' +
                ", SignatureVerification='" + SignatureVerification + '\'' +
                ", Xid='" + Xid + '\'' +
                '}';
    }
}
