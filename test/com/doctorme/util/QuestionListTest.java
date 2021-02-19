package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionListTest {

    QuestionList questionList = new QuestionList();

    @Test
    public void allQuestionsValidList() {
        assertNotNull(questionList.allQuestions());
    }
}