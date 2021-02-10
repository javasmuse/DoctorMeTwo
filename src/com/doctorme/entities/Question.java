package com.doctorme.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {

    private int id;
    private String hint;
    private String question;
    private int correctAnswer;
    private int points;
    private List<String> possibleAnswers;

    public Question(){
        //default constructor
    }

    public Question(int id, String hint, String question, int correctAnswer, int points, List<String> possibleAnswers) {
        this.id = id;
        this.hint = hint;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.possibleAnswers = possibleAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
