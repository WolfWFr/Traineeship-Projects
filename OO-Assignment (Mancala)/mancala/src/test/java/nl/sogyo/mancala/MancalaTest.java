package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void testApp()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void testMancalaInstance(){
        Mancala mancala = new Mancala();
        Assert.assertTrue(mancala instanceof Mancala);
    }

//    @Test
//    public void testBowlCreationMethodCreatesBowls(){
//        Mancala mancala = new Mancala();
//        Assert.assertTrue(mancala.createBowls)
//    }

}
