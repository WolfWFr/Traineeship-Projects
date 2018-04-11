package SudokuSolverPackage;

import java.util.ArrayList;

public class PossibleValueObject {
    private int[] coordinates = {0,0};
    private ArrayList<Integer> values = new ArrayList<>();

    public PossibleValueObject(ArrayList<Integer> possibleValues, int[] position){
        setValues(possibleValues);
        setCoordinates(position);
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

}
