
package com.sams.wsdl.S100434;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for S100434Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="S100434Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonRqHdr" type="{http://www.example.org/S100434/}CommonRqHdr"/>
 *         &lt;element name="CommonXmlRq" type="{http://www.example.org/S100434/}CommonXmlRq"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "S100434Type", propOrder = {
    "commonRqHdr",
    "commonXmlRq"
})
public class S100434Type {

    @XmlElement(name = "CommonRqHdr", required = true)
    protected CommonRqHdr commonRqHdr;
    @XmlElement(name = "CommonXmlRq", required = true)
    protected CommonXmlRq commonXmlRq;

    /**
     * Gets the value of the commonRqHdr property.
     * 
     * @return
     *     possible object is
     *     {@link CommonRqHdr }
     *     
     */
    public CommonRqHdr getCommonRqHdr() {
        return commonRqHdr;
    }

    /**
     * Sets the value of the commonRqHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonRqHdr }
     *     
     */
    public void setCommonRqHdr(CommonRqHdr value) {
        this.commonRqHdr = value;
    }

    /**
     * Gets the value of the commonXmlRq property.
     * 
     * @return
     *     possible object is
     *     {@link CommonXmlRq }
     *     
     */
    public CommonXmlRq getCommonXmlRq() {
        return commonXmlRq;
    }

    /**
     * Sets the value of the commonXmlRq property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonXmlRq }
     *     
     */
    public void setCommonXmlRq(CommonXmlRq value) {
        this.commonXmlRq = value;
    }

}
