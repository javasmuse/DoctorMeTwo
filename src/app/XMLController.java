package app;

//import entities.Threat;

import java.util.Collection;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.*;

public class XMLController {
   // XMLController xmlc = new XMLController();

    private List<Object> diseases = new ArrayList<>();

    private String xmlFileName;

    public XMLController() {
    }
    // read xml, not sure if this is needed, maybe just use as private
    public void readXML(){
        // push into collection
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File inputFile = new File("resources/test.xml");
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("disease");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Question: " + eElement.getElementsByTagName("question").item(0).getTextContent());
                    System.out.println("Points: " + eElement.getElementsByTagName("points").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    // Parse entities
//    public Collection<Threat> parseXML(){
//        //readXML(this.filename)
//        return null;
//    }
//    public String getXmlFileName() {
//        return xmlFileName;
//    }
//    public void setXmlFileName(String xmlFileName) {
//        this.xmlFileName = xmlFileName;
//    }



}
