
package com.sams.wsdl.S100442;

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
@WebService(name = "S100442", targetNamespace = "http://www.example.org/S100442/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface S100442 {


    /**
     * 
     * @param common
     * @return
     *     returns com.sams.wsdl.S100442.S100442TypeResponse
     */
    @WebMethod(operationName = "S100442", action = "http://www.example.org/S100442/S100442")
    @WebResult(name = "CommonRs", targetNamespace = "http://www.example.org/S100442/", partName = "Common")
    public S100442TypeResponse s100442(
        @WebParam(name = "CommonRq", targetNamespace = "http://www.example.org/S100442/", partName = "Common")
        S100442Type common);

}
