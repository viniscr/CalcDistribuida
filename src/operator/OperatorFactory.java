package operator;  
  
import base.operator.OperatorType;  
  
/** 
 * Fábrica de operadores 
 *
 * 
 */  
public class OperatorFactory {  
  
    /** 
     * Fabrica a classe que irá realizar o cálculo de acordo com o tipo 
     * @param ot Tipo de operador solicitado 
     * @return 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */  
    @SuppressWarnings("unchecked")  
    public static Operator getOperator(OperatorType ot) throws ClassNotFoundException, InstantiationException,  
 IllegalAccessException {  
        Operator returnValue = null;  
          
        Class<Operator> clazz = (Class<Operator>) Class.forName(ot.getClassName());  
          
        returnValue =  clazz.newInstance();  
        /* 
        switch (ot) { 
            case ADD: returnValue = new AddOperator(); break; 
            case SUB: returnValue = new SubOperator(); break; 
            case MUL: returnValue = new MulOperator(); break; 
            case DIV: returnValue = new DivOperator(); break; 
        } 
        */  
        return returnValue;  
    }  
}  
