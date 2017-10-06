package operator;  
  
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;  
  
/** 
 * Efetua a multiplicação 
 *
 * 
 */  
public class MulOperator implements Operator {  
  
    private Document doc = null;  
  
    public void setSourceDocument(Document doc) {  
        this.doc = doc;  
    }  
  
    @SuppressWarnings("unchecked")  
    public Document getTargetDocument() {  
          
        System.out.println("Efetuando operação de multiplicação...");  
          
        Element root      = doc.getRootElement();  
          
        List<Element> children = root.getChildren();  
        Element fator1  =  children.get(0);  
        Element fator2  =  children.get(1);  
  
        Double mul = Double.parseDouble(fator1.getValue()) * Double.parseDouble(fator2.getValue());  
          
        Element result = new Element("result");  
        Element value  = new Element("value");  
          
        value.addContent(String.valueOf(mul));  
        result.addContent(value);  
          
        Document doc = new Document();  
        doc.setRootElement(result);  
        return doc;  
    }  
  
      
    public String getOperatorName() {  
        return "Multiplicação";  
    }  
}  