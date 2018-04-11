package RobotPackage;

import java.util.*;

public class Robot {
    private ArrayList<Integer> CommandList = new ArrayList<>();
    private ArrayList<String> Directions = new ArrayList<>();
    private String state;
    private int[] Coordinates = new int[2];

    public Robot(){
        Directions.add("North");Directions.add("East");Directions.add("South");Directions.add("West");
        state = Directions.get(0);
        Coordinates[0]=0;
        Coordinates[1]=0;
    }

    public Robot(int x, int y, String direction){
        Directions.add("North");Directions.add("East");Directions.add("South");Directions.add("West");
        if(Directions.contains(direction)){
            state = direction;
        }
        else{
            System.out.println("Direction is not legit. Options: North, East, South, West. Direction is case-sensitive!");
        }
        Coordinates[0]=x;
        Coordinates[1]=y;
    }

    public String getState(){
        return state;
    }

    public int[] getCoordinates(){
        return Coordinates;
    }

    public void turnLeft(){
        CommandList.add(0);
        CommandList.add(-1);

        /*int currentIndex = Directions.indexOf(state);
        int newIndex = currentIndex-1;
        if(newIndex==-1){
            newIndex+=4;
        }*/
    }

    public void turnRight(){
        CommandList.add(0);
        CommandList.add(1);

        /*int currentIndex = Directions.indexOf(state);
        int newIndex = currentIndex+1;
        System.out.println(newIndex);
        if(newIndex==4){
            newIndex-=4;
        }*/
    }

    public void moveForward(){
        CommandList.add(1);
        CommandList.add(1);
    }

    public void moveBackward(){
        CommandList.add(1);
        CommandList.add(-1);
    }

    public void moveForward(int speed){
       if(0<speed&&speed<4){
           CommandList.add(1);
           CommandList.add(speed);
       }else{
           System.out.println("Speed is not within allowed limits. Give speed between 0 and 4.");
       }
    }

    public void Execute(){
        int i;
        for(i=0;i<CommandList.size();i++){
            if(CommandList.get(i)==0){
                int currentIndex = Directions.indexOf(state);
                int newIndex = currentIndex+CommandList.get(i+1);
                if(newIndex==4) {
                    newIndex -= 4;
                }
                state = Directions.get(newIndex);
            }else{
                if(state==Directions.get(0)||state==Directions.get(2)){
                    if(state==Directions.get(0)) {
                        Coordinates[1]+=CommandList.get(i+1);
                    }else{
                        Coordinates[1]-=CommandList.get(i+1);
                    }
                }else{
                    if(state==Directions.get(1)){
                        Coordinates[0]+=CommandList.get(i+1);
                    }else{
                        Coordinates[0]-=CommandList.get(i+1);
                    }
                }
            }
            i++;
        }
    }

}
