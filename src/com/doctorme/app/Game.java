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
        GameGUI gooey = new GameGUI(printWelcome(), printIntro(), printInstructions());
        bringQuestions(); // stock the questions on startup - could ask user to choose topic or increase level through input another xml and adding args to method and method call
        bringLocations(); // same but for locations
        // current location in game initialized with 'Invictus'
        Location location = listLocas.get(1);
        List<Question> bodyQs = questionsByType("body");
        List<Question> astroQs = questionsByType("astronomy");

        while(!gooey.isEnteredGame()){  //wait for player to exit initial setup, then set initial values
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // initialize first location and question in GUI

        // initialize question and location fields for first display
        String questionG = bodyQs.get(0).getQuestion();
        String optionA = bodyQs.get(0).getPossibleAnswers().get(0);
        String optionB = bodyQs.get(0).getPossibleAnswers().get(1);
        String optionC = bodyQs.get(0).getPossibleAnswers().get(2);
        String optionD = bodyQs.get(0).getPossibleAnswers().get(3);
        String correctAns = convertCorrectAns(bodyQs.get(0).getCorrectAnswer());
        String hint = bodyQs.get(0).getHint();
        String currentLocation = location.getName();
        String leadLocation1 = location.getRoomLeadTo().get(1);
        String leadLocation2 = location.getRoomLeadTo().get(0);

        gooey.updateQuestion(questionG);
        gooey.updateOptionA(optionA);
        gooey.updateOptionB(optionB);
        gooey.updateOptionC(optionC);
        gooey.updateOptionD(optionD);
        gooey.setCorrectAnswer(correctAns);
        gooey.updateHintText(hint);
        System.out.println(hint);
        System.out.println(questionG);
        gooey.updateCurrentLocation(currentLocation);
        gooey.updateLeftLocationButton(leadLocation1);
        gooey.updateRightLocationButton(leadLocation2);
        gooey.guiUpdate();

        currentPlayer = new Player("Rennie"); // set temp current player name - get from GUI on start up
        questionsByType("astronomy");
        questionsByType("body");

        while (keepGoing) {     //there will be a sys exit when player hits quit (for now)
            if (gooey.isReadyForNextQuestion()){
                //TODO: get values from GUI and store them, i.e. whether player answered correctly, if they want to change rooms, etc
//                Location currentLocation = location;
//                gooey.updateQuestion(bodyQs.get(0).getQuestion());
//                gooey.updateOptionA(bodyQs.get(0).getPossibleAnswers().get(0));
//                gooey.updateOptionB(bodyQs.get(0).getPossibleAnswers().get(1));
//                gooey.updateOptionC(bodyQs.get(0).getPossibleAnswers().get(2));
//                gooey.updateOptionD(bodyQs.get(0).getPossibleAnswers().get(3));
//                gooey.setCorrectAnswer(String.valueOf(bodyQs.get(0).getCorrectAnswer()));
//                gooey.updateCurrentLocation(currentLocation.getName());
//                gooey.updateLeftLocationButton(currentLocation.getRoomLeadTo().get(1));
//                gooey.updateRightLocationButton(currentLocation.getRoomLeadTo().get(0));
                //TODO: update score (if necessary). Still needs to be implemented in GUI
                gooey.guiUpdate();
            }
        }
    }

    // Track current games question list by type - removing correct answers
    public List<Question> trakQuestion(List<Question> locall, String typ){


        return null;
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
    // check user's answer by question index -- checking is done on GUI side
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

    // CONVERT LOCATION INT OF CORRECT ANSWER TO A B C D
    public String convertCorrectAns(int localCA){
        String alphCorrectAns = "";

        switch (localCA) {
            case 0: alphCorrectAns = "A";
            break;
            case 1: alphCorrectAns = "B";
            break;
            case 2: alphCorrectAns = "C";
            break;
            case 3: alphCorrectAns = "D";
            break;
        }
        return alphCorrectAns;
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
    public int randomNumber(int local){
        return (int)(Math.random() * local);
    }

    //

    // GAME TEXT DISPLAY  - Welcome - Intro - Instructions
    public String printInstructions(){
        return (text.readInstructions().get(2));
    }

    public String printWelcome(){ return (text.readInstructions().get(0)); }

    public String printIntro(){ return text.readInstructions().get(1); }

}
