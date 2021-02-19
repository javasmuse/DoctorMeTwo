package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertAnswerTest {

    ConvertAnswer convertAnswer = new ConvertAnswer();

    @Test
    public void convertCorrectAnsConverts() {
        assertEquals("B", convertAnswer.convertCorrectAns(1));
    }
}