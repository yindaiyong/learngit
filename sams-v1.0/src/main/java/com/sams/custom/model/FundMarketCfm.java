package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundMarketCfm implements Serializable {
    private Long fmId;

    private String fmFundName;

    private String fmFundCode;

    private BigDecimal fmTotalFundVol;

    private String fmFundStatus;

    private BigDecimal fmNAV;

    private String fmUpdateDate;

    private String fmNetValueType;

    private BigDecimal fmAccumulativeNav;

    private String fmConvertStatus;

    private String fmPeriodicStatus;

    private String fmTransferAgencyStatus;

    private BigDecimal fmFundSize;

    private String fmCurrencyType;

    private String fmAnnouncFlag;

    private String fmDefDividenDmethod;

    private BigDecimal fmInstAppSubsAmnt;

    private BigDecimal fmInstAppSubsVol;

    private BigDecimal fmMinAmountByInst;

    private BigDecimal fmMinVolByInst;

    private String fmCustodianCode;

    private BigDecimal fmAmountOfPeriodicSubs;

    private String fmDateOfPeriodicSubs;

    private BigDecimal fmMaxRedemptionVol;

    private BigDecimal fmMinAccountBalance;

    private String fmIpostartDate;

    private String fmIpoendDate;

    private String fmFundManagerCode;

    private BigDecimal fmIndiAppSubsVol;

    private BigDecimal fmIndiAppSubsAmount;

    private BigDecimal fmMinSubsVolByIndi;

    private BigDecimal fmMinSubsAmountByIndi;

    private String fmRegistrarCode;

    private String fmFundSponsor;

    private BigDecimal fmTradingPrice;

    private BigDecimal fmFaceValue;

    private String fmDividentDate;

    private String fmRegistrationDate;

    private String fmXrDate;

    private BigDecimal fmMaxSubsVolByIndi;

    private BigDecimal fmMaxSubsAmountByIndi;

    private BigDecimal fmMaxSubsVolByInst;

    private BigDecimal fmMaxSubsAmountByInst;

    private BigDecimal fmUnitSubsVolByIndi;

    private BigDecimal fmUnitSubsAmountByIndi;

    private BigDecimal fmUnitSubsVolByInst;

    private BigDecimal fmUnitSubsAmountByInst;

    private BigDecimal fmMinBidsAmountByIndi;

    private BigDecimal fmMinBidsAmountByInst;

    private BigDecimal fmMinAppBidsAmountByIndi;

    private BigDecimal fmMinAppBidsAmountByInst;

    private BigDecimal fmMinRedemptionVol;

    private BigDecimal fmMinInterconvertVol;

    private String fmIssueTypeByIndi;

    private String fmIssueTypeByInst;

    private String fmSubsType;

    private String fmCollectFeeType;

    private String fmNextTradeDate;

    private BigDecimal fmValueLine;

    private BigDecimal fmTotalDivident;

    private BigDecimal fmFundIncome;

    private String fmFundIncomeFlag;

    private BigDecimal fmYield;

    private String fmYieldFlag;

    private BigDecimal fmGuaranteedNav;

    private BigDecimal fmFundYearIncomeRate;

    private String fmFundYearIncomeRateFlag;

    private BigDecimal fmIndiMaxPurchase;

    private BigDecimal fmInstMaxPurchase;

    private BigDecimal fmIndiDayMaxSumBuy;

    private BigDecimal fmInstDayMaxSumBuy;

    private BigDecimal fmIndiDayMaxSumRedeem;

    private BigDecimal fmInstDayMaxSumRedeem;

    private BigDecimal fmIndiMaxRedeem;

    private BigDecimal fmInstMaxRedeem;

    private String fmFundDayIncomeFlag;

    private BigDecimal fmFundDayIncome;

    private String fmAllowBreachRedempt;

    private String fmFundType;

    private String fmFundTypeName;

    private String fmRegistrarName;

    private String fmFundManagerName;

    private String fmFundServerTel;

    private String fmDundInternetAddress;

    private String fmBusinessDate;

    private String fmSendStatus;

    private String fmGenerateStatus;

    private Date fmSendFileTime;

    private Date fmGenerateFileTime;

    private Date fmDataGenerate;

    private Date fmCtime;

    private Date fmUtime;

    private String fmCuser;

    private String fmUuser;

    private String fmChannelCode;
    
    private String fmMaxAmountRaised;
    
    private String fqRemainShares;
    
    private String fqProductType;
    
    private String fqTaProductCode;


    private static final long serialVersionUID = 1L;

    public Long getFmId() {
        return fmId;
    }

    public void setFmId(Long fmId) {
        this.fmId = fmId;
    }

    public String getFmFundName() {
        return fmFundName;
    }

    public void setFmFundName(String fmFundName) {
        this.fmFundName = fmFundName == null ? null : fmFundName.trim();
    }

    public String getFmFundCode() {
        return fmFundCode;
    }

    public void setFmFundCode(String fmFundCode) {
        this.fmFundCode = fmFundCode == null ? null : fmFundCode.trim();
    }

    public BigDecimal getFmTotalFundVol() {
        return fmTotalFundVol;
    }

    public void setFmTotalFundVol(BigDecimal fmTotalFundVol) {
        this.fmTotalFundVol = fmTotalFundVol;
    }

    public String getFmFundStatus() {
        return fmFundStatus;
    }

    public void setFmFundStatus(String fmFundStatus) {
        this.fmFundStatus = fmFundStatus == null ? null : fmFundStatus.trim();
    }

    public BigDecimal getfmNAV() {
        return fmNAV;
    }

    public void setfmNAV(BigDecimal fmNAV) {
        this.fmNAV = fmNAV;
    }

    public String getFmUpdateDate() {
        return fmUpdateDate;
    }

    public void setFmUpdateDate(String fmUpdateDate) {
        this.fmUpdateDate = fmUpdateDate == null ? null : fmUpdateDate.trim();
    }

    public String getFmNetValueType() {
        return fmNetValueType;
    }

    public void setFmNetValueType(String fmNetValueType) {
        this.fmNetValueType = fmNetValueType == null ? null : fmNetValueType.trim();
    }

    public BigDecimal getFmAccumulativeNav() {
        return fmAccumulativeNav;
    }

    public void setFmAccumulativeNav(BigDecimal fmAccumulativeNav) {
        this.fmAccumulativeNav = fmAccumulativeNav;
    }

    public String getFmConvertStatus() {
        return fmConvertStatus;
    }

    public void setFmConvertStatus(String fmConvertStatus) {
        this.fmConvertStatus = fmConvertStatus == null ? null : fmConvertStatus.trim();
    }

    public String getFmPeriodicStatus() {
        return fmPeriodicStatus;
    }

    public void setFmPeriodicStatus(String fmPeriodicStatus) {
        this.fmPeriodicStatus = fmPeriodicStatus == null ? null : fmPeriodicStatus.trim();
    }

    public String getFmTransferAgencyStatus() {
        return fmTransferAgencyStatus;
    }

    public void setFmTransferAgencyStatus(String fmTransferAgencyStatus) {
        this.fmTransferAgencyStatus = fmTransferAgencyStatus == null ? null : fmTransferAgencyStatus.trim();
    }

    public BigDecimal getFmFundSize() {
        return fmFundSize;
    }

    public void setFmFundSize(BigDecimal fmFundSize) {
        this.fmFundSize = fmFundSize;
    }

    public String getFmCurrencyType() {
        return fmCurrencyType;
    }

    public void setFmCurrencyType(String fmCurrencyType) {
        this.fmCurrencyType = fmCurrencyType == null ? null : fmCurrencyType.trim();
    }

    public String getFmAnnouncFlag() {
        return fmAnnouncFlag;
    }

    public void setFmAnnouncFlag(String fmAnnouncFlag) {
        this.fmAnnouncFlag = fmAnnouncFlag == null ? null : fmAnnouncFlag.trim();
    }

    public String getFmDefDividenDmethod() {
        return fmDefDividenDmethod;
    }

    public void setFmDefDividenDmethod(String fmDefDividenDmethod) {
        this.fmDefDividenDmethod = fmDefDividenDmethod == null ? null : fmDefDividenDmethod.trim();
    }

    public BigDecimal getFmInstAppSubsAmnt() {
        return fmInstAppSubsAmnt;
    }

    public void setFmInstAppSubsAmnt(BigDecimal fmInstAppSubsAmnt) {
        this.fmInstAppSubsAmnt = fmInstAppSubsAmnt;
    }

    public BigDecimal getFmInstAppSubsVol() {
        return fmInstAppSubsVol;
    }

    public void setFmInstAppSubsVol(BigDecimal fmInstAppSubsVol) {
        this.fmInstAppSubsVol = fmInstAppSubsVol;
    }

    public BigDecimal getFmMinAmountByInst() {
        return fmMinAmountByInst;
    }

    public void setFmMinAmountByInst(BigDecimal fmMinAmountByInst) {
        this.fmMinAmountByInst = fmMinAmountByInst;
    }

    public BigDecimal getFmMinVolByInst() {
        return fmMinVolByInst;
    }

    public void setFmMinVolByInst(BigDecimal fmMinVolByInst) {
        this.fmMinVolByInst = fmMinVolByInst;
    }

    public String getFmCustodianCode() {
        return fmCustodianCode;
    }

    public void setFmCustodianCode(String fmCustodianCode) {
        this.fmCustodianCode = fmCustodianCode == null ? null : fmCustodianCode.trim();
    }

    public BigDecimal getFmAmountOfPeriodicSubs() {
        return fmAmountOfPeriodicSubs;
    }

    public void setFmAmountOfPeriodicSubs(BigDecimal fmAmountOfPeriodicSubs) {
        this.fmAmountOfPeriodicSubs = fmAmountOfPeriodicSubs;
    }

    public String getFmDateOfPeriodicSubs() {
        return fmDateOfPeriodicSubs;
    }

    public void setFmDateOfPeriodicSubs(String fmDateOfPeriodicSubs) {
        this.fmDateOfPeriodicSubs = fmDateOfPeriodicSubs == null ? null : fmDateOfPeriodicSubs.trim();
    }

    public BigDecimal getFmMaxRedemptionVol() {
        return fmMaxRedemptionVol;
    }

    public void setFmMaxRedemptionVol(BigDecimal fmMaxRedemptionVol) {
        this.fmMaxRedemptionVol = fmMaxRedemptionVol;
    }

    public BigDecimal getFmMinAccountBalance() {
        return fmMinAccountBalance;
    }

    public void setFmMinAccountBalance(BigDecimal fmMinAccountBalance) {
        this.fmMinAccountBalance = fmMinAccountBalance;
    }

    public String getFmIpostartDate() {
        return fmIpostartDate;
    }

    public void setFmIpostartDate(String fmIpostartDate) {
        this.fmIpostartDate = fmIpostartDate == null ? null : fmIpostartDate.trim();
    }

    public String getFmIpoendDate() {
        return fmIpoendDate;
    }

    public void setFmIpoendDate(String fmIpoendDate) {
        this.fmIpoendDate = fmIpoendDate == null ? null : fmIpoendDate.trim();
    }

    public String getFmFundManagerCode() {
        return fmFundManagerCode;
    }

    public void setFmFundManagerCode(String fmFundManagerCode) {
        this.fmFundManagerCode = fmFundManagerCode == null ? null : fmFundManagerCode.trim();
    }

    public BigDecimal getFmIndiAppSubsVol() {
        return fmIndiAppSubsVol;
    }

    public void setFmIndiAppSubsVol(BigDecimal fmIndiAppSubsVol) {
        this.fmIndiAppSubsVol = fmIndiAppSubsVol;
    }

    public BigDecimal getFmIndiAppSubsAmount() {
        return fmIndiAppSubsAmount;
    }

    public void setFmIndiAppSubsAmount(BigDecimal fmIndiAppSubsAmount) {
        this.fmIndiAppSubsAmount = fmIndiAppSubsAmount;
    }

    public BigDecimal getFmMinSubsVolByIndi() {
        return fmMinSubsVolByIndi;
    }

    public void setFmMinSubsVolByIndi(BigDecimal fmMinSubsVolByIndi) {
        this.fmMinSubsVolByIndi = fmMinSubsVolByIndi;
    }

    public BigDecimal getFmMinSubsAmountByIndi() {
        return fmMinSubsAmountByIndi;
    }

    public void setFmMinSubsAmountByIndi(BigDecimal fmMinSubsAmountByIndi) {
        this.fmMinSubsAmountByIndi = fmMinSubsAmountByIndi;
    }

    public String getFmRegistrarCode() {
        return fmRegistrarCode;
    }

    public void setFmRegistrarCode(String fmRegistrarCode) {
        this.fmRegistrarCode = fmRegistrarCode == null ? null : fmRegistrarCode.trim();
    }

    public String getFmFundSponsor() {
        return fmFundSponsor;
    }

    public void setFmFundSponsor(String fmFundSponsor) {
        this.fmFundSponsor = fmFundSponsor == null ? null : fmFundSponsor.trim();
    }

    public BigDecimal getFmTradingPrice() {
        return fmTradingPrice;
    }

    public void setFmTradingPrice(BigDecimal fmTradingPrice) {
        this.fmTradingPrice = fmTradingPrice;
    }

    public BigDecimal getFmFaceValue() {
        return fmFaceValue;
    }

    public void setFmFaceValue(BigDecimal fmFaceValue) {
        this.fmFaceValue = fmFaceValue;
    }

    public String getFmDividentDate() {
        return fmDividentDate;
    }

    public void setFmDividentDate(String fmDividentDate) {
        this.fmDividentDate = fmDividentDate == null ? null : fmDividentDate.trim();
    }

    public String getFmRegistrationDate() {
        return fmRegistrationDate;
    }

    public void setFmRegistrationDate(String fmRegistrationDate) {
        this.fmRegistrationDate = fmRegistrationDate == null ? null : fmRegistrationDate.trim();
    }

    public String getFmXrDate() {
        return fmXrDate;
    }

    public void setFmXrDate(String fmXrDate) {
        this.fmXrDate = fmXrDate == null ? null : fmXrDate.trim();
    }

    public BigDecimal getFmMaxSubsVolByIndi() {
        return fmMaxSubsVolByIndi;
    }

    public void setFmMaxSubsVolByIndi(BigDecimal fmMaxSubsVolByIndi) {
        this.fmMaxSubsVolByIndi = fmMaxSubsVolByIndi;
    }

    public BigDecimal getFmMaxSubsAmountByIndi() {
        return fmMaxSubsAmountByIndi;
    }

    public void setFmMaxSubsAmountByIndi(BigDecimal fmMaxSubsAmountByIndi) {
        this.fmMaxSubsAmountByIndi = fmMaxSubsAmountByIndi;
    }

    public BigDecimal getFmMaxSubsVolByInst() {
        return fmMaxSubsVolByInst;
    }

    public void setFmMaxSubsVolByInst(BigDecimal fmMaxSubsVolByInst) {
        this.fmMaxSubsVolByInst = fmMaxSubsVolByInst;
    }

    public BigDecimal getFmMaxSubsAmountByInst() {
        return fmMaxSubsAmountByInst;
    }

    public void setFmMaxSubsAmountByInst(BigDecimal fmMaxSubsAmountByInst) {
        this.fmMaxSubsAmountByInst = fmMaxSubsAmountByInst;
    }

    public BigDecimal getFmUnitSubsVolByIndi() {
        return fmUnitSubsVolByIndi;
    }

    public void setFmUnitSubsVolByIndi(BigDecimal fmUnitSubsVolByIndi) {
        this.fmUnitSubsVolByIndi = fmUnitSubsVolByIndi;
    }

    public BigDecimal getFmUnitSubsAmountByIndi() {
        return fmUnitSubsAmountByIndi;
    }

    public void setFmUnitSubsAmountByIndi(BigDecimal fmUnitSubsAmountByIndi) {
        this.fmUnitSubsAmountByIndi = fmUnitSubsAmountByIndi;
    }

    public BigDecimal getFmUnitSubsVolByInst() {
        return fmUnitSubsVolByInst;
    }

    public void setFmUnitSubsVolByInst(BigDecimal fmUnitSubsVolByInst) {
        this.fmUnitSubsVolByInst = fmUnitSubsVolByInst;
    }

    public BigDecimal getFmUnitSubsAmountByInst() {
        return fmUnitSubsAmountByInst;
    }

    public void setFmUnitSubsAmountByInst(BigDecimal fmUnitSubsAmountByInst) {
        this.fmUnitSubsAmountByInst = fmUnitSubsAmountByInst;
    }

    public BigDecimal getFmMinBidsAmountByIndi() {
        return fmMinBidsAmountByIndi;
    }

    public void setFmMinBidsAmountByIndi(BigDecimal fmMinBidsAmountByIndi) {
        this.fmMinBidsAmountByIndi = fmMinBidsAmountByIndi;
    }

    public BigDecimal getFmMinBidsAmountByInst() {
        return fmMinBidsAmountByInst;
    }

    public void setFmMinBidsAmountByInst(BigDecimal fmMinBidsAmountByInst) {
        this.fmMinBidsAmountByInst = fmMinBidsAmountByInst;
    }

    public BigDecimal getFmMinAppBidsAmountByIndi() {
        return fmMinAppBidsAmountByIndi;
    }

    public void setFmMinAppBidsAmountByIndi(BigDecimal fmMinAppBidsAmountByIndi) {
        this.fmMinAppBidsAmountByIndi = fmMinAppBidsAmountByIndi;
    }

    public BigDecimal getFmMinAppBidsAmountByInst() {
        return fmMinAppBidsAmountByInst;
    }

    public void setFmMinAppBidsAmountByInst(BigDecimal fmMinAppBidsAmountByInst) {
        this.fmMinAppBidsAmountByInst = fmMinAppBidsAmountByInst;
    }

    public BigDecimal getFmMinRedemptionVol() {
        return fmMinRedemptionVol;
    }

    public void setFmMinRedemptionVol(BigDecimal fmMinRedemptionVol) {
        this.fmMinRedemptionVol = fmMinRedemptionVol;
    }

    public BigDecimal getFmMinInterconvertVol() {
        return fmMinInterconvertVol;
    }

    public void setFmMinInterconvertVol(BigDecimal fmMinInterconvertVol) {
        this.fmMinInterconvertVol = fmMinInterconvertVol;
    }

    public String getFmIssueTypeByIndi() {
        return fmIssueTypeByIndi;
    }

    public void setFmIssueTypeByIndi(String fmIssueTypeByIndi) {
        this.fmIssueTypeByIndi = fmIssueTypeByIndi == null ? null : fmIssueTypeByIndi.trim();
    }

    public String getFmIssueTypeByInst() {
        return fmIssueTypeByInst;
    }

    public void setFmIssueTypeByInst(String fmIssueTypeByInst) {
        this.fmIssueTypeByInst = fmIssueTypeByInst == null ? null : fmIssueTypeByInst.trim();
    }

    public String getFmSubsType() {
        return fmSubsType;
    }

    public void setFmSubsType(String fmSubsType) {
        this.fmSubsType = fmSubsType == null ? null : fmSubsType.trim();
    }

    public String getFmCollectFeeType() {
        return fmCollectFeeType;
    }

    public void setFmCollectFeeType(String fmCollectFeeType) {
        this.fmCollectFeeType = fmCollectFeeType == null ? null : fmCollectFeeType.trim();
    }

    public String getFmNextTradeDate() {
        return fmNextTradeDate;
    }

    public void setFmNextTradeDate(String fmNextTradeDate) {
        this.fmNextTradeDate = fmNextTradeDate == null ? null : fmNextTradeDate.trim();
    }

    public BigDecimal getFmValueLine() {
        return fmValueLine;
    }

    public void setFmValueLine(BigDecimal fmValueLine) {
        this.fmValueLine = fmValueLine;
    }

    public BigDecimal getFmTotalDivident() {
        return fmTotalDivident;
    }

    public void setFmTotalDivident(BigDecimal fmTotalDivident) {
        this.fmTotalDivident = fmTotalDivident;
    }

    public BigDecimal getFmFundIncome() {
        return fmFundIncome;
    }

    public void setFmFundIncome(BigDecimal fmFundIncome) {
        this.fmFundIncome = fmFundIncome;
    }

    public String getFmFundIncomeFlag() {
        return fmFundIncomeFlag;
    }

    public void setFmFundIncomeFlag(String fmFundIncomeFlag) {
        this.fmFundIncomeFlag = fmFundIncomeFlag == null ? null : fmFundIncomeFlag.trim();
    }

    public BigDecimal getFmYield() {
        return fmYield;
    }

    public void setFmYield(BigDecimal fmYield) {
        this.fmYield = fmYield;
    }

    public String getFmYieldFlag() {
        return fmYieldFlag;
    }

    public void setFmYieldFlag(String fmYieldFlag) {
        this.fmYieldFlag = fmYieldFlag == null ? null : fmYieldFlag.trim();
    }

    public BigDecimal getFmGuaranteedNav() {
        return fmGuaranteedNav;
    }

    public void setFmGuaranteedNav(BigDecimal fmGuaranteedNav) {
        this.fmGuaranteedNav = fmGuaranteedNav;
    }

    public BigDecimal getFmFundYearIncomeRate() {
        return fmFundYearIncomeRate;
    }

    public void setFmFundYearIncomeRate(BigDecimal fmFundYearIncomeRate) {
        this.fmFundYearIncomeRate = fmFundYearIncomeRate;
    }

    public String getFmFundYearIncomeRateFlag() {
        return fmFundYearIncomeRateFlag;
    }

    public void setFmFundYearIncomeRateFlag(String fmFundYearIncomeRateFlag) {
        this.fmFundYearIncomeRateFlag = fmFundYearIncomeRateFlag == null ? null : fmFundYearIncomeRateFlag.trim();
    }

    public BigDecimal getFmIndiMaxPurchase() {
        return fmIndiMaxPurchase;
    }

    public void setFmIndiMaxPurchase(BigDecimal fmIndiMaxPurchase) {
        this.fmIndiMaxPurchase = fmIndiMaxPurchase;
    }

    public BigDecimal getFmInstMaxPurchase() {
        return fmInstMaxPurchase;
    }

    public void setFmInstMaxPurchase(BigDecimal fmInstMaxPurchase) {
        this.fmInstMaxPurchase = fmInstMaxPurchase;
    }

    public BigDecimal getFmIndiDayMaxSumBuy() {
        return fmIndiDayMaxSumBuy;
    }

    public void setFmIndiDayMaxSumBuy(BigDecimal fmIndiDayMaxSumBuy) {
        this.fmIndiDayMaxSumBuy = fmIndiDayMaxSumBuy;
    }

    public BigDecimal getFmInstDayMaxSumBuy() {
        return fmInstDayMaxSumBuy;
    }

    public void setFmInstDayMaxSumBuy(BigDecimal fmInstDayMaxSumBuy) {
        this.fmInstDayMaxSumBuy = fmInstDayMaxSumBuy;
    }

    public BigDecimal getFmIndiDayMaxSumRedeem() {
        return fmIndiDayMaxSumRedeem;
    }

    public void setFmIndiDayMaxSumRedeem(BigDecimal fmIndiDayMaxSumRedeem) {
        this.fmIndiDayMaxSumRedeem = fmIndiDayMaxSumRedeem;
    }

    public BigDecimal getFmInstDayMaxSumRedeem() {
        return fmInstDayMaxSumRedeem;
    }

    public void setFmInstDayMaxSumRedeem(BigDecimal fmInstDayMaxSumRedeem) {
        this.fmInstDayMaxSumRedeem = fmInstDayMaxSumRedeem;
    }

    public BigDecimal getFmIndiMaxRedeem() {
        return fmIndiMaxRedeem;
    }

    public void setFmIndiMaxRedeem(BigDecimal fmIndiMaxRedeem) {
        this.fmIndiMaxRedeem = fmIndiMaxRedeem;
    }

    public BigDecimal getFmInstMaxRedeem() {
        return fmInstMaxRedeem;
    }

    public void setFmInstMaxRedeem(BigDecimal fmInstMaxRedeem) {
        this.fmInstMaxRedeem = fmInstMaxRedeem;
    }

    public String getFmFundDayIncomeFlag() {
        return fmFundDayIncomeFlag;
    }

    public void setFmFundDayIncomeFlag(String fmFundDayIncomeFlag) {
        this.fmFundDayIncomeFlag = fmFundDayIncomeFlag == null ? null : fmFundDayIncomeFlag.trim();
    }

    public BigDecimal getFmFundDayIncome() {
        return fmFundDayIncome;
    }

    public void setFmFundDayIncome(BigDecimal fmFundDayIncome) {
        this.fmFundDayIncome = fmFundDayIncome;
    }

    public String getFmAllowBreachRedempt() {
        return fmAllowBreachRedempt;
    }

    public void setFmAllowBreachRedempt(String fmAllowBreachRedempt) {
        this.fmAllowBreachRedempt = fmAllowBreachRedempt == null ? null : fmAllowBreachRedempt.trim();
    }

    public String getFmFundType() {
        return fmFundType;
    }

    public void setFmFundType(String fmFundType) {
        this.fmFundType = fmFundType == null ? null : fmFundType.trim();
    }

    public String getFmFundTypeName() {
        return fmFundTypeName;
    }

    public void setFmFundTypeName(String fmFundTypeName) {
        this.fmFundTypeName = fmFundTypeName == null ? null : fmFundTypeName.trim();
    }

    public String getFmRegistrarName() {
        return fmRegistrarName;
    }

    public void setFmRegistrarName(String fmRegistrarName) {
        this.fmRegistrarName = fmRegistrarName == null ? null : fmRegistrarName.trim();
    }

    public String getFmFundManagerName() {
        return fmFundManagerName;
    }

    public void setFmFundManagerName(String fmFundManagerName) {
        this.fmFundManagerName = fmFundManagerName == null ? null : fmFundManagerName.trim();
    }

    public String getFmFundServerTel() {
        return fmFundServerTel;
    }

    public void setFmFundServerTel(String fmFundServerTel) {
        this.fmFundServerTel = fmFundServerTel == null ? null : fmFundServerTel.trim();
    }

    public String getFmDundInternetAddress() {
        return fmDundInternetAddress;
    }

    public void setFmDundInternetAddress(String fmDundInternetAddress) {
        this.fmDundInternetAddress = fmDundInternetAddress == null ? null : fmDundInternetAddress.trim();
    }

    public String getFmBusinessDate() {
        return fmBusinessDate;
    }

    public void setFmBusinessDate(String fmBusinessDate) {
        this.fmBusinessDate = fmBusinessDate == null ? null : fmBusinessDate.trim();
    }

    public String getFmSendStatus() {
        return fmSendStatus;
    }

    public void setFmSendStatus(String fmSendStatus) {
        this.fmSendStatus = fmSendStatus == null ? null : fmSendStatus.trim();
    }

    public String getFmGenerateStatus() {
        return fmGenerateStatus;
    }

    public void setFmGenerateStatus(String fmGenerateStatus) {
        this.fmGenerateStatus = fmGenerateStatus == null ? null : fmGenerateStatus.trim();
    }

    public Date getFmSendFileTime() {
        return fmSendFileTime;
    }

    public void setFmSendFileTime(Date fmSendFileTime) {
        this.fmSendFileTime = fmSendFileTime;
    }

    public Date getFmGenerateFileTime() {
        return fmGenerateFileTime;
    }

    public void setFmGenerateFileTime(Date fmGenerateFileTime) {
        this.fmGenerateFileTime = fmGenerateFileTime;
    }

    public Date getFmDataGenerate() {
        return fmDataGenerate;
    }

    public void setFmDataGenerate(Date fmDataGenerate) {
        this.fmDataGenerate = fmDataGenerate;
    }

    public Date getFmCtime() {
        return fmCtime;
    }

    public void setFmCtime(Date fmCtime) {
        this.fmCtime = fmCtime;
    }

    public Date getFmUtime() {
        return fmUtime;
    }

    public void setFmUtime(Date fmUtime) {
        this.fmUtime = fmUtime;
    }

    public String getFmCuser() {
        return fmCuser;
    }

    public void setFmCuser(String fmCuser) {
        this.fmCuser = fmCuser == null ? null : fmCuser.trim();
    }

    public String getFmUuser() {
        return fmUuser;
    }

    public void setFmUuser(String fmUuser) {
        this.fmUuser = fmUuser == null ? null : fmUuser.trim();
    }

    public String getFmChannelCode() {
        return fmChannelCode;
    }

    public void setFmChannelCode(String fmChannelCode) {
        this.fmChannelCode = fmChannelCode == null ? null : fmChannelCode.trim();
    }

	public String getFmMaxAmountRaised() {
		return fmMaxAmountRaised;
	}

	public void setFmMaxAmountRaised(String fmMaxAmountRaised) {
		this.fmMaxAmountRaised = fmMaxAmountRaised;
	}

	public String getFqRemainShares() {
		return fqRemainShares;
	}

	public void setFqRemainShares(String fqRemainShares) {
		this.fqRemainShares = fqRemainShares;
	}

	public String getFqProductType() {
		return fqProductType;
	}

	public void setFqProductType(String fqProductType) {
		this.fqProductType = fqProductType;
	}

	public String getFqTaProductCode() {
		return fqTaProductCode;
	}

	public void setFqTaProductCode(String fqTaProductCode) {
		this.fqTaProductCode = fqTaProductCode;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fmId=").append(fmId);
        sb.append(", fmFundName=").append(fmFundName);
        sb.append(", fmFundCode=").append(fmFundCode);
        sb.append(", fmTotalFundVol=").append(fmTotalFundVol);
        sb.append(", fmFundStatus=").append(fmFundStatus);
        sb.append(", fmNAV=").append(fmNAV);
        sb.append(", fmUpdateDate=").append(fmUpdateDate);
        sb.append(", fmNetValueType=").append(fmNetValueType);
        sb.append(", fmAccumulativeNav=").append(fmAccumulativeNav);
        sb.append(", fmConvertStatus=").append(fmConvertStatus);
        sb.append(", fmPeriodicStatus=").append(fmPeriodicStatus);
        sb.append(", fmTransferAgencyStatus=").append(fmTransferAgencyStatus);
        sb.append(", fmFundSize=").append(fmFundSize);
        sb.append(", fmCurrencyType=").append(fmCurrencyType);
        sb.append(", fmAnnouncFlag=").append(fmAnnouncFlag);
        sb.append(", fmDefDividenDmethod=").append(fmDefDividenDmethod);
        sb.append(", fmInstAppSubsAmnt=").append(fmInstAppSubsAmnt);
        sb.append(", fmInstAppSubsVol=").append(fmInstAppSubsVol);
        sb.append(", fmMinAmountByInst=").append(fmMinAmountByInst);
        sb.append(", fmMinVolByInst=").append(fmMinVolByInst);
        sb.append(", fmCustodianCode=").append(fmCustodianCode);
        sb.append(", fmAmountOfPeriodicSubs=").append(fmAmountOfPeriodicSubs);
        sb.append(", fmDateOfPeriodicSubs=").append(fmDateOfPeriodicSubs);
        sb.append(", fmMaxRedemptionVol=").append(fmMaxRedemptionVol);
        sb.append(", fmMinAccountBalance=").append(fmMinAccountBalance);
        sb.append(", fmIpostartDate=").append(fmIpostartDate);
        sb.append(", fmIpoendDate=").append(fmIpoendDate);
        sb.append(", fmFundManagerCode=").append(fmFundManagerCode);
        sb.append(", fmIndiAppSubsVol=").append(fmIndiAppSubsVol);
        sb.append(", fmIndiAppSubsAmount=").append(fmIndiAppSubsAmount);
        sb.append(", fmMinSubsVolByIndi=").append(fmMinSubsVolByIndi);
        sb.append(", fmMinSubsAmountByIndi=").append(fmMinSubsAmountByIndi);
        sb.append(", fmRegistrarCode=").append(fmRegistrarCode);
        sb.append(", fmFundSponsor=").append(fmFundSponsor);
        sb.append(", fmTradingPrice=").append(fmTradingPrice);
        sb.append(", fmFaceValue=").append(fmFaceValue);
        sb.append(", fmDividentDate=").append(fmDividentDate);
        sb.append(", fmRegistrationDate=").append(fmRegistrationDate);
        sb.append(", fmXrDate=").append(fmXrDate);
        sb.append(", fmMaxSubsVolByIndi=").append(fmMaxSubsVolByIndi);
        sb.append(", fmMaxSubsAmountByIndi=").append(fmMaxSubsAmountByIndi);
        sb.append(", fmMaxSubsVolByInst=").append(fmMaxSubsVolByInst);
        sb.append(", fmMaxSubsAmountByInst=").append(fmMaxSubsAmountByInst);
        sb.append(", fmUnitSubsVolByIndi=").append(fmUnitSubsVolByIndi);
        sb.append(", fmUnitSubsAmountByIndi=").append(fmUnitSubsAmountByIndi);
        sb.append(", fmUnitSubsVolByInst=").append(fmUnitSubsVolByInst);
        sb.append(", fmUnitSubsAmountByInst=").append(fmUnitSubsAmountByInst);
        sb.append(", fmMinBidsAmountByIndi=").append(fmMinBidsAmountByIndi);
        sb.append(", fmMinBidsAmountByInst=").append(fmMinBidsAmountByInst);
        sb.append(", fmMinAppBidsAmountByIndi=").append(fmMinAppBidsAmountByIndi);
        sb.append(", fmMinAppBidsAmountByInst=").append(fmMinAppBidsAmountByInst);
        sb.append(", fmMinRedemptionVol=").append(fmMinRedemptionVol);
        sb.append(", fmMinInterconvertVol=").append(fmMinInterconvertVol);
        sb.append(", fmIssueTypeByIndi=").append(fmIssueTypeByIndi);
        sb.append(", fmIssueTypeByInst=").append(fmIssueTypeByInst);
        sb.append(", fmSubsType=").append(fmSubsType);
        sb.append(", fmCollectFeeType=").append(fmCollectFeeType);
        sb.append(", fmNextTradeDate=").append(fmNextTradeDate);
        sb.append(", fmValueLine=").append(fmValueLine);
        sb.append(", fmTotalDivident=").append(fmTotalDivident);
        sb.append(", fmFundIncome=").append(fmFundIncome);
        sb.append(", fmFundIncomeFlag=").append(fmFundIncomeFlag);
        sb.append(", fmYield=").append(fmYield);
        sb.append(", fmYieldFlag=").append(fmYieldFlag);
        sb.append(", fmGuaranteedNav=").append(fmGuaranteedNav);
        sb.append(", fmFundYearIncomeRate=").append(fmFundYearIncomeRate);
        sb.append(", fmFundYearIncomeRateFlag=").append(fmFundYearIncomeRateFlag);
        sb.append(", fmIndiMaxPurchase=").append(fmIndiMaxPurchase);
        sb.append(", fmInstMaxPurchase=").append(fmInstMaxPurchase);
        sb.append(", fmIndiDayMaxSumBuy=").append(fmIndiDayMaxSumBuy);
        sb.append(", fmInstDayMaxSumBuy=").append(fmInstDayMaxSumBuy);
        sb.append(", fmIndiDayMaxSumRedeem=").append(fmIndiDayMaxSumRedeem);
        sb.append(", fmInstDayMaxSumRedeem=").append(fmInstDayMaxSumRedeem);
        sb.append(", fmIndiMaxRedeem=").append(fmIndiMaxRedeem);
        sb.append(", fmInstMaxRedeem=").append(fmInstMaxRedeem);
        sb.append(", fmFundDayIncomeFlag=").append(fmFundDayIncomeFlag);
        sb.append(", fmFundDayIncome=").append(fmFundDayIncome);
        sb.append(", fmAllowBreachRedempt=").append(fmAllowBreachRedempt);
        sb.append(", fmFundType=").append(fmFundType);
        sb.append(", fmFundTypeName=").append(fmFundTypeName);
        sb.append(", fmRegistrarName=").append(fmRegistrarName);
        sb.append(", fmFundManagerName=").append(fmFundManagerName);
        sb.append(", fmFundServerTel=").append(fmFundServerTel);
        sb.append(", fmDundInternetAddress=").append(fmDundInternetAddress);
        sb.append(", fmBusinessDate=").append(fmBusinessDate);
        sb.append(", fmSendStatus=").append(fmSendStatus);
        sb.append(", fmGenerateStatus=").append(fmGenerateStatus);
        sb.append(", fmSendFileTime=").append(fmSendFileTime);
        sb.append(", fmGenerateFileTime=").append(fmGenerateFileTime);
        sb.append(", fmDataGenerate=").append(fmDataGenerate);
        sb.append(", fmCtime=").append(fmCtime);
        sb.append(", fmUtime=").append(fmUtime);
        sb.append(", fmCuser=").append(fmCuser);
        sb.append(", fmUuser=").append(fmUuser);
        sb.append(", fmChannelCode=").append(fmChannelCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}