package nl.sogyo.mancala;

public class Kalaha extends Container {
    @Override
    protected Kalaha getKalaha() {
        return this;
    }

    @Override
    protected Bowl getOppositeBowl(int count) {
        count++;
        count = -count;
        Bowl bowlToReturn = neighbour.getOppositeBowl(count);
        return bowlToReturn;
    }

    @Override
    protected boolean receiveStones(int additionalStones) {
        if(player.hasTurn()){
            return allowKalahaToTakeStoneAndPassTheRest(additionalStones);
        }else return neighbour.receiveStones(additionalStones);
    }

    @Override
    protected boolean hasOptions() {
        return false;
    }

    @Override
    void allBowlsToKalaha(Kalaha myKalaha){}

    private boolean allowKalahaToTakeStoneAndPassTheRest(int additionalStones){
        numberOfStones++;
        if(additionalStones==1){
            return true;
        }else return neighbour.receiveStones(--additionalStones);
    }

    protected void receiveStonesForKalaha(int stonesReceived){
        numberOfStones+=stonesReceived;
    }

    protected Kalaha(Player player, int count, Bowl firstBowl){
        if(count==7){
            this.player = player;
            this.neighbour = new Bowl(player.getNextPlayer(), ++count, firstBowl);
        }else{
            this.player=player;
            this.neighbour = firstBowl;
        }
    }
}
