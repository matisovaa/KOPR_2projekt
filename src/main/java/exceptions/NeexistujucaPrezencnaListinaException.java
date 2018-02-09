
package exceptions;

import javax.xml.ws.WebFault;

/*
Zdroj:
https://blog.idrsolutions.com/2013/10/web-services-exception-handling/
*/
@WebFault(name="NeexistujucaPrezencnaListinaException")
public class NeexistujucaPrezencnaListinaException extends Exception{
    
    private SimpleExceptionBean faultBean;
 
    public NeexistujucaPrezencnaListinaException(String message, SimpleExceptionBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public NeexistujucaPrezencnaListinaException(String message, SimpleExceptionBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public SimpleExceptionBean getFaultInfo(){
        return faultBean;
    }
    
}