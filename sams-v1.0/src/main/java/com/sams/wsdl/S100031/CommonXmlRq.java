
package com.sams.wsdl.S100031;

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
 *         &lt;element name="CustName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ShortName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdentityType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdentityNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProvinceCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CityNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdValidBegDate">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdValidEndDate">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BillSendPass">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MobileNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="24"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PhoneNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="22"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PhoneNo2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="22"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FaxNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="24"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZipCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Address">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RiskLevel">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RiskLevelName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EmContact">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EmContactPhone">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindMobile">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindEmail">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindBankNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindBankAcco">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BindNameinBank">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Password">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Recommender">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CustType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RegCustType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IndustryDetail">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="InstiRegAddr">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IndustryIdentityNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TaxIdentityNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LawName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LawIdentityType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LawIdentityNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LawIdValidBegDate">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LawIdValidEndDate">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Contact">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConIdentityType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConIdValiddate">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OldCustNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="QueryPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OutRequestNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OutSystemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OutCustNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrokerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EastCustType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegCapital" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConIdValidDateBeg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContrHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContrHolderIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContrhHolderIdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContrHolderIdValidDateBeg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContrHolderIdValidDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkUnitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FundAcco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Birthday" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Vocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncomeSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "custName",
    "shortName",
    "identityType",
    "identityNo",
    "provinceCode",
    "cityNo",
    "idValidBegDate",
    "idValidEndDate",
    "billSendPass",
    "mobileNo",
    "phoneNo",
    "phoneNo2",
    "email",
    "faxNo",
    "zipCode",
    "address",
    "riskLevel",
    "riskLevelName",
    "emContact",
    "emContactPhone",
    "bindMobile",
    "bindEmail",
    "bindBankNo",
    "bindBankAcco",
    "bindNameinBank",
    "password",
    "recommender",
    "custType",
    "regCustType",
    "industryDetail",
    "instiRegAddr",
    "industryIdentityNo",
    "taxIdentityNo",
    "lawName",
    "lawIdentityType",
    "lawIdentityNo",
    "lawIdValidBegDate",
    "lawIdValidEndDate",
    "contact",
    "conIdentityType",
    "conNo",
    "conIdValiddate",
    "oldCustNo",
    "queryPassword",
    "outRequestNo",
    "outSystemFlag",
    "outCustNo",
    "brokerCode",
    "eastCustType",
    "regCapital",
    "conIdValidDateBeg",
    "contrHolderName",
    "contrHolderIdType",
    "contrhHolderIdNo",
    "contrHolderIdValidDateBeg",
    "contrHolderIdValidDate",
    "corpName",
    "workUnitType",
    "flag",
    "fundAcco",
    "birthday",
    "nationality",
    "vocation",
    "sex",
    "incomeSource"
})
public class CommonXmlRq {

