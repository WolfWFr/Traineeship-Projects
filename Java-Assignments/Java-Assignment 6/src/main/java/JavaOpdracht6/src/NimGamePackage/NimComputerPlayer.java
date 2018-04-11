package NimGamePackage;

public class NimComputerPlayer {
    int result;

    public static void main(String[] args){

    }
    public int MakeMove(int NumberOfMatches){
        if (NumberOfMatches<6&&NumberOfMatches>1){
            result = NumberOfMatches-1;
        }else{
            if(NumberOfMatches>6&&NumberOfMatches<11){
                result = NumberOfMatches-6;
            }else{
                if(NumberOfMatches==1||NumberOfMatches==11){
                    result = 1;
                }
            }
        }
        return result;
    }

}
