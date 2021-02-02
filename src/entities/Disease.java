package entities;

import java.util.ArrayList;
import java.util.Objects;

public class Disease {

    private String name;
    private String description;
    private String hint;
    private String location;
    private String question;
    private String correctAnswer;
    private int points;
    private ArrayList<Disease> diseaseList;

    public  Disease(){

    }

    public Disease(ArrayList<Disease> diseasesList) {
        this.diseaseList = diseasesList;
    }

    public Disease(String name, String description,
                   String hint, String location,
                   String question, String correctAnswer,
                   int points){
//        this(diseasesList);
        setName(name);
        setDescription(description);
        setHint(hint);
        setLocation(location);
        setQuestion(question);
        setCorrectAnswer(correctAnswer);
        setPoints(points);
    }



    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(ArrayList<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    @Override
    public boolean equals(Object comparedDisease) {
        if (this == comparedDisease) return true;
        if (comparedDisease == null || getClass() != comparedDisease.getClass()) return false;
        Disease disease = (Disease) comparedDisease;
        return Objects.equals(getName(), disease.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Disease: " +
                "name=  " + name + "  " +
                ", description='" + description + "  " +
                ", hint= '" + hint + "  " +
                ", location= '" + location + "  " +
                ", question= '" + question + "  " +
                ", correctAnswer=  '" + correctAnswer +"  "+
                ", points= " + points;
    }


}
