package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class DayEndTransDate implements Serializable {
    private Long dtId;

    private String dtChannelCode;

    private String dtLastDate;

    private String dtCurrentDate;

    private String dtNextDate;

    private Date dtCtime;

    private Date dtUtime;

    private String dtCuser;

    private String dtUuser;

    private static final long serialVersionUID = 1L;

    public Long getDtId() {
        return dtId;
    }

    public void setDtId(Long dtId) {
        this.dtId = dtId;
    }

    public String getDtChannelCode() {
        return dtChannelCode;
    }

    public void setDtChannelCode(String dtChannelCode) {
        this.dtChannelCode = dtChannelCode == null ? null : dtChannelCode.trim();
    }

    public String getDtLastDate() {
        return dtLastDate;
    }

    public void setDtLastDate(String dtLastDate) {
        this.dtLastDate = dtLastDate == null ? null : dtLastDate.trim();
    }

    public String getDtCurrentDate() {
        return dtCurrentDate;
    }

    public void setDtCurrentDate(String dtCurrentDate) {
        this.dtCurrentDate = dtCurrentDate == null ? null : dtCurrentDate.trim();
    }

    public String getDtNextDate() {
        return dtNextDate;
    }

    public void setDtNextDate(String dtNextDate) {
        this.dtNextDate = dtNextDate == null ? null : dtNextDate.trim();
    }

    public Date getDtCtime() {
        return dtCtime;
    }

    public void setDtCtime(Date dtCtime) {
        this.dtCtime = dtCtime;
    }

    public Date getDtUtime() {
        return dtUtime;
    }

    public void setDtUtime(Date dtUtime) {
        this.dtUtime = dtUtime;
    }

    public String getDtCuser() {
        return dtCuser;
    }

    public void setDtCuser(String dtCuser) {
        this.dtCuser = dtCuser == null ? null : dtCuser.trim();
    }

    public String getDtUuser() {
        return dtUuser;
    }

    public void setDtUuser(String dtUuser) {
        this.dtUuser = dtUuser == null ? null : dtUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dtId=").append(dtId);
        sb.append(", dtChannelCode=").append(dtChannelCode);
        sb.append(", dtLastDate=").append(dtLastDate);
        sb.append(", dtCurrentDate=").append(dtCurrentDate);
        sb.append(", dtNextDate=").append(dtNextDate);
        sb.append(", dtCtime=").append(dtCtime);
        sb.append(", dtUtime=").append(dtUtime);
        sb.append(", dtCuser=").append(dtCuser);
        sb.append(", dtUuser=").append(dtUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}