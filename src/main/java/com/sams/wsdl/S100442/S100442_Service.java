
package com.sams.wsdl.S100442;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "S100442", targetNamespace = "http://www.example.org/S100442/", wsdlLocation = "file:/E:/\u4ee3\u9500\u7ba1\u7406\u7cfb\u7edf/03\u5de5\u7a0b\u8fc7\u7a0b/3.\u7f16\u7801\u548c\u5355\u5143\u6d4b\u8bd5/coding/sams/src/main/resources/wsdl/S100442.wsdl")
public class S100442_Service
    extends Service
{

    private final static URL S100442_WSDL_LOCATION;
    private final static WebServiceException S100442_EXCEPTION;
    private final static QName S100442_QNAME = new QName("http://www.example.org/S100442/", "S100442");

    static {
    	S100442_WSDL_LOCATION = com.sams.wsdl.S100442.S100442_Service.class.getResource("/wsdl/S100442.wsdl");
        WebServiceException e = null;
        if (S100442_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdl/S100442.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        S100442_EXCEPTION = e;
    }

    public S100442_Service() {
        super(__getWsdlLocation(), S100442_QNAME);
    }

    public S100442_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), S100442_QNAME, features);
    }

    public S100442_Service(URL wsdlLocation) {
        super(wsdlLocation, S100442_QNAME);
    }

    public S100442_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, S100442_QNAME, features);
    }

    public S100442_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public S100442_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns S100442
     */
    @WebEndpoint(name = "S100442SOAP")
    public S100442 getS100442SOAP() {
        return super.getPort(new QName("http://www.example.org/S100442/", "S100442SOAP"), S100442.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns S100442
     */
    @WebEndpoint(name = "S100442SOAP")
    public S100442 getS100442SOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.org/S100442/", "S100442SOAP"), S100442.class, features);
    }

    private static URL __getWsdlLocation() {
        if (S100442_EXCEPTION!= null) {
            throw S100442_EXCEPTION;
        }
        return S100442_WSDL_LOCATION;
    }

}