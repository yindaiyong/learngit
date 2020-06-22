
package com.sams.wsdl.S100454;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonXmlRs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonXmlRs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RetMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reserverno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gencontractmode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContractAuditToDC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contractserialno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FundAcco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reserverno",
    "gencontractmode",
    "contractAuditToDC",
    "contractserialno",
    "fundAcco"
})
public class CommonXmlRs {

    @XmlElement(name = "RetCode", required = true)
    protected String retCode;
    @XmlElement(name = "RetMsg", required = true)
    protected String retMsg;
    @XmlElement(name = "Reserverno")
    protected String reserverno;
    @XmlElement(name = "Gencontractmode")
    protected String gencontractmode;
    @XmlElement(name = "ContractAuditToDC")
    protected String contractAuditToDC;
    @XmlElement(name = "Contractserialno")
    protected String contractserialno;
    @XmlElement(name = "FundAcco")
    protected String fundAcco;

    /**
     * Gets the value of the retCode property.
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
     * Sets the value of the retCode property.
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
     * Gets the value of the retMsg property.
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
     * Sets the value of the retMsg property.
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
     * Gets the value of the reserverno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserverno() {
        return reserverno;
    }

    /**
     * Sets the value of the reserverno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserverno(String value) {
        this.reserverno = value;
    }

    /**
     * Gets the value of the gencontractmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGencontractmode() {
        return gencontractmode;
    }

    /**
     * Sets the value of the gencontractmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGencontractmode(String value) {
        this.gencontractmode = value;
    }

    /**
     * Gets the value of the contractAuditToDC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractAuditToDC() {
        return contractAuditToDC;
    }

    /**
     * Sets the value of the contractAuditToDC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractAuditToDC(String value) {
        this.contractAuditToDC = value;
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

}
