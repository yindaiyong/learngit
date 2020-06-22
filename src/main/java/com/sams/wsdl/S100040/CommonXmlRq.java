
package com.sams.wsdl.S100040;

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
 *         &lt;element name="BindEmail">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindMobile">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FundAcco">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TradePassword">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "bindEmail",
    "bindMobile",
    "fundAcco",
    "tradePassword"
})
public class CommonXmlRq {

    @XmlElement(name = "BindEmail", required = true)
    protected String bindEmail;
    @XmlElement(name = "BindMobile", required = true)
    protected String bindMobile;
    @XmlElement(name = "FundAcco", required = true)
    protected String fundAcco;
    @XmlElement(name = "TradePassword", required = true)
    protected String tradePassword;

    /**
     * Gets the value of the bindEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindEmail() {
        return bindEmail;
    }

    /**
     * Sets the value of the bindEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindEmail(String value) {
        this.bindEmail = value;
    }

    /**
     * Gets the value of the bindMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindMobile() {
        return bindMobile;
    }

    /**
     * Sets the value of the bindMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindMobile(String value) {
        this.bindMobile = value;
    }

    /**
     * Gets the value of the fundAcco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundAcco() {
        return fundAcco;
    }

    /**
     * Sets the value of the fundAcco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundAcco(String value) {
        this.fundAcco = value;
    }

    /**
     * Gets the value of the tradePassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradePassword() {
        return tradePassword;
    }

    /**
     * Sets the value of the tradePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradePassword(String value) {
        this.tradePassword = value;
    }

}
