package com.doctorme.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class GameText {
    XMLReader reader = new XMLReader();

    public List<String> readInstructions(){
        List<String> instructions = new ArrayList<>();
        NodeList instNode = reader.readXMLFiles("resources/Instructions.xml","instruct");
        for(int i=0; i<instNode.getLength(); i++){
            Node nod = instNode.item(i);
            if(nod.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) nod;
                instructions.add(eElement.getElementsByTagName("one").item(0).getTextContent());
            }

        }
        return instructions;
    }


}
