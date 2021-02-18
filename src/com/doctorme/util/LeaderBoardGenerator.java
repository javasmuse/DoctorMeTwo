package com.doctorme.util;

import com.doctorme.entities.LeaderBoard;
import com.doctorme.entities.Player;
import com.doctorme.xmlreadwrite.XMLWriter;

import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardGenerator {
    XMLWriter xmlw = new XMLWriter();
    private List<LeaderBoard> leaderBoards = new ArrayList<>(); // list to read and repost to the leaderboard


    // READ IN CURRENT LEADERBOARD XML

    // CONTINUALLY UPDATE CURRENT PLAYER XML




    // LEADER BOARD XML WRITER BASED ON LEADERBOARD CLASS USE ARRAY LIST TO FEED IN INFO


    /* NEED TO READ IN THE CURRENT XML
    - POPULATE A LOCAL LIST FROM IT - ADD IT BACK WITH NEW INFO -- ALSO SORT IT BY # POINTS
    -- IF THE JAR TAKES THE XML WITH, IT WILL THEN POPULATE IT DURING THE GAME AND CONTINUALLY 'SAVE'
     */

    public void postPlayerLB(LeaderBoard leaderBoard) throws TransformerException {
        leaderBoards.add(leaderBoard);
        xmlw.xmlWrite(leaderBoards);
    }






}
