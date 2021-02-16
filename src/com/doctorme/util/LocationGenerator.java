package com.doctorme.util;

import com.doctorme.entities.Location;
import com.doctorme.entities.Question;

import java.util.ArrayList;
import java.util.List;

// TODO: this class - currently all kinds of junk - fix me

public class LocationGenerator {
    private LocationList ll = new LocationList();
    private List<Location> listLocas = new ArrayList<>();
    private String fileName; // available for future use to allow user or designer to choose or change files
    private String nodeNameXML; // ditto ^
    private Location currLoc;
//    private Location current = listLocas.get(1);

    // STOCK LOCATION LIST - expansion possible for user or designer selected 'topics or level' - alternate xmls
    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);
    }

    public Location startLocation(){
        return  randoLocation(listLocas);
    }

    public Location nextLocation(String locationName){

        for (int i = 0; i < listLocas.size(); i++) {
            if(locationName.equals(listLocas.get(i).getName())) {
               currLoc = listLocas.get(i);
               return currLoc;
            }
        }
        return currLoc;
    }

    // retrieve random Question from current room list
    public Location randoLocation(List<Location> locals) {
        return locals.get(randomNumber(locals.size()));
    }

    // RANDOM number generator
    public int randomNumber(int local){
        return (int)(Math.random() * local);
    }






}
