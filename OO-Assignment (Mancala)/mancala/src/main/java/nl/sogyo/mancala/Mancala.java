package nl.sogyo.mancala;

public class Mancala {
    private Player player1;

    private void makePlayers(){
        player1 = new Player();
    }

    private void makeContainers(){
        new Bowl(player1);
    }

    public static void main( String[] args ) {
        Mancala mancala = new Mancala();
        mancala.makePlayers();
        mancala.makeContainers();
    }

}