public class Opdracht1_LeapYear {
    static final byte leapYearDivid = 4;
    static final short leapYearDividCen = 400;

    public static void main(String[] args){
        short yearToTest = 2000;
        Opdracht1_LeapYear LYChecker = new Opdracht1_LeapYear();
        boolean century = LYChecker.cenCheck(yearToTest);
        boolean leapYear = LYChecker.check(yearToTest, century);
        if(leapYear){
            System.out.println("The year "+yearToTest+" is a leap year.");
        }else{
            System.out.println("The year "+yearToTest+" is not a leap year.");
        }

    }

    private boolean cenCheck(short year){
        boolean isCentury;
        int intYear = (int) year;
        if (Math.abs(intYear%100)==0){
            isCentury = true;
        }
        else{
            isCentury = false;
        }
        return isCentury;
    }

    private boolean check(short year,boolean century){
        boolean isLeapYear;
        boolean cencen = century;
        double checkResult;

        if(cencen){
            checkResult = (double) year/leapYearDividCen;
        }else{
            checkResult = (double) year/leapYearDivid;
        }
        double absResult = Math.abs(checkResult);

        if(absResult == Math.rint(absResult)){
            isLeapYear = true;
        }else{
            isLeapYear = false;
        }
        return isLeapYear;
    }
}
