package servidorEscravo.launch;  
  
import base.servidor.TipoServidor;
import servidorEscravo.EscravoServer;  
  
/** 
 * Lan�ador do servidor Escravo (ESPECIAL ou B�SICO) 
 * 
 */  
public class EscravoServerLauncher {  
  
    public static void main(String[] args) throws Exception {  
          
        if (!isArgsValid(args)) {  
            showUsageMode();  
            System.exit(1);  
        }
          
        TipoServidor tipoServidor = TipoServidor.valueOf(args[0].toUpperCase());
    	
      
        try {  
            EscravoServer escravoServer = new EscravoServer(tipoServidor);  
            Thread thread = new Thread(escravoServer);  
            thread.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
      
      
    private static boolean isArgsValid(String[] args) {  
        boolean returnValue = true;  
          
        if (args.length != 1) {  
            returnValue = false;  
        } else {  
            try {  
                TipoServidor.valueOf(args[0]);  
                returnValue = true;  
            } catch (Exception e) {  
                returnValue = false;  
            }  
        }  
          
        return returnValue;  
    }  
      
    private static void showUsageMode() {  
        String msg = "uso: java -jar EscravoServer.jar [tipo]\n";  
        msg+="\nonde tipo pode ser: BASICO ou ESPECIAL\n";  
    
        System.out.println(msg);  
    }  
} 