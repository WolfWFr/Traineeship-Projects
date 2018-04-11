import java.time.LocalTime;
import java.util.*;

public class ASCIIClock {

    //The numbers 10,11, and 12 represent 'A','P', and ':', respectively.
    ArrayList<Integer> topLineNeg = new ArrayList<>(Arrays.asList(1,4,12));
    ArrayList<Integer> middleLineNeg = new ArrayList<>(Arrays.asList(0,1,7,12));
    ArrayList<Integer> bottomLineNeg = new ArrayList<>(Arrays.asList(1,4,7,10,11,12));

    int[] vertLinePos0 = {3,3};
    int[] vertLinePos1 = {2,2};
    int[] vertLinePos2 = {2,1};
    int[] vertLinePos3 = {2,2};
    int[] vertLinePos4 = {3,2};
    int[] vertLinePos5 = {1,2};
    int[] vertLinePos6 = {1,3};
    int[] vertLinePos7 = {2,2};
    int[] vertLinePos8 = {3,3};
    int[] vertLinePos9 = {3,2};
    int[] vertLinePos10 = {3,3};
    int[] vertLinePos11 = {3,1};

    int[][] vertLinePosAll = {vertLinePos0,vertLinePos1,vertLinePos2,vertLinePos3,vertLinePos4,vertLinePos5,
            vertLinePos6,vertLinePos7,vertLinePos8,vertLinePos9,vertLinePos10,vertLinePos11};

    String[] digitMorph = {"hor","ver","hor","ver","hor"};

    private int size=2;
    private int timeFormat = 24;

    public void setSize(int userSize){
        if(0<userSize&&userSize<6){
            size = userSize;
        }else{
            System.out.println("Give size as positive integer between 0 and 6!");
        }
    }

    public void setTimeFormat(int format){
        timeFormat=format;
    }

    public String build(){
        int hor_ver;
        int digitIndex;
        int verStep;
        String output = "";

        int[] input = getTime();

        for(hor_ver=0;hor_ver<digitMorph.length;hor_ver++){
            if(digitMorph[hor_ver]=="hor"){
                for(digitIndex=0;digitIndex<input.length;digitIndex++){
                    output+=horGen(input[digitIndex],hor_ver);
                }
                output+="\n";
            }else{
                for(verStep=0;verStep<size;verStep++){
                    for(digitIndex=0;digitIndex<input.length;digitIndex++){
                        if(input[digitIndex]==12){
                            output+=colonGen();
                        }else{
                            if(hor_ver==1){
                                output+=verGen1(input[digitIndex]);
                            }else{
                                output+=verGen2(input[digitIndex]);
                            }
                        }
                    }
                    output+="\n";
                }
            }
        }

        return output;

    }

    public int[] getTime(){
        int i;
        LocalTime localtime = LocalTime.now();
        int[] input;
        int hour = localtime.getHour();
        int minute = localtime.getMinute();
        if(timeFormat==24){
            input = new int[5];
            for(i=1;i>=0;i--){
                input[i]=(hour%10);
                hour/=10;
            }
            input[2]=12;
            for(i=4;i>2;i--){
                input[i]=(minute%10);
                minute/=10;
            }
        }else{
            input = new int[6];
            if(00<=hour&&hour<12){
                input[0]=10;
            }else{
                input[0]=11;
            }
            if(hour==00){
                hour=12;
            }
            for(i=2;i>0;i--){
                input[i]=(hour%10);
                hour/=10;
            }
            input[3]=12;
            for(i=5;i>3;i--){
                input[i]=(minute%10);
                minute/=10;
            }
        }

        return input;
    }

    public String horGen(int digit, int pos){
        int i;
        ArrayList<Integer> horList;
        String horOutput = "";
        horOutput+=" ";

        if(pos==0){
            horList=topLineNeg;
        }else{
            if(pos==2){
                horList=middleLineNeg;
            }else{
                horList=bottomLineNeg;
            }
        }

        for(i=0;i<size;i++){
            if(!horList.contains(digit)){
                horOutput+="-";
            }else{
                horOutput+=" ";
            }
        }
        horOutput+=" ";
        return horOutput;
    }

    public String verGen1(int digit){
        String verOutput = "";
        int i;
        if(vertLinePosAll[digit][0]==1||vertLinePosAll[digit][0]==3){
            verOutput+="|";
        }else{
            verOutput+=" ";
        }
        for(i=0;i<size;i++){
            verOutput+=" ";
        }
        if(vertLinePosAll[digit][0]>1){
            verOutput+="|";
        }else{
            verOutput+=" ";
        }
        return verOutput;
    }

    public String verGen2(int digit){
        String verOutput = "";
        int i;
        if(vertLinePosAll[digit][1]==1||vertLinePosAll[digit][1]==3){
            verOutput+="|";
        }else{
            verOutput+=" ";
        }
        for(i=0;i<size;i++){
            verOutput+=" ";
        }
        if(vertLinePosAll[digit][1]>1){
            verOutput+="|";
        }else{
            verOutput+=" ";
        }
        return verOutput;
    }

    public String colonGen(){
        String colonOutput="";
        int i;
        colonOutput+=" ";
        for(i=0;i<size;i++){
            colonOutput+="-";
        }
        colonOutput+=" ";

        return colonOutput;
    }

    public static void main(String[] args){
        ASCIIClock clocky = new ASCIIClock();
        int i;

        for(i=0;i<args.length;i++){
            if(args[i].equals("-s")&&i<args.length-1){
                try{
                    int userSize = Integer.parseInt(args[i+1]);
                    clocky.setSize(userSize);
                }catch(NumberFormatException NFE){
                    System.out.println("NumberFormatException! Follow -s command with positive integer!");
                }
            }
            if(args[i].equals("-12")){
                clocky.setTimeFormat(12);
            }
        }

        String output = clocky.build();
        System.out.println(output);
    }



}
