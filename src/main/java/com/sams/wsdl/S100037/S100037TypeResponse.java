
package com.sams.wsdl.S100037;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for S100037TypeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="S100037TypeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonRsHdr" type="{http://www.example.org/S100037/}CommonRsHdr"/>
 *         &lt;element name="CommonXmlRs" type="{http://www.example.org/S100037/}CommonXmlRs"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "S100037TypeResponse", propOrder = {
    "commonRsHdr",
    "commonXmlRs"
})
public class S100037TypeResponse {

    @XmlElement(name = "CommonRsHdr", required = true)
    protected CommonRsHdr commonRsHdr;
    @XmlElement(name = "CommonXmlRs", required = true)
    protected CommonXmlRs commonXmlRs;

    /**
     * Gets the value of the commonRsHdr property.
     * 
     * @return
     *     possible object is
     *     {@link CommonRsHdr }
     *     
     */
    public CommonRsHdr getCommonRsHdr() {
        return commonRsHdr;
    }

    /**
     * Sets the value of the commonRsHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonRsHdr }
     *     
     */
    public void setCommonRsHdr(CommonRsHdr value) {
        this.commonRsHdr = value;
    }

    /**
     * Gets the value of the commonXmlRs property.
     * 
     * @return
     *     possible object is
     *     {@link CommonXmlRs }
     *     
     */
    public CommonXmlRs getCommonXmlRs() {
        return commonXmlRs;
    }

    /**
     * Sets the value of the commonXmlRs property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonXmlRs }
     *     
     */
    public void setCommonXmlRs(CommonXmlRs value) {
        this.commonXmlRs = value;
    }

}
