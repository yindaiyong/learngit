package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class ProductOpenDay implements Serializable {
    private String poId;

    private String poChannelCode;

    private String poProductCode;

    private String poYear;

    private String poMonth;

    private String poCloseDay;

    private String poBatchNo;

    private Date poCtime;

    private Date poUtime;

    private String poCuser;

    private String poUuser;

    private String poAction;
    
    private String poCheckState;
    
    private String poOpenDayType;

    private static final long serialVersionUID = 1L;

    public ProductOpenDay() {
		super();
	}

	public ProductOpenDay(String poId, String poChannelCode, String poProductCode, String poYear, String poMonth,
			String poCloseDay, String poBatchNo, Date poCtime, Date poUtime, String poCuser, String poUuser,
			String poAction, String poCheckState,String poOpenDayType) {
		super();
		this.poId = poId;
		this.poChannelCode = poChannelCode;
		this.poProductCode = poProductCode;
		this.poYear = poYear;
		this.poMonth = poMonth;
		this.poCloseDay = poCloseDay;
		this.poBatchNo = poBatchNo;
		this.poCtime = poCtime;
		this.poUtime = poUtime;
		this.poCuser = poCuser;
		this.poUuser = poUuser;
		this.poAction = poAction;
		this.poCheckState = poCheckState;
		this.poOpenDayType = poOpenDayType;
	}

	public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId == null ? null : poId.trim();
    }

    public String getPoChannelCode() {
        return poChannelCode;
    }

    public void setPoChannelCode(String poChannelCode) {
        this.poChannelCode = poChannelCode == null ? null : poChannelCode.trim();
    }

    public String getPoProductCode() {
        return poProductCode;
    }

    public void setPoProductCode(String poProductCode) {
        this.poProductCode = poProductCode == null ? null : poProductCode.trim();
    }

    public String getPoYear() {
        return poYear;
    }

    public void setPoYear(String poYear) {
        this.poYear = poYear == null ? null : poYear.trim();
    }

    public String getPoMonth() {
        return poMonth;
    }

    public void setPoMonth(String poMonth) {
        this.poMonth = poMonth == null ? null : poMonth.trim();
    }

    public String getPoCloseDay() {
        return poCloseDay;
    }

    public void setPoCloseDay(String poCloseDay) {
        this.poCloseDay = poCloseDay == null ? null : poCloseDay.trim();
    }

    public String getPoBatchNo() {
        return poBatchNo;
    }

    public void setPoBatchNo(String poBatchNo) {
        this.poBatchNo = poBatchNo == null ? null : poBatchNo.trim();
    }

    public Date getPoCtime() {
        return poCtime;
    }

    public void setPoCtime(Date poCtime) {
        this.poCtime = poCtime;
    }

    public Date getPoUtime() {
        return poUtime;
    }

    public void setPoUtime(Date poUtime) {
        this.poUtime = poUtime;
    }

    public String getPoCuser() {
        return poCuser;
    }

    public void setPoCuser(String poCuser) {
        this.poCuser = poCuser == null ? null : poCuser.trim();
    }

    public String getPoUuser() {
        return poUuser;
    }

    public void setPoUuser(String poUuser) {
        this.poUuser = poUuser == null ? null : poUuser.trim();
    }

    public String getPoAction() {
        return poAction;
    }

    public void setPoAction(String poAction) {
        this.poAction = poAction == null ? null : poAction.trim();
    }

    public String getPoCheckState() {
		return poCheckState;
	}

	public void setPoCheckState(String poCheckState) {
		this.poCheckState = poCheckState;
	}

	public String getPoOpenDayType() {
		return poOpenDayType;
	}

	public void setPoOpenDayType(String poOpenDayType) {
		this.poOpenDayType = poOpenDayType;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", poId=").append(poId);
        sb.append(", poChannelCode=").append(poChannelCode);
        sb.append(", poProductCode=").append(poProductCode);
        sb.append(", poYear=").append(poYear);
        sb.append(", poMonth=").append(poMonth);
        sb.append(", poCloseDay=").append(poCloseDay);
        sb.append(", poBatchNo=").append(poBatchNo);
        sb.append(", poCtime=").append(poCtime);
        sb.append(", poUtime=").append(poUtime);
        sb.append(", poCuser=").append(poCuser);
        sb.append(", poUuser=").append(poUuser);
        sb.append(", poAction=").append(poAction);
        sb.append(", poCheckState=").append(poCheckState);
        sb.append(", poOpenDayType=").append(poOpenDayType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}