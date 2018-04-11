package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {

    @Test
    public void testgetKalaha(){
        Bowl bowl = new Bowl(new Player());
        Assert.assertTrue(bowl.getKalaha() instanceof Kalaha);
    }

    @Test
    public void testreceiveStonesForKalaha(){
        Kalaha kalaha = new Kalaha(new Player(),1,new Bowl(new Player()));
        kalaha.receiveStonesForKalaha(5);
        Assert.assertTrue(kalaha.numberOfStones==5);
    }

    @Test
    public void testreceiveStonesForKalaha2(){
        Kalaha kalaha = new Kalaha(new Player(),1,new Bowl(new Player()));
        kalaha.receiveStonesForKalaha(5);
        kalaha.receiveStonesForKalaha(5);
        Assert.assertTrue(kalaha.numberOfStones==10);
    }

    @Test
    public void testWhetherOpponentKalahaIsSkippedWhenStonesAreMoved(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=15;
        bowl.doTurn();
        Kalaha kalaha = (Kalaha) bowl.getToContainer(13);
        Assert.assertTrue(kalaha.numberOfStones==0);
    }

    @Test
    public void testWhetherOwnKalahaReceivesAStoneWhenStonesAreMoved(){
        Bowl bowl = new Bowl(new Player());
        bowl.numberOfStones=15;
        bowl.doTurn();
        Kalaha kalaha = (Kalaha) bowl.getToContainer(6);
        Assert.assertFalse(kalaha.numberOfStones==0);
    }
}
