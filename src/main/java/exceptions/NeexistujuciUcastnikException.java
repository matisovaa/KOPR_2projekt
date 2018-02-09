
package exceptions;

import javax.xml.ws.WebFault;

/*
Zdroj:
https://blog.idrsolutions.com/2013/10/web-services-exception-handling/
*/
@WebFault(name="NeexistujuciUcastnikException")
public class NeexistujuciUcastnikException extends Exception{
    
    private SimpleExceptionBean faultBean;
 
    public NeexistujuciUcastnikException(String message, SimpleExceptionBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public NeexistujuciUcastnikException(String message, SimpleExceptionBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public SimpleExceptionBean getFaultInfo(){
        return faultBean;
    }
    
}