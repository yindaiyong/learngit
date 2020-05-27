package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExchangeReqCfm implements Serializable {
    private Long ercId;

    private String ercChannelCode;

    private String ercAppSheetSerialNo;

    private String ercTransactionCfmDate;

    private String ercCurrencyType;

    private BigDecimal ercConfirmedVol;

    private BigDecimal ercConfirmedAmount;

    private String ercFundCode;

    private String ercLargeRedemptionFlag;

    private String ercTransactionDate;

    private String ercTransactionTime;

    private String ercReturnCode;

    private String ercTransactionAccountId;

    private String ercDistributorCode;

    private BigDecimal ercApplicationVol;

    private BigDecimal ercApplicationAmount;

    private String ercBusinessCode;

    private String ercTaAccountId;

    private String ercTaSerialNo;

    private BigDecimal ercDiscountRateOfComm;

    private String ercDepositAcct;

    private String ercRegionCode;

    private String ercDownloadDate;

    private BigDecimal ercCharge;

    private BigDecimal ercAgencyFee;

    private BigDecimal ercNav;

    private String ercBranchCode;

    private String ercOriginalAppSheetNo;

    private String ercOriginalSubsDate;

    private BigDecimal ercOtherFee1;

    private String ercIndividualOrInstitution;

    private String ercRedemptionDateInadvance;

    private BigDecimal ercStampDuty;

    private BigDecimal ercValidPeriod;

    private BigDecimal ercRateFee;

    private BigDecimal ercTotalBackendLoad;

    private String ercOriginalSerialNo;

    private String ercSpecification;

    private String ercDateOfPeriodicSubs;

    private String ercTargetDistributorCode;

    private String ercTargetBranchCode;

    private String ercTargetTransactAcctCode;

    private String ercTargetRegionCode;

    private String ercTransferDirection;

    private String ercDefDividendMethod;

    private BigDecimal ercDividendRatio;

    private BigDecimal ercInterest;

    private BigDecimal ercVolumeByInterest;

    private BigDecimal ercInterestTax;

    private BigDecimal ercTradingPrice;

    private String ercFreezingDeadline;

    private String ercFrozenCause;

    private BigDecimal ercTax;

    private BigDecimal ercTargetNav;

    private BigDecimal ercTargetFundPrice;

    private BigDecimal ercCfmVolOfTargetFund;

    private BigDecimal ercMinFee;

    private BigDecimal ercOtherFee2;

    private String ercOriginalAppDate;

    private BigDecimal ercTransferFee;

    private String ercFromTaFlag;

    private String ercShareClass;

    private String ercDetailFlag;

    private String ercRedemptionInAdvanceFlag;

    private String ercFrozenMethod;

    private String ercOriginalCfmDate;

    private String ercRedemptionReason;

    private String ercCodeOfTargetFund;

    private BigDecimal ercTotalTransFee;

    private String ercVarietyCodePeriodicSub;

    private String ercSerialNoOfPeriodicSubs;

    private String ercRationType;

    private String ercTargetTaAccountId;

    private String ercTargetRegistrarCode;

    private String ercNetNo;

    private String ercCustomerNo;

    private String ercTargetShareType;

    private String ercRationProtocolNo;

    private String ercBeginDatePeriodicSubs;

    private String ercEndDateOfPeriodicSubs;

    private BigDecimal ercSendDayOfPeriodicSubs;

    private String ercBroker;

    private String ercSalesPromotion;

    private String ercAcceptMethod;

    private String ercForceRedemptionType;

    private String ercAlternationDate;

    private String ercTakeincomeFlag;

    private String ercPurposeOfPeSubs;

    private BigDecimal ercFrequencyOfPeSubs;

    private String ercPeriodSubTimeUnit;

    private BigDecimal ercBatchNumOfPeSubs;

    private String ercCapitalMode;

    private String ercDetailCapticalMode;

    private BigDecimal ercBackenloadDiscount;

    private String ercCombineNum;

    private BigDecimal ercRefundAmount;

    private BigDecimal ercSalePercent;

    private BigDecimal ercManagerRealRatio;

    private BigDecimal ercChangeFee;

    private BigDecimal ercRecuperateFee;

    private BigDecimal ercAchievementPay;

    private BigDecimal ercAchievementCompen;

    private String ercSharesAdjustmentFlag;

    private String ercGeneralTaSerialNo;

    private BigDecimal ercUndiMonetaryIncome;

    private String ercUndiMonetaryIncomeFlag;

    private BigDecimal ercBreachFee;

    private BigDecimal ercBreachFeeBackToFund;

    private String ercPunishFee;

    private String ercTradingMethod;

    private BigDecimal ercChangeAgencyFee;

    private BigDecimal ercRecuperateAgencyFee;

    private String ercErrorDetail;

    private String ercLargeBuyFlag;

    private BigDecimal ercRaiseInterest;

    private String ercFeeCalculator;

    private String ercShareRegisterDate;

    private BigDecimal ercTotalFrozenVol;

    private BigDecimal ercFundVolBalance;

    private BigDecimal ercFrozenBalance;

    private String ercFutureSubscribeDate;

    private String ercTransDateThroughClear;

    private String ercTaErrorDetail;

    private String ercCfmDate;

    private String ercSendStatus;

    private String ercGenerateStatus;

    private Date ercSendFileTime;

    private Date ercGenerateFileTime;

    private String ercTaRetSerialNo;

    private Date ercCtime;

    private Date ercUtime;

    private String ercCuser;

    private String ercUuser;

    private static final long serialVersionUID = 1L;

    public Long getErcId() {
        return ercId;
    }

    public void setErcId(Long ercId) {
        this.ercId = ercId;
    }

    public String getErcChannelCode() {
        return ercChannelCode;
    }

    public void setErcChannelCode(String ercChannelCode) {
        this.ercChannelCode = ercChannelCode == null ? null : ercChannelCode.trim();
    }

    public String getErcAppSheetSerialNo() {
        return ercAppSheetSerialNo;
    }

    public void setErcAppSheetSerialNo(String ercAppSheetSerialNo) {
        this.ercAppSheetSerialNo = ercAppSheetSerialNo == null ? null : ercAppSheetSerialNo.trim();
    }

    public String getErcTransactionCfmDate() {
        return ercTransactionCfmDate;
    }

    public void setErcTransactionCfmDate(String ercTransactionCfmDate) {
        this.ercTransactionCfmDate = ercTransactionCfmDate == null ? null : ercTransactionCfmDate.trim();
    }

    public String getErcCurrencyType() {
        return ercCurrencyType;
    }

    public void setErcCurrencyType(String ercCurrencyType) {
        this.ercCurrencyType = ercCurrencyType == null ? null : ercCurrencyType.trim();
    }

    public BigDecimal getErcConfirmedVol() {
        return ercConfirmedVol;
    }

    public void setErcConfirmedVol(BigDecimal ercConfirmedVol) {
        this.ercConfirmedVol = ercConfirmedVol;
    }

    public BigDecimal getErcConfirmedAmount() {
        return ercConfirmedAmount;
    }

    public void setErcConfirmedAmount(BigDecimal ercConfirmedAmount) {
        this.ercConfirmedAmount = ercConfirmedAmount;
    }

    public String getErcFundCode() {
        return ercFundCode;
    }

    public void setErcFundCode(String ercFundCode) {
        this.ercFundCode = ercFundCode == null ? null : ercFundCode.trim();
    }

    public String getErcLargeRedemptionFlag() {
        return ercLargeRedemptionFlag;
    }

    public void setErcLargeRedemptionFlag(String ercLargeRedemptionFlag) {
        this.ercLargeRedemptionFlag = ercLargeRedemptionFlag == null ? null : ercLargeRedemptionFlag.trim();
    }

    public String getErcTransactionDate() {
        return ercTransactionDate;
    }

    public void setErcTransactionDate(String ercTransactionDate) {
        this.ercTransactionDate = ercTransactionDate == null ? null : ercTransactionDate.trim();
    }

    public String getErcTransactionTime() {
        return ercTransactionTime;
    }

    public void setErcTransactionTime(String ercTransactionTime) {
        this.ercTransactionTime = ercTransactionTime == null ? null : ercTransactionTime.trim();
    }

    public String getErcReturnCode() {
        return ercReturnCode;
    }

    public void setErcReturnCode(String ercReturnCode) {
        this.ercReturnCode = ercReturnCode == null ? null : ercReturnCode.trim();
    }

    public String getErcTransactionAccountId() {
        return ercTransactionAccountId;
    }

    public void setErcTransactionAccountId(String ercTransactionAccountId) {
        this.ercTransactionAccountId = ercTransactionAccountId == null ? null : ercTransactionAccountId.trim();
    }

    public String getErcDistributorCode() {
        return ercDistributorCode;
    }

    public void setErcDistributorCode(String ercDistributorCode) {
        this.ercDistributorCode = ercDistributorCode == null ? null : ercDistributorCode.trim();
    }

    public BigDecimal getErcApplicationVol() {
        return ercApplicationVol;
    }

    public void setErcApplicationVol(BigDecimal ercApplicationVol) {
        this.ercApplicationVol = ercApplicationVol;
    }

    public BigDecimal getErcApplicationAmount() {
        return ercApplicationAmount;
    }

    public void setErcApplicationAmount(BigDecimal ercApplicationAmount) {
        this.ercApplicationAmount = ercApplicationAmount;
    }

    public String getErcBusinessCode() {
        return ercBusinessCode;
    }

    public void setErcBusinessCode(String ercBusinessCode) {
        this.ercBusinessCode = ercBusinessCode == null ? null : ercBusinessCode.trim();
    }

    public String getErcTaAccountId() {
        return ercTaAccountId;
    }

    public void setErcTaAccountId(String ercTaAccountId) {
        this.ercTaAccountId = ercTaAccountId == null ? null : ercTaAccountId.trim();
    }

    public String getErcTaSerialNo() {
        return ercTaSerialNo;
    }

    public void setErcTaSerialNo(String ercTaSerialNo) {
        this.ercTaSerialNo = ercTaSerialNo == null ? null : ercTaSerialNo.trim();
    }

    public BigDecimal getErcDiscountRateOfComm() {
        return ercDiscountRateOfComm;
    }

    public void setErcDiscountRateOfComm(BigDecimal ercDiscountRateOfComm) {
        this.ercDiscountRateOfComm = ercDiscountRateOfComm;
    }

    public String getErcDepositAcct() {
        return ercDepositAcct;
    }

    public void setErcDepositAcct(String ercDepositAcct) {
        this.ercDepositAcct = ercDepositAcct == null ? null : ercDepositAcct.trim();
    }

    public String getErcRegionCode() {
        return ercRegionCode;
    }

    public void setErcRegionCode(String ercRegionCode) {
        this.ercRegionCode = ercRegionCode == null ? null : ercRegionCode.trim();
    }

    public String getErcDownloadDate() {
        return ercDownloadDate;
    }

    public void setErcDownloadDate(String ercDownloadDate) {
        this.ercDownloadDate = ercDownloadDate == null ? null : ercDownloadDate.trim();
    }

    public BigDecimal getErcCharge() {
        return ercCharge;
    }

    public void setErcCharge(BigDecimal ercCharge) {
        this.ercCharge = ercCharge;
    }

    public BigDecimal getErcAgencyFee() {
        return ercAgencyFee;
    }

    public void setErcAgencyFee(BigDecimal ercAgencyFee) {
        this.ercAgencyFee = ercAgencyFee;
    }

    public BigDecimal getErcNav() {
        return ercNav;
    }

    public void setErcNav(BigDecimal ercNav) {
        this.ercNav = ercNav;
    }

    public String getErcBranchCode() {
        return ercBranchCode;
    }

    public void setErcBranchCode(String ercBranchCode) {
        this.ercBranchCode = ercBranchCode == null ? null : ercBranchCode.trim();
    }

    public String getErcOriginalAppSheetNo() {
        return ercOriginalAppSheetNo;
    }

    public void setErcOriginalAppSheetNo(String ercOriginalAppSheetNo) {
        this.ercOriginalAppSheetNo = ercOriginalAppSheetNo == null ? null : ercOriginalAppSheetNo.trim();
    }

    public String getErcOriginalSubsDate() {
        return ercOriginalSubsDate;
    }

    public void setErcOriginalSubsDate(String ercOriginalSubsDate) {
        this.ercOriginalSubsDate = ercOriginalSubsDate == null ? null : ercOriginalSubsDate.trim();
    }

    public BigDecimal getErcOtherFee1() {
        return ercOtherFee1;
    }

    public void setErcOtherFee1(BigDecimal ercOtherFee1) {
        this.ercOtherFee1 = ercOtherFee1;
    }

    public String getErcIndividualOrInstitution() {
        return ercIndividualOrInstitution;
    }

    public void setErcIndividualOrInstitution(String ercIndividualOrInstitution) {
        this.ercIndividualOrInstitution = ercIndividualOrInstitution == null ? null : ercIndividualOrInstitution.trim();
    }

    public String getErcRedemptionDateInadvance() {
        return ercRedemptionDateInadvance;
    }

    public void setErcRedemptionDateInadvance(String ercRedemptionDateInadvance) {
        this.ercRedemptionDateInadvance = ercRedemptionDateInadvance == null ? null : ercRedemptionDateInadvance.trim();
    }

    public BigDecimal getErcStampDuty() {
        return ercStampDuty;
    }

    public void setErcStampDuty(BigDecimal ercStampDuty) {
        this.ercStampDuty = ercStampDuty;
    }

    public BigDecimal getErcValidPeriod() {
        return ercValidPeriod;
    }

    public void setErcValidPeriod(BigDecimal ercValidPeriod) {
        this.ercValidPeriod = ercValidPeriod;
    }

    public BigDecimal getErcRateFee() {
        return ercRateFee;
    }

    public void setErcRateFee(BigDecimal ercRateFee) {
        this.ercRateFee = ercRateFee;
    }

    public BigDecimal getErcTotalBackendLoad() {
        return ercTotalBackendLoad;
    }

    public void setErcTotalBackendLoad(BigDecimal ercTotalBackendLoad) {
        this.ercTotalBackendLoad = ercTotalBackendLoad;
    }

    public String getErcOriginalSerialNo() {
        return ercOriginalSerialNo;
    }

    public void setErcOriginalSerialNo(String ercOriginalSerialNo) {
        this.ercOriginalSerialNo = ercOriginalSerialNo == null ? null : ercOriginalSerialNo.trim();
    }

    public String getErcSpecification() {
        return ercSpecification;
    }

    public void setErcSpecification(String ercSpecification) {
        this.ercSpecification = ercSpecification == null ? null : ercSpecification.trim();
    }

    public String getErcDateOfPeriodicSubs() {
        return ercDateOfPeriodicSubs;
    }

    public void setErcDateOfPeriodicSubs(String ercDateOfPeriodicSubs) {
        this.ercDateOfPeriodicSubs = ercDateOfPeriodicSubs == null ? null : ercDateOfPeriodicSubs.trim();
    }

    public String getErcTargetDistributorCode() {
        return ercTargetDistributorCode;
    }

    public void setErcTargetDistributorCode(String ercTargetDistributorCode) {
        this.ercTargetDistributorCode = ercTargetDistributorCode == null ? null : ercTargetDistributorCode.trim();
    }

    public String getErcTargetBranchCode() {
        return ercTargetBranchCode;
    }

    public void setErcTargetBranchCode(String ercTargetBranchCode) {
        this.ercTargetBranchCode = ercTargetBranchCode == null ? null : ercTargetBranchCode.trim();
    }

    public String getErcTargetTransactAcctCode() {
        return ercTargetTransactAcctCode;
    }

    public void setErcTargetTransactAcctCode(String ercTargetTransactAcctCode) {
        this.ercTargetTransactAcctCode = ercTargetTransactAcctCode == null ? null : ercTargetTransactAcctCode.trim();
    }

    public String getErcTargetRegionCode() {
        return ercTargetRegionCode;
    }

    public void setErcTargetRegionCode(String ercTargetRegionCode) {
        this.ercTargetRegionCode = ercTargetRegionCode == null ? null : ercTargetRegionCode.trim();
    }

    public String getErcTransferDirection() {
        return ercTransferDirection;
    }

    public void setErcTransferDirection(String ercTransferDirection) {
        this.ercTransferDirection = ercTransferDirection == null ? null : ercTransferDirection.trim();
    }

    public String getErcDefDividendMethod() {
        return ercDefDividendMethod;
    }

    public void setErcDefDividendMethod(String ercDefDividendMethod) {
        this.ercDefDividendMethod = ercDefDividendMethod == null ? null : ercDefDividendMethod.trim();
    }

    public BigDecimal getErcDividendRatio() {
        return ercDividendRatio;
    }

    public void setErcDividendRatio(BigDecimal ercDividendRatio) {
        this.ercDividendRatio = ercDividendRatio;
    }

    public BigDecimal getErcInterest() {
        return ercInterest;
    }

    public void setErcInterest(BigDecimal ercInterest) {
        this.ercInterest = ercInterest;
    }

    public BigDecimal getErcVolumeByInterest() {
        return ercVolumeByInterest;
    }

    public void setErcVolumeByInterest(BigDecimal ercVolumeByInterest) {
        this.ercVolumeByInterest = ercVolumeByInterest;
    }

    public BigDecimal getErcInterestTax() {
        return ercInterestTax;
    }

    public void setErcInterestTax(BigDecimal ercInterestTax) {
        this.ercInterestTax = ercInterestTax;
    }

    public BigDecimal getErcTradingPrice() {
        return ercTradingPrice;
    }

    public void setErcTradingPrice(BigDecimal ercTradingPrice) {
        this.ercTradingPrice = ercTradingPrice;
    }

    public String getErcFreezingDeadline() {
        return ercFreezingDeadline;
    }

    public void setErcFreezingDeadline(String ercFreezingDeadline) {
        this.ercFreezingDeadline = ercFreezingDeadline == null ? null : ercFreezingDeadline.trim();
    }

    public String getErcFrozenCause() {
        return ercFrozenCause;
    }

    public void setErcFrozenCause(String ercFrozenCause) {
        this.ercFrozenCause = ercFrozenCause == null ? null : ercFrozenCause.trim();
    }

    public BigDecimal getErcTax() {
        return ercTax;
    }

    public void setErcTax(BigDecimal ercTax) {
        this.ercTax = ercTax;
    }

    public BigDecimal getErcTargetNav() {
        return ercTargetNav;
    }

    public void setErcTargetNav(BigDecimal ercTargetNav) {
        this.ercTargetNav = ercTargetNav;
    }

    public BigDecimal getErcTargetFundPrice() {
        return ercTargetFundPrice;
    }

    public void setErcTargetFundPrice(BigDecimal ercTargetFundPrice) {
        this.ercTargetFundPrice = ercTargetFundPrice;
    }

    public BigDecimal getErcCfmVolOfTargetFund() {
        return ercCfmVolOfTargetFund;
    }

    public void setErcCfmVolOfTargetFund(BigDecimal ercCfmVolOfTargetFund) {
        this.ercCfmVolOfTargetFund = ercCfmVolOfTargetFund;
    }

    public BigDecimal getErcMinFee() {
        return ercMinFee;
    }

    public void setErcMinFee(BigDecimal ercMinFee) {
        this.ercMinFee = ercMinFee;
    }

    public BigDecimal getErcOtherFee2() {
        return ercOtherFee2;
    }

    public void setErcOtherFee2(BigDecimal ercOtherFee2) {
        this.ercOtherFee2 = ercOtherFee2;
    }

    public String getErcOriginalAppDate() {
        return ercOriginalAppDate;
    }

    public void setErcOriginalAppDate(String ercOriginalAppDate) {
        this.ercOriginalAppDate = ercOriginalAppDate == null ? null : ercOriginalAppDate.trim();
    }

    public BigDecimal getErcTransferFee() {
        return ercTransferFee;
    }

    public void setErcTransferFee(BigDecimal ercTransferFee) {
        this.ercTransferFee = ercTransferFee;
    }

    public String getErcFromTaFlag() {
        return ercFromTaFlag;
    }

    public void setErcFromTaFlag(String ercFromTaFlag) {
        this.ercFromTaFlag = ercFromTaFlag == null ? null : ercFromTaFlag.trim();
    }

    public String getErcShareClass() {
        return ercShareClass;
    }

    public void setErcShareClass(String ercShareClass) {
        this.ercShareClass = ercShareClass == null ? null : ercShareClass.trim();
    }

    public String getErcDetailFlag() {
        return ercDetailFlag;
    }

    public void setErcDetailFlag(String ercDetailFlag) {
        this.ercDetailFlag = ercDetailFlag == null ? null : ercDetailFlag.trim();
    }

    public String getErcRedemptionInAdvanceFlag() {
        return ercRedemptionInAdvanceFlag;
    }

    public void setErcRedemptionInAdvanceFlag(String ercRedemptionInAdvanceFlag) {
        this.ercRedemptionInAdvanceFlag = ercRedemptionInAdvanceFlag == null ? null : ercRedemptionInAdvanceFlag.trim();
    }

    public String getErcFrozenMethod() {
        return ercFrozenMethod;
    }

    public void setErcFrozenMethod(String ercFrozenMethod) {
        this.ercFrozenMethod = ercFrozenMethod == null ? null : ercFrozenMethod.trim();
    }

    public String getErcOriginalCfmDate() {
        return ercOriginalCfmDate;
    }

    public void setErcOriginalCfmDate(String ercOriginalCfmDate) {
        this.ercOriginalCfmDate = ercOriginalCfmDate == null ? null : ercOriginalCfmDate.trim();
    }

    public String getErcRedemptionReason() {
        return ercRedemptionReason;
    }

    public void setErcRedemptionReason(String ercRedemptionReason) {
        this.ercRedemptionReason = ercRedemptionReason == null ? null : ercRedemptionReason.trim();
    }

    public String getErcCodeOfTargetFund() {
        return ercCodeOfTargetFund;
    }

    public void setErcCodeOfTargetFund(String ercCodeOfTargetFund) {
        this.ercCodeOfTargetFund = ercCodeOfTargetFund == null ? null : ercCodeOfTargetFund.trim();
    }

    public BigDecimal getErcTotalTransFee() {
        return ercTotalTransFee;
    }

    public void setErcTotalTransFee(BigDecimal ercTotalTransFee) {
        this.ercTotalTransFee = ercTotalTransFee;
    }

    public String getErcVarietyCodePeriodicSub() {
        return ercVarietyCodePeriodicSub;
    }

    public void setErcVarietyCodePeriodicSub(String ercVarietyCodePeriodicSub) {
        this.ercVarietyCodePeriodicSub = ercVarietyCodePeriodicSub == null ? null : ercVarietyCodePeriodicSub.trim();
    }

    public String getErcSerialNoOfPeriodicSubs() {
        return ercSerialNoOfPeriodicSubs;
    }

    public void setErcSerialNoOfPeriodicSubs(String ercSerialNoOfPeriodicSubs) {
        this.ercSerialNoOfPeriodicSubs = ercSerialNoOfPeriodicSubs == null ? null : ercSerialNoOfPeriodicSubs.trim();
    }

    public String getErcRationType() {
        return ercRationType;
    }

    public void setErcRationType(String ercRationType) {
        this.ercRationType = ercRationType == null ? null : ercRationType.trim();
    }

    public String getErcTargetTaAccountId() {
        return ercTargetTaAccountId;
    }

    public void setErcTargetTaAccountId(String ercTargetTaAccountId) {
        this.ercTargetTaAccountId = ercTargetTaAccountId == null ? null : ercTargetTaAccountId.trim();
    }

    public String getErcTargetRegistrarCode() {
        return ercTargetRegistrarCode;
    }

    public void setErcTargetRegistrarCode(String ercTargetRegistrarCode) {
        this.ercTargetRegistrarCode = ercTargetRegistrarCode == null ? null : ercTargetRegistrarCode.trim();
    }

    public String getErcNetNo() {
        return ercNetNo;
    }

    public void setErcNetNo(String ercNetNo) {
        this.ercNetNo = ercNetNo == null ? null : ercNetNo.trim();
    }

    public String getErcCustomerNo() {
        return ercCustomerNo;
    }

    public void setErcCustomerNo(String ercCustomerNo) {
        this.ercCustomerNo = ercCustomerNo == null ? null : ercCustomerNo.trim();
    }

    public String getErcTargetShareType() {
        return ercTargetShareType;
    }

    public void setErcTargetShareType(String ercTargetShareType) {
        this.ercTargetShareType = ercTargetShareType == null ? null : ercTargetShareType.trim();
    }

    public String getErcRationProtocolNo() {
        return ercRationProtocolNo;
    }

    public void setErcRationProtocolNo(String ercRationProtocolNo) {
        this.ercRationProtocolNo = ercRationProtocolNo == null ? null : ercRationProtocolNo.trim();
    }

    public String getErcBeginDatePeriodicSubs() {
        return ercBeginDatePeriodicSubs;
    }

    public void setErcBeginDatePeriodicSubs(String ercBeginDatePeriodicSubs) {
        this.ercBeginDatePeriodicSubs = ercBeginDatePeriodicSubs == null ? null : ercBeginDatePeriodicSubs.trim();
    }

    public String getErcEndDateOfPeriodicSubs() {
        return ercEndDateOfPeriodicSubs;
    }

    public void setErcEndDateOfPeriodicSubs(String ercEndDateOfPeriodicSubs) {
        this.ercEndDateOfPeriodicSubs = ercEndDateOfPeriodicSubs == null ? null : ercEndDateOfPeriodicSubs.trim();
    }

    public BigDecimal getErcSendDayOfPeriodicSubs() {
        return ercSendDayOfPeriodicSubs;
    }

    public void setErcSendDayOfPeriodicSubs(BigDecimal ercSendDayOfPeriodicSubs) {
        this.ercSendDayOfPeriodicSubs = ercSendDayOfPeriodicSubs;
    }

    public String getErcBroker() {
        return ercBroker;
    }

    public void setErcBroker(String ercBroker) {
        this.ercBroker = ercBroker == null ? null : ercBroker.trim();
    }

    public String getErcSalesPromotion() {
        return ercSalesPromotion;
    }

    public void setErcSalesPromotion(String ercSalesPromotion) {
        this.ercSalesPromotion = ercSalesPromotion == null ? null : ercSalesPromotion.trim();
    }

    public String getErcAcceptMethod() {
        return ercAcceptMethod;
    }

    public void setErcAcceptMethod(String ercAcceptMethod) {
        this.ercAcceptMethod = ercAcceptMethod == null ? null : ercAcceptMethod.trim();
    }

    public String getErcForceRedemptionType() {
        return ercForceRedemptionType;
    }

    public void setErcForceRedemptionType(String ercForceRedemptionType) {
        this.ercForceRedemptionType = ercForceRedemptionType == null ? null : ercForceRedemptionType.trim();
    }

    public String getErcAlternationDate() {
        return ercAlternationDate;
    }

    public void setErcAlternationDate(String ercAlternationDate) {
        this.ercAlternationDate = ercAlternationDate == null ? null : ercAlternationDate.trim();
    }

    public String getErcTakeincomeFlag() {
        return ercTakeincomeFlag;
    }

    public void setErcTakeincomeFlag(String ercTakeincomeFlag) {
        this.ercTakeincomeFlag = ercTakeincomeFlag == null ? null : ercTakeincomeFlag.trim();
    }

    public String getErcPurposeOfPeSubs() {
        return ercPurposeOfPeSubs;
    }

    public void setErcPurposeOfPeSubs(String ercPurposeOfPeSubs) {
        this.ercPurposeOfPeSubs = ercPurposeOfPeSubs == null ? null : ercPurposeOfPeSubs.trim();
    }

    public BigDecimal getErcFrequencyOfPeSubs() {
        return ercFrequencyOfPeSubs;
    }

    public void setErcFrequencyOfPeSubs(BigDecimal ercFrequencyOfPeSubs) {
        this.ercFrequencyOfPeSubs = ercFrequencyOfPeSubs;
    }

    public String getErcPeriodSubTimeUnit() {
        return ercPeriodSubTimeUnit;
    }

    public void setErcPeriodSubTimeUnit(String ercPeriodSubTimeUnit) {
        this.ercPeriodSubTimeUnit = ercPeriodSubTimeUnit == null ? null : ercPeriodSubTimeUnit.trim();
    }

    public BigDecimal getErcBatchNumOfPeSubs() {
        return ercBatchNumOfPeSubs;
    }

    public void setErcBatchNumOfPeSubs(BigDecimal ercBatchNumOfPeSubs) {
        this.ercBatchNumOfPeSubs = ercBatchNumOfPeSubs;
    }

    public String getErcCapitalMode() {
        return ercCapitalMode;
    }

    public void setErcCapitalMode(String ercCapitalMode) {
        this.ercCapitalMode = ercCapitalMode == null ? null : ercCapitalMode.trim();
    }

    public String getErcDetailCapticalMode() {
        return ercDetailCapticalMode;
    }

    public void setErcDetailCapticalMode(String ercDetailCapticalMode) {
        this.ercDetailCapticalMode = ercDetailCapticalMode == null ? null : ercDetailCapticalMode.trim();
    }

    public BigDecimal getErcBackenloadDiscount() {
        return ercBackenloadDiscount;
    }

    public void setErcBackenloadDiscount(BigDecimal ercBackenloadDiscount) {
        this.ercBackenloadDiscount = ercBackenloadDiscount;
    }

    public String getErcCombineNum() {
        return ercCombineNum;
    }

    public void setErcCombineNum(String ercCombineNum) {
        this.ercCombineNum = ercCombineNum == null ? null : ercCombineNum.trim();
    }

    public BigDecimal getErcRefundAmount() {
        return ercRefundAmount;
    }

    public void setErcRefundAmount(BigDecimal ercRefundAmount) {
        this.ercRefundAmount = ercRefundAmount;
    }

    public BigDecimal getErcSalePercent() {
        return ercSalePercent;
    }

    public void setErcSalePercent(BigDecimal ercSalePercent) {
        this.ercSalePercent = ercSalePercent;
    }

    public BigDecimal getErcManagerRealRatio() {
        return ercManagerRealRatio;
    }

    public void setErcManagerRealRatio(BigDecimal ercManagerRealRatio) {
        this.ercManagerRealRatio = ercManagerRealRatio;
    }

    public BigDecimal getErcChangeFee() {
        return ercChangeFee;
    }

    public void setErcChangeFee(BigDecimal ercChangeFee) {
        this.ercChangeFee = ercChangeFee;
    }

    public BigDecimal getErcRecuperateFee() {
        return ercRecuperateFee;
    }

    public void setErcRecuperateFee(BigDecimal ercRecuperateFee) {
        this.ercRecuperateFee = ercRecuperateFee;
    }

    public BigDecimal getErcAchievementPay() {
        return ercAchievementPay;
    }

    public void setErcAchievementPay(BigDecimal ercAchievementPay) {
        this.ercAchievementPay = ercAchievementPay;
    }

    public BigDecimal getErcAchievementCompen() {
        return ercAchievementCompen;
    }

    public void setErcAchievementCompen(BigDecimal ercAchievementCompen) {
        this.ercAchievementCompen = ercAchievementCompen;
    }

    public String getErcSharesAdjustmentFlag() {
        return ercSharesAdjustmentFlag;
    }

    public void setErcSharesAdjustmentFlag(String ercSharesAdjustmentFlag) {
        this.ercSharesAdjustmentFlag = ercSharesAdjustmentFlag == null ? null : ercSharesAdjustmentFlag.trim();
    }

    public String getErcGeneralTaSerialNo() {
        return ercGeneralTaSerialNo;
    }

    public void setErcGeneralTaSerialNo(String ercGeneralTaSerialNo) {
        this.ercGeneralTaSerialNo = ercGeneralTaSerialNo == null ? null : ercGeneralTaSerialNo.trim();
    }

    public BigDecimal getErcUndiMonetaryIncome() {
        return ercUndiMonetaryIncome;
    }

    public void setErcUndiMonetaryIncome(BigDecimal ercUndiMonetaryIncome) {
        this.ercUndiMonetaryIncome = ercUndiMonetaryIncome;
    }

    public String getErcUndiMonetaryIncomeFlag() {
        return ercUndiMonetaryIncomeFlag;
    }

    public void setErcUndiMonetaryIncomeFlag(String ercUndiMonetaryIncomeFlag) {
        this.ercUndiMonetaryIncomeFlag = ercUndiMonetaryIncomeFlag == null ? null : ercUndiMonetaryIncomeFlag.trim();
    }

    public BigDecimal getErcBreachFee() {
        return ercBreachFee;
    }

    public void setErcBreachFee(BigDecimal ercBreachFee) {
        this.ercBreachFee = ercBreachFee;
    }

    public BigDecimal getErcBreachFeeBackToFund() {
        return ercBreachFeeBackToFund;
    }

    public void setErcBreachFeeBackToFund(BigDecimal ercBreachFeeBackToFund) {
        this.ercBreachFeeBackToFund = ercBreachFeeBackToFund;
    }

    public String getErcPunishFee() {
        return ercPunishFee;
    }

    public void setErcPunishFee(String ercPunishFee) {
        this.ercPunishFee = ercPunishFee == null ? null : ercPunishFee.trim();
    }

    public String getErcTradingMethod() {
        return ercTradingMethod;
    }

    public void setErcTradingMethod(String ercTradingMethod) {
        this.ercTradingMethod = ercTradingMethod == null ? null : ercTradingMethod.trim();
    }

    public BigDecimal getErcChangeAgencyFee() {
        return ercChangeAgencyFee;
    }

    public void setErcChangeAgencyFee(BigDecimal ercChangeAgencyFee) {
        this.ercChangeAgencyFee = ercChangeAgencyFee;
    }

    public BigDecimal getErcRecuperateAgencyFee() {
        return ercRecuperateAgencyFee;
    }

    public void setErcRecuperateAgencyFee(BigDecimal ercRecuperateAgencyFee) {
        this.ercRecuperateAgencyFee = ercRecuperateAgencyFee;
    }

    public String getErcErrorDetail() {
        return ercErrorDetail;
    }

    public void setErcErrorDetail(String ercErrorDetail) {
        this.ercErrorDetail = ercErrorDetail == null ? null : ercErrorDetail.trim();
    }

    public String getErcLargeBuyFlag() {
        return ercLargeBuyFlag;
    }

    public void setErcLargeBuyFlag(String ercLargeBuyFlag) {
        this.ercLargeBuyFlag = ercLargeBuyFlag == null ? null : ercLargeBuyFlag.trim();
    }

    public BigDecimal getErcRaiseInterest() {
        return ercRaiseInterest;
    }

    public void setErcRaiseInterest(BigDecimal ercRaiseInterest) {
        this.ercRaiseInterest = ercRaiseInterest;
    }

    public String getErcFeeCalculator() {
        return ercFeeCalculator;
    }

    public void setErcFeeCalculator(String ercFeeCalculator) {
        this.ercFeeCalculator = ercFeeCalculator == null ? null : ercFeeCalculator.trim();
    }

    public String getErcShareRegisterDate() {
        return ercShareRegisterDate;
    }

    public void setErcShareRegisterDate(String ercShareRegisterDate) {
        this.ercShareRegisterDate = ercShareRegisterDate == null ? null : ercShareRegisterDate.trim();
    }

    public BigDecimal getErcTotalFrozenVol() {
        return ercTotalFrozenVol;
    }

    public void setErcTotalFrozenVol(BigDecimal ercTotalFrozenVol) {
        this.ercTotalFrozenVol = ercTotalFrozenVol;
    }

    public BigDecimal getErcFundVolBalance() {
        return ercFundVolBalance;
    }

    public void setErcFundVolBalance(BigDecimal ercFundVolBalance) {
        this.ercFundVolBalance = ercFundVolBalance;
    }

    public BigDecimal getErcFrozenBalance() {
        return ercFrozenBalance;
    }

    public void setErcFrozenBalance(BigDecimal ercFrozenBalance) {
        this.ercFrozenBalance = ercFrozenBalance;
    }

    public String getErcFutureSubscribeDate() {
        return ercFutureSubscribeDate;
    }

    public void setErcFutureSubscribeDate(String ercFutureSubscribeDate) {
        this.ercFutureSubscribeDate = ercFutureSubscribeDate == null ? null : ercFutureSubscribeDate.trim();
    }

    public String getErcTransDateThroughClear() {
        return ercTransDateThroughClear;
    }

    public void setErcTransDateThroughClear(String ercTransDateThroughClear) {
        this.ercTransDateThroughClear = ercTransDateThroughClear == null ? null : ercTransDateThroughClear.trim();
    }

    public String getErcTaErrorDetail() {
        return ercTaErrorDetail;
    }

    public void setErcTaErrorDetail(String ercTaErrorDetail) {
        this.ercTaErrorDetail = ercTaErrorDetail == null ? null : ercTaErrorDetail.trim();
    }

    public String getErcCfmDate() {
        return ercCfmDate;
    }

    public void setErcCfmDate(String ercCfmDate) {
        this.ercCfmDate = ercCfmDate == null ? null : ercCfmDate.trim();
    }

    public String getErcSendStatus() {
        return ercSendStatus;
    }

    public void setErcSendStatus(String ercSendStatus) {
        this.ercSendStatus = ercSendStatus == null ? null : ercSendStatus.trim();
    }

    public String getErcGenerateStatus() {
        return ercGenerateStatus;
    }

    public void setErcGenerateStatus(String ercGenerateStatus) {
        this.ercGenerateStatus = ercGenerateStatus == null ? null : ercGenerateStatus.trim();
    }

    public Date getErcSendFileTime() {
        return ercSendFileTime;
    }

    public void setErcSendFileTime(Date ercSendFileTime) {
        this.ercSendFileTime = ercSendFileTime;
    }

    public Date getErcGenerateFileTime() {
        return ercGenerateFileTime;
    }

    public void setErcGenerateFileTime(Date ercGenerateFileTime) {
        this.ercGenerateFileTime = ercGenerateFileTime;
    }

    public String getErcTaRetSerialNo() {
        return ercTaRetSerialNo;
    }

    public void setErcTaRetSerialNo(String ercTaRetSerialNo) {
        this.ercTaRetSerialNo = ercTaRetSerialNo == null ? null : ercTaRetSerialNo.trim();
    }

    public Date getErcCtime() {
        return ercCtime;
    }

    public void setErcCtime(Date ercCtime) {
        this.ercCtime = ercCtime;
    }

    public Date getErcUtime() {
        return ercUtime;
    }

    public void setErcUtime(Date ercUtime) {
        this.ercUtime = ercUtime;
    }

    public String getErcCuser() {
        return ercCuser;
    }

    public void setErcCuser(String ercCuser) {
        this.ercCuser = ercCuser == null ? null : ercCuser.trim();
    }

    public String getErcUuser() {
        return ercUuser;
    }

    public void setErcUuser(String ercUuser) {
        this.ercUuser = ercUuser == null ? null : ercUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ercId=").append(ercId);
        sb.append(", ercChannelCode=").append(ercChannelCode);
        sb.append(", ercAppSheetSerialNo=").append(ercAppSheetSerialNo);
        sb.append(", ercTransactionCfmDate=").append(ercTransactionCfmDate);
        sb.append(", ercCurrencyType=").append(ercCurrencyType);
        sb.append(", ercConfirmedVol=").append(ercConfirmedVol);
        sb.append(", ercConfirmedAmount=").append(ercConfirmedAmount);
        sb.append(", ercFundCode=").append(ercFundCode);
        sb.append(", ercLargeRedemptionFlag=").append(ercLargeRedemptionFlag);
        sb.append(", ercTransactionDate=").append(ercTransactionDate);
        sb.append(", ercTransactionTime=").append(ercTransactionTime);
        sb.append(", ercReturnCode=").append(ercReturnCode);
        sb.append(", ercTransactionAccountId=").append(ercTransactionAccountId);
        sb.append(", ercDistributorCode=").append(ercDistributorCode);
        sb.append(", ercApplicationVol=").append(ercApplicationVol);
        sb.append(", ercApplicationAmount=").append(ercApplicationAmount);
        sb.append(", ercBusinessCode=").append(ercBusinessCode);
        sb.append(", ercTaAccountId=").append(ercTaAccountId);
        sb.append(", ercTaSerialNo=").append(ercTaSerialNo);
        sb.append(", ercDiscountRateOfComm=").append(ercDiscountRateOfComm);
        sb.append(", ercDepositAcct=").append(ercDepositAcct);
        sb.append(", ercRegionCode=").append(ercRegionCode);
        sb.append(", ercDownloadDate=").append(ercDownloadDate);
        sb.append(", ercCharge=").append(ercCharge);
        sb.append(", ercAgencyFee=").append(ercAgencyFee);
        sb.append(", ercNav=").append(ercNav);
        sb.append(", ercBranchCode=").append(ercBranchCode);
        sb.append(", ercOriginalAppSheetNo=").append(ercOriginalAppSheetNo);
        sb.append(", ercOriginalSubsDate=").append(ercOriginalSubsDate);
        sb.append(", ercOtherFee1=").append(ercOtherFee1);
        sb.append(", ercIndividualOrInstitution=").append(ercIndividualOrInstitution);
        sb.append(", ercRedemptionDateInadvance=").append(ercRedemptionDateInadvance);
        sb.append(", ercStampDuty=").append(ercStampDuty);
        sb.append(", ercValidPeriod=").append(ercValidPeriod);
        sb.append(", ercRateFee=").append(ercRateFee);
        sb.append(", ercTotalBackendLoad=").append(ercTotalBackendLoad);
        sb.append(", ercOriginalSerialNo=").append(ercOriginalSerialNo);
        sb.append(", ercSpecification=").append(ercSpecification);
        sb.append(", ercDateOfPeriodicSubs=").append(ercDateOfPeriodicSubs);
        sb.append(", ercTargetDistributorCode=").append(ercTargetDistributorCode);
        sb.append(", ercTargetBranchCode=").append(ercTargetBranchCode);
        sb.append(", ercTargetTransactAcctCode=").append(ercTargetTransactAcctCode);
        sb.append(", ercTargetRegionCode=").append(ercTargetRegionCode);
        sb.append(", ercTransferDirection=").append(ercTransferDirection);
        sb.append(", ercDefDividendMethod=").append(ercDefDividendMethod);
        sb.append(", ercDividendRatio=").append(ercDividendRatio);
        sb.append(", ercInterest=").append(ercInterest);
        sb.append(", ercVolumeByInterest=").append(ercVolumeByInterest);
        sb.append(", ercInterestTax=").append(ercInterestTax);
        sb.append(", ercTradingPrice=").append(ercTradingPrice);
        sb.append(", ercFreezingDeadline=").append(ercFreezingDeadline);
        sb.append(", ercFrozenCause=").append(ercFrozenCause);
        sb.append(", ercTax=").append(ercTax);
        sb.append(", ercTargetNav=").append(ercTargetNav);
        sb.append(", ercTargetFundPrice=").append(ercTargetFundPrice);
        sb.append(", ercCfmVolOfTargetFund=").append(ercCfmVolOfTargetFund);
        sb.append(", ercMinFee=").append(ercMinFee);
        sb.append(", ercOtherFee2=").append(ercOtherFee2);
        sb.append(", ercOriginalAppDate=").append(ercOriginalAppDate);
        sb.append(", ercTransferFee=").append(ercTransferFee);
        sb.append(", ercFromTaFlag=").append(ercFromTaFlag);
        sb.append(", ercShareClass=").append(ercShareClass);
        sb.append(", ercDetailFlag=").append(ercDetailFlag);
        sb.append(", ercRedemptionInAdvanceFlag=").append(ercRedemptionInAdvanceFlag);
        sb.append(", ercFrozenMethod=").append(ercFrozenMethod);
        sb.append(", ercOriginalCfmDate=").append(ercOriginalCfmDate);
        sb.append(", ercRedemptionReason=").append(ercRedemptionReason);
        sb.append(", ercCodeOfTargetFund=").append(ercCodeOfTargetFund);
        sb.append(", ercTotalTransFee=").append(ercTotalTransFee);
        sb.append(", ercVarietyCodePeriodicSub=").append(ercVarietyCodePeriodicSub);
        sb.append(", ercSerialNoOfPeriodicSubs=").append(ercSerialNoOfPeriodicSubs);
        sb.append(", ercRationType=").append(ercRationType);
        sb.append(", ercTargetTaAccountId=").append(ercTargetTaAccountId);
        sb.append(", ercTargetRegistrarCode=").append(ercTargetRegistrarCode);
        sb.append(", ercNetNo=").append(ercNetNo);
        sb.append(", ercCustomerNo=").append(ercCustomerNo);
        sb.append(", ercTargetShareType=").append(ercTargetShareType);
        sb.append(", ercRationProtocolNo=").append(ercRationProtocolNo);
        sb.append(", ercBeginDatePeriodicSubs=").append(ercBeginDatePeriodicSubs);
        sb.append(", ercEndDateOfPeriodicSubs=").append(ercEndDateOfPeriodicSubs);
        sb.append(", ercSendDayOfPeriodicSubs=").append(ercSendDayOfPeriodicSubs);
        sb.append(", ercBroker=").append(ercBroker);
        sb.append(", ercSalesPromotion=").append(ercSalesPromotion);
        sb.append(", ercAcceptMethod=").append(ercAcceptMethod);
        sb.append(", ercForceRedemptionType=").append(ercForceRedemptionType);
        sb.append(", ercAlternationDate=").append(ercAlternationDate);
        sb.append(", ercTakeincomeFlag=").append(ercTakeincomeFlag);
        sb.append(", ercPurposeOfPeSubs=").append(ercPurposeOfPeSubs);
        sb.append(", ercFrequencyOfPeSubs=").append(ercFrequencyOfPeSubs);
        sb.append(", ercPeriodSubTimeUnit=").append(ercPeriodSubTimeUnit);
        sb.append(", ercBatchNumOfPeSubs=").append(ercBatchNumOfPeSubs);
        sb.append(", ercCapitalMode=").append(ercCapitalMode);
        sb.append(", ercDetailCapticalMode=").append(ercDetailCapticalMode);
        sb.append(", ercBackenloadDiscount=").append(ercBackenloadDiscount);
        sb.append(", ercCombineNum=").append(ercCombineNum);
        sb.append(", ercRefundAmount=").append(ercRefundAmount);
        sb.append(", ercSalePercent=").append(ercSalePercent);
        sb.append(", ercManagerRealRatio=").append(ercManagerRealRatio);
        sb.append(", ercChangeFee=").append(ercChangeFee);
        sb.append(", ercRecuperateFee=").append(ercRecuperateFee);
        sb.append(", ercAchievementPay=").append(ercAchievementPay);
        sb.append(", ercAchievementCompen=").append(ercAchievementCompen);
        sb.append(", ercSharesAdjustmentFlag=").append(ercSharesAdjustmentFlag);
        sb.append(", ercGeneralTaSerialNo=").append(ercGeneralTaSerialNo);
        sb.append(", ercUndiMonetaryIncome=").append(ercUndiMonetaryIncome);
        sb.append(", ercUndiMonetaryIncomeFlag=").append(ercUndiMonetaryIncomeFlag);
        sb.append(", ercBreachFee=").append(ercBreachFee);
        sb.append(", ercBreachFeeBackToFund=").append(ercBreachFeeBackToFund);
        sb.append(", ercPunishFee=").append(ercPunishFee);
        sb.append(", ercTradingMethod=").append(ercTradingMethod);
        sb.append(", ercChangeAgencyFee=").append(ercChangeAgencyFee);
        sb.append(", ercRecuperateAgencyFee=").append(ercRecuperateAgencyFee);
        sb.append(", ercErrorDetail=").append(ercErrorDetail);
        sb.append(", ercLargeBuyFlag=").append(ercLargeBuyFlag);
        sb.append(", ercRaiseInterest=").append(ercRaiseInterest);
        sb.append(", ercFeeCalculator=").append(ercFeeCalculator);
        sb.append(", ercShareRegisterDate=").append(ercShareRegisterDate);
        sb.append(", ercTotalFrozenVol=").append(ercTotalFrozenVol);
        sb.append(", ercFundVolBalance=").append(ercFundVolBalance);
        sb.append(", ercFrozenBalance=").append(ercFrozenBalance);
        sb.append(", ercFutureSubscribeDate=").append(ercFutureSubscribeDate);
        sb.append(", ercTransDateThroughClear=").append(ercTransDateThroughClear);
        sb.append(", ercTaErrorDetail=").append(ercTaErrorDetail);
        sb.append(", ercCfmDate=").append(ercCfmDate);
        sb.append(", ercSendStatus=").append(ercSendStatus);
        sb.append(", ercGenerateStatus=").append(ercGenerateStatus);
        sb.append(", ercSendFileTime=").append(ercSendFileTime);
        sb.append(", ercGenerateFileTime=").append(ercGenerateFileTime);
        sb.append(", ercTaRetSerialNo=").append(ercTaRetSerialNo);
        sb.append(", ercCtime=").append(ercCtime);
        sb.append(", ercUtime=").append(ercUtime);
        sb.append(", ercCuser=").append(ercCuser);
        sb.append(", ercUuser=").append(ercUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}