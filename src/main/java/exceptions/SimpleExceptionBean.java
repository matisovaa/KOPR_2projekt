
package exceptions;
/*
Zdroj:
https://blog.idrsolutions.com/2013/10/web-services-exception-handling/
*/
public class SimpleExceptionBean {
    private String message;
 
    public SimpleExceptionBean() {
    }
    public SimpleExceptionBean(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }
}