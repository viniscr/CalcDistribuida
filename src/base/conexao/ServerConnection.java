package base.conexao;  
  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
import java.net.UnknownHostException;  
import java.util.Properties;  
  
import org.jdom.Document;  
import org.jdom.JDOMException;  
  
import base.xml.XMLDocumentIO;  
  
  
/** 
 * Respons�vel pela conex�o com os servidores  
 * 
 */  
public abstract class ServerConnection {  
  
   protected Socket socket = null;  
   protected int serverPort = 0;  
   protected String serverAddress = null;  
   protected Properties properties = null;  
   protected String configFileName = null;  
   protected XMLDocumentIO xmlDocumentIO = null;  
     
   /** 
    * Cria uma conex�o baseada no arquivo de configura��o 
    * @param configFileName Arquivo de configura��o para a conex�o 
    * @throws FileNotFoundException 
    * @throws IOException 
    */  
   protected ServerConnection(String configFileName) throws FileNotFoundException, IOException {  
      this.configFileName = configFileName;  
      loadProperties();  
   }  
     
   /** 
    * Carrega as propriedades do arquivo de configura��o 
    * @throws FileNotFoundException 
    * @throws IOException 
    */  
   private void loadProperties() throws FileNotFoundException, IOException {  
      File file = null;  
      FileReader reader = null;  
      file = new File(this.configFileName);   
      reader = new FileReader(file);  
      properties = new Properties();  
      properties.load(reader);  
   }     
     
   /** 
    * M�todo que configura a conex�o 
    */  
   public abstract void configure();  
     
   /** 
    * Abre a conex�o com o servidor 
    * @throws IOException  
    * @throws UnknownHostException  
    */  
   public final void open() throws UnknownHostException, IOException {  
      configure();  
      System.out.println(this.serverAddress + " - " + this.serverPort);  
      this.socket = new Socket(this.serverAddress, this.serverPort);  
      this.xmlDocumentIO = new XMLDocumentIO(this.socket);  
   }  
     
   /** 
    * Retorna o Stream de entrada da conex�o 
    * @return InputStream da conex�o 
    * @throws IOException 
    */  
   public InputStream getInputStream() throws IOException {  
      return this.socket.getInputStream();  
   }  
     
   /** 
    * Retorna o Stream de sa�da da conex�o 
    * @return OutputStream da conex�o 
    * @throws IOException 
    */  
   public OutputStream getOutputStream() throws IOException {  
      return this.socket.getOutputStream();  
   }  
     
   public void sendDocumentToServer(Document doc) throws IOException {  
      this.xmlDocumentIO.sendXMLDocument(doc);  
   }  
     
   public Document getDocumentFromServer() throws IOException, JDOMException {  
      return this.xmlDocumentIO.getXMLDocument();  
   }  
}  