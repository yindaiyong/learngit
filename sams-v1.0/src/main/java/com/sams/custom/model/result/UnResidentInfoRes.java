package com.sams.custom.model.result;

import java.io.Serializable;

/**
 * @ClassName UnResidentInfoRes
 * 描述 : 非居民信息返回展示bean
 * @Author weijunjie
 * @Date 2019/9/24 13:57
 */
public class UnResidentInfoRes implements Serializable {

    //申请单编号
    private String appSheetSerialNo;
    //交易发生日期
    private String transactionDate;
    //交易发生时间
    private String transactionTime;
    //交易发生时间
    //销售人代码
    private String distributorCode;
    //网点号码
    private String branchCode;
    //个人/机构标志
    private String individualOrInstitution;
    //调查规则
    private String surveyMethod;
    //取得投资人声明标识
    private String getInvestCerFlag;
    //非居民标识
    private String nonResiFlag;
    //现居国家
    private String livingCountry;
    //现居地址
    private String livingAddress3;
    //税收居民国
    private String taxCountry;
    //增删标志
    private String addFlag;

    private String errorDec;

    private String channelCode;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getErrorDec() {
        return errorDec;
    }

    public void setErrorDec(String errorDec) {
        this.errorDec = errorDec;
    }

    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIndividualOrInstitution() {
        return individualOrInstitution;
    }

    public void setIndividualOrInstitution(String individualOrInstitution) {
        this.individualOrInstitution = individualOrInstitution;
    }

    public String getSurveyMethod() {
        return surveyMethod;
    }

    public void setSurveyMethod(String surveyMethod) {
        this.surveyMethod = surveyMethod;
    }

    public String getGetInvestCerFlag() {
        return getInvestCerFlag;
    }

    public void setGetInvestCerFlag(String getInvestCerFlag) {
        this.getInvestCerFlag = getInvestCerFlag;
    }

    public String getNonResiFlag() {
        return nonResiFlag;
    }

    public void setNonResiFlag(String nonResiFlag) {
        this.nonResiFlag = nonResiFlag;
    }

    public String getLivingCountry() {
        return livingCountry;
    }

    public void setLivingCountry(String livingCountry) {
        this.livingCountry = livingCountry;
    }

    public String getLivingAddress3() {
        return livingAddress3;
    }

    public void setLivingAddress3(String livingAddress3) {
        this.livingAddress3 = livingAddress3;
    }

    public String getTaxCountry() {
        return taxCountry;
    }

    public void setTaxCountry(String taxCountry) {
        this.taxCountry = taxCountry;
    }

    public String getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(String addFlag) {
        this.addFlag = addFlag;
    }

    @Override
    public String toString() {
        return "UnResidentInfoRes{" +
                "appSheetSerialNo='" + appSheetSerialNo + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", distributorCode='" + distributorCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", individualOrInstitution='" + individualOrInstitution + '\'' +
                ", surveyMethod='" + surveyMethod + '\'' +
                ", getInvestCerFlag='" + getInvestCerFlag + '\'' +
                ", nonResiFlag='" + nonResiFlag + '\'' +
                ", livingCountry='" + livingCountry + '\'' +
                ", livingAddress3='" + livingAddress3 + '\'' +
                ", taxCountry='" + taxCountry + '\'' +
                ", addFlag='" + addFlag + '\'' +
                '}';
    }

    public UnResidentInfoRes() {
    }

    public UnResidentInfoRes(String appSheetSerialNo, String transactionDate, String transactionTime, String distributorCode, String branchCode, String individualOrInstitution, String surveyMethod, String getInvestCerFlag, String nonResiFlag, String livingCountry, String livingAddress3, String taxCountry, String addFlag) {
        this.appSheetSerialNo = appSheetSerialNo;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.distributorCode = distributorCode;
        this.branchCode = branchCode;
        this.individualOrInstitution = individualOrInstitution;
        this.surveyMethod = surveyMethod;
        this.getInvestCerFlag = getInvestCerFlag;
        this.nonResiFlag = nonResiFlag;
        this.livingCountry = livingCountry;
        this.livingAddress3 = livingAddress3;
        this.taxCountry = taxCountry;
        this.addFlag = addFlag;
    }
}
