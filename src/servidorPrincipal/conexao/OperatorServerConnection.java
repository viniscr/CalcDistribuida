package servidorPrincipal.conexao;  
  
import java.io.FileNotFoundException;  
import java.io.IOException;  
  
import base.conexao.ServerConnection;  
import base.operator.OperatorType;  
/** 
 * Respons�vel pela conex�o com os servidores de opera��o
 * 
 */  
public class OperatorServerConnection extends ServerConnection {  
     
   private OperatorType operatorType = null;  
  
   public OperatorServerConnection(String configFileName, OperatorType operatorType)  
         throws FileNotFoundException, IOException {  
      super(configFileName);  
      this.operatorType = operatorType;  
   }  
  
   @Override  
   public void configure() {  
      this.serverAddress = properties.getProperty(this.operatorType + ".address");  
      this.serverPort    = Integer.parseInt(properties.getProperty(this.operatorType + ".port"));  
   }  
}