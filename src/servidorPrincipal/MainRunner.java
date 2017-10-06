package servidorPrincipal;  
  
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import base.conexao.ServerConnection;
import base.operator.OperatorType;
import base.xml.XMLDocumentIO;
import servidorPrincipal.conexao.OperatorServerConnection;  
  
public class MainRunner implements Runnable {  
  
   private XMLDocumentIO xmlDocumentIO = null;  
     
   public MainRunner(Socket socket) throws IOException {  
      xmlDocumentIO = new XMLDocumentIO(socket);     
   }  
     
   public void run() {  
      try {  
         Document     clientDoc       = xmlDocumentIO.getXMLDocument();  
         //Document     serverDoc       = clientDoc2ServerDoc(clientDoc);
           
         OperatorType operatorType    = getOperatorType(clientDoc);  
           
         ServerConnection con = new OperatorServerConnection("XMLMainServer.properties", operatorType);  
         con.open();  
         con.sendDocumentToServer(clientDoc);  
           
         Document resultDoc = con.getDocumentFromServer();  
           
         xmlDocumentIO.sendXMLDocument(resultDoc);  
      } catch (Exception e) {  
         e.printStackTrace();  
      }     
   }  
     
   /** 
    * Descobre qual o tipo de operação solicitada pelo cliente 
    * @param doc Documento XML que veio do cliente 
    * @return 
    */  
   private OperatorType getOperatorType(Document doc) {  
      System.out.println("Descobrindo o tipo da operacao...");  
      Element root = doc.getRootElement();  
      Element type = root.getChild("type");
      System.out.println(type.getValue());
      return OperatorType.valueOf(type.getValue());  
   }  
     
   /** 
    * Converte o documento XML que veio do cliente para o documento XML 
    * que será enviado ao servidor de operação 
    * @param clientDoc Documento XML recebido do cliente 
    * @return Documento XML que será enviado ao servidor de operação 
    */  
   @SuppressWarnings("unchecked")  
   private Document clientDoc2ServerDoc(Document clientDoc) {  
      Document docServer  = new Document();  
      Element  rootServer = new Element("operation");  
        
      Element rootClient = clientDoc.getRootElement();  
        
      List<Element> children = rootClient.getChildren();  
        
      for (Element chield : children) {  
         if (chield.getName().equalsIgnoreCase("param")) {  
            Element param = new Element("param");  
            param.addContent(chield.getValue());  
            rootServer.addContent(param);  
         }  
      }  
        
      docServer.setRootElement(rootServer);  
      return docServer;  
   }  
}