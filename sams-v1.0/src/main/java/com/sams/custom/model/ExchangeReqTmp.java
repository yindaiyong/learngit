package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExchangeReqTmp implements Serializable {
    private Long ertId;

    private String ertChannelCode;
    
    private String ertTransDate;

    private String ertAppSheetSerialNo;

    private String ertFundCode;

    private String ertLargeRedemptionFlag;

    private String ertTransactionDate;

    private String ertTransactionTime;

    private String ertTransactionAccountId;

    private String ertDistributorCode;

    private BigDecimal ertApplicationVol;

    private BigDecimal ertApplicationAmount;

    private String ertBusinessCode;

    private String ertTaAccountId;

    private BigDecimal ertDiscountRateOfComm;

    private String ertDepositAcct;

    private String ertRegionCode;

    private String ertCurrencyType;

    private String ertBranchCode;

    private String ertOriginalAppSheetNo;

    private String ertOriginalSubsDate;

    private String ertIndividualOrInstitution;

    private BigDecimal ertValidPeriod;

    private BigDecimal ertDaysRedemptionInAdvance;

    private String ertRedemptionDateInAdvance;

    private String ertOriginalSerialNo;

    private String ertDateOfPeriodicSubs;

    private String ertTaSerialNo;

    private BigDecimal ertTermOfPeriodicSubs;

    private String ertFutureBuyDate;

    private String ertTargetDistributorCode;

    private BigDecimal ertCharge;

    private String ertTargetBranchCode;

    private String ertTargetTransactAcctCode;

    private String ertTargetRegionCode;

    private BigDecimal ertDividendRatio;

    private String ertSpecification;

    private String ertCodeOfTargetFund;

    private BigDecimal ertTotalBackendLoad;

    private String ertShareClass;

    private String ertOriginalCfmDate;

    private String ertDetailFlag;

    private String ertOriginalAppDate;

    private String ertDefDividendMethod;

    private String ertFrozenCause;

    private String ertFreezingDeadline;

    private String ertVarietyCodePeriodicSub;

    private String ertSerialNoPeriodicSub;

    private String ertRationType;

    private String ertTargetTaAccountId;

    private String ertTargetRegistrarCode;

    private String ertNetNo;

    private String ertCustomerNo;

    private String ertTargetShareType;

    private String ertRationProtocolNo;

    private String ertBeginDatePeriodicSubs;

    private String ertEndDateOfPeriodicSubs;

    private BigDecimal ertSendDayOfPeriodicSubs;

    private String ertBroker;

    private String ertSalesPromotion;

    private String ertAcceptMethod;

    private String ertForceRedemptionType;

    private String ertTakeIncomeFlag;

    private String ertPurposeOfPeSubs;

    private BigDecimal ertFrequencyOfPeSubs;

    private String ertPeriodSubTimeUnit;

    private BigDecimal ertBatchNumOfPesubs;

    private String ertCapitalMode;

    private String ertDetailCapticalMode;

    private BigDecimal ertBackenloadDiscount;

    private String ertCombineNum;

    private String ertFutureSubscribeDate;

    private String ertTradingMethod;

    private String ertLargeBuyFlag;

    private String ertChargeType;

    private BigDecimal ertSpecifyRateFee;

    private BigDecimal ertSpecifyFee;

    private Date ertCtime;

    private Date ertUtime;

    private String ertCuser;

    private String ertUuser;


    private static final long serialVersionUID = 1L;

    public Long getErtId() {
        return ertId;
    }

    public void setErtId(Long ertId) {
        this.ertId = ertId;
    }

    public String getErtChannelCode() {
        return ertChannelCode;
    }

    public void setErtChannelCode(String ertChannelCode) {
        this.ertChannelCode = ertChannelCode == null ? null : ertChannelCode.trim();
    }

    public String getErtAppSheetSerialNo() {
        return ertAppSheetSerialNo;
    }

    public void setErtAppSheetSerialNo(String ertAppSheetSerialNo) {
        this.ertAppSheetSerialNo = ertAppSheetSerialNo == null ? null : ertAppSheetSerialNo.trim();
    }

    public String getErtFundCode() {
        return ertFundCode;
    }

    public void setErtFundCode(String ertFundCode) {
        this.ertFundCode = ertFundCode == null ? null : ertFundCode.trim();
    }

    public String getErtLargeRedemptionFlag() {
        return ertLargeRedemptionFlag;
    }

    public void setErtLargeRedemptionFlag(String ertLargeRedemptionFlag) {
        this.ertLargeRedemptionFlag = ertLargeRedemptionFlag == null ? null : ertLargeRedemptionFlag.trim();
    }

    public String getErtTransactionDate() {
        return ertTransactionDate;
    }

    public void setErtTransactionDate(String ertTransactionDate) {
        this.ertTransactionDate = ertTransactionDate == null ? null : ertTransactionDate.trim();
    }

    public String getErtTransactionTime() {
        return ertTransactionTime;
    }

    public void setErtTransactionTime(String ertTransactionTime) {
        this.ertTransactionTime = ertTransactionTime == null ? null : ertTransactionTime.trim();
    }

    public String getErtTransactionAccountId() {
        return ertTransactionAccountId;
    }

    public void setErtTransactionAccountId(String ertTransactionAccountId) {
        this.ertTransactionAccountId = ertTransactionAccountId == null ? null : ertTransactionAccountId.trim();
    }

    public String getErtDistributorCode() {
        return ertDistributorCode;
    }

    public void setErtDistributorCode(String ertDistributorCode) {
        this.ertDistributorCode = ertDistributorCode == null ? null : ertDistributorCode.trim();
    }

    public BigDecimal getErtApplicationVol() {
        return ertApplicationVol;
    }

    public void setErtApplicationVol(BigDecimal ertApplicationVol) {
        this.ertApplicationVol = ertApplicationVol;
    }

    public BigDecimal getErtApplicationAmount() {
        return ertApplicationAmount;
    }

    public void setErtApplicationAmount(BigDecimal ertApplicationAmount) {
        this.ertApplicationAmount = ertApplicationAmount;
    }

    public String getErtBusinessCode() {
        return ertBusinessCode;
    }

    public void setErtBusinessCode(String ertBusinessCode) {
        this.ertBusinessCode = ertBusinessCode == null ? null : ertBusinessCode.trim();
    }

    public String getErtTaAccountId() {
        return ertTaAccountId;
    }

    public void setErtTaAccountId(String ertTaAccountId) {
        this.ertTaAccountId = ertTaAccountId == null ? null : ertTaAccountId.trim();
    }

    public BigDecimal getErtDiscountRateOfComm() {
        return ertDiscountRateOfComm;
    }

    public void setErtDiscountRateOfComm(BigDecimal ertDiscountRateOfComm) {
        this.ertDiscountRateOfComm = ertDiscountRateOfComm;
    }

    public String getErtDepositAcct() {
        return ertDepositAcct;
    }

    public void setErtDepositAcct(String ertDepositAcct) {
        this.ertDepositAcct = ertDepositAcct == null ? null : ertDepositAcct.trim();
    }

    public String getErtRegionCode() {
        return ertRegionCode;
    }

    public void setErtRegionCode(String ertRegionCode) {
        this.ertRegionCode = ertRegionCode == null ? null : ertRegionCode.trim();
    }

    public String getErtCurrencyType() {
        return ertCurrencyType;
    }

    public void setErtCurrencyType(String ertCurrencyType) {
        this.ertCurrencyType = ertCurrencyType == null ? null : ertCurrencyType.trim();
    }

    public String getErtBranchCode() {
        return ertBranchCode;
    }

    public void setErtBranchCode(String ertBranchCode) {
        this.ertBranchCode = ertBranchCode == null ? null : ertBranchCode.trim();
    }

    public String getErtOriginalAppSheetNo() {
        return ertOriginalAppSheetNo;
    }

    public void setErtOriginalAppSheetNo(String ertOriginalAppSheetNo) {
        this.ertOriginalAppSheetNo = ertOriginalAppSheetNo == null ? null : ertOriginalAppSheetNo.trim();
    }

    public String getErtOriginalSubsDate() {
        return ertOriginalSubsDate;
    }

    public void setErtOriginalSubsDate(String ertOriginalSubsDate) {
        this.ertOriginalSubsDate = ertOriginalSubsDate == null ? null : ertOriginalSubsDate.trim();
    }

    public String getErtIndividualOrInstitution() {
        return ertIndividualOrInstitution;
    }

    public void setErtIndividualOrInstitution(String ertIndividualOrInstitution) {
        this.ertIndividualOrInstitution = ertIndividualOrInstitution == null ? null : ertIndividualOrInstitution.trim();
    }

    public BigDecimal getErtValidPeriod() {
        return ertValidPeriod;
    }

    public void setErtValidPeriod(BigDecimal ertValidPeriod) {
        this.ertValidPeriod = ertValidPeriod;
    }

    public BigDecimal getErtDaysRedemptionInAdvance() {
        return ertDaysRedemptionInAdvance;
    }

    public void setErtDaysRedemptionInAdvance(BigDecimal ertDaysRedemptionInAdvance) {
        this.ertDaysRedemptionInAdvance = ertDaysRedemptionInAdvance;
    }

    public String getErtRedemptionDateInAdvance() {
        return ertRedemptionDateInAdvance;
    }

    public void setErtRedemptionDateInAdvance(String ertRedemptionDateInAdvance) {
        this.ertRedemptionDateInAdvance = ertRedemptionDateInAdvance == null ? null : ertRedemptionDateInAdvance.trim();
    }

    public String getErtOriginalSerialNo() {
        return ertOriginalSerialNo;
    }

    public void setErtOriginalSerialNo(String ertOriginalSerialNo) {
        this.ertOriginalSerialNo = ertOriginalSerialNo == null ? null : ertOriginalSerialNo.trim();
    }

    public String getErtDateOfPeriodicSubs() {
        return ertDateOfPeriodicSubs;
    }

    public void setErtDateOfPeriodicSubs(String ertDateOfPeriodicSubs) {
        this.ertDateOfPeriodicSubs = ertDateOfPeriodicSubs == null ? null : ertDateOfPeriodicSubs.trim();
    }

    public String getErtTaSerialNo() {
        return ertTaSerialNo;
    }

    public void setErtTaSerialNo(String ertTaSerialNo) {
        this.ertTaSerialNo = ertTaSerialNo == null ? null : ertTaSerialNo.trim();
    }

    public BigDecimal getErtTermOfPeriodicSubs() {
        return ertTermOfPeriodicSubs;
    }

    public void setErtTermOfPeriodicSubs(BigDecimal ertTermOfPeriodicSubs) {
        this.ertTermOfPeriodicSubs = ertTermOfPeriodicSubs;
    }

    public String getErtFutureBuyDate() {
        return ertFutureBuyDate;
    }

    public void setErtFutureBuyDate(String ertFutureBuyDate) {
        this.ertFutureBuyDate = ertFutureBuyDate == null ? null : ertFutureBuyDate.trim();
    }

    public String getErtTargetDistributorCode() {
        return ertTargetDistributorCode;
    }

    public void setErtTargetDistributorCode(String ertTargetDistributorCode) {
        this.ertTargetDistributorCode = ertTargetDistributorCode == null ? null : ertTargetDistributorCode.trim();
    }

    public BigDecimal getErtCharge() {
        return ertCharge;
    }

    public void setErtCharge(BigDecimal ertCharge) {
        this.ertCharge = ertCharge;
    }

    public String getErtTargetBranchCode() {
        return ertTargetBranchCode;
    }

    public void setErtTargetBranchCode(String ertTargetBranchCode) {
        this.ertTargetBranchCode = ertTargetBranchCode == null ? null : ertTargetBranchCode.trim();
    }

    public String getErtTargetTransactAcctCode() {
        return ertTargetTransactAcctCode;
    }

    public void setErtTargetTransactAcctCode(String ertTargetTransactAcctCode) {
        this.ertTargetTransactAcctCode = ertTargetTransactAcctCode == null ? null : ertTargetTransactAcctCode.trim();
    }

    public String getErtTargetRegionCode() {
        return ertTargetRegionCode;
    }

    public void setErtTargetRegionCode(String ertTargetRegionCode) {
        this.ertTargetRegionCode = ertTargetRegionCode == null ? null : ertTargetRegionCode.trim();
    }

    public BigDecimal getErtDividendRatio() {
        return ertDividendRatio;
    }

    public void setErtDividendRatio(BigDecimal ertDividendRatio) {
        this.ertDividendRatio = ertDividendRatio;
    }

    public String getErtSpecification() {
        return ertSpecification;
    }

    public void setErtSpecification(String ertSpecification) {
        this.ertSpecification = ertSpecification == null ? null : ertSpecification.trim();
    }

    public String getErtCodeOfTargetFund() {
        return ertCodeOfTargetFund;
    }

    public void setErtCodeOfTargetFund(String ertCodeOfTargetFund) {
        this.ertCodeOfTargetFund = ertCodeOfTargetFund == null ? null : ertCodeOfTargetFund.trim();
    }

    public BigDecimal getErtTotalBackendLoad() {
        return ertTotalBackendLoad;
    }

    public void setErtTotalBackendLoad(BigDecimal ertTotalBackendLoad) {
        this.ertTotalBackendLoad = ertTotalBackendLoad;
    }

    public String getErtShareClass() {
        return ertShareClass;
    }

    public void setErtShareClass(String ertShareClass) {
        this.ertShareClass = ertShareClass == null ? null : ertShareClass.trim();
    }

    public String getErtOriginalCfmDate() {
        return ertOriginalCfmDate;
    }

    public void setErtOriginalCfmDate(String ertOriginalCfmDate) {
        this.ertOriginalCfmDate = ertOriginalCfmDate == null ? null : ertOriginalCfmDate.trim();
    }

    public String getErtDetailFlag() {
        return ertDetailFlag;
    }

    public void setErtDetailFlag(String ertDetailFlag) {
        this.ertDetailFlag = ertDetailFlag == null ? null : ertDetailFlag.trim();
    }

    public String getErtOriginalAppDate() {
        return ertOriginalAppDate;
    }

    public void setErtOriginalAppDate(String ertOriginalAppDate) {
        this.ertOriginalAppDate = ertOriginalAppDate == null ? null : ertOriginalAppDate.trim();
    }

    public String getErtDefDividendMethod() {
        return ertDefDividendMethod;
    }

    public void setErtDefDividendMethod(String ertDefDividendMethod) {
        this.ertDefDividendMethod = ertDefDividendMethod == null ? null : ertDefDividendMethod.trim();
    }

    public String getErtFrozenCause() {
        return ertFrozenCause;
    }

    public void setErtFrozenCause(String ertFrozenCause) {
        this.ertFrozenCause = ertFrozenCause == null ? null : ertFrozenCause.trim();
    }

    public String getErtFreezingDeadline() {
        return ertFreezingDeadline;
    }

    public void setErtFreezingDeadline(String ertFreezingDeadline) {
        this.ertFreezingDeadline = ertFreezingDeadline == null ? null : ertFreezingDeadline.trim();
    }

    public String getErtVarietyCodePeriodicSub() {
        return ertVarietyCodePeriodicSub;
    }

    public void setErtVarietyCodePeriodicSub(String ertVarietyCodePeriodicSub) {
        this.ertVarietyCodePeriodicSub = ertVarietyCodePeriodicSub == null ? null : ertVarietyCodePeriodicSub.trim();
    }

    public String getErtSerialNoPeriodicSub() {
        return ertSerialNoPeriodicSub;
    }

    public void setErtSerialNoPeriodicSub(String ertSerialNoPeriodicSub) {
        this.ertSerialNoPeriodicSub = ertSerialNoPeriodicSub == null ? null : ertSerialNoPeriodicSub.trim();
    }

    public String getErtRationType() {
        return ertRationType;
    }

    public void setErtRationType(String ertRationType) {
        this.ertRationType = ertRationType == null ? null : ertRationType.trim();
    }

    public String getErtTargetTaAccountId() {
        return ertTargetTaAccountId;
    }

    public void setErtTargetTaAccountId(String ertTargetTaAccountId) {
        this.ertTargetTaAccountId = ertTargetTaAccountId == null ? null : ertTargetTaAccountId.trim();
    }

    public String getErtTargetRegistrarCode() {
        return ertTargetRegistrarCode;
    }

    public void setErtTargetRegistrarCode(String ertTargetRegistrarCode) {
        this.ertTargetRegistrarCode = ertTargetRegistrarCode == null ? null : ertTargetRegistrarCode.trim();
    }

    public String getErtNetNo() {
        return ertNetNo;
    }

    public void setErtNetNo(String ertNetNo) {
        this.ertNetNo = ertNetNo == null ? null : ertNetNo.trim();
    }

    public String getErtCustomerNo() {
        return ertCustomerNo;
    }

    public void setErtCustomerNo(String ertCustomerNo) {
        this.ertCustomerNo = ertCustomerNo == null ? null : ertCustomerNo.trim();
    }

    public String getErtTargetShareType() {
        return ertTargetShareType;
    }

    public void setErtTargetShareType(String ertTargetShareType) {
        this.ertTargetShareType = ertTargetShareType == null ? null : ertTargetShareType.trim();
    }

    public String getErtRationProtocolNo() {
        return ertRationProtocolNo;
    }

    public void setErtRationProtocolNo(String ertRationProtocolNo) {
        this.ertRationProtocolNo = ertRationProtocolNo == null ? null : ertRationProtocolNo.trim();
    }

    public String getErtBeginDatePeriodicSubs() {
        return ertBeginDatePeriodicSubs;
    }

    public void setErtBeginDatePeriodicSubs(String ertBeginDatePeriodicSubs) {
        this.ertBeginDatePeriodicSubs = ertBeginDatePeriodicSubs == null ? null : ertBeginDatePeriodicSubs.trim();
    }

    public String getErtEndDateOfPeriodicSubs() {
        return ertEndDateOfPeriodicSubs;
    }

    public void setErtEndDateOfPeriodicSubs(String ertEndDateOfPeriodicSubs) {
        this.ertEndDateOfPeriodicSubs = ertEndDateOfPeriodicSubs == null ? null : ertEndDateOfPeriodicSubs.trim();
    }

    public BigDecimal getErtSendDayOfPeriodicSubs() {
        return ertSendDayOfPeriodicSubs;
    }

    public void setErtSendDayOfPeriodicSubs(BigDecimal ertSendDayOfPeriodicSubs) {
        this.ertSendDayOfPeriodicSubs = ertSendDayOfPeriodicSubs;
    }

    public String getErtBroker() {
        return ertBroker;
    }

    public void setErtBroker(String ertBroker) {
        this.ertBroker = ertBroker == null ? null : ertBroker.trim();
    }

    public String getErtSalesPromotion() {
        return ertSalesPromotion;
    }

    public void setErtSalesPromotion(String ertSalesPromotion) {
        this.ertSalesPromotion = ertSalesPromotion == null ? null : ertSalesPromotion.trim();
    }

    public String getErtAcceptMethod() {
        return ertAcceptMethod;
    }

    public void setErtAcceptMethod(String ertAcceptMethod) {
        this.ertAcceptMethod = ertAcceptMethod == null ? null : ertAcceptMethod.trim();
    }

    public String getErtForceRedemptionType() {
        return ertForceRedemptionType;
    }

    public void setErtForceRedemptionType(String ertForceRedemptionType) {
        this.ertForceRedemptionType = ertForceRedemptionType == null ? null : ertForceRedemptionType.trim();
    }

    public String getErtTakeIncomeFlag() {
        return ertTakeIncomeFlag;
    }

    public void setErtTakeIncomeFlag(String ertTakeIncomeFlag) {
        this.ertTakeIncomeFlag = ertTakeIncomeFlag == null ? null : ertTakeIncomeFlag.trim();
    }

    public String getErtPurposeOfPeSubs() {
        return ertPurposeOfPeSubs;
    }

    public void setErtPurposeOfPeSubs(String ertPurposeOfPeSubs) {
        this.ertPurposeOfPeSubs = ertPurposeOfPeSubs == null ? null : ertPurposeOfPeSubs.trim();
    }

    public BigDecimal getErtFrequencyOfPeSubs() {
        return ertFrequencyOfPeSubs;
    }

    public void setErtFrequencyOfPeSubs(BigDecimal ertFrequencyOfPeSubs) {
        this.ertFrequencyOfPeSubs = ertFrequencyOfPeSubs;
    }

    public String getErtPeriodSubTimeUnit() {
        return ertPeriodSubTimeUnit;
    }

    public void setErtPeriodSubTimeUnit(String ertPeriodSubTimeUnit) {
        this.ertPeriodSubTimeUnit = ertPeriodSubTimeUnit == null ? null : ertPeriodSubTimeUnit.trim();
    }

    public BigDecimal getErtBatchNumOfPesubs() {
        return ertBatchNumOfPesubs;
    }

    public void setErtBatchNumOfPesubs(BigDecimal ertBatchNumOfPesubs) {
        this.ertBatchNumOfPesubs = ertBatchNumOfPesubs;
    }

    public String getErtCapitalMode() {
        return ertCapitalMode;
    }

    public void setErtCapitalMode(String ertCapitalMode) {
        this.ertCapitalMode = ertCapitalMode == null ? null : ertCapitalMode.trim();
    }

    public String getErtDetailCapticalMode() {
        return ertDetailCapticalMode;
    }

    public void setErtDetailCapticalMode(String ertDetailCapticalMode) {
        this.ertDetailCapticalMode = ertDetailCapticalMode == null ? null : ertDetailCapticalMode.trim();
    }

    public BigDecimal getErtBackenloadDiscount() {
        return ertBackenloadDiscount;
    }

    public void setErtBackenloadDiscount(BigDecimal ertBackenloadDiscount) {
        this.ertBackenloadDiscount = ertBackenloadDiscount;
    }

    public String getErtCombineNum() {
        return ertCombineNum;
    }

    public void setErtCombineNum(String ertCombineNum) {
        this.ertCombineNum = ertCombineNum == null ? null : ertCombineNum.trim();
    }

    public String getErtFutureSubscribeDate() {
        return ertFutureSubscribeDate;
    }

    public void setErtFutureSubscribeDate(String ertFutureSubscribeDate) {
        this.ertFutureSubscribeDate = ertFutureSubscribeDate == null ? null : ertFutureSubscribeDate.trim();
    }

    public String getErtTradingMethod() {
        return ertTradingMethod;
    }

    public void setErtTradingMethod(String ertTradingMethod) {
        this.ertTradingMethod = ertTradingMethod == null ? null : ertTradingMethod.trim();
    }

    public String getErtLargeBuyFlag() {
        return ertLargeBuyFlag;
    }

    public void setErtLargeBuyFlag(String ertLargeBuyFlag) {
        this.ertLargeBuyFlag = ertLargeBuyFlag == null ? null : ertLargeBuyFlag.trim();
    }

    public String getErtChargeType() {
        return ertChargeType;
    }

    public void setErtChargeType(String ertChargeType) {
        this.ertChargeType = ertChargeType == null ? null : ertChargeType.trim();
    }

    public BigDecimal getErtSpecifyRateFee() {
        return ertSpecifyRateFee;
    }

    public void setErtSpecifyRateFee(BigDecimal ertSpecifyRateFee) {
        this.ertSpecifyRateFee = ertSpecifyRateFee;
    }

    public BigDecimal getErtSpecifyFee() {
        return ertSpecifyFee;
    }

    public void setErtSpecifyFee(BigDecimal ertSpecifyFee) {
        this.ertSpecifyFee = ertSpecifyFee;
    }

    public Date getErtCtime() {
        return ertCtime;
    }

    public void setErtCtime(Date ertCtime) {
        this.ertCtime = ertCtime;
    }

    public Date getErtUtime() {
        return ertUtime;
    }

    public void setErtUtime(Date ertUtime) {
        this.ertUtime = ertUtime;
    }

    public String getErtCuser() {
        return ertCuser;
    }

    public void setErtCuser(String ertCuser) {
        this.ertCuser = ertCuser == null ? null : ertCuser.trim();
    }

    public String getErtUuser() {
        return ertUuser;
    }

    public void setErtUuser(String ertUuser) {
        this.ertUuser = ertUuser == null ? null : ertUuser.trim();
    }

	public String getErtTransDate() {
		return ertTransDate;
	}

	public void setErtTransDate(String ertTransDate) {
		this.ertTransDate = ertTransDate;
	}

	@Override
	public String toString() {
		return "ExchangeReqTmp [ertId=" + ertId + ", ertChannelCode="
				+ ertChannelCode + ", ertTransDate=" + ertTransDate
				+ ", ertAppSheetSerialNo=" + ertAppSheetSerialNo
				+ ", ertFundCode=" + ertFundCode + ", ertLargeRedemptionFlag="
				+ ertLargeRedemptionFlag + ", ertTransactionDate="
				+ ertTransactionDate + ", ertTransactionTime="
				+ ertTransactionTime + ", ertTransactionAccountId="
				+ ertTransactionAccountId + ", ertDistributorCode="
				+ ertDistributorCode + ", ertApplicationVol="
				+ ertApplicationVol + ", ertApplicationAmount="
				+ ertApplicationAmount + ", ertBusinessCode=" + ertBusinessCode
				+ ", ertTaAccountId=" + ertTaAccountId
				+ ", ertDiscountRateOfComm=" + ertDiscountRateOfComm
				+ ", ertDepositAcct=" + ertDepositAcct + ", ertRegionCode="
				+ ertRegionCode + ", ertCurrencyType=" + ertCurrencyType
				+ ", ertBranchCode=" + ertBranchCode
				+ ", ertOriginalAppSheetNo=" + ertOriginalAppSheetNo
				+ ", ertOriginalSubsDate=" + ertOriginalSubsDate
				+ ", ertIndividualOrInstitution=" + ertIndividualOrInstitution
				+ ", ertValidPeriod=" + ertValidPeriod
				+ ", ertDaysRedemptionInAdvance=" + ertDaysRedemptionInAdvance
				+ ", ertRedemptionDateInAdvance=" + ertRedemptionDateInAdvance
				+ ", ertOriginalSerialNo=" + ertOriginalSerialNo
				+ ", ertDateOfPeriodicSubs=" + ertDateOfPeriodicSubs
				+ ", ertTaSerialNo=" + ertTaSerialNo
				+ ", ertTermOfPeriodicSubs=" + ertTermOfPeriodicSubs
				+ ", ertFutureBuyDate=" + ertFutureBuyDate
				+ ", ertTargetDistributorCode=" + ertTargetDistributorCode
				+ ", ertCharge=" + ertCharge + ", ertTargetBranchCode="
				+ ertTargetBranchCode + ", ertTargetTransactAcctCode="
				+ ertTargetTransactAcctCode + ", ertTargetRegionCode="
				+ ertTargetRegionCode + ", ertDividendRatio="
				+ ertDividendRatio + ", ertSpecification=" + ertSpecification
				+ ", ertCodeOfTargetFund=" + ertCodeOfTargetFund
				+ ", ertTotalBackendLoad=" + ertTotalBackendLoad
				+ ", ertShareClass=" + ertShareClass + ", ertOriginalCfmDate="
				+ ertOriginalCfmDate + ", ertDetailFlag=" + ertDetailFlag
				+ ", ertOriginalAppDate=" + ertOriginalAppDate
				+ ", ertDefDividendMethod=" + ertDefDividendMethod
				+ ", ertFrozenCause=" + ertFrozenCause
				+ ", ertFreezingDeadline=" + ertFreezingDeadline
				+ ", ertVarietyCodePeriodicSub=" + ertVarietyCodePeriodicSub
				+ ", ertSerialNoPeriodicSub=" + ertSerialNoPeriodicSub
				+ ", ertRationType=" + ertRationType
				+ ", ertTargetTaAccountId=" + ertTargetTaAccountId
				+ ", ertTargetRegistrarCode=" + ertTargetRegistrarCode
				+ ", ertNetNo=" + ertNetNo + ", ertCustomerNo=" + ertCustomerNo
				+ ", ertTargetShareType=" + ertTargetShareType
				+ ", ertRationProtocolNo=" + ertRationProtocolNo
				+ ", ertBeginDatePeriodicSubs=" + ertBeginDatePeriodicSubs
				+ ", ertEndDateOfPeriodicSubs=" + ertEndDateOfPeriodicSubs
				+ ", ertSendDayOfPeriodicSubs=" + ertSendDayOfPeriodicSubs
				+ ", ertBroker=" + ertBroker + ", ertSalesPromotion="
				+ ertSalesPromotion + ", ertAcceptMethod=" + ertAcceptMethod
				+ ", ertForceRedemptionType=" + ertForceRedemptionType
				+ ", ertTakeIncomeFlag=" + ertTakeIncomeFlag
				+ ", ertPurposeOfPeSubs=" + ertPurposeOfPeSubs
				+ ", ertFrequencyOfPeSubs=" + ertFrequencyOfPeSubs
				+ ", ertPeriodSubTimeUnit=" + ertPeriodSubTimeUnit
				+ ", ertBatchNumOfPesubs=" + ertBatchNumOfPesubs
				+ ", ertCapitalMode=" + ertCapitalMode
				+ ", ertDetailCapticalMode=" + ertDetailCapticalMode
				+ ", ertBackenloadDiscount=" + ertBackenloadDiscount
				+ ", ertCombineNum=" + ertCombineNum
				+ ", ertFutureSubscribeDate=" + ertFutureSubscribeDate
				+ ", ertTradingMethod=" + ertTradingMethod
				+ ", ertLargeBuyFlag=" + ertLargeBuyFlag + ", ertChargeType="
				+ ertChargeType + ", ertSpecifyRateFee=" + ertSpecifyRateFee
				+ ", ertSpecifyFee=" + ertSpecifyFee + ", ertCtime=" + ertCtime
				+ ", ertUtime=" + ertUtime + ", ertCuser=" + ertCuser
				+ ", ertUuser=" + ertUuser + "]";
	}

}