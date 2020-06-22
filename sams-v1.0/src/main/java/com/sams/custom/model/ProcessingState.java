package com.sams.custom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProcessingState implements Serializable {
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
        this.psProcessCode = psProcessCode == null ? null : psProcessCode.trim();
    }

    public String getPsChannelCode() {
        return psChannelCode;
    }

    public void setPsChannelCode(String psChannelCode) {
        this.psChannelCode = psChannelCode == null ? null : psChannelCode.trim();
    }

    public String getPsBranchCode() {
        return psBranchCode;
    }

    public void setPsBranchCode(String psBranchCode) {
        this.psBranchCode = psBranchCode == null ? null : psBranchCode.trim();
    }

    public String getPsProcessStep() {
        return psProcessStep;
    }

    public void setPsProcessStep(String psProcessStep) {
        this.psProcessStep = psProcessStep == null ? null : psProcessStep.trim();
    }

    public String getPsProcessStart() {
        return psProcessStart;
    }

    public void setPsProcessStart(String psProcessStart) {
        this.psProcessStart = psProcessStart == null ? null : psProcessStart.trim();
    }

    public String getPsProcessStartTime() {
        return psProcessStartTime;
    }

    public void setPsProcessStartTime(String psProcessStartTime) {
        this.psProcessStartTime = psProcessStartTime == null ? null : psProcessStartTime.trim();
    }

    public String getPsProcessEndTime() {
        return psProcessEndTime;
    }

    public void setPsProcessEndTime(String psProcessEndTime) {
        this.psProcessEndTime = psProcessEndTime == null ? null : psProcessEndTime.trim();
    }

    public String getPsProcessDec() {
        return psProcessDec;
    }

    public void setPsProcessDec(String psProcessDec) {
        this.psProcessDec = psProcessDec == null ? null : psProcessDec.trim();
    }

    public String getPsBusinessDate() {
        return psBusinessDate;
    }

    public void setPsBusinessDate(String psBusinessDate) {
        this.psBusinessDate = psBusinessDate == null ? null : psBusinessDate.trim();
    }

    public String getPsErrorCode() {
        return psErrorCode;
    }

    public void setPsErrorCode(String psErrorCode) {
        this.psErrorCode = psErrorCode == null ? null : psErrorCode.trim();
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
        this.psCuser = psCuser == null ? null : psCuser.trim();
    }

    public String getPsUuser() {
        return psUuser;
    }

    public void setPsUuser(String psUuser) {
        this.psUuser = psUuser == null ? null : psUuser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", psId=").append(psId);
        sb.append(", psProcessCode=").append(psProcessCode);
        sb.append(", psChannelCode=").append(psChannelCode);
        sb.append(", psBranchCode=").append(psBranchCode);
        sb.append(", psProcessStep=").append(psProcessStep);
        sb.append(", psProcessStart=").append(psProcessStart);
        sb.append(", psProcessStartTime=").append(psProcessStartTime);
        sb.append(", psProcessEndTime=").append(psProcessEndTime);
        sb.append(", psProcessDec=").append(psProcessDec);
        sb.append(", psBusinessDate=").append(psBusinessDate);
        sb.append(", psErrorCode=").append(psErrorCode);
        sb.append(", psCtime=").append(psCtime);
        sb.append(", psUtime=").append(psUtime);
        sb.append(", psCuser=").append(psCuser);
        sb.append(", psUuser=").append(psUuser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}