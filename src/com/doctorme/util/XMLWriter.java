package com.doctorme.util;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLWriter {

    String xmlFilePath = "resources/saves.xml";

    public void readXMLFiles() {
        System.out.println("making an attempt to write xml");

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("gameQuestions");
            document.appendChild(root);

            // question element
            Element questions = document.createElement("questions");
            root.appendChild(questions);

            // set an attribute to a staff element
            Attr attr = document.createAttribute("id");
            attr.setValue("99");
            questions.setAttributeNode(attr);
            // alternative to above is staff.setAttribute("id", "99)

            // type
            Element type = document.createElement("type");
            type.appendChild(document.createTextNode("astronomy"));
            questions.appendChild(type);

            // question
            Element question = document.createElement("question");
            question.appendChild(document.createTextNode("How many moons does the Earth have?"));
            questions.appendChild(question);

            // create the xml file
            // transform teh DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            /* If you use
            StreamResult result = new StreamResult(System.out);
            the output will be pushed to the standard output ...
            You can use that for debugging
             */

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
