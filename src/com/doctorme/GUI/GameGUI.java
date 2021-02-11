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
    private JLabel currLocation, welcomeTitle, badgeTitle;
    private JPanel enterGameButtonPanel, welcomeTitlePanel, questionPanel, currLocationPanel, answerPanel, buttonPanel, helpPanel, buttonPanelHelpPage, badgePanel, scorePanel, enterGamePanel, badge1, badge2, badge3, badge4, badge5, badge6, badge7, badge8, badge9;
    private JTextArea questionText, badgeText, scoreText, enterGameHelp;
    private JRadioButton optA, optB, optC, optD;
    private static final Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
    private static final Font questionFont = new Font("Times New Roman", Font.ITALIC, 16);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 16);

    GameGUI(){
        //Setting the GUI window
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
        content.add(currLocationPanel);

        currLocation = new JLabel("Current Location");
        currLocation.setBounds(50,50,600,30);
        currLocation.setForeground(Color.white);
        currLocation.setFont(titleFont);
        currLocationPanel.add(currLocation);

        //Panel for Question Display
        questionPanel = new JPanel();
        questionPanel.setBounds(50,100,600,100);
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
//        badgeTitle.setBorder(BorderFactory.createLineBorder(Color.black));
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
