package servidorPrincipal.conexao;  
  
import java.io.FileNotFoundException;  
import java.io.IOException;  
  
import base.conexao.ServerConnection;
import base.servidor.TipoServidor;  
/** 
 * Responsável pela conexão com os servidores de operação
 * 
 */  
public class EscravoServerConnection extends ServerConnection {  
     
   private TipoServidor serverType = null;  
  
   public EscravoServerConnection(String configFileName, TipoServidor operatorType)  
         throws FileNotFoundException, IOException {  
      super(configFileName);  
      this.serverType = operatorType;  
   }  
  
   @Override  
   public void configure() {  
      this.serverAddress = properties.getProperty(this.serverType + ".address");  
      this.serverPort    = Integer.parseInt(properties.getProperty(this.serverType + ".port"));  
   }  
}