package com.doctorme.util;

public class ConvertAnswer {

    // CONVERT LOCATION INT OF CORRECT ANSWER TO A B C D
    // Convert here pass to GUI through game controller
    public String convertCorrectAns(int localCA){
        String alphCorrectAns = "";

        switch (localCA) {
            case 0: alphCorrectAns = "A";
                break;
            case 1: alphCorrectAns = "B";
                break;
            case 2: alphCorrectAns = "C";
                break;
            case 3: alphCorrectAns = "D";
                break;
        }
        return alphCorrectAns;
    }
}
