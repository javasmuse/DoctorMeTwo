package com.doctorme.app;

import com.doctorme.entities.Location;
import com.doctorme.entities.Player;
import com.doctorme.entities.Question;
import com.doctorme.util.LocationList;
import com.doctorme.util.QuestionList;

import java.util.ArrayList;
import java.util.List;

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
        bringQuestions(); // stock the questions on startup - could ask user to choose topic or increase level through input another xml and adding args to method and method call
        bringLocations(); // same but for locations
        currentPlayer = new Player("Rennie"); // set temp current player name - get from GUI on start up
    }

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
        userAnswer = 3; // add
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
}
