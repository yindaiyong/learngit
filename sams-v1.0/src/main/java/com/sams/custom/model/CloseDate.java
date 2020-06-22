package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class CloseDate implements Serializable {
    private Long cdId;

    private String cdMarketCode;

    private String cdYear;

    private String cdMonth;

    private String cdCloseDate;

    private String cdCheckState;

    private Date cdCtime;

    private Date cdUtime;

    private String cdCuser;

    private String cdUuser;
    
    private String cdAction;

    private static final long serialVersionUID = 1L;

    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public String getCdMarketCode() {
        return cdMarketCode;
    }

    public void setCdMarketCode(String cdMarketCode) {
        this.cdMarketCode = cdMarketCode == null ? null : cdMarketCode.trim();
    }

    public String getCdYear() {
        return cdYear;
    }

    public void setCdYear(String cdYear) {
        this.cdYear = cdYear == null ? null : cdYear.trim();
    }

    public String getCdMonth() {
        return cdMonth;
    }

    public void setCdMonth(String cdMonth) {
        this.cdMonth = cdMonth == null ? null : cdMonth.trim();
    }

    public String getCdCloseDate() {
        return cdCloseDate;
    }

    public void setCdCloseDate(String cdCloseDate) {
        this.cdCloseDate = cdCloseDate == null ? null : cdCloseDate.trim();
    }

    public String getCdCheckState() {
        return cdCheckState;
    }

    public void setCdCheckState(String cdCheckState) {
        this.cdCheckState = cdCheckState == null ? null : cdCheckState.trim();
    }

    public Date getCdCtime() {
        return cdCtime;
    }

    public void setCdCtime(Date cdCtime) {
        this.cdCtime = cdCtime;
    }

    public Date getCdUtime() {
        return cdUtime;
    }

    public void setCdUtime(Date cdUtime) {
        this.cdUtime = cdUtime;
    }

    public String getCdCuser() {
        return cdCuser;
    }

    public void setCdCuser(String cdCuser) {
        this.cdCuser = cdCuser == null ? null : cdCuser.trim();
    }

    public String getCdUuser() {
        return cdUuser;
    }

    public void setCdUuser(String cdUuser) {
        this.cdUuser = cdUuser == null ? null : cdUuser.trim();
    }
    
	public String getCdAction() {
		return cdAction;
	}

	public void setCdAction(String cdAction) {
		this.cdAction = cdAction;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cdId=").append(cdId);
        sb.append(", cdMarketCode=").append(cdMarketCode);
        sb.append(", cdYear=").append(cdYear);
        sb.append(", cdMonth=").append(cdMonth);
        sb.append(", cdCloseDate=").append(cdCloseDate);
        sb.append(", cdCheckState=").append(cdCheckState);
        sb.append(", cdCtime=").append(cdCtime);
        sb.append(", cdUtime=").append(cdUtime);
        sb.append(", cdCuser=").append(cdCuser);
        sb.append(", cdUuser=").append(cdUuser);
        sb.append(", cdAction=").append(cdAction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}