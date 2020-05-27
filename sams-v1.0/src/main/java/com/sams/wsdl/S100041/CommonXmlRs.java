
package com.sams.wsdl.S100041;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CommonXmlRs complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CommonXmlRs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RetMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RequestNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContractSerialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrustContractId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonXmlRs", propOrder = {
    "retCode",
    "retMsg",
    "requestNo",
    "contractSerialNo",
    "trustContractId"
})
public class CommonXmlRs {

    @XmlElement(name = "RetCode", required = true)
    protected String retCode;
    @XmlElement(name = "RetMsg", required = true)
    protected String retMsg;
    @XmlElement(name = "RequestNo", required = true)
    protected String requestNo;
    @XmlElement(name = "ContractSerialNo", required = true)
    protected String contractSerialNo;
    @XmlElement(name = "TrustContractId", required = true)
    protected String trustContractId;

    /**
     * 获取retCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetCode() {
        return retCode;
    }

    /**
     * 设置retCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetCode(String value) {
        this.retCode = value;
    }

    /**
     * 获取retMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * 设置retMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetMsg(String value) {
        this.retMsg = value;
    }

    /**
     * 获取requestNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * 设置requestNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestNo(String value) {
        this.requestNo = value;
    }

    /**
     * 获取contractSerialNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractSerialNo() {
        return contractSerialNo;
    }

    /**
     * 设置contractSerialNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractSerialNo(String value) {
        this.contractSerialNo = value;
    }

    /**
     * 获取trustContractId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustContractId() {
        return trustContractId;
    }

    /**
     * 设置trustContractId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustContractId(String value) {
        this.trustContractId = value;
    }

}
