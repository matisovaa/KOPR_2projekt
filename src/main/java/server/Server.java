
package server;

import javax.xml.ws.Endpoint;

public class Server {
    
    public static void main(String[] args){
        System.out.println("Server is running");
	Endpoint.publish("http://localhost:8888/evidencia", new EvidenciaService());
    }
    
}
