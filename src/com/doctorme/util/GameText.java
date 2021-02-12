package com.doctorme.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class GameText {
    XMLReader reader = new XMLReader();

    // passes xml file to xml reader and parses it to a List
    public List<String> readInstructions(){
        List<String> instructions = new ArrayList<>();
        NodeList instNode = reader.readXMLFiles("resources/GameText.xml","textLines");
        for(int i=0; i<instNode.getLength(); i++){
            Node nod = instNode.item(i);
            if(nod.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) nod;
                instructions.add(eElement.getElementsByTagName("welcome").item(0).getTextContent());
                instructions.add(eElement.getElementsByTagName("intro").item(0).getTextContent());
                instructions.add(eElement.getElementsByTagName("instructions").item(0).getTextContent());
            }

        }
        return instructions;
    }


}
