package operatorServer.launch;  
  
import base.operator.OperatorType;
import operatorServer.OperatorServer;  
  
/** 
 * Lançador do servidor de operações 
 * 
 */  
public class OperatorServerLauncher {  
  
    public static void main(String[] args) throws Exception {  
          
        if (!isArgsValid(args)) {  
            showUsageMode();  
            System.exit(1);  
        }
          
        OperatorType operatorType =  OperatorType.valueOf(args[0]);
    	
    	//OperatorType operatorType =  OperatorType.BASICO;
    	
      
        try {  
            OperatorServer operatorServer = new OperatorServer(operatorType);  
            Thread thread = new Thread(operatorServer);  
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
                OperatorType.valueOf(args[0]);  
                returnValue = true;  
            } catch (Exception e) {  
                returnValue = false;  
            }  
        }  
          
        return returnValue;  
    }  
      
    private static void showUsageMode() {  
        String msg = "uso: java -jar OperatorServer.jar [tipo]\n";  
        msg+="\nonde tipo pode ser: BASICO ou ESPECIAL\n";  
    
        System.out.println(msg);  
    }  
} 