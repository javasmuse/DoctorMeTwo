package com.doctorme.app;

import com.doctorme.GUI.GameGUI;
import com.doctorme.entities.Badge;
import com.doctorme.entities.Location;
import com.doctorme.entities.Player;
import com.doctorme.entities.Question;
import com.doctorme.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    // TODO: flow of game is controlled here

    // FIELDS
    private String fileName; // useful to allow user to choose different sets of questions or different rooms, also can be used to level up with insertion of more xml
    private String nodeNameXML; // same as above comment
    // available lists - questions, locations
    private List<Question> listQs = new ArrayList<>();     // list of questions
    private List<Location> listLocas = new ArrayList<>();  // list of locations
    private List<Badge> badges = new ArrayList<>();
    // access - question list, location list
    private QuestionList ql = new QuestionList();
    private LocationList ll = new LocationList();
    private QuestionGenerator qg = new QuestionGenerator();
    private LocationGenerator lg = new LocationGenerator();
    private Player currentPlayer = new Player();
    private ConvertAnswer conAns = new ConvertAnswer();
    private GameTextGenerator gtg = new GameTextGenerator();
    private Boolean keepGoing = true;
//    private Badge badge = new Badge("badge1");
//    private XMLWriter xmlW = new XMLWriter();
//    private QuestionSaver qs = new QuestionSaver();
    private int currQpoints;
    private HashMap<String, Integer> categoryPoints = new HashMap<>();

    // START HERE
    public void startGame() {
        // instantiate and start the GUI
        GameGUI gooey = new GameGUI(gtg.printWelcome(), gtg.printIntro(), gtg.printInstructions());

        lg.bringLocations(); // set locations
        qg.bringQuestions(); // set questions
        setBadges(new BadgeGenerator().allBadges());

        // STRETCH GOAL - user given option to choose 'topic' or 'level' and enter their name - before entering game loop

        while (!gooey.isEnteredGame()) {  //wait for player to exit initial setup, then set initial values

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // INITIALIZE - first location and question in GUI
        // current location - randomized start, same map, changeable if fixed starter preferred
        Location location = lg.startLocation();  // calls to location generator and returns a random room

        // initialize question and location fields for first display
        // STOCKS FIRST QUESTION - send in first location
        stockNextQuestion(gooey, location);
        // STOCKS FIRST LOCATION in GUI
        stockLocation(gooey, location);
        // SET SCORE IN GUI
        gooey.setCurrentScore(0);

        // update GUI
        gooey.guiUpdate();

        while (keepGoing) {     //there will be a sys exit when player hits quit (for now)
            // if the player clicks the "next question" button
            Location currentLocation = location;

            // allows pause for code to enter if
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

//  TODO: get values from GUI and store them, i.e. whether player answered correctly, if they want to change rooms, etc
//  TODO: CHECK IF USER ANSWERED CORRECTLY removed that one from the room question list
//  TODO: update score (if necessary). Still needs to be implemented in GUI

            if (gooey.isHasSubmittedAnswer()) {
                if (gooey.hadCorrectAnswer()) {
                    qg.removeCorrAnsQuest(); // see comment block below
                    /* removes correctly answered question from the list
                    -- need trigger in GUI to convey this,
                    currently responds via sout
                     */

                    // grab current question object and do whatever is needed for points / badge
                    String questType = qg.getCurrQ().getType().toLowerCase();

                    if(categoryPoints.containsKey(questType)){
                        categoryPoints.put(questType,categoryPoints.get(questType)+currQpoints);
                    }
                    else{
                        categoryPoints.put(questType,currQpoints);
                    }

                    if(categoryPoints.get(questType)>=30){
                        awardBadge(questType);
                    }
                    setCurrentGameScore(getCurrentGameScore() + currQpoints);
                    gooey.setCurrentScore(getCurrentGameScore());
                    //TODO: CHECK IF USER ANSWERED CORRECTLY remove that one from the room question list
                }else{
                    gooey.setHasSubmittedAnswer(false);
                }
            }else if (gooey.isReadyForNextQuestion()) {
//             TODO: get values from GUI and store them, i.e. whether player answered correctly, if they want to change rooms, etc
                // set next Question object in GUI
                stockNextQuestion(gooey, location);

                // update GUI
                gooey.guiUpdate();
            } else if (gooey.isWantsToChangeLocation()) {

                // retrieves location name from GUI button press - send String locationName to Location generator for next location to retrieve object location
                location = lg.nextLocation((gooey.getNextLocation()));
                System.out.println(location.getName());

                // use new location to reset room and questions
                stockLocation(gooey, location);
                stockNextQuestion(gooey, location);

                gooey.guiUpdate();
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

    // STOCK THE LOCATION OBJECT
    private void stockLocation(GameGUI gooey, Location location) {
        Location currL = location;

        String currLocalDescrip = currL.getDescription();
        String typeLocal = currL.getType();
        gooey.updateCurrentLocation(currL.getName());  // added in attempt to get name
        gooey.updateLocationDescription("Subject: " + typeLocal + "\n" + "View of room: " + currLocalDescrip);
        gooey.updateNextLocations(location.getRoomLeadTo());


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

    /* QUESTION METHODS  are all in the Question Generator*/
    /* LOCATION METHODS are all in the Location Generator */

    public void awardBadge(String questType) {
        for(Badge bad : badges){
            if(bad.getType().toLowerCase().equals(questType)){
                currentPlayer.addBadge(bad);
                break;
            }
        }
    }

    //Getter and Setter

    public int getCurrentGameScore() {
        return currentPlayer.getPoints();
    }

    public void setCurrentGameScore(int currentGameScore) {
        currentPlayer.setPoints(currentGameScore);
    }

    public List<Badge> getBadges() {
        return badges;
    }

    private void setBadges(List<Badge> badges) {
        this.badges.clear();
        for(Badge badge: badges){
            this.badges.add(badge);
        }
    }
}
