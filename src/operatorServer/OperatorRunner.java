package operatorServer;  
  
import java.io.IOException;
import java.net.Socket;

import org.jdom.Document;
import base.xml.XMLDocumentIO;
import operator.Operator;
  
public class OperatorRunner implements Runnable {  
  
    private Operator      operator      = null;  
    private XMLDocumentIO xmlDocumentIO = null;  
      
      
    public OperatorRunner(Socket socket, Operator operator) throws IOException {  
        xmlDocumentIO = new XMLDocumentIO(socket);  
        this.operator = operator;  
    }  
      
    public void run() {  
        Document docFromServer;  
        try {  
            docFromServer = xmlDocumentIO.getXMLDocument();  
            operator.setSourceDocument(docFromServer);  
            Document docToServer   = operator.getTargetDocument();  
            xmlDocumentIO.sendXMLDocument(docToServer);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  