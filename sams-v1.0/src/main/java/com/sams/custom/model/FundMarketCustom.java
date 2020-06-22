package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundMarketCustom implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ciChannelCode;

	private String ciChannelName;

	private String ciSaleAgentCode;
	
	private String ciCheckState;

	private String ciMarketCode;

	private String ciCsdcVer;
	
	private String ciSalesPerson;

	private String ciCheckAcctType;

	private String ciEconVerifyType;

	private String ciSztRecvPath;

	private String ciSztSendPath;

	private String ciPerAcctType;

	private String ciOrgAcctType;

	private String ciValidFlag;

	private String ciDealFile;
	
	private String ciBatchNoOnOff;

	private String piProductType;

	private String piChannelProductCode;

	private String piChannelProductName;

	private BigDecimal piIndiMinBidsAmt;

	private BigDecimal piInstMinBidsAmt;

	private BigDecimal piIndiBidsDiffAmt;

	private BigDecimal piInstBidsDiffAmt;

	private BigDecimal piIndiMaxBidsAmt;

	private BigDecimal piInstMaxBidsAmt;

	private BigDecimal piIndiMinAppBidsAmt;

	private BigDecimal piInstMinAppBidsAmt;

	private BigDecimal piIndiAppBidsDiffAmt;

	private BigDecimal piInstAppBidsDiffAmt;

	private BigDecimal piIndiMaxAppBidsAmt;

	private BigDecimal piInstMaxAppBidsAmt;

	private BigDecimal piIndiMinRedeemVol;

	private BigDecimal piInstMinRedeemVol;

	private BigDecimal piIndiRedeemDiff;

	private BigDecimal piInstRedeemDiff;

	private BigDecimal piIndiMaxVol;

	private BigDecimal piInstMaxVol;

	private BigDecimal piIndiMinVol;

	private BigDecimal piInstMinVol;

	private BigDecimal piChannelRate;

	private String piPayType;

	private String piIpoBeginDate;

	private String piIpoEndDate;

	private String piProSetupDate;

	private String piProEndDate;

	private String piProYieldType;

	private BigDecimal piProYield;

	private String piFeeFlag;

	private String piIndiBuyFlag;

	private String piInstBuyFlag;

	private String piProCfmWay;

	private String piCheckState;
	
	private BigDecimal piMaxAmountRaised;

	private Date piCtime;

	private Date piUtime;

	private String piCuser;

	private String piUuser;

	private String piCurrencyType;

	private String piAnnuCalDays;

	private String piOtherFileType;
	
	private String fundStatus;
	
	private String piProductTotalShare;
	
	private String isTradeDateFlag;
	
	private String piValidFlag;
	
	private String piBatchNumber;
	
	private String piChannelCode;
	
	private String sumRemainshares;
	
	private String cpTaProductCode;
	
	private String piSubChannelCode;


	public String getFundStatus() {
		return fundStatus;
	}



	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}



	public String getCiChannelCode() {
		return ciChannelCode;
	}



	public void setCiChannelCode(String ciChannelCode) {
		this.ciChannelCode = ciChannelCode;
	}



	public String getCiChannelName() {
		return ciChannelName;
	}



	public void setCiChannelName(String ciChannelName) {
		this.ciChannelName = ciChannelName;
	}



	public String getCiSaleAgentCode() {
		return ciSaleAgentCode;
	}



	public void setCiSaleAgentCode(String ciSaleAgentCode) {
		this.ciSaleAgentCode = ciSaleAgentCode;
	}



	public String getCiMarketCode() {
		return ciMarketCode;
	}



	public void setCiMarketCode(String ciMarketCode) {
		this.ciMarketCode = ciMarketCode;
	}



	public String getCiCsdcVer() {
		return ciCsdcVer;
	}



	public void setCiCsdcVer(String ciCsdcVer) {
		this.ciCsdcVer = ciCsdcVer;
	}



	public String getCiCheckAcctType() {
		return ciCheckAcctType;
	}



	public void setCiCheckAcctType(String ciCheckAcctType) {
		this.ciCheckAcctType = ciCheckAcctType;
	}



	public String getCiEconVerifyType() {
		return ciEconVerifyType;
	}



	public void setCiEconVerifyType(String ciEconVerifyType) {
		this.ciEconVerifyType = ciEconVerifyType;
	}



	public String getCiSztRecvPath() {
		return ciSztRecvPath;
	}



	public void setCiSztRecvPath(String ciSztRecvPath) {
		this.ciSztRecvPath = ciSztRecvPath;
	}



	public String getCiSztSendPath() {
		return ciSztSendPath;
	}



	public void setCiSztSendPath(String ciSztSendPath) {
		this.ciSztSendPath = ciSztSendPath;
	}



	public String getCiPerAcctType() {
		return ciPerAcctType;
	}



	public void setCiPerAcctType(String ciPerAcctType) {
		this.ciPerAcctType = ciPerAcctType;
	}



	public String getCiOrgAcctType() {
		return ciOrgAcctType;
	}



	public void setCiOrgAcctType(String ciOrgAcctType) {
		this.ciOrgAcctType = ciOrgAcctType;
	}



	public String getCiValidFlag() {
		return ciValidFlag;
	}



	public void setCiValidFlag(String ciValidFlag) {
		this.ciValidFlag = ciValidFlag;
	}



	public String getCiDealFile() {
		return ciDealFile;
	}



	public void setCiDealFile(String ciDealFile) {
		this.ciDealFile = ciDealFile;
	}



	public String getPiProductType() {
		return piProductType;
	}



	public void setPiProductType(String piProductType) {
		this.piProductType = piProductType;
	}



	public String getPiChannelProductCode() {
		return piChannelProductCode;
	}



	public void setPiChannelProductCode(String piChannelProductCode) {
		this.piChannelProductCode = piChannelProductCode;
	}



	public String getPiChannelProductName() {
		return piChannelProductName;
	}



	public void setPiChannelProductName(String piChannelProductName) {
		this.piChannelProductName = piChannelProductName;
	}



	public BigDecimal getPiIndiMinBidsAmt() {
		return piIndiMinBidsAmt;
	}



	public void setPiIndiMinBidsAmt(BigDecimal piIndiMinBidsAmt) {
		this.piIndiMinBidsAmt = piIndiMinBidsAmt;
	}



	public BigDecimal getPiInstMinBidsAmt() {
		return piInstMinBidsAmt;
	}



	public void setPiInstMinBidsAmt(BigDecimal piInstMinBidsAmt) {
		this.piInstMinBidsAmt = piInstMinBidsAmt;
	}



	public BigDecimal getPiIndiBidsDiffAmt() {
		return piIndiBidsDiffAmt;
	}



	public void setPiIndiBidsDiffAmt(BigDecimal piIndiBidsDiffAmt) {
		this.piIndiBidsDiffAmt = piIndiBidsDiffAmt;
	}



	public BigDecimal getPiInstBidsDiffAmt() {
		return piInstBidsDiffAmt;
	}



	public void setPiInstBidsDiffAmt(BigDecimal piInstBidsDiffAmt) {
		this.piInstBidsDiffAmt = piInstBidsDiffAmt;
	}



	public BigDecimal getPiIndiMaxBidsAmt() {
		return piIndiMaxBidsAmt;
	}



	public void setPiIndiMaxBidsAmt(BigDecimal piIndiMaxBidsAmt) {
		this.piIndiMaxBidsAmt = piIndiMaxBidsAmt;
	}



	public BigDecimal getPiInstMaxBidsAmt() {
		return piInstMaxBidsAmt;
	}



	public void setPiInstMaxBidsAmt(BigDecimal piInstMaxBidsAmt) {
		this.piInstMaxBidsAmt = piInstMaxBidsAmt;
	}



	public BigDecimal getPiIndiMinAppBidsAmt() {
		return piIndiMinAppBidsAmt;
	}



	public void setPiIndiMinAppBidsAmt(BigDecimal piIndiMinAppBidsAmt) {
		this.piIndiMinAppBidsAmt = piIndiMinAppBidsAmt;
	}



	public BigDecimal getPiInstMinAppBidsAmt() {
		return piInstMinAppBidsAmt;
	}



	public void setPiInstMinAppBidsAmt(BigDecimal piInstMinAppBidsAmt) {
		this.piInstMinAppBidsAmt = piInstMinAppBidsAmt;
	}



	public BigDecimal getPiIndiAppBidsDiffAmt() {
		return piIndiAppBidsDiffAmt;
	}



	public void setPiIndiAppBidsDiffAmt(BigDecimal piIndiAppBidsDiffAmt) {
		this.piIndiAppBidsDiffAmt = piIndiAppBidsDiffAmt;
	}



	public BigDecimal getPiInstAppBidsDiffAmt() {
		return piInstAppBidsDiffAmt;
	}



	public void setPiInstAppBidsDiffAmt(BigDecimal piInstAppBidsDiffAmt) {
		this.piInstAppBidsDiffAmt = piInstAppBidsDiffAmt;
	}



	public BigDecimal getPiIndiMaxAppBidsAmt() {
		return piIndiMaxAppBidsAmt;
	}



	public void setPiIndiMaxAppBidsAmt(BigDecimal piIndiMaxAppBidsAmt) {
		this.piIndiMaxAppBidsAmt = piIndiMaxAppBidsAmt;
	}



	public BigDecimal getPiInstMaxAppBidsAmt() {
		return piInstMaxAppBidsAmt;
	}



	public void setPiInstMaxAppBidsAmt(BigDecimal piInstMaxAppBidsAmt) {
		this.piInstMaxAppBidsAmt = piInstMaxAppBidsAmt;
	}



	public BigDecimal getPiIndiMinRedeemVol() {
		return piIndiMinRedeemVol;
	}



	public void setPiIndiMinRedeemVol(BigDecimal piIndiMinRedeemVol) {
		this.piIndiMinRedeemVol = piIndiMinRedeemVol;
	}



	public BigDecimal getPiInstMinRedeemVol() {
		return piInstMinRedeemVol;
	}



	public void setPiInstMinRedeemVol(BigDecimal piInstMinRedeemVol) {
		this.piInstMinRedeemVol = piInstMinRedeemVol;
	}



	public BigDecimal getPiIndiRedeemDiff() {
		return piIndiRedeemDiff;
	}



	public void setPiIndiRedeemDiff(BigDecimal piIndiRedeemDiff) {
		this.piIndiRedeemDiff = piIndiRedeemDiff;
	}



	public BigDecimal getPiInstRedeemDiff() {
		return piInstRedeemDiff;
	}



	public void setPiInstRedeemDiff(BigDecimal piInstRedeemDiff) {
		this.piInstRedeemDiff = piInstRedeemDiff;
	}



	public BigDecimal getPiIndiMaxVol() {
		return piIndiMaxVol;
	}



	public void setPiIndiMaxVol(BigDecimal piIndiMaxVol) {
		this.piIndiMaxVol = piIndiMaxVol;
	}



	public BigDecimal getPiInstMaxVol() {
		return piInstMaxVol;
	}



	public void setPiInstMaxVol(BigDecimal piInstMaxVol) {
		this.piInstMaxVol = piInstMaxVol;
	}



	public BigDecimal getPiIndiMinVol() {
		return piIndiMinVol;
	}



	public void setPiIndiMinVol(BigDecimal piIndiMinVol) {
		this.piIndiMinVol = piIndiMinVol;
	}



	public BigDecimal getPiInstMinVol() {
		return piInstMinVol;
	}



	public void setPiInstMinVol(BigDecimal piInstMinVol) {
		this.piInstMinVol = piInstMinVol;
	}



	public BigDecimal getPiChannelRate() {
		return piChannelRate;
	}



	public void setPiChannelRate(BigDecimal piChannelRate) {
		this.piChannelRate = piChannelRate;
	}



	public String getPiPayType() {
		return piPayType;
	}



	public void setPiPayType(String piPayType) {
		this.piPayType = piPayType;
	}




	public String getPiIpoBeginDate() {
		return piIpoBeginDate;
	}



	public void setPiIpoBeginDate(String piIpoBeginDate) {
		this.piIpoBeginDate = piIpoBeginDate;
	}



	public String getPiIpoEndDate() {
		return piIpoEndDate;
	}



	public void setPiIpoEndDate(String piIpoEndDate) {
		this.piIpoEndDate = piIpoEndDate;
	}



	public String getPiProSetupDate() {
		return piProSetupDate;
	}



	public void setPiProSetupDate(String piProSetupDate) {
		this.piProSetupDate = piProSetupDate;
	}



	public String getPiProEndDate() {
		return piProEndDate;
	}



	public BigDecimal getPiMaxAmountRaised() {
		return piMaxAmountRaised;
	}



	public void setPiMaxAmountRaised(BigDecimal piMaxAmountRaised) {
		this.piMaxAmountRaised = piMaxAmountRaised;
	}



	public void setPiProEndDate(String piProEndDate) {
		this.piProEndDate = piProEndDate;
	}



	public String getPiProYieldType() {
		return piProYieldType;
	}



	public void setPiProYieldType(String piProYieldType) {
		this.piProYieldType = piProYieldType;
	}



	public BigDecimal getPiProYield() {
		return piProYield;
	}



	public void setPiProYield(BigDecimal piProYield) {
		this.piProYield = piProYield;
	}



	public String getPiFeeFlag() {
		return piFeeFlag;
	}



	public void setPiFeeFlag(String piFeeFlag) {
		this.piFeeFlag = piFeeFlag;
	}



	public String getPiIndiBuyFlag() {
		return piIndiBuyFlag;
	}



	public void setPiIndiBuyFlag(String piIndiBuyFlag) {
		this.piIndiBuyFlag = piIndiBuyFlag;
	}



	public String getPiInstBuyFlag() {
		return piInstBuyFlag;
	}



	public void setPiInstBuyFlag(String piInstBuyFlag) {
		this.piInstBuyFlag = piInstBuyFlag;
	}



	public String getPiProCfmWay() {
		return piProCfmWay;
	}



	public void setPiProCfmWay(String piProCfmWay) {
		this.piProCfmWay = piProCfmWay;
	}



	public String getPiCheckState() {
		return piCheckState;
	}



	public void setPiCheckState(String piCheckState) {
		this.piCheckState = piCheckState;
	}



	public Date getPiCtime() {
		return piCtime;
	}



	public void setPiCtime(Date piCtime) {
		this.piCtime = piCtime;
	}



	public Date getPiUtime() {
		return piUtime;
	}



	public void setPiUtime(Date piUtime) {
		this.piUtime = piUtime;
	}



	public String getPiCuser() {
		return piCuser;
	}



	public void setPiCuser(String piCuser) {
		this.piCuser = piCuser;
	}



	public String getPiUuser() {
		return piUuser;
	}



	public void setPiUuser(String piUuser) {
		this.piUuser = piUuser;
	}



	public String getPiCurrencyType() {
		return piCurrencyType;
	}



	public void setPiCurrencyType(String piCurrencyType) {
		this.piCurrencyType = piCurrencyType;
	}



	public String getPiAnnuCalDays() {
		return piAnnuCalDays;
	}



	public void setPiAnnuCalDays(String piAnnuCalDays) {
		this.piAnnuCalDays = piAnnuCalDays;
	}



	public String getPiOtherFileType() {
		return piOtherFileType;
	}


	public void setPiOtherFileType(String piOtherFileType) {
		this.piOtherFileType = piOtherFileType;
	}

	public String getPiProductTotalShare() {
		return piProductTotalShare;
	}

	public void setPiProductTotalShare(String piProductTotalShare) {
		this.piProductTotalShare = piProductTotalShare;
	}
	
	public String getCiCheckState() {
		return ciCheckState;
	}

	public void setCiCheckState(String ciCheckState) {
		this.ciCheckState = ciCheckState;
	}



	public String getPiValidFlag() {
		return piValidFlag;
	}



	public void setPiValidFlag(String piValidFlag) {
		this.piValidFlag = piValidFlag;
	}



	public String getIsTradeDateFlag() {
		return isTradeDateFlag;
	}



	public void setIsTradeDateFlag(String isTradeDateFlag) {
		this.isTradeDateFlag = isTradeDateFlag;
	}

	public String getPiBatchNumber() {
		return piBatchNumber;
	}



	public void setPiBatchNumber(String piBatchNumber) {
		this.piBatchNumber = piBatchNumber;
	}



	public String getCiSalesPerson() {
		return ciSalesPerson;
	}



	public void setCiSalesPerson(String ciSalesPerson) {
		this.ciSalesPerson = ciSalesPerson;
	}



	public String getCiBatchNoOnOff() {
		return ciBatchNoOnOff;
	}



	public void setCiBatchNoOnOff(String ciBatchNoOnOff) {
		this.ciBatchNoOnOff = ciBatchNoOnOff;
	}


	public String getPiChannelCode() {
		return piChannelCode;
	}

	public void setPiChannelCode(String piChannelCode) {
		this.piChannelCode = piChannelCode;
	}


	public String getSumRemainshares() {
		return sumRemainshares;
	}



	public void setSumRemainshares(String sumRemainshares) {
		this.sumRemainshares = sumRemainshares;
	}


	public String getCpTaProductCode() {
		return cpTaProductCode;
	}

	public void setCpTaProductCode(String cpTaProductCode) {
		this.cpTaProductCode = cpTaProductCode;
	}

	public String getPiSubChannelCode() {
		return piSubChannelCode;
	}

	public void setPiSubChannelCode(String piSubChannelCode) {
		this.piSubChannelCode = piSubChannelCode;
	}

	public FundMarketCustom() {
		super();
	}



	public FundMarketCustom(String ciChannelCode, String ciChannelName, String ciSaleAgentCode, String ciCheckState,
			String ciMarketCode, String ciCsdcVer, String ciSalesPerson, String ciCheckAcctType,
			String ciEconVerifyType, String ciSztRecvPath, String ciSztSendPath, String ciPerAcctType,
			String ciOrgAcctType, String ciValidFlag, String ciDealFile, String ciBatchNoOnOff, String piProductType,
			String piChannelProductCode, String piChannelProductName, BigDecimal piIndiMinBidsAmt,
			BigDecimal piInstMinBidsAmt, BigDecimal piIndiBidsDiffAmt, BigDecimal piInstBidsDiffAmt,
			BigDecimal piIndiMaxBidsAmt, BigDecimal piInstMaxBidsAmt, BigDecimal piIndiMinAppBidsAmt,
			BigDecimal piInstMinAppBidsAmt, BigDecimal piIndiAppBidsDiffAmt, BigDecimal piInstAppBidsDiffAmt,
			BigDecimal piIndiMaxAppBidsAmt, BigDecimal piInstMaxAppBidsAmt, BigDecimal piIndiMinRedeemVol,
			BigDecimal piInstMinRedeemVol, BigDecimal piIndiRedeemDiff, BigDecimal piInstRedeemDiff,
			BigDecimal piIndiMaxVol, BigDecimal piInstMaxVol, BigDecimal piIndiMinVol, BigDecimal piInstMinVol,
			BigDecimal piChannelRate, String piPayType, String piIpoBeginDate, String piIpoEndDate,
			String piProSetupDate, String piProEndDate, String piProYieldType, BigDecimal piProYield, String piFeeFlag,
			String piIndiBuyFlag, String piInstBuyFlag, String piProCfmWay, String piCheckState,
			BigDecimal piMaxAmountRaised, Date piCtime, Date piUtime, String piCuser, String piUuser,
			String piCurrencyType, String piAnnuCalDays, String piOtherFileType, String fundStatus,
			String piProductTotalShare, String isTradeDateFlag, String piValidFlag, String piBatchNumber,String piSubChannelCode) {
		super();
		this.ciChannelCode = ciChannelCode;
		this.ciChannelName = ciChannelName;
		this.ciSaleAgentCode = ciSaleAgentCode;
		this.ciCheckState = ciCheckState;
		this.ciMarketCode = ciMarketCode;
		this.ciCsdcVer = ciCsdcVer;
		this.ciSalesPerson = ciSalesPerson;
		this.ciCheckAcctType = ciCheckAcctType;
		this.ciEconVerifyType = ciEconVerifyType;
		this.ciSztRecvPath = ciSztRecvPath;
		this.ciSztSendPath = ciSztSendPath;
		this.ciPerAcctType = ciPerAcctType;
		this.ciOrgAcctType = ciOrgAcctType;
		this.ciValidFlag = ciValidFlag;
		this.ciDealFile = ciDealFile;
		this.ciBatchNoOnOff = ciBatchNoOnOff;
		this.piProductType = piProductType;
		this.piChannelProductCode = piChannelProductCode;
		this.piChannelProductName = piChannelProductName;
		this.piIndiMinBidsAmt = piIndiMinBidsAmt;
		this.piInstMinBidsAmt = piInstMinBidsAmt;
		this.piIndiBidsDiffAmt = piIndiBidsDiffAmt;
		this.piInstBidsDiffAmt = piInstBidsDiffAmt;
		this.piIndiMaxBidsAmt = piIndiMaxBidsAmt;
		this.piInstMaxBidsAmt = piInstMaxBidsAmt;
		this.piIndiMinAppBidsAmt = piIndiMinAppBidsAmt;
		this.piInstMinAppBidsAmt = piInstMinAppBidsAmt;
		this.piIndiAppBidsDiffAmt = piIndiAppBidsDiffAmt;
		this.piInstAppBidsDiffAmt = piInstAppBidsDiffAmt;
		this.piIndiMaxAppBidsAmt = piIndiMaxAppBidsAmt;
		this.piInstMaxAppBidsAmt = piInstMaxAppBidsAmt;
		this.piIndiMinRedeemVol = piIndiMinRedeemVol;
		this.piInstMinRedeemVol = piInstMinRedeemVol;
		this.piIndiRedeemDiff = piIndiRedeemDiff;
		this.piInstRedeemDiff = piInstRedeemDiff;
		this.piIndiMaxVol = piIndiMaxVol;
		this.piInstMaxVol = piInstMaxVol;
		this.piIndiMinVol = piIndiMinVol;
		this.piInstMinVol = piInstMinVol;
		this.piChannelRate = piChannelRate;
		this.piPayType = piPayType;
		this.piIpoBeginDate = piIpoBeginDate;
		this.piIpoEndDate = piIpoEndDate;
		this.piProSetupDate = piProSetupDate;
		this.piProEndDate = piProEndDate;
		this.piProYieldType = piProYieldType;
		this.piProYield = piProYield;
		this.piFeeFlag = piFeeFlag;
		this.piIndiBuyFlag = piIndiBuyFlag;
		this.piInstBuyFlag = piInstBuyFlag;
		this.piProCfmWay = piProCfmWay;
		this.piCheckState = piCheckState;
		this.piMaxAmountRaised = piMaxAmountRaised;
		this.piCtime = piCtime;
		this.piUtime = piUtime;
		this.piCuser = piCuser;
		this.piUuser = piUuser;
		this.piCurrencyType = piCurrencyType;
		this.piAnnuCalDays = piAnnuCalDays;
		this.piOtherFileType = piOtherFileType;
		this.fundStatus = fundStatus;
		this.piProductTotalShare = piProductTotalShare;
		this.isTradeDateFlag = isTradeDateFlag;
		this.piValidFlag = piValidFlag;
		this.piBatchNumber = piBatchNumber;
		this.piSubChannelCode = piSubChannelCode;
	}



	@Override
	public String toString() {
		return "FundMarketCustom [ciChannelCode=" + ciChannelCode + ", ciChannelName=" + ciChannelName
				+ ", ciSaleAgentCode=" + ciSaleAgentCode + ", ciCheckState=" + ciCheckState + ", ciMarketCode="
				+ ciMarketCode + ", ciCsdcVer=" + ciCsdcVer + ", ciSalesPerson=" + ciSalesPerson + ", ciCheckAcctType="
				+ ciCheckAcctType + ", ciEconVerifyType=" + ciEconVerifyType + ", ciSztRecvPath=" + ciSztRecvPath
				+ ", ciSztSendPath=" + ciSztSendPath + ", ciPerAcctType=" + ciPerAcctType + ", ciOrgAcctType="
				+ ciOrgAcctType + ", ciValidFlag=" + ciValidFlag + ", ciDealFile=" + ciDealFile + ", ciBatchNoOnOff="
				+ ciBatchNoOnOff + ", piProductType=" + piProductType + ", piChannelProductCode=" + piChannelProductCode
				+ ", piChannelProductName=" + piChannelProductName + ", piIndiMinBidsAmt=" + piIndiMinBidsAmt
				+ ", piInstMinBidsAmt=" + piInstMinBidsAmt + ", piIndiBidsDiffAmt=" + piIndiBidsDiffAmt
				+ ", piInstBidsDiffAmt=" + piInstBidsDiffAmt + ", piIndiMaxBidsAmt=" + piIndiMaxBidsAmt
				+ ", piInstMaxBidsAmt=" + piInstMaxBidsAmt + ", piIndiMinAppBidsAmt=" + piIndiMinAppBidsAmt
				+ ", piInstMinAppBidsAmt=" + piInstMinAppBidsAmt + ", piIndiAppBidsDiffAmt=" + piIndiAppBidsDiffAmt
				+ ", piInstAppBidsDiffAmt=" + piInstAppBidsDiffAmt + ", piIndiMaxAppBidsAmt=" + piIndiMaxAppBidsAmt
				+ ", piInstMaxAppBidsAmt=" + piInstMaxAppBidsAmt + ", piIndiMinRedeemVol=" + piIndiMinRedeemVol
				+ ", piInstMinRedeemVol=" + piInstMinRedeemVol + ", piIndiRedeemDiff=" + piIndiRedeemDiff
				+ ", piInstRedeemDiff=" + piInstRedeemDiff + ", piIndiMaxVol=" + piIndiMaxVol + ", piInstMaxVol="
				+ piInstMaxVol + ", piIndiMinVol=" + piIndiMinVol + ", piInstMinVol=" + piInstMinVol
				+ ", piChannelRate=" + piChannelRate + ", piPayType=" + piPayType + ", piIpoBeginDate=" + piIpoBeginDate
				+ ", piIpoEndDate=" + piIpoEndDate + ", piProSetupDate=" + piProSetupDate + ", piProEndDate="
				+ piProEndDate + ", piProYieldType=" + piProYieldType + ", piProYield=" + piProYield + ", piFeeFlag="
				+ piFeeFlag + ", piIndiBuyFlag=" + piIndiBuyFlag + ", piInstBuyFlag=" + piInstBuyFlag + ", piProCfmWay="
				+ piProCfmWay + ", piCheckState=" + piCheckState + ", piMaxAmountRaised=" + piMaxAmountRaised
				+ ", piCtime=" + piCtime + ", piUtime=" + piUtime + ", piCuser=" + piCuser + ", piUuser=" + piUuser
				+ ", piCurrencyType=" + piCurrencyType + ", piAnnuCalDays=" + piAnnuCalDays + ", piOtherFileType="
				+ piOtherFileType + ", fundStatus=" + fundStatus + ", piProductTotalShare=" + piProductTotalShare
				+ ", isTradeDateFlag=" + isTradeDateFlag + ", piValidFlag=" + piValidFlag + ", piBatchNumber="
				+ piBatchNumber + ", piChannelCode=" + piChannelCode + ", sumRemainshares=" + sumRemainshares
				+ ", cpTaProductCode=" + cpTaProductCode + ", piSubChannelCode=" + piSubChannelCode + "]";
	}

}