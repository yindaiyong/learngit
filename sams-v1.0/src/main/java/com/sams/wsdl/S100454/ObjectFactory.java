
package com.sams.wsdl.S100454;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100454 package. 
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

    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100454/", "CommonRs");
    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100454/", "CommonRq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100454
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
     * Create an instance of {@link S100454TypeResponse }
     * 
     */
    public S100454TypeResponse createS100454TypeResponse() {
        return new S100454TypeResponse();
    }

    /**
     * Create an instance of {@link S100454Type }
     * 
     */
    public S100454Type createS100454Type() {
        return new S100454Type();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link S100454TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100454/", name = "CommonRs")
    public JAXBElement<S100454TypeResponse> createCommonRs(S100454TypeResponse value) {
        return new JAXBElement<S100454TypeResponse>(_CommonRs_QNAME, S100454TypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100454Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100454/", name = "CommonRq")
    public JAXBElement<S100454Type> createCommonRq(S100454Type value) {
        return new JAXBElement<S100454Type>(_CommonRq_QNAME, S100454Type.class, null, value);
    }

}
