package servidorPrincipal.launch;  
  
import java.io.IOException;

import servidorPrincipal.MainServer;  
  
/** 
 * Lançador do servidor principal 
 * 
 */  
public class MainServerLauncher {  
  
     
   public static void main(String[] args) {  
      
	  if (!isArgsValid(args)) {  
         showUsageMode();  
         System.exit(1);  
      }
        
      int serverPort = Integer.parseInt(args[0]);
	   //int serverPort = Integer.parseInt("10000"); 
        
      try {  
         MainServer mainServer = new MainServer(serverPort);  
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
      String msg = "uso: java -jar MainServer.jar [porta]\n";  
      msg+="\nonde porta é a porta onde o servidor vai esperar por conexões.\n";  
      System.out.println(msg);  
   }  
}