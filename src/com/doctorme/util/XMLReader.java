//package com.doctorme.util;
//
////import entities.Threat;
//
//import java.io.File;
//
//import com.doctorme.entities.Question;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.Element;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import java.util.*;
//
//public class XMLReader {
//
//    private static ArrayList<Question> pathogens = new ArrayList<>();
//    private String xmlFileName;
//
//
//    public XMLReader() {
//    }
//
//    // read xml, not sure if this is needed, maybe just use as private
//    public static ArrayList<Question> readXML() {
//
//        NodeList nodeList;
//        String name;
//        String description;
//        String hint;
//        String location;
//        String question;
//        String correctAnswer;
//        int points;
//        Question path;
//
//        // push into collection
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            File inputFile = new File("resources/Pathogens.xml");
//            Document doc = db.parse(inputFile);
//            doc.getDocumentElement().normalize();
//           // System.out.println("Root Element:  " + doc.getDocumentElement().getNodeName());
//            nodeList = doc.getElementsByTagName("pathogen");
//
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
//                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
//                    hint = eElement.getElementsByTagName("hint").item(0).getTextContent();
//                    location = eElement.getElementsByTagName("location").item(0).getTextContent();
//                    question = eElement.getElementsByTagName("question").item(0).getTextContent();
//                    correctAnswer = eElement.getElementsByTagName("correctAnswer").item(0).getTextContent();
//                    points = Integer.parseInt(eElement.getElementsByTagName("points").item(0).getTextContent());
//
//                    pathogens.add(i, new Question( name,  description, hint, location, question, correctAnswer, points));
//                    path = new Question(name,  description, hint, location, question, correctAnswer, points);
//                    path.setDiseaseList( pathogens);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pathogens;
//    }
//
//
//}