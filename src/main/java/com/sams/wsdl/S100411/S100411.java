
package com.sams.wsdl.S100411;

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
@WebService(name = "S100411", targetNamespace = "http://www.example.org/S100411/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface S100411 {


    /**
     * 
     * @param common
     * @return
     *     returns com.sitco.salebyspdb.wsdl.S100411.S100411TypeResponse
     */
    @WebMethod(operationName = "NewOperation", action = "http://www.example.org/S100411/NewOperation")
    @WebResult(name = "CommonRs", targetNamespace = "http://www.example.org/S100411/", partName = "Common")
    public S100411TypeResponse newOperation(
        @WebParam(name = "CommonRq", targetNamespace = "http://www.example.org/S100411/", partName = "Common")
        S100411Type common);

}
