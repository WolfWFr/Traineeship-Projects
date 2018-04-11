package SudokuSolverPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class SolverCore {

    String SudokuStart = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
    int[] SudokuInt = new int[81];
    ArrayList<SudokuValue> sudokuValues = new ArrayList<>();
    int startIndex;
    ArrayList<SudokuValue> valuesToPass;
    boolean failed = false;

    public void setSudoku(String SudokuInput){
        SudokuStart = SudokuInput;
    }

    public void SudokuToInt(String SudokuStart){
        String[] SudokuStringArray = SudokuStart.split("");
        int i;

        for(i=0;i<SudokuInt.length;i++){
            SudokuInt[i]=Integer.parseInt(SudokuStringArray[i]);
        }
    }

    public void convertToValue(){
        int i;
        for(i=0;i<SudokuInt.length;i++){
            int[] coordinates = {i%9,(i/9)};
            sudokuValues.add(new SudokuValue(SudokuInt[i],coordinates));
        }
    }

    public ArrayList<SudokuElement> produceLines(){
        ArrayList<SudokuElement> lines = new ArrayList<>();
        for(startIndex=0;startIndex<81;startIndex+=9){
            valuesToPass = new ArrayList<>(sudokuValues.subList(startIndex,startIndex+9));
            lines.add(new SudokuElement(valuesToPass));
        }
        return lines;
    }

     public ArrayList<SudokuElement> produceColumns(){
        ArrayList<SudokuElement> columns = new ArrayList<>();
        int j;
        for(startIndex=0;startIndex<9;startIndex++){
            valuesToPass = new ArrayList<>();
            int columnIndex = 0;
            for(j=0;j<9;j++){
                valuesToPass.add(sudokuValues.get(startIndex+columnIndex));
                columnIndex+=9;
            }
            columns.add(new SudokuElement(valuesToPass));
        }
        return columns;
    }

    public ArrayList<SudokuElement> produceBlocks(){
        ArrayList<SudokuElement> blocks = new ArrayList<>();
        int i;
        int j;
        int k;

        for(startIndex=0;startIndex<81;startIndex+=27){//loop for blocks of 3 final blocks
            for(i=0;i<9;i+=3){//loop for each final block
                valuesToPass = new ArrayList<>();
                for(k=0;k<27;k+=9){//loop for each line of the block
                    for(j=0;j<3;j++){//loop for each number per line
                        valuesToPass.add(sudokuValues.get(startIndex+i+k+j));
                    }
                }
                blocks.add(new SudokuElement(valuesToPass));
            }
        }
        return blocks;
    }

    public void goSolve(ArrayList<SudokuElement> lines,ArrayList<SudokuElement> columns,ArrayList<SudokuElement> blocks){
        ArrayList<ArrayList<SudokuElement>> masterList = new ArrayList<>(Arrays.asList(lines,columns,blocks));
        int lineIt;
        int masterListIt;
        int i;
        int j;
        int elementIt;
        boolean done=false;
        String lastResult = "";
        String newResult;

        while(!done) {

            //=== Method 1 ===//
            for(lineIt=0;lineIt<lines.size();lineIt++){
                ArrayList<int[]> possiblePositions = lines.get(lineIt).goCheckPositions();

                for(int[] position:possiblePositions){
                    ArrayList<Integer> possibleValues = lines.get(lineIt).goCheckValues();

                    for(masterListIt=1;masterListIt<3;masterListIt++){
                        boolean found = false;
                        ArrayList<Integer> otherPossibleValues=new ArrayList<>();
                        while(!found){
                            for(i=0;i<masterList.get(masterListIt).size();i++){
                                if(masterList.get(masterListIt).get(i).goCheckPositions().contains(position)){
                                    otherPossibleValues = masterList.get(masterListIt).get(i).goCheckValues();
                                    found=true;
                                }
                            }
                        }
                        possibleValues=filterValues(possibleValues,otherPossibleValues);
                        boolean changed = checkAndChangeValue(position, possibleValues);
                        if(changed){
                            break;
                        }
                    }
                }
            }

            //=== Method 2 ===//
            for(masterListIt=0;masterListIt<masterList.size();masterListIt++){
                ArrayList<Integer> otherElementIndexes = new ArrayList<>(Arrays.asList(0,1,2));
                otherElementIndexes.remove(masterListIt);
                for(elementIt=0;elementIt<9;elementIt++){
                    ArrayList<PossibleValueObject> possibleValueObjects = new ArrayList<>();
                    ArrayList<int[]> possiblePositions = masterList.get(masterListIt).get(elementIt).goCheckPositions();
                    ArrayList<Integer> possibleValues;

                    for(int[] position:possiblePositions){
                        ArrayList<Integer> possibleValues1 = masterList.get(masterListIt).get(elementIt).goCheckValues();
                        int otherElementIndex;
                        for(otherElementIndex=0;otherElementIndex<otherElementIndexes.size();otherElementIndex++){
                            boolean found = false;
                            ArrayList<Integer> otherPossibleValues=new ArrayList<>();
                            while(!found){
                                for(i=0;i<9;i++){
                                    if(masterList.get(otherElementIndexes.get(otherElementIndex)).get(i).goCheckPositions().contains(position)){
                                        otherPossibleValues = masterList.get(otherElementIndexes.get(otherElementIndex)).get(i).goCheckValues();
                                        found=true;
                                    }
                                }
                            }
                            possibleValues1=filterValues(possibleValues1,otherPossibleValues);
                        }
                        possibleValueObjects.add(new PossibleValueObject(possibleValues1,position));
                    }
                    for(i=1;i<10;i++){
                        int counter = 0;
                        int[] position2={0,0};
                        while(counter<2){
                            for(j=0;j<possibleValueObjects.size();j++){
                                if(possibleValueObjects.get(j).getValues().contains(i)){
                                    counter++;
                                    position2 = possibleValueObjects.get(j).getCoordinates();
                                }
                            }
                            if(counter==1){
                                possibleValues=new ArrayList<>(Arrays.asList(i));
                                checkAndChangeValue(position2,possibleValues);
                                break;
                            }
                            if(counter==0){
                                break;
                            }
                        }
                    }
                }
            }
            int counter = 0;
            for (i=0;i<sudokuValues.size();i++) {
                if (sudokuValues.get(i).getValue()==0) {
                    counter++;
                    break;
                }
            }
            if (counter == 0) {
                done = true;
            }
            newResult="";
            for(i=0;i<sudokuValues.size();i++){
                newResult+=sudokuValues.get(i).getValue();
            }
            if(newResult.equals(lastResult)){
                done = true;
                failed = true;
            }else{
                lastResult=newResult;
            }
        }
        print(sudokuValues);
    }

    public ArrayList<Integer> filterValues(ArrayList<Integer> possibleValues, ArrayList<Integer> otherPossibleValues){
        int i;
        for(i=0;i<possibleValues.size();i++){
            if(!otherPossibleValues.contains(possibleValues.get(i))){
                possibleValues.remove(i);
                i--;
            }
        }
        return possibleValues;
    }

    public boolean checkAndChangeValue(int[] position,ArrayList<Integer> possibleValues){
        int i;
        boolean changed=false;
        if(possibleValues.size()==1){
            boolean found = false;
            while(!found){
                for(i=0;i<sudokuValues.size();i++){
                    if(position.equals(sudokuValues.get(i).getCoordinates())){
                        sudokuValues.get(i).setValue(possibleValues.get(0));
                        found=true;
                        changed=true;
                    }
                }
            }
        }
        return changed;
    }

    public void print(ArrayList<SudokuValue> values){
        int i;
        if(failed){
            System.out.println("The sudoku was too hard to solve.\n");
        }
        for(i=0;i<values.size();i++){
            if(i%9==0){
                if(i==0){
                }else{
                    System.out.print("|");
                    System.out.println("");
                }
            }
            System.out.print("|"+values.get(i).getValue());
        }
        System.out.print("|");
    }

    public static void main(String[] args){
        SolverCore solver = new SolverCore();
        try{
            if(args[0].length()==81){
                solver.setSudoku(args[0]);
            }
        }catch(Exception ex){
        }

        solver.SudokuToInt(solver.SudokuStart);
        solver.convertToValue();

        ArrayList<SudokuElement> lines = solver.produceLines();
        ArrayList<SudokuElement> columns = solver.produceColumns();
        ArrayList<SudokuElement> blocks = solver.produceBlocks();

        solver.goSolve(lines,columns,blocks);

    }

}
