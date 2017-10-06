package base.operator;  
/** 
 * Define os tipos de operação existentes 
 *
 * 
 */  
public enum OperatorType {  
    ADD("operator.BasicOperator"),  
    SUB("operator.BasicOperator"),  
    MUL("operator.BasicOperator"),  
    DIV("operator.BasicOperator"),
    
	RAIZ("operator.EspecialOperator"),
	PORC("operator.EspecialOperator"),
	POTE("operator.EspecialOperator"),
	
	BASICO("operator.BasicOperator"),
	ESPECIAL("operator.EspecialOperator");
      
    private String className = null;  
      
      
    private OperatorType(String className) {  
        this.className = className;      
    }  
      
      
    public String getClassName() {  
        return this.className;  
    }  
} 