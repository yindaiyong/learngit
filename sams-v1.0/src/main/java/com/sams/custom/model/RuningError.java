package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RuningError implements Serializable {
    private BigDecimal reId;

    private String reErrorCode;

    private String reErrorMessage;

    private String reErrorTime;

    private String reBranchCode;

    private String reProcessStep;

    private Date reCtime;

    private Date reUtime;

    private String reCuser;

    private String reUuser;

    private static final long serialVersionUID = 1L;

    public BigDecimal getReId() {
        return reId;
    }

    public void setReId(BigDecimal reId) {
        this.reId = reId;
    }

    public String getReErrorCode() {
        return reErrorCode;
    }

    public void setReErrorCode(String reErrorCode) {
        this.reErrorCode = reErrorCode == null ? null : reErrorCode.trim();
    }

    public String getReErrorMessage() {
        return reErrorMessage;
    }

    public void setReErrorMessage(String reErrorMessage) {
        this.reErrorMessage = reErrorMessage == null ? null : reErrorMessage.trim();
    }

    public String getReErrorTime() {
        return reErrorTime;
    }

    public void setReErrorTime(String reErrorTime) {
        this.reErrorTime = reErrorTime == null ? null : reErrorTime.trim();
    }

    public String getReBranchCode() {
        return reBranchCode;
    }

    public void setReBranchCode(String reBranchCode) {
        this.reBranchCode = reBranchCode == null ? null : reBranchCode.trim();
    }

    public String getReProcessStep() {
        return reProcessStep;
    }

    public void setReProcessStep(String reProcessStep) {
        this.reProcessStep = reProcessStep == null ? null : reProcessStep.trim();
    }

    public Date getReCtime() {
        return reCtime;
    }

    public void setReCtime(Date reCtime) {
        this.reCtime = reCtime;
    }

    public Date getReUtime() {
        return reUtime;
    }

    public void setReUtime(Date reUtime) {
        this.reUtime = reUtime;
    }

    public String getReCuser() {
        return reCuser;
    }

    public void setReCuser(String reCuser) {
        this.reCuser = reCuser == null ? null : reCuser.trim();
    }

    public String getReUuser() {
        return reUuser;
    }

    public void setReUuser(String reUuser) {
        this.reUuser = reUuser == null ? null : reUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reId=").append(reId);
        sb.append(", reErrorCode=").append(reErrorCode);
        sb.append(", reErrorMessage=").append(reErrorMessage);
        sb.append(", reErrorTime=").append(reErrorTime);
        sb.append(", reBranchCode=").append(reBranchCode);
        sb.append(", reProcessStep=").append(reProcessStep);
        sb.append(", reCtime=").append(reCtime);
        sb.append(", reUtime=").append(reUtime);
        sb.append(", reCuser=").append(reCuser);
        sb.append(", reUuser=").append(reUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}