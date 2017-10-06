package servidorEscravo;  
  
import java.io.IOException;
import java.net.Socket;

import org.jdom.Document;
import base.xml.XMLDocumentIO;
import servidor.Server;
  
public class EscravoRunner implements Runnable {  
  
    private Server operator = null;  
    private XMLDocumentIO xmlDocumentIO = null;  
      
      
    public EscravoRunner(Socket socket, Server operator) throws IOException {  
        xmlDocumentIO = new XMLDocumentIO(socket);  
        this.operator = operator;  
    }  
      
    public void run() {  
        Document docFromServer;  
        try {  
            docFromServer = xmlDocumentIO.getXMLDocument();  
            operator.setSourceDocument(docFromServer);  
            Document docToServer = operator.getTargetDocument();  
            xmlDocumentIO.sendXMLDocument(docToServer);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  