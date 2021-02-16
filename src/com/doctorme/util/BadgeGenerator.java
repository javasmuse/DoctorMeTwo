package com.doctorme.util;


import com.doctorme.entities.Badge;
import com.doctorme.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class BadgeGenerator {
    private String fileName;
    private String nodeNameXML;
    private BadgeList bl = new BadgeList();
    private List<Badge> listBadges = new ArrayList<>();

    public void bringBadges() {
        fileName = "resources/Badge.xml";
        nodeNameXML = "Badge";
        listBadges = bl.allBadges(fileName, nodeNameXML);
    }

}
