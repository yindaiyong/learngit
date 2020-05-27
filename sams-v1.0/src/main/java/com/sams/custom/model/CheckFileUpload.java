package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CheckFileUpload implements Serializable {
    private BigDecimal cfuId;

    private String cfuChannelCode;

    private String cfuFileName;

    private String cfuLineNumber;

    private String cfuTransDate;

    private String cfuUploadFlag;

    private String cfuMsg;

    private String cfuCuser;

    private Date cfuCtime;
    
    private String cfuSuccessTime;

    private static final long serialVersionUID = 1L;

    public BigDecimal getCfuId() {
        return cfuId;
    }

    public void setCfuId(BigDecimal cfuId) {
        this.cfuId = cfuId;
    }

    public String getCfuChannelCode() {
        return cfuChannelCode;
    }

    public void setCfuChannelCode(String cfuChannelCode) {
        this.cfuChannelCode = cfuChannelCode == null ? null : cfuChannelCode.trim();
    }

    public String getCfuFileName() {
        return cfuFileName;
    }

    public void setCfuFileName(String cfuFileName) {
        this.cfuFileName = cfuFileName == null ? null : cfuFileName.trim();
    }

    public String getCfuLineNumber() {
        return cfuLineNumber;
    }

    public void setCfuLineNumber(String cfuLineNumber) {
        this.cfuLineNumber = cfuLineNumber == null ? null : cfuLineNumber.trim();
    }

    public String getCfuTransDate() {
        return cfuTransDate;
    }

    public void setCfuTransDate(String cfuTransDate) {
        this.cfuTransDate = cfuTransDate == null ? null : cfuTransDate.trim();
    }

    public String getCfuUploadFlag() {
        return cfuUploadFlag;
    }

    public void setCfuUploadFlag(String cfuUploadFlag) {
        this.cfuUploadFlag = cfuUploadFlag == null ? null : cfuUploadFlag.trim();
    }

    public String getCfuMsg() {
        return cfuMsg;
    }

    public void setCfuMsg(String cfuMsg) {
        this.cfuMsg = cfuMsg == null ? null : cfuMsg.trim();
    }

    public String getCfuCuser() {
        return cfuCuser;
    }

    public void setCfuCuser(String cfuCuser) {
        this.cfuCuser = cfuCuser == null ? null : cfuCuser.trim();
    }

    public Date getCfuCtime() {
        return cfuCtime;
    }

    public void setCfuCtime(Date cfuCtime) {
        this.cfuCtime = cfuCtime;
    }

    public String getCfuSuccessTime() {
		return cfuSuccessTime;
	}

	public void setCfuSuccessTime(String cfuSuccessTime) {
		this.cfuSuccessTime = cfuSuccessTime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cfuId=").append(cfuId);
        sb.append(", cfuChannelCode=").append(cfuChannelCode);
        sb.append(", cfuFileName=").append(cfuFileName);
        sb.append(", cfuLineNumber=").append(cfuLineNumber);
        sb.append(", cfuTransDate=").append(cfuTransDate);
        sb.append(", cfuUploadFlag=").append(cfuUploadFlag);
        sb.append(", cfuMsg=").append(cfuMsg);
        sb.append(", cfuCuser=").append(cfuCuser);
        sb.append(", cfuCtime=").append(cfuCtime);
        sb.append(", cfuSuccessTime=").append(cfuSuccessTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}