package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class AccountReqCfm implements Serializable {
    private Long arcId;

    private String arcChannelCode;

    private String arcAppSheetSerialNo;

    private String arcTransactionCfmDate;

    private String arcReturnCode;

    private String arcTaAccountId;

    private String arcMultiAcctFlag;

    private String arcTaSerialNo;

    private String arcFromTaFlag;

    private String arcAccountAbbr;

    private String arcAccountCardId;

    private String arcCustomerNo;

    private String arcErrorDetail;

    private String arcTaRetSerialNo;

    private String arcTaErrorDetail;

    private Date arcCtime;

    private Date arcUtime;

    private String arcCuser;

    private String arcUuser;

    private String arcSendStatus;

    private String arcGenerateStatus;

    private Date arcSendFileTime;

    private Date arcGenerateFileTime;

    private String arcTransactionAccountId;

    private String arcDistributorCode;

    private String arcBusinessCode;

    private String arcTransactionTime;

    private String arcTransactionDate;

    private String arcBranchCode;

    private String arcCertificateType;

    private String arcCertificateNo;

    private String arcInvestorName;

    private String arcIndividualOrInstitution;

    private String arcRegionCode;

    private String arcTargetTransactAcctCode;

    private String arcNetNo;

    private String arcSpecification;

    private String arcFrozenCause;

    private String arcFreezingDeadline;

    private static final long serialVersionUID = 1L;

    public Long getArcId() {
        return arcId;
    }

    public void setArcId(Long arcId) {
        this.arcId = arcId;
    }

    public String getArcChannelCode() {
        return arcChannelCode;
    }

    public void setArcChannelCode(String arcChannelCode) {
        this.arcChannelCode = arcChannelCode == null ? null : arcChannelCode.trim();
    }

    public String getArcAppSheetSerialNo() {
        return arcAppSheetSerialNo;
    }

    public void setArcAppSheetSerialNo(String arcAppSheetSerialNo) {
        this.arcAppSheetSerialNo = arcAppSheetSerialNo == null ? null : arcAppSheetSerialNo.trim();
    }

    public String getArcTransactionCfmDate() {
        return arcTransactionCfmDate;
    }

    public void setArcTransactionCfmDate(String arcTransactionCfmDate) {
        this.arcTransactionCfmDate = arcTransactionCfmDate == null ? null : arcTransactionCfmDate.trim();
    }

    public String getArcReturnCode() {
        return arcReturnCode;
    }

    public void setArcReturnCode(String arcReturnCode) {
        this.arcReturnCode = arcReturnCode == null ? null : arcReturnCode.trim();
    }

    public String getArcTaAccountId() {
        return arcTaAccountId;
    }

    public void setArcTaAccountId(String arcTaAccountId) {
        this.arcTaAccountId = arcTaAccountId == null ? null : arcTaAccountId.trim();
    }

    public String getArcMultiAcctFlag() {
        return arcMultiAcctFlag;
    }

    public void setArcMultiAcctFlag(String arcMultiAcctFlag) {
        this.arcMultiAcctFlag = arcMultiAcctFlag == null ? null : arcMultiAcctFlag.trim();
    }

    public String getArcTaSerialNo() {
        return arcTaSerialNo;
    }

    public void setArcTaSerialNo(String arcTaSerialNo) {
        this.arcTaSerialNo = arcTaSerialNo == null ? null : arcTaSerialNo.trim();
    }

    public String getArcFromTaFlag() {
        return arcFromTaFlag;
    }

    public void setArcFromTaFlag(String arcFromTaFlag) {
        this.arcFromTaFlag = arcFromTaFlag == null ? null : arcFromTaFlag.trim();
    }

    public String getArcAccountAbbr() {
        return arcAccountAbbr;
    }

    public void setArcAccountAbbr(String arcAccountAbbr) {
        this.arcAccountAbbr = arcAccountAbbr == null ? null : arcAccountAbbr.trim();
    }

    public String getArcAccountCardId() {
        return arcAccountCardId;
    }

    public void setArcAccountCardId(String arcAccountCardId) {
        this.arcAccountCardId = arcAccountCardId == null ? null : arcAccountCardId.trim();
    }

    public String getArcCustomerNo() {
        return arcCustomerNo;
    }

    public void setArcCustomerNo(String arcCustomerNo) {
        this.arcCustomerNo = arcCustomerNo == null ? null : arcCustomerNo.trim();
    }

    public String getArcErrorDetail() {
        return arcErrorDetail;
    }

    public void setArcErrorDetail(String arcErrorDetail) {
        this.arcErrorDetail = arcErrorDetail == null ? null : arcErrorDetail.trim();
    }

    public String getArcTaRetSerialNo() {
        return arcTaRetSerialNo;
    }

    public void setArcTaRetSerialNo(String arcTaRetSerialNo) {
        this.arcTaRetSerialNo = arcTaRetSerialNo == null ? null : arcTaRetSerialNo.trim();
    }

    public String getArcTaErrorDetail() {
        return arcTaErrorDetail;
    }

    public void setArcTaErrorDetail(String arcTaErrorDetail) {
        this.arcTaErrorDetail = arcTaErrorDetail == null ? null : arcTaErrorDetail.trim();
    }

    public Date getArcCtime() {
        return arcCtime;
    }

    public void setArcCtime(Date arcCtime) {
        this.arcCtime = arcCtime;
    }

    public Date getArcUtime() {
        return arcUtime;
    }

    public void setArcUtime(Date arcUtime) {
        this.arcUtime = arcUtime;
    }

    public String getArcCuser() {
        return arcCuser;
    }

    public void setArcCuser(String arcCuser) {
        this.arcCuser = arcCuser == null ? null : arcCuser.trim();
    }

    public String getArcUuser() {
        return arcUuser;
    }

    public void setArcUuser(String arcUuser) {
        this.arcUuser = arcUuser == null ? null : arcUuser.trim();
    }

    public String getArcSendStatus() {
        return arcSendStatus;
    }

    public void setArcSendStatus(String arcSendStatus) {
        this.arcSendStatus = arcSendStatus == null ? null : arcSendStatus.trim();
    }

    public String getArcGenerateStatus() {
        return arcGenerateStatus;
    }

    public void setArcGenerateStatus(String arcGenerateStatus) {
        this.arcGenerateStatus = arcGenerateStatus == null ? null : arcGenerateStatus.trim();
    }

    public Date getArcSendFileTime() {
        return arcSendFileTime;
    }

    public void setArcSendFileTime(Date arcSendFileTime) {
        this.arcSendFileTime = arcSendFileTime;
    }

    public Date getArcGenerateFileTime() {
        return arcGenerateFileTime;
    }

    public void setArcGenerateFileTime(Date arcGenerateFileTime) {
        this.arcGenerateFileTime = arcGenerateFileTime;
    }

    public String getArcTransactionAccountId() {
        return arcTransactionAccountId;
    }

    public void setArcTransactionAccountId(String arcTransactionAccountId) {
        this.arcTransactionAccountId = arcTransactionAccountId == null ? null : arcTransactionAccountId.trim();
    }

    public String getArcDistributorCode() {
        return arcDistributorCode;
    }

    public void setArcDistributorCode(String arcDistributorCode) {
        this.arcDistributorCode = arcDistributorCode == null ? null : arcDistributorCode.trim();
    }

    public String getArcBusinessCode() {
        return arcBusinessCode;
    }

    public void setArcBusinessCode(String arcBusinessCode) {
        this.arcBusinessCode = arcBusinessCode == null ? null : arcBusinessCode.trim();
    }

    public String getArcTransactionTime() {
        return arcTransactionTime;
    }

    public void setArcTransactionTime(String arcTransactionTime) {
        this.arcTransactionTime = arcTransactionTime == null ? null : arcTransactionTime.trim();
    }

    public String getArcTransactionDate() {
        return arcTransactionDate;
    }

    public void setArcTransactionDate(String arcTransactionDate) {
        this.arcTransactionDate = arcTransactionDate == null ? null : arcTransactionDate.trim();
    }

    public String getArcBranchCode() {
        return arcBranchCode;
    }

    public void setArcBranchCode(String arcBranchCode) {
        this.arcBranchCode = arcBranchCode == null ? null : arcBranchCode.trim();
    }

    public String getArcCertificateType() {
        return arcCertificateType;
    }

    public void setArcCertificateType(String arcCertificateType) {
        this.arcCertificateType = arcCertificateType == null ? null : arcCertificateType.trim();
    }

    public String getArcCertificateNo() {
        return arcCertificateNo;
    }

    public void setArcCertificateNo(String arcCertificateNo) {
        this.arcCertificateNo = arcCertificateNo == null ? null : arcCertificateNo.trim();
    }

    public String getArcInvestorName() {
        return arcInvestorName;
    }

    public void setArcInvestorName(String arcInvestorName) {
        this.arcInvestorName = arcInvestorName == null ? null : arcInvestorName.trim();
    }

    public String getArcIndividualOrInstitution() {
        return arcIndividualOrInstitution;
    }

    public void setArcIndividualOrInstitution(String arcIndividualOrInstitution) {
        this.arcIndividualOrInstitution = arcIndividualOrInstitution == null ? null : arcIndividualOrInstitution.trim();
    }

    public String getArcRegionCode() {
        return arcRegionCode;
    }

    public void setArcRegionCode(String arcRegionCode) {
        this.arcRegionCode = arcRegionCode == null ? null : arcRegionCode.trim();
    }

    public String getArcTargetTransactAcctCode() {
        return arcTargetTransactAcctCode;
    }

    public void setArcTargetTransactAcctCode(String arcTargetTransactAcctCode) {
        this.arcTargetTransactAcctCode = arcTargetTransactAcctCode == null ? null : arcTargetTransactAcctCode.trim();
    }

    public String getArcNetNo() {
        return arcNetNo;
    }

    public void setArcNetNo(String arcNetNo) {
        this.arcNetNo = arcNetNo == null ? null : arcNetNo.trim();
    }

    public String getArcSpecification() {
        return arcSpecification;
    }

    public void setArcSpecification(String arcSpecification) {
        this.arcSpecification = arcSpecification == null ? null : arcSpecification.trim();
    }

    public String getArcFrozenCause() {
        return arcFrozenCause;
    }

    public void setArcFrozenCause(String arcFrozenCause) {
        this.arcFrozenCause = arcFrozenCause == null ? null : arcFrozenCause.trim();
    }

    public String getArcFreezingDeadline() {
        return arcFreezingDeadline;
    }

    public void setArcFreezingDeadline(String arcFreezingDeadline) {
        this.arcFreezingDeadline = arcFreezingDeadline == null ? null : arcFreezingDeadline.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", arcId=").append(arcId);
        sb.append(", arcChannelCode=").append(arcChannelCode);
        sb.append(", arcAppSheetSerialNo=").append(arcAppSheetSerialNo);
        sb.append(", arcTransactionCfmDate=").append(arcTransactionCfmDate);
        sb.append(", arcReturnCode=").append(arcReturnCode);
        sb.append(", arcTaAccountId=").append(arcTaAccountId);
        sb.append(", arcMultiAcctFlag=").append(arcMultiAcctFlag);
        sb.append(", arcTaSerialNo=").append(arcTaSerialNo);
        sb.append(", arcFromTaFlag=").append(arcFromTaFlag);
        sb.append(", arcAccountAbbr=").append(arcAccountAbbr);
        sb.append(", arcAccountCardId=").append(arcAccountCardId);
        sb.append(", arcCustomerNo=").append(arcCustomerNo);
        sb.append(", arcErrorDetail=").append(arcErrorDetail);
        sb.append(", arcTaRetSerialNo=").append(arcTaRetSerialNo);
        sb.append(", arcTaErrorDetail=").append(arcTaErrorDetail);
        sb.append(", arcCtime=").append(arcCtime);
        sb.append(", arcUtime=").append(arcUtime);
        sb.append(", arcCuser=").append(arcCuser);
        sb.append(", arcUuser=").append(arcUuser);
        sb.append(", arcSendStatus=").append(arcSendStatus);
        sb.append(", arcGenerateStatus=").append(arcGenerateStatus);
        sb.append(", arcSendFileTime=").append(arcSendFileTime);
        sb.append(", arcGenerateFileTime=").append(arcGenerateFileTime);
        sb.append(", arcTransactionAccountId=").append(arcTransactionAccountId);
        sb.append(", arcDistributorCode=").append(arcDistributorCode);
        sb.append(", arcBusinessCode=").append(arcBusinessCode);
        sb.append(", arcTransactionTime=").append(arcTransactionTime);
        sb.append(", arcTransactionDate=").append(arcTransactionDate);
        sb.append(", arcBranchCode=").append(arcBranchCode);
        sb.append(", arcCertificateType=").append(arcCertificateType);
        sb.append(", arcCertificateNo=").append(arcCertificateNo);
        sb.append(", arcInvestorName=").append(arcInvestorName);
        sb.append(", arcIndividualOrInstitution=").append(arcIndividualOrInstitution);
        sb.append(", arcRegionCode=").append(arcRegionCode);
        sb.append(", arcTargetTransactAcctCode=").append(arcTargetTransactAcctCode);
        sb.append(", arcNetNo=").append(arcNetNo);
        sb.append(", arcSpecification=").append(arcSpecification);
        sb.append(", arcFrozenCause=").append(arcFrozenCause);
        sb.append(", arcFreezingDeadline=").append(arcFreezingDeadline);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}