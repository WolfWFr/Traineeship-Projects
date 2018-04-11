package nl.sogyo.mancala;

public class Player {
    private Player nextPlayer;
    private int PlayerNumber;
    private boolean myTurn;

    protected void changeTurn(){
        myTurn=!myTurn;
        nextPlayer.myTurn=!nextPlayer.myTurn;
    }

    protected boolean hasTurn(){
        return myTurn;
    }

    protected int getPlayerNumber(){
        return PlayerNumber;
    }

    protected void stopTurn(){
        myTurn=false;
    }

    protected Player getNextPlayer(){
        return nextPlayer;
    }

    protected Player(){
        PlayerNumber=1;
        myTurn=true;
        nextPlayer = new Player(this);
    }

    private Player(Player player1){
        PlayerNumber=2;
        myTurn=false;
        nextPlayer=player1;
    }
}
