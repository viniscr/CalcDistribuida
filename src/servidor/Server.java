package servidor;

import org.jdom.Document;

/** 
 * Interface que ser� implementada pelos operadores
 * 
 */  
public interface Server {  
      
    /** 
     * Seta o documento XML que ser� usado no c�lculo 
     * @param params Map com os par�metros 
     */  
      
    void setSourceDocument(Document doc);  
      
    /** 
     * Efetua o c�lculo e retorna o documento XML gerado 
     * @return Documento XML produzido com o resultado da opera��o 
     */  
    Document getTargetDocument();  
      
    /** 
     * Retorna o nome do operador que est� sendo usado 
     * @return Uma String contendo o nome do operador que est� sendo usado 
     */  
    String getOperatorName();  
}