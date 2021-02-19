package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionGeneratorTest {
    QuestionGenerator questionGenerator = new QuestionGenerator();

    @Test
    public void bringQuestions() {
        assertNotNull(questionGenerator.getListQ());
    }

    @Test
    public void randomNumber() {
        assertNotNull(questionGenerator.randomNumber(4));
    }

    @Test
    public void getCurrQ() {
        assertNotNull(questionGenerator.getCurrQ());
    }
}