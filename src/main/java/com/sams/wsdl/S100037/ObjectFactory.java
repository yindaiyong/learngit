
package com.sams.wsdl.S100037;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100037 package. 
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

    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100037/", "CommonRq");
    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100037/", "CommonRs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100037
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link S100037_Type }
     * 
     */
    public S100037_Type createS100037_Type() {
        return new S100037_Type();
    }

    /**
     * Create an instance of {@link S100037TypeResponse }
     * 
     */
    public S100037TypeResponse createS100037TypeResponse() {
        return new S100037TypeResponse();
    }

    /**
     * Create an instance of {@link S100037Type }
     * 
     */
    public S100037Type createS100037Type() {
        return new S100037Type();
    }

    /**
     * Create an instance of {@link S100037Response }
     * 
     */
    public S100037Response createS100037Response() {
        return new S100037Response();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link S100037Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100037/", name = "CommonRq")
    public JAXBElement<S100037Type> createCommonRq(S100037Type value) {
        return new JAXBElement<S100037Type>(_CommonRq_QNAME, S100037Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100037TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100037/", name = "CommonRs")
    public JAXBElement<S100037TypeResponse> createCommonRs(S100037TypeResponse value) {
        return new JAXBElement<S100037TypeResponse>(_CommonRs_QNAME, S100037TypeResponse.class, null, value);
    }

}
