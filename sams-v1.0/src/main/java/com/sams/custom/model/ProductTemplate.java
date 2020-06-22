package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductTemplate implements Serializable {
    private Long ptId;

    private String ptTemplateCode;

    private String ptTemplateName;

    private String ptProductCode;

    private String ptIndividualOrInstitution;

    private BigDecimal ptMinBidsAmt;

    private BigDecimal ptBidsDiffAmt;

    private BigDecimal ptMaxBidsAmt;

    private BigDecimal ptMinAppBidsAmt;

    private BigDecimal ptAppBidsDiffAmt;

    private BigDecimal ptMaxAppBidsAmt;

    private BigDecimal ptMinRedeemVol;

    private BigDecimal ptRedeemDiff;

    private BigDecimal ptMaxVol;

    private BigDecimal ptMinVol;

    private BigDecimal ptChannelRate;

    private String ptPayType;

    private String ptIpoBeginDate;

    private String ptIpoEndDate;

    private String ptProSetupDate;

    private String ptProEndDate;

    private String ptProYieldType;

    private BigDecimal ptProYield;

    private String ptFeeFlag;

    private String ptIndiBuyFlag;

    private String ptInstBuyFlag;

    private String ptProCfmWay;

    private String ptAnnuCalDays;

    private BigDecimal ptMaxAmountRaised;

    private BigDecimal ptMinAmountRaised;

    private Date ptCtime;

    private Date ptUtime;

    private String ptCuser;

    private String ptUuser;

    private String ptCheckState;
    
    private String ptCurrency;
    
    private String ptAction;
    
    private String ptValidFlag;
    
    private String ptProCalculateType;
    
    private String ptRowNumber;
    
    private String productTypeName;
    
    private static final long serialVersionUID = 1L;

    public String getPtValidFlag() {
		return ptValidFlag;
	}

	public void setPtValidFlag(String ptValidFlag) {
		this.ptValidFlag = ptValidFlag;
	}

	public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public String getPtTemplateCode() {
        return ptTemplateCode;
    }

    public void setPtTemplateCode(String ptTemplateCode) {
        this.ptTemplateCode = ptTemplateCode == null ? null : ptTemplateCode.trim();
    }

    public String getPtTemplateName() {
        return ptTemplateName;
    }

    public void setPtTemplateName(String ptTemplateName) {
        this.ptTemplateName = ptTemplateName == null ? null : ptTemplateName.trim();
    }

    public String getPtProductCode() {
        return ptProductCode;
    }

    public void setPtProductCode(String ptProductCode) {
        this.ptProductCode = ptProductCode == null ? null : ptProductCode.trim();
    }

    public String getPtIndividualOrInstitution() {
        return ptIndividualOrInstitution;
    }

    public void setPtIndividualOrInstitution(String ptIndividualOrInstitution) {
        this.ptIndividualOrInstitution = ptIndividualOrInstitution == null ? null : ptIndividualOrInstitution.trim();
    }

    public BigDecimal getPtMinBidsAmt() {
        return ptMinBidsAmt;
    }

    public void setPtMinBidsAmt(BigDecimal ptMinBidsAmt) {
        this.ptMinBidsAmt = ptMinBidsAmt;
    }

    public BigDecimal getPtBidsDiffAmt() {
        return ptBidsDiffAmt;
    }

    public void setPtBidsDiffAmt(BigDecimal ptBidsDiffAmt) {
        this.ptBidsDiffAmt = ptBidsDiffAmt;
    }

    public BigDecimal getPtMaxBidsAmt() {
        return ptMaxBidsAmt;
    }

    public void setPtMaxBidsAmt(BigDecimal ptMaxBidsAmt) {
        this.ptMaxBidsAmt = ptMaxBidsAmt;
    }

    public BigDecimal getPtMinAppBidsAmt() {
        return ptMinAppBidsAmt;
    }

    public void setPtMinAppBidsAmt(BigDecimal ptMinAppBidsAmt) {
        this.ptMinAppBidsAmt = ptMinAppBidsAmt;
    }

    public BigDecimal getPtAppBidsDiffAmt() {
        return ptAppBidsDiffAmt;
    }

    public void setPtAppBidsDiffAmt(BigDecimal ptAppBidsDiffAmt) {
        this.ptAppBidsDiffAmt = ptAppBidsDiffAmt;
    }

    public BigDecimal getPtMaxAppBidsAmt() {
        return ptMaxAppBidsAmt;
    }

    public void setPtMaxAppBidsAmt(BigDecimal ptMaxAppBidsAmt) {
        this.ptMaxAppBidsAmt = ptMaxAppBidsAmt;
    }

    public BigDecimal getPtMinRedeemVol() {
        return ptMinRedeemVol;
    }

    public void setPtMinRedeemVol(BigDecimal ptMinRedeemVol) {
        this.ptMinRedeemVol = ptMinRedeemVol;
    }

    public BigDecimal getPtRedeemDiff() {
        return ptRedeemDiff;
    }

    public void setPtRedeemDiff(BigDecimal ptRedeemDiff) {
        this.ptRedeemDiff = ptRedeemDiff;
    }

    public BigDecimal getPtMaxVol() {
        return ptMaxVol;
    }

    public void setPtMaxVol(BigDecimal ptMaxVol) {
        this.ptMaxVol = ptMaxVol;
    }

    public BigDecimal getPtMinVol() {
        return ptMinVol;
    }

    public void setPtMinVol(BigDecimal ptMinVol) {
        this.ptMinVol = ptMinVol;
    }

    public BigDecimal getPtChannelRate() {
        return ptChannelRate;
    }

    public void setPtChannelRate(BigDecimal ptChannelRate) {
        this.ptChannelRate = ptChannelRate;
    }

    public String getPtPayType() {
        return ptPayType;
    }

    public void setPtPayType(String ptPayType) {
        this.ptPayType = ptPayType == null ? null : ptPayType.trim();
    }

    public String getPtIpoBeginDate() {
        return ptIpoBeginDate;
    }

    public void setPtIpoBeginDate(String ptIpoBeginDate) {
        this.ptIpoBeginDate = ptIpoBeginDate == null ? null : ptIpoBeginDate.trim();
    }

    public String getPtIpoEndDate() {
        return ptIpoEndDate;
    }

    public void setPtIpoEndDate(String ptIpoEndDate) {
        this.ptIpoEndDate = ptIpoEndDate == null ? null : ptIpoEndDate.trim();
    }

    public String getPtProSetupDate() {
        return ptProSetupDate;
    }

    public void setPtProSetupDate(String ptProSetupDate) {
        this.ptProSetupDate = ptProSetupDate == null ? null : ptProSetupDate.trim();
    }

    public String getPtProEndDate() {
        return ptProEndDate;
    }

    public void setPtProEndDate(String ptProEndDate) {
        this.ptProEndDate = ptProEndDate == null ? null : ptProEndDate.trim();
    }

    public String getPtProYieldType() {
        return ptProYieldType;
    }

    public void setPtProYieldType(String ptProYieldType) {
        this.ptProYieldType = ptProYieldType == null ? null : ptProYieldType.trim();
    }

    public BigDecimal getPtProYield() {
        return ptProYield;
    }

    public void setPtProYield(BigDecimal ptProYield) {
        this.ptProYield = ptProYield;
    }

    public String getPtFeeFlag() {
        return ptFeeFlag;
    }

    public void setPtFeeFlag(String ptFeeFlag) {
        this.ptFeeFlag = ptFeeFlag == null ? null : ptFeeFlag.trim();
    }

    public String getPtIndiBuyFlag() {
        return ptIndiBuyFlag;
    }

    public void setPtIndiBuyFlag(String ptIndiBuyFlag) {
        this.ptIndiBuyFlag = ptIndiBuyFlag == null ? null : ptIndiBuyFlag.trim();
    }

    public String getPtInstBuyFlag() {
        return ptInstBuyFlag;
    }

    public void setPtInstBuyFlag(String ptInstBuyFlag) {
        this.ptInstBuyFlag = ptInstBuyFlag == null ? null : ptInstBuyFlag.trim();
    }

    public String getPtProCfmWay() {
        return ptProCfmWay;
    }

    public void setPtProCfmWay(String ptProCfmWay) {
        this.ptProCfmWay = ptProCfmWay == null ? null : ptProCfmWay.trim();
    }

    public String getPtAnnuCalDays() {
        return ptAnnuCalDays;
    }

    public void setPtAnnuCalDays(String ptAnnuCalDays) {
        this.ptAnnuCalDays = ptAnnuCalDays == null ? null : ptAnnuCalDays.trim();
    }

    public BigDecimal getPtMaxAmountRaised() {
        return ptMaxAmountRaised;
    }

    public void setPtMaxAmountRaised(BigDecimal ptMaxAmountRaised) {
        this.ptMaxAmountRaised = ptMaxAmountRaised;
    }

    public BigDecimal getPtMinAmountRaised() {
        return ptMinAmountRaised;
    }

    public void setPtMinAmountRaised(BigDecimal ptMinAmountRaised) {
        this.ptMinAmountRaised = ptMinAmountRaised;
    }

    public Date getPtCtime() {
        return ptCtime;
    }

    public void setPtCtime(Date ptCtime) {
        this.ptCtime = ptCtime;
    }

    public Date getPtUtime() {
        return ptUtime;
    }

    public void setPtUtime(Date ptUtime) {
        this.ptUtime = ptUtime;
    }

    public String getPtCuser() {
        return ptCuser;
    }

    public void setPtCuser(String ptCuser) {
        this.ptCuser = ptCuser == null ? null : ptCuser.trim();
    }

    public String getPtUuser() {
        return ptUuser;
    }

    public void setPtUuser(String ptUuser) {
        this.ptUuser = ptUuser == null ? null : ptUuser.trim();
    }

    public String getPtCheckState() {
        return ptCheckState;
    }

    public void setPtCheckState(String ptCheckState) {
        this.ptCheckState = ptCheckState == null ? null : ptCheckState.trim();
    }

    public String getPtCurrency() {
		return ptCurrency;
	}

	public void setPtCurrency(String ptCurrency) {
		this.ptCurrency = ptCurrency;
	}

	public String getPtAction() {
		return ptAction;
	}

	public void setPtAction(String ptAction) {
		this.ptAction = ptAction;
	}

	public String getPtProCalculateType() {
		return ptProCalculateType;
	}

	public void setPtProCalculateType(String ptProCalculateType) {
		this.ptProCalculateType = ptProCalculateType;
	}

	public String getPtRowNumber() {
		return ptRowNumber;
	}

	public void setPtRowNumber(String ptRowNumber) {
		this.ptRowNumber = ptRowNumber;
	}
	
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public ProductTemplate() {
		super();
	}

	public ProductTemplate(Long ptId, String ptTemplateCode, String ptTemplateName, String ptProductCode,
			String ptIndividualOrInstitution, BigDecimal ptMinBidsAmt, BigDecimal ptBidsDiffAmt,
			BigDecimal ptMaxBidsAmt, BigDecimal ptMinAppBidsAmt, BigDecimal ptAppBidsDiffAmt,
			BigDecimal ptMaxAppBidsAmt, BigDecimal ptMinRedeemVol, BigDecimal ptRedeemDiff, BigDecimal ptMaxVol,
			BigDecimal ptMinVol, BigDecimal ptChannelRate, String ptPayType, String ptIpoBeginDate, String ptIpoEndDate,
			String ptProSetupDate, String ptProEndDate, String ptProYieldType, BigDecimal ptProYield, String ptFeeFlag,
			String ptIndiBuyFlag, String ptInstBuyFlag, String ptProCfmWay, String ptAnnuCalDays,
			BigDecimal ptMaxAmountRaised, BigDecimal ptMinAmountRaised, Date ptCtime, Date ptUtime, String ptCuser,
			String ptUuser, String ptCheckState, String ptCurrency, String ptAction, String ptValidFlag,String productTypeName){
		super();
		this.ptId = ptId;
		this.ptTemplateCode = ptTemplateCode;
		this.ptTemplateName = ptTemplateName;
		this.ptProductCode = ptProductCode;
		this.ptIndividualOrInstitution = ptIndividualOrInstitution;
		this.ptMinBidsAmt = ptMinBidsAmt;
		this.ptBidsDiffAmt = ptBidsDiffAmt;
		this.ptMaxBidsAmt = ptMaxBidsAmt;
		this.ptMinAppBidsAmt = ptMinAppBidsAmt;
		this.ptAppBidsDiffAmt = ptAppBidsDiffAmt;
		this.ptMaxAppBidsAmt = ptMaxAppBidsAmt;
		this.ptMinRedeemVol = ptMinRedeemVol;
		this.ptRedeemDiff = ptRedeemDiff;
		this.ptMaxVol = ptMaxVol;
		this.ptMinVol = ptMinVol;
		this.ptChannelRate = ptChannelRate;
		this.ptPayType = ptPayType;
		this.ptIpoBeginDate = ptIpoBeginDate;
		this.ptIpoEndDate = ptIpoEndDate;
		this.ptProSetupDate = ptProSetupDate;
		this.ptProEndDate = ptProEndDate;
		this.ptProYieldType = ptProYieldType;
		this.ptProYield = ptProYield;
		this.ptFeeFlag = ptFeeFlag;
		this.ptIndiBuyFlag = ptIndiBuyFlag;
		this.ptInstBuyFlag = ptInstBuyFlag;
		this.ptProCfmWay = ptProCfmWay;
		this.ptAnnuCalDays = ptAnnuCalDays;
		this.ptMaxAmountRaised = ptMaxAmountRaised;
		this.ptMinAmountRaised = ptMinAmountRaised;
		this.ptCtime = ptCtime;
		this.ptUtime = ptUtime;
		this.ptCuser = ptCuser;
		this.ptUuser = ptUuser;
		this.ptCheckState = ptCheckState;
		this.ptCurrency = ptCurrency;
		this.ptAction = ptAction;
		this.ptValidFlag = ptValidFlag;
		this.ptProCalculateType = ptProCalculateType;
		this.productTypeName = productTypeName;
	}

	@Override
	public String toString() {
		return "ProductTemplate [ptId=" + ptId + ", ptTemplateCode=" + ptTemplateCode + ", ptTemplateName="
				+ ptTemplateName + ", ptProductCode=" + ptProductCode + ", ptIndividualOrInstitution="
				+ ptIndividualOrInstitution + ", ptMinBidsAmt=" + ptMinBidsAmt + ", ptBidsDiffAmt=" + ptBidsDiffAmt
				+ ", ptMaxBidsAmt=" + ptMaxBidsAmt + ", ptMinAppBidsAmt=" + ptMinAppBidsAmt + ", ptAppBidsDiffAmt="
				+ ptAppBidsDiffAmt + ", ptMaxAppBidsAmt=" + ptMaxAppBidsAmt + ", ptMinRedeemVol=" + ptMinRedeemVol
				+ ", ptRedeemDiff=" + ptRedeemDiff + ", ptMaxVol=" + ptMaxVol + ", ptMinVol=" + ptMinVol
				+ ", ptChannelRate=" + ptChannelRate + ", ptPayType=" + ptPayType + ", ptIpoBeginDate=" + ptIpoBeginDate
				+ ", ptIpoEndDate=" + ptIpoEndDate + ", ptProSetupDate=" + ptProSetupDate + ", ptProEndDate="
				+ ptProEndDate + ", ptProYieldType=" + ptProYieldType + ", ptProYield=" + ptProYield + ", ptFeeFlag="
				+ ptFeeFlag + ", ptIndiBuyFlag=" + ptIndiBuyFlag + ", ptInstBuyFlag=" + ptInstBuyFlag + ", ptProCfmWay="
				+ ptProCfmWay + ", ptAnnuCalDays=" + ptAnnuCalDays + ", ptMaxAmountRaised=" + ptMaxAmountRaised
				+ ", ptMinAmountRaised=" + ptMinAmountRaised + ", ptCtime=" + ptCtime + ", ptUtime=" + ptUtime
				+ ", ptCuser=" + ptCuser + ", ptUuser=" + ptUuser + ", ptCheckState=" + ptCheckState + ", ptCurrency="
				+ ptCurrency + ", ptAction=" + ptAction + ", ptValidFlag=" + ptValidFlag + ", ptProCalculateType="
				+ ptProCalculateType + ", productTypeName="+productTypeName+"]";
	}

}