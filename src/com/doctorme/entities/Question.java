package com.doctorme.entities;

import java.util.List;

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

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getPoints() {
        return points;
    }

    public String getHint() {
        return hint;
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