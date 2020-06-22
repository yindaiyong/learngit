
package com.sams.wsdl.S100442;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100442 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100442/", "CommonRs");
    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100442/", "CommonRq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100442
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link S100442TypeResponse }
     * 
     */
    public S100442TypeResponse createS100442TypeResponse() {
        return new S100442TypeResponse();
    }

    /**
     * Create an instance of {@link S100442Type }
     * 
     */
    public S100442Type createS100442Type() {
        return new S100442Type();
    }

    /**
     * Create an instance of {@link CommonXmlRs }
     * 
     */
    public CommonXmlRs createCommonXmlRs() {
        return new CommonXmlRs();
    }

    /**
     * Create an instance of {@link CommonXmlRq }
     * 
     */
    public CommonXmlRq createCommonXmlRq() {
        return new CommonXmlRq();
    }

    /**
     * Create an instance of {@link CommonRsHdr }
     * 
     */
    public CommonRsHdr createCommonRsHdr() {
        return new CommonRsHdr();
    }

    /**
     * Create an instance of {@link CommonRqHdr }
     * 
     */
    public CommonRqHdr createCommonRqHdr() {
        return new CommonRqHdr();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100442TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100442/", name = "CommonRs")
    public JAXBElement<S100442TypeResponse> createCommonRs(S100442TypeResponse value) {
        return new JAXBElement<S100442TypeResponse>(_CommonRs_QNAME, S100442TypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100442Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100442/", name = "CommonRq")
    public JAXBElement<S100442Type> createCommonRq(S100442Type value) {
        return new JAXBElement<S100442Type>(_CommonRq_QNAME, S100442Type.class, null, value);
    }

}