    @XmlElement(name = "CustName", required = true)
    protected String custName;
    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "IdentityType", required = true)
    protected String identityType;
    @XmlElement(name = "IdentityNo", required = true)
    protected String identityNo;
    @XmlElement(name = "ProvinceCode", required = true)
    protected String provinceCode;
    @XmlElement(name = "CityNo", required = true)
    protected String cityNo;
    @XmlElement(name = "IdValidBegDate", required = true)
    protected String idValidBegDate;
    @XmlElement(name = "IdValidEndDate", required = true)
    protected String idValidEndDate;
    @XmlElement(name = "BillSendPass", required = true)
    protected String billSendPass;
    @XmlElement(name = "MobileNo", required = true)
    protected String mobileNo;
    @XmlElement(name = "PhoneNo", required = true)
    protected String phoneNo;
    @XmlElement(name = "PhoneNo2", required = true)
    protected String phoneNo2;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "FaxNo", required = true)
    protected String faxNo;
    @XmlElement(name = "ZipCode", required = true)
    protected String zipCode;
    @XmlElement(name = "Address", required = true)
    protected String address;
    @XmlElement(name = "RiskLevel", required = true)
    protected String riskLevel;
    @XmlElement(name = "RiskLevelName", required = true)
    protected String riskLevelName;
    @XmlElement(name = "EmContact", required = true)
    protected String emContact;
    @XmlElement(name = "EmContactPhone", required = true)
    protected String emContactPhone;
    @XmlElement(name = "BindMobile", required = true)
    protected String bindMobile;
    @XmlElement(name = "BindEmail", required = true)
    protected String bindEmail;
    @XmlElement(name = "BindBankNo", required = true)
    protected String bindBankNo;
    @XmlElement(name = "BindBankAcco", required = true)
    protected String bindBankAcco;
    @XmlElement(name = "BindNameinBank", required = true)
    protected String bindNameinBank;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "Recommender", required = true)
    protected String recommender;
    @XmlElement(name = "CustType", required = true)
    protected String custType;
    @XmlElement(name = "RegCustType", required = true)
    protected String regCustType;
    @XmlElement(name = "IndustryDetail", required = true)
    protected String industryDetail;
    @XmlElement(name = "InstiRegAddr", required = true)
    protected String instiRegAddr;
    @XmlElement(name = "IndustryIdentityNo", required = true)
    protected String industryIdentityNo;
    @XmlElement(name = "TaxIdentityNo", required = true)
    protected String taxIdentityNo;
    @XmlElement(name = "LawName", required = true)
    protected String lawName;
    @XmlElement(name = "LawIdentityType", required = true)
    protected String lawIdentityType;
    @XmlElement(name = "LawIdentityNo", required = true)
    protected String lawIdentityNo;
    @XmlElement(name = "LawIdValidBegDate", required = true)
    protected String lawIdValidBegDate;
    @XmlElement(name = "LawIdValidEndDate", required = true)
    protected String lawIdValidEndDate;
    @XmlElement(name = "Contact", required = true)
    protected String contact;
    @XmlElement(name = "ConIdentityType", required = true)
    protected String conIdentityType;
    @XmlElement(name = "ConNo", required = true)
    protected String conNo;
    @XmlElement(name = "ConIdValiddate", required = true)
    protected String conIdValiddate;
    @XmlElement(name = "OldCustNo")
    protected String oldCustNo;
    @XmlElement(name = "QueryPassword")
    protected String queryPassword;
    @XmlElement(name = "OutRequestNo")
    protected String outRequestNo;
    @XmlElement(name = "OutSystemFlag")
    protected String outSystemFlag;
    @XmlElement(name = "OutCustNo")
    protected String outCustNo;
    @XmlElement(name = "BrokerCode")
    protected String brokerCode;
    @XmlElement(name = "EastCustType")
    protected String eastCustType;
    @XmlElement(name = "RegCapital")
    protected String regCapital;
    @XmlElement(name = "ConIdValidDateBeg")
    protected String conIdValidDateBeg;
    @XmlElement(name = "ContrHolderName")
    protected String contrHolderName;
    @XmlElement(name = "ContrHolderIdType")
    protected String contrHolderIdType;
    @XmlElement(name = "ContrhHolderIdNo")
    protected String contrhHolderIdNo;
    @XmlElement(name = "ContrHolderIdValidDateBeg")
    protected String contrHolderIdValidDateBeg;
    @XmlElement(name = "ContrHolderIdValidDate")
    protected String contrHolderIdValidDate;
    @XmlElement(name = "CorpName")
    protected String corpName;
    @XmlElement(name = "WorkUnitType")
    protected String workUnitType;
    @XmlElement(name = "Flag")
    protected String flag;
    @XmlElement(name = "FundAcco")
    protected String fundAcco;
    @XmlElement(name = "Birthday")
    protected String birthday;
    @XmlElement(name = "Nationality")
    protected String nationality;
    @XmlElement(name = "Vocation")
    protected String vocation;
    @XmlElement(name = "Sex")
    protected String sex;
    @XmlElement(name = "IncomeSource")
    protected String incomeSource;

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the identityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * Sets the value of the identityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityType(String value) {
        this.identityType = value;
    }

    /**
     * Gets the value of the identityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * Sets the value of the identityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityNo(String value) {
        this.identityNo = value;
    }

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the cityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNo() {
        return cityNo;
    }

    /**
     * Sets the value of the cityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNo(String value) {
        this.cityNo = value;
    }

    /**
     * Gets the value of the idValidBegDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdValidBegDate() {
        return idValidBegDate;
    }

    /**
     * Sets the value of the idValidBegDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdValidBegDate(String value) {
        this.idValidBegDate = value;
    }

    /**
     * Gets the value of the idValidEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdValidEndDate() {
        return idValidEndDate;
    }

    /**
     * Sets the value of the idValidEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdValidEndDate(String value) {
        this.idValidEndDate = value;
    }

    /**
     * Gets the value of the billSendPass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillSendPass() {
        return billSendPass;
    }

    /**
     * Sets the value of the billSendPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillSendPass(String value) {
        this.billSendPass = value;
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNo(String value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the phoneNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the value of the phoneNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNo(String value) {
        this.phoneNo = value;
    }

    /**
     * Gets the value of the phoneNo2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNo2() {
        return phoneNo2;
    }

    /**
     * Sets the value of the phoneNo2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNo2(String value) {
        this.phoneNo2 = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the faxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * Sets the value of the faxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNo(String value) {
        this.faxNo = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the riskLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * Sets the value of the riskLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskLevel(String value) {
        this.riskLevel = value;
    }

    /**
     * Gets the value of the riskLevelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskLevelName() {
        return riskLevelName;
    }

    /**
     * Sets the value of the riskLevelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskLevelName(String value) {
        this.riskLevelName = value;
    }

    /**
     * Gets the value of the emContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmContact() {
        return emContact;
    }

    /**
     * Sets the value of the emContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmContact(String value) {
        this.emContact = value;
    }

    /**
     * Gets the value of the emContactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmContactPhone() {
        return emContactPhone;
    }

    /**
     * Sets the value of the emContactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmContactPhone(String value) {
        this.emContactPhone = value;
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
     * Gets the value of the bindBankNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindBankNo() {
        return bindBankNo;
    }

    /**
     * Sets the value of the bindBankNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindBankNo(String value) {
        this.bindBankNo = value;
    }

    /**
     * Gets the value of the bindBankAcco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindBankAcco() {
        return bindBankAcco;
    }

    /**
     * Sets the value of the bindBankAcco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindBankAcco(String value) {
        this.bindBankAcco = value;
    }

    /**
     * Gets the value of the bindNameinBank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindNameinBank() {
        return bindNameinBank;
    }

    /**
     * Sets the value of the bindNameinBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindNameinBank(String value) {
        this.bindNameinBank = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the recommender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommender() {
        return recommender;
    }

    /**
     * Sets the value of the recommender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommender(String value) {
        this.recommender = value;
    }

    /**
     * Gets the value of the custType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustType() {
        return custType;
    }

    /**
     * Sets the value of the custType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustType(String value) {
        this.custType = value;
    }

    /**
     * Gets the value of the regCustType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegCustType() {
        return regCustType;
    }

    /**
     * Sets the value of the regCustType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegCustType(String value) {
        this.regCustType = value;
    }

    /**
     * Gets the value of the industryDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryDetail() {
        return industryDetail;
    }

    /**
     * Sets the value of the industryDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryDetail(String value) {
        this.industryDetail = value;
    }

    /**
     * Gets the value of the instiRegAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstiRegAddr() {
        return instiRegAddr;
    }

    /**
     * Sets the value of the instiRegAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstiRegAddr(String value) {
        this.instiRegAddr = value;
    }

    /**
     * Gets the value of the industryIdentityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryIdentityNo() {
        return industryIdentityNo;
    }

    /**
     * Sets the value of the industryIdentityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryIdentityNo(String value) {
        this.industryIdentityNo = value;
    }

    /**
     * Gets the value of the taxIdentityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxIdentityNo() {
        return taxIdentityNo;
    }

    /**
     * Sets the value of the taxIdentityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxIdentityNo(String value) {
        this.taxIdentityNo = value;
    }

    /**
     * Gets the value of the lawName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawName() {
        return lawName;
    }

    /**
     * Sets the value of the lawName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawName(String value) {
        this.lawName = value;
    }

    /**
     * Gets the value of the lawIdentityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawIdentityType() {
        return lawIdentityType;
    }

    /**
     * Sets the value of the lawIdentityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawIdentityType(String value) {
        this.lawIdentityType = value;
    }

    /**
     * Gets the value of the lawIdentityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawIdentityNo() {
        return lawIdentityNo;
    }

    /**
     * Sets the value of the lawIdentityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawIdentityNo(String value) {
        this.lawIdentityNo = value;
    }

    /**
     * Gets the value of the lawIdValidBegDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawIdValidBegDate() {
        return lawIdValidBegDate;
    }

    /**
     * Sets the value of the lawIdValidBegDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawIdValidBegDate(String value) {
        this.lawIdValidBegDate = value;
    }

    /**
     * Gets the value of the lawIdValidEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawIdValidEndDate() {
        return lawIdValidEndDate;
    }

    /**
     * Sets the value of the lawIdValidEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawIdValidEndDate(String value) {
        this.lawIdValidEndDate = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Gets the value of the conIdentityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConIdentityType() {
        return conIdentityType;
    }

    /**
     * Sets the value of the conIdentityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConIdentityType(String value) {
        this.conIdentityType = value;
    }

    /**
     * Gets the value of the conNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConNo() {
        return conNo;
    }

    /**
     * Sets the value of the conNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConNo(String value) {
        this.conNo = value;
    }

    /**
     * Gets the value of the conIdValiddate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConIdValiddate() {
        return conIdValiddate;
    }

    /**
     * Sets the value of the conIdValiddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConIdValiddate(String value) {
        this.conIdValiddate = value;
    }

    /**
     * Gets the value of the oldCustNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldCustNo() {
        return oldCustNo;
    }

    /**
     * Sets the value of the oldCustNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldCustNo(String value) {
        this.oldCustNo = value;
    }

    /**
     * Gets the value of the queryPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryPassword() {
        return queryPassword;
    }

    /**
     * Sets the value of the queryPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryPassword(String value) {
        this.queryPassword = value;
    }

    /**
     * Gets the value of the outRequestNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutRequestNo() {
        return outRequestNo;
    }

    /**
     * Sets the value of the outRequestNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutRequestNo(String value) {
        this.outRequestNo = value;
    }

    /**
     * Gets the value of the outSystemFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutSystemFlag() {
        return outSystemFlag;
    }

    /**
     * Sets the value of the outSystemFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutSystemFlag(String value) {
        this.outSystemFlag = value;
    }

    /**
     * Gets the value of the outCustNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutCustNo() {
        return outCustNo;
    }

    /**
     * Sets the value of the outCustNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutCustNo(String value) {
        this.outCustNo = value;
    }

    /**
     * Gets the value of the brokerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerCode() {
        return brokerCode;
    }

    /**
     * Sets the value of the brokerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerCode(String value) {
        this.brokerCode = value;
    }

    /**
     * Gets the value of the eastCustType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEastCustType() {
        return eastCustType;
    }

    /**
     * Sets the value of the eastCustType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEastCustType(String value) {
        this.eastCustType = value;
    }

    /**
     * Gets the value of the regCapital property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegCapital() {
        return regCapital;
    }

    /**
     * Sets the value of the regCapital property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegCapital(String value) {
        this.regCapital = value;
    }

    /**
     * Gets the value of the conIdValidDateBeg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConIdValidDateBeg() {
        return conIdValidDateBeg;
    }

    /**
     * Sets the value of the conIdValidDateBeg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConIdValidDateBeg(String value) {
        this.conIdValidDateBeg = value;
    }

    /**
     * Gets the value of the contrHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrHolderName() {
        return contrHolderName;
    }

    /**
     * Sets the value of the contrHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrHolderName(String value) {
        this.contrHolderName = value;
    }

    /**
     * Gets the value of the contrHolderIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrHolderIdType() {
        return contrHolderIdType;
    }

    /**
     * Sets the value of the contrHolderIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrHolderIdType(String value) {
        this.contrHolderIdType = value;
    }

    /**
     * Gets the value of the contrhHolderIdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrhHolderIdNo() {
        return contrhHolderIdNo;
    }

    /**
     * Sets the value of the contrhHolderIdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrhHolderIdNo(String value) {
        this.contrhHolderIdNo = value;
    }

    /**
     * Gets the value of the contrHolderIdValidDateBeg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrHolderIdValidDateBeg() {
        return contrHolderIdValidDateBeg;
    }

    /**
     * Sets the value of the contrHolderIdValidDateBeg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrHolderIdValidDateBeg(String value) {
        this.contrHolderIdValidDateBeg = value;
    }

    /**
     * Gets the value of the contrHolderIdValidDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrHolderIdValidDate() {
        return contrHolderIdValidDate;
    }

    /**
     * Sets the value of the contrHolderIdValidDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrHolderIdValidDate(String value) {
        this.contrHolderIdValidDate = value;
    }

    /**
     * Gets the value of the corpName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * Sets the value of the corpName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorpName(String value) {
        this.corpName = value;
    }

    /**
     * Gets the value of the workUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkUnitType() {
        return workUnitType;
    }

    /**
     * Sets the value of the workUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkUnitType(String value) {
        this.workUnitType = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
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
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthday(String value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the vocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocation() {
        return vocation;
    }

    /**
     * Sets the value of the vocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocation(String value) {
        this.vocation = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the incomeSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeSource() {
        return incomeSource;
    }

    /**
     * Sets the value of the incomeSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeSource(String value) {
        this.incomeSource = value;
    }

}
