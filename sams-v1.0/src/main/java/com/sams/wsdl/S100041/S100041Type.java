
package com.sams.wsdl.S100041;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>S100041Type complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="S100041Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonRqHdr" type="{http://www.example.org/S100041/}CommonRqHdr"/>
 *         &lt;element name="CommonXmlRq" type="{http://www.example.org/S100041/}CommonXmlRq"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "S100041Type", propOrder = {
    "commonRqHdr",
    "commonXmlRq"
})
public class S100041Type {

    @XmlElement(name = "CommonRqHdr", required = true)
    protected CommonRqHdr commonRqHdr;
    @XmlElement(name = "CommonXmlRq", required = true)
    protected CommonXmlRq commonXmlRq;

    /**
     * 获取commonRqHdr属性的值。
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
     * 设置commonRqHdr属性的值。
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
     * 获取commonXmlRq属性的值。
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
     * 设置commonXmlRq属性的值。
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
