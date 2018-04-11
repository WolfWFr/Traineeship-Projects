package NimGamePackage;

public class NimGameCore {
    boolean ComputerPlayer = false;
    private int NumberOfMatches = 11;
    String[] PlayerNames = {"Player 1", "Player 2"};
    NimComputerPlayer Computer = new NimComputerPlayer();

    public void setComputerPlayer(int TwoPlayers){
        if (TwoPlayers==0){
            ComputerPlayer = true;
            PlayerNames[1]="Computer";
        }
    }

    public String GoCheckYourself(int PlayerMatchNum,int WhichPlayer){
        if (PlayerMatchNum <= NumberOfMatches){
            NumberOfMatches -= PlayerMatchNum;
            if (NumberOfMatches>0) {
                return PlayerNames[WhichPlayer] + " takes " + PlayerMatchNum + " matches and leaves " + NumberOfMatches + ".\n"+
                        PlayerNames[WhichPlayer^=1]+" is next.\n\n";
            }else{
                return PlayerNames[WhichPlayer] + " takes " + PlayerMatchNum + " matches and leaves " + NumberOfMatches + ".\n"+
                        PlayerNames[(WhichPlayer^=1)]+" wins!\n\n";
            }
        }else{
            return "You cannot take more matches than are available!\n\n";
        }
    }

    public String NextMove(){
        if (ComputerPlayer){
            int ComputerMove = Computer.MakeMove(NumberOfMatches);
            return GoCheckYourself(ComputerMove,1);
        }else{
            return "";
        }
    }

    public int getNumberOfMatches(){
        return NumberOfMatches;
    }

    }
