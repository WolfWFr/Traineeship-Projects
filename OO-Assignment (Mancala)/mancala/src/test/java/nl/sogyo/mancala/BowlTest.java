package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BowlTest {

    @Test
    public void testFirstBowlHasConnectionToOtherBowl(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertTrue(bowl.neighbour instanceof Bowl);
    }

    @Test
    public void testfirstBowlHasFourStones(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertEquals(4,bowl.getStonesFromBowl());
    }

    @Test
    public void testsixthBowlHasConnectionToKalaha(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertTrue(bowl.getToContainer(6) instanceof Kalaha);
    }

    @Test
    public void testThirteenthBowlHasConnectionToKalaha(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertTrue(bowl.getToContainer(13) instanceof Kalaha);
    }

    @Test
    public void testLastKalahaConnectsToFirstBowl(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertEquals(bowl,bowl.getToContainer(13).neighbour);
    }

    @Test
    public void testWhetherSecondBowlIsRightlyReferencedByGetToContainerMethod(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertEquals(bowl.neighbour,bowl.getToContainer(1));
    }

    @Test(expected = TurnNotAllowedException.class)
    public void testWhetherDoTurnThrowsException(){
        Player player1 = new Player();
        Bowl bowl = new Bowl(player1);
        player1.changeTurn();
        bowl.doTurn();
    }

    @Test(expected = TurnNotAllowedException.class)
    public void testExceptionUponSelectionOfEmptyBowl(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=0;
        bowl.doTurn();
    }

    @Test
    public void testStoneGetDepletedWhenBowlIsSelectedForTurn(){
        Bowl bowl = new Bowl(new Player());
        bowl.doTurn();
        Assert.assertEquals(0,bowl.numberOfStones);
    }

    @Test
    public void testStonesOfNeighbourGetsIncremented(){
        Bowl bowl = new Bowl(new Player());
        bowl.doTurn();
        Assert.assertEquals(5,bowl.neighbour.numberOfStones);
    }

    @Test
    public void testWhethergetOppositeBowlReturnsBowl(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertTrue(bowl.neighbour.getOppositeBowl(0) instanceof Bowl);
    }

    @Test
    public void testGetStonesFromBowl(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones+=bowl.neighbour.getOppositeBowl(0).getStonesFromBowl();
        Assert.assertEquals(8, bowl.numberOfStones);
    }

    @Test
    public void testmoveStonesToKalahaDepletesBowlStones(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=0;
        bowl.receiveStones(1);
        Assert.assertTrue(bowl.numberOfStones==0);
    }

    @Test
    public void testmoveStonesToKalahagivesStonesToKalaha(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=0;
        bowl.receiveStones(1);
        Assert.assertEquals(5,bowl.getToContainer(6).numberOfStones);
    }

    @Test
    public void testreceiveStonesTakesOneStone(){
        Bowl bowl = new Bowl(new Player());
        bowl.receiveStones(4);
        Assert.assertEquals(5,bowl.numberOfStones);
    }

    @Test
    public void testreceiveStonesPassesOtherStonesToNeighbour(){
        Bowl bowl = new Bowl(new Player());
        bowl.receiveStones(4);
        Assert.assertEquals(5,bowl.neighbour.numberOfStones);
    }

    @Test
    public void testreceiveStonesPassesNoStonesIfLastStoneIsReceived() {
        Bowl bowl = new Bowl(new Player());
        bowl.receiveStones(1);
        Assert.assertEquals(4, bowl.neighbour.numberOfStones);
    }

    @Test
    public void testreceiveStonesDepletesOppositeBowlIfLastStoneIsReceived(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=0;
        bowl.receiveStones(1);
        Assert.assertEquals(0,bowl.getOppositeBowl(-1).numberOfStones);
    }

    @Test
    public void testdoTurnInitiatesTurnIfBowlIsOfActivePlayer(){
        Bowl bowl = new Bowl(new Player());
        bowl.doTurn();
        Assert.assertEquals(0,bowl.numberOfStones);
    }

    @Test
    public void testdoTurnGivesTurnToOtherPlayerIfLastStoneIsNotInOwnKalaha(){
        Bowl bowl = new Bowl(new Player());
        bowl.doTurn();
        Assert.assertFalse(bowl.player.hasTurn());
    }

    @Test
    public void testdoTurnKeepsTurnIfLastStoneIsInOwnKalaha(){
        Bowl bowl = new Bowl(new Player());
        Bowl bowl3 = (Bowl) bowl.getToContainer(2);
        bowl3.doTurn();
        Assert.assertTrue(bowl.player.hasTurn());
    }

    @Test
    public void testAllBowlsToKalahaEmptyTheFirstBowl(){
        Bowl bowl = new Bowl(new Player());
        Kalaha myKalaha = bowl.getKalaha();
        bowl.allBowlsToKalaha(myKalaha);
        Assert.assertEquals(0,bowl.numberOfStones);
    }

    @Test
    public void testAllBowlsToKalahaEmptyTheLastBowl(){
        Bowl bowl = new Bowl(new Player());
        Kalaha myKalaha = bowl.getKalaha();
        bowl.allBowlsToKalaha(myKalaha);
        Bowl lastBowl = (Bowl) bowl.getToContainer(5);
        Assert.assertEquals(0,lastBowl.numberOfStones);
    }

    @Test
    public void testAllBowlsToKalahaFillsKalaha(){
        Bowl bowl = new Bowl(new Player());
        Kalaha myKalaha = bowl.getKalaha();
        bowl.allBowlsToKalaha(myKalaha);
        Assert.assertEquals(24,myKalaha.numberOfStones);
    }

    @Test
    public void testWhenLastStoneOfGameIsPlacedInKalahaTheGameStillEnds(){
        Bowl bowl = new Bowl(new Player());
        bowl.allBowlsToKalaha(bowl.getKalaha());
        Bowl opponentBowl = (Bowl) bowl.getToContainer(7);
        opponentBowl.allBowlsToKalaha(opponentBowl.getKalaha());
        Bowl lastBowlOfCurrentPlayer = (Bowl) bowl.getToContainer(5);
        lastBowlOfCurrentPlayer.numberOfStones=1;
        lastBowlOfCurrentPlayer.doTurn();
        Assert.assertFalse(lastBowlOfCurrentPlayer.player.hasTurn());
    }
}
