package com.sams.custom.model.result;

import java.math.BigDecimal;
import java.util.Date;

public class ChannelProductResult {

	 private Long cprId;

	    private String cprChannelCode;

	    private String cprFundCode;

	    private String cprSectionNumber;

	    private BigDecimal cprFundRate;

	    private String cprStartMoney;

	    private String cprEndMoney;

	    private Date cprCtime;

	    private Date cprUtime;

	    private String cprCuser;

	    private String cprUuser;
	    
	    private String cprAction;

	    private static final long serialVersionUID = 1L;
	    
	    private String piChannelProductName;
	    
	    private String piCheckState;
	    
	    private Long piId;
	    
	    private String cprCheckState;
	    
	    private String cprTaProductName;
	    
	    private String cprTaProductCode;
	    
	    private String cprValidFlag;
	    
	    private String cprBatchNumber;

		public ChannelProductResult() {
			super();
		}

		public ChannelProductResult(Long cprId, String cprChannelCode,
				String cprFundCode, String cprSectionNumber,
				BigDecimal cprFundRate, String cprStartMoney,
				String cprEndMoney, Date cprCtime, Date cprUtime,
				String cprCuser, String cprUuser, String piChannelProductName,
				String piCheckState, Long piId,String cprCheckState,String cprAction) {
			super();
			this.cprId = cprId;
			this.cprChannelCode = cprChannelCode;
			this.cprFundCode = cprFundCode;
			this.cprSectionNumber = cprSectionNumber;
			this.cprFundRate = cprFundRate;
			this.cprStartMoney = cprStartMoney;
			this.cprEndMoney = cprEndMoney;
			this.cprCtime = cprCtime;
			this.cprUtime = cprUtime;
			this.cprCuser = cprCuser;
			this.cprUuser = cprUuser;
			this.piChannelProductName = piChannelProductName;
			this.piCheckState = piCheckState;
			this.piId = piId;
			this.cprCheckState = cprCheckState;
			this.cprAction = cprAction;
		}

		public Long getCprId() {
			return cprId;
		}

		public void setCprId(Long cprId) {
			this.cprId = cprId;
		}

		public String getCprChannelCode() {
			return cprChannelCode;
		}

		public void setCprChannelCode(String cprChannelCode) {
			this.cprChannelCode = cprChannelCode;
		}

		public String getCprFundCode() {
			return cprFundCode;
		}

		public void setCprFundCode(String cprFundCode) {
			this.cprFundCode = cprFundCode;
		}

		public String getCprSectionNumber() {
			return cprSectionNumber;
		}

		public void setCprSectionNumber(String cprSectionNumber) {
			this.cprSectionNumber = cprSectionNumber;
		}

		public BigDecimal getCprFundRate() {
			return cprFundRate;
		}

		public void setCprFundRate(BigDecimal cprFundRate) {
			this.cprFundRate = cprFundRate;
		}

		public String getCprStartMoney() {
			return cprStartMoney;
		}

		public void setCprStartMoney(String cprStartMoney) {
			this.cprStartMoney = cprStartMoney;
		}

		public String getCprEndMoney() {
			return cprEndMoney;
		}

		public void setCprEndMoney(String cprEndMoney) {
			this.cprEndMoney = cprEndMoney;
		}

		public Date getCprCtime() {
			return cprCtime;
		}

		public void setCprCtime(Date cprCtime) {
			this.cprCtime = cprCtime;
		}

		public Date getCprUtime() {
			return cprUtime;
		}

		public void setCprUtime(Date cprUtime) {
			this.cprUtime = cprUtime;
		}

		public String getCprCuser() {
			return cprCuser;
		}

		public void setCprCuser(String cprCuser) {
			this.cprCuser = cprCuser;
		}

		public String getCprUuser() {
			return cprUuser;
		}

		public void setCprUuser(String cprUuser) {
			this.cprUuser = cprUuser;
		}

		public String getPiChannelProductName() {
			return piChannelProductName;
		}

		public void setPiChannelProductName(String piChannelProductName) {
			this.piChannelProductName = piChannelProductName;
		}

		public String getPiCheckState() {
			return piCheckState;
		}

		public void setPiCheckState(String piCheckState) {
			this.piCheckState = piCheckState;
		}

		public Long getPiId() {
			return piId;
		}

		public void setPiId(Long piId) {
			this.piId = piId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public String getCprCheckState() {
			return cprCheckState;
		}

		public void setCprCheckState(String cprCheckState) {
			this.cprCheckState = cprCheckState;
		}
		public String getCprTaProductName() {
			return cprTaProductName;
		}

		public void setCprTaProductName(String cprTaProductName) {
			this.cprTaProductName = cprTaProductName;
		}

		public String getCprTaProductCode() {
			return cprTaProductCode;
		}

		public void setCprTaProductCode(String cprTaProductCode) {
			this.cprTaProductCode = cprTaProductCode;
		}

		@Override
		public String toString() {
			return "ChannelProductResult [cprId=" + cprId + ", cprChannelCode="
					+ cprChannelCode + ", cprFundCode=" + cprFundCode
					+ ", cprSectionNumber=" + cprSectionNumber
					+ ", cprFundRate=" + cprFundRate + ", cprStartMoney="
					+ cprStartMoney + ", cprEndMoney=" + cprEndMoney
					+ ", cprCtime=" + cprCtime + ", cprUtime=" + cprUtime
					+ ", cprCuser=" + cprCuser + ", cprUuser=" + cprUuser
					+ ", piChannelProductName=" + piChannelProductName
					+ ", piCheckState=" + piCheckState + ", piId=" + piId
					+ ", cprCheckState=" + cprCheckState
					+ ", cprTaProductName=" + cprTaProductName
					+ ", cprAction=" + cprAction
					+ ", cprTaProductCode=" + cprTaProductCode + "]";
		}

		public String getCprAction() {
			return cprAction;
		}

		public void setCprAction(String cprAction) {
			this.cprAction = cprAction;
		}

		public String getCprValidFlag() {
			return cprValidFlag;
		}

		public void setCprValidFlag(String cprValidFlag) {
			this.cprValidFlag = cprValidFlag;
		}

		public String getCprBatchNumber() {
			return cprBatchNumber;
		}

		public void setCprBatchNumber(String cprBatchNumber) {
			this.cprBatchNumber = cprBatchNumber;
		}

}
