package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class InterfaceColInfo implements Serializable {
    private Long icId;

    private String icInterfaceCode;

    private String icColCode;

    private String icColName;

    private String icColDesc;

    private String icColType;

    private String icColLength;

    private String icColDecimal;

    private String icRequOnOff;

    private String icColRequ;

    private String icColDict;

    private String icColRule;

    private String icRuleOnOff;

    private String icColValue;

    private String icColOrder;

    private Date icCtime;

    private Date icUtime;

    private String icCuser;

    private String icUuser;
    
    private String iiInterfaceName;
    
    private String iiInterfaceCode;

    private static final long serialVersionUID = 1L;

    public Long getIcId() {
        return icId;
    }

    public void setIcId(Long icId) {
        this.icId = icId;
    }

    public String getIcInterfaceCode() {
        return icInterfaceCode;
    }

    public void setIcInterfaceCode(String icInterfaceCode) {
        this.icInterfaceCode = icInterfaceCode == null ? null : icInterfaceCode.trim();
    }

    public String getIcColCode() {
        return icColCode;
    }

    public void setIcColCode(String icColCode) {
        this.icColCode = icColCode == null ? null : icColCode.trim();
    }

    public String getIcColName() {
        return icColName;
    }

    public void setIcColName(String icColName) {
        this.icColName = icColName == null ? null : icColName.trim();
    }

    public String getIcColDesc() {
        return icColDesc;
    }

    public void setIcColDesc(String icColDesc) {
        this.icColDesc = icColDesc == null ? null : icColDesc.trim();
    }

    public String getIcColType() {
        return icColType;
    }

    public void setIcColType(String icColType) {
        this.icColType = icColType == null ? null : icColType.trim();
    }

    public String getIcColLength() {
        return icColLength;
    }

    public void setIcColLength(String icColLength) {
        this.icColLength = icColLength == null ? null : icColLength.trim();
    }

    public String getIcColDecimal() {
        return icColDecimal;
    }

    public void setIcColDecimal(String icColDecimal) {
        this.icColDecimal = icColDecimal == null ? null : icColDecimal.trim();
    }

    public String getIcRequOnOff() {
        return icRequOnOff;
    }

    public void setIcRequOnOff(String icRequOnOff) {
        this.icRequOnOff = icRequOnOff == null ? null : icRequOnOff.trim();
    }

    public String getIcColRequ() {
        return icColRequ;
    }

    public void setIcColRequ(String icColRequ) {
        this.icColRequ = icColRequ == null ? null : icColRequ.trim();
    }

    public String getIcColDict() {
        return icColDict;
    }

    public void setIcColDict(String icColDict) {
        this.icColDict = icColDict == null ? null : icColDict.trim();
    }

    public String getIcColRule() {
        return icColRule;
    }

    public void setIcColRule(String icColRule) {
        this.icColRule = icColRule == null ? null : icColRule.trim();
    }

    public String getIcRuleOnOff() {
        return icRuleOnOff;
    }

    public void setIcRuleOnOff(String icRuleOnOff) {
        this.icRuleOnOff = icRuleOnOff == null ? null : icRuleOnOff.trim();
    }

    public String getIcColValue() {
        return icColValue;
    }

    public void setIcColValue(String icColValue) {
        this.icColValue = icColValue == null ? null : icColValue.trim();
    }

    public String getIcColOrder() {
        return icColOrder;
    }

    public void setIcColOrder(String icColOrder) {
        this.icColOrder = icColOrder == null ? null : icColOrder.trim();
    }

    public Date getIcCtime() {
        return icCtime;
    }

    public void setIcCtime(Date icCtime) {
        this.icCtime = icCtime;
    }

    public Date getIcUtime() {
        return icUtime;
    }

    public void setIcUtime(Date icUtime) {
        this.icUtime = icUtime;
    }

    public String getIcCuser() {
        return icCuser;
    }

    public void setIcCuser(String icCuser) {
        this.icCuser = icCuser == null ? null : icCuser.trim();
    }

    public String getIcUuser() {
        return icUuser;
    }

    public void setIcUuser(String icUuser) {
        this.icUuser = icUuser == null ? null : icUuser.trim();
    }

    
    
    public String getIiInterfaceName() {
		return iiInterfaceName;
	}

	public void setIiInterfaceName(String iiInterfaceName) {
		this.iiInterfaceName = iiInterfaceName;
	}

	
	public String getIiInterfaceCode() {
		return iiInterfaceCode;
	}

	public void setIiInterfaceCode(String iiInterfaceCode) {
		this.iiInterfaceCode = iiInterfaceCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", icId=").append(icId);
        sb.append(", icInterfaceCode=").append(icInterfaceCode);
        sb.append(", icColCode=").append(icColCode);
        sb.append(", icColName=").append(icColName);
        sb.append(", icColDesc=").append(icColDesc);
        sb.append(", icColType=").append(icColType);
        sb.append(", icColLength=").append(icColLength);
        sb.append(", icColDecimal=").append(icColDecimal);
        sb.append(", icRequOnOff=").append(icRequOnOff);
        sb.append(", icColRequ=").append(icColRequ);
        sb.append(", icColDict=").append(icColDict);
        sb.append(", icColRule=").append(icColRule);
        sb.append(", icRuleOnOff=").append(icRuleOnOff);
        sb.append(", icColValue=").append(icColValue);
        sb.append(", icColOrder=").append(icColOrder);
        sb.append(", icCtime=").append(icCtime);
        sb.append(", icUtime=").append(icUtime);
        sb.append(", icCuser=").append(icCuser);
        sb.append(", icUuser=").append(icUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}