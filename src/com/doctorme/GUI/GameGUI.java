package com.doctorme.GUI;

import com.doctorme.entities.Location;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameGUI implements ActionListener {
    private JButton quitBtn, helpBtn,submit, back, enterGameBtn ;
    private List<Location> board;
    private Container content;
    private final JFrame window = new JFrame();
    private JLabel currLocation, welcomeTitle;
    private JPanel enterGameButtonPanel, welcomeTitlePanel, questionPanel, currLocationPanel, answerPanel, buttonPanel, helpPanel, buttonPanelHelpPage, badgePanel, scorePanel, enterGamePanel;
    private JTextArea questionText, badgeText, scoreText, enterGameHelp;
    private JRadioButton optA, optB, optC, optD;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 16);

    GameGUI(){
        window.setSize(1000,700);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode("#ADC7D9"));

        //Welcome Title
        welcomeTitlePanel = new JPanel();
        welcomeTitlePanel.setBounds(50,50,600,50);
//        welcomeTitlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        welcomeTitlePanel.setBackground(Color.decode("#ADC7D9"));
        content.add(welcomeTitlePanel);
        window.setSize(800,800);

        welcomeTitle = new JLabel("Welcome to the Doctor Me Game! ");
        welcomeTitle.setFont(new Font("Consolas", Font.BOLD, 20 ));
        welcomeTitle.setBounds(50,1,600,150);
        welcomeTitlePanel.add(welcomeTitle,BorderLayout.CENTER);

        //----Help or Instruction of the game
        enterGamePanel = new JPanel();
        enterGamePanel.setBounds(50,125, 600, 500);
        enterGamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(enterGamePanel);

        enterGameHelp = new JTextArea("Instructions for the Game: ");
        enterGameHelp.setEditable(false);
        enterGameHelp.setBounds(50,125,600,500);
        enterGamePanel.add(enterGameHelp);

        //EnterGame Panel for button
        enterGameButtonPanel = new JPanel();
        enterGameButtonPanel.setBounds(50,650,600,100);
        enterGameButtonPanel.setBackground(Color.decode("#ADC7D9"));
//        enterGameButtonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(enterGameButtonPanel);

        enterGameBtn = new JButton("Enter Game");
        enterGameBtn.setBounds(300,750,100,100);
        enterGameButtonPanel.add(enterGameBtn);
        enterGameBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==enterGameBtn){
            window.remove(welcomeTitlePanel);
            window.remove(enterGamePanel);
            window.remove(enterGameButtonPanel);
            gameScreen();
        }
        if(e.getSource() == helpBtn){

            window.remove(currLocationPanel);
            window.remove(questionPanel);
            window.remove(answerPanel);
            window.remove(buttonPanel);
            window.remove(badgePanel);
            window.remove(scorePanel);

            helpPanel = new JPanel();
            helpPanel.setBounds(50,50,600,500);
            helpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            //TODO: Fill with Help instructions of the game

            content.add(helpPanel);

            buttonPanelHelpPage = new JPanel();
            buttonPanelHelpPage.setBounds(50,600,600,100);
            buttonPanelHelpPage.setBorder(BorderFactory.createLineBorder(Color.black));

            back = new JButton("Back");

            buttonPanelHelpPage.add(back);

            content.add(buttonPanelHelpPage);
            window.setVisible(true);

        }

        if(e.getSource() == quitBtn){
            window.dispose();
        }

        if(e.getSource()==back){
            //TODO: Go back to the Game Screen which will be in a function later on
        }


    }

    public void gameScreen(){
        window.setSize(1000,700);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();
        content.setBackground(Color.decode("#ADC7D9"));

        //Title of Current Location
        currLocationPanel = new JPanel();
        currLocationPanel.setBounds(50,30,600,50);
        currLocationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        currLocationPanel.setBackground(Color.decode("#043769"));

        currLocation = new JLabel("Current Location");
        currLocation.setBounds(50,50,600,30);
        currLocation.setForeground(Color.white);
        currLocation.setFont(titleFont);
        currLocationPanel.add(currLocation);

        content.add(currLocationPanel);

        //Panel for Question Display
        questionPanel = new JPanel();
        questionPanel.setBounds(50,100,600,100);
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(questionPanel);

        questionText = new JTextArea("Question 1: ");
        questionText.setBounds(50,100,600,100);
        questionText.setEditable(false);
        questionPanel.add(questionText);

        //Answer Panel
        answerPanel = new JPanel();
        answerPanel.setBounds(50,250,600,200);
//        answerPanel.setLayout(null);
        answerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        answerPanel.setVisible(true);

        Box verticalBox = Box.createVerticalBox();
        verticalBox.setVisible(true);
        verticalBox.setBorder(BorderFactory.createLineBorder(Color.black));

        content.add(answerPanel);

        optA = new JRadioButton("Option A");
        optA.setVisible(true);

        optB = new JRadioButton("Option B");
        optB.setVisible(true);

        optC = new JRadioButton("Option C");
        optC.setVisible(true);

        optD = new JRadioButton("Option D");
        optD.setVisible(true);

        verticalBox.add(optA);
        verticalBox.add(optB);
        verticalBox.add(optC);
        verticalBox.add(optD);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(optA);
        radioGroup.add(optB);
        radioGroup.add(optC);
        radioGroup.add(optD);

        answerPanel.add(verticalBox);

        submit = new JButton("Submit");
        submit.setBounds(260,160,80,30);
        answerPanel.add(submit);

        //Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBounds(50, 470,600,100);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        content.add(buttonPanel);

        helpBtn = new JButton("Help");
        buttonPanel.add(helpBtn);
        helpBtn.addActionListener(this);

        quitBtn = new JButton("Quit");
        buttonPanel.add(quitBtn);
        quitBtn.addActionListener(this);

        //Badge Panel
        badgePanel = new JPanel();
        badgePanel.setBounds(700,30,300,200);
        badgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(badgePanel);

        badgeText = new JTextArea("Badges Earned: ");
        badgeText.setEditable(false);
        badgeText.setBounds(700,30,300,200);
        badgePanel.add(badgeText);

        //Score Panel
        scorePanel = new JPanel();
        scorePanel.setBounds(700, 250,300,300);
        badgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(scorePanel);

        scoreText = new JTextArea("Score Update: ");
        scoreText.setEditable(false);
        scoreText.setBounds(700,250,300,350);
        scorePanel.add(scoreText);

    }

    public static void main(String[] args) {
        GameGUI gui = new GameGUI();

    }

}
