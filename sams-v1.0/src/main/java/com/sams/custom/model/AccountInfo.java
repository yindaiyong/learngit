package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountInfo implements Serializable {
    private Long aiId;

    private String aiChannelCode;

    private String aiAddress;

    private String aiInstReprIdCode;

    private String aiInstReprIdType;

    private String aiInstReprName;

    private String aiAppSheetSerialNo;

    private String aiCertificateType;

    private String aiCertificateNo;

    private Object aiInvestorName;

    private String aiTransactionDate;

    private String aiTransactionTime;

    private String aiIndividualOrInstitution;

    private String aiPostCode;

    private String aiTransactorCertNo;

    private String aiTransactorCertType;

    private String aiTransactorName;

    private String aiTransactionAccountId;

    private String aiDistributorCode;

    private String aiBusinessCode;

    private String aiAcctNoFmInCleaiAgency;

    private String aiAcctNameFICleaiAgency;

    private String aiClearingAgencyCode;

    private String aiInvestorsBirthday;

    private String aiDepositAcct;

    private String aiRegionCode;

    private String aiEducationLevel;

    private String aiEmailAddress;

    private String aiFaxNo;

    private String aiVocationCode;

    private String aiHomeTelNo;

    private BigDecimal aiAnnualIncome;

    private String aiMobileTelNo;

    private String aiBranchCode;

    private String aiOfficeTelNo;

    private String aiAccountAbbr;

    private String aiConfidentialDocumentCode;

    private String aiSex;

    private String aiShSecuritiesAccountid;

    private String aiSzSecuritiesAccountid;

    private String aiTaAccountId;

    private String aiTelNo;

    private String aiTradingMethod;

    private String aiMinorFlag;

    private String aiDeliverType;

    private String aiTransactorIdType;

    private String aiAccountCardId;

    private String aiMultiacctFlag;

    private String aiTargetTransactAcctCode;

    private String aiAcctNameInveCleaiAgen;

    private String aiAcctNoInveCleaiAgen;

    private String aiClearingAgency;

    private String aiDeliverWay;

    private String aiNationality;

    private String aiNetNo;

    private String aiBroker;

    private String aiCorpName;

    private String aiCertValidDate;

    private String aiInstTranCertValidDate;

    private String aiInstReprCertValidDate;

    private String aiClientRiskRate;

    private String aiInstReprManageRange;

    private String aiControlHolder;

    private String aiActualController;

    private String aiMarriageStatus;

    private BigDecimal aiFamilyNum;

    private BigDecimal aiPenates;

    private String aiMediaHobby;

    private String aiInstitutionType;

    private String aiEnglishFirstName;

    private String aiEnglishFamliyName;

    private String aiVocation;

    private String aiCorpoProperty;

    private BigDecimal aiStaffNum;

    private String aiHobbytype;

    private String aiProvince;

    private String aiCity;

    private String aiCounty;

    private String aiCommendPerson;

    private String aiCommendPersonType;

    private String aiAcceptMethod;

    private String aiFrozenCause;

    private String aiFreezingDeadline;

    private String aiOriginalSerialNo;

    private String aiOriginalAppSheetNo;

    private String aiSpecification;

    private Date aiCtime;

    private Date aiUtime;

    private String aiCuser;

    private String aiUuser;

    private static final long serialVersionUID = 1L;

    public Long getAiId() {
        return aiId;
    }

    public void setAiId(Long aiId) {
        this.aiId = aiId;
    }

    public String getAiChannelCode() {
        return aiChannelCode;
    }

    public void setAiChannelCode(String aiChannelCode) {
        this.aiChannelCode = aiChannelCode == null ? null : aiChannelCode.trim();
    }

    public String getAiAddress() {
        return aiAddress;
    }

    public void setAiAddress(String aiAddress) {
        this.aiAddress = aiAddress == null ? null : aiAddress.trim();
    }

    public String getAiInstReprIdCode() {
        return aiInstReprIdCode;
    }

    public void setAiInstReprIdCode(String aiInstReprIdCode) {
        this.aiInstReprIdCode = aiInstReprIdCode == null ? null : aiInstReprIdCode.trim();
    }

    public String getAiInstReprIdType() {
        return aiInstReprIdType;
    }

    public void setAiInstReprIdType(String aiInstReprIdType) {
        this.aiInstReprIdType = aiInstReprIdType == null ? null : aiInstReprIdType.trim();
    }

    public String getAiInstReprName() {
        return aiInstReprName;
    }

    public void setAiInstReprName(String aiInstReprName) {
        this.aiInstReprName = aiInstReprName == null ? null : aiInstReprName.trim();
    }

    public String getAiAppSheetSerialNo() {
        return aiAppSheetSerialNo;
    }

    public void setAiAppSheetSerialNo(String aiAppSheetSerialNo) {
        this.aiAppSheetSerialNo = aiAppSheetSerialNo == null ? null : aiAppSheetSerialNo.trim();
    }

    public String getAiCertificateType() {
        return aiCertificateType;
    }

    public void setAiCertificateType(String aiCertificateType) {
        this.aiCertificateType = aiCertificateType == null ? null : aiCertificateType.trim();
    }

    public String getAiCertificateNo() {
        return aiCertificateNo;
    }

    public void setAiCertificateNo(String aiCertificateNo) {
        this.aiCertificateNo = aiCertificateNo == null ? null : aiCertificateNo.trim();
    }

    public Object getAiInvestorName() {
        return aiInvestorName;
    }

    public void setAiInvestorName(Object aiInvestorName) {
        this.aiInvestorName = aiInvestorName;
    }

    public String getAiTransactionDate() {
        return aiTransactionDate;
    }

    public void setAiTransactionDate(String aiTransactionDate) {
        this.aiTransactionDate = aiTransactionDate == null ? null : aiTransactionDate.trim();
    }

    public String getAiTransactionTime() {
        return aiTransactionTime;
    }

    public void setAiTransactionTime(String aiTransactionTime) {
        this.aiTransactionTime = aiTransactionTime == null ? null : aiTransactionTime.trim();
    }

    public String getAiIndividualOrInstitution() {
        return aiIndividualOrInstitution;
    }

    public void setAiIndividualOrInstitution(String aiIndividualOrInstitution) {
        this.aiIndividualOrInstitution = aiIndividualOrInstitution == null ? null : aiIndividualOrInstitution.trim();
    }

    public String getAiPostCode() {
        return aiPostCode;
    }

    public void setAiPostCode(String aiPostCode) {
        this.aiPostCode = aiPostCode == null ? null : aiPostCode.trim();
    }

    public String getAiTransactorCertNo() {
        return aiTransactorCertNo;
    }

    public void setAiTransactorCertNo(String aiTransactorCertNo) {
        this.aiTransactorCertNo = aiTransactorCertNo == null ? null : aiTransactorCertNo.trim();
    }

    public String getAiTransactorCertType() {
        return aiTransactorCertType;
    }

    public void setAiTransactorCertType(String aiTransactorCertType) {
        this.aiTransactorCertType = aiTransactorCertType == null ? null : aiTransactorCertType.trim();
    }

    public String getAiTransactorName() {
        return aiTransactorName;
    }

    public void setAiTransactorName(String aiTransactorName) {
        this.aiTransactorName = aiTransactorName == null ? null : aiTransactorName.trim();
    }

    public String getAiTransactionAccountId() {
        return aiTransactionAccountId;
    }

    public void setAiTransactionAccountId(String aiTransactionAccountId) {
        this.aiTransactionAccountId = aiTransactionAccountId == null ? null : aiTransactionAccountId.trim();
    }

    public String getAiDistributorCode() {
        return aiDistributorCode;
    }

    public void setAiDistributorCode(String aiDistributorCode) {
        this.aiDistributorCode = aiDistributorCode == null ? null : aiDistributorCode.trim();
    }

    public String getAiBusinessCode() {
        return aiBusinessCode;
    }

    public void setAiBusinessCode(String aiBusinessCode) {
        this.aiBusinessCode = aiBusinessCode == null ? null : aiBusinessCode.trim();
    }

    public String getAiAcctNoFmInCleaiAgency() {
        return aiAcctNoFmInCleaiAgency;
    }

    public void setAiAcctNoFmInCleaiAgency(String aiAcctNoFmInCleaiAgency) {
        this.aiAcctNoFmInCleaiAgency = aiAcctNoFmInCleaiAgency == null ? null : aiAcctNoFmInCleaiAgency.trim();
    }

    public String getAiAcctNameFICleaiAgency() {
        return aiAcctNameFICleaiAgency;
    }

    public void setAiAcctNameFICleaiAgency(String aiAcctNameFICleaiAgency) {
        this.aiAcctNameFICleaiAgency = aiAcctNameFICleaiAgency == null ? null : aiAcctNameFICleaiAgency.trim();
    }

    public String getAiClearingAgencyCode() {
        return aiClearingAgencyCode;
    }

    public void setAiClearingAgencyCode(String aiClearingAgencyCode) {
        this.aiClearingAgencyCode = aiClearingAgencyCode == null ? null : aiClearingAgencyCode.trim();
    }

    public String getAiInvestorsBirthday() {
        return aiInvestorsBirthday;
    }

    public void setAiInvestorsBirthday(String aiInvestorsBirthday) {
        this.aiInvestorsBirthday = aiInvestorsBirthday == null ? null : aiInvestorsBirthday.trim();
    }

    public String getAiDepositAcct() {
        return aiDepositAcct;
    }

    public void setAiDepositAcct(String aiDepositAcct) {
        this.aiDepositAcct = aiDepositAcct == null ? null : aiDepositAcct.trim();
    }

    public String getAiRegionCode() {
        return aiRegionCode;
    }

    public void setAiRegionCode(String aiRegionCode) {
        this.aiRegionCode = aiRegionCode == null ? null : aiRegionCode.trim();
    }

    public String getAiEducationLevel() {
        return aiEducationLevel;
    }

    public void setAiEducationLevel(String aiEducationLevel) {
        this.aiEducationLevel = aiEducationLevel == null ? null : aiEducationLevel.trim();
    }

    public String getAiEmailAddress() {
        return aiEmailAddress;
    }

    public void setAiEmailAddress(String aiEmailAddress) {
        this.aiEmailAddress = aiEmailAddress == null ? null : aiEmailAddress.trim();
    }

    public String getAiFaxNo() {
        return aiFaxNo;
    }

    public void setAiFaxNo(String aiFaxNo) {
        this.aiFaxNo = aiFaxNo == null ? null : aiFaxNo.trim();
    }

    public String getAiVocationCode() {
        return aiVocationCode;
    }

    public void setAiVocationCode(String aiVocationCode) {
        this.aiVocationCode = aiVocationCode == null ? null : aiVocationCode.trim();
    }

    public String getAiHomeTelNo() {
        return aiHomeTelNo;
    }

    public void setAiHomeTelNo(String aiHomeTelNo) {
        this.aiHomeTelNo = aiHomeTelNo == null ? null : aiHomeTelNo.trim();
    }

    public BigDecimal getAiAnnualIncome() {
        return aiAnnualIncome;
    }

    public void setAiAnnualIncome(BigDecimal aiAnnualIncome) {
        this.aiAnnualIncome = aiAnnualIncome;
    }

    public String getAiMobileTelNo() {
        return aiMobileTelNo;
    }

    public void setAiMobileTelNo(String aiMobileTelNo) {
        this.aiMobileTelNo = aiMobileTelNo == null ? null : aiMobileTelNo.trim();
    }

    public String getAiBranchCode() {
        return aiBranchCode;
    }

    public void setAiBranchCode(String aiBranchCode) {
        this.aiBranchCode = aiBranchCode == null ? null : aiBranchCode.trim();
    }

    public String getAiOfficeTelNo() {
        return aiOfficeTelNo;
    }

    public void setAiOfficeTelNo(String aiOfficeTelNo) {
        this.aiOfficeTelNo = aiOfficeTelNo == null ? null : aiOfficeTelNo.trim();
    }

    public String getAiAccountAbbr() {
        return aiAccountAbbr;
    }

    public void setAiAccountAbbr(String aiAccountAbbr) {
        this.aiAccountAbbr = aiAccountAbbr == null ? null : aiAccountAbbr.trim();
    }

    public String getAiConfidentialDocumentCode() {
        return aiConfidentialDocumentCode;
    }

    public void setAiConfidentialDocumentCode(String aiConfidentialDocumentCode) {
        this.aiConfidentialDocumentCode = aiConfidentialDocumentCode == null ? null : aiConfidentialDocumentCode.trim();
    }

    public String getAiSex() {
        return aiSex;
    }

    public void setAiSex(String aiSex) {
        this.aiSex = aiSex == null ? null : aiSex.trim();
    }

    public String getAiShSecuritiesAccountid() {
        return aiShSecuritiesAccountid;
    }

    public void setAiShSecuritiesAccountid(String aiShSecuritiesAccountid) {
        this.aiShSecuritiesAccountid = aiShSecuritiesAccountid == null ? null : aiShSecuritiesAccountid.trim();
    }

    public String getAiSzSecuritiesAccountid() {
        return aiSzSecuritiesAccountid;
    }

    public void setAiSzSecuritiesAccountid(String aiSzSecuritiesAccountid) {
        this.aiSzSecuritiesAccountid = aiSzSecuritiesAccountid == null ? null : aiSzSecuritiesAccountid.trim();
    }

    public String getAiTaAccountId() {
        return aiTaAccountId;
    }

    public void setAiTaAccountId(String aiTaAccountId) {
        this.aiTaAccountId = aiTaAccountId == null ? null : aiTaAccountId.trim();
    }

    public String getAiTelNo() {
        return aiTelNo;
    }

    public void setAiTelNo(String aiTelNo) {
        this.aiTelNo = aiTelNo == null ? null : aiTelNo.trim();
    }

    public String getAiTradingMethod() {
        return aiTradingMethod;
    }

    public void setAiTradingMethod(String aiTradingMethod) {
        this.aiTradingMethod = aiTradingMethod == null ? null : aiTradingMethod.trim();
    }

    public String getAiMinorFlag() {
        return aiMinorFlag;
    }

    public void setAiMinorFlag(String aiMinorFlag) {
        this.aiMinorFlag = aiMinorFlag == null ? null : aiMinorFlag.trim();
    }

    public String getAiDeliverType() {
        return aiDeliverType;
    }

    public void setAiDeliverType(String aiDeliverType) {
        this.aiDeliverType = aiDeliverType == null ? null : aiDeliverType.trim();
    }

    public String getAiTransactorIdType() {
        return aiTransactorIdType;
    }

    public void setAiTransactorIdType(String aiTransactorIdType) {
        this.aiTransactorIdType = aiTransactorIdType == null ? null : aiTransactorIdType.trim();
    }

    public String getAiAccountCardId() {
        return aiAccountCardId;
    }

    public void setAiAccountCardId(String aiAccountCardId) {
        this.aiAccountCardId = aiAccountCardId == null ? null : aiAccountCardId.trim();
    }

    public String getAiMultiacctFlag() {
        return aiMultiacctFlag;
    }

    public void setAiMultiacctFlag(String aiMultiacctFlag) {
        this.aiMultiacctFlag = aiMultiacctFlag == null ? null : aiMultiacctFlag.trim();
    }

    public String getAiTargetTransactAcctCode() {
        return aiTargetTransactAcctCode;
    }

    public void setAiTargetTransactAcctCode(String aiTargetTransactAcctCode) {
        this.aiTargetTransactAcctCode = aiTargetTransactAcctCode == null ? null : aiTargetTransactAcctCode.trim();
    }

    public String getAiAcctNameInveCleaiAgen() {
        return aiAcctNameInveCleaiAgen;
    }

    public void setAiAcctNameInveCleaiAgen(String aiAcctNameInveCleaiAgen) {
        this.aiAcctNameInveCleaiAgen = aiAcctNameInveCleaiAgen == null ? null : aiAcctNameInveCleaiAgen.trim();
    }

    public String getAiAcctNoInveCleaiAgen() {
        return aiAcctNoInveCleaiAgen;
    }

    public void setAiAcctNoInveCleaiAgen(String aiAcctNoInveCleaiAgen) {
        this.aiAcctNoInveCleaiAgen = aiAcctNoInveCleaiAgen == null ? null : aiAcctNoInveCleaiAgen.trim();
    }

    public String getAiClearingAgency() {
        return aiClearingAgency;
    }

    public void setAiClearingAgency(String aiClearingAgency) {
        this.aiClearingAgency = aiClearingAgency == null ? null : aiClearingAgency.trim();
    }

    public String getAiDeliverWay() {
        return aiDeliverWay;
    }

    public void setAiDeliverWay(String aiDeliverWay) {
        this.aiDeliverWay = aiDeliverWay == null ? null : aiDeliverWay.trim();
    }

    public String getAiNationality() {
        return aiNationality;
    }

    public void setAiNationality(String aiNationality) {
        this.aiNationality = aiNationality == null ? null : aiNationality.trim();
    }

    public String getAiNetNo() {
        return aiNetNo;
    }

    public void setAiNetNo(String aiNetNo) {
        this.aiNetNo = aiNetNo == null ? null : aiNetNo.trim();
    }

    public String getAiBroker() {
        return aiBroker;
    }

    public void setAiBroker(String aiBroker) {
        this.aiBroker = aiBroker == null ? null : aiBroker.trim();
    }

    public String getAiCorpName() {
        return aiCorpName;
    }

    public void setAiCorpName(String aiCorpName) {
        this.aiCorpName = aiCorpName == null ? null : aiCorpName.trim();
    }

    public String getAiCertValidDate() {
        return aiCertValidDate;
    }

    public void setAiCertValidDate(String aiCertValidDate) {
        this.aiCertValidDate = aiCertValidDate == null ? null : aiCertValidDate.trim();
    }

    public String getAiInstTranCertValidDate() {
        return aiInstTranCertValidDate;
    }

    public void setAiInstTranCertValidDate(String aiInstTranCertValidDate) {
        this.aiInstTranCertValidDate = aiInstTranCertValidDate == null ? null : aiInstTranCertValidDate.trim();
    }

    public String getAiInstReprCertValidDate() {
        return aiInstReprCertValidDate;
    }

    public void setAiInstReprCertValidDate(String aiInstReprCertValidDate) {
        this.aiInstReprCertValidDate = aiInstReprCertValidDate == null ? null : aiInstReprCertValidDate.trim();
    }

    public String getAiClientRiskRate() {
        return aiClientRiskRate;
    }

    public void setAiClientRiskRate(String aiClientRiskRate) {
        this.aiClientRiskRate = aiClientRiskRate == null ? null : aiClientRiskRate.trim();
    }

    public String getAiInstReprManageRange() {
        return aiInstReprManageRange;
    }

    public void setAiInstReprManageRange(String aiInstReprManageRange) {
        this.aiInstReprManageRange = aiInstReprManageRange == null ? null : aiInstReprManageRange.trim();
    }

    public String getAiControlHolder() {
        return aiControlHolder;
    }

    public void setAiControlHolder(String aiControlHolder) {
        this.aiControlHolder = aiControlHolder == null ? null : aiControlHolder.trim();
    }

    public String getAiActualController() {
        return aiActualController;
    }

    public void setAiActualController(String aiActualController) {
        this.aiActualController = aiActualController == null ? null : aiActualController.trim();
    }

    public String getAiMarriageStatus() {
        return aiMarriageStatus;
    }

    public void setAiMarriageStatus(String aiMarriageStatus) {
        this.aiMarriageStatus = aiMarriageStatus == null ? null : aiMarriageStatus.trim();
    }

    public BigDecimal getAiFamilyNum() {
        return aiFamilyNum;
    }

    public void setAiFamilyNum(BigDecimal aiFamilyNum) {
        this.aiFamilyNum = aiFamilyNum;
    }

    public BigDecimal getAiPenates() {
        return aiPenates;
    }

    public void setAiPenates(BigDecimal aiPenates) {
        this.aiPenates = aiPenates;
    }

    public String getAiMediaHobby() {
        return aiMediaHobby;
    }

    public void setAiMediaHobby(String aiMediaHobby) {
        this.aiMediaHobby = aiMediaHobby == null ? null : aiMediaHobby.trim();
    }

    public String getAiInstitutionType() {
        return aiInstitutionType;
    }

    public void setAiInstitutionType(String aiInstitutionType) {
        this.aiInstitutionType = aiInstitutionType == null ? null : aiInstitutionType.trim();
    }

    public String getAiEnglishFirstName() {
        return aiEnglishFirstName;
    }

    public void setAiEnglishFirstName(String aiEnglishFirstName) {
        this.aiEnglishFirstName = aiEnglishFirstName == null ? null : aiEnglishFirstName.trim();
    }

    public String getAiEnglishFamliyName() {
        return aiEnglishFamliyName;
    }

    public void setAiEnglishFamliyName(String aiEnglishFamliyName) {
        this.aiEnglishFamliyName = aiEnglishFamliyName == null ? null : aiEnglishFamliyName.trim();
    }

    public String getAiVocation() {
        return aiVocation;
    }

    public void setAiVocation(String aiVocation) {
        this.aiVocation = aiVocation == null ? null : aiVocation.trim();
    }

    public String getAiCorpoProperty() {
        return aiCorpoProperty;
    }

    public void setAiCorpoProperty(String aiCorpoProperty) {
        this.aiCorpoProperty = aiCorpoProperty == null ? null : aiCorpoProperty.trim();
    }

    public BigDecimal getAiStaffNum() {
        return aiStaffNum;
    }

    public void setAiStaffNum(BigDecimal aiStaffNum) {
        this.aiStaffNum = aiStaffNum;
    }

    public String getAiHobbytype() {
        return aiHobbytype;
    }

    public void setAiHobbytype(String aiHobbytype) {
        this.aiHobbytype = aiHobbytype == null ? null : aiHobbytype.trim();
    }

    public String getAiProvince() {
        return aiProvince;
    }

    public void setAiProvince(String aiProvince) {
        this.aiProvince = aiProvince == null ? null : aiProvince.trim();
    }

    public String getAiCity() {
        return aiCity;
    }

    public void setAiCity(String aiCity) {
        this.aiCity = aiCity == null ? null : aiCity.trim();
    }

    public String getAiCounty() {
        return aiCounty;
    }

    public void setAiCounty(String aiCounty) {
        this.aiCounty = aiCounty == null ? null : aiCounty.trim();
    }

    public String getAiCommendPerson() {
        return aiCommendPerson;
    }

    public void setAiCommendPerson(String aiCommendPerson) {
        this.aiCommendPerson = aiCommendPerson == null ? null : aiCommendPerson.trim();
    }

    public String getAiCommendPersonType() {
        return aiCommendPersonType;
    }

    public void setAiCommendPersonType(String aiCommendPersonType) {
        this.aiCommendPersonType = aiCommendPersonType == null ? null : aiCommendPersonType.trim();
    }

    public String getAiAcceptMethod() {
        return aiAcceptMethod;
    }

    public void setAiAcceptMethod(String aiAcceptMethod) {
        this.aiAcceptMethod = aiAcceptMethod == null ? null : aiAcceptMethod.trim();
    }

    public String getAiFrozenCause() {
        return aiFrozenCause;
    }

    public void setAiFrozenCause(String aiFrozenCause) {
        this.aiFrozenCause = aiFrozenCause == null ? null : aiFrozenCause.trim();
    }

    public String getAiFreezingDeadline() {
        return aiFreezingDeadline;
    }

    public void setAiFreezingDeadline(String aiFreezingDeadline) {
        this.aiFreezingDeadline = aiFreezingDeadline == null ? null : aiFreezingDeadline.trim();
    }

    public String getAiOriginalSerialNo() {
        return aiOriginalSerialNo;
    }

    public void setAiOriginalSerialNo(String aiOriginalSerialNo) {
        this.aiOriginalSerialNo = aiOriginalSerialNo == null ? null : aiOriginalSerialNo.trim();
    }

    public String getAiOriginalAppSheetNo() {
        return aiOriginalAppSheetNo;
    }

    public void setAiOriginalAppSheetNo(String aiOriginalAppSheetNo) {
        this.aiOriginalAppSheetNo = aiOriginalAppSheetNo == null ? null : aiOriginalAppSheetNo.trim();
    }

    public String getAiSpecification() {
        return aiSpecification;
    }

    public void setAiSpecification(String aiSpecification) {
        this.aiSpecification = aiSpecification == null ? null : aiSpecification.trim();
    }

    public Date getAiCtime() {
        return aiCtime;
    }

    public void setAiCtime(Date aiCtime) {
        this.aiCtime = aiCtime;
    }

    public Date getAiUtime() {
        return aiUtime;
    }

    public void setAiUtime(Date aiUtime) {
        this.aiUtime = aiUtime;
    }

    public String getAiCuser() {
        return aiCuser;
    }

    public void setAiCuser(String aiCuser) {
        this.aiCuser = aiCuser == null ? null : aiCuser.trim();
    }

    public String getAiUuser() {
        return aiUuser;
    }

    public void setAiUuser(String aiUuser) {
        this.aiUuser = aiUuser == null ? null : aiUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aiId=").append(aiId);
        sb.append(", aiChannelCode=").append(aiChannelCode);
        sb.append(", aiAddress=").append(aiAddress);
        sb.append(", aiInstReprIdCode=").append(aiInstReprIdCode);
        sb.append(", aiInstReprIdType=").append(aiInstReprIdType);
        sb.append(", aiInstReprName=").append(aiInstReprName);
        sb.append(", aiAppSheetSerialNo=").append(aiAppSheetSerialNo);
        sb.append(", aiCertificateType=").append(aiCertificateType);
        sb.append(", aiCertificateNo=").append(aiCertificateNo);
        sb.append(", aiInvestorName=").append(aiInvestorName);
        sb.append(", aiTransactionDate=").append(aiTransactionDate);
        sb.append(", aiTransactionTime=").append(aiTransactionTime);
        sb.append(", aiIndividualOrInstitution=").append(aiIndividualOrInstitution);
        sb.append(", aiPostCode=").append(aiPostCode);
        sb.append(", aiTransactorCertNo=").append(aiTransactorCertNo);
        sb.append(", aiTransactorCertType=").append(aiTransactorCertType);
        sb.append(", aiTransactorName=").append(aiTransactorName);
        sb.append(", aiTransactionAccountId=").append(aiTransactionAccountId);
        sb.append(", aiDistributorCode=").append(aiDistributorCode);
        sb.append(", aiBusinessCode=").append(aiBusinessCode);
        sb.append(", aiAcctNoFmInCleaiAgency=").append(aiAcctNoFmInCleaiAgency);
        sb.append(", aiAcctNameFICleaiAgency=").append(aiAcctNameFICleaiAgency);
        sb.append(", aiClearingAgencyCode=").append(aiClearingAgencyCode);
        sb.append(", aiInvestorsBirthday=").append(aiInvestorsBirthday);
        sb.append(", aiDepositAcct=").append(aiDepositAcct);
        sb.append(", aiRegionCode=").append(aiRegionCode);
        sb.append(", aiEducationLevel=").append(aiEducationLevel);
        sb.append(", aiEmailAddress=").append(aiEmailAddress);
        sb.append(", aiFaxNo=").append(aiFaxNo);
        sb.append(", aiVocationCode=").append(aiVocationCode);
        sb.append(", aiHomeTelNo=").append(aiHomeTelNo);
        sb.append(", aiAnnualIncome=").append(aiAnnualIncome);
        sb.append(", aiMobileTelNo=").append(aiMobileTelNo);
        sb.append(", aiBranchCode=").append(aiBranchCode);
        sb.append(", aiOfficeTelNo=").append(aiOfficeTelNo);
        sb.append(", aiAccountAbbr=").append(aiAccountAbbr);
        sb.append(", aiConfidentialDocumentCode=").append(aiConfidentialDocumentCode);
        sb.append(", aiSex=").append(aiSex);
        sb.append(", aiShSecuritiesAccountid=").append(aiShSecuritiesAccountid);
        sb.append(", aiSzSecuritiesAccountid=").append(aiSzSecuritiesAccountid);
        sb.append(", aiTaAccountId=").append(aiTaAccountId);
        sb.append(", aiTelNo=").append(aiTelNo);
        sb.append(", aiTradingMethod=").append(aiTradingMethod);
        sb.append(", aiMinorFlag=").append(aiMinorFlag);
        sb.append(", aiDeliverType=").append(aiDeliverType);
        sb.append(", aiTransactorIdType=").append(aiTransactorIdType);
        sb.append(", aiAccountCardId=").append(aiAccountCardId);
        sb.append(", aiMultiacctFlag=").append(aiMultiacctFlag);
        sb.append(", aiTargetTransactAcctCode=").append(aiTargetTransactAcctCode);
        sb.append(", aiAcctNameInveCleaiAgen=").append(aiAcctNameInveCleaiAgen);
        sb.append(", aiAcctNoInveCleaiAgen=").append(aiAcctNoInveCleaiAgen);
        sb.append(", aiClearingAgency=").append(aiClearingAgency);
        sb.append(", aiDeliverWay=").append(aiDeliverWay);
        sb.append(", aiNationality=").append(aiNationality);
        sb.append(", aiNetNo=").append(aiNetNo);
        sb.append(", aiBroker=").append(aiBroker);
        sb.append(", aiCorpName=").append(aiCorpName);
        sb.append(", aiCertValidDate=").append(aiCertValidDate);
        sb.append(", aiInstTranCertValidDate=").append(aiInstTranCertValidDate);
        sb.append(", aiInstReprCertValidDate=").append(aiInstReprCertValidDate);
        sb.append(", aiClientRiskRate=").append(aiClientRiskRate);
        sb.append(", aiInstReprManageRange=").append(aiInstReprManageRange);
        sb.append(", aiControlHolder=").append(aiControlHolder);
        sb.append(", aiActualController=").append(aiActualController);
        sb.append(", aiMarriageStatus=").append(aiMarriageStatus);
        sb.append(", aiFamilyNum=").append(aiFamilyNum);
        sb.append(", aiPenates=").append(aiPenates);
        sb.append(", aiMediaHobby=").append(aiMediaHobby);
        sb.append(", aiInstitutionType=").append(aiInstitutionType);
        sb.append(", aiEnglishFirstName=").append(aiEnglishFirstName);
        sb.append(", aiEnglishFamliyName=").append(aiEnglishFamliyName);
        sb.append(", aiVocation=").append(aiVocation);
        sb.append(", aiCorpoProperty=").append(aiCorpoProperty);
        sb.append(", aiStaffNum=").append(aiStaffNum);
        sb.append(", aiHobbytype=").append(aiHobbytype);
        sb.append(", aiProvince=").append(aiProvince);
        sb.append(", aiCity=").append(aiCity);
        sb.append(", aiCounty=").append(aiCounty);
        sb.append(", aiCommendPerson=").append(aiCommendPerson);
        sb.append(", aiCommendPersonType=").append(aiCommendPersonType);
        sb.append(", aiAcceptMethod=").append(aiAcceptMethod);
        sb.append(", aiFrozenCause=").append(aiFrozenCause);
        sb.append(", aiFreezingDeadline=").append(aiFreezingDeadline);
        sb.append(", aiOriginalSerialNo=").append(aiOriginalSerialNo);
        sb.append(", aiOriginalAppSheetNo=").append(aiOriginalAppSheetNo);
        sb.append(", aiSpecification=").append(aiSpecification);
        sb.append(", aiCtime=").append(aiCtime);
        sb.append(", aiUtime=").append(aiUtime);
        sb.append(", aiCuser=").append(aiCuser);
        sb.append(", aiUuser=").append(aiUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}