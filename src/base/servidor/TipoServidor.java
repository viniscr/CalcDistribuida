package base.servidor;

/** 
 * Define os tipos de operação e de servidores existentes 
 *
 */

public enum TipoServidor {  
    ADD("servidor.ServerBasico"),  
    SUB("servidor.ServerBasico"),  
    MUL("servidor.ServerBasico"),  
    DIV("servidor.ServerBasico"),
    
	RAIZ("servidor.ServerEspecial"),
	PORC("servidor.ServerEspecial"),
	POTE("servidor.ServerEspecial"),
	
	BASICO("servidor.ServerBasico"),
	ESPECIAL("servidor.ServerEspecial");
      
    private String className = null;  
      
      
    private TipoServidor(String className) {  
        this.className = className;      
    }  
      
      
    public String getClassName() {  
        return this.className;  
    }  
} 