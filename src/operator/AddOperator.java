package operator;  
  
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
  
/** 
 * Efetua a adição 
 *
 * 
 */  
public class AddOperator implements Operator {  
  
    private Document doc = null;  
  
    public void setSourceDocument(Document doc) {  
        this.doc = doc;  
    }  
  
    @SuppressWarnings("unchecked")  
    public Document getTargetDocument() {  
          
        System.out.println("Efetuando operação de adição...");  
          
        Element root      = doc.getRootElement();  
          
        List<Element> children = root.getChildren();  
        Element parcela1       =  children.get(0);  
        Element parcela2       =  children.get(1);  
  
        Double add = Double.parseDouble(parcela1.getValue()) + Double.parseDouble(parcela2.getValue());  
          
        Element result = new Element("result");  
        Element value  = new Element("value");  
         
        value.addContent(String.valueOf(add));  
        result.addContent(value);  
          
        Document doc = new Document();  
        doc.setRootElement(result);  
        return doc;  
    }  
  
    public String getOperatorName() {  
        return "Adição";  
    }  
}  