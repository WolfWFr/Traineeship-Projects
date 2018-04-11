package nl.sogyo.mancala;

public abstract class Container {
    protected Player player;
    protected Container neighbour;
    protected int numberOfStones;

    protected boolean moveStonesToNext(int stonesToMove){
        return neighbour.receiveStones(stonesToMove);
    }

    protected Container getToContainer(int containerNumber){
        Container neighbourToReturn;
        if(containerNumber==1){
            neighbourToReturn = neighbour;
        }else{
            neighbourToReturn = neighbour.getToContainer(containerNumber, 1);
        }
        return neighbourToReturn;
    }

    private Container getToContainer(int containerNumber, int currentContainer){
        Container neighbourToReturn;
        if(containerNumber==(currentContainer+1)){
            neighbourToReturn = neighbour;
        }else{
            neighbourToReturn = neighbour.getToContainer(containerNumber,++currentContainer);
        }
        return neighbourToReturn;
    }

    abstract Kalaha getKalaha();
    abstract Bowl getOppositeBowl(int count);
    abstract boolean receiveStones(int additionalStones);
    abstract boolean hasOptions();
    abstract void allBowlsToKalaha(Kalaha myKalaha);
}
