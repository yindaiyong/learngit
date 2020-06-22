
package com.sams.wsdl.S100434;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonXmlRq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonXmlRq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actflag">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="chengoutsum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractserialno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmauditor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmaudittime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmoperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deductfare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deductfaretype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exceedflag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faretype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fundcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isredeemall" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manualfare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="othercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outrequestno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outsystemflag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redeembalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redeemprofit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redeemshares" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redeemtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toprofitclass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totrustcontractid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trustcontractid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonXmlRq", propOrder = {
    "actflag",
    "chengoutsum",
    "contractserialno",
    "crmauditor",
    "crmaudittime",
    "crmoperator",
    "custname",
    "deductfare",
    "deductfaretype",
    "exceedflag",
    "faretype",
    "fundcode",
    "isredeemall",
    "manualfare",
    "othercode",
    "outrequestno",
    "outsystemflag",
    "redeembalance",
    "redeemprofit",
    "redeemshares",
    "redeemtype",
    "requestdate",
    "toprofitclass",
    "totrustcontractid",
    "trustcontractid"
})
public class CommonXmlRq {

    @XmlElement(required = true)
    protected String actflag;
    protected String chengoutsum;
    protected String contractserialno;
    protected String crmauditor;
    protected String crmaudittime;
    protected String crmoperator;
    protected String custname;
    protected String deductfare;
    protected String deductfaretype;
    protected String exceedflag;
    protected String faretype;
    protected String fundcode;
    protected String isredeemall;
    protected String manualfare;
    protected String othercode;
    protected String outrequestno;
    protected String outsystemflag;
    protected String redeembalance;
    protected String redeemprofit;
    protected String redeemshares;
    protected String redeemtype;
    protected String requestdate;
    protected String toprofitclass;
    protected String totrustcontractid;
    protected String trustcontractid;

    /**
     * Gets the value of the actflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActflag() {
        return actflag;
    }

    /**
     * Sets the value of the actflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActflag(String value) {
        this.actflag = value;
    }

    /**
     * Gets the value of the chengoutsum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChengoutsum() {
        return chengoutsum;
    }

    /**
     * Sets the value of the chengoutsum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChengoutsum(String value) {
        this.chengoutsum = value;
    }

    /**
     * Gets the value of the contractserialno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractserialno() {
        return contractserialno;
    }

    /**
     * Sets the value of the contractserialno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractserialno(String value) {
        this.contractserialno = value;
    }

    /**
     * Gets the value of the crmauditor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmauditor() {
        return crmauditor;
    }

    /**
     * Sets the value of the crmauditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmauditor(String value) {
        this.crmauditor = value;
    }

    /**
     * Gets the value of the crmaudittime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmaudittime() {
        return crmaudittime;
    }

    /**
     * Sets the value of the crmaudittime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmaudittime(String value) {
        this.crmaudittime = value;
    }

    /**
     * Gets the value of the crmoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmoperator() {
        return crmoperator;
    }

    /**
     * Sets the value of the crmoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmoperator(String value) {
        this.crmoperator = value;
    }

    /**
     * Gets the value of the custname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustname() {
        return custname;
    }

    /**
     * Sets the value of the custname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustname(String value) {
        this.custname = value;
    }

    /**
     * Gets the value of the deductfare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeductfare() {
        return deductfare;
    }

    /**
     * Sets the value of the deductfare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeductfare(String value) {
        this.deductfare = value;
    }

    /**
     * Gets the value of the deductfaretype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeductfaretype() {
        return deductfaretype;
    }

    /**
     * Sets the value of the deductfaretype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeductfaretype(String value) {
        this.deductfaretype = value;
    }

    /**
     * Gets the value of the exceedflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceedflag() {
        return exceedflag;
    }

    /**
     * Sets the value of the exceedflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceedflag(String value) {
        this.exceedflag = value;
    }

    /**
     * Gets the value of the faretype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaretype() {
        return faretype;
    }

    /**
     * Sets the value of the faretype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaretype(String value) {
        this.faretype = value;
    }

    /**
     * Gets the value of the fundcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundcode() {
        return fundcode;
    }

    /**
     * Sets the value of the fundcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundcode(String value) {
        this.fundcode = value;
    }

    /**
     * Gets the value of the isredeemall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsredeemall() {
        return isredeemall;
    }

    /**
     * Sets the value of the isredeemall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsredeemall(String value) {
        this.isredeemall = value;
    }

    /**
     * Gets the value of the manualfare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManualfare() {
        return manualfare;
    }

    /**
     * Sets the value of the manualfare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManualfare(String value) {
        this.manualfare = value;
    }

    /**
     * Gets the value of the othercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthercode() {
        return othercode;
    }

    /**
     * Sets the value of the othercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthercode(String value) {
        this.othercode = value;
    }

    /**
     * Gets the value of the outrequestno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutrequestno() {
        return outrequestno;
    }

    /**
     * Sets the value of the outrequestno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutrequestno(String value) {
        this.outrequestno = value;
    }

    /**
     * Gets the value of the outsystemflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutsystemflag() {
        return outsystemflag;
    }

    /**
     * Sets the value of the outsystemflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutsystemflag(String value) {
        this.outsystemflag = value;
    }

    /**
     * Gets the value of the redeembalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedeembalance() {
        return redeembalance;
    }

    /**
     * Sets the value of the redeembalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedeembalance(String value) {
        this.redeembalance = value;
    }

    /**
     * Gets the value of the redeemprofit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedeemprofit() {
        return redeemprofit;
    }

    /**
     * Sets the value of the redeemprofit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedeemprofit(String value) {
        this.redeemprofit = value;
    }

    /**
     * Gets the value of the redeemshares property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedeemshares() {
        return redeemshares;
    }

    /**
     * Sets the value of the redeemshares property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedeemshares(String value) {
        this.redeemshares = value;
    }

    /**
     * Gets the value of the redeemtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedeemtype() {
        return redeemtype;
    }

    /**
     * Sets the value of the redeemtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedeemtype(String value) {
        this.redeemtype = value;
    }

    /**
     * Gets the value of the requestdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestdate() {
        return requestdate;
    }

    /**
     * Sets the value of the requestdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestdate(String value) {
        this.requestdate = value;
    }

    /**
     * Gets the value of the toprofitclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToprofitclass() {
        return toprofitclass;
    }

    /**
     * Sets the value of the toprofitclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToprofitclass(String value) {
        this.toprofitclass = value;
    }

    /**
     * Gets the value of the totrustcontractid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotrustcontractid() {
        return totrustcontractid;
    }

    /**
     * Sets the value of the totrustcontractid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotrustcontractid(String value) {
        this.totrustcontractid = value;
    }

    /**
     * Gets the value of the trustcontractid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustcontractid() {
        return trustcontractid;
    }

    /**
     * Sets the value of the trustcontractid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustcontractid(String value) {
        this.trustcontractid = value;
    }

}
