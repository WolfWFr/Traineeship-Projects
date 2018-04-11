package SudokuSolverPackage;

public class SudokuValue {
    private int[] coordinates = {0,0};
    private int value = 0;

    public SudokuValue(int value, int[] coordinates){
        setCoordinates(coordinates);
        setValue(value);
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

}
