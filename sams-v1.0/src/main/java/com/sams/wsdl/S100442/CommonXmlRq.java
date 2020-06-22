
package com.sams.wsdl.S100442;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="actflag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="crmserialno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserveno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="occurbalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arrivedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paidcount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frombankno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frombankname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromnameinbank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frombankacco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accoid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmoperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fundacco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fundcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outsystemflag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentresno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tobankacco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="capdealtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="captype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "crmserialno",
    "reserveno",
    "occurbalance",
    "arrivedate",
    "sourcetype",
    "paidcount",
    "frombankno",
    "frombankname",
    "fromnameinbank",
    "frombankacco",
    "accoid",
    "crmoperator",
    "fundacco",
    "fundcode",
    "outsystemflag",
    "parentresno",
    "tobankacco",
    "capdealtype",
    "captype"
})
public class CommonXmlRq {

    protected String actflag;
    protected String crmserialno;
    protected String reserveno;
    protected String occurbalance;
    protected String arrivedate;
    protected String sourcetype;
    protected String paidcount;
    protected String frombankno;
    protected String frombankname;
    protected String fromnameinbank;
    protected String frombankacco;
    protected String accoid;
    protected String crmoperator;
    protected String fundacco;
    protected String fundcode;
    protected String outsystemflag;
    protected String parentresno;
    protected String tobankacco;
    protected String capdealtype;
    protected String captype;

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
     * Gets the value of the crmserialno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmserialno() {
        return crmserialno;
    }

    /**
     * Sets the value of the crmserialno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmserialno(String value) {
        this.crmserialno = value;
    }

    /**
     * Gets the value of the reserveno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveno() {
        return reserveno;
    }

    /**
     * Sets the value of the reserveno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveno(String value) {
        this.reserveno = value;
    }

    /**
     * Gets the value of the occurbalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccurbalance() {
        return occurbalance;
    }

    /**
     * Sets the value of the occurbalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccurbalance(String value) {
        this.occurbalance = value;
    }

    /**
     * Gets the value of the arrivedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivedate() {
        return arrivedate;
    }

    /**
     * Sets the value of the arrivedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivedate(String value) {
        this.arrivedate = value;
    }

    /**
     * Gets the value of the sourcetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcetype() {
        return sourcetype;
    }

    /**
     * Sets the value of the sourcetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcetype(String value) {
        this.sourcetype = value;
    }

    /**
     * Gets the value of the paidcount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaidcount() {
        return paidcount;
    }

    /**
     * Sets the value of the paidcount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaidcount(String value) {
        this.paidcount = value;
    }

    /**
     * Gets the value of the frombankno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrombankno() {
        return frombankno;
    }

    /**
     * Sets the value of the frombankno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrombankno(String value) {
        this.frombankno = value;
    }

    /**
     * Gets the value of the frombankname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrombankname() {
        return frombankname;
    }

    /**
     * Sets the value of the frombankname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrombankname(String value) {
        this.frombankname = value;
    }

    /**
     * Gets the value of the fromnameinbank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromnameinbank() {
        return fromnameinbank;
    }

    /**
     * Sets the value of the fromnameinbank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromnameinbank(String value) {
        this.fromnameinbank = value;
    }

    /**
     * Gets the value of the frombankacco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrombankacco() {
        return frombankacco;
    }

    /**
     * Sets the value of the frombankacco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrombankacco(String value) {
        this.frombankacco = value;
    }

    /**
     * Gets the value of the accoid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccoid() {
        return accoid;
    }

    /**
     * Sets the value of the accoid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccoid(String value) {
        this.accoid = value;
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
     * Gets the value of the fundacco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundacco() {
        return fundacco;
    }

    /**
     * Sets the value of the fundacco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundacco(String value) {
        this.fundacco = value;
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
     * Gets the value of the parentresno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentresno() {
        return parentresno;
    }

    /**
     * Sets the value of the parentresno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentresno(String value) {
        this.parentresno = value;
    }

    /**
     * Gets the value of the tobankacco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTobankacco() {
        return tobankacco;
    }

    /**
     * Sets the value of the tobankacco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTobankacco(String value) {
        this.tobankacco = value;
    }

    /**
     * Gets the value of the capdealtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapdealtype() {
        return capdealtype;
    }

    /**
     * Sets the value of the capdealtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapdealtype(String value) {
        this.capdealtype = value;
    }

    /**
     * Gets the value of the captype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaptype() {
        return captype;
    }

    /**
     * Sets the value of the captype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaptype(String value) {
        this.captype = value;
    }

}
