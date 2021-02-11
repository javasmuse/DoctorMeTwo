package com.doctorme.GUI;

import com.doctorme.app.Game;
import com.doctorme.entities.Location;
import com.doctorme.util.GameText;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI implements ActionListener {
    private JButton quitBtn, helpBtn, submit, back, enterGameBtn, leftLocBtn, rightLocBtn, helpCloseBtn;
    private List<Location> board;
    private Container content;
    private final JFrame window = new JFrame();
    private JFrame helpWindow;
    private JLabel currLocation, welcomeTitle, badgeTitle, scoreTitle;
    private JPanel questionPanel, currLocationPanel, answerPanel, helpPanel, buttonPanelHelpPage, badgePanel, scorePanel, enterGamePanel, badge1, badge2, badge3, badge4, badge5, badge6, badge7, badge8, badge9;
    private JTextArea helpText, gameInstructions, questionText;
    private JRadioButton optA, optB, optC, optD;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
    private static final Font questionFont = new Font("Times New Roman", Font.ITALIC, 16);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 16);
    private JScrollPane scrollPane;
    private Game game = new Game();

    public GameGUI(){
        //Setting the GUI window
        window.setSize(1050,520);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode("#ADC7D9"));

        //Welcome Title
        welcomeTitle = new JLabel("Welcome to the Doctor Me Game!", SwingConstants.CENTER);
        welcomeTitle.setBounds(50,10,950,50);
        welcomeTitle.setForeground(Color.black);
        welcomeTitle.setFont(titleFont);
        content.add(welcomeTitle);

        //----Help or Instruction of the game
        enterGamePanel = new JPanel();
        enterGamePanel.setBounds(50,70, 950, 350);
        enterGamePanel.setBackground(Color.white);
//        enterGamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(enterGamePanel);

        gameInstructions = new JTextArea();
        gameInstructions.setText(game.printInstructions());
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
        enterGameBtn.setBounds(475, 430, 100, 30);
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
        }else if(e.getSource() == helpBtn){
            displayHelpWindow();
        }else if(e.getSource() == helpCloseBtn){
            helpWindow.dispose();
        }else if(e.getSource() == quitBtn){
            window.dispose();
            System.exit(0);
        }

        if(e.getSource()==back){
            //TODO: Go back to the Game Screen which will be in a function later on
        }


    }

    //*************** SETUP METHODS ***************
    private void setup(){
        locationPanelSetup();
        questionPanelSetup();
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

        currLocation = new JLabel("Current Location");
        currLocation.setBounds(50,50,600,30);
        currLocation.setForeground(Color.white);
        currLocation.setFont(titleFont);
        currLocationPanel.add(currLocation);
    }

    private void questionPanelSetup(){
        //Panel for Question Display
        questionPanel = new JPanel();
        questionPanel.setBounds(50,100,600,130);
        questionPanel.setBackground(Color.white);
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(questionPanel);

        questionText = new JTextArea("How much wood would a woodchuck chuck if a woodchuck could chuck wood? Peter Piper picked a peck of pickled peppers. If Peter Piper picked a peck of pickled peppers, how many pecks of pickled peppers did Peter Piper pick?");
        questionText.setBounds(52,102,596,96);
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

        optA = new JRadioButton("Option A: A lot of wood");
        optA.setBounds(2, 2, 596, 41);
        optA.setBackground(Color.white);
        optA.setFont(normalFont);
        optA.setVisible(true);
        answerPanel.add(optA);

        optB = new JRadioButton("Option B: Peter Piper's woodchuck");
        optB.setBounds(2, 42, 596, 41);
        optB.setBackground(Color.white);
        optB.setFont(normalFont);
        optB.setVisible(true);
        answerPanel.add(optB);

        optC = new JRadioButton("Option C: A peck of wood");
        optC.setBounds(2, 82, 596, 41);
        optC.setBackground(Color.white);
        optC.setFont(normalFont);
        optC.setVisible(true);
        answerPanel.add(optC);

        optD = new JRadioButton("Option D: Peterpickledpiperpeppersppspspsps?????");
        optD.setBounds(2, 122, 596, 41);
        optD.setBackground(Color.white);
        optD.setFont(normalFont);
        optD.setVisible(true);
        answerPanel.add(optD);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(optA);
        radioGroup.add(optB);
        radioGroup.add(optC);
        radioGroup.add(optD);

        submit = new JButton("Submit");
        submit.setBounds(260,165,80,30);
        answerPanel.add(submit);
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

    //*************** ACCESSOR METHODS ***************
    private void updateHelpScreenText(String newHelpText){
        helpText.setText(newHelpText);
    }

    private void updateInstructionsText(String newInstructions){
        gameInstructions.setText(newInstructions);
    }

    private void updateCurrentLocation(String newLocation){
        currLocation.setText(newLocation);
    }

    private void updateQuestion(String newQuestion){
        questionText.setText(newQuestion);
    }

    private void updateOptionA(String newOption){
        optA.setText(newOption);
    }

    private void updateOptionB(String newOption){
        optB.setText(newOption);
    }

    private void updateOptionC(String newOption){
        optC.setText(newOption);
    }

    private void updateOptionD(String newOption){
        optD.setText(newOption);
    }

    private void updateLeftLocationButton(String newLocation){
        leftLocBtn.setText("<<< " + newLocation);
    }

    private void updateRightLocationButton(String newLocation){
        rightLocBtn.setText(newLocation + " >>>");
    }

    //*************** MAIN (TESTING) ***************
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
    }

}
