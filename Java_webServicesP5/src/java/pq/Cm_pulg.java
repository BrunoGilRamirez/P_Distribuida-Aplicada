/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pq;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author bruno
 */
@WebService(serviceName = "NewWebService")
public class Cm_pulg {


    @WebMethod(operationName = "cm_pulg")
    public float cm_pulg(@WebParam(name = "cm") float cm) {
        //TODO write your implementation code here:
        
        return (float) (cm/2.54);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "pulcm")
    public float pulcm(@WebParam(name = "pulga") float pulga) {
        //TODO write your implementation code here:
        return (float) (pulga*2.54);
    }
}
