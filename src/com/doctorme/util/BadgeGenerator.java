package com.doctorme.util;

import com.doctorme.entities.Badge;
import com.doctorme.xmlreadwrite.XMLReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BadgeGenerator {
    XMLReader xmlR = new XMLReader();

    /*
    read in Badge xml holding links to png files and route via game controller to GUI
     */

    public List<Badge> allBadges() {
        List<Badge> badges = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("Badge.xml");
        String nodeNameXML = "badges";

        NodeList badgeNode = xmlR.readXMLFiles(is, nodeNameXML);

        for (int i = 0; i < badgeNode.getLength(); i++) {
            Node nod = badgeNode.item(i);

            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nod;

                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String imageFile = eElement.getElementsByTagName("imageFile").item(0).getTextContent();
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                String hexColor = eElement.getElementsByTagName("hexColor").item(0).getTextContent();

                Badge bad = new Badge(name, imageFile, type, hexColor);
                badges.add(bad);
            }

        }
        return badges;
    }
}
