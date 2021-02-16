package com.doctorme.util;

import com.doctorme.entities.Location;
import com.doctorme.entities.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionGenerator {
    private QuestionList ql = new QuestionList();
    private List<Question> listQ = new ArrayList<>(); // list of questions
    private String fileName; // available for future use to allow user or designer to choose or change files
    private String nodeNameXML; // ditto ^
    private List<Question> currentRoomQues = new ArrayList<>();
    private Question currQ = new Question();
//    List<Question> bodyQs = questionsByType("body");
//    List<Question> astroQs = questionsByType("astronomy");   not needed, set by room type in method questionsByType

    // STOCK QUESTION LIST - expansion possible for user or desinger selected 'topics or level' - alternate xmls
    public void bringQuestions() {
        fileName = "resources/questionsLevelOne";
        nodeNameXML = "questions";
        listQ = ql.allQuestions(fileName, nodeNameXML);
    }

    // SET LIST OF QUESTIONS FOR CURRENT ROOM
    public List<Question> currentRoomQs(Location location, List<Question> localQL){
        List<Question> currentRoomQues = questionsByType(location.getType());
        return currentRoomQues;
    }

    public List<Question> removeCorrAnsQuest(){
        listQ.remove(currQ);
        return listQ;
    }

    // QUESTION LIST BY TYPE - user provided type for arg
    public List<Question> questionsByType(String typeHere) {
        return listQ.stream()
                .filter(typ -> typ.getType() .equals(typeHere))
                .collect(Collectors.toList());
    }

    // INITIALIZE A QUESTION OBJECT
    public Question nextQuestion(Location location){

        // initialize question and location fields for first display
        List<Question> roomQs = questionsByType(location.getType()); // set list of questions by room type
        if (roomQs.isEmpty()) {
            currQ = dummyQuest();
            System.out.println("Sorry there are no more of that type of question. Please change rooms.");
        } else {
            currentRoomQs(location, roomQs); // set this rooms questions
            currQ = randoQuestion(roomQs); // return one q from the roomQs list
        }
        return currQ;
    }

    public Question dummyQuest(){
        List<String> dummy = new ArrayList<>();
        String dumOne = "";
        for(int i = 0; i < 4; i++){
            dummy.add(dumOne);
        }
        currQ = new Question(999, "none", "", dummy, 2, 0, "" );
        return currQ;
    }

    // retrieve random Question from current room list
    public Question randoQuestion(List<Question> quests) {
        return quests.get(randomNumber(quests.size()));
    }

    // RANDOM number generator
    public int randomNumber(int local){
        return (int)(Math.random() * local);
    }

    // ACCESSORS
    public Question getCurrQ() {
        return currQ;
    }

}
