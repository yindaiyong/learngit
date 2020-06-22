package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChannelProductRelation implements Serializable {
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
    
    private String cprCheckState;
    
    private String cprAction;
    
    private String cprValidFlag;
    
    private String cprBatchNumber;

    private static final long serialVersionUID = 1L;

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
        this.cprChannelCode = cprChannelCode == null ? null : cprChannelCode.trim();
    }

    public String getCprFundCode() {
        return cprFundCode;
    }

    public void setCprFundCode(String cprFundCode) {
        this.cprFundCode = cprFundCode == null ? null : cprFundCode.trim();
    }

    public String getCprSectionNumber() {
        return cprSectionNumber;
    }

    public void setCprSectionNumber(String cprSectionNumber) {
        this.cprSectionNumber = cprSectionNumber == null ? null : cprSectionNumber.trim();
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
        this.cprStartMoney = cprStartMoney == null ? null : cprStartMoney.trim();
    }

    public String getCprEndMoney() {
        return cprEndMoney;
    }

    public void setCprEndMoney(String cprEndMoney) {
        this.cprEndMoney = cprEndMoney == null ? null : cprEndMoney.trim();
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
        this.cprCuser = cprCuser == null ? null : cprCuser.trim();
    }

    public String getCprUuser() {
        return cprUuser;
    }

    public void setCprUuser(String cprUuser) {
        this.cprUuser = cprUuser == null ? null : cprUuser.trim();
    }

    public String getCprCheckState() {
		return cprCheckState;
	}

	public void setCprCheckState(String cprCheckState) {
		this.cprCheckState = cprCheckState;
	}

	@Override
	public String toString() {
		return "ChannelProductRelation [cprId=" + cprId + ", cprChannelCode="
				+ cprChannelCode + ", cprFundCode=" + cprFundCode
				+ ", cprSectionNumber=" + cprSectionNumber + ", cprFundRate="
				+ cprFundRate + ", cprStartMoney=" + cprStartMoney
				+ ", cprEndMoney=" + cprEndMoney + ", cprCtime=" + cprCtime
				+ ", cprUtime=" + cprUtime + ", cprCuser=" + cprCuser
				+ ", cprUuser=" + cprUuser + ", cprCheckState=" + cprCheckState
				+ ", cprAction=" + cprAction
				+ "]";
	}

	public ChannelProductRelation(Long cprId, String cprChannelCode,
			String cprFundCode, String cprSectionNumber,
			BigDecimal cprFundRate, String cprStartMoney, String cprEndMoney,
			Date cprCtime, Date cprUtime, String cprCuser, String cprUuser,
			String cprCheckState,String cprAction) {
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
		this.cprCheckState = cprCheckState;
		this.cprAction = cprAction;
	}

	public ChannelProductRelation() {
		super();
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