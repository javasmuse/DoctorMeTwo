package com.doctorme.GUI;

import com.doctorme.entities.Badge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI implements ActionListener {
    private JButton mapCloseBtn, mapBtn, quitBtn, helpBtn, submit, hintBtn, enterGameBtn, leftTopLocBtn, rightTopLocBtn, leftBotLocBtn, rightBotLocBtn, helpCloseBtn;
    private List<String> nextLocs = new ArrayList<>();
    private Container content;
    private final JFrame window = new JFrame();
    private JFrame helpWindow, mapWindow;
    private JLabel timeToAnswer, gameMap, mapTitle, name, gameDescription, currLocation, welcomeTitle, badgeTitle, scoreTitle, scoreBox, progressTitle, progressBox, correctLabel, incorrectLabel, badge1, badge2, badge3, badge4, badge5, badge6, badge7, badge8, badge9, badge10, badge11, badge12;
    private JPanel descriptionPanel, questionPanel, currLocationPanel, answerPanel, badgePanel, scorePanel, enterGamePanel;
    private JTextArea helpText, gameInstructions, questionText, descriptionText, hintText, noMoreQuestionsText;
    private JRadioButton optA, optB, optC, optD;
    private static final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 32);
    private static final Font SUB_TITLE_FONT = new Font("Times New Roman", Font.BOLD, 16);
    private static final Font QUESTION_FONT = new Font("Times New Roman", Font.ITALIC, 16);
    private static final Font NORMAL_FONT = new Font("Times New Roman", Font.PLAIN, 16);
    private static final Font ANSWER_FONT = new Font("Times New Roman", Font.BOLD, 24);
    private static final Font NO_QUESTIONS_FONT = new Font("Times New Roman", Font.BOLD, 28);
    private JScrollPane scrollPane;
    int currentScore, badgeProgress;
    private String correctAnswer, nextLocation, playerName, backgroundHexColor, locationHexColor, buttonHexColor;
    private ButtonGroup radioGroup;
    private boolean readyForNextQuestion, hasCorrectAnswer, enteredGame, wantsToChangeLocation, hasSubmittedAnswer, badgeEarned;
    private JTextField userName;
    private List<Badge> badges = new ArrayList<>();
    private List<JLabel> badgeLabels = new ArrayList<>();
    private long startQuestion,endQuestion, startBadgeTimer, endBadgeTimer;

    public GameGUI(String introTitle, String introText, String introInstructions){
        setHasCorrectAnswer(false);
        setReadyForNextQuestion(false);
        setEnteredGame(false);
        setHasSubmittedAnswer(false);
        setBackgroundHexColor("#ADC7D9");
        setLocationHexColor("#043769");

        //Setting the GUI window
        window.setSize(1050,540);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode(getBackgroundHexColor()));

        //Welcome Title
        welcomeTitle = new JLabel(introTitle, SwingConstants.CENTER);
        welcomeTitle.setBounds(50,10,950,35);
        welcomeTitle.setForeground(Color.black);
        welcomeTitle.setFont(TITLE_FONT);
        content.add(welcomeTitle);

        gameDescription = new JLabel(introText, SwingConstants.CENTER);
        gameDescription.setBounds(50,45,950,30);
        gameDescription.setForeground(Color.black);
        gameDescription.setFont(NORMAL_FONT);
        content.add(gameDescription);


        //----Help or Instruction of the game
        enterGamePanel = new JPanel();
        enterGamePanel.setBounds(50,90, 950, 350);
        enterGamePanel.setBackground(Color.white);
//        enterGamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(enterGamePanel);

        gameInstructions = new JTextArea();
        gameInstructions.setText(introInstructions);
        gameInstructions.setBounds(0,0,950,350);
        gameInstructions.setForeground(Color.black);
        gameInstructions.setFont(QUESTION_FONT);
        gameInstructions.setLineWrap(true);
        gameInstructions.setWrapStyleWord(true);
        gameInstructions.setEditable(false);
        enterGamePanel.add(gameInstructions);


