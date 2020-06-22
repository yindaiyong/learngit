package com.sams.custom.model.result;

import java.io.Serializable;
import java.util.Date;

public class ChannelManagerResult implements Serializable{

	    private Long cmId;

	    private String cmChannelCode;

	    private String cmManagerCode;

	    private String cmManagerName;

	    private String cmStartDate;

	    private String cmEndDate;

	    private String cmCheckState;

	    private Date cmCtime;

	    private Date cmUtime;

	    private String cmCuser;

	    private String cmUuser;
	    
	    private String ciChannelName;
	    
	    private String ciBizManagerPhone;
	    
	    private String ciBizManagerMail;
	    
	    private String cmValidFlag;
	    
	    private String cmAction;
	    

	    private static final long serialVersionUID = 1L;

	    public Long getCmId() {
	        return cmId;
	    }

	    public void setCmId(Long cmId) {
	        this.cmId = cmId;
	    }

	    public String getCmChannelCode() {
	        return cmChannelCode;
	    }

	    public void setCmChannelCode(String cmChannelCode) {
	        this.cmChannelCode = cmChannelCode == null ? null : cmChannelCode.trim();
	    }

	    public String getCmManagerCode() {
	        return cmManagerCode;
	    }

	    public void setCmManagerCode(String cmManagerCode) {
	        this.cmManagerCode = cmManagerCode == null ? null : cmManagerCode.trim();
	    }

	    public String getCmManagerName() {
	        return cmManagerName;
	    }

	    public void setCmManagerName(String cmManagerName) {
	        this.cmManagerName = cmManagerName == null ? null : cmManagerName.trim();
	    }

	    public String getCmStartDate() {
	        return cmStartDate;
	    }

	    public void setCmStartDate(String cmStartDate) {
	        this.cmStartDate = cmStartDate == null ? null : cmStartDate.trim();
	    }

	    public String getCmEndDate() {
	        return cmEndDate;
	    }

	    public void setCmEndDate(String cmEndDate) {
	        this.cmEndDate = cmEndDate == null ? null : cmEndDate.trim();
	    }

	    public String getCmCheckState() {
	        return cmCheckState;
	    }

	    public void setCmCheckState(String cmCheckState) {
	        this.cmCheckState = cmCheckState == null ? null : cmCheckState.trim();
	    }

	    public Date getCmCtime() {
	        return cmCtime;
	    }

	    public void setCmCtime(Date cmCtime) {
	        this.cmCtime = cmCtime;
	    }

	    public Date getCmUtime() {
	        return cmUtime;
	    }

	    public void setCmUtime(Date cmUtime) {
	        this.cmUtime = cmUtime;
	    }

	    public String getCmCuser() {
	        return cmCuser;
	    }

	    public void setCmCuser(String cmCuser) {
	        this.cmCuser = cmCuser == null ? null : cmCuser.trim();
	    }

	    public String getCmUuser() {
	        return cmUuser;
	    }

	    public void setCmUuser(String cmUuser) {
	        this.cmUuser = cmUuser == null ? null : cmUuser.trim();
	    }
	    

	    public String getCiChannelName() {
			return ciChannelName;
		}

		public void setCiChannelName(String ciChannelName) {
			this.ciChannelName = ciChannelName;
		}

		public String getCiBizManagerPhone() {
			return ciBizManagerPhone;
		}

		public void setCiBizManagerPhone(String ciBizManagerPhone) {
			this.ciBizManagerPhone = ciBizManagerPhone;
		}

		public String getCiBizManagerMail() {
			return ciBizManagerMail;
		}

		public void setCiBizManagerMail(String ciBizManagerMail) {
			this.ciBizManagerMail = ciBizManagerMail;
		}

		public String getcmValidFlag() {
			return cmValidFlag;
		}

		public void setcmValidFlag(String cmValidFlag) {
			this.cmValidFlag = cmValidFlag;
		}

		public String getCmAction() {
			return cmAction;
		}

		public void setCmAction(String cmAction) {
			this.cmAction = cmAction;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public ChannelManagerResult() {
			super();
		}

		public ChannelManagerResult(Long cmId, String cmChannelCode, String cmManagerCode, String cmManagerName,
				String cmStartDate, String cmEndDate, String cmCheckState, Date cmCtime, Date cmUtime, String cmCuser,
				String cmUuser, String ciChannelName, String ciBizManagerPhone, String ciBizManagerMail,
				String cmValidFlag, String cmAction) {
			super();
			this.cmId = cmId;
			this.cmChannelCode = cmChannelCode;
			this.cmManagerCode = cmManagerCode;
			this.cmManagerName = cmManagerName;
			this.cmStartDate = cmStartDate;
			this.cmEndDate = cmEndDate;
			this.cmCheckState = cmCheckState;
			this.cmCtime = cmCtime;
			this.cmUtime = cmUtime;
			this.cmCuser = cmCuser;
			this.cmUuser = cmUuser;
			this.ciChannelName = ciChannelName;
			this.ciBizManagerPhone = ciBizManagerPhone;
			this.ciBizManagerMail = ciBizManagerMail;
			this.cmValidFlag = cmValidFlag;
			this.cmAction = cmAction;
		}

		@Override
		public String toString() {
			return "ChannelManagerResult [cmId=" + cmId + ", cmChannelCode=" + cmChannelCode + ", cmManagerCode="
					+ cmManagerCode + ", cmManagerName=" + cmManagerName + ", cmStartDate=" + cmStartDate
					+ ", cmEndDate=" + cmEndDate + ", cmCheckState=" + cmCheckState + ", cmCtime=" + cmCtime
					+ ", cmUtime=" + cmUtime + ", cmCuser=" + cmCuser + ", cmUuser=" + cmUuser + ", ciChannelName="
					+ ciChannelName + ", ciBizManagerPhone=" + ciBizManagerPhone + ", ciBizManagerMail="
					+ ciBizManagerMail + ", cmValidFlag=" + cmValidFlag + ", cmAction=" + cmAction + "]";
		}

}
