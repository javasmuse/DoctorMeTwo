package com.doctorme.xmlreadwrite;

import com.doctorme.entities.LeaderBoard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class XMLWriter {

//    String xmlFilePath = "resources/leaderBoard.xml";

    InputStream is = getClass().getClassLoader().getResourceAsStream("leaderBoard.xml");
    URL os = getClass().getClassLoader().getResource("leaderBoard.xml");


    public void xmlWrite(List<LeaderBoard> leeB) throws TransformerException {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("leaderBoard");
            document.appendChild(root);

            // playerInfo element
            Element playerInfo = document.createElement("playerInfo");
            root.appendChild(playerInfo);

            for (int i = 0; i < leeB.size(); i++) {

                // name
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(leeB.get(i).getName()));
                playerInfo.appendChild(name);

                // points
                Element points = document.createElement("points");
                points.appendChild(document.createTextNode(leeB.get(i).getPoints()));
                playerInfo.appendChild(points);

                // badges
                Element badges = document.createElement("badges");
                badges.appendChild(document.createTextNode(leeB.get(i).getBadges()));
                playerInfo.appendChild(badges);

//                // time
//                Element time = document.createElement("time");
//                time.appendChild(document.createTextNode(pTime));
//                playerInfo.appendChild(time);


                // create the xml file
                // transform the DOM Object to an XML File
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
            }

        } catch (
                ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }
}

