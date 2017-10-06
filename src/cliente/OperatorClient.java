package cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import base.conexao.ServerConnection;
import base.servidor.TipoServidor;
import cliente.conexao.PrincipalServerConnection;  
  
/** 
 * Cliente
 * 
 */  
public class OperatorClient {  
  
   private List<Double> params = null;  
   private TipoServidor serverType = null;  
     
   private ServerConnection serverConnection = null;  
     
   public OperatorClient(List<Double> params, TipoServidor serverType) {  
      this.params = params;  
      this.serverType = serverType;  
   }  
     
   /** 
    * Lê o resultado da operação 
    * @return Valor da operação  
    * @throws FileNotFoundException 
    * @throws IOException 
    * @throws JDOMException 
    */  
   public Double getValue() throws FileNotFoundException, IOException, JDOMException {  
        
      if (!isParamsValid()) {  
         throw new IllegalArgumentException("Parâmetros inválidos!");  
      }  
        
      serverConnection = new PrincipalServerConnection("XMLClient.properties");  
      serverConnection.open();  
      Document serverDoc = params2XMLDocument();  
        
      serverConnection.sendDocumentToServer(serverDoc);  
      Document clientDoc = serverConnection.getDocumentFromServer();  
      Double returnValue = getValueFromDoc(clientDoc);  
      return returnValue;  
   }  
     
   /** 
    * Lê os parâmetros e gera o documento XML que será enviado ao servidor  
    * principal 
    * @return Documento XML, baseado nos parâmetros e no tipo da operação 
    */  
   private Document params2XMLDocument() {  
        
      Document doc = new Document();  
      Element root = new Element("operation");  
        
      for (Double paramValue: params) {  
         Element param = new Element("param");  
         param.addContent(paramValue.toString());  
         root.addContent(param);  
      }  
      Element type = new Element("type");  
      type.addContent(serverType.toString());  
      root.addContent(type);
      doc.setRootElement(root);  
        
        
      return doc;  
   }  
     
   /** 
    * Verifica se é possível realizar o cálculo com os argumentos 
    * @return verdadeiro se for possível realizar o cálculo, falso caso contrário  
    */  
   private boolean isParamsValid() {  
      return this.params != null && this.params.size() > 0   
          && this.serverType != null;  
   }  
     
     
   /** 
    * Lê o valor (resultado) que veio do documento XML do servidor principal 
    * @param doc Documento XML que veio do servidor principal 
    * @return 
    */  
   private Double getValueFromDoc(Document doc) {  
      Element root = doc.getRootElement();  
      Element value = root.getChild("value");  
      Double returnValue = Double.parseDouble(value.getValue());  
      return returnValue;  
   }  
}