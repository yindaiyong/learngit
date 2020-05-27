
package com.sams.wsdl.S100434;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100434 package. 
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

    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100434/", "CommonRs");
    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100434/", "CommonRq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100434
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link S100434TypeResponse }
     * 
     */
    public S100434TypeResponse createS100434TypeResponse() {
        return new S100434TypeResponse();
    }

    /**
     * Create an instance of {@link S100434Type }
     * 
     */
    public S100434Type createS100434Type() {
        return new S100434Type();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link S100434TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100434/", name = "CommonRs")
    public JAXBElement<S100434TypeResponse> createCommonRs(S100434TypeResponse value) {
        return new JAXBElement<S100434TypeResponse>(_CommonRs_QNAME, S100434TypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100434Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100434/", name = "CommonRq")
    public JAXBElement<S100434Type> createCommonRq(S100434Type value) {
        return new JAXBElement<S100434Type>(_CommonRq_QNAME, S100434Type.class, null, value);
    }

}
