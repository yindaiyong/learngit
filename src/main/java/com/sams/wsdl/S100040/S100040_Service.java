
package com.sams.wsdl.S100040;

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
@WebServiceClient(name = "S100040", targetNamespace = "http://www.example.org/S100040/", wsdlLocation = "/wsdl/S100040.wsdl")
public class S100040_Service
    extends Service
{

    private final static URL S100040_WSDL_LOCATION;
    private final static WebServiceException S100040_EXCEPTION;
    private final static QName S100040_QNAME = new QName("http://www.example.org/S100040/", "S100040");

    static {
    	S100040_WSDL_LOCATION = com.sams.wsdl.S100040.S100040_Service.class.getResource("/wsdl/S100040.wsdl");
        WebServiceException e = null;
        if (S100040_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdl/S100040.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        S100040_EXCEPTION = e;
    }

    public S100040_Service() {
        super(__getWsdlLocation(), S100040_QNAME);
    }

    public S100040_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), S100040_QNAME, features);
    }

    public S100040_Service(URL wsdlLocation) {
        super(wsdlLocation, S100040_QNAME);
    }

    public S100040_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, S100040_QNAME, features);
    }

    public S100040_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public S100040_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns S100040
     */
    @WebEndpoint(name = "S100040SOAP")
    public S100040 getS100040SOAP() {
        return super.getPort(new QName("http://www.example.org/S100040/", "S100040SOAP"), S100040.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns S100040
     */
    @WebEndpoint(name = "S100040SOAP")
    public S100040 getS100040SOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.org/S100040/", "S100040SOAP"), S100040.class, features);
    }

    private static URL __getWsdlLocation() {
        if (S100040_EXCEPTION!= null) {
            throw S100040_EXCEPTION;
        }
        return S100040_WSDL_LOCATION;
    }

}