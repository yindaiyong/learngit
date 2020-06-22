
package com.sams.wsdl.S100411;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sitco.salebyspdb.wsdl.S100411 package. 
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

    private final static QName _CommonRs_QNAME = new QName("http://www.example.org/S100411/", "CommonRs");
    private final static QName _CommonRq_QNAME = new QName("http://www.example.org/S100411/", "CommonRq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sitco.salebyspdb.wsdl.S100411
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
     * Create an instance of {@link S100411TypeResponse }
     * 
     */
    public S100411TypeResponse createS100411TypeResponse() {
        return new S100411TypeResponse();
    }

    /**
     * Create an instance of {@link S100411Type }
     * 
     */
    public S100411Type createS100411Type() {
        return new S100411Type();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link S100411TypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100411/", name = "CommonRs")
    public JAXBElement<S100411TypeResponse> createCommonRs(S100411TypeResponse value) {
        return new JAXBElement<S100411TypeResponse>(_CommonRs_QNAME, S100411TypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link S100411Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/S100411/", name = "CommonRq")
    public JAXBElement<S100411Type> createCommonRq(S100411Type value) {
        return new JAXBElement<S100411Type>(_CommonRq_QNAME, S100411Type.class, null, value);
    }

}
