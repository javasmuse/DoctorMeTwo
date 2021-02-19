package com.doctorme.util;

import com.doctorme.xmlreadwrite.XMLReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameText {
    XMLReader reader = new XMLReader();

    /*
    game or story text feeds from xml and routes through the game text generator via game controller to GUI
     */

    // passes xml file to xml reader and parses it to a List
    public List<String> readInstructions(){
        List<String> instructions = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("GameText.xml"); // added to enable use with JAR
        String nodeNameXML = "textLines";

        NodeList instNode = reader.readXMLFiles(is, nodeNameXML);
        for(int i=0; i<instNode.getLength(); i++){
            Node nod = instNode.item(i);
            if(nod.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) nod;
                instructions.add(eElement.getElementsByTagName("welcome").item(0).getTextContent());
                instructions.add(eElement.getElementsByTagName("intro").item(0).getTextContent());
                instructions.add(eElement.getElementsByTagName("welcomeInstruction").item(0).getTextContent());
                instructions.add(eElement.getElementsByTagName("gameScreenInstruction").item(0).getTextContent());
            }

        }
        return instructions;
    }


}
