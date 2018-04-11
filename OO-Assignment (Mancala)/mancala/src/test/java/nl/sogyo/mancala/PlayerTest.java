package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testPlayer1HasTurn(){
        Player player1 = new Player();
        Assert.assertTrue(player1.hasTurn());
    }

    @Test
    public void testPlayer2IsPlayer(){
        Player player1 = new Player();
        Assert.assertTrue(player1.getNextPlayer() instanceof Player);
    }

    @Test
    public void testPlayer2HasNoTurn(){
        Player player1 = new Player();
        Assert.assertFalse(player1.getNextPlayer().hasTurn());
    }

    @Test
    public void testPlayerChangeTurn(){
        Player player1 = new Player();
        player1.changeTurn();
        Assert.assertFalse(player1.hasTurn());
    }

    @Test
    public void testPlayer1ChangeTurnChangesOtherturnToo(){
        Player player1 = new Player();
        player1.changeTurn();
        Assert.assertTrue(player1.getNextPlayer().hasTurn());
    }

    @Test
    public void testIfChangeTurnSwitchesPlayersAfterMultipleIterations(){
        Player player1 = new Player();
        player1.changeTurn();
        player1.changeTurn();
        Assert.assertTrue(player1.hasTurn());
    }

    @Test
    public void testIfChangeTurnWorksIdenticallyWhenCalledOnOtherPlayer(){
        Player player1 = new Player();
        player1.getNextPlayer().changeTurn();
        Assert.assertTrue(player1.getNextPlayer().hasTurn());
    }

}
