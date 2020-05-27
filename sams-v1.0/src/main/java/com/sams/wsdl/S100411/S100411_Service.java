
package com.sams.wsdl.S100411;

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
@WebServiceClient(name = "S100411", targetNamespace = "http://www.example.org/S100411/", wsdlLocation = "file:/D:/wsdl/S100411.wsdl")
public class S100411_Service
    extends Service
{

    private final static URL S100411_WSDL_LOCATION;
    private final static WebServiceException S100411_EXCEPTION;
    private final static QName S100411_QNAME = new QName("http://www.example.org/S100411/", "S100411");

    static {

        S100411_WSDL_LOCATION = com.sams.wsdl.S100411.S100411_Service.class.getResource("/wsdl/S100411.wsdl");
         WebServiceException e = null;
         if (S100411_WSDL_LOCATION == null) {
             e = new WebServiceException("Cannot find '/wsdl/S100411.wsdl' wsdl. Place the resource correctly in the classpath.");
         }
         S100411_EXCEPTION = e;
     }

    public S100411_Service() {
        super(__getWsdlLocation(), S100411_QNAME);
    }

    public S100411_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), S100411_QNAME, features);
    }

    public S100411_Service(URL wsdlLocation) {
        super(wsdlLocation, S100411_QNAME);
    }

    public S100411_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, S100411_QNAME, features);
    }

    public S100411_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public S100411_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns S100411
     */
    @WebEndpoint(name = "S100411SOAP")
    public S100411 getS100411SOAP() {
        return super.getPort(new QName("http://www.example.org/S100411/", "S100411SOAP"), S100411.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns S100411
     */
    @WebEndpoint(name = "S100411SOAP")
    public S100411 getS100411SOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.org/S100411/", "S100411SOAP"), S100411.class, features);
    }

    private static URL __getWsdlLocation() {
        if (S100411_EXCEPTION!= null) {
            throw S100411_EXCEPTION;
        }
        return S100411_WSDL_LOCATION;
    }

}