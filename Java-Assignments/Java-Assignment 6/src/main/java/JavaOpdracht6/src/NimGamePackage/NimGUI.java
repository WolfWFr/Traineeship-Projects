package NimGamePackage;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class NimGUI implements ActionListener{

    JTextArea GameOutput = new JTextArea(25,20);
    JTextField UserInputField = new JTextField("",5);
    String[] PlayerNames = {"Player 1", "Player 2"};
    int PlayerNumber = 0;
    int NumberOfMatches = 11;
    JLabel NorthLabel = new JLabel(PlayerNames[PlayerNumber]+", how many matches do you want to take?");
    JLabel NorthCenterLabel = new JLabel("Number of matches: "+NumberOfMatches);
    int TwoPlayers;
    NimGameCore GameCore = new NimGameCore();

    private void BuildGUI(){
        JFrame frame = new JFrame();
        JPanel NorthPanel = new JPanel();
        JPanel NorthCenterPanel = new JPanel();
        JPanel CenterPanel = new JPanel();
        JPanel CenterPanel2 = new JPanel();
        CenterPanel.setLayout(new BorderLayout());
        CenterPanel2.setLayout(new BorderLayout());

        GameOutput.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(GameOutput);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        DefaultCaret caret = (DefaultCaret) GameOutput.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JButton UserInputButton = new JButton("Enter");

        NorthPanel.add(NorthLabel);
        NorthCenterPanel.add(NorthCenterLabel);
        NorthCenterPanel.add(UserInputField);
        NorthCenterPanel.add(UserInputButton);
        CenterPanel.add(NorthCenterPanel, BorderLayout.NORTH);
        CenterPanel.add(CenterPanel2, BorderLayout.CENTER);
        CenterPanel2.add(NorthCenterLabel, BorderLayout.NORTH);
        CenterPanel2.add(scroller, BorderLayout.CENTER);

        frame.getContentPane().add(NorthPanel, BorderLayout.NORTH);
        frame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,600);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Object[] options = {"Single-Player", "Two Players"};
        TwoPlayers = JOptionPane.showOptionDialog(frame,"Will you start a single-player or two-player game?","Setup Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        GameCore.setComputerPlayer(TwoPlayers);

        UserInputButton.addActionListener(this);
        UserInputField.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event){
        String UserInput = UserInputField.getText();
        try{
            int UserInputInt = Integer.parseInt(UserInput);
            if(GameCore.getNumberOfMatches()>0){
                if(0<UserInputInt&&UserInputInt<5){
                    GameOutput.append(GameCore.GoCheckYourself(UserInputInt,PlayerNumber));
                    PlayerNumber^=1;
                    NorthCenterLabel.setText("Number of matches: "+GameCore.getNumberOfMatches());
                    if(TwoPlayers==1){
                        NorthLabel.setText(PlayerNames[PlayerNumber]+", how many matches do you want to take?");
                    }
                    if(GameCore.getNumberOfMatches()>0){
                        GameOutput.append(GameCore.NextMove());
                    }
                }else{
                    GameOutput.append("Input requires integer between 0 and 5!\n\n");
                }
                UserInputField.selectAll();
            }else {
                GameOutput.append("The game has ended.\n");
            }
        }catch(NumberFormatException NFE){
            GameOutput.append("Input requires integer between 0 and 5!\n\n");
        }
    }

    public static void main(String[] args){
        NimGUI GUI = new NimGUI();
        GUI.BuildGUI();
    }

}
