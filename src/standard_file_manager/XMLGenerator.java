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

//    public static void main(String[] args) {
//        String nombre_archivo = "geekyxml";
//        ArrayList key = new ArrayList();
//        ArrayList value = new ArrayList();
//
//        key.add("opcion1");
//        value.add("22");
//
//        key.add("opcion2");
//        value.add("22");
//
//        key.add("opcion3");
//        value.add("22");
//
//        key.add("opcion4");
//        value.add("25");
//
//        try { 
//          //  generate(nombre_archivo, key, value);
//        } catch (Exception e) {}
//    }

    public XMLGenerator(){
    }
    
    public void generate(String path, String name, ArrayList<Campo> key,ArrayList<Registro> value) throws Exception{
        for (int j = 0; j < value.size(); j++) {
            for (int i = 0; i < value.get(j).getPablo().size(); i++) {
                System.out.println("<"+value.get(i).getPablo().get(j)+">");
            }
        }
        if(key.isEmpty() || value.isEmpty() || key.size()!=value.size()){
            System.out.println("ERROR empty ArrayList");
            System.out.println(key.size());
            System.out.println(value.size());
            return;
        }else{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for(int i=0; i<key.size();i++){
                //Item Node
                Element itemNode = document.createElement("ITEM"); 
                //Key Node
                Element keyNode = document.createElement("KEY"); 
                Text nodeKeyValue = document.createTextNode(key.get(i).getNombre());
                keyNode.appendChild(nodeKeyValue);      
                //Value Node
                Element valueNode = document.createElement("VALUE"); 
                for (int j = 0; j < value.get(i).getPablo().size(); j++) {
                    Text nodeValueValue = document.createTextNode(value.get(j).getPablo().get(i)); 
                    valueNode.appendChild(nodeValueValue);
                    //append keyNode and valueNode to itemNode
                    itemNode.appendChild(keyNode);
                    itemNode.appendChild(valueNode);
                    //append itemNode to raiz
                    raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
                }
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            System.out.println("NAME: "+path);
            File archivo = new File(path+"/"+name+".xml");
            Result result = new StreamResult(archivo); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }

}
