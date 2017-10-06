package cliente.aplicacao;  
  
import java.util.ArrayList;  
import java.util.List;  
  
import base.operator.OperatorType;  
import cliente.OperatorClient;  
  
public class ConsoleCalc {  
  
     
   public static void main(String[] args) {
	   
	  /*if (!isArgsValid(args)) {  
         showUsageMode();  
         System.exit(1);  
      }*/  
        
      List<Double> params = new ArrayList<Double>();  
      //params.add(Double.parseDouble(args[0]));  
      //params.add(Double.parseDouble(args[2]));  
        
      //OperatorType operatorType = OperatorType.valueOf(args[1]);  
      
      params.add(36.0);
      params.add(2.3);
      
      
      OperatorType operatorType = OperatorType.PORC;
      
      OperatorClient client = new OperatorClient(params, operatorType);  
      try {  
         Double result =  client.getValue();  
         System.out.println("Resultado: " + result);  
      } catch (Exception e) {  
         e.printStackTrace();
      }   
  
   }  
        
   /** 
    * Verifica se os argumentos são válidos 
    * @param args Argumentos a serem validados 
    * @return 
    */  
   private static boolean isArgsValid(String[] args) {  
      boolean returnValue = true;  
        
      if (args.length != 3) {  
         returnValue =  false;  
      } else {  
         try {  
            Double.parseDouble(args[0]);  
            OperatorType.valueOf(args[1]);  
            Double.parseDouble(args[2]);  
            returnValue = true;  
         } catch (Exception e) {  
            returnValue = false;  
         }  
           
           
      }  
      return returnValue;  
   }  
     
     
   private static void showUsageMode() {  
      String msg = "uso: java -jar Client.jar [valor1] [operacao] [valor2] \n";  
      msg+="\nonde:\n";  
      msg+="valor1...: primeiro valor para o calculo \n";  
      msg+="operacao.: operacao que sera executada, podendo ser:\n";  
        
      OperatorType[] types = OperatorType.values();  
      for (OperatorType type: types) {  
         msg+=type+"\n";  
      }  
      msg+="valor2...: segundo valor para o calculo\n";  
      System.out.println(msg);  
   }  
}