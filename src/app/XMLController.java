package app;

//import entities.Threat;

import java.io.File;

import entities.Word;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.*;
import entities.Disease;

import app.Game;

public class XMLController {
    // XMLController xmlc = new XMLController();
    Game game = new Game();
    //private Disease disease;
    private ArrayList<Disease> diseases = new ArrayList<>();
    private HashMap< String , String[]> wordList = new HashMap();

    private String xmlFileName;


    public HashMap<String, String[]> readWordXML() { // TODO make repairs, until then data is hardcoded
        NodeList nodeList;
        NodeList nodeList2;
        String action;
        String[] synonym = new String[10];
        try {
            DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder db2 = dbf2.newDocumentBuilder();
            File inputFile = new File("resources/Word.xml");
            Document doc = db2.parse(inputFile);
            doc.getDocumentElement().normalize();
            // System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
            nodeList = doc.getElementsByTagName("verb");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    action = eElement.getElementsByTagName("action").item(0).getTextContent();
                    System.out.println(action);
                    String tempSyn;
                    nodeList2 = doc.getElementsByTagName("synonym");
                    for( int j =0; j< nodeList2.getLength(); j++){
                        tempSyn = eElement.getElementsByTagName("synonym").item(0).getTextContent();
                        synonym[j]=tempSyn;
                        System.out.println(synonym[j]+" with j");
                        System.out.println(synonym+"no j");
                    }
                    wordList.put(action, synonym);
                    System.out.println(wordList.size()+"  at line 60");

                }
               // wordList.put(action, synonym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("the word list size is:  "+ wordList.keySet().size());
        return wordList;
    }

    // read xml, not sure if this is needed, maybe just use as private
    public ArrayList<Disease> readXML() {
        Disease threat = new Disease();

        NodeList nodeList;
        String name;
        String description;
        String hint;
        String location;
        String question;
        String correctAnswer;
        int points;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File inputFile = new File("resources/Disease.xml");
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();
           // System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
            nodeList = doc.getElementsByTagName("disease");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    hint = eElement.getElementsByTagName("hint").item(0).getTextContent();
                    location = eElement.getElementsByTagName("location").item(0).getTextContent();
                    question = eElement.getElementsByTagName("question").item(0).getTextContent();
                    correctAnswer = eElement.getElementsByTagName("correctAnswer").item(0).getTextContent();
                    points = Integer.parseInt(eElement.getElementsByTagName("points").item(0).getTextContent());
                    diseases.add(i, new Disease( name,  description, hint, location, question, correctAnswer, points));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diseases;
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
