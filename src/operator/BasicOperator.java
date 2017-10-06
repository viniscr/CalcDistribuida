package operator;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import base.operator.OperatorType;

public class BasicOperator implements Operator{

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
        Element fator2  =  children.get(1);
        
        Double mul = null;
        
        if(operacao.equals(String.valueOf(OperatorType.ADD))){
        	mul = Double.parseDouble(fator1.getValue()) + Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando operação de adição...");
        }
        
        if(operacao.equals(String.valueOf(OperatorType.SUB))){
        	mul = Double.parseDouble(fator1.getValue()) - Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando operação de subtracao...");
        }
        
        if(operacao.equals(String.valueOf(OperatorType.MUL))){
        	mul = Double.parseDouble(fator1.getValue()) * Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando operação de multiplicacao...");
        }
        
        if(operacao.equals(String.valueOf(OperatorType.DIV))){
        	mul = Double.parseDouble(fator1.getValue()) / Double.parseDouble(fator2.getValue());
        	System.out.println("Efetuando operação de divisao...");
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
        return "Básico";  
    }
}