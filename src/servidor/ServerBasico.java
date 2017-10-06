package servidor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import base.servidor.TipoServidor;

public class ServerBasico implements Server{

	private Document doc = null;  
	  
    public void setSourceDocument(Document doc) {  
        this.doc = doc;  
    }  
  
    @SuppressWarnings("unchecked")  
    public Document getTargetDocument() {  
          
        System.out.println("Efetuando opera��o...");  
          
        Element root = doc.getRootElement();  
          
        List<Element> children = root.getChildren();
        String operacao = root.getChild("type").getValue();
        Element fator1 = children.get(0);  
        Element fator2 = children.get(1);
        
        Double mul = null;
        
        if(operacao.equals(String.valueOf(TipoServidor.ADD))){
        	mul = Double.parseDouble(fator1.getValue()) + Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando opera��o de adi��o...");
        }
        
        if(operacao.equals(String.valueOf(TipoServidor.SUB))){
        	mul = Double.parseDouble(fator1.getValue()) - Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando opera��o de subtracao...");
        }
        
        if(operacao.equals(String.valueOf(TipoServidor.MUL))){
        	mul = Double.parseDouble(fator1.getValue()) * Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando opera��o de multiplicacao...");
        }
        
        if(operacao.equals(String.valueOf(TipoServidor.DIV))){
        	mul = Double.parseDouble(fator1.getValue()) / Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando opera��o de divisao...");
        } 
        
        Element result = new Element("result");  
        Element value = new Element("value");  
          
        value.addContent(String.valueOf(mul));  
        result.addContent(value);  
          
        Document doc = new Document();  
        doc.setRootElement(result);  
        return doc;  
    }  
  
      
    public String getOperatorName() {  
        return "B�sico";  
    }
}