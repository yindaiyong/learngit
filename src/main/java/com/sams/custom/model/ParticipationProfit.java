package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParticipationProfit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String channelCode;
	
	private String channelName;
	
	private String fundCode;
	
	private String profitNature; //分红性质    中期  到期  
	
	private BigDecimal applicationAmount; //本金
	
	private BigDecimal earningsAmount; //收益
	
	private BigDecimal totalAmount ; //总额
	
	private String taAccountId; //基金账户
	
	private String investorName;
	
	private String certificateno;
	
	private String inContract;
	
	private String originalSerialNo;
	
	private String productType;
	
	public String getInContract() {
		return inContract;
	}

	public void setInContract(String inContract) {
		this.inContract = inContract;
	}

	public String getOriginalSerialNo() {
		return originalSerialNo;
	}

	public void setOriginalSerialNo(String originalSerialNo) {
		this.originalSerialNo = originalSerialNo;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getProfitNature() {
		return profitNature;
	}

	public void setProfitNature(String profitNature) {
		this.profitNature = profitNature;
	}

	public BigDecimal getApplicationAmount() {
		return applicationAmount;
	}

	public void setApplicationAmount(BigDecimal applicationAmount) {
		this.applicationAmount = applicationAmount;
	}

	public BigDecimal getEarningsAmount() {
		return earningsAmount;
	}

	public void setEarningsAmount(BigDecimal earningsAmount) {
		this.earningsAmount = earningsAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTaAccountId() {
		return taAccountId;
	}

	public void setTaAccountId(String taAccountId) {
		this.taAccountId = taAccountId;
	}

	
	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getCertificateno() {
		return certificateno;
	}

	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno;
	}

	@Override
	public String toString() {
		return "ParticipationProfit [channelCode=" + channelCode
				+ ", channelName=" + channelName + ", fundCode=" + fundCode
				+ ", profitNature=" + profitNature + ", applicationAmount="
				+ applicationAmount + ", earningsAmount=" + earningsAmount
				+ ", totalAmount=" + totalAmount + ", taAccountId="
				+ taAccountId + "]";
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	
	
}