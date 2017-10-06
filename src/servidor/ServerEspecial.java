package servidor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import base.servidor.TipoServidor;

public class ServerEspecial implements Server{

	private Document doc = null;  
	  
    public void setSourceDocument(Document doc) {  
        this.doc = doc;  
    }  
  
    @SuppressWarnings("unchecked")  
    public Document getTargetDocument() {  
          
        System.out.println("Efetuando operação...");  
          
        Element root      = doc.getRootElement();  
          
        List<Element> children = root.getChildren();
        String operacao = root.getChild("type").getValue();
        Element fator1  =  children.get(0);  
        //Element fator2  =  children.get(1);
        
        Double mul = null;
        
        if(operacao.equals(String.valueOf(TipoServidor.PORC))){
        	mul = (Double.parseDouble(fator1.getValue()) / 100.0);
        	System.out.println("Efetuando operação de porcentagem...");
        }
        
        if(operacao.equals(String.valueOf(TipoServidor.POTE))){
        	mul = Math.pow(Double.parseDouble(fator1.getValue()), 2);
        	System.out.println("Efetuando operação de Potencia...");
        }
        
        if(operacao.equals(String.valueOf(TipoServidor.RAIZ))){
        	mul = Math.sqrt(Double.parseDouble(fator1.getValue()));
        	System.out.println("Efetuando operação de raiz...");
        }        
        
        Element result = new Element("result");  
        Element value  = new Element("value");  
          
        value.addContent(String.valueOf(mul));  
        result.addContent(value);  
          
        Document doc = new Document();  
        doc.setRootElement(result);  
        return doc;  
    }  
  
      
    public String getOperatorName() {  
        return "Especial";  
    }
}
