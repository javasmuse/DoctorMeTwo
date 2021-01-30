package entities;

import java.util.ArrayList;

public class Disease {
    private static ArrayList<Disease> diseasesList;
    public String name;
    public String description;
    public String hint;
    public String location;
    public String question;
    public String correctAnswer;
    public int points;
    public ArrayList<Disease> diseaseList;

    public  Disease(){

    }
    public Disease(String name, String description, String hint, String location, String question, String correctAnswer, int points){
        this.name = name;
        this.description = description;
        this.hint = hint;
        this.location = location;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    public Disease(ArrayList<Disease> diseasesList) {
        this.diseaseList = diseasesList;
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

    public static ArrayList<Disease> getDiseasesList() {
        return diseasesList;
    }

    public static void setDiseasesList(ArrayList<Disease> diseasesList) {
        Disease.diseasesList = diseasesList;
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

    // toString


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
