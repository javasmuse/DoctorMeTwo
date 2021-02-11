package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationListTest {

    LocationList llTest = new LocationList();
    String fileName = "resources/locations.xml";
    String nodeName = "locations";

    @Test
    public void allLocations() {
        assertNotNull(llTest.allLocations(fileName, nodeName));
    }

    @Test
    public void checkNameLocations() {
        assertEquals("Cricket", llTest.allLocations(fileName, nodeName).get(11).getName());
    }
}