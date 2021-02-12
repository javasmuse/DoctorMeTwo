package com.doctorme.app;

import com.doctorme.GUI.GameGUI;
import com.doctorme.entities.Location;
import com.doctorme.entities.Player;
import com.doctorme.entities.Question;
import com.doctorme.util.GameText;
import com.doctorme.util.LocationList;
import com.doctorme.util.QuestionList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    // TODO: flow of game is controlled here

    // FIELDS
    private String fileName; // useful to allow user to choose different sets of questions or different rooms, also can be used to level up with insertion of more xml
    private String nodeNameXML; // same as above comment
    // available lists - questions, locations
    private List<Question> listQs = new ArrayList<>();     // list of questions
    private List<Location> listLocas = new ArrayList<>();  // list of locations
    // access - question list, location list
    private QuestionList ql = new QuestionList();
    private LocationList ll = new LocationList();
    private Player currentPlayer = new Player();
    private GameText text = new GameText();
    private Boolean keepGoing = true;


    // START HERE
    public void startGame() {
        // instantiate and start the GUI
        GameGUI gooey = new GameGUI(printWelcome(), printInstructions());
        bringQuestions(); // stock the questions on startup - could ask user to choose topic or increase level through input another xml and adding args to method and method call
        bringLocations(); // same but for locations
        // current location in game initialized with 'entry'
        Location location = listLocas.get(0);

        while (keepGoing) {
            List<Question> bodyQs = questionsByType("body");
            List<Question> astroQs = questionsByType("astronomy");
            Location currentLocation = location;
            gooey.updateQuestion(bodyQs.get(0).getQuestion());
            gooey.updateOptionA(bodyQs.get(0).getPossibleAnswers().get(0));
            gooey.updateOptionB(bodyQs.get(0).getPossibleAnswers().get(1));
            gooey.updateOptionC(bodyQs.get(0).getPossibleAnswers().get(2));
            gooey.updateOptionD(bodyQs.get(0).getPossibleAnswers().get(3));
            gooey.setCorrectAnswer(String.valueOf(bodyQs.get(0).getCorrectAnswer()));
            gooey.updateCurrentLocation(currentLocation.getName());
            gooey.updateLeftLocationButton(currentLocation.getRoomLeadTo().get(1));
            gooey.updateRightLocationButton(currentLocation.getRoomLeadTo().get(0));

        }


        currentPlayer = new Player("Rennie"); // set temp current player name - get from GUI on start up
        questionsByType("astronomy");
        questionsByType("body");

        String sampleQ = questionsByType("body").get(3).getQuestion();
        List<String> possAns = questionsByType("body").get(3).getPossibleAnswers();
        String sampleQH = questionsByType("body").get(3).getHint();
        System.out.println(sampleQ + "\n" + possAns + "\nHint: " + sampleQH);
        System.out.println("test answer: option 3");
        checkAnswerByIndex(3, 2); // checks same q for possible answer of 3rd index

    }


    // SHOW START SCREEN - AND FIRST LOCATION 'ENTRY'

   /* SHOW LOCATION AND QUESTIONS - TYPICAL 'PLAY SCENE'
   --- while loop through
   --- changing location and available questions
   --- recording questions - asked & answered
   --- awarding and tracking player points and badges
   --- track requirements to 'level up' - send to 'end of this level - celebrate screen'
    */

    // STOCK QUESTION AND LOCATION LISTS- expansion possible for user selected 'topics or level' - alternate xmls
    public void bringQuestions() {
        fileName = "resources/questionsLevelOne";
        nodeNameXML = "questions";
        listQs = ql.allQuestions(fileName, nodeNameXML);
    }

    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);

    }

    // RETRIEVE QUESTION BY TYPE
    // CREATE RANDOMIZED LIST OF QUESTIONS BY TYPE

    // IN CODE RE-FACTOR FROM ORIGINAL - RETAIN THEIR README && USE ONE OF THEIR QUESTIONS FOR FINAL QUESTION && REUSE SOME CODE

    /* QUESTION METHODS */

    // HINTS
    // user asks for hint - provide hint for specified question by index
    public String displayHintbyIndex(int questIndx) {
        return listQs.get(questIndx).getHint();
    }
    // user asks for hint - provide hint for specified question by question id number
    public String displayHintbyId(int questID) {
        for (int i = 0; i < listQs.size(); i++) {
            if (listQs.get(i).getId() == questID) {
                return listQs.get(i).getHint();
            }
        }
        return null;
    }

    // CHECK ANSWER
    // check user's answer by question index
    public Boolean checkAnswerByIndex(int questIdx, int userAnswer) {
        if (listQs.get(questIdx).getCorrectAnswer() == userAnswer) {
            return true;
        } else {
            return false;
        }
    }

    // check user's answer by question id
    public void checkAnswerById(int questId, int answerUser) {

        for (int i = 0; i < listQs.size(); i++) {
            if (listQs.get(i).getId() == questId) {
                if (listQs.get(i).getCorrectAnswer() == answerUser) {
                    System.out.println("YES!");
                }
            }
        }
    }


    // QUESTION LIST BY TYPE - user provided type for arg
    public List<Question> questionsByType(String typeHere) {
     List<Question> typeSpecific = listQs.stream()
             .filter(typ -> typ.getType() .equals(typeHere))
             .collect(Collectors.toList());
     return typeSpecific;
    }

    /* LOCATION METHODS */
    // SET START LOCATION
    public Location startLocation(){
        return listLocas.get(0);
    }

    // RANDOM number generator

    //

    // GAME TEXT DISPLAY  - Welcome - Intro - Instructions
    public String printInstructions(){
        return (text.readInstructions().get(2));
    }

    public String printWelcome(){ return (text.readInstructions().get(0)); }

    public String printIntro(){ return text.readInstructions().get(1); }

}
