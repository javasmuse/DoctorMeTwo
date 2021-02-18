package com.doctorme.util;

import com.doctorme.entities.LeaderBoard;
import com.doctorme.entities.Player;
import com.doctorme.xmlreadwrite.XMLWriter;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoGenerator {
    XMLWriter xmlw = new XMLWriter();
    List<Player> players = new ArrayList<>();
    List<LeaderBoard> pads = new ArrayList<>();

    LeaderBoard leebor = new LeaderBoard("Max", "150", "8");
    LeaderBoard leebor2 = new LeaderBoard("Wade", "140", "9");
    LeaderBoard leebor3 = new LeaderBoard("Sue", "180", "3");


    // LEADER BOARD XML WRITER BASED ON LEADERBOARD CLASS USE ARRAY LIST TO FEED IN INFO


    /* NEED TO READ IN THE CURRENT XML
    - POPULATE A LOCAL LIST FROM IT - ADD IT BACK WITH NEW INFO -- ALSO SORT IT BY # POINTS
    -- IF THE JAR TAKES THE XML WITH, IT WILL THEN POPULATE IT DURING THE GAME AND CONTINUALLY 'SAVE'
     */

    public void genLeadBored(){
        pads.add(leebor);
        pads.add(leebor2);
        pads.add(leebor3);

        xmlw.xmlWrite(pads);

    }






}
