package FibonacciPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstGUI implements ActionListener, ItemListener{
    JButton button;
    JTextField text;
    JTextArea result;
    JLabel westlabel;
    JLabel eastlabel;
    JCheckBox evenSumBox;
    String input;
    String evenSumNumberFormat;
    long evenSumNumber;
    int inputInt;

    public static void main(String[] args){
        FirstGUI GUI = new FirstGUI();
        GUI.buildGUI();
    }
    public void buildGUI(){
        JFrame frame = new JFrame();
        JPanel westpanel = new JPanel();
        JPanel eastpanel = new JPanel();
        JPanel middlepanel = new JPanel();
        JPanel southwestpanel = new JPanel();

        westlabel = new JLabel("       Give 'N', for the Nth term in the Fibonacci sequence.       ");
        eastlabel = new JLabel("                               Result:");
        button = new JButton("Enter");
        text = new JTextField();
        result = new JTextArea(4,19);
        evenSumBox = new JCheckBox("Give sum of even values");
        text.selectAll();
        text.requestFocus();
        result.setLineWrap(true);

        westpanel.setLayout(new BorderLayout());
        eastpanel.setLayout(new BorderLayout());

        eastpanel.add(eastlabel,BorderLayout.NORTH);

        JScrollPane scroller = new JScrollPane(result);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        eastpanel.add(scroller,BorderLayout.CENTER);

        southwestpanel.add(evenSumBox);
        southwestpanel.add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        westpanel.add(westlabel, BorderLayout.NORTH);
        westpanel.add(text, BorderLayout.CENTER);
        westpanel.add(southwestpanel, BorderLayout.SOUTH);
        frame.getContentPane().add(westpanel, BorderLayout.WEST);
        frame.getContentPane().add(eastpanel, BorderLayout.EAST);
        frame.getContentPane().add(middlepanel, BorderLayout.CENTER);
        frame.setSize(595,110);
        frame.setVisible(true);

        button.addActionListener(this);
        text.addActionListener(this);
        evenSumBox.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent evt){
        if (evenSumBox.isSelected()){
            evenSumBox.setSelected(true);
        }else{
            evenSumBox.setSelected(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        input = text.getText();
        try {
            inputInt = Integer.parseInt(input);
            if(inputInt<96){
                if(input!=null&&inputInt>0){
                    FibonacciPackage.Fibonacci calc = new FibonacciPackage.Fibonacci();
                    long[] FibArray = calc.build(inputInt);
                    long FibNumber = calc.check(FibArray, inputInt);
                    String FibNumberFormat = String.format("%, d", FibNumber);
                    result.append("The Fibonacci number at term "+inputInt+" is: \n"+FibNumberFormat+"\n");
                    if(evenSumBox.isSelected()){
                        if(inputInt<93){
                            evenSumNumber = calc.checkEvenSum(FibArray);
                            evenSumNumberFormat = String.format("%, d", evenSumNumber);
                            result.append("Sum of even terms up to term "+inputInt+":\n"+evenSumNumberFormat+"\n");
                        }else{
                            result.append("Sum of even terms exceeded long datatype positive limit.\n");
                        }
                    }
                }else{
                    result.append("Input requires positive integer! \n");
                }
            }else{
                result.append("Result is out of bounds!\nLong datatype positive limit reached. \n");
            }

        }catch(NumberFormatException NFE){
            result.append("Input requires positive integer! \n");
        }
    }
}
