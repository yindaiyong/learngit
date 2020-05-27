
package com.sams.wsdl.S100037;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "S100037", targetNamespace = "http://www.example.org/S100037/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface S100037 {


    /**
     * 
     * @param common
     * @return
     *     returns com.sams.wsdl.S100037.S100037TypeResponse
     */
    @WebMethod(operationName = "S100037", action = "http://www.example.org/S100037/S100037")
    @WebResult(name = "CommonRs", targetNamespace = "http://www.example.org/S100037/", partName = "Common")
    public S100037TypeResponse s100037(
        @WebParam(name = "CommonRq", targetNamespace = "http://www.example.org/S100037/", partName = "Common")
        S100037Type common);

}
