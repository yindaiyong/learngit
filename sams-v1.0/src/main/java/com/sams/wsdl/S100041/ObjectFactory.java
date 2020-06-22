
package com.sams.wsdl.S100041;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sams.wsdl.S100041 package. 
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

    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100041/", "CommonRq");
    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100041/", "CommonRs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sams.wsdl.S100041
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewOperationResponse }
     * 
     */
    public NewOperationResponse createNewOperationResponse() {
        return new NewOperationResponse();
    }

    /**
     * Create an instance of {@link S100041TypeResponse }
     * 
     */
    public S100041TypeResponse createS100041TypeResponse() {
        return new S100041TypeResponse();
    }

    /**
     * Create an instance of {@link NewOperation }
     * 
     */
    public NewOperation createNewOperation() {
        return new NewOperation();
    }

    /**
     * Create an instance of {@link S100041Type }
     * 
     */
    public S100041Type createS100041Type() {
        return new S100041Type();
    }

    /**
     * Create an instance of {@link CommonXmlRq }
     * 
     */
    public CommonXmlRq createCommonXmlRq() {
        return new CommonXmlRq();
    }

    /**
     * Create an instance of {@link CommonXmlRs }
     * 
     */
    public CommonXmlRs createCommonXmlRs() {
        return new CommonXmlRs();
    }

    /**
     * Create an instance of {@link CommonRqHdr }
     * 
     */
    public CommonRqHdr createCommonRqHdr() {
        return new CommonRqHdr();
    }

    /**
     * Create an instance of {@link CommonRsHdr }
     * 
     */
    public CommonRsHdr createCommonRsHdr() {
        return new CommonRsHdr();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100041Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100041/", name = "CommonRq")
    public JAXBElement<S100041Type> createCommonRq(S100041Type value) {
        return new JAXBElement<S100041Type>(_CommonRq_QNAME, S100041Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100041TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100041/", name = "CommonRs")
    public JAXBElement<S100041TypeResponse> createCommonRs(S100041TypeResponse value) {
        return new JAXBElement<S100041TypeResponse>(_CommonRs_QNAME, S100041TypeResponse.class, null, value);
    }

}
