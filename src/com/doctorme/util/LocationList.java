package com.doctorme.util;

import com.doctorme.entities.Location;
import com.doctorme.xmlreadwrite.XMLReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocationList {
    // FIELDS
    XMLReader xmlR = new XMLReader();

    public List<Location> allLocations() {

        List<Location> locations = new ArrayList<>();
        // Used to readXML in JAR
        InputStream is = getClass().getClassLoader().getResourceAsStream("locations.xml");
        String nodeNameXML = "location";

        // pass in file link and node name in a call on XMLReader to return a node list of Questions
        // block level fields
        NodeList locaNod = xmlR.readXMLFiles(is, nodeNameXML);  // change to call from Game w/ choice of xml and sub in correct file with args
        String adjacentRoom, direction, name, type, description;
        int len, id, tierLevel;
        Node node;
        List<String> roomLeadTo, exits;

        //iterate through the node list to extract each location and store it in an object list of locations
        for(int i = 0; i < locaNod.getLength(); i++) {
            // initialize a new node based on index
            node = locaNod.item(i);
            roomLeadTo = new ArrayList<>();
            exits = new ArrayList<>();
            // check if type is an element
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // set new element to case node
                Element eElement = (Element) node;
                // set Location object fields from xml
                id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                name = eElement.getElementsByTagName("name").item(0).getTextContent();
                type = eElement.getElementsByTagName("type").item(0).getTextContent();
                description = eElement.getElementsByTagName("description").item(0).getTextContent();
                tierLevel = Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent());
                len = eElement.getElementsByTagName("exit").getLength();
                for (int x = 0; x < len; x++) {
                    adjacentRoom = eElement.getElementsByTagName("leadTo").item(x).getTextContent();
                    roomLeadTo.add(adjacentRoom);
                    direction = eElement.getElementsByTagName("exit").item(x).getTextContent();
                    exits.add(direction);
                }

                // new temporary location
                Location local = new Location(id, name, type, description, tierLevel, roomLeadTo, exits);

                // add new location to List of Locations
                locations.add(local);
            }
        }
        return locations;
    }
}
