package com.doctorme.util;

import com.doctorme.entities.Question;
import com.doctorme.xmlreadwrite.XMLReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    // FIELDS
    XMLReader xmlR = new XMLReader();

    // GENERATE LIST OF QUESTIONS FROM XML - call and pass in desired params (i.e. filename and node name)
    public List<Question> allQuestions() {

        List<Question> questions = new ArrayList<>();
        // ALLOWS EXPORT TO JAR
        InputStream is = getClass().getClassLoader().getResourceAsStream("questionsLevelOne.xml");
        String nodeNameXML = "questions";

        // pass in file link and node name in a call on XMLReader to return a node list of Questions
        NodeList questNod = xmlR.readXMLFiles(is, nodeNameXML);

        // iterate through the node list to extract each question and store it in an object list of questions
        for (int i = 0; i < questNod.getLength(); i++) {
            //initialize a new node based on index
            Node nod = questNod.item(i);
            // check if type is an element
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                // set new element to case node
                Element eElement = (Element) nod;
                // set Question object fields from xml
                int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                String question = eElement.getElementsByTagName("question").item(0).getTextContent();
                List<String> possibleAnswers = new ArrayList<>();
                possibleAnswers.add(eElement.getElementsByTagName("possibleAnswers1").item(0).getTextContent());
                possibleAnswers.add(eElement.getElementsByTagName("possibleAnswers2").item(0).getTextContent());
                possibleAnswers.add(eElement.getElementsByTagName("possibleAnswers3").item(0).getTextContent());
                possibleAnswers.add(eElement.getElementsByTagName("possibleAnswers4").item(0).getTextContent());
                int correctAnswer = Integer.parseInt(eElement.getElementsByTagName("correctAnswer").item(0).getTextContent()); // correct answer corresponds to index + 1 of possibleAnswers
                int points = Integer.parseInt(eElement.getElementsByTagName("points").item(0).getTextContent());
                String hint = eElement.getElementsByTagName("hint").item(0).getTextContent();

                // new temporary question
                Question questy = new Question(id, type, question, possibleAnswers, correctAnswer, points, hint);

                // add new question to List of Question objects
                questions.add(questy);

            }
        }

        return questions;
    }
}