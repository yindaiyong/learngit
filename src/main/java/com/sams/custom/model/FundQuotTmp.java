package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundQuotTmp implements Serializable {
    private Long fqId;

    private String fqChannelCode;

    private String fqFundName;

    private String fqFundCode;

    private BigDecimal fqTotalFundVol;

    private String fqFundStatus;

    private BigDecimal fqNav;

    private String fqUpdateDate;

    private String fqNetValueType;

    private BigDecimal fqAccumulativeNav;

    private String fqConvertStatus;

    private String fqCurrencyType;

    private BigDecimal fqInstAppSubsAmnt;

    private BigDecimal fqMinAmountByInst;

    private String fqIpostartDate;

    private String fqIpoendDate;

    private BigDecimal fqIndiAppSubsAmount;

    private BigDecimal fqMinSubsAmountByIndi;

    private BigDecimal fqUnitSubsAmountByIndi;

    private BigDecimal fqUnitSubsAmountByInst;

    private BigDecimal fqMinBidsAmountByIndi;

    private BigDecimal fqMinBidsAmountByInst;

    private BigDecimal fqMinAppBidsAmountByIndi;

    private BigDecimal fqMinAppBidsAmountByInst;

    private String fqCollectFeeType;

    private BigDecimal fqFundIncome;

    private String fqFundIncomeFlag;

    private BigDecimal fqYield;

    private String fqYieldFlag;

    private Date fqDataGenerate;

    private Date fqCtime;

    private Date fqUtime;

    private String fqCuser;

    private String fqUuser;

    private BigDecimal fqInstFayMaxSumBuy;

    private String fqExchangeDate;

    private String fqSztSendPath;

    private String fqCsdcVer;

    private String fqValidFlag;

    private String fqBatchNo;

    private String fqMaxAmountRaised;

    private String fqProductType;

    private String fqTaProductCode;

    private String fqRemainShares;

    private String fqMinAccountBalance;

    private String fqPeriodicStatus;

    private String fqTransferAgencyStatus;

    private BigDecimal fqFundSize;

    private String fqAnnouncFlag;

    private String fqDefDividenDmethod;

    private BigDecimal fqInstAppSubsVol;

    private BigDecimal fqMinVolByInst;

    private String fqCustodianCode;

    private BigDecimal fqAmountOfPeriodicSubs;

    private String fqDateOfPeriodicSubs;

    private BigDecimal fqMaxRedemptionVol;

    private String fqFundManagerCode;

    private BigDecimal fqIndiAppSubsVol;

    private BigDecimal fqMinSubsVolByIndi;

    private String fqRegistrarCode;

    private String fqFundSponsor;

    private BigDecimal fqTradingPrice;

    private BigDecimal fqFaceValue;

    private String fqDividentDate;

    private String fqRegistrationDate;

    private String fqXrDate;

    private BigDecimal fqMaxSubsVolByIndi;

    private BigDecimal fqMaxSubsAmountByIndi;

    private BigDecimal fqMaxSubsVolByInst;

    private BigDecimal fqMaxSubsAmountByInst;

    private BigDecimal fqUnitSubsVolByIndi;

    private BigDecimal fqUnitSubsVolByInst;

    private BigDecimal fqMinRedemptionVol;

    private BigDecimal fqMinInterconvertVol;

    private String fqIssueTypeByIndi;

    private String fqIssueTypeByInst;

    private String fqSubsType;

    private String fqNextTradeDate;

    private BigDecimal fqValueLine;

    private BigDecimal fqTotalDivident;

    private BigDecimal fqGuaranteedNav;

    private BigDecimal fqFundYearIncomeRate;

    private String fqFundYearIncomeRateFlag;

    private BigDecimal fqIndiMaxPurchase;

    private BigDecimal fqInstMaxPurchase;

    private BigDecimal fqIndiDayMaxSumBuy;

    private BigDecimal fqIndiDayMaxSumRedeem;

    private BigDecimal fqInstDayMaxSumRedeem;

    private BigDecimal fqIndiMaxRedeem;

    private BigDecimal fqInstMaxRedeem;

    private String fqFundDayIncomeFlag;

    private BigDecimal fqFundDayIncome;

    private String fqAllowBreachRedempt;

    private String fqFundType;

    private String fqFundTypeName;

    private String fqRegistrarName;

    private String fqFundManagerName;

    private String fqFundServerTel;

    private String fqDundInternetAddress;
    
    private String fqProSetupDate;

    private static final long serialVersionUID = 1L;

    public Long getFqId() {
        return fqId;
    }

    public void setFqId(Long fqId) {
        this.fqId = fqId;
    }

    public String getFqChannelCode() {
        return fqChannelCode;
    }

    public void setFqChannelCode(String fqChannelCode) {
        this.fqChannelCode = fqChannelCode == null ? null : fqChannelCode.trim();
    }

    public String getFqFundName() {
        return fqFundName;
    }

    public void setFqFundName(String fqFundName) {
        this.fqFundName = fqFundName == null ? null : fqFundName.trim();
    }

    public String getFqFundCode() {
        return fqFundCode;
    }

    public void setFqFundCode(String fqFundCode) {
        this.fqFundCode = fqFundCode == null ? null : fqFundCode.trim();
    }

    public BigDecimal getFqTotalFundVol() {
        return fqTotalFundVol;
    }

    public void setFqTotalFundVol(BigDecimal fqTotalFundVol) {
        this.fqTotalFundVol = fqTotalFundVol;
    }

    public String getFqFundStatus() {
        return fqFundStatus;
    }

    public void setFqFundStatus(String fqFundStatus) {
        this.fqFundStatus = fqFundStatus == null ? null : fqFundStatus.trim();
    }

    public BigDecimal getFqNav() {
        return fqNav;
    }

    public void setFqNav(BigDecimal fqNav) {
        this.fqNav = fqNav;
    }

    public String getFqUpdateDate() {
        return fqUpdateDate;
    }

    public void setFqUpdateDate(String fqUpdateDate) {
        this.fqUpdateDate = fqUpdateDate == null ? null : fqUpdateDate.trim();
    }

    public String getFqNetValueType() {
        return fqNetValueType;
    }

    public void setFqNetValueType(String fqNetValueType) {
        this.fqNetValueType = fqNetValueType == null ? null : fqNetValueType.trim();
    }

    public BigDecimal getFqAccumulativeNav() {
        return fqAccumulativeNav;
    }

    public void setFqAccumulativeNav(BigDecimal fqAccumulativeNav) {
        this.fqAccumulativeNav = fqAccumulativeNav;
    }

    public String getFqConvertStatus() {
        return fqConvertStatus;
    }

    public void setFqConvertStatus(String fqConvertStatus) {
        this.fqConvertStatus = fqConvertStatus == null ? null : fqConvertStatus.trim();
    }

    public String getFqCurrencyType() {
        return fqCurrencyType;
    }

    public void setFqCurrencyType(String fqCurrencyType) {
        this.fqCurrencyType = fqCurrencyType == null ? null : fqCurrencyType.trim();
    }

    public BigDecimal getFqInstAppSubsAmnt() {
        return fqInstAppSubsAmnt;
    }

    public void setFqInstAppSubsAmnt(BigDecimal fqInstAppSubsAmnt) {
        this.fqInstAppSubsAmnt = fqInstAppSubsAmnt;
    }

    public BigDecimal getFqMinAmountByInst() {
        return fqMinAmountByInst;
    }

    public void setFqMinAmountByInst(BigDecimal fqMinAmountByInst) {
        this.fqMinAmountByInst = fqMinAmountByInst;
    }

    public String getFqIpostartDate() {
        return fqIpostartDate;
    }

    public void setFqIpostartDate(String fqIpostartDate) {
        this.fqIpostartDate = fqIpostartDate == null ? null : fqIpostartDate.trim();
    }

    public String getFqIpoendDate() {
        return fqIpoendDate;
    }

    public void setFqIpoendDate(String fqIpoendDate) {
        this.fqIpoendDate = fqIpoendDate == null ? null : fqIpoendDate.trim();
    }

    public BigDecimal getFqIndiAppSubsAmount() {
        return fqIndiAppSubsAmount;
    }

    public void setFqIndiAppSubsAmount(BigDecimal fqIndiAppSubsAmount) {
        this.fqIndiAppSubsAmount = fqIndiAppSubsAmount;
    }

    public BigDecimal getFqMinSubsAmountByIndi() {
        return fqMinSubsAmountByIndi;
    }

    public void setFqMinSubsAmountByIndi(BigDecimal fqMinSubsAmountByIndi) {
        this.fqMinSubsAmountByIndi = fqMinSubsAmountByIndi;
    }

    public BigDecimal getFqUnitSubsAmountByIndi() {
        return fqUnitSubsAmountByIndi;
    }

    public void setFqUnitSubsAmountByIndi(BigDecimal fqUnitSubsAmountByIndi) {
        this.fqUnitSubsAmountByIndi = fqUnitSubsAmountByIndi;
    }

    public BigDecimal getFqUnitSubsAmountByInst() {
        return fqUnitSubsAmountByInst;
    }

    public void setFqUnitSubsAmountByInst(BigDecimal fqUnitSubsAmountByInst) {
        this.fqUnitSubsAmountByInst = fqUnitSubsAmountByInst;
    }

    public BigDecimal getFqMinBidsAmountByIndi() {
        return fqMinBidsAmountByIndi;
    }

    public void setFqMinBidsAmountByIndi(BigDecimal fqMinBidsAmountByIndi) {
        this.fqMinBidsAmountByIndi = fqMinBidsAmountByIndi;
    }

    public BigDecimal getFqMinBidsAmountByInst() {
        return fqMinBidsAmountByInst;
    }

    public void setFqMinBidsAmountByInst(BigDecimal fqMinBidsAmountByInst) {
        this.fqMinBidsAmountByInst = fqMinBidsAmountByInst;
    }

    public BigDecimal getFqMinAppBidsAmountByIndi() {
        return fqMinAppBidsAmountByIndi;
    }

    public void setFqMinAppBidsAmountByIndi(BigDecimal fqMinAppBidsAmountByIndi) {
        this.fqMinAppBidsAmountByIndi = fqMinAppBidsAmountByIndi;
    }

    public BigDecimal getFqMinAppBidsAmountByInst() {
        return fqMinAppBidsAmountByInst;
    }

    public void setFqMinAppBidsAmountByInst(BigDecimal fqMinAppBidsAmountByInst) {
        this.fqMinAppBidsAmountByInst = fqMinAppBidsAmountByInst;
    }

    public String getFqCollectFeeType() {
        return fqCollectFeeType;
    }

    public void setFqCollectFeeType(String fqCollectFeeType) {
        this.fqCollectFeeType = fqCollectFeeType == null ? null : fqCollectFeeType.trim();
    }

    public BigDecimal getFqFundIncome() {
        return fqFundIncome;
    }

    public void setFqFundIncome(BigDecimal fqFundIncome) {
        this.fqFundIncome = fqFundIncome;
    }

    public String getFqFundIncomeFlag() {
        return fqFundIncomeFlag;
    }

    public void setFqFundIncomeFlag(String fqFundIncomeFlag) {
        this.fqFundIncomeFlag = fqFundIncomeFlag == null ? null : fqFundIncomeFlag.trim();
    }

    public BigDecimal getFqYield() {
        return fqYield;
    }

    public void setFqYield(BigDecimal fqYield) {
        this.fqYield = fqYield;
    }

    public String getFqYieldFlag() {
        return fqYieldFlag;
    }

    public void setFqYieldFlag(String fqYieldFlag) {
        this.fqYieldFlag = fqYieldFlag == null ? null : fqYieldFlag.trim();
    }

    public Date getFqDataGenerate() {
        return fqDataGenerate;
    }

    public void setFqDataGenerate(Date fqDataGenerate) {
        this.fqDataGenerate = fqDataGenerate;
    }

    public Date getFqCtime() {
        return fqCtime;
    }

    public void setFqCtime(Date fqCtime) {
        this.fqCtime = fqCtime;
    }

    public Date getFqUtime() {
        return fqUtime;
    }

    public void setFqUtime(Date fqUtime) {
        this.fqUtime = fqUtime;
    }

    public String getFqCuser() {
        return fqCuser;
    }

    public void setFqCuser(String fqCuser) {
        this.fqCuser = fqCuser == null ? null : fqCuser.trim();
    }

    public String getFqUuser() {
        return fqUuser;
    }

    public void setFqUuser(String fqUuser) {
        this.fqUuser = fqUuser == null ? null : fqUuser.trim();
    }

    public BigDecimal getFqInstFayMaxSumBuy() {
        return fqInstFayMaxSumBuy;
    }

    public void setFqInstFayMaxSumBuy(BigDecimal fqInstFayMaxSumBuy) {
        this.fqInstFayMaxSumBuy = fqInstFayMaxSumBuy;
    }

    public String getFqExchangeDate() {
        return fqExchangeDate;
    }

    public void setFqExchangeDate(String fqExchangeDate) {
        this.fqExchangeDate = fqExchangeDate == null ? null : fqExchangeDate.trim();
    }

    public String getFqSztSendPath() {
        return fqSztSendPath;
    }

    public void setFqSztSendPath(String fqSztSendPath) {
        this.fqSztSendPath = fqSztSendPath == null ? null : fqSztSendPath.trim();
    }

    public String getFqCsdcVer() {
        return fqCsdcVer;
    }

    public void setFqCsdcVer(String fqCsdcVer) {
        this.fqCsdcVer = fqCsdcVer == null ? null : fqCsdcVer.trim();
    }

    public String getFqValidFlag() {
        return fqValidFlag;
    }

    public void setFqValidFlag(String fqValidFlag) {
        this.fqValidFlag = fqValidFlag == null ? null : fqValidFlag.trim();
    }

    public String getFqBatchNo() {
        return fqBatchNo;
    }

    public void setFqBatchNo(String fqBatchNo) {
        this.fqBatchNo = fqBatchNo == null ? null : fqBatchNo.trim();
    }

    public String getFqMaxAmountRaised() {
        return fqMaxAmountRaised;
    }

    public void setFqMaxAmountRaised(String fqMaxAmountRaised) {
        this.fqMaxAmountRaised = fqMaxAmountRaised == null ? null : fqMaxAmountRaised.trim();
    }

    public String getFqProductType() {
        return fqProductType;
    }

    public void setFqProductType(String fqProductType) {
        this.fqProductType = fqProductType == null ? null : fqProductType.trim();
    }

    public String getFqTaProductCode() {
        return fqTaProductCode;
    }

    public void setFqTaProductCode(String fqTaProductCode) {
        this.fqTaProductCode = fqTaProductCode == null ? null : fqTaProductCode.trim();
    }

    public String getFqRemainShares() {
        return fqRemainShares;
    }

    public void setFqRemainShares(String fqRemainShares) {
        this.fqRemainShares = fqRemainShares == null ? null : fqRemainShares.trim();
    }

    public String getFqMinAccountBalance() {
        return fqMinAccountBalance;
    }

    public void setFqMinAccountBalance(String fqMinAccountBalance) {
        this.fqMinAccountBalance = fqMinAccountBalance == null ? null : fqMinAccountBalance.trim();
    }

    public String getFqPeriodicStatus() {
        return fqPeriodicStatus;
    }

    public void setFqPeriodicStatus(String fqPeriodicStatus) {
        this.fqPeriodicStatus = fqPeriodicStatus == null ? null : fqPeriodicStatus.trim();
    }

    public String getFqTransferAgencyStatus() {
        return fqTransferAgencyStatus;
    }

    public void setFqTransferAgencyStatus(String fqTransferAgencyStatus) {
        this.fqTransferAgencyStatus = fqTransferAgencyStatus == null ? null : fqTransferAgencyStatus.trim();
    }

    public BigDecimal getFqFundSize() {
        return fqFundSize;
    }

    public void setFqFundSize(BigDecimal fqFundSize) {
        this.fqFundSize = fqFundSize;
    }

    public String getFqAnnouncFlag() {
        return fqAnnouncFlag;
    }

    public void setFqAnnouncFlag(String fqAnnouncFlag) {
        this.fqAnnouncFlag = fqAnnouncFlag == null ? null : fqAnnouncFlag.trim();
    }

    public String getFqDefDividenDmethod() {
        return fqDefDividenDmethod;
    }

    public void setFqDefDividenDmethod(String fqDefDividenDmethod) {
        this.fqDefDividenDmethod = fqDefDividenDmethod == null ? null : fqDefDividenDmethod.trim();
    }

    public BigDecimal getFqInstAppSubsVol() {
        return fqInstAppSubsVol;
    }

    public void setFqInstAppSubsVol(BigDecimal fqInstAppSubsVol) {
        this.fqInstAppSubsVol = fqInstAppSubsVol;
    }

    public BigDecimal getFqMinVolByInst() {
        return fqMinVolByInst;
    }

    public void setFqMinVolByInst(BigDecimal fqMinVolByInst) {
        this.fqMinVolByInst = fqMinVolByInst;
    }

    public String getFqCustodianCode() {
        return fqCustodianCode;
    }

    public void setFqCustodianCode(String fqCustodianCode) {
        this.fqCustodianCode = fqCustodianCode == null ? null : fqCustodianCode.trim();
    }

    public BigDecimal getFqAmountOfPeriodicSubs() {
        return fqAmountOfPeriodicSubs;
    }

    public void setFqAmountOfPeriodicSubs(BigDecimal fqAmountOfPeriodicSubs) {
        this.fqAmountOfPeriodicSubs = fqAmountOfPeriodicSubs;
    }

    public String getFqDateOfPeriodicSubs() {
        return fqDateOfPeriodicSubs;
    }

    public void setFqDateOfPeriodicSubs(String fqDateOfPeriodicSubs) {
        this.fqDateOfPeriodicSubs = fqDateOfPeriodicSubs == null ? null : fqDateOfPeriodicSubs.trim();
    }

    public BigDecimal getFqMaxRedemptionVol() {
        return fqMaxRedemptionVol;
    }

    public void setFqMaxRedemptionVol(BigDecimal fqMaxRedemptionVol) {
        this.fqMaxRedemptionVol = fqMaxRedemptionVol;
    }

    public String getFqFundManagerCode() {
        return fqFundManagerCode;
    }

    public void setFqFundManagerCode(String fqFundManagerCode) {
        this.fqFundManagerCode = fqFundManagerCode == null ? null : fqFundManagerCode.trim();
    }

    public BigDecimal getFqIndiAppSubsVol() {
        return fqIndiAppSubsVol;
    }

    public void setFqIndiAppSubsVol(BigDecimal fqIndiAppSubsVol) {
        this.fqIndiAppSubsVol = fqIndiAppSubsVol;
    }

    public BigDecimal getFqMinSubsVolByIndi() {
        return fqMinSubsVolByIndi;
    }

    public void setFqMinSubsVolByIndi(BigDecimal fqMinSubsVolByIndi) {
        this.fqMinSubsVolByIndi = fqMinSubsVolByIndi;
    }

    public String getFqRegistrarCode() {
        return fqRegistrarCode;
    }

    public void setFqRegistrarCode(String fqRegistrarCode) {
        this.fqRegistrarCode = fqRegistrarCode == null ? null : fqRegistrarCode.trim();
    }

    public String getFqFundSponsor() {
        return fqFundSponsor;
    }

    public void setFqFundSponsor(String fqFundSponsor) {
        this.fqFundSponsor = fqFundSponsor == null ? null : fqFundSponsor.trim();
    }

    public BigDecimal getFqTradingPrice() {
        return fqTradingPrice;
    }

    public void setFqTradingPrice(BigDecimal fqTradingPrice) {
        this.fqTradingPrice = fqTradingPrice;
    }

    public BigDecimal getFqFaceValue() {
        return fqFaceValue;
    }

    public void setFqFaceValue(BigDecimal fqFaceValue) {
        this.fqFaceValue = fqFaceValue;
    }

    public String getFqDividentDate() {
        return fqDividentDate;
    }

    public void setFqDividentDate(String fqDividentDate) {
        this.fqDividentDate = fqDividentDate == null ? null : fqDividentDate.trim();
    }

    public String getFqRegistrationDate() {
        return fqRegistrationDate;
    }

    public void setFqRegistrationDate(String fqRegistrationDate) {
        this.fqRegistrationDate = fqRegistrationDate == null ? null : fqRegistrationDate.trim();
    }

    public String getFqXrDate() {
        return fqXrDate;
    }

    public void setFqXrDate(String fqXrDate) {
        this.fqXrDate = fqXrDate == null ? null : fqXrDate.trim();
    }

    public BigDecimal getFqMaxSubsVolByIndi() {
        return fqMaxSubsVolByIndi;
    }

    public void setFqMaxSubsVolByIndi(BigDecimal fqMaxSubsVolByIndi) {
        this.fqMaxSubsVolByIndi = fqMaxSubsVolByIndi;
    }

    public BigDecimal getFqMaxSubsAmountByIndi() {
        return fqMaxSubsAmountByIndi;
    }

    public void setFqMaxSubsAmountByIndi(BigDecimal fqMaxSubsAmountByIndi) {
        this.fqMaxSubsAmountByIndi = fqMaxSubsAmountByIndi;
    }

    public BigDecimal getFqMaxSubsVolByInst() {
        return fqMaxSubsVolByInst;
    }

    public void setFqMaxSubsVolByInst(BigDecimal fqMaxSubsVolByInst) {
        this.fqMaxSubsVolByInst = fqMaxSubsVolByInst;
    }

    public BigDecimal getFqMaxSubsAmountByInst() {
        return fqMaxSubsAmountByInst;
    }

    public void setFqMaxSubsAmountByInst(BigDecimal fqMaxSubsAmountByInst) {
        this.fqMaxSubsAmountByInst = fqMaxSubsAmountByInst;
    }

    public BigDecimal getFqUnitSubsVolByIndi() {
        return fqUnitSubsVolByIndi;
    }

    public void setFqUnitSubsVolByIndi(BigDecimal fqUnitSubsVolByIndi) {
        this.fqUnitSubsVolByIndi = fqUnitSubsVolByIndi;
    }

    public BigDecimal getFqUnitSubsVolByInst() {
        return fqUnitSubsVolByInst;
    }

    public void setFqUnitSubsVolByInst(BigDecimal fqUnitSubsVolByInst) {
        this.fqUnitSubsVolByInst = fqUnitSubsVolByInst;
    }

    public BigDecimal getFqMinRedemptionVol() {
        return fqMinRedemptionVol;
    }

    public void setFqMinRedemptionVol(BigDecimal fqMinRedemptionVol) {
        this.fqMinRedemptionVol = fqMinRedemptionVol;
    }

    public BigDecimal getFqMinInterconvertVol() {
        return fqMinInterconvertVol;
    }

    public void setFqMinInterconvertVol(BigDecimal fqMinInterconvertVol) {
        this.fqMinInterconvertVol = fqMinInterconvertVol;
    }

    public String getFqIssueTypeByIndi() {
        return fqIssueTypeByIndi;
    }

    public void setFqIssueTypeByIndi(String fqIssueTypeByIndi) {
        this.fqIssueTypeByIndi = fqIssueTypeByIndi == null ? null : fqIssueTypeByIndi.trim();
    }

    public String getFqIssueTypeByInst() {
        return fqIssueTypeByInst;
    }

    public void setFqIssueTypeByInst(String fqIssueTypeByInst) {
        this.fqIssueTypeByInst = fqIssueTypeByInst == null ? null : fqIssueTypeByInst.trim();
    }

    public String getFqSubsType() {
        return fqSubsType;
    }

    public void setFqSubsType(String fqSubsType) {
        this.fqSubsType = fqSubsType == null ? null : fqSubsType.trim();
    }

    public String getFqNextTradeDate() {
        return fqNextTradeDate;
    }

    public void setFqNextTradeDate(String fqNextTradeDate) {
        this.fqNextTradeDate = fqNextTradeDate == null ? null : fqNextTradeDate.trim();
    }

    public BigDecimal getFqValueLine() {
        return fqValueLine;
    }

    public void setFqValueLine(BigDecimal fqValueLine) {
        this.fqValueLine = fqValueLine;
    }

    public BigDecimal getFqTotalDivident() {
        return fqTotalDivident;
    }

    public void setFqTotalDivident(BigDecimal fqTotalDivident) {
        this.fqTotalDivident = fqTotalDivident;
    }

    public BigDecimal getFqGuaranteedNav() {
        return fqGuaranteedNav;
    }

    public void setFqGuaranteedNav(BigDecimal fqGuaranteedNav) {
        this.fqGuaranteedNav = fqGuaranteedNav;
    }

    public BigDecimal getFqFundYearIncomeRate() {
        return fqFundYearIncomeRate;
    }

    public void setFqFundYearIncomeRate(BigDecimal fqFundYearIncomeRate) {
        this.fqFundYearIncomeRate = fqFundYearIncomeRate;
    }

    public String getFqFundYearIncomeRateFlag() {
        return fqFundYearIncomeRateFlag;
    }

    public void setFqFundYearIncomeRateFlag(String fqFundYearIncomeRateFlag) {
        this.fqFundYearIncomeRateFlag = fqFundYearIncomeRateFlag == null ? null : fqFundYearIncomeRateFlag.trim();
    }

    public BigDecimal getFqIndiMaxPurchase() {
        return fqIndiMaxPurchase;
    }

    public void setFqIndiMaxPurchase(BigDecimal fqIndiMaxPurchase) {
        this.fqIndiMaxPurchase = fqIndiMaxPurchase;
    }

    public BigDecimal getFqInstMaxPurchase() {
        return fqInstMaxPurchase;
    }

    public void setFqInstMaxPurchase(BigDecimal fqInstMaxPurchase) {
        this.fqInstMaxPurchase = fqInstMaxPurchase;
    }

    public BigDecimal getFqIndiDayMaxSumBuy() {
        return fqIndiDayMaxSumBuy;
    }

    public void setFqIndiDayMaxSumBuy(BigDecimal fqIndiDayMaxSumBuy) {
        this.fqIndiDayMaxSumBuy = fqIndiDayMaxSumBuy;
    }

    public BigDecimal getFqIndiDayMaxSumRedeem() {
        return fqIndiDayMaxSumRedeem;
    }

    public void setFqIndiDayMaxSumRedeem(BigDecimal fqIndiDayMaxSumRedeem) {
        this.fqIndiDayMaxSumRedeem = fqIndiDayMaxSumRedeem;
    }

    public BigDecimal getFqInstDayMaxSumRedeem() {
        return fqInstDayMaxSumRedeem;
    }

    public void setFqInstDayMaxSumRedeem(BigDecimal fqInstDayMaxSumRedeem) {
        this.fqInstDayMaxSumRedeem = fqInstDayMaxSumRedeem;
    }

    public BigDecimal getFqIndiMaxRedeem() {
        return fqIndiMaxRedeem;
    }

    public void setFqIndiMaxRedeem(BigDecimal fqIndiMaxRedeem) {
        this.fqIndiMaxRedeem = fqIndiMaxRedeem;
    }

    public BigDecimal getFqInstMaxRedeem() {
        return fqInstMaxRedeem;
    }

    public void setFqInstMaxRedeem(BigDecimal fqInstMaxRedeem) {
        this.fqInstMaxRedeem = fqInstMaxRedeem;
    }

    public String getFqFundDayIncomeFlag() {
        return fqFundDayIncomeFlag;
    }

    public void setFqFundDayIncomeFlag(String fqFundDayIncomeFlag) {
        this.fqFundDayIncomeFlag = fqFundDayIncomeFlag == null ? null : fqFundDayIncomeFlag.trim();
    }

    public BigDecimal getFqFundDayIncome() {
        return fqFundDayIncome;
    }

    public void setFqFundDayIncome(BigDecimal fqFundDayIncome) {
        this.fqFundDayIncome = fqFundDayIncome;
    }

    public String getFqAllowBreachRedempt() {
        return fqAllowBreachRedempt;
    }

    public void setFqAllowBreachRedempt(String fqAllowBreachRedempt) {
        this.fqAllowBreachRedempt = fqAllowBreachRedempt == null ? null : fqAllowBreachRedempt.trim();
    }

    public String getFqFundType() {
        return fqFundType;
    }

    public void setFqFundType(String fqFundType) {
        this.fqFundType = fqFundType == null ? null : fqFundType.trim();
    }

    public String getFqFundTypeName() {
        return fqFundTypeName;
    }

    public void setFqFundTypeName(String fqFundTypeName) {
        this.fqFundTypeName = fqFundTypeName == null ? null : fqFundTypeName.trim();
    }

    public String getFqRegistrarName() {
        return fqRegistrarName;
    }

    public void setFqRegistrarName(String fqRegistrarName) {
        this.fqRegistrarName = fqRegistrarName == null ? null : fqRegistrarName.trim();
    }

    public String getFqFundManagerName() {
        return fqFundManagerName;
    }

    public void setFqFundManagerName(String fqFundManagerName) {
        this.fqFundManagerName = fqFundManagerName == null ? null : fqFundManagerName.trim();
    }

    public String getFqFundServerTel() {
        return fqFundServerTel;
    }

    public void setFqFundServerTel(String fqFundServerTel) {
        this.fqFundServerTel = fqFundServerTel == null ? null : fqFundServerTel.trim();
    }

    public String getFqDundInternetAddress() {
        return fqDundInternetAddress;
    }

    public void setFqDundInternetAddress(String fqDundInternetAddress) {
        this.fqDundInternetAddress = fqDundInternetAddress == null ? null : fqDundInternetAddress.trim();
    }

    public String getFqProSetupDate() {
		return fqProSetupDate;
	}

	public void setFqProSetupDate(String fqProSetupDate) {
		this.fqProSetupDate = fqProSetupDate;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fqId=").append(fqId);
        sb.append(", fqChannelCode=").append(fqChannelCode);
        sb.append(", fqFundName=").append(fqFundName);
        sb.append(", fqFundCode=").append(fqFundCode);
        sb.append(", fqTotalFundVol=").append(fqTotalFundVol);
        sb.append(", fqFundStatus=").append(fqFundStatus);
        sb.append(", fqNav=").append(fqNav);
        sb.append(", fqUpdateDate=").append(fqUpdateDate);
        sb.append(", fqNetValueType=").append(fqNetValueType);
        sb.append(", fqAccumulativeNav=").append(fqAccumulativeNav);
        sb.append(", fqConvertStatus=").append(fqConvertStatus);
        sb.append(", fqCurrencyType=").append(fqCurrencyType);
        sb.append(", fqInstAppSubsAmnt=").append(fqInstAppSubsAmnt);
        sb.append(", fqMinAmountByInst=").append(fqMinAmountByInst);
        sb.append(", fqIpostartDate=").append(fqIpostartDate);
        sb.append(", fqIpoendDate=").append(fqIpoendDate);
        sb.append(", fqIndiAppSubsAmount=").append(fqIndiAppSubsAmount);
        sb.append(", fqMinSubsAmountByIndi=").append(fqMinSubsAmountByIndi);
        sb.append(", fqUnitSubsAmountByIndi=").append(fqUnitSubsAmountByIndi);
        sb.append(", fqUnitSubsAmountByInst=").append(fqUnitSubsAmountByInst);
        sb.append(", fqMinBidsAmountByIndi=").append(fqMinBidsAmountByIndi);
        sb.append(", fqMinBidsAmountByInst=").append(fqMinBidsAmountByInst);
        sb.append(", fqMinAppBidsAmountByIndi=").append(fqMinAppBidsAmountByIndi);
        sb.append(", fqMinAppBidsAmountByInst=").append(fqMinAppBidsAmountByInst);
        sb.append(", fqCollectFeeType=").append(fqCollectFeeType);
        sb.append(", fqFundIncome=").append(fqFundIncome);
        sb.append(", fqFundIncomeFlag=").append(fqFundIncomeFlag);
        sb.append(", fqYield=").append(fqYield);
        sb.append(", fqYieldFlag=").append(fqYieldFlag);
        sb.append(", fqDataGenerate=").append(fqDataGenerate);
        sb.append(", fqCtime=").append(fqCtime);
        sb.append(", fqUtime=").append(fqUtime);
        sb.append(", fqCuser=").append(fqCuser);
        sb.append(", fqUuser=").append(fqUuser);
        sb.append(", fqInstFayMaxSumBuy=").append(fqInstFayMaxSumBuy);
        sb.append(", fqExchangeDate=").append(fqExchangeDate);
        sb.append(", fqSztSendPath=").append(fqSztSendPath);
        sb.append(", fqCsdcVer=").append(fqCsdcVer);
        sb.append(", fqValidFlag=").append(fqValidFlag);
        sb.append(", fqBatchNo=").append(fqBatchNo);
        sb.append(", fqMaxAmountRaised=").append(fqMaxAmountRaised);
        sb.append(", fqProductType=").append(fqProductType);
        sb.append(", fqTaProductCode=").append(fqTaProductCode);
        sb.append(", fqRemainShares=").append(fqRemainShares);
        sb.append(", fqMinAccountBalance=").append(fqMinAccountBalance);
        sb.append(", fqPeriodicStatus=").append(fqPeriodicStatus);
        sb.append(", fqTransferAgencyStatus=").append(fqTransferAgencyStatus);
        sb.append(", fqFundSize=").append(fqFundSize);
        sb.append(", fqAnnouncFlag=").append(fqAnnouncFlag);
        sb.append(", fqDefDividenDmethod=").append(fqDefDividenDmethod);
        sb.append(", fqInstAppSubsVol=").append(fqInstAppSubsVol);
        sb.append(", fqMinVolByInst=").append(fqMinVolByInst);
        sb.append(", fqCustodianCode=").append(fqCustodianCode);
        sb.append(", fqAmountOfPeriodicSubs=").append(fqAmountOfPeriodicSubs);
        sb.append(", fqDateOfPeriodicSubs=").append(fqDateOfPeriodicSubs);
        sb.append(", fqMaxRedemptionVol=").append(fqMaxRedemptionVol);
        sb.append(", fqFundManagerCode=").append(fqFundManagerCode);
        sb.append(", fqIndiAppSubsVol=").append(fqIndiAppSubsVol);
        sb.append(", fqMinSubsVolByIndi=").append(fqMinSubsVolByIndi);
        sb.append(", fqRegistrarCode=").append(fqRegistrarCode);
        sb.append(", fqFundSponsor=").append(fqFundSponsor);
        sb.append(", fqTradingPrice=").append(fqTradingPrice);
        sb.append(", fqFaceValue=").append(fqFaceValue);
        sb.append(", fqDividentDate=").append(fqDividentDate);
        sb.append(", fqRegistrationDate=").append(fqRegistrationDate);
        sb.append(", fqXrDate=").append(fqXrDate);
        sb.append(", fqMaxSubsVolByIndi=").append(fqMaxSubsVolByIndi);
        sb.append(", fqMaxSubsAmountByIndi=").append(fqMaxSubsAmountByIndi);
        sb.append(", fqMaxSubsVolByInst=").append(fqMaxSubsVolByInst);
        sb.append(", fqMaxSubsAmountByInst=").append(fqMaxSubsAmountByInst);
        sb.append(", fqUnitSubsVolByIndi=").append(fqUnitSubsVolByIndi);
        sb.append(", fqUnitSubsVolByInst=").append(fqUnitSubsVolByInst);
        sb.append(", fqMinRedemptionVol=").append(fqMinRedemptionVol);
        sb.append(", fqMinInterconvertVol=").append(fqMinInterconvertVol);
        sb.append(", fqIssueTypeByIndi=").append(fqIssueTypeByIndi);
        sb.append(", fqIssueTypeByInst=").append(fqIssueTypeByInst);
        sb.append(", fqSubsType=").append(fqSubsType);
        sb.append(", fqNextTradeDate=").append(fqNextTradeDate);
        sb.append(", fqValueLine=").append(fqValueLine);
        sb.append(", fqTotalDivident=").append(fqTotalDivident);
        sb.append(", fqGuaranteedNav=").append(fqGuaranteedNav);
        sb.append(", fqFundYearIncomeRate=").append(fqFundYearIncomeRate);
        sb.append(", fqFundYearIncomeRateFlag=").append(fqFundYearIncomeRateFlag);
        sb.append(", fqIndiMaxPurchase=").append(fqIndiMaxPurchase);
        sb.append(", fqInstMaxPurchase=").append(fqInstMaxPurchase);
        sb.append(", fqIndiDayMaxSumBuy=").append(fqIndiDayMaxSumBuy);
        sb.append(", fqIndiDayMaxSumRedeem=").append(fqIndiDayMaxSumRedeem);
        sb.append(", fqInstDayMaxSumRedeem=").append(fqInstDayMaxSumRedeem);
        sb.append(", fqIndiMaxRedeem=").append(fqIndiMaxRedeem);
        sb.append(", fqInstMaxRedeem=").append(fqInstMaxRedeem);
        sb.append(", fqFundDayIncomeFlag=").append(fqFundDayIncomeFlag);
        sb.append(", fqFundDayIncome=").append(fqFundDayIncome);
        sb.append(", fqAllowBreachRedempt=").append(fqAllowBreachRedempt);
        sb.append(", fqFundType=").append(fqFundType);
        sb.append(", fqFundTypeName=").append(fqFundTypeName);
        sb.append(", fqRegistrarName=").append(fqRegistrarName);
        sb.append(", fqFundManagerName=").append(fqFundManagerName);
        sb.append(", fqFundServerTel=").append(fqFundServerTel);
        sb.append(", fqDundInternetAddress=").append(fqDundInternetAddress);
        sb.append(", fqProSetupDate=").append(fqProSetupDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}