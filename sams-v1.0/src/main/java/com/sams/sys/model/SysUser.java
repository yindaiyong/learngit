package com.sams.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sams.common.utils.DateUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser implements Serializable {

	@Id
    @GeneratedValue(generator = "UUID")
    private String userId;

    private String loginName="";

    private String password="";

    private String salt="";

    private String userName="";

    private String userStatus="";

    private String userLevel="";

    private String userFlag="";

    private String orgId="";

    private String sex="";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthDate=DateUtils.getOracleSysDate();

    private String phone="";

    private String email="";

    private String address="";

    private String description="";

    private Integer loginCount=0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastvisitDate=DateUtils.getOracleSysDate();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate=DateUtils.getOracleSysDate();

    @Column(name="field_1")
    private String field1="";

    @Column(name="field_2")
    private String field2="";

    @Column(name="field_3")
    private String field3="";

    @Column(name="field_4")
    private String field4="";
    
    @Column(name="field_5")
    private String field5="";

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? "" : userId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? "" : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? "" : salt.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? "" : userName.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? "" : userStatus.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? "" : userLevel.trim();
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag == null ? "" : userFlag.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? "" : orgId.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? "" : sex.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastvisitDate() {
        return lastvisitDate;
    }

    public void setLastvisitDate(Date lastvisitDate) {
        this.lastvisitDate = lastvisitDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1 == null ? "" : field1.trim();
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2 == null ? "" : field2.trim();
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3 == null ? "" : field3.trim();
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4 == null ? "" : field4.trim();
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5 == null ? "" : field5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", userName=").append(userName);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", userFlag=").append(userFlag);
        sb.append(", orgId=").append(orgId);
        sb.append(", sex=").append(sex);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", description=").append(description);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", lastvisitDate=").append(lastvisitDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", field1=").append(field1);
        sb.append(", field2=").append(field2);
        sb.append(", field3=").append(field3);
        sb.append(", field4=").append(field4);
        sb.append(", field5=").append(field5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}