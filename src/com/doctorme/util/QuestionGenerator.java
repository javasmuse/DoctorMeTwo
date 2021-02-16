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
        currentRoomQs(location, roomQs); // set this rooms questions
        Question currQ = randoQuestion(roomQs); // return one q from the roomQs list

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


}