//        scrollPane = new JScrollPane(gameInstructions);
//        scrollPane.setPreferredSize(new Dimension(950,350));
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        enterGamePanel.add(scrollPane);

        //EnterGame button
        enterGameBtn = new JButton();
        enterGameBtn.setBounds(475, 450, 100, 30);
        enterGameBtn.setText("Enter Game");
//         enterGameBtn.setBackground(Color.white);
//         enterGameBtn.setForeground(Color.black);
//         enterGameBtn.setFont(normalFont);
        enterGameBtn.setVisible(true);
        enterGameBtn.addActionListener(this);
        content.add( enterGameBtn);

        //label to prompt player for name
        name = new JLabel("Player Name:");
        name.setBounds(220,450,100,30);
        content.add(name);

        //label for player to enter their name
        userName = new JTextField();
        userName.setBounds(300,450,150,30);
        content.add(userName);

        window.repaint();
        window.revalidate();
    }

    //*************** EVENT LISTENER ***************
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enterGameBtn){
            setPlayerName(userName.getText());
            content.removeAll();
            window.repaint();
            window.revalidate();
            setup();
            setEnteredGame(true);
        }else if(e.getSource() == mapBtn){
            displayMapWindow();
        }else if(e.getSource() == helpBtn){
            displayHelpWindow();
        }else if(e.getSource() == helpCloseBtn){
            helpWindow.dispose();
        }else if(e.getSource() == mapCloseBtn){
            mapWindow.dispose();
        }
        else if(e.getSource() == quitBtn) {
            window.dispose();
            System.exit(0);
        }else if(e.getSource() == hintBtn){
            hintText.setVisible(true);
        }else if(e.getSource() == submit && submit.getText().equals("Submit")) {
            endQuestion = System.currentTimeMillis();
            checkAnswer();
            setHasSubmittedAnswer(true);
        }else if(e.getSource() == submit && submit.getText().equals("Next Question")){
            timeToAnswer.setText("");
            startQuestion = System.currentTimeMillis();
            setReadyForNextQuestion(true);
        }else if(e.getSource() == leftTopLocBtn){
            timeToAnswer.setText("");
            startQuestion = System.currentTimeMillis();
            startBadgeTimer = System.currentTimeMillis();
            setNextLocation(nextLocs.get(0));
            setWantsToChangeLocation(true);
        }else if(e.getSource() == rightTopLocBtn) {
            timeToAnswer.setText("");
            startQuestion = System.currentTimeMillis();
            startBadgeTimer = System.currentTimeMillis();
            setNextLocation(nextLocs.get(1));
            setWantsToChangeLocation(true);
        }else if(e.getSource() == leftBotLocBtn) {
            timeToAnswer.setText("");
            startQuestion = System.currentTimeMillis();
            startBadgeTimer = System.currentTimeMillis();
            setNextLocation(nextLocs.get(2));
            setWantsToChangeLocation(true);
        }else if(e.getSource() == rightBotLocBtn){
            timeToAnswer.setText("");
            startQuestion = System.currentTimeMillis();
            startBadgeTimer = System.currentTimeMillis();
            setNextLocation(nextLocs.get(3));
            setWantsToChangeLocation(true);
        }else{
            System.out.println("Unrecognized event");
        }
    }

    //*************** ACCESSORY METHODS ***************
    public void guiUpdate(){
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(false);
        hintText.setVisible(false);
        radioGroup.clearSelection();
        submit.setText("Submit");
        clearButtons();
        buttonSetup();

        window.repaint();
        window.revalidate();
    }

    private void checkAnswer(){
        submit.setText("Next Question");
        timeToAnswer.setText("You took " + ((endQuestion - startQuestion)/1000) + " seconds to answer the question!");
        timeToAnswer.setBounds(50,485,650,30);
        window.add(timeToAnswer);
        if ((optA.isSelected() && correctAnswer.equals("A")) ||
                (optB.isSelected() && correctAnswer.equals("B")) ||
                (optC.isSelected() && correctAnswer.equals("C")) ||
                (optD.isSelected() && correctAnswer.equals("D"))){
            correctLabel.setVisible(true);
            setHasCorrectAnswer(true);
        }else{
            incorrectLabel.setVisible(true);
            setHasCorrectAnswer(false);
        }
        window.repaint();
        window.revalidate();
    }

    private void clearButtons(){
        List<Component> components = Arrays.asList(content.getComponents());
        if (components.contains(leftTopLocBtn)) content.remove(leftTopLocBtn);
        if (components.contains(rightTopLocBtn)) content.remove(rightTopLocBtn);
        if (components.contains(leftBotLocBtn)) content.remove(leftBotLocBtn);
        if (components.contains(rightBotLocBtn)) content.remove(rightBotLocBtn);
        if (components.contains(helpBtn)) content.remove(helpBtn);
        if (components.contains(quitBtn)) content.remove(quitBtn);
        if(components.contains(mapBtn)) content.remove(mapBtn);
    }

    private void updateBadges(){
        int numBadges = badges.size();

        for (int i = 0; i < numBadges; i++){
            updateBadgePanel(badgeLabels.get(i), i);
        }
    }

    private void updateBadgePanel(JLabel badgeP, int index){
        //timer ends
        endBadgeTimer = System.currentTimeMillis();
        timeToAnswer.setText("");
        timeToAnswer.setText("You took " + ((endQuestion - startQuestion)/1000) + " seconds to answer the question! and You took " + (endBadgeTimer - startBadgeTimer)/1000 + " seconds to earn a badge for this room!");
        timeToAnswer.setFont(NORMAL_FONT);
        Badge toBeAdded = badges.get(index);
        if (toBeAdded.getImageFile() != null){
            BufferedImage bufImg = null;
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/" + toBeAdded.getImageFile());
            try {
                bufImg = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert bufImg != null;
            Image bgImg = bufImg.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(bgImg);
            badgeP.setIcon(img);
        }else{
            if (toBeAdded.getHexColor() != null){
                badgeP.setForeground(Color.decode(toBeAdded.getHexColor()));
            }
            badgeP.setText(toBeAdded.getName());
        }
    }

    //*************** SETUP METHODS ***************
    private void setup(){
        locationPanelSetup();
        descriptionPanelSetup();
        outOfQuestionsSetup();
        questionPanelSetup(); // XXX this is to test
        answerPanelSetup();
        badgePanelSetup();
        scorePanelSetup();
        buttonSetup();
        timerSetup();
    }

    private void locationPanelSetup(){
        //Title of Current Location
        currLocationPanel = new JPanel();
        currLocationPanel.setBounds(50,30,600,50);
        currLocationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        currLocationPanel.setBackground(Color.decode(getLocationHexColor()));
        content.add(currLocationPanel);

        currLocation = new JLabel();
        currLocation.setBounds(50,50,600,30);
        currLocation.setForeground(Color.white);
        currLocation.setFont(TITLE_FONT);
        currLocationPanel.add(currLocation);
    }

    private void descriptionPanelSetup(){
        //Panel for Location Description Display
        descriptionPanel = new JPanel();
        descriptionPanel.setBounds(50,80,600,70);
        descriptionPanel.setBackground(Color.white);
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(descriptionPanel);

        descriptionText = new JTextArea();
        descriptionText.setBounds(52,82,596,66);
        descriptionText.setForeground(Color.black);
        descriptionText.setFont(QUESTION_FONT);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setEditable(false);
        descriptionPanel.add(descriptionText);
    }

    private void outOfQuestionsSetup(){
        noMoreQuestionsText = new JTextArea();
        noMoreQuestionsText.setBounds(50,260,600,200);
        noMoreQuestionsText.setForeground(Color.black);
        noMoreQuestionsText.setBackground(Color.decode(getBackgroundHexColor()));
        noMoreQuestionsText.setFont(NO_QUESTIONS_FONT);
        noMoreQuestionsText.setLineWrap(true);
        noMoreQuestionsText.setWrapStyleWord(true);
        noMoreQuestionsText.setEditable(false);
        noMoreQuestionsText.setVisible(false);
        noMoreQuestionsText.setText("All questions here answered correctly! \n\nPlease move to a new location for more questions.");
        content.add(noMoreQuestionsText);
    }

    private void questionPanelSetup(){
        //Panel for Question Display
        questionPanel = new JPanel();
        questionPanel.setBounds(50,160,600,80);
        questionPanel.setBackground(Color.white);
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(questionPanel);

        questionText = new JTextArea();
        questionText.setBounds(52,102,596,76);
        questionText.setForeground(Color.black);
        questionText.setFont(QUESTION_FONT);
        startBadgeTimer = System.currentTimeMillis();
        startQuestion = System.currentTimeMillis();
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setEditable(false);
        questionPanel.add(questionText);
    }

    private void answerPanelSetup(){
        //Answer Panel
        answerPanel = new JPanel();
        answerPanel.setBounds(50,250,600,230);
        answerPanel.setBackground(Color.white);
        answerPanel.setFont(NORMAL_FONT);
        answerPanel.setLayout(null);
        answerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        answerPanel.setVisible(true);
        content.add(answerPanel);

        optA = new JRadioButton();
        optA.setBounds(2, 2, 596, 32);
        optA.setBackground(Color.white);
        optA.setFont(NORMAL_FONT);
        optA.setVisible(true);
        answerPanel.add(optA);

        optB = new JRadioButton();
        optB.setBounds(2, 35, 596, 32);
        optB.setBackground(Color.white);
        optB.setFont(NORMAL_FONT);
        optB.setVisible(true);
        answerPanel.add(optB);

        optC = new JRadioButton();
        optC.setBounds(2, 68, 596, 32);
        optC.setBackground(Color.white);
        optC.setFont(NORMAL_FONT);
        optC.setVisible(true);
        answerPanel.add(optC);

        optD = new JRadioButton();
        optD.setBounds(2, 101, 596, 32);
        optD.setBackground(Color.white);
        optD.setFont(NORMAL_FONT);
        optD.setVisible(true);
        answerPanel.add(optD);

        radioGroup = new ButtonGroup();
        radioGroup.add(optA);
        radioGroup.add(optB);
        radioGroup.add(optC);
        radioGroup.add(optD);

        hintText = new JTextArea();
        hintText.setBounds(2, 134, 596, 40);
        hintText.setForeground(Color.black);
        hintText.setFont(QUESTION_FONT);
        hintText.setLineWrap(true);
        hintText.setWrapStyleWord(true);
        hintText.setEditable(false);
        hintText.setVisible(false);
        answerPanel.add(hintText);

        hintBtn = new JButton("Hint");
        hintBtn.setBounds(10,190,60,30);
        hintBtn.setVisible(true);
        hintBtn.addActionListener(this);
        answerPanel.add(hintBtn);

        submit = new JButton("Submit");
        submit.setBounds(240,190,120,30);
        submit.setVisible(true);
        submit.addActionListener(this);
        answerPanel.add(submit);

        correctLabel = new JLabel("CORRECT!");
        correctLabel.setBounds(400,190,150,30);
        correctLabel.setForeground(Color.green);
        correctLabel.setBackground(Color.white);
        correctLabel.setFont(ANSWER_FONT);
        correctLabel.setVisible(false);
        answerPanel.add(correctLabel);

        incorrectLabel = new JLabel("INCORRECT");
        incorrectLabel.setBounds(400,190,150,30);
        incorrectLabel.setForeground(Color.red);
        incorrectLabel.setBackground(Color.white);
        incorrectLabel.setFont(ANSWER_FONT);
        incorrectLabel.setVisible(false);
        answerPanel.add(incorrectLabel);
    }

    private void badgePanelSetup(){
        //Badge Panel
        badgePanel = new JPanel();
        badgePanel.setBounds(700,30,300,220);
        badgePanel.setBackground(Color.white);
        badgePanel.setFont(NORMAL_FONT);
        badgePanel.setLayout(null);
        badgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.setVisible(true);
        content.add(badgePanel);

        badgeTitle = new JLabel("Badges", SwingConstants.CENTER);
        badgeTitle.setBounds(0,0,300,20);
        badgeTitle.setOpaque(true);
        badgeTitle.setForeground(Color.white);
        badgeTitle.setBackground(Color.decode(getLocationHexColor()));
        badgeTitle.setFont(SUB_TITLE_FONT);
        badgePanel.add(badgeTitle);

        badge1 = new JLabel();
        badge1.setBounds(0,20,100,50);
        badge1.setBackground(Color.white);
        badge1.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge1);
        badgeLabels.add(badge1);

        badge2 = new JLabel();
        badge2.setBounds(100,20,100,50);
        badge2.setBackground(Color.white);
        badge2.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge2);
        badgeLabels.add(badge2);

        badge3 = new JLabel();
        badge3.setBounds(200,20,100,50);
        badge3.setBackground(Color.white);
        badge3.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge3);
        badgeLabels.add(badge3);

        badge4 = new JLabel();
        badge4.setBounds(0,70,100,50);
        badge4.setBackground(Color.white);
        badge4.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge4);
        badgeLabels.add(badge4);

        badge5 = new JLabel();
        badge5.setBounds(100,70,100,50);
        badge5.setBackground(Color.white);
        badge5.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge5);
        badgeLabels.add(badge5);

        badge6 = new JLabel();
        badge6.setBounds(200,70,100,50);
        badge6.setBackground(Color.white);
        badge6.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge6);
        badgeLabels.add(badge6);

        badge7 = new JLabel();
        badge7.setBounds(0,120,100,50);
        badge7.setBackground(Color.white);
        badge7.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge7);
        badgeLabels.add(badge7);

        badge8 = new JLabel();
        badge8.setBounds(100,120,100,50);
        badge8.setBackground(Color.white);
        badge8.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge8);
        badgeLabels.add(badge8);

        badge9 = new JLabel();
        badge9.setBounds(200,120,100,50);
        badge9.setBackground(Color.white);
        badge9.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge9);
        badgeLabels.add(badge9);

        badge10 = new JLabel();
        badge10.setBounds(0,170,100,50);
        badge10.setBackground(Color.white);
        badge10.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge10);
        badgeLabels.add(badge10);

        badge11 = new JLabel();
        badge11.setBounds(100,170,100,50);
        badge11.setBackground(Color.white);
        badge11.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge11);
        badgeLabels.add(badge11);

        badge12 = new JLabel();
        badge12.setBounds(200,170,100,50);
        badge12.setBackground(Color.white);
        badge12.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge12);
        badgeLabels.add(badge12);
    }

    private void scorePanelSetup(){
        //Score Panel
        scorePanel = new JPanel();
        scorePanel.setBounds(700, 260,300,100);
        scorePanel.setBackground(Color.white);
        scorePanel.setFont(NORMAL_FONT);
        scorePanel.setLayout(null);
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.setVisible(true);
        content.add(scorePanel);

        scoreTitle = new JLabel("Score", SwingConstants.CENTER);
        scoreTitle.setBounds(0,0,300,20);
        scoreTitle.setOpaque(true);
        scoreTitle.setForeground(Color.white);
        scoreTitle.setBackground(Color.decode(getLocationHexColor()));
        scoreTitle.setFont(SUB_TITLE_FONT);
        scoreTitle.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.add(scoreTitle);

        scoreBox = new JLabel("", SwingConstants.CENTER);
        scoreBox.setBounds(0,20,300,30);
        scoreBox.setForeground(Color.black);
        scoreBox.setBackground(Color.white);
        scoreBox.setFont(NORMAL_FONT);
        scorePanel.add(scoreBox);

        progressTitle = new JLabel("Badge Progress", SwingConstants.CENTER);
        progressTitle.setBounds(0,50,300,20);
        progressTitle.setOpaque(true);
        progressTitle.setForeground(Color.white);
        progressTitle.setBackground(Color.decode(getLocationHexColor()));
        progressTitle.setFont(SUB_TITLE_FONT);
        scorePanel.add(progressTitle);

        progressBox = new JLabel("0/3", SwingConstants.CENTER);
        progressBox.setBounds(0,70,300,30);
        progressBox.setForeground(Color.black);
        progressBox.setBackground(Color.white);
        progressBox.setFont(NORMAL_FONT);
        scorePanel.add(progressBox);
    }

    private void buttonSetup(){
        if (nextLocs.size() == 1){
            setLeftTopLocBtn(300, nextLocs.get(0));
            setHelpBtn(420);
            setQuitBtn(420);
            setMapBtn(420);
        }else if (nextLocs.size() == 2){
            setLeftTopLocBtn(145, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setHelpBtn(420);
            setQuitBtn(420);
            setMapBtn(420);
        }else if (nextLocs.size() == 3){
            setLeftTopLocBtn(145, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setLeftBotLocBtn(300, nextLocs.get(2));
            setHelpBtn(460);
            setQuitBtn(460);
            setMapBtn(460);
        }else if (nextLocs.size() == 4){
            setLeftTopLocBtn(145, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setLeftBotLocBtn(145, nextLocs.get(2));
            setRightBotLocBtn(nextLocs.get(3));
            setHelpBtn(460);
            setQuitBtn(460);
            setMapBtn(460);
        }else{
            setHelpBtn(420);
            setQuitBtn(420);
            setMapBtn(420);
        }
    }
    private void timerSetup(){
        timeToAnswer = new JLabel();
        timeToAnswer.setBounds(50,485,650,30);
        timeToAnswer.setText("");
    }

    private void setMapBtn(int y){
        mapBtn = new JButton();
        mapBtn.setText("Map");
        mapBtn.setBounds(900,y,100,30);
        mapBtn.setVisible(true);
        mapBtn.addActionListener(this);
        content.add(mapBtn);
    }
    private void setHelpBtn(int y){
        helpBtn = new JButton();
        helpBtn.setBounds(700, y, 100, 30);
        helpBtn.setText("Help");
//        helpBtn.setBackground(Color.white);
//        helpBtn.setForeground(Color.black);
//        helpBtn.setFont(normalFont);
        helpBtn.setVisible(true);
        helpBtn.addActionListener(this);
        content.add(helpBtn);
    }

    private void setQuitBtn(int y){
        quitBtn = new JButton();
        quitBtn.setBounds(800, y, 100, 30);
        quitBtn.setText("Quit");
//        quitBtn.setBackground(Color.white);
//        quitBtn.setForeground(Color.black);
//        quitBtn.setFont(normalFont);
        quitBtn.setVisible(true);
        quitBtn.addActionListener(this);
        content.add(quitBtn);
    }

    private void setLeftTopLocBtn(int width, String name){
        leftTopLocBtn = new JButton();
        leftTopLocBtn.setBounds(700, 380, width, 30);
        leftTopLocBtn.setText("<<< " + name);
//        leftTopLocBtn.setBackground(Color.white);
//        leftTopLocBtn.setForeground(Color.black);
//        leftTopLocBtn.setFont(normalFont);
        leftTopLocBtn.setVisible(true);
        leftTopLocBtn.addActionListener(this);
        content.add(leftTopLocBtn);
    }

    private void setRightTopLocBtn(String name){
        rightTopLocBtn = new JButton();
        rightTopLocBtn.setBounds(855, 380, 145, 30);
        rightTopLocBtn.setText(name + " >>>");
//        rightTopLocBtn.setBackground(Color.white);
//        rightTopLocBtn.setForeground(Color.black);
//        rightTopLocBtn.setFont(normalFont);
        rightTopLocBtn.setVisible(true);
        rightTopLocBtn.addActionListener(this);
        content.add(rightTopLocBtn);
    }

    private void setLeftBotLocBtn(int width, String name){
        leftBotLocBtn = new JButton();
        leftBotLocBtn.setBounds(700, 420, width, 30);
        leftBotLocBtn.setText("<<< " + name);
//        leftBotLocBtn.setBackground(Color.white);
//        leftBotLocBtn.setForeground(Color.black);
//        leftBotLocBtn.setFont(normalFont);
        leftBotLocBtn.setVisible(true);
        leftBotLocBtn.addActionListener(this);
        content.add(leftBotLocBtn);
    }

    private void setRightBotLocBtn(String name){
        rightBotLocBtn = new JButton();
        rightBotLocBtn.setBounds(855, 420, 145, 30);
        rightBotLocBtn.setText(name + " >>>");
//        rightBotLocBtn.setBackground(Color.white);
//        rightBotLocBtn.setForeground(Color.black);
//        rightBotLocBtn.setFont(normalFont);
        rightBotLocBtn.setVisible(true);
        rightBotLocBtn.addActionListener(this);
        content.add(rightBotLocBtn);
    }

    private void displayMapWindow(){
        mapWindow = new JFrame();
        Container mapContent;

        mapWindow.setSize(900,800);
        mapWindow.setLocation(1600,500);
        mapWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapWindow.setLayout(null);
        mapWindow.setVisible(true);
        mapContent = mapWindow.getContentPane();
        mapContent.setBackground(Color.decode("#ADC7D9"));

        mapTitle = new JLabel("The Map of Doctor Me Game",SwingConstants.CENTER);
        mapTitle.setBounds(100,10,500,50);
        mapTitle.setFont(TITLE_FONT);
        mapTitle.setForeground(Color.black);
        mapContent.add(mapTitle,BorderLayout.CENTER);

        gameMap = new JLabel();
        gameMap.setBounds(20,70,850,650);
        gameMap.setBackground(Color.green);
        gameMap.setBorder(BorderFactory.createLineBorder(Color.black));
        mapContent.add(gameMap);

        BufferedImage mapImg = null;
        try{
            mapImg = ImageIO.read(new File("resources/images/gameMap.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        assert mapImg != null;
        Image mpImage = mapImg.getScaledInstance(850,650,Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(mpImage);
        gameMap.setIcon(img1);

        mapCloseBtn = new JButton();
        mapCloseBtn.setBounds(450, 730, 100, 30);
        mapCloseBtn.setText("Close");
        mapCloseBtn.setVisible(true);
        mapCloseBtn.addActionListener(this);
        mapContent.add(mapCloseBtn);

    }
    private void displayHelpWindow(){
        helpWindow = new JFrame(); //initiate help window
        Container helpContent;
        JLabel helpTitle;

        //set up help window
        helpWindow.setSize(500,520);
        helpWindow.setLocation(1600,500);
        helpWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helpWindow.setLayout(null);
        helpWindow.setVisible(true);
        helpContent = helpWindow.getContentPane();
        helpContent.setBackground(Color.decode(backgroundHexColor));

        //help title
        helpTitle = new JLabel("Need help?", SwingConstants.CENTER);
        helpTitle.setBounds(50,10,400,50);
        helpTitle.setFont(TITLE_FONT);
        helpTitle.setForeground(Color.black);
        helpContent.add(helpTitle,BorderLayout.CENTER);

        //text area for the help window
        helpText = new JTextArea();
        helpText.setText("Here are some basic instructions if you get stuck blah blah blah");
        helpText.setBounds(50,70,400,350);
        helpText.setForeground(Color.black);
        helpText.setFont(QUESTION_FONT);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(false);
        helpContent.add(helpText);

        //close button for help window
        helpCloseBtn = new JButton();
        helpCloseBtn.setBounds(210, 430, 80, 30);
        helpCloseBtn.setText("Close");
//        helpCloseBtn.setBackground(Color.white);
//        helpCloseBtn.setForeground(Color.black);
//        helpCloseBtn.setFont(normalFont);
        helpCloseBtn.setVisible(true);
        helpCloseBtn.addActionListener(this);
        helpContent.add(helpCloseBtn);

        helpWindow.repaint();
        helpWindow.revalidate();
    }

    //*************** SETTER METHODS ***************
    public void updateHelpScreenText(String newHelpText){
        helpText.setText(newHelpText);
    }

    public void updateInstructionsText(String newInstructions){
        gameInstructions.setText(newInstructions);
    }

    public void updateCurrentLocation(String newLocation){
        currLocation.setText(newLocation);
        setWantsToChangeLocation(false);
    }

    public void updateLocationDescription(String newDescription){ descriptionText.setText(newDescription);}

    public void updateQuestion(String newQuestion){
        if (newQuestion.equals("")){
            questionPanel.setVisible(false);
            answerPanel.setVisible(false);
            noMoreQuestionsText.setVisible(true);
        }else{
            questionPanel.setVisible(true);
            answerPanel.setVisible(true);
            noMoreQuestionsText.setVisible(false);
            questionText.setText(newQuestion);
        }
        setReadyForNextQuestion(false);
        setHasCorrectAnswer(false);
    }

    public void updateOptionA(String newOption){
        optA.setText(newOption);
    }

    public void updateOptionB(String newOption){
        optB.setText(newOption);
    }

    public void updateOptionC(String newOption){
        optC.setText(newOption);
    }

    public void updateOptionD(String newOption){
        optD.setText(newOption);
    }

    public void updateHintText(String newHint) { hintText.setText(newHint);}

    public void updateNextLocations(List<String> newLocations){
        nextLocs.clear();
        for (String loc: newLocations) nextLocs.add(loc);
    }

    private String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getNextLocation() {
        return nextLocation;
    }

    private void setNextLocation(String nextLocation) {
        this.nextLocation = nextLocation;
    }

    public boolean isReadyForNextQuestion() {
        return readyForNextQuestion;
    }

    private void setReadyForNextQuestion(boolean readyForNextQuestion) {
        this.readyForNextQuestion = readyForNextQuestion;
    }

    public boolean hadCorrectAnswer() {
        return hasCorrectAnswer;
    }

    private void setHasCorrectAnswer(boolean hasCorrectAnswer) {
        this.hasCorrectAnswer = hasCorrectAnswer;
    }

    public boolean isEnteredGame() {
        return enteredGame;
    }

    private void setEnteredGame(boolean enteredGame) {
        this.enteredGame = enteredGame;
    }

    public boolean isWantsToChangeLocation() {
        return wantsToChangeLocation;
    }

    private void setWantsToChangeLocation(boolean wantsToChangeLocation) {
        this.wantsToChangeLocation = wantsToChangeLocation;
    }

    public boolean isHasSubmittedAnswer() {
        return hasSubmittedAnswer;
    }

    public void setHasSubmittedAnswer(boolean hasSubmittedAnswer) {
        this.hasSubmittedAnswer = hasSubmittedAnswer;
    }

    private int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
        scoreBox.setText(currentScore + " points");
        setHasSubmittedAnswer(false);

        window.repaint();
        window.revalidate();
    }

    private int getBadgeProgress() {
        return badgeProgress;
    }

    public void setBadgeProgress(int badgeProgress) {
        this.badgeProgress = badgeProgress;
        setBadgeEarned(badgeProgress >= 3);
    }

    public String getPlayerName() {
        return playerName;
    }

    private void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges.clear();
        for (Badge bad: badges) this.badges.add(bad);
        updateBadges();
    }

    private String getBackgroundHexColor() {
        return backgroundHexColor;
    }

    private void setBackgroundHexColor(String backgroundHexColor) {
        this.backgroundHexColor = backgroundHexColor;
    }

    private String getLocationHexColor() {
        return locationHexColor;
    }

    private void setLocationHexColor(String locationHexColor) {
        this.locationHexColor = locationHexColor;
    }

    private String getButtonHexColor() {
        return buttonHexColor;
    }

    private void setButtonHexColor(String buttonHexColor) {
        this.buttonHexColor = buttonHexColor;
    }

    private boolean isBadgeEarned() {
        return badgeEarned;
    }

    private void setBadgeEarned(boolean badgeEarned) {
        this.badgeEarned = badgeEarned;
        progressBox.setText(getBadgeProgress() + "/3");
        if (badgeEarned){
            progressBox.setForeground(Color.green);
        }else {
            progressBox.setForeground(Color.red);
        }
    }
}