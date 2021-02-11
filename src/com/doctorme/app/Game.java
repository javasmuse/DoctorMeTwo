package com.doctorme.app;

import com.doctorme.entities.Location;
import com.doctorme.entities.Question;
import com.doctorme.util.GameText;
import com.doctorme.util.LocationList;
import com.doctorme.util.QuestionList;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // TODO: flow of game is controlled here

    // CURRENTLY USED TO CHECK QUESTIONS -- DELETE WHEN TESTS WRITTEN

    String fileName;
    String nodeNameXML;
    // available lists - questions, locations
    List<Question> listQs = new ArrayList<>();
    List<Location> listLocas = new ArrayList<>();

    // access - question list, location list
    QuestionList ql = new QuestionList();
    LocationList ll = new LocationList();

    public void startGame() {
        // TODO: put what is need to run here
        bringQuestions(); // stock the questions on startup
        printQ(5);
        displayHint(5);
        checkAnswer(5);
        bringLocations();
        printL(4);
        printInstructions();
    }

    // QUESTION ROUTING - TESTING through souts
    // we could bring them in directly from the question list class - but here allows there to be multiple xmls of questions and configure this to send for the user requested list
    public void bringQuestions() {
        fileName = "resources/questionsLevelOne";
        nodeNameXML = "questions";
        listQs = ql.allQuestions(fileName, nodeNameXML);
    }

    // user asks for hint
    public void displayHint(int questID){
        System.out.println(listQs.get(questID).getHint());

    }

    public void checkAnswer(int questID){
        int userInput = 3;
        if (listQs.get(questID).getCorrectAnswer() == userInput ) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public void printQ(int idx) {
        System.out.println(listQs.get(idx));
    }

    // LOCATION ROUTING - TESTING through souts
    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);

    }

    public void printL(int inx) {
        System.out.println(listLocas.get(inx));
    }

    //Instructions
    public void printInstructions(){
        GameText text = new GameText();
        System.out.println(text.readInstructions().get(0));
    }
}
