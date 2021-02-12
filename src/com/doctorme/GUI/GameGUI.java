package com.doctorme.GUI;

import com.doctorme.app.Game;
import com.doctorme.entities.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI implements ActionListener {
    private JButton quitBtn, helpBtn, submit, hintBtn, enterGameBtn, leftLocBtn, rightLocBtn, helpCloseBtn, nextQuestion;
    private List<Location> board;
    private Container content;
    private final JFrame window = new JFrame();
    private JFrame helpWindow;
    private JLabel gameDescription, currLocation, welcomeTitle, badgeTitle, scoreTitle, correctLabel, incorrectLabel;
    private JPanel descriptionPanel, questionPanel, currLocationPanel, answerPanel, helpPanel, buttonPanelHelpPage, badgePanel, scorePanel, enterGamePanel, badge1, badge2, badge3, badge4, badge5, badge6, badge7, badge8, badge9;
    private JTextArea helpText, gameInstructions, questionText, descriptionText, hintText;
    private JRadioButton optA, optB, optC, optD;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
    private static final Font questionFont = new Font("Times New Roman", Font.ITALIC, 16);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 16);
    private static final Font answerFont = new Font("Times New Roman", Font.BOLD, 24);
    private JScrollPane scrollPane;
    private Game game = new Game();
    private String correctAnswer = "A";
    private ButtonGroup radioGroup;
    private boolean readyForNextQuestion, hasCorrectAnswer, enteredGame;

    public GameGUI(String introTitle, String introText, String introInstructions){
        setHasCorrectAnswer(false);
        setReadyForNextQuestion(false);
        setEnteredGame(false);

        //Setting the GUI window
        window.setSize(1050,520);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode("#ADC7D9"));

        //Welcome Title
        welcomeTitle = new JLabel(introTitle, SwingConstants.CENTER);
        welcomeTitle.setBounds(50,10,950,35);
        welcomeTitle.setForeground(Color.black);
        welcomeTitle.setFont(titleFont);
        content.add(welcomeTitle);

        gameDescription = new JLabel(introText, SwingConstants.CENTER);
        gameDescription.setBounds(50,45,950,30);
        gameDescription.setForeground(Color.black);
        gameDescription.setFont(normalFont);
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
        gameInstructions.setFont(questionFont);
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

        window.repaint();
        window.revalidate();
    }

    //*************** EVENT LISTENER ***************
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enterGameBtn){
            content.removeAll();
            window.repaint();
            window.revalidate();
            setup();
            setEnteredGame(true);
//            setReadyForNextQuestion(true);
        }else if(e.getSource() == helpBtn){
            displayHelpWindow();
        }else if(e.getSource() == helpCloseBtn){
            helpWindow.dispose();
        }else if(e.getSource() == quitBtn) {
            window.dispose();
            System.exit(0);
        }else if(e.getSource() == hintBtn){
            hintText.setVisible(true);
        }else if(e.getSource() == submit && submit.getText().equals("Submit")) {
            checkAnswer();
        }else if(e.getSource() == submit && submit.getText().equals("Next Question")){
            setReadyForNextQuestion(true);
        }else if(e.getSource() == leftLocBtn){
//            changeToLeftLoc();
        }else if(e.getSource() == rightLocBtn){
//            changeToRightLoc();
        }else{
            System.out.println("Unrecognized event");
        }
