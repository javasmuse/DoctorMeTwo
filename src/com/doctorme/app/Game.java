package com.doctorme.app;

import com.doctorme.GUI.GameGUI;
import com.doctorme.entities.Badge;
import com.doctorme.entities.Location;
import com.doctorme.entities.Player;
import com.doctorme.entities.Question;
import com.doctorme.util.*;

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
    private List<Badge> badgesEarned = new ArrayList<>();
    // access - question list, location list
    private QuestionList ql = new QuestionList();
    private LocationList ll = new LocationList();
    private QuestionGenerator qg = new QuestionGenerator();
    private Player currentPlayer = new Player();
    private ConvertAnswer conAns = new ConvertAnswer();
    private GameTextGenerator gtg = new GameTextGenerator();
    private Boolean keepGoing = true;
    private Badge badge = new Badge("badge1");
    private int currentGameScore= 0;


    // START HERE
    public void startGame() {
        // instantiate and start the GUI
        GameGUI gooey = new GameGUI(gtg.printWelcome(), gtg.printIntro(), gtg.printInstructions());

        bringLocations(); // set locations
        qg.bringQuestions(); // set questions

        // stretch goal - user given option to choose 'topic' or 'level' and enter their name - before entering game loop


        while(!gooey.isEnteredGame()){  //wait for player to exit initial setup, then set initial values
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // initialize first location and question in GUI
        // current location in game initialized with 'Invictus'
        Location location = listLocas.get(1);

        // initialize question and location fields for first display


        // STOCKS FIRST QUESTION
        stockNextQuestion(gooey, location);

        String currLocalDescrip = location.getDescription();
        String typeLocal = location.getType();
        gooey.updateLocationDescription(typeLocal + "\n" +  currLocalDescrip);
        gooey.updateNextLocations(location.getRoomLeadTo());
        gooey.setCurrentScore(0);

        // update GUI
        gooey.guiUpdate();

        while (keepGoing) {     //there will be a sys exit when player hits quit (for now)
            // if the player clicks the "next question" button

            System.out.println("line 75");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            if (gooey.isReadyForNextQuestion()){
                System.out.println("line 78");
//             TODO: get values from GUI and store them, i.e. whether player answered correctly, if they want to change rooms, etc
//             TODO: CHECK IF USER ANSWERED CORRECTLY removed that one from the room question list

                // set next Question object in GUI
                stockNextQuestion(gooey, location);
                currLocalDescrip = location.getDescription();
                typeLocal = location.getType();
                gooey.updateLocationDescription(typeLocal + "\n" +  currLocalDescrip);
                gooey.updateNextLocations(location.getRoomLeadTo());

                // update GUI
                gooey.guiUpdate();


                //TODO: update score (if necessary). Still needs to be implemented in GUI

            }
        }
    }

    // STOCK THE QUESTION OBJECT
    private void stockNextQuestion(GameGUI gooey, Location location) {
        Question currQ = qg.nextQuestion(location);
        gooey.updateQuestion(currQ.getQuestion());
        gooey.updateOptionA(currQ.getPossibleAnswers().get(0));
        gooey.updateOptionB(currQ.getPossibleAnswers().get(1));
        gooey.updateOptionC(currQ.getPossibleAnswers().get(2));
        gooey.updateOptionD(currQ.getPossibleAnswers().get(3));
        gooey.setCorrectAnswer(conAns.convertCorrectAns(currQ.getCorrectAnswer()));
        gooey.updateHintText(currQ.getHint());
        gooey.updateCurrentLocation(location.getName());
    }

    // RETRIEVE QUESTION BY TYPE
    public List<Question> nextQuestion(List<Question> roomQuestions){
//        roomQuestions.
        return null;
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

    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);

    }

    // IN CODE RE-FACTOR FROM ORIGINAL - RETAIN THEIR README && USE ONE OF THEIR QUESTIONS FOR FINAL QUESTION && REUSE SOME CODE

    /* QUESTION METHODS */

    // HINTS  --- Probably don't need, but verify
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

    public void awardBadge(){
        if(currentPlayer.getPoints()==30){  // changed to 30 - bite sized and keeping in mind creating a winnable game in short time for presentation
            //Would need to create a list of Badges to keep track of what badges is earned by a player? - Player.Badges List
            System.out.println(currentPlayer.getName() + "has earned " + badge.getName());
            badgesEarned.add(badge);
        }
    }




    //Getter and Setter

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
    }
}
