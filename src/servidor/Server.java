package servidor;

import org.jdom.Document;

/** 
 * Interface que será implementada pelos operadores
 * 
 */  
public interface Server {  
      
    /** 
     * Seta o documento XML que será usado no cálculo 
     * @param params Map com os parâmetros 
     */  
      
    void setSourceDocument(Document doc);  
      
    /** 
     * Efetua o cálculo e retorna o documento XML gerado 
     * @return Documento XML produzido com o resultado da operação 
     */  
    Document getTargetDocument();  
      
    /** 
     * Retorna o nome do operador que está sendo usado 
     * @return Uma String contendo o nome do operador que está sendo usado 
     */  
    String getOperatorName();  
}