package JavaOpdracht5;

import javax.swing.*;
import javax.swing.text.*;
import java.util.*;

public class Opdracht5GUI {

    JTextArea Output;
    int[] Array;

    private void buildGUI(){
        Output = new JTextArea(10,20);
        JFrame UI = new JFrame();
        Output.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(Output);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        DefaultCaret caret = (DefaultCaret) Output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        UI.getContentPane().add(scroller);

        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UI.setSize(300,600);
        UI.setVisible(true);
    }

    private int[] buildArray(){
        int[] Array = new int[10];
        String ArrayPrint = "Built Array: \n{";
        int i;
        for(i=0;i<Array.length;i++){
            Array[i] = (int) (Math.random()*100);
            if(i==(Array.length-1)){
                ArrayPrint=ArrayPrint+Array[i]+"}\n\n";
            }else{
                ArrayPrint=ArrayPrint+Array[i]+", ";
            }
        }
        Output.append(ArrayPrint);
        return Array;
    }

    private void getHighest(int[] Array){
        int i;
        int highest = 0;
        for(i=0;i<Array.length;i++){
            if(Array[i]>highest){
                highest=Array[i];
            }
        }
        Output.append("Highest value: "+highest+".\n\n");
    }

    private void getLowestTwo(int[] Array){
        int i;
        int Lowest=100;
        int SecondLowest=100;
        int IndexOfLowest=0;

        for(i=0;i<Array.length;i++){
            if(Array[i]<Lowest){
                Lowest=Array[i];
                IndexOfLowest = i;
            }
        }
        for(i=0;i<Array.length;i++){
            if(Array[i]<SecondLowest&&i!=IndexOfLowest){
                SecondLowest=Array[i];
            }
        }
        Output.append("Two lowest values: "+Lowest+" and "+SecondLowest+".\n\n");
    }

    private void getEven(int[] Array){
        ArrayList<Integer> EvenList = new ArrayList<>();
        int i;

        for(i=0;i<Array.length;i++){
            if(Array[i]%2==0){
                EvenList.add(Array[i]);
            }
        }
        Output.append("All even numbers: \n"+EvenList+".\n\n");
    }

    private void getDivid(int[] Array){
        int i;
        ArrayList<Integer> Divid2 = new ArrayList<>();
        ArrayList<Integer> Divid3 = new ArrayList<>();
        ArrayList<Integer> Divid5 = new ArrayList<>();
        ArrayList<Integer> DividRest = new ArrayList<>();

        for(i=0;i<Array.length;i++){
            if(Array[i]%2==0){
                Divid2.add(Array[i]);
            }else{
                if(Array[i]%3==0){
                    Divid3.add(Array[i]);
                }else{
                    if(Array[i]%5==0){
                        Divid5.add(Array[i]);
                    }else{
                        DividRest.add(Array[i]);
                    }
                }
            }

        }
        Output.append("Numbers dividable by 2: \n"+Divid2+".\n");
        Output.append("Numbers dividable by 3: \n"+Divid3+".\n");
        Output.append("Numbers dividable by 5: \n"+Divid5+".\n");
        Output.append("The rest: \n"+DividRest+".\n\n");
    }

    private void getSorted(int[] Array){
        int i;
        int j;
        int PlaceHolder1;
        int PlaceHolder2;
        String ArrayPrint = "Array in ascending order: \n{";

        for(i=Array.length-1;i>0;i--){
            for(j=0;j<i;j++){
                if(Array[j]>Array[j+1]){
                    PlaceHolder1 = Array[j];
                    PlaceHolder2 = Array[j+1];
                    Array[j] = PlaceHolder2;
                    Array[j+1] = PlaceHolder1;
                }
            }
        }
        for(i=0;i<Array.length;i++){
            if(i==Array.length-1){
                ArrayPrint=ArrayPrint+Array[i]+"}.";
            }else{
                ArrayPrint=ArrayPrint+Array[i]+", ";
            }
        }
        Output.append(ArrayPrint);
    }

    public static void main(String[] args){
        Opdracht5GUI ListProgramme = new Opdracht5GUI();
        ListProgramme.buildGUI();

        int[] Array = ListProgramme.buildArray();
        ListProgramme.getHighest(Array);
        ListProgramme.getLowestTwo(Array);
        ListProgramme.getEven(Array);
        ListProgramme.getDivid(Array);
        ListProgramme.getSorted(Array);
    }

}
