//package com.doctorme.util;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class QuestionListTest {
//    QuestionList qlTest = new QuestionList();
//    String fileName = "resources/questionsLevelOne";
//    String nodeName = "question";
//
//    @Test
//    public void allQuestions() {
//        assertNotNull(qlTest.allQuestions(fileName, nodeName));
//    }
//
//    @Test
//    public void answerToQuestions() {
//        assertEquals("astronomy", qlTest.allQuestions(fileName, nodeName).get(2).getType());
//    }
//
//}