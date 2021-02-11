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

    // START HERE
    public void startGame() {
        // TODO: put what is need to run here
        new GameGUI();
        bringQuestions(); // stock the questions on startup - could ask user to choose topic or increase level through input another xml and adding args to method and method call
        bringLocations(); // same but for locations
        currentPlayer = new Player("Rennie"); // set temp current player name - get from GUI on start up
        questionsByType("astronomy");
        questionsByType("body");
        System.out.println("\nWelcome \n Interested in a PhD?" + "\n Or is your favorite Amazon Leadership Principle 'Learn & Be Curious' ? " + "\n Let's Play Doctor Me!");
        printInstructions();
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
    public void checkAnswerByIndex(int questIdx, int userAnswer) {
//        userAnswer = 3; // add
        if (listQs.get(questIdx).getCorrectAnswer() == userAnswer) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
//        System.out.println(typeSpecific.get(3));
     return typeSpecific;
    }

    // Random

    // INSTRUCTIONS DISPLAY
    public String printInstructions(){
        GameText text = new GameText();
        return (text.readInstructions().get(0));
    }

}
