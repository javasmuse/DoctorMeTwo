package com.doctorme.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {

    // FIELDS
    private int id;
    private String type;
    private String question;
    private List<String> possibleAnswers;
    private int correctAnswer;
    private int points;
    private String hint;

    // CONSTRUCTOR
    public Question(int id, String type, String question, List<String> possibleAnswers, int correctAnswer, int points, String hint) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.hint = hint;
    }

    public Question() {
        //no-arg
    }


    // ACCESSORS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    // Custom toString
    @Override
    public String toString() {
        return "Question: " + question + "\n" +
                "A. " + possibleAnswers.get(0) + "\n" +
                "B. " + possibleAnswers.get(1) + "\n" +
                "C. " + possibleAnswers.get(2) + "\n" +
                "D. " + possibleAnswers.get(3) + "\n" +
                "Your answer >";
    }
}