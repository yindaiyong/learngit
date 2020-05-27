
package com.sams.wsdl.S100512;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CommonXmlRq complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CommonXmlRq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractserialno">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nameinbank">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="40000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="bankname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankacco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bankprovincecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankcityno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="banklinecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneytype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "contractserialno",
    "nameinbank",
    "bankname",
    "bankacco",
    "bankno",
    "bankprovincecode",
    "bankcityno",
    "banklinecode",
    "moneytype"
})
public class CommonXmlRq {

    @XmlElement(required = true)
    protected String contractserialno;
    @XmlElement(required = true)
    protected String nameinbank;
    @XmlElement(required = true)
    protected String bankname;
    @XmlElement(required = true)
    protected String bankacco;
    @XmlElement(required = true)
    protected String bankno;
    protected String bankprovincecode;
    protected String bankcityno;
    protected String banklinecode;
    protected String moneytype;

    /**
     * 获取contractserialno属性的值。
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
     * 设置contractserialno属性的值。
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
     * 获取nameinbank属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameinbank() {
        return nameinbank;
    }

    /**
     * 设置nameinbank属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameinbank(String value) {
        this.nameinbank = value;
    }

    /**
     * 获取bankname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 设置bankname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankname(String value) {
        this.bankname = value;
    }

    /**
     * 获取bankacco属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankacco() {
        return bankacco;
    }

    /**
     * 设置bankacco属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankacco(String value) {
        this.bankacco = value;
    }

    /**
     * 获取bankno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankno() {
        return bankno;
    }

    /**
     * 设置bankno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankno(String value) {
        this.bankno = value;
    }

    /**
     * 获取bankprovincecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankprovincecode() {
        return bankprovincecode;
    }

    /**
     * 设置bankprovincecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankprovincecode(String value) {
        this.bankprovincecode = value;
    }

    /**
     * 获取bankcityno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankcityno() {
        return bankcityno;
    }

    /**
     * 设置bankcityno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankcityno(String value) {
        this.bankcityno = value;
    }

    /**
     * 获取banklinecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanklinecode() {
        return banklinecode;
    }

    /**
     * 设置banklinecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanklinecode(String value) {
        this.banklinecode = value;
    }

    /**
     * 获取moneytype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneytype() {
        return moneytype;
    }

    /**
     * 设置moneytype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneytype(String value) {
        this.moneytype = value;
    }

}
