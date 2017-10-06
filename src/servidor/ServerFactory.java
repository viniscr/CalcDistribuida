package servidor;  
  
import base.servidor.TipoServidor;  
  
/** 
 * F�brica de operadores 
 *
 */  
public class ServerFactory {  
  
    /** 
     * Fabrica a classe que ir� realizar o c�lculo de acordo com o tipo da servidor 
     * @param ot Tipo de servidor solicitado 
     * @return 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */  
    @SuppressWarnings("unchecked")  
    public static Server getOperator(TipoServidor ot) throws ClassNotFoundException, InstantiationException,  
 IllegalAccessException {  
        Server returnValue = null;  
          
        Class<Server> clazz = (Class<Server>) Class.forName(ot.getClassName());  
          
        returnValue =  clazz.newInstance();
        
        return returnValue;  
    }  
}  
