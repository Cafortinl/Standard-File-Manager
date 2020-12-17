package standard_file_manager;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLGenerator {

    public XMLGenerator(){
    }
    
    public void generate(String path, String name, ArrayList<Campo> key,ArrayList<Registro> value) throws Exception{ 
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");
            Element raiz = document.getDocumentElement();
            for (int j = 0; j < value.size(); j++) {
                Element itemNode = document.createElement("REGISTER"); 
                for(int i=0; i<key.size();i++){
                    Element CampNode = document.createElement("CAMP");
                    Text nodeCampoValue = document.createTextNode(key.get(i).getNombre());
                    CampNode.appendChild(nodeCampoValue);
                    itemNode.appendChild(CampNode);
                    Element TypeNode = document.createElement("TYPE"); 
                    Text nodeTypeValue;
                    if(key.get(i).isIsChar()){
                        nodeTypeValue = document.createTextNode("Char"); 
                    }else{
                        nodeTypeValue = document.createTextNode("Integer");
                    }
                    TypeNode.appendChild(nodeTypeValue);
                    itemNode.appendChild(TypeNode);
                    Element valueNode = document.createElement("VALUE"); 
                    Text nodeValueValue = document.createTextNode(value.get(j).getPablo().get(i)); 
                    valueNode.appendChild(nodeValueValue);
                    itemNode.appendChild(valueNode);
                    raiz.appendChild(itemNode);
                }         
            }
            Source source = new DOMSource(document);
            System.out.println("NAME: "+path);
            File archivo = new File(path+"/"+name+".xml");
            Result result = new StreamResult(archivo);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }

}
