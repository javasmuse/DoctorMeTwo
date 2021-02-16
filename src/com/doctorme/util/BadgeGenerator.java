package com.doctorme.util;

import com.doctorme.entities.Badge;
import com.doctorme.entities.Question;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class BadgeGenerator {
    XMLReader xmlR = new XMLReader();

    public List<Badge> allBadges() {
        List<Badge> badges = new ArrayList<>();

        NodeList badgeNode = xmlR.readXMLFiles("resources/Badge.xml", "badges");

        for (int i = 0; i < badgeNode.getLength(); i++) {
            Node nod = badgeNode.item(i);

            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nod;

                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                String imageFile = eElement.getElementsByTagName("imageFile").item(0).getTextContent();
                String hexColor = eElement.getElementsByTagName("hexColor").item(0).getTextContent();

                if(name.equals(null)){
                    name = "";
                }
                if(type.equals(null)){
                    type = "";
                }
                if(imageFile.equals(null)){
                    imageFile = "";
                }
                if(hexColor.equals(null)){
                    hexColor = "";
                }

                Badge bad = new Badge(name,imageFile,type,hexColor);
                badges.add(bad);
            }

            }
            return badges;
    }
}
