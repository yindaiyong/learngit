package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductSalePrarms implements Serializable {
    private BigDecimal pspId;

    private String pspChannelCode;

    private String pspChannelProductCode;

    private BigDecimal pspIndiMinBidsAmt;

    private BigDecimal pspInstMinBidsAmt;

    private BigDecimal pspIndiBidsDiffAmt;

    private BigDecimal pspInstBidsDiffAmt;

    private BigDecimal pspIndiMaxBidsAmt;

    private BigDecimal pspInstMaxBidsAmt;

    private BigDecimal pspIndiMinAppBidsAmt;

    private BigDecimal pspInstMinAppBidsAmt;

    private BigDecimal pspIndiAppBidsDiffAmt;

    private BigDecimal pspInstAppBidsDiffAmt;

    private BigDecimal pspIndiMaxAppBidsAmt;

    private BigDecimal pspInstMaxAppBidsAmt;
    
    private BigDecimal pspIndiMaxVol;
    
    private BigDecimal pspInstMaxVol;
    
    private String pspPayType;

    private String pspIndiBuyFlag;

    private String pspInstBuyFlag;

    private String pspCurrencyType;
    
    private String pspheckFlag;

    private Date pspCtime;

    private Date pspUtime;

    private String pspCuser;

    private String pspUuser;
    
    private String pspValidFlag;
    
    private String pspCheckFlag;

    private static final long serialVersionUID = 1L;

	public ProductSalePrarms() {
		super();
	}

	public ProductSalePrarms(BigDecimal pspId, String pspChannelCode, String pspChannelProductCode,
			BigDecimal pspIndiMinBidsAmt, BigDecimal pspInstMinBidsAmt, BigDecimal pspIndiBidsDiffAmt,
			BigDecimal pspInstBidsDiffAmt, BigDecimal pspIndiMaxBidsAmt, BigDecimal pspInstMaxBidsAmt,
			BigDecimal pspIndiMinAppBidsAmt, BigDecimal pspInstMinAppBidsAmt, BigDecimal pspIndiAppBidsDiffAmt,
			BigDecimal pspInstAppBidsDiffAmt, BigDecimal pspIndiMaxAppBidsAmt, BigDecimal pspInstMaxAppBidsAmt,
			BigDecimal pspIndiMaxVol, BigDecimal pspInstMaxVol, String pspPayType, String pspIndiBuyFlag,
			String pspInstBuyFlag, String pspCurrencyType, String pspheckFlag, Date pspCtime, Date pspUtime,
			String pspCuser, String pspUuser, String pspValidFlag, String pspCheckFlag) {
		super();
		this.pspId = pspId;
		this.pspChannelCode = pspChannelCode;
		this.pspChannelProductCode = pspChannelProductCode;
		this.pspIndiMinBidsAmt = pspIndiMinBidsAmt;
		this.pspInstMinBidsAmt = pspInstMinBidsAmt;
		this.pspIndiBidsDiffAmt = pspIndiBidsDiffAmt;
		this.pspInstBidsDiffAmt = pspInstBidsDiffAmt;
		this.pspIndiMaxBidsAmt = pspIndiMaxBidsAmt;
		this.pspInstMaxBidsAmt = pspInstMaxBidsAmt;
		this.pspIndiMinAppBidsAmt = pspIndiMinAppBidsAmt;
		this.pspInstMinAppBidsAmt = pspInstMinAppBidsAmt;
		this.pspIndiAppBidsDiffAmt = pspIndiAppBidsDiffAmt;
		this.pspInstAppBidsDiffAmt = pspInstAppBidsDiffAmt;
		this.pspIndiMaxAppBidsAmt = pspIndiMaxAppBidsAmt;
		this.pspInstMaxAppBidsAmt = pspInstMaxAppBidsAmt;
		this.pspIndiMaxVol = pspIndiMaxVol;
		this.pspInstMaxVol = pspInstMaxVol;
		this.pspPayType = pspPayType;
		this.pspIndiBuyFlag = pspIndiBuyFlag;
		this.pspInstBuyFlag = pspInstBuyFlag;
		this.pspCurrencyType = pspCurrencyType;
		this.pspheckFlag = pspheckFlag;
		this.pspCtime = pspCtime;
		this.pspUtime = pspUtime;
		this.pspCuser = pspCuser;
		this.pspUuser = pspUuser;
		this.pspValidFlag = pspValidFlag;
		this.pspCheckFlag = pspCheckFlag;
	}




	public BigDecimal getPspIndiMaxVol() {
		return pspIndiMaxVol;
	}


	public void setPspIndiMaxVol(BigDecimal pspIndiMaxVol) {
		this.pspIndiMaxVol = pspIndiMaxVol;
	}


	public BigDecimal getPspInstMaxVol() {
		return pspInstMaxVol;
	}


	public void setPspInstMaxVol(BigDecimal pspInstMaxVol) {
		this.pspInstMaxVol = pspInstMaxVol;
	}


	public BigDecimal getPspId() {
		return pspId;
	}

	public void setPspId(BigDecimal pspId) {
		this.pspId = pspId;
	}

	public String getPspChannelCode() {
		return pspChannelCode;
	}

	public void setPspChannelCode(String pspChannelCode) {
		this.pspChannelCode = pspChannelCode;
	}

	public String getPspChannelProductCode() {
		return pspChannelProductCode;
	}

	public void setPspChannelProductCode(String pspChannelProductCode) {
		this.pspChannelProductCode = pspChannelProductCode;
	}

	public BigDecimal getPspIndiMinBidsAmt() {
		return pspIndiMinBidsAmt;
	}

	public void setPspIndiMinBidsAmt(BigDecimal pspIndiMinBidsAmt) {
		this.pspIndiMinBidsAmt = pspIndiMinBidsAmt;
	}

	public BigDecimal getPspInstMinBidsAmt() {
		return pspInstMinBidsAmt;
	}

	public void setPspInstMinBidsAmt(BigDecimal pspInstMinBidsAmt) {
		this.pspInstMinBidsAmt = pspInstMinBidsAmt;
	}

	public BigDecimal getPspIndiBidsDiffAmt() {
		return pspIndiBidsDiffAmt;
	}

	public void setPspIndiBidsDiffAmt(BigDecimal pspIndiBidsDiffAmt) {
		this.pspIndiBidsDiffAmt = pspIndiBidsDiffAmt;
	}

	public BigDecimal getPspInstBidsDiffAmt() {
		return pspInstBidsDiffAmt;
	}

	public void setPspInstBidsDiffAmt(BigDecimal pspInstBidsDiffAmt) {
		this.pspInstBidsDiffAmt = pspInstBidsDiffAmt;
	}

	public BigDecimal getPspIndiMaxBidsAmt() {
		return pspIndiMaxBidsAmt;
	}

	public void setPspIndiMaxBidsAmt(BigDecimal pspIndiMaxBidsAmt) {
		this.pspIndiMaxBidsAmt = pspIndiMaxBidsAmt;
	}

	public BigDecimal getPspInstMaxBidsAmt() {
		return pspInstMaxBidsAmt;
	}

	public void setPspInstMaxBidsAmt(BigDecimal pspInstMaxBidsAmt) {
		this.pspInstMaxBidsAmt = pspInstMaxBidsAmt;
	}

	public BigDecimal getPspIndiMinAppBidsAmt() {
		return pspIndiMinAppBidsAmt;
	}

	public void setPspIndiMinAppBidsAmt(BigDecimal pspIndiMinAppBidsAmt) {
		this.pspIndiMinAppBidsAmt = pspIndiMinAppBidsAmt;
	}

	public BigDecimal getPspInstMinAppBidsAmt() {
		return pspInstMinAppBidsAmt;
	}

	public void setPspInstMinAppBidsAmt(BigDecimal pspInstMinAppBidsAmt) {
		this.pspInstMinAppBidsAmt = pspInstMinAppBidsAmt;
	}

	public BigDecimal getPspIndiAppBidsDiffAmt() {
		return pspIndiAppBidsDiffAmt;
	}

	public void setPspIndiAppBidsDiffAmt(BigDecimal pspIndiAppBidsDiffAmt) {
		this.pspIndiAppBidsDiffAmt = pspIndiAppBidsDiffAmt;
	}

	public BigDecimal getPspInstAppBidsDiffAmt() {
		return pspInstAppBidsDiffAmt;
	}

	public void setPspInstAppBidsDiffAmt(BigDecimal pspInstAppBidsDiffAmt) {
		this.pspInstAppBidsDiffAmt = pspInstAppBidsDiffAmt;
	}

	public BigDecimal getPspIndiMaxAppBidsAmt() {
		return pspIndiMaxAppBidsAmt;
	}

	public void setPspIndiMaxAppBidsAmt(BigDecimal pspIndiMaxAppBidsAmt) {
		this.pspIndiMaxAppBidsAmt = pspIndiMaxAppBidsAmt;
	}

	public BigDecimal getPspInstMaxAppBidsAmt() {
		return pspInstMaxAppBidsAmt;
	}

	public void setPspInstMaxAppBidsAmt(BigDecimal pspInstMaxAppBidsAmt) {
		this.pspInstMaxAppBidsAmt = pspInstMaxAppBidsAmt;
	}

	

	public String getPspPayType() {
		return pspPayType;
	}

	public void setPspPayType(String pspPayType) {
		this.pspPayType = pspPayType;
	}

	public String getPspIndiBuyFlag() {
		return pspIndiBuyFlag;
	}

	public void setPspIndiBuyFlag(String pspIndiBuyFlag) {
		this.pspIndiBuyFlag = pspIndiBuyFlag;
	}

	public String getPspInstBuyFlag() {
		return pspInstBuyFlag;
	}

	public void setPspInstBuyFlag(String pspInstBuyFlag) {
		this.pspInstBuyFlag = pspInstBuyFlag;
	}

	public String getPspCurrencyType() {
		return pspCurrencyType;
	}

	public void setPspCurrencyType(String pspCurrencyType) {
		this.pspCurrencyType = pspCurrencyType;
	}

	public String getPspheckFlag() {
		return pspheckFlag;
	}

	public void setPspheckFlag(String pspheckFlag) {
		this.pspheckFlag = pspheckFlag;
	}

	public Date getPspCtime() {
		return pspCtime;
	}

	public void setPspCtime(Date pspCtime) {
		this.pspCtime = pspCtime;
	}

	public Date getPspUtime() {
		return pspUtime;
	}

	public void setPspUtime(Date pspUtime) {
		this.pspUtime = pspUtime;
	}

	public String getPspCuser() {
		return pspCuser;
	}

	public void setPspCuser(String pspCuser) {
		this.pspCuser = pspCuser;
	}

	public String getPspUuser() {
		return pspUuser;
	}

	public void setPspUuser(String pspUuser) {
		this.pspUuser = pspUuser;
	}

	public String getPspValidFlag() {
		return pspValidFlag;
	}

	public void setPspValidFlag(String pspValidFlag) {
		this.pspValidFlag = pspValidFlag;
	}

	public String getPspCheckFlag() {
		return pspCheckFlag;
	}

	public void setPspCheckFlag(String pspCheckFlag) {
		this.pspCheckFlag = pspCheckFlag;
	}

	@Override
	public String toString() {
		return "ProductSalePrarms [pspId=" + pspId + ", pspChannelCode=" + pspChannelCode + ", pspChannelProductCode="
				+ pspChannelProductCode + ", pspIndiMinBidsAmt=" + pspIndiMinBidsAmt + ", pspInstMinBidsAmt="
				+ pspInstMinBidsAmt + ", pspIndiBidsDiffAmt=" + pspIndiBidsDiffAmt + ", pspInstBidsDiffAmt="
				+ pspInstBidsDiffAmt + ", pspIndiMaxBidsAmt=" + pspIndiMaxBidsAmt + ", pspInstMaxBidsAmt="
				+ pspInstMaxBidsAmt + ", pspIndiMinAppBidsAmt=" + pspIndiMinAppBidsAmt + ", pspInstMinAppBidsAmt="
				+ pspInstMinAppBidsAmt + ", pspIndiAppBidsDiffAmt=" + pspIndiAppBidsDiffAmt + ", pspInstAppBidsDiffAmt="
				+ pspInstAppBidsDiffAmt + ", pspIndiMaxAppBidsAmt=" + pspIndiMaxAppBidsAmt + ", pspInstMaxAppBidsAmt="
				+ pspInstMaxAppBidsAmt + ", pspIndiMaxVol=" + pspIndiMaxVol + ", pspInstMaxVol=" + pspInstMaxVol
				+ ", pspPayType=" + pspPayType + ", pspIndiBuyFlag=" + pspIndiBuyFlag + ", pspInstBuyFlag="
				+ pspInstBuyFlag + ", pspCurrencyType=" + pspCurrencyType + ", pspheckFlag=" + pspheckFlag
				+ ", pspCtime=" + pspCtime + ", pspUtime=" + pspUtime + ", pspCuser=" + pspCuser + ", pspUuser="
				+ pspUuser + ", pspValidFlag=" + pspValidFlag + ", pspCheckFlag=" + pspCheckFlag + "]";
	}

}