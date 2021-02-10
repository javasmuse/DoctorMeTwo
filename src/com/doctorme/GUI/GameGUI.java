package com.doctorme.GUI;

import com.doctorme.entities.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameGUI implements ActionListener {
    private JButton quitBtn, helpBtn,submit ;
    private List<Location> board;
    private Container content;
    private final JFrame window = new JFrame();
    private JLabel currLocation;
    private JPanel questionPanel, currLocationPanel, answerPanel, buttonPanel;
    private JTextArea questionText;
    private JRadioButton optA, optB, optC, optD;

    GameGUI(){
        window.setSize(1000,700);
        window.setLocation(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        content = window.getContentPane();

        //Title of Current Location
        currLocationPanel = new JPanel();
        currLocationPanel.setBounds(50,50,600,30);
        currLocationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        currLocation = new JLabel("Current Location: ");
        currLocation.setBounds(50,50,600,30);

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
        buttonPanel.setBounds(50, 550,600,100);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        content.add(buttonPanel);

        helpBtn = new JButton("Help");
        buttonPanel.add(helpBtn);

        quitBtn = new JButton("Quit");
        buttonPanel.add(quitBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == helpBtn){
            //TODO
        }

        if(e.getSource() == quitBtn){

        }

    }

    public static void main(String[] args) {
        GameGUI gui = new GameGUI();

    }

}
