package operator;  
  
import java.util.List;  
  
import org.jdom.Document;  
import org.jdom.Element;  
  
/** 
 * Efetua a subtração 
 *
 * 
 */  
public class SubOperator implements Operator {  
  
    private Document doc = null;  
  
    public void setSourceDocument(Document doc) {  
        this.doc = doc;  
    }  
  
    @SuppressWarnings("unchecked")  
    public Document getTargetDocument() {  
          
        System.out.println("Efetuando operação de subtração...");  
          
        Element root      = doc.getRootElement();  
          
        List<Element> children = root.getChildren();  
        Element minuendo =  children.get(0);  
        Element subtraendo   =  children.get(1);  
  
        Double sub = Double.parseDouble(minuendo.getValue()) - Double.parseDouble(subtraendo.getValue());  
          
        Element result = new Element("result");  
        Element value  = new Element("value");  
          
        value.addContent(String.valueOf(sub));  
        result.addContent(value);  
          
        Document doc = new Document();  
        doc.setRootElement(result);  
        return doc;  
    }  
  
    public String getOperatorName() {  
        return "Subtração";  
    }  
}  