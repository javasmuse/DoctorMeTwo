package com.doctorme.util;

import com.doctorme.entities.Location;

import java.util.ArrayList;
import java.util.List;

// TODO: this class - currently all kinds of junk - fix me

public class LocationGenerator {
    private LocationList ll = new LocationList();
    private List<Location> listLocas = new ArrayList<>();
    private Location current = listLocas.get(1);
    private String fileName; // available for future use to allow user or designer to choose or change files
    private String nodeNameXML; // ditto ^

    // STOCK LOCATION LIST - expansion possible for user or designer selected 'topics or level' - alternate xmls
    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);
    }

    public Location changeRoom(Location currentLocal, String nameLocal){
        String nameRoom = currentLocal.getName();

        List<String> listLeadTo = currentLocal.getRoomLeadTo();
//        for (int i =0; i < listLeadTo.size(); i++ ){
//            if (listLeadTo.get(i) == )
//        }  -- not yet implemented
//        Location newLocation = currentLocal.getRoomLeadTo();


        return null;
    }


}
