package com.sams.custom.model.result;

import java.math.BigDecimal;
import java.util.Date;

public class MarketHandingResult {

	 private BigDecimal psId;

	    private String psProcessCode;

	    private String psChannelCode;

	    private String psBranchCode;

	    private String psProcessStep;

	    private String psProcessStart;

	    private String psProcessStartTime;

	    private String psProcessEndTime;

	    private String psProcessDec;

	    private String psBusinessDate;

	    private String psErrorCode;

	    private Date psCtime;

	    private Date psUtime;

	    private String psCuser;

	    private String psUuser;

	    private static final long serialVersionUID = 1L;
	    
	    private String ciChannelName;
	    
	    private String reErrorMessage;
	    
	    private String ciChannelCode;
	    
	    private String lastDay;

		public MarketHandingResult() {
			super();
		}

		public MarketHandingResult(BigDecimal psId, String psProcessCode,
				String psChannelCode, String psBranchCode,
				String psProcessStep, String psProcessStart,
				String psProcessStartTime, String psProcessEndTime,
				String psProcessDec, String psBusinessDate, String psErrorCode,
				Date psCtime, Date psUtime, String psCuser, String psUuser,
				String ciChannelName, String reErrorMessage,
				String ciChannelCode, String lastDay) {
			super();
			this.psId = psId;
			this.psProcessCode = psProcessCode;
			this.psChannelCode = psChannelCode;
			this.psBranchCode = psBranchCode;
			this.psProcessStep = psProcessStep;
			this.psProcessStart = psProcessStart;
			this.psProcessStartTime = psProcessStartTime;
			this.psProcessEndTime = psProcessEndTime;
			this.psProcessDec = psProcessDec;
			this.psBusinessDate = psBusinessDate;
			this.psErrorCode = psErrorCode;
			this.psCtime = psCtime;
			this.psUtime = psUtime;
			this.psCuser = psCuser;
			this.psUuser = psUuser;
			this.ciChannelName = ciChannelName;
			this.reErrorMessage = reErrorMessage;
			this.ciChannelCode = ciChannelCode;
			this.lastDay = lastDay;
		}



		public BigDecimal getPsId() {
			return psId;
		}

		public void setPsId(BigDecimal psId) {
			this.psId = psId;
		}

		public String getPsProcessCode() {
			return psProcessCode;
		}

		public void setPsProcessCode(String psProcessCode) {
			this.psProcessCode = psProcessCode;
		}

		public String getPsChannelCode() {
			return psChannelCode;
		}

		public void setPsChannelCode(String psChannelCode) {
			this.psChannelCode = psChannelCode;
		}

		public String getPsBranchCode() {
			return psBranchCode;
		}

		public void setPsBranchCode(String psBranchCode) {
			this.psBranchCode = psBranchCode;
		}

		public String getPsProcessStep() {
			return psProcessStep;
		}

		public void setPsProcessStep(String psProcessStep) {
			this.psProcessStep = psProcessStep;
		}

		public String getPsProcessStart() {
			return psProcessStart;
		}

		public void setPsProcessStart(String psProcessStart) {
			this.psProcessStart = psProcessStart;
		}

		public String getPsProcessStartTime() {
			return psProcessStartTime;
		}

		public void setPsProcessStartTime(String psProcessStartTime) {
			this.psProcessStartTime = psProcessStartTime;
		}

		public String getPsProcessEndTime() {
			return psProcessEndTime;
		}

		public void setPsProcessEndTime(String psProcessEndTime) {
			this.psProcessEndTime = psProcessEndTime;
		}

		public String getPsProcessDec() {
			return psProcessDec;
		}

		public void setPsProcessDec(String psProcessDec) {
			this.psProcessDec = psProcessDec;
		}

		public String getPsBusinessDate() {
			return psBusinessDate;
		}

		public void setPsBusinessDate(String psBusinessDate) {
			this.psBusinessDate = psBusinessDate;
		}

		public String getPsErrorCode() {
			return psErrorCode;
		}

		public void setPsErrorCode(String psErrorCode) {
			this.psErrorCode = psErrorCode;
		}

		public Date getPsCtime() {
			return psCtime;
		}

		public void setPsCtime(Date psCtime) {
			this.psCtime = psCtime;
		}

		public Date getPsUtime() {
			return psUtime;
		}

		public void setPsUtime(Date psUtime) {
			this.psUtime = psUtime;
		}

		public String getPsCuser() {
			return psCuser;
		}

		public void setPsCuser(String psCuser) {
			this.psCuser = psCuser;
		}

		public String getPsUuser() {
			return psUuser;
		}

		public void setPsUuser(String psUuser) {
			this.psUuser = psUuser;
		}

		public String getCiChannelName() {
			return ciChannelName;
		}

		public void setCiChannelName(String ciChannelName) {
			this.ciChannelName = ciChannelName;
		}

		public String getReErrorMessage() {
			return reErrorMessage;
		}

		public void setReErrorMessage(String reErrorMessage) {
			this.reErrorMessage = reErrorMessage;
		}

		public String getCiChannelCode() {
			return ciChannelCode;
		}

		public void setCiChannelCode(String ciChannelCode) {
			this.ciChannelCode = ciChannelCode;
		}

		public String getLastDay() {
			return lastDay;
		}

		public void setLastDay(String lastDay) {
			this.lastDay = lastDay;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "MarketHandingResult [psId=" + psId + ", psProcessCode="
					+ psProcessCode + ", psChannelCode=" + psChannelCode
					+ ", psBranchCode=" + psBranchCode + ", psProcessStep="
					+ psProcessStep + ", psProcessStart=" + psProcessStart
					+ ", psProcessStartTime=" + psProcessStartTime
					+ ", psProcessEndTime=" + psProcessEndTime
					+ ", psProcessDec=" + psProcessDec + ", psBusinessDate="
					+ psBusinessDate + ", psErrorCode=" + psErrorCode
					+ ", psCtime=" + psCtime + ", psUtime=" + psUtime
					+ ", psCuser=" + psCuser + ", psUuser=" + psUuser
					+ ", ciChannelName=" + ciChannelName + ", reErrorMessage="
					+ reErrorMessage + ", ciChannelCode=" + ciChannelCode
					+ ", lastDay=" + lastDay + "]";
		}
}
