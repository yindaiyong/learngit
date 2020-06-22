package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundVolDetailCfm implements Serializable {
    private Long fvdId;

    private String fvdChannelCode;

    private String fvdTransDate;

    private BigDecimal fvdAvailableVol;

    private BigDecimal fvdTotalVolOfDistributor;

    private String fvdTransactionCfmDate;

    private String fvdFundCode;

    private String fvdTransactionAccountId;

    private String fvdDistributorCode;

    private String fvdTAAccountId;

    private BigDecimal fvdTotalFrozenVol;

    private String fvdBranchCode;

    private String fvdTaSerialNo;

    private BigDecimal fvdTotalBackendLoad;

    private String fvdShareClass;

    private String fvdDetailFlag;

    private String fvdAccountStatus;

    private String fvdShareRegisterDate;

    private BigDecimal fvdUndistributeMonetary;

    private String fvdUndistributeMonetaryFlag;

    private BigDecimal fvdGuaranteedAmount;

    private String fvdSourceType;

    private String fvdDefDividendMethod;

    private String fvdAllowRedemptDate;

    private Date fvdCtime;

    private Date fvdUtime;

    private String fvdCuser;

    private String fvdUuser;

    private String fvdSendStatus;
    private String fvdGenerateStatus;
    private Date fvdSendFileTime;
    private Date fvdGenerateFileTime;
    
    private String fvdRelAllowRedemptDate;

    private static final long serialVersionUID = 1L;

    public Long getFvdId() {
        return fvdId;
    }

    public void setFvdId(Long fvdId) {
        this.fvdId = fvdId;
    }

    public String getFvdChannelCode() {
        return fvdChannelCode;
    }

    public void setFvdChannelCode(String fvdChannelCode) {
        this.fvdChannelCode = fvdChannelCode == null ? null : fvdChannelCode.trim();
    }

    public String getFvdTransDate() {
        return fvdTransDate;
    }

    public void setFvdTransDate(String fvdTransDate) {
        this.fvdTransDate = fvdTransDate == null ? null : fvdTransDate.trim();
    }

    public BigDecimal getFvdAvailableVol() {
        return fvdAvailableVol;
    }

    public void setFvdAvailableVol(BigDecimal fvdAvailableVol) {
        this.fvdAvailableVol = fvdAvailableVol;
    }

    public BigDecimal getFvdTotalVolOfDistributor() {
        return fvdTotalVolOfDistributor;
    }

    public void setFvdTotalVolOfDistributor(BigDecimal fvdTotalVolOfDistributor) {
        this.fvdTotalVolOfDistributor = fvdTotalVolOfDistributor;
    }

    public String getFvdTransactionCfmDate() {
        return fvdTransactionCfmDate;
    }

    public void setFvdTransactionCfmDate(String fvdTransactionCfmDate) {
        this.fvdTransactionCfmDate = fvdTransactionCfmDate == null ? null : fvdTransactionCfmDate.trim();
    }

    public String getFvdFundCode() {
        return fvdFundCode;
    }

    public void setFvdFundCode(String fvdFundCode) {
        this.fvdFundCode = fvdFundCode == null ? null : fvdFundCode.trim();
    }

    public String getFvdTransactionAccountId() {
        return fvdTransactionAccountId;
    }

    public void setFvdTransactionAccountId(String fvdTransactionAccountId) {
        this.fvdTransactionAccountId = fvdTransactionAccountId == null ? null : fvdTransactionAccountId.trim();
    }

    public String getFvdDistributorCode() {
        return fvdDistributorCode;
    }

    public void setFvdDistributorCode(String fvdDistributorCode) {
        this.fvdDistributorCode = fvdDistributorCode == null ? null : fvdDistributorCode.trim();
    }

    public String getFvdTAAccountId() {
        return fvdTAAccountId;
    }

    public void setFvdTAAccountId(String fvdTAAccountId) {
        this.fvdTAAccountId = fvdTAAccountId == null ? null : fvdTAAccountId.trim();
    }

    public BigDecimal getFvdTotalFrozenVol() {
        return fvdTotalFrozenVol;
    }

    public void setFvdTotalFrozenVol(BigDecimal fvdTotalFrozenVol) {
        this.fvdTotalFrozenVol = fvdTotalFrozenVol;
    }

    public String getFvdBranchCode() {
        return fvdBranchCode;
    }

    public void setFvdBranchCode(String fvdBranchCode) {
        this.fvdBranchCode = fvdBranchCode == null ? null : fvdBranchCode.trim();
    }

    public String getFvdTaSerialNo() {
        return fvdTaSerialNo;
    }

    public void setFvdTaSerialNo(String fvdTaSerialNo) {
        this.fvdTaSerialNo = fvdTaSerialNo == null ? null : fvdTaSerialNo.trim();
    }

    public BigDecimal getFvdTotalBackendLoad() {
        return fvdTotalBackendLoad;
    }

    public void setFvdTotalBackendLoad(BigDecimal fvdTotalBackendLoad) {
        this.fvdTotalBackendLoad = fvdTotalBackendLoad;
    }

    public String getFvdShareClass() {
        return fvdShareClass;
    }

    public void setFvdShareClass(String fvdShareClass) {
        this.fvdShareClass = fvdShareClass == null ? null : fvdShareClass.trim();
    }

    public String getFvdDetailFlag() {
        return fvdDetailFlag;
    }

    public void setFvdDetailFlag(String fvdDetailFlag) {
        this.fvdDetailFlag = fvdDetailFlag == null ? null : fvdDetailFlag.trim();
    }

    public String getFvdAccountStatus() {
        return fvdAccountStatus;
    }

    public void setFvdAccountStatus(String fvdAccountStatus) {
        this.fvdAccountStatus = fvdAccountStatus == null ? null : fvdAccountStatus.trim();
    }

    public String getFvdShareRegisterDate() {
        return fvdShareRegisterDate;
    }

    public void setFvdShareRegisterDate(String fvdShareRegisterDate) {
        this.fvdShareRegisterDate = fvdShareRegisterDate == null ? null : fvdShareRegisterDate.trim();
    }

    public BigDecimal getFvdUndistributeMonetary() {
        return fvdUndistributeMonetary;
    }

    public void setFvdUndistributeMonetary(BigDecimal fvdUndistributeMonetary) {
        this.fvdUndistributeMonetary = fvdUndistributeMonetary;
    }

    public String getFvdUndistributeMonetaryFlag() {
        return fvdUndistributeMonetaryFlag;
    }

    public void setFvdUndistributeMonetaryFlag(String fvdUndistributeMonetaryFlag) {
        this.fvdUndistributeMonetaryFlag = fvdUndistributeMonetaryFlag == null ? null : fvdUndistributeMonetaryFlag.trim();
    }

    public BigDecimal getFvdGuaranteedAmount() {
        return fvdGuaranteedAmount;
    }

    public void setFvdGuaranteedAmount(BigDecimal fvdGuaranteedAmount) {
        this.fvdGuaranteedAmount = fvdGuaranteedAmount;
    }

    public String getFvdSourceType() {
        return fvdSourceType;
    }

    public void setFvdSourceType(String fvdSourceType) {
        this.fvdSourceType = fvdSourceType == null ? null : fvdSourceType.trim();
    }

    public String getFvdDefDividendMethod() {
        return fvdDefDividendMethod;
    }

    public void setFvdDefDividendMethod(String fvdDefDividendMethod) {
        this.fvdDefDividendMethod = fvdDefDividendMethod == null ? null : fvdDefDividendMethod.trim();
    }

    public String getFvdAllowRedemptDate() {
        return fvdAllowRedemptDate;
    }

    public void setFvdAllowRedemptDate(String fvdAllowRedemptDate) {
        this.fvdAllowRedemptDate = fvdAllowRedemptDate == null ? null : fvdAllowRedemptDate.trim();
    }

    public Date getFvdCtime() {
        return fvdCtime;
    }

    public void setFvdCtime(Date fvdCtime) {
        this.fvdCtime = fvdCtime;
    }

    public Date getFvdUtime() {
        return fvdUtime;
    }

    public void setFvdUtime(Date fvdUtime) {
        this.fvdUtime = fvdUtime;
    }

    public String getFvdCuser() {
        return fvdCuser;
    }

    public void setFvdCuser(String fvdCuser) {
        this.fvdCuser = fvdCuser == null ? null : fvdCuser.trim();
    }

    public String getFvdUuser() {
        return fvdUuser;
    }

    public void setFvdUuser(String fvdUuser) {
        this.fvdUuser = fvdUuser == null ? null : fvdUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fvdId=").append(fvdId);
        sb.append(", fvdChannelCode=").append(fvdChannelCode);
        sb.append(", fvdTransDate=").append(fvdTransDate);
        sb.append(", fvdAvailableVol=").append(fvdAvailableVol);
        sb.append(", fvdTotalVolOfDistributor=").append(fvdTotalVolOfDistributor);
        sb.append(", fvdTransactionCfmDate=").append(fvdTransactionCfmDate);
        sb.append(", fvdFundCode=").append(fvdFundCode);
        sb.append(", fvdTransactionAccountId=").append(fvdTransactionAccountId);
        sb.append(", fvdDistributorCode=").append(fvdDistributorCode);
        sb.append(", fvdTAAccountId=").append(fvdTAAccountId);
        sb.append(", fvdTotalFrozenVol=").append(fvdTotalFrozenVol);
        sb.append(", fvdBranchCode=").append(fvdBranchCode);
        sb.append(", fvdTaSerialNo=").append(fvdTaSerialNo);
        sb.append(", fvdTotalBackendLoad=").append(fvdTotalBackendLoad);
        sb.append(", fvdShareClass=").append(fvdShareClass);
        sb.append(", fvdDetailFlag=").append(fvdDetailFlag);
        sb.append(", fvdAccountStatus=").append(fvdAccountStatus);
        sb.append(", fvdShareRegisterDate=").append(fvdShareRegisterDate);
        sb.append(", fvdUndistributeMonetary=").append(fvdUndistributeMonetary);
        sb.append(", fvdUndistributeMonetaryFlag=").append(fvdUndistributeMonetaryFlag);
        sb.append(", fvdGuaranteedAmount=").append(fvdGuaranteedAmount);
        sb.append(", fvdSourceType=").append(fvdSourceType);
        sb.append(", fvdDefDividendMethod=").append(fvdDefDividendMethod);
        sb.append(", fvdAllowRedemptDate=").append(fvdAllowRedemptDate);
        sb.append(", fvdCtime=").append(fvdCtime);
        sb.append(", fvdUtime=").append(fvdUtime);
        sb.append(", fvdCuser=").append(fvdCuser);
        sb.append(", fvdUuser=").append(fvdUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getFvdSendStatus() {
        return fvdSendStatus;
    }

    public void setFvdSendStatus(String fvdSendStatus) {
        this.fvdSendStatus = fvdSendStatus;
    }

    public String getFvdGenerateStatus() {
        return fvdGenerateStatus;
    }

    public void setFvdGenerateStatus(String fvdGenerateStatus) {
        this.fvdGenerateStatus = fvdGenerateStatus;
    }

    public Date getFvdSendFileTime() {
        return fvdSendFileTime;
    }

    public void setFvdSendFileTime(Date fvdSendFileTime) {
        this.fvdSendFileTime = fvdSendFileTime;
    }

    public Date getFvdGenerateFileTime() {
        return fvdGenerateFileTime;
    }

    public void setFvdGenerateFileTime(Date fvdGenerateFileTime) {
        this.fvdGenerateFileTime = fvdGenerateFileTime;
    }

	public String getFvdRelAllowRedemptDate() {
		return fvdRelAllowRedemptDate;
	}

	public void setFvdRelAllowRedemptDate(String fvdRelAllowRedemptDate) {
		this.fvdRelAllowRedemptDate = fvdRelAllowRedemptDate;
	}
    
    
}