//        if(e.getSource()==back){
//            //TODO: Go back to the Game Screen which will be in a function later on
//        }
    }

    //*************** ACCESSORY METHODS ***************
    public void guiUpdate(){
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(false);
        hintText.setVisible(false);
        radioGroup.clearSelection();
        submit.setText("Submit");

        window.repaint();
        window.revalidate();
    }

    private void checkAnswer(){
        submit.setText("Next Question");
        if ((optA.isSelected() && correctAnswer.equals("A")) ||
                (optB.isSelected() && correctAnswer.equals("B")) ||
                (optC.isSelected() && correctAnswer.equals("C")) ||
                (optD.isSelected() && correctAnswer.equals("D"))){
            correctLabel.setVisible(true);
            setHasCorrectAnswer(true);
//            incrementScore();
        }else{
            incorrectLabel.setVisible(true);
            setHasCorrectAnswer(false);
//            resetScore();
        }
        window.repaint();
        window.revalidate();
    }

    private void incrementScore(){

    }

    private void resetScore(){

    }

    //*************** SETUP METHODS ***************
    private void setup(){
        locationPanelSetup();
        descriptionPanelSetup();
        questionPanelSetup(); // XXX this is to test
        answerPanelSetup();
        badgePanelSetup();
        scorePanelSetup();
        buttonSetup();
    }

    private void locationPanelSetup(){
        //Title of Current Location
        currLocationPanel = new JPanel();
        currLocationPanel.setBounds(50,30,600,50);
        currLocationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        currLocationPanel.setBackground(Color.decode("#043769"));
        content.add(currLocationPanel);

        currLocation = new JLabel();
        currLocation.setBounds(50,50,600,30);
        currLocation.setForeground(Color.white);
        currLocation.setFont(titleFont);
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
        descriptionText.setFont(questionFont);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setEditable(false);
        descriptionPanel.add(descriptionText);
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
        questionText.setFont(questionFont);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setEditable(false);
        questionPanel.add(questionText);
    }

    private void answerPanelSetup(){
        //Answer Panel
        answerPanel = new JPanel();
        answerPanel.setBounds(50,250,600,200);
        answerPanel.setBackground(Color.white);
        answerPanel.setFont(normalFont);
        answerPanel.setLayout(null);
        answerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        answerPanel.setVisible(true);
        content.add(answerPanel);

        optA = new JRadioButton();
        optA.setBounds(2, 2, 596, 32);
        optA.setBackground(Color.white);
        optA.setFont(normalFont);
        optA.setVisible(true);
        answerPanel.add(optA);

        optB = new JRadioButton();
        optB.setBounds(2, 35, 596, 32);
        optB.setBackground(Color.white);
        optB.setFont(normalFont);
        optB.setVisible(true);
        answerPanel.add(optB);

        optC = new JRadioButton();
        optC.setBounds(2, 68, 596, 32);
        optC.setBackground(Color.white);
        optC.setFont(normalFont);
        optC.setVisible(true);
        answerPanel.add(optC);

        optD = new JRadioButton();
        optD.setBounds(2, 101, 596, 32);
        optD.setBackground(Color.white);
        optD.setFont(normalFont);
        optD.setVisible(true);
        answerPanel.add(optD);

        radioGroup = new ButtonGroup();
        radioGroup.add(optA);
        radioGroup.add(optB);
        radioGroup.add(optC);
        radioGroup.add(optD);

        hintText = new JTextArea();
        hintText.setBounds(2, 134, 596, 30);
        hintText.setForeground(Color.black);
        hintText.setFont(questionFont);
        hintText.setLineWrap(true);
        hintText.setWrapStyleWord(true);
        hintText.setEditable(false);
        hintText.setVisible(false);
        answerPanel.add(hintText);

        hintBtn = new JButton("Hint");
        hintBtn.setBounds(10,165,60,30);
        hintBtn.setVisible(true);
        hintBtn.addActionListener(this);
        answerPanel.add(hintBtn);

        submit = new JButton("Submit");
        submit.setBounds(240,165,120,30);
        submit.setVisible(true);
        submit.addActionListener(this);
        answerPanel.add(submit);

        correctLabel = new JLabel("CORRECT!");
        correctLabel.setBounds(400,165,150,30);
        correctLabel.setForeground(Color.green);
        correctLabel.setBackground(Color.white);
        correctLabel.setFont(answerFont);
        correctLabel.setVisible(false);
        answerPanel.add(correctLabel);

        incorrectLabel = new JLabel("INCORRECT");
        incorrectLabel.setBounds(400,165,150,30);
        incorrectLabel.setForeground(Color.red);
        incorrectLabel.setBackground(Color.white);
        incorrectLabel.setFont(answerFont);
        incorrectLabel.setVisible(false);
        answerPanel.add(incorrectLabel);
    }

    private void badgePanelSetup(){
        //Badge Panel
        badgePanel = new JPanel();
        badgePanel.setBounds(700,30,300,200);
        badgePanel.setBackground(Color.white);
        badgePanel.setFont(normalFont);
        badgePanel.setLayout(null);
        badgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.setVisible(true);
        content.add(badgePanel);

        badgeTitle = new JLabel("Badges", SwingConstants.CENTER);
        badgeTitle.setBounds(0,0,300,20);
        badgeTitle.setForeground(Color.black);
        badgeTitle.setBackground(Color.white);
        badgeTitle.setFont(normalFont);
        badgePanel.add(badgeTitle);

        badge1 = new JPanel();
        badge1.setBounds(0,20,100,60);
        badge1.setBackground(Color.white);
        badge1.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge1);

        badge2 = new JPanel();
        badge2.setBounds(100,20,100,60);
        badge2.setBackground(Color.white);
        badge2.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge2);

        badge3 = new JPanel();
        badge3.setBounds(200,20,100,60);
        badge3.setBackground(Color.white);
        badge3.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge3);

        badge4 = new JPanel();
        badge4.setBounds(0,80,100,60);
        badge4.setBackground(Color.white);
        badge4.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge4);

        badge5 = new JPanel();
        badge5.setBounds(100,80,100,60);
        badge5.setBackground(Color.white);
        badge5.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge5);

        badge6 = new JPanel();
        badge6.setBounds(200,80,100,60);
        badge6.setBackground(Color.white);
        badge6.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge6);

        badge7 = new JPanel();
        badge7.setBounds(0,140,100,60);
        badge7.setBackground(Color.white);
        badge7.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge7);

        badge8 = new JPanel();
        badge8.setBounds(100,140,100,60);
        badge8.setBackground(Color.white);
        badge8.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge8);

        badge9 = new JPanel();
        badge9.setBounds(200,140,100,60);
        badge9.setBackground(Color.white);
        badge9.setBorder(BorderFactory.createLineBorder(Color.black));
        badgePanel.add(badge9);
    }

    private void scorePanelSetup(){
        //Score Panel
        scorePanel = new JPanel();
        scorePanel.setBounds(700, 250,300,120);
        scorePanel.setBackground(Color.white);
        scorePanel.setFont(normalFont);
        scorePanel.setLayout(null);
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.setVisible(true);
        content.add(scorePanel);

        scoreTitle = new JLabel("Score", SwingConstants.CENTER);
        scoreTitle.setBounds(2,2,300,50);
        scoreTitle.setForeground(Color.black);
        scoreTitle.setBackground(Color.white);
        scoreTitle.setFont(normalFont);
        scorePanel.add(scoreTitle);
    }

    private void buttonSetup(){
        leftLocBtn = new JButton();
        leftLocBtn.setBounds(700, 380, 145, 30);
        leftLocBtn.setText("<<< Location1");
//        leftLocBtn.setBackground(Color.white);
//        leftLocBtn.setForeground(Color.black);
//        leftLocBtn.setFont(normalFont);
        leftLocBtn.setVisible(true);
        leftLocBtn.addActionListener(this);
        content.add(leftLocBtn);

        rightLocBtn = new JButton();
        rightLocBtn.setBounds(855, 380, 145, 30);
        rightLocBtn.setText("Location2 >>>");
//        rightLocBtn.setBackground(Color.white);
//        rightLocBtn.setForeground(Color.black);
//        rightLocBtn.setFont(normalFont);
        rightLocBtn.setVisible(true);
        rightLocBtn.addActionListener(this);
        content.add(rightLocBtn);

        helpBtn = new JButton();
        helpBtn.setBounds(700, 420, 145, 30);
        helpBtn.setText("Help");
//        helpBtn.setBackground(Color.white);
//        helpBtn.setForeground(Color.black);
//        helpBtn.setFont(normalFont);
        helpBtn.setVisible(true);
        helpBtn.addActionListener(this);
        content.add(helpBtn);

        quitBtn = new JButton();
        quitBtn.setBounds(855, 420, 145, 30);
        quitBtn.setText("Quit");
//        quitBtn.setBackground(Color.white);
//        quitBtn.setForeground(Color.black);
//        quitBtn.setFont(normalFont);
        quitBtn.setVisible(true);
        quitBtn.addActionListener(this);
        content.add(quitBtn);
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
        helpContent.setBackground(Color.decode("#ADC7D9"));

        //help title
        helpTitle = new JLabel("Need help?", SwingConstants.CENTER);
        helpTitle.setBounds(50,10,400,50);
        helpTitle.setFont(titleFont);
        helpTitle.setForeground(Color.black);
        helpContent.add(helpTitle,BorderLayout.CENTER);

        //text area for the help window
        helpText = new JTextArea();
        helpText.setText("Here are some basic instructions if you get stuck blah blah blah");
        helpText.setBounds(50,70,400,350);
        helpText.setForeground(Color.black);
        helpText.setFont(questionFont);
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
    }

    public void updateLocationDescription(String newDescription){ descriptionText.setText(newDescription);}

    public void updateQuestion(String newQuestion){
        questionText.setText(newQuestion);
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

    public void updateLeftLocationButton(String newLocation){
        leftLocBtn.setText("<<< " + newLocation);
    }

    public void updateRightLocationButton(String newLocation){
        rightLocBtn.setText(newLocation + " >>>");
    }

    private String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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

    //*************** MAIN (TESTING) ***************
//    public static void main(String[] args) {
//        GameGUI gui = new GameGUI("Welcome", "Here are the instructions! Nothing!");
//    }

}