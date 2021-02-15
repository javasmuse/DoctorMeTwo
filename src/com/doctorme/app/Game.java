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
    private int currQpoints;
    private int currentGameScore= 0;


    // START HERE
    public void startGame() {
        // instantiate and start the GUI
        GameGUI gooey = new GameGUI(gtg.printWelcome(), gtg.printIntro(), gtg.printInstructions());

        bringLocations(); // set locations
        qg.bringQuestions(); // set questions

        // STRETCH GOAL - user given option to choose 'topic' or 'level' and enter their name - before entering game loop

        while(!gooey.isEnteredGame()){  //wait for player to exit initial setup, then set initial values
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // initialize first location and question in GUI
        // current location in game initialized with 'Invictus'
        Location location = listLocas.get(7);

        // initialize question and location fields for first display
        // STOCKS FIRST QUESTION - send in first location
        stockNextQuestion(gooey, location);
        // STOCKS FIRST LOCATION in GUI
        String currLocalDescrip = location.getDescription();
        String typeLocal = location.getType();
        gooey.updateLocationDescription(typeLocal + "\n" +  currLocalDescrip);
        gooey.updateNextLocations(location.getRoomLeadTo());
        gooey.setCurrentScore(0);

        // update GUI
        gooey.guiUpdate();

        while (keepGoing) {     //there will be a sys exit when player hits quit (for now)
            // if the player clicks the "next question" button

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            if (gooey.isHasSubmittedAnswer()){
                System.out.println("Hello");
                if (gooey.hadCorrectAnswer()){
                    setCurrentGameScore(getCurrentGameScore() + currQpoints);
                    gooey.setCurrentScore(getCurrentGameScore());
                    //TODO: CHECK IF USER ANSWERED CORRECTLY removed that one from the room question list
                }
            }else if (gooey.isReadyForNextQuestion()){
//             TODO: get values from GUI and store them, i.e. whether player answered correctly, if they want to change rooms, etc
                // set next Question object in GUI
                stockNextQuestion(gooey, location);
                currLocalDescrip = location.getDescription();
                typeLocal = location.getType();
                gooey.updateLocationDescription(typeLocal + "\n" +  currLocalDescrip);
                gooey.updateNextLocations(location.getRoomLeadTo());

                // update GUI
                gooey.guiUpdate();
            }else if (gooey.isWantsToChangeLocation()){
                String nextDesiredLocation = gooey.getNextLocation();
                //TODO: find matching location and setup
            }
        }
    }

    // STOCK THE QUESTION OBJECT
    private void stockNextQuestion(GameGUI gooey, Location location) {
        Question currQ = qg.nextQuestion(location);
        currQpoints = currQ.getPoints();
        gooey.updateQuestion(currQ.getQuestion());
        gooey.updateOptionA(currQ.getPossibleAnswers().get(0));
        gooey.updateOptionB(currQ.getPossibleAnswers().get(1));
        gooey.updateOptionC(currQ.getPossibleAnswers().get(2));
        gooey.updateOptionD(currQ.getPossibleAnswers().get(3));
        gooey.setCorrectAnswer(conAns.convertCorrectAns(currQ.getCorrectAnswer()));
        gooey.updateHintText(currQ.getHint());
        gooey.updateCurrentLocation(location.getName());

    }


   /* SHOW LOCATION AND QUESTIONS - TYPICAL 'PLAY SCENE'
   --- while loop through
   --- changing location and available questions
   --- recording questions - asked & answered
   --- awarding and tracking player points and badges
   --- track requirements to 'level up' - send to 'end of this level - celebrate screen'
    */
    // IN CODE RE-FACTOR FROM ORIGINAL - RETAIN THEIR README && USE ONE OF THEIR QUESTIONS FOR FINAL QUESTION && REUSE SOME CODE

    // STOCK QUESTION AND LOCATION LISTS- expansion possible for user selected 'topics or level' - alternate xmls

    public void bringLocations() {
        fileName = "resources/locations.xml";
        nodeNameXML = "location";
        listLocas = ll.allLocations(fileName, nodeNameXML);

    }

    /* QUESTION METHODS  are all in the Question Generator*/




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
