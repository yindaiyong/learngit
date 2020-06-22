package com.sams.custom.model;

import java.io.Serializable;
import java.util.Date;

public class ProcessStepInfo implements Serializable {
    private Long psiId;

    private String psiFlowCode;

    private String psiProcessCode;

    private String psiPackageName;

    private String psiClassName;

    private String psiMethodName;

    private String psiInputParam;

    private String psiOutputParam;

    private String psiInputStrongParam;

    private String psiOutputStrongParam;

    private String psiProcessStep;

    private Date psiCtime;

    private Date psiUtime;

    private String psiCuser;

    private String psiUuser;

    private String psiProcessName;
    
    private Integer operType; //操作类型    重做该步  强制通过
    
    private String specialFile;//特殊处理文件类型
    

    public String getSpecialFile() {
		return specialFile;
	}

	public void setSpecialFile(String specialFile) {
		this.specialFile = specialFile;
	}

	private static final long serialVersionUID = 1L;

    public Long getPsiId() {
        return psiId;
    }

    public void setPsiId(Long psiId) {
        this.psiId = psiId;
    }

    public String getPsiFlowCode() {
        return psiFlowCode;
    }

    public void setPsiFlowCode(String psiFlowCode) {
        this.psiFlowCode = psiFlowCode == null ? null : psiFlowCode.trim();
    }

    public String getPsiProcessCode() {
        return psiProcessCode;
    }

    public void setPsiProcessCode(String psiProcessCode) {
        this.psiProcessCode = psiProcessCode == null ? null : psiProcessCode.trim();
    }

    public String getPsiPackageName() {
        return psiPackageName;
    }

    public void setPsiPackageName(String psiPackageName) {
        this.psiPackageName = psiPackageName == null ? null : psiPackageName.trim();
    }

    public String getPsiClassName() {
        return psiClassName;
    }

    public void setPsiClassName(String psiClassName) {
        this.psiClassName = psiClassName == null ? null : psiClassName.trim();
    }

    public String getPsiMethodName() {
        return psiMethodName;
    }

    public void setPsiMethodName(String psiMethodName) {
        this.psiMethodName = psiMethodName == null ? null : psiMethodName.trim();
    }

    public String getPsiInputParam() {
        return psiInputParam;
    }

    public void setPsiInputParam(String psiInputParam) {
        this.psiInputParam = psiInputParam == null ? null : psiInputParam.trim();
    }

    public String getPsiOutputParam() {
        return psiOutputParam;
    }

    public void setPsiOutputParam(String psiOutputParam) {
        this.psiOutputParam = psiOutputParam == null ? null : psiOutputParam.trim();
    }

    public String getPsiInputStrongParam() {
        return psiInputStrongParam;
    }

    public void setPsiInputStrongParam(String psiInputStrongParam) {
        this.psiInputStrongParam = psiInputStrongParam == null ? null : psiInputStrongParam.trim();
    }

    public String getPsiOutputStrongParam() {
        return psiOutputStrongParam;
    }

    public void setPsiOutputStrongParam(String psiOutputStrongParam) {
        this.psiOutputStrongParam = psiOutputStrongParam == null ? null : psiOutputStrongParam.trim();
    }

    public String getPsiProcessStep() {
        return psiProcessStep;
    }

    public void setPsiProcessStep(String psiProcessStep) {
        this.psiProcessStep = psiProcessStep == null ? null : psiProcessStep.trim();
    }

    public Date getPsiCtime() {
        return psiCtime;
    }

    public void setPsiCtime(Date psiCtime) {
        this.psiCtime = psiCtime;
    }

    public Date getPsiUtime() {
        return psiUtime;
    }

    public void setPsiUtime(Date psiUtime) {
        this.psiUtime = psiUtime;
    }

    public String getPsiCuser() {
        return psiCuser;
    }

    public void setPsiCuser(String psiCuser) {
        this.psiCuser = psiCuser == null ? null : psiCuser.trim();
    }

    public String getPsiUuser() {
        return psiUuser;
    }

    public void setPsiUuser(String psiUuser) {
        this.psiUuser = psiUuser == null ? null : psiUuser.trim();
    }

    public String getPsiProcessName() {
        return psiProcessName;
    }

    public void setPsiProcessName(String psiProcessName) {
        this.psiProcessName = psiProcessName == null ? null : psiProcessName.trim();
    }

    public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	
	
	public ProcessStepInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProcessStepInfo( String psiProcessName,String psiProcessCode,
			String psiProcessStep) {
		super();
		this.psiProcessCode = psiProcessCode;
		this.psiProcessName = psiProcessName;
		this.psiProcessStep = psiProcessStep;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", psiId=").append(psiId);
        sb.append(", psiFlowCode=").append(psiFlowCode);
        sb.append(", psiProcessCode=").append(psiProcessCode);
        sb.append(", psiPackageName=").append(psiPackageName);
        sb.append(", psiClassName=").append(psiClassName);
        sb.append(", psiMethodName=").append(psiMethodName);
        sb.append(", psiInputParam=").append(psiInputParam);
        sb.append(", psiOutputParam=").append(psiOutputParam);
        sb.append(", psiInputStrongParam=").append(psiInputStrongParam);
        sb.append(", psiOutputStrongParam=").append(psiOutputStrongParam);
        sb.append(", psiProcessStep=").append(psiProcessStep);
        sb.append(", psiCtime=").append(psiCtime);
        sb.append(", psiUtime=").append(psiUtime);
        sb.append(", psiCuser=").append(psiCuser);
        sb.append(", psiUuser=").append(psiUuser);
        sb.append(", psiProcessName=").append(psiProcessName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessStepInfo processStepInfo = (ProcessStepInfo) obj;
		if (processStepInfo.getPsiFlowCode() == null) {
			return false;
		}
		if(psiFlowCode.equals(processStepInfo.getPsiFlowCode()) && 
			psiProcessCode.equals(processStepInfo.getPsiProcessCode()) &&
			psiPackageName.equals(processStepInfo.getPsiPackageName()) &&
			psiClassName.equals(processStepInfo.getPsiClassName()) && 
			psiMethodName.equals(processStepInfo.getPsiMethodName()) && 
			psiProcessStep.equals(processStepInfo.getPsiProcessStep())
			){
				return true;
			}
		return false;
	}
	
}