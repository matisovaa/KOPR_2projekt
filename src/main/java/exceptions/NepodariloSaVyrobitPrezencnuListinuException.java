
package exceptions;

import javax.xml.ws.WebFault;

/*
Zdroj:
https://blog.idrsolutions.com/2013/10/web-services-exception-handling/
*/
@WebFault(name="NepodariloSaVyrobitPrezencnuListinu")
public class NepodariloSaVyrobitPrezencnuListinuException extends Exception{
    
    private SimpleExceptionBean faultBean;
 
    public NepodariloSaVyrobitPrezencnuListinuException(String message, SimpleExceptionBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public NepodariloSaVyrobitPrezencnuListinuException(String message, SimpleExceptionBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public SimpleExceptionBean getFaultInfo(){
        return faultBean;
    }
    
}
