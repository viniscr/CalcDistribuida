package servidorPrincipal;  
  
import java.io.IOException;
import java.net.Socket;
import org.jdom.Document;
import org.jdom.Element;

import base.conexao.ServerConnection;
import base.servidor.TipoServidor;
import base.xml.XMLDocumentIO;
import servidorPrincipal.conexao.EscravoServerConnection;  
  
public class PrincipalRunner implements Runnable {  
  
   private XMLDocumentIO xmlDocumentIO = null;  
     
   public PrincipalRunner(Socket socket) throws IOException {  
      xmlDocumentIO = new XMLDocumentIO(socket);     
   }  
     
   public void run() {  
      try {  
         Document clientDoc = xmlDocumentIO.getXMLDocument();
           
         TipoServidor serverType = getServerType(clientDoc);  
           
         ServerConnection con = new EscravoServerConnection("XMLMainServer.properties", serverType);  
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
   private TipoServidor getServerType(Document doc) {  
      System.out.println("Descobrindo o tipo da operacao...");  
      Element root = doc.getRootElement();  
      Element type = root.getChild("type");
      System.out.println(type.getValue());
      return TipoServidor.valueOf(type.getValue());  
   }  

}