package servidorPrincipal.launch;  
  
import java.io.IOException;

import servidorPrincipal.PrincipalServer;  
  
/** 
 * Lançador do servidor principal 
 * 
 */  
public class PrincipalServerLauncher {  
  
     
   public static void main(String[] args) {  
      
	  if (!isArgsValid(args)) {  
         showUsageMode();  
         System.exit(1);  
      }
        
      int serverPort = Integer.parseInt(args[0]); 
        
      try {  
         PrincipalServer mainServer = new PrincipalServer(serverPort);  
         Thread thread = new Thread(mainServer);  
         thread.start();  
      } catch (IOException e) {  
         // TODO Auto-generated catch block  
         e.printStackTrace();  
      }  
        
   }  
     
   private static boolean isArgsValid(String[] args) {  
      boolean returnValue = true;  
        
      if (args.length != 1) {  
         returnValue = false;  
      } else {  
         try {  
            Integer.parseInt(args[0]);  
            returnValue = true;  
         } catch (Exception e) {  
            returnValue = false;  
         }  
      }  
        
      return returnValue;  
   }  
     
   private static void showUsageMode() {  
      String msg = "uso: java -jar PrincipalServer.jar [porta]\n";  
      msg+="\nonde porta é a porta onde o servidor vai esperar por conexões.\n";  
      System.out.println(msg);  
   }  
}