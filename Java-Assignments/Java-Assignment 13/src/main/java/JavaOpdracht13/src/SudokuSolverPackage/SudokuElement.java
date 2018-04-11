package SudokuSolverPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuElement {
    ArrayList<SudokuValue> list;
    public SudokuElement(ArrayList<SudokuValue> values){
        list=values;
    }

    public ArrayList<Integer> goCheckValues(){
        ArrayList<Integer> possibleValues = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

        int i;
        for(i=0;i<list.size();i++){
            if(possibleValues.contains(list.get(i).getValue())){
                possibleValues.remove(possibleValues.indexOf(list.get(i).getValue()));
            }
        }
        return possibleValues;
    }

    public ArrayList<int[]> goCheckPositions(){
        ArrayList<int[]> possiblePositions=new ArrayList<>();

        for(SudokuValue coord:list){
            possiblePositions.add(coord.getCoordinates());
        }

        for(SudokuValue coord:list){
            if(coord.getValue()>0){
                possiblePositions.remove(possiblePositions.indexOf(coord.getCoordinates()));
            }
        }

        return possiblePositions;
    }

}
