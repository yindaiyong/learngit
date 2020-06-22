package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExchangeReq implements Serializable {
    private Long erId;

    private String erChannelCode;

    private String erTransDate;

    private String erAppSheetSerialNo;

    private String erFundCode;

    private String erLargeRedemptionFlag;

    private String erTransactionDate;

    private String erTransactionTime;

    private String erTransactionAccountId;

    private String erDistributorCode;

    private BigDecimal erApplicationVol;

    private BigDecimal erApplicationAmount;

    private String erBusinessCode;

    private String erTaAccountId;

    private BigDecimal erDiscountRateOfComm;

    private String erDepositAcct;

    private String erRegionCode;

    private String erCurrencyType;

    private String erBranchCode;

    private String erOriginalAppSheetNo;

    private String erOriginalSubsDate;

    private String erIndividualOrInstitution;

    private BigDecimal erValidPeriod;

    private BigDecimal erDaysRedemptionInAdvance;

    private String erRedemptionDateInAdvance;

    private String erOriginalSerialNo;

    private String erDateOfPeriodicSubs;

    private String erTaSerialNo;

    private BigDecimal erTermOfPeriodicSubs;

    private String erFutureBuyDate;

    private String erTargetDistributorCode;

    private BigDecimal erCharge;

    private String erTargetBranchCode;

    private String erTargetTransactAcctCode;

    private String erTargetRegionCode;

    private BigDecimal erDividendRatio;

    private String erSpecification;

    private String erCodeOfTargetFund;

    private BigDecimal erTotalBackendLoad;

    private String erShareClass;

    private String erOriginalCfmDate;

    private String erDetailFlag;

    private String erOriginalAppDate;

    private String erDefDividendMethod;

    private String erFrozenCause;

    private String erFreezingDeadline;

    private String erVarietyCodePeriodicSub;

    private String erSerialNoPeriodicSub;

    private String erRationType;

    private String erTargetTaAccountId;

    private String erTargetRegistrarCode;

    private String erNetNo;

    private String erCustomerNo;

    private String erTargetShareType;

    private String erRationProtocolNo;

    private String erBeginDatePeriodicSubs;

    private String erEndDateOfPeriodicSubs;

    private BigDecimal erSendDayOfPeriodicSubs;

    private String erBroker;

    private String erSalesPromotion;

    private String erAcceptMethod;

    private String erForceRedemptionType;

    private String erTakeIncomeFlag;

    private String erPurposeOfPeSubs;

    private BigDecimal erFrequencyOfPeSubs;

    private String erPeriodSubTimeUnit;

    private BigDecimal erBatchNumOfPesubs;

    private String erCapitalMode;

    private String erDetailCapticalMode;

    private BigDecimal erBackenloadDiscount;

    private String erCombineNum;

    private String erFutureSubscribeDate;

    private String erTradingMethod;

    private String erLargeBuyFlag;

    private String erChargeType;

    private BigDecimal erSpecifyRateFee;

    private BigDecimal erSpecifyFee;

    private String erValidFlag;

    private String erErrorDEC;

    private String erTASerialNO;

    private String erTATransStatus;
    
    private String erExpectedCFMDate;
    
    private String erTaProductCode;

    private Date erCtime;

    private Date erUtime;

    private String erCuser;

    private String erUuser;
    
    private String erBatchNo;

    private static final long serialVersionUID = 1L;

    public Long getErId() {
        return erId;
    }

    public void setErId(Long erId) {
        this.erId = erId;
    }

    public String getErChannelCode() {
        return erChannelCode;
    }

    public void setErChannelCode(String erChannelCode) {
        this.erChannelCode = erChannelCode == null ? null : erChannelCode.trim();
    }

    public String getErAppSheetSerialNo() {
        return erAppSheetSerialNo;
    }

    public void setErAppSheetSerialNo(String erAppSheetSerialNo) {
        this.erAppSheetSerialNo = erAppSheetSerialNo == null ? null : erAppSheetSerialNo.trim();
    }

    public String getErFundCode() {
        return erFundCode;
    }

    public void setErFundCode(String erFundCode) {
        this.erFundCode = erFundCode == null ? null : erFundCode.trim();
    }

    public String getErLargeRedemptionFlag() {
        return erLargeRedemptionFlag;
    }

    public void setErLargeRedemptionFlag(String erLargeRedemptionFlag) {
        this.erLargeRedemptionFlag = erLargeRedemptionFlag == null ? null : erLargeRedemptionFlag.trim();
    }

    public String getErTransactionDate() {
        return erTransactionDate;
    }

    public void setErTransactionDate(String erTransactionDate) {
        this.erTransactionDate = erTransactionDate == null ? null : erTransactionDate.trim();
    }

    public String getErTransactionTime() {
        return erTransactionTime;
    }

    public void setErTransactionTime(String erTransactionTime) {
        this.erTransactionTime = erTransactionTime == null ? null : erTransactionTime.trim();
    }

    public String getErTransactionAccountId() {
        return erTransactionAccountId;
    }

    public void setErTransactionAccountId(String erTransactionAccountId) {
        this.erTransactionAccountId = erTransactionAccountId == null ? null : erTransactionAccountId.trim();
    }

    public String getErDistributorCode() {
        return erDistributorCode;
    }

    public void setErDistributorCode(String erDistributorCode) {
        this.erDistributorCode = erDistributorCode == null ? null : erDistributorCode.trim();
    }

    public BigDecimal getErApplicationVol() {
        return erApplicationVol;
    }

    public void setErApplicationVol(BigDecimal erApplicationVol) {
        this.erApplicationVol = erApplicationVol;
    }

    public BigDecimal getErApplicationAmount() {
        return erApplicationAmount;
    }

    public void setErApplicationAmount(BigDecimal erApplicationAmount) {
        this.erApplicationAmount = erApplicationAmount;
    }

    public String getErBusinessCode() {
        return erBusinessCode;
    }

    public void setErBusinessCode(String erBusinessCode) {
        this.erBusinessCode = erBusinessCode == null ? null : erBusinessCode.trim();
    }

    public String getErTaAccountId() {
        return erTaAccountId;
    }

    public void setErTaAccountId(String erTaAccountId) {
        this.erTaAccountId = erTaAccountId == null ? null : erTaAccountId.trim();
    }

    public BigDecimal getErDiscountRateOfComm() {
        return erDiscountRateOfComm;
    }

    public void setErDiscountRateOfComm(BigDecimal erDiscountRateOfComm) {
        this.erDiscountRateOfComm = erDiscountRateOfComm;
    }

    public String getErDepositAcct() {
        return erDepositAcct;
    }

    public void setErDepositAcct(String erDepositAcct) {
        this.erDepositAcct = erDepositAcct == null ? null : erDepositAcct.trim();
    }

    public String getErRegionCode() {
        return erRegionCode;
    }

    public void setErRegionCode(String erRegionCode) {
        this.erRegionCode = erRegionCode == null ? null : erRegionCode.trim();
    }

    public String getErCurrencyType() {
        return erCurrencyType;
    }

    public void setErCurrencyType(String erCurrencyType) {
        this.erCurrencyType = erCurrencyType == null ? null : erCurrencyType.trim();
    }

    public String getErBranchCode() {
        return erBranchCode;
    }

    public void setErBranchCode(String erBranchCode) {
        this.erBranchCode = erBranchCode == null ? null : erBranchCode.trim();
    }

    public String getErOriginalAppSheetNo() {
        return erOriginalAppSheetNo;
    }

    public void setErOriginalAppSheetNo(String erOriginalAppSheetNo) {
        this.erOriginalAppSheetNo = erOriginalAppSheetNo == null ? null : erOriginalAppSheetNo.trim();
    }

    public String getErOriginalSubsDate() {
        return erOriginalSubsDate;
    }

    public void setErOriginalSubsDate(String erOriginalSubsDate) {
        this.erOriginalSubsDate = erOriginalSubsDate == null ? null : erOriginalSubsDate.trim();
    }

    public String getErIndividualOrInstitution() {
        return erIndividualOrInstitution;
    }

    public void setErIndividualOrInstitution(String erIndividualOrInstitution) {
        this.erIndividualOrInstitution = erIndividualOrInstitution == null ? null : erIndividualOrInstitution.trim();
    }

    public BigDecimal getErValidPeriod() {
        return erValidPeriod;
    }

    public void setErValidPeriod(BigDecimal erValidPeriod) {
        this.erValidPeriod = erValidPeriod;
    }

    public BigDecimal getErDaysRedemptionInAdvance() {
        return erDaysRedemptionInAdvance;
    }

    public void setErDaysRedemptionInAdvance(BigDecimal erDaysRedemptionInAdvance) {
        this.erDaysRedemptionInAdvance = erDaysRedemptionInAdvance;
    }

    public String getErRedemptionDateInAdvance() {
        return erRedemptionDateInAdvance;
    }

    public void setErRedemptionDateInAdvance(String erRedemptionDateInAdvance) {
        this.erRedemptionDateInAdvance = erRedemptionDateInAdvance == null ? null : erRedemptionDateInAdvance.trim();
    }

    public String getErOriginalSerialNo() {
        return erOriginalSerialNo;
    }

    public void setErOriginalSerialNo(String erOriginalSerialNo) {
        this.erOriginalSerialNo = erOriginalSerialNo == null ? null : erOriginalSerialNo.trim();
    }

    public String getErDateOfPeriodicSubs() {
        return erDateOfPeriodicSubs;
    }

    public void setErDateOfPeriodicSubs(String erDateOfPeriodicSubs) {
        this.erDateOfPeriodicSubs = erDateOfPeriodicSubs == null ? null : erDateOfPeriodicSubs.trim();
    }

    public String getErTaSerialNo() {
        return erTaSerialNo;
    }

    public void setErTaSerialNo(String erTaSerialNo) {
        this.erTaSerialNo = erTaSerialNo == null ? null : erTaSerialNo.trim();
    }

    public BigDecimal getErTermOfPeriodicSubs() {
        return erTermOfPeriodicSubs;
    }

    public void setErTermOfPeriodicSubs(BigDecimal erTermOfPeriodicSubs) {
        this.erTermOfPeriodicSubs = erTermOfPeriodicSubs;
    }

    public String getErFutureBuyDate() {
        return erFutureBuyDate;
    }

    public void setErFutureBuyDate(String erFutureBuyDate) {
        this.erFutureBuyDate = erFutureBuyDate == null ? null : erFutureBuyDate.trim();
    }

    public String getErTargetDistributorCode() {
        return erTargetDistributorCode;
    }

    public void setErTargetDistributorCode(String erTargetDistributorCode) {
        this.erTargetDistributorCode = erTargetDistributorCode == null ? null : erTargetDistributorCode.trim();
    }

    public BigDecimal getErCharge() {
        return erCharge;
    }

    public void setErCharge(BigDecimal erCharge) {
        this.erCharge = erCharge;
    }

    public String getErTargetBranchCode() {
        return erTargetBranchCode;
    }

    public void setErTargetBranchCode(String erTargetBranchCode) {
        this.erTargetBranchCode = erTargetBranchCode == null ? null : erTargetBranchCode.trim();
    }

    public String getErTargetTransactAcctCode() {
        return erTargetTransactAcctCode;
    }

    public void setErTargetTransactAcctCode(String erTargetTransactAcctCode) {
        this.erTargetTransactAcctCode = erTargetTransactAcctCode == null ? null : erTargetTransactAcctCode.trim();
    }

    public String getErTargetRegionCode() {
        return erTargetRegionCode;
    }

    public void setErTargetRegionCode(String erTargetRegionCode) {
        this.erTargetRegionCode = erTargetRegionCode == null ? null : erTargetRegionCode.trim();
    }

    public BigDecimal getErDividendRatio() {
        return erDividendRatio;
    }

    public void setErDividendRatio(BigDecimal erDividendRatio) {
        this.erDividendRatio = erDividendRatio;
    }

    public String getErSpecification() {
        return erSpecification;
    }

    public void setErSpecification(String erSpecification) {
        this.erSpecification = erSpecification == null ? null : erSpecification.trim();
    }

    public String getErCodeOfTargetFund() {
        return erCodeOfTargetFund;
    }

    public void setErCodeOfTargetFund(String erCodeOfTargetFund) {
        this.erCodeOfTargetFund = erCodeOfTargetFund == null ? null : erCodeOfTargetFund.trim();
    }

    public BigDecimal getErTotalBackendLoad() {
        return erTotalBackendLoad;
    }

    public void setErTotalBackendLoad(BigDecimal erTotalBackendLoad) {
        this.erTotalBackendLoad = erTotalBackendLoad;
    }

    public String getErShareClass() {
        return erShareClass;
    }

    public void setErShareClass(String erShareClass) {
        this.erShareClass = erShareClass == null ? null : erShareClass.trim();
    }

    public String getErOriginalCfmDate() {
        return erOriginalCfmDate;
    }

    public void setErOriginalCfmDate(String erOriginalCfmDate) {
        this.erOriginalCfmDate = erOriginalCfmDate == null ? null : erOriginalCfmDate.trim();
    }

    public String getErDetailFlag() {
        return erDetailFlag;
    }

    public void setErDetailFlag(String erDetailFlag) {
        this.erDetailFlag = erDetailFlag == null ? null : erDetailFlag.trim();
    }

    public String getErOriginalAppDate() {
        return erOriginalAppDate;
    }

    public void setErOriginalAppDate(String erOriginalAppDate) {
        this.erOriginalAppDate = erOriginalAppDate == null ? null : erOriginalAppDate.trim();
    }

    public String getErDefDividendMethod() {
        return erDefDividendMethod;
    }

    public void setErDefDividendMethod(String erDefDividendMethod) {
        this.erDefDividendMethod = erDefDividendMethod == null ? null : erDefDividendMethod.trim();
    }

    public String getErFrozenCause() {
        return erFrozenCause;
    }

    public void setErFrozenCause(String erFrozenCause) {
        this.erFrozenCause = erFrozenCause == null ? null : erFrozenCause.trim();
    }

    public String getErFreezingDeadline() {
        return erFreezingDeadline;
    }

    public void setErFreezingDeadline(String erFreezingDeadline) {
        this.erFreezingDeadline = erFreezingDeadline == null ? null : erFreezingDeadline.trim();
    }

    public String getErVarietyCodePeriodicSub() {
        return erVarietyCodePeriodicSub;
    }

    public void setErVarietyCodePeriodicSub(String erVarietyCodePeriodicSub) {
        this.erVarietyCodePeriodicSub = erVarietyCodePeriodicSub == null ? null : erVarietyCodePeriodicSub.trim();
    }

    public String getErSerialNoPeriodicSub() {
        return erSerialNoPeriodicSub;
    }

    public void setErSerialNoPeriodicSub(String erSerialNoPeriodicSub) {
        this.erSerialNoPeriodicSub = erSerialNoPeriodicSub == null ? null : erSerialNoPeriodicSub.trim();
    }

    public String getErRationType() {
        return erRationType;
    }

    public void setErRationType(String erRationType) {
        this.erRationType = erRationType == null ? null : erRationType.trim();
    }

    public String getErTargetTaAccountId() {
        return erTargetTaAccountId;
    }

    public void setErTargetTaAccountId(String erTargetTaAccountId) {
        this.erTargetTaAccountId = erTargetTaAccountId == null ? null : erTargetTaAccountId.trim();
    }

    public String getErTargetRegistrarCode() {
        return erTargetRegistrarCode;
    }

    public void setErTargetRegistrarCode(String erTargetRegistrarCode) {
        this.erTargetRegistrarCode = erTargetRegistrarCode == null ? null : erTargetRegistrarCode.trim();
    }

    public String getErNetNo() {
        return erNetNo;
    }

    public void setErNetNo(String erNetNo) {
        this.erNetNo = erNetNo == null ? null : erNetNo.trim();
    }

    public String getErCustomerNo() {
        return erCustomerNo;
    }

    public void setErCustomerNo(String erCustomerNo) {
        this.erCustomerNo = erCustomerNo == null ? null : erCustomerNo.trim();
    }

    public String getErTargetShareType() {
        return erTargetShareType;
    }

    public void setErTargetShareType(String erTargetShareType) {
        this.erTargetShareType = erTargetShareType == null ? null : erTargetShareType.trim();
    }

    public String getErRationProtocolNo() {
        return erRationProtocolNo;
    }

    public void setErRationProtocolNo(String erRationProtocolNo) {
        this.erRationProtocolNo = erRationProtocolNo == null ? null : erRationProtocolNo.trim();
    }

    public String getErBeginDatePeriodicSubs() {
        return erBeginDatePeriodicSubs;
    }

    public void setErBeginDatePeriodicSubs(String erBeginDatePeriodicSubs) {
        this.erBeginDatePeriodicSubs = erBeginDatePeriodicSubs == null ? null : erBeginDatePeriodicSubs.trim();
    }

    public String getErEndDateOfPeriodicSubs() {
        return erEndDateOfPeriodicSubs;
    }

    public void setErEndDateOfPeriodicSubs(String erEndDateOfPeriodicSubs) {
        this.erEndDateOfPeriodicSubs = erEndDateOfPeriodicSubs == null ? null : erEndDateOfPeriodicSubs.trim();
    }

    public BigDecimal getErSendDayOfPeriodicSubs() {
        return erSendDayOfPeriodicSubs;
    }

    public void setErSendDayOfPeriodicSubs(BigDecimal erSendDayOfPeriodicSubs) {
        this.erSendDayOfPeriodicSubs = erSendDayOfPeriodicSubs;
    }

    public String getErBroker() {
        return erBroker;
    }

    public void setErBroker(String erBroker) {
        this.erBroker = erBroker == null ? null : erBroker.trim();
    }

    public String getErSalesPromotion() {
        return erSalesPromotion;
    }

    public void setErSalesPromotion(String erSalesPromotion) {
        this.erSalesPromotion = erSalesPromotion == null ? null : erSalesPromotion.trim();
    }

    public String getErAcceptMethod() {
        return erAcceptMethod;
    }

    public void setErAcceptMethod(String erAcceptMethod) {
        this.erAcceptMethod = erAcceptMethod == null ? null : erAcceptMethod.trim();
    }

    public String getErForceRedemptionType() {
        return erForceRedemptionType;
    }

    public void setErForceRedemptionType(String erForceRedemptionType) {
        this.erForceRedemptionType = erForceRedemptionType == null ? null : erForceRedemptionType.trim();
    }

    public String getErTakeIncomeFlag() {
        return erTakeIncomeFlag;
    }

    public void setErTakeIncomeFlag(String erTakeIncomeFlag) {
        this.erTakeIncomeFlag = erTakeIncomeFlag == null ? null : erTakeIncomeFlag.trim();
    }

    public String getErPurposeOfPeSubs() {
        return erPurposeOfPeSubs;
    }

    public void setErPurposeOfPeSubs(String erPurposeOfPeSubs) {
        this.erPurposeOfPeSubs = erPurposeOfPeSubs == null ? null : erPurposeOfPeSubs.trim();
    }

    public BigDecimal getErFrequencyOfPeSubs() {
        return erFrequencyOfPeSubs;
    }

    public void setErFrequencyOfPeSubs(BigDecimal erFrequencyOfPeSubs) {
        this.erFrequencyOfPeSubs = erFrequencyOfPeSubs;
    }

    public String getErPeriodSubTimeUnit() {
        return erPeriodSubTimeUnit;
    }

    public void setErPeriodSubTimeUnit(String erPeriodSubTimeUnit) {
        this.erPeriodSubTimeUnit = erPeriodSubTimeUnit == null ? null : erPeriodSubTimeUnit.trim();
    }

    public BigDecimal getErBatchNumOfPesubs() {
        return erBatchNumOfPesubs;
    }

    public void setErBatchNumOfPesubs(BigDecimal erBatchNumOfPesubs) {
        this.erBatchNumOfPesubs = erBatchNumOfPesubs;
    }

    public String getErCapitalMode() {
        return erCapitalMode;
    }

    public void setErCapitalMode(String erCapitalMode) {
        this.erCapitalMode = erCapitalMode == null ? null : erCapitalMode.trim();
    }

    public String getErDetailCapticalMode() {
        return erDetailCapticalMode;
    }

    public void setErDetailCapticalMode(String erDetailCapticalMode) {
        this.erDetailCapticalMode = erDetailCapticalMode == null ? null : erDetailCapticalMode.trim();
    }

    public BigDecimal getErBackenloadDiscount() {
        return erBackenloadDiscount;
    }

    public void setErBackenloadDiscount(BigDecimal erBackenloadDiscount) {
        this.erBackenloadDiscount = erBackenloadDiscount;
    }

    public String getErCombineNum() {
        return erCombineNum;
    }

    public void setErCombineNum(String erCombineNum) {
        this.erCombineNum = erCombineNum == null ? null : erCombineNum.trim();
    }

    public String getErFutureSubscribeDate() {
        return erFutureSubscribeDate;
    }

    public void setErFutureSubscribeDate(String erFutureSubscribeDate) {
        this.erFutureSubscribeDate = erFutureSubscribeDate == null ? null : erFutureSubscribeDate.trim();
    }

    public String getErTradingMethod() {
        return erTradingMethod;
    }

    public void setErTradingMethod(String erTradingMethod) {
        this.erTradingMethod = erTradingMethod == null ? null : erTradingMethod.trim();
    }

    public String getErLargeBuyFlag() {
        return erLargeBuyFlag;
    }

    public void setErLargeBuyFlag(String erLargeBuyFlag) {
        this.erLargeBuyFlag = erLargeBuyFlag == null ? null : erLargeBuyFlag.trim();
    }

    public String getErChargeType() {
        return erChargeType;
    }

    public void setErChargeType(String erChargeType) {
        this.erChargeType = erChargeType == null ? null : erChargeType.trim();
    }

    public BigDecimal getErSpecifyRateFee() {
        return erSpecifyRateFee;
    }

    public void setErSpecifyRateFee(BigDecimal erSpecifyRateFee) {
        this.erSpecifyRateFee = erSpecifyRateFee;
    }

    public BigDecimal getErSpecifyFee() {
        return erSpecifyFee;
    }

    public void setErSpecifyFee(BigDecimal erSpecifyFee) {
        this.erSpecifyFee = erSpecifyFee;
    }

    public String getErValidFlag() {
        return erValidFlag;
    }

    public void setErValidFlag(String erValidFlag) {
        this.erValidFlag = erValidFlag == null ? null : erValidFlag.trim();
    }

    public Date getErCtime() {
        return erCtime;
    }

    public void setErCtime(Date erCtime) {
        this.erCtime = erCtime;
    }

    public Date getErUtime() {
        return erUtime;
    }

    public void setErUtime(Date erUtime) {
        this.erUtime = erUtime;
    }

    public String getErCuser() {
        return erCuser;
    }

    public void setErCuser(String erCuser) {
        this.erCuser = erCuser == null ? null : erCuser.trim();
    }

    public String getErUuser() {
        return erUuser;
    }

    public void setErUuser(String erUuser) {
        this.erUuser = erUuser == null ? null : erUuser.trim();
    }

	public String getErTransDate() {
		return erTransDate;
	}

	public void setErTransDate(String erTransDate) {
		this.erTransDate = erTransDate;
	}

	public String getErErrorDEC() {
		return erErrorDEC;
	}

	public void setErErrorDEC(String erErrorDEC) {
		this.erErrorDEC = erErrorDEC;
	}

	public String getErTASerialNO() {
		return erTASerialNO;
	}

	public void setErTASerialNO(String erTASerialNO) {
		this.erTASerialNO = erTASerialNO;
	}

	public String getErTATransStatus() {
		return erTATransStatus;
	}

	public void setErTATransStatus(String erTATransStatus) {
		this.erTATransStatus = erTATransStatus;
	}

	public String getErExpectedCFMDate() {
		return erExpectedCFMDate;
	}

	public String getErTaProductCode() {
		return erTaProductCode;
	}

	public void setErTaProductCode(String erTaProductCode) {
		this.erTaProductCode = erTaProductCode;
	}

	public void setErExpectedCFMDate(String erExpectedCFMDate) {
		this.erExpectedCFMDate = erExpectedCFMDate;
	}

	public String getErBatchNo() {
		return erBatchNo;
	}

	public void setErBatchNo(String erBatchNo) {
		this.erBatchNo = erBatchNo;
	}

	@Override
	public String toString() {
		return "ExchangeReq [erId=" + erId + ", erChannelCode=" + erChannelCode + ", erTransDate=" + erTransDate
				+ ", erAppSheetSerialNo=" + erAppSheetSerialNo + ", erFundCode=" + erFundCode
				+ ", erLargeRedemptionFlag=" + erLargeRedemptionFlag + ", erTransactionDate=" + erTransactionDate
				+ ", erTransactionTime=" + erTransactionTime + ", erTransactionAccountId=" + erTransactionAccountId
				+ ", erDistributorCode=" + erDistributorCode + ", erApplicationVol=" + erApplicationVol
				+ ", erApplicationAmount=" + erApplicationAmount + ", erBusinessCode=" + erBusinessCode
				+ ", erTaAccountId=" + erTaAccountId + ", erDiscountRateOfComm=" + erDiscountRateOfComm
				+ ", erDepositAcct=" + erDepositAcct + ", erRegionCode=" + erRegionCode + ", erCurrencyType="
				+ erCurrencyType + ", erBranchCode=" + erBranchCode + ", erOriginalAppSheetNo=" + erOriginalAppSheetNo
				+ ", erOriginalSubsDate=" + erOriginalSubsDate + ", erIndividualOrInstitution="
				+ erIndividualOrInstitution + ", erValidPeriod=" + erValidPeriod + ", erDaysRedemptionInAdvance="
				+ erDaysRedemptionInAdvance + ", erRedemptionDateInAdvance=" + erRedemptionDateInAdvance
				+ ", erOriginalSerialNo=" + erOriginalSerialNo + ", erDateOfPeriodicSubs=" + erDateOfPeriodicSubs
				+ ", erTaSerialNo=" + erTaSerialNo + ", erTermOfPeriodicSubs=" + erTermOfPeriodicSubs
				+ ", erFutureBuyDate=" + erFutureBuyDate + ", erTargetDistributorCode=" + erTargetDistributorCode
				+ ", erCharge=" + erCharge + ", erTargetBranchCode=" + erTargetBranchCode
				+ ", erTargetTransactAcctCode=" + erTargetTransactAcctCode + ", erTargetRegionCode="
				+ erTargetRegionCode + ", erDividendRatio=" + erDividendRatio + ", erSpecification=" + erSpecification
				+ ", erCodeOfTargetFund=" + erCodeOfTargetFund + ", erTotalBackendLoad=" + erTotalBackendLoad
				+ ", erShareClass=" + erShareClass + ", erOriginalCfmDate=" + erOriginalCfmDate + ", erDetailFlag="
				+ erDetailFlag + ", erOriginalAppDate=" + erOriginalAppDate + ", erDefDividendMethod="
				+ erDefDividendMethod + ", erFrozenCause=" + erFrozenCause + ", erFreezingDeadline="
				+ erFreezingDeadline + ", erVarietyCodePeriodicSub=" + erVarietyCodePeriodicSub
				+ ", erSerialNoPeriodicSub=" + erSerialNoPeriodicSub + ", erRationType=" + erRationType
				+ ", erTargetTaAccountId=" + erTargetTaAccountId + ", erTargetRegistrarCode=" + erTargetRegistrarCode
				+ ", erNetNo=" + erNetNo + ", erCustomerNo=" + erCustomerNo + ", erTargetShareType=" + erTargetShareType
				+ ", erRationProtocolNo=" + erRationProtocolNo + ", erBeginDatePeriodicSubs=" + erBeginDatePeriodicSubs
				+ ", erEndDateOfPeriodicSubs=" + erEndDateOfPeriodicSubs + ", erSendDayOfPeriodicSubs="
				+ erSendDayOfPeriodicSubs + ", erBroker=" + erBroker + ", erSalesPromotion=" + erSalesPromotion
				+ ", erAcceptMethod=" + erAcceptMethod + ", erForceRedemptionType=" + erForceRedemptionType
				+ ", erTakeIncomeFlag=" + erTakeIncomeFlag + ", erPurposeOfPeSubs=" + erPurposeOfPeSubs
				+ ", erFrequencyOfPeSubs=" + erFrequencyOfPeSubs + ", erPeriodSubTimeUnit=" + erPeriodSubTimeUnit
				+ ", erBatchNumOfPesubs=" + erBatchNumOfPesubs + ", erCapitalMode=" + erCapitalMode
				+ ", erDetailCapticalMode=" + erDetailCapticalMode + ", erBackenloadDiscount=" + erBackenloadDiscount
				+ ", erCombineNum=" + erCombineNum + ", erFutureSubscribeDate=" + erFutureSubscribeDate
				+ ", erTradingMethod=" + erTradingMethod + ", erLargeBuyFlag=" + erLargeBuyFlag + ", erChargeType="
				+ erChargeType + ", erSpecifyRateFee=" + erSpecifyRateFee + ", erSpecifyFee=" + erSpecifyFee
				+ ", erValidFlag=" + erValidFlag + ", erErrorDEC=" + erErrorDEC + ", erTASerialNO=" + erTASerialNO
				+ ", erTATransStatus=" + erTATransStatus + ", erExpectedCFMDate=" + erExpectedCFMDate
				+ ", erTaProductCode=" + erTaProductCode + ", erCtime=" + erCtime + ", erUtime=" + erUtime
				+ ", erCuser=" + erCuser + ", erUuser=" + erUuser + "]";
	}

}