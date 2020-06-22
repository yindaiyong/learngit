
package com.sams.wsdl.S100031;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100031 package. 
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

    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100031/", "CommonRs");
    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100031/", "CommonRq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100031
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewOperation }
     * 
     */
    public NewOperation createNewOperation() {
        return new NewOperation();
    }

    /**
     * Create an instance of {@link NewOperationResponse }
     * 
     */
    public NewOperationResponse createNewOperationResponse() {
        return new NewOperationResponse();
    }

    /**
     * Create an instance of {@link S100031TypeResponse }
     * 
     */
    public S100031TypeResponse createS100031TypeResponse() {
        return new S100031TypeResponse();
    }

    /**
     * Create an instance of {@link S100031Type }
     * 
     */
    public S100031Type createS100031Type() {
        return new S100031Type();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link S100031TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100031/", name = "CommonRs")
    public JAXBElement<S100031TypeResponse> createCommonRs(S100031TypeResponse value) {
        return new JAXBElement<S100031TypeResponse>(_CommonRs_QNAME, S100031TypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100031Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100031/", name = "CommonRq")
    public JAXBElement<S100031Type> createCommonRq(S100031Type value) {
        return new JAXBElement<S100031Type>(_CommonRq_QNAME, S100031Type.class, null, value);
    }

}
