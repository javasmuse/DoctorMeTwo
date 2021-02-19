package com.doctorme.GUI;

import com.doctorme.entities.Badge;
import com.doctorme.util.Roboto;

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
    private static final Font TITLE_FONT = Roboto.REGULAR.deriveFont((float)32);
    private static final Font SUB_TITLE_FONT = Roboto.BOLD.deriveFont((float) 16);
    private static final Font QUESTION_FONT = Roboto.ITALIC.deriveFont((float) 16);
    private static final Font NORMAL_FONT = Roboto.REGULAR.deriveFont((float) 16);
    private static final Font ANSWER_FONT = Roboto.BOLD.deriveFont((float) 24);
    private static final Font NO_QUESTIONS_FONT = Roboto.BOLD.deriveFont((float) 28);
    private static final Font BUTTON_FONT = Roboto.BOLD.deriveFont((float) 12);
    private final Container content;
    private Container nextLevelContent;
    private final JFrame window = new JFrame();
    private JFrame helpWindow, mapWindow,nextLevel;
    private JPanel questionPanel, answerPanel;
    private JLabel nextLevelPara,congratulationsLabel,timeToAnswer, currLocation, scoreBox, progressBox, correctLabel, incorrectLabel;
    private final JTextArea gameInstructions;
    private final JTextArea helpText;
    private JTextArea questionText, descriptionText, hintText, noMoreQuestionsText;
    private final JTextField userName;
    private JScrollPane scrollPane;
    private final JButton enterGameBtn;
    private JButton nextLevelBtn, mapCloseBtn, mapBtn, quitBtn, helpBtn, submit, hintBtn, leftTopLocBtn, rightTopLocBtn, leftBotLocBtn, rightBotLocBtn, helpCloseBtn;
    private JRadioButton optA, optB, optC, optD;
    private ButtonGroup radioGroup;
    private final List<JLabel> badgeLabels = new ArrayList<>();
    private final List<Badge> badges = new ArrayList<>();
    private final List<String> nextLocs = new ArrayList<>();
    private String correctAnswer, nextLocation, playerName, backgroundHexColor, locationHexColor, buttonHexColor;
    private int currentScore, badgeProgress;
    private long startQuestion, endQuestion, startBadgeTimer;
    private boolean readyForNextQuestion, hasCorrectAnswer, enteredGame, wantsToChangeLocation, hasSubmittedAnswer, badgeEarned;

    public GameGUI(String introTitle, String introText, String introInstructions){
        setHasCorrectAnswer(false);
        setReadyForNextQuestion(false);
        setEnteredGame(false);
        setHasSubmittedAnswer(false);
        setBackgroundHexColor("#ADC7D9");  // #146EB4 (darker blue), #F9F3E6 (linen)
        setLocationHexColor("#242F41"); // #043769
        setButtonHexColor("#F3A847");
        helpText = new JTextArea();

        //Setting the GUI window
        window.setSize(1050,540);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode(getBackgroundHexColor()));

        //Welcome Title
        JLabel welcomeTitle = new JLabel(introTitle, SwingConstants.CENTER);
        welcomeTitle.setBounds(50,10,950,35);
        welcomeTitle.setForeground(Color.black);
        welcomeTitle.setFont(TITLE_FONT);
        content.add(welcomeTitle);

        JLabel gameDescription = new JLabel(introText, SwingConstants.CENTER);
        gameDescription.setBounds(50,45,950,30);
        gameDescription.setForeground(Color.black);
        gameDescription.setFont(NORMAL_FONT);
        content.add(gameDescription);


        //----Help or Instruction of the game
        JPanel enterGamePanel = new JPanel();
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

        //label to prompt player for name
        JLabel name = new JLabel("Player Name:");
        name.setBounds(200,450,100,30);
        name.setFont(NORMAL_FONT);
        content.add(name);

        //label for player to enter their name
        userName = new JTextField();
        userName.setBounds(300,450,150,30);
        content.add(userName);

        //EnterGame button
        enterGameBtn = new JButton();
        enterGameBtn.setBounds(475, 450, 100, 30);
        enterGameBtn.setText("Enter Game");
        enterGameBtn.setFont(BUTTON_FONT);
        enterGameBtn.setBackground(Color.decode(getButtonHexColor()));
        enterGameBtn.setForeground(Color.decode(getLocationHexColor()));
        enterGameBtn.setVisible(true);
        enterGameBtn.addActionListener(this);
        content.add( enterGameBtn);

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
        }else if(e.getSource()==nextLevelBtn){
            //Level 2 should load up! Coming Soon!
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
            timerSetHelper();
            setReadyForNextQuestion(true);
        }else if(e.getSource() == leftTopLocBtn){
            locationSetHelper(0);
        }else if(e.getSource() == rightTopLocBtn) {
            locationSetHelper(1);
        }else if(e.getSource() == leftBotLocBtn) {
            locationSetHelper(2);
        }else if(e.getSource() == rightBotLocBtn){
            locationSetHelper(3);
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
        timeToAnswer = new JLabel("Question answered in: " + ((endQuestion - startQuestion)/1000) + "seconds!");
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
        long endBadgeTimer = System.currentTimeMillis();
        timeToAnswer.setText("");
        timeToAnswer.setText("Question answered in " + ((endQuestion - startQuestion)/1000) + " seconds. Badge earned in " + (endBadgeTimer - startBadgeTimer)/1000 + " seconds!");
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

        if(badges.size() == 12 && nextLevel == null){
            winLevel1Screen();
        }
    }

    private void timerSetHelper(){
        timeToAnswer.setText("");
        startQuestion = System.currentTimeMillis();
    }

    private void locationSetHelper(int index){
        timerSetHelper();
        startBadgeTimer = System.currentTimeMillis();
        setNextLocation(nextLocs.get(index));
        setWantsToChangeLocation(true);
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

    private void winLevel1Screen(){
        nextLevel = new JFrame();
        nextLevel.setSize(1050,450);
        nextLevel.setLocation(500,500);
        nextLevel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextLevel.setLayout(null);
        nextLevel.setVisible(true);
        nextLevelContent = nextLevel.getContentPane();
        nextLevelContent.setBackground(Color.decode(getBackgroundHexColor()));

        congratulationsLabel = new JLabel();
        congratulationsLabel.setBounds(50,50,900,250);
        congratulationsLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        nextLevelContent.add(congratulationsLabel);

        BufferedImage congratsImg = null;
        try{
            congratsImg = ImageIO.read(new File("resources/images/congratulations.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        assert congratsImg != null;
        Image cgImage = congratsImg.getScaledInstance(900,250,Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(cgImage);
        congratulationsLabel.setIcon(img1);

        nextLevelPara = new JLabel("Level 2 has now been unlocked.", SwingConstants.CENTER);
        nextLevelPara.setBounds(50,310,900,100);
        nextLevelPara.setVisible(true);
        nextLevelPara.setForeground(Color.black);
        nextLevelPara.setFont(SUB_TITLE_FONT);
        nextLevelContent.add(nextLevelPara);

        nextLevelBtn = new JButton("Level 2");
        nextLevelBtn.setBounds(450,375,100,30);
        nextLevelBtn.addActionListener(this);
        nextLevelBtn.setFont(BUTTON_FONT);
        nextLevelBtn.setBackground(Color.decode(getButtonHexColor()));
        nextLevelBtn.setForeground(Color.decode(getLocationHexColor()));
        nextLevelContent.add(nextLevelBtn);

        nextLevel.repaint();
        nextLevel.revalidate();

    }

    private void locationPanelSetup(){
        //Title of Current Location
        JPanel currLocationPanel = new JPanel();
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
        JPanel descriptionPanel = new JPanel();
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
        // acknowledging this should really be in the GameText and routed in through the GameText Generator and controoler (Game)
        noMoreQuestionsText.setText("All location questions answered correctly! \n\nPlease move to a new location for questions.");
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
        questionText.setBounds(2,2,596,76);
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
        hintText.setBounds(10, 136, 588, 40);
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
        hintBtn.setFont(BUTTON_FONT);
        hintBtn.setBackground(Color.decode(getButtonHexColor()));
        hintBtn.setForeground(Color.decode(getLocationHexColor()));
        answerPanel.add(hintBtn);

        submit = new JButton("Submit");
        submit.setBounds(240,190,120,30);
        submit.setVisible(true);
        submit.addActionListener(this);
        submit.setFont(BUTTON_FONT);
        submit.setBackground(Color.decode(getButtonHexColor()));
        submit.setForeground(Color.decode(getLocationHexColor()));
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
        JPanel badgePanel = new JPanel();
        badgePanel.setBounds(700,30,300,220);
        badgePanel.setBackground(Color.white);
        badgePanel.setFont(NORMAL_FONT);
        badgePanel.setLayout(null);
        badgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.setVisible(true);
        content.add(badgePanel);

        JLabel badgeTitle = new JLabel("Badges", SwingConstants.CENTER);
        badgeTitle.setBounds(0,0,300,20);
        badgeTitle.setOpaque(true);
        badgeTitle.setForeground(Color.white);
        badgeTitle.setBackground(Color.decode(getLocationHexColor()));
        badgeTitle.setFont(SUB_TITLE_FONT);
        badgePanel.add(badgeTitle);

        JLabel badge1 = new JLabel();
        badge1.setBounds(0,20,100,50);
        badge1.setBackground(Color.white);
        badge1.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge1);
        badgeLabels.add(badge1);

        JLabel badge2 = new JLabel();
        badge2.setBounds(100,20,100,50);
        badge2.setBackground(Color.white);
        badge2.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge2);
        badgeLabels.add(badge2);

        JLabel badge3 = new JLabel();
        badge3.setBounds(200,20,100,50);
        badge3.setBackground(Color.white);
        badge3.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge3);
        badgeLabels.add(badge3);

        JLabel badge4 = new JLabel();
        badge4.setBounds(0,70,100,50);
        badge4.setBackground(Color.white);
        badge4.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge4);
        badgeLabels.add(badge4);

        JLabel badge5 = new JLabel();
        badge5.setBounds(100,70,100,50);
        badge5.setBackground(Color.white);
        badge5.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge5);
        badgeLabels.add(badge5);

        JLabel badge6 = new JLabel();
        badge6.setBounds(200,70,100,50);
        badge6.setBackground(Color.white);
        badge6.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge6);
        badgeLabels.add(badge6);

        JLabel badge7 = new JLabel();
        badge7.setBounds(0,120,100,50);
        badge7.setBackground(Color.white);
        badge7.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge7);
        badgeLabels.add(badge7);

        JLabel badge8 = new JLabel();
        badge8.setBounds(100,120,100,50);
        badge8.setBackground(Color.white);
        badge8.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge8);
        badgeLabels.add(badge8);

        JLabel badge9 = new JLabel();
        badge9.setBounds(200,120,100,50);
        badge9.setBackground(Color.white);
        badge9.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge9);
        badgeLabels.add(badge9);

        JLabel badge10 = new JLabel();
        badge10.setBounds(0,170,100,50);
        badge10.setBackground(Color.white);
        badge10.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge10);
        badgeLabels.add(badge10);

        JLabel badge11 = new JLabel();
        badge11.setBounds(100,170,100,50);
        badge11.setBackground(Color.white);
        badge11.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge11);
        badgeLabels.add(badge11);

        JLabel badge12 = new JLabel();
        badge12.setBounds(200,170,100,50);
        badge12.setBackground(Color.white);
        badge12.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge12);
        badgeLabels.add(badge12);
    }

    private void scorePanelSetup(){
        //Score Panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(700, 260,300,110);
        scorePanel.setBackground(Color.decode(getBackgroundHexColor()));
        scorePanel.setFont(NORMAL_FONT);
        scorePanel.setLayout(null);
//        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.setVisible(true);
        content.add(scorePanel);

        JLabel scoreTitle = new JLabel("Score", SwingConstants.CENTER);
        scoreTitle.setBounds(0,0,300,20);
        scoreTitle.setOpaque(true);
        scoreTitle.setForeground(Color.white);
        scoreTitle.setBackground(Color.decode(getLocationHexColor()));
        scoreTitle.setFont(SUB_TITLE_FONT);
        scoreTitle.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.add(scoreTitle);

        scoreBox = new JLabel("", SwingConstants.CENTER);
        scoreBox.setBounds(0,20,300,30);
        scoreBox.setOpaque(true);
        scoreBox.setForeground(Color.black);
        scoreBox.setBackground(Color.white);
        scoreBox.setFont(NORMAL_FONT);
        scorePanel.add(scoreBox);

        JLabel progressTitle = new JLabel("Badge Progress", SwingConstants.CENTER);
        progressTitle.setBounds(0,60,300,20);
        progressTitle.setOpaque(true);
        progressTitle.setForeground(Color.white);
        progressTitle.setBackground(Color.decode(getLocationHexColor()));
        progressTitle.setFont(SUB_TITLE_FONT);
        scorePanel.add(progressTitle);

        progressBox = new JLabel("0/3", SwingConstants.CENTER);
        progressBox.setBounds(0,80,300,30);
        progressBox.setOpaque(true);
        progressBox.setForeground(Color.red);
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
            setLeftTopLocBtn(150, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setHelpBtn(420);
            setQuitBtn(420);
            setMapBtn(420);
        }else if (nextLocs.size() == 3){
            setLeftTopLocBtn(150, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setLeftBotLocBtn(300, nextLocs.get(2));
            setHelpBtn(450);
            setQuitBtn(450);
            setMapBtn(450);
        }else if (nextLocs.size() == 4){
            setLeftTopLocBtn(150, nextLocs.get(0));
            setRightTopLocBtn(nextLocs.get(1));
            setLeftBotLocBtn(150, nextLocs.get(2));
            setRightBotLocBtn(nextLocs.get(3));
            setHelpBtn(450);
            setQuitBtn(450);
            setMapBtn(450);
        }else{
            setHelpBtn(420);
            setQuitBtn(420);
            setMapBtn(420);
        }
    }

    private void setMapBtn(int y){
        mapBtn = new JButton();
        mapBtn.setText("Map");
        mapBtn.setFont(BUTTON_FONT);
        mapBtn.setBounds(900,y,100,30);
        mapBtn.setVisible(true);
        mapBtn.addActionListener(this);
        mapBtn.setFont(BUTTON_FONT);
        mapBtn.setBackground(Color.decode(getButtonHexColor()));
        mapBtn.setForeground(Color.decode(getLocationHexColor()));
        content.add(mapBtn);
    }
    private void setHelpBtn(int y){
        helpBtn = new JButton();
        helpBtn.setBounds(700, y, 100, 30);
        helpBtn.setText("Help");
        helpBtn.setFont(BUTTON_FONT);
        helpBtn.setBackground(Color.decode(getButtonHexColor()));
        helpBtn.setForeground(Color.decode(getLocationHexColor()));
        helpBtn.setVisible(true);
        helpBtn.addActionListener(this);
        content.add(helpBtn);
    }

    private void setQuitBtn(int y){
        quitBtn = new JButton();
        quitBtn.setBounds(800, y, 100, 30);
        quitBtn.setText("Quit");
        quitBtn.setFont(BUTTON_FONT);
        quitBtn.setBackground(Color.decode(getButtonHexColor()));
        quitBtn.setForeground(Color.decode(getLocationHexColor()));
        quitBtn.setVisible(true);
        quitBtn.addActionListener(this);
        content.add(quitBtn);
    }

    private void setLeftTopLocBtn(int width, String name){
        leftTopLocBtn = new JButton();
        leftTopLocBtn.setBounds(700, 380, width, 30);
        leftTopLocBtn.setText("<<< " + name);
        leftTopLocBtn.setFont(BUTTON_FONT);
        leftTopLocBtn.setBackground(Color.decode(getButtonHexColor()));
        leftTopLocBtn.setForeground(Color.decode(getLocationHexColor()));
        leftTopLocBtn.setVisible(true);
        leftTopLocBtn.addActionListener(this);
        content.add(leftTopLocBtn);
    }

    private void setRightTopLocBtn(String name){
        rightTopLocBtn = new JButton();
        rightTopLocBtn.setBounds(850, 380, 150, 30);
        rightTopLocBtn.setText(name + " >>>");
        rightTopLocBtn.setFont(BUTTON_FONT);
        rightTopLocBtn.setBackground(Color.decode(getButtonHexColor()));
        rightTopLocBtn.setForeground(Color.decode(getLocationHexColor()));
        rightTopLocBtn.setVisible(true);
        rightTopLocBtn.addActionListener(this);
        content.add(rightTopLocBtn);
    }

    private void setLeftBotLocBtn(int width, String name){
        leftBotLocBtn = new JButton();
        leftBotLocBtn.setBounds(700, 410, width, 30);
        leftBotLocBtn.setText("<<< " + name);
        leftBotLocBtn.setFont(BUTTON_FONT);
        leftBotLocBtn.setBackground(Color.decode(getButtonHexColor()));
        leftBotLocBtn.setForeground(Color.decode(getLocationHexColor()));
        leftBotLocBtn.setVisible(true);
        leftBotLocBtn.addActionListener(this);
        content.add(leftBotLocBtn);
    }

    private void setRightBotLocBtn(String name){
        rightBotLocBtn = new JButton();
        rightBotLocBtn.setBounds(850, 410, 150, 30);
        rightBotLocBtn.setText(name + " >>>");
        rightBotLocBtn.setFont(BUTTON_FONT);
        rightBotLocBtn.setBackground(Color.decode(getButtonHexColor()));
        rightBotLocBtn.setForeground(Color.decode(getLocationHexColor()));
        rightBotLocBtn.setVisible(true);
        rightBotLocBtn.addActionListener(this);
        content.add(rightBotLocBtn);
    }

    private void timerSetup(){
        timeToAnswer = new JLabel();
        timeToAnswer.setBounds(50,485,650,30);
        timeToAnswer.setText("");
        timeToAnswer.setFont(NORMAL_FONT);
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
        mapContent.setBackground(Color.decode(getBackgroundHexColor()));

        JLabel mapTitle = new JLabel("The Map of Doctor Me Game", SwingConstants.CENTER);
        mapTitle.setBounds(100,10,500,50);
        mapTitle.setFont(TITLE_FONT);
        mapTitle.setForeground(Color.black);
        mapContent.add(mapTitle,BorderLayout.CENTER);

        JLabel gameMap = new JLabel();
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
        mapCloseBtn.setFont(BUTTON_FONT);
        mapCloseBtn.setBackground(Color.decode(getButtonHexColor()));
        mapCloseBtn.setForeground(Color.decode(getLocationHexColor()));
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
        helpCloseBtn.setFont(BUTTON_FONT);
        helpCloseBtn.setBackground(Color.decode(getButtonHexColor()));
        helpCloseBtn.setForeground(Color.decode(getLocationHexColor()));
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