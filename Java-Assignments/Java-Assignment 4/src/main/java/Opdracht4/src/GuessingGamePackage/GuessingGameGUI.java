package GuessingGamePackage;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class GuessingGameGUI implements ActionListener{

    JTextField UserInputField;
    JButton EnterUserInputButton;
    JTextArea uiOutput;
    String UserGuess;
    GuessMaker calc = new GuessMaker();
    String UserGuessResult;
    boolean limitReached = false;

    public void buildGUI(){
        JPanel topNorthPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topCenterPanel = new JPanel();

        JLabel topNorthLabel = new JLabel("Guess number between 0 and 50:");
        uiOutput = new JTextArea(10,20);
        UserInputField = new JTextField("",5);
        EnterUserInputButton = new JButton("Enter");

        UserInputField.requestFocus();
        UserInputField.selectAll();

        uiOutput.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(uiOutput);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        DefaultCaret caret = (DefaultCaret) uiOutput.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        topNorthPanel.add(topNorthLabel);
        topCenterPanel.add(UserInputField);
        topCenterPanel.add(EnterUserInputButton);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(topCenterPanel, BorderLayout.NORTH);
        centerPanel.add(scroller, BorderLayout.CENTER);

        JFrame frame = new JFrame();
        frame.getContentPane().add(topNorthPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,400);
        frame.setVisible(true);

        EnterUserInputButton.addActionListener(this);
        UserInputField.addActionListener(this);

        calc.RandomNumber();
        uiOutput.append("The game has initialized, you may begin.\n");
    }

    public void actionPerformed(ActionEvent event){
        UserGuess = UserInputField.getText();
        try{
            int UserGuessInt = Integer.parseInt(UserGuess);
            if(UserGuessInt>0){
                UserGuessResult = calc.checkUserGuess(UserGuessInt);
                if(UserGuessResult!="The game has ended.\n"){
                    uiOutput.append("Your Guess: "+UserGuess+"\n");
                }
                uiOutput.append(UserGuessResult);
            }else{
                uiOutput.append("Guess must be a positive integer!\n");
            }
            if(UserGuessResult=="Guess is correct!\n"){
                calc.stopGame();
            }
        }catch(NumberFormatException NFE){
            uiOutput.append("Guess must be positive integer!\n");
        }
        if((UserGuessResult!="Guess is correct!\n"&&UserGuessResult!="The game has ended.\n") && calc.getNumberOfGuesses()>9){
            calc.stopGame();
            if(!limitReached){
                uiOutput.append("You have reached the limit of 10 guesses.\n");
                limitReached=true;
            }
        }
    }

    public static void main(String[] args) {
        GuessingGameGUI NewGame = new GuessingGameGUI();
        NewGame.buildGUI();
    }
}
