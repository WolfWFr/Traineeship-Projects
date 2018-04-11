package nl.sogyo.mancala;

public class Bowl extends Container {

    @Override
    protected Kalaha getKalaha() {
        return neighbour.getKalaha();
    }

    @Override
    protected Bowl getOppositeBowl(int count) {
        Bowl bowlToReturn;
        if(++count==0){
            bowlToReturn=this;
        }else{
            bowlToReturn = neighbour.getOppositeBowl(count);
        }
        return bowlToReturn;
    }

    @Override
    protected boolean receiveStones(int additionalStones) {
        numberOfStones++;
        if(--additionalStones>0) return neighbour.receiveStones(additionalStones);
        else {
            if(numberOfStones==1){
                Bowl oppositeBowl = neighbour.getOppositeBowl(0);
                int stonesGotten = oppositeBowl.getStonesFromBowl();
                numberOfStones+=stonesGotten;
                Kalaha myKalaha = getKalaha();
                moveStonesToKalaha(myKalaha);
            }
            return false;
        }
    }

    @Override
    protected boolean hasOptions(){
        if(numberOfStones>0) return true;
        else return neighbour.hasOptions();
    }

    @Override
    protected void allBowlsToKalaha(Kalaha myKalaha){
        if(numberOfStones>0) moveStonesToKalaha(myKalaha);
        neighbour.allBowlsToKalaha(myKalaha);
    }

    protected int getStonesFromBowl(){
        int stonesToGive=numberOfStones;
        numberOfStones=0;
        return stonesToGive;
    }

    private void moveStonesToKalaha(Kalaha kalaha){
        int stonesToMoveToKalaha = numberOfStones;
        numberOfStones=0;
        kalaha.receiveStonesForKalaha(stonesToMoveToKalaha);
    }

    public void doTurn() {
        boolean keepsTurn;
        if (player.hasTurn() && numberOfStones > 0) {
            keepsTurn = moveStonesToNext(numberOfStones);
            numberOfStones = 0;
        } else throw new TurnNotAllowedException();

        if (!keepsTurn && checkIfOpponentHasOptions()) {
            player.changeTurn();
        } else if (!keepsTurn || (keepsTurn && !checkIfCurrentPlayerHasOptions())) endGame();
    }

    private boolean checkIfOpponentHasOptions(){
        Kalaha myKalaha = getKalaha();
        return myKalaha.neighbour.hasOptions();
    }

    private boolean checkIfCurrentPlayerHasOptions(){
        Kalaha myKalaha = getKalaha();
        Kalaha opponentKalaha = myKalaha.neighbour.getKalaha();
        return opponentKalaha.neighbour.hasOptions();
    }

    private void endGame(){
        Kalaha myKalaha = getKalaha();
        Bowl myFirstBowl = (Bowl) myKalaha.getToContainer(8);
        myFirstBowl.allBowlsToKalaha(myKalaha);
        player.stopTurn();
    }

    public Bowl(Player player){
        this.player=player;
        this.numberOfStones=4;
        this.neighbour = new Bowl(player, 2, this);
    }

    protected Bowl(Player player, int count, Bowl firstBowl){
        if(count < 6 || count < 13 && count > 6){
            this.player = player;
            this.numberOfStones=4;
            this.neighbour = new Bowl(player, ++count, firstBowl);
        }else if(count == 6 || count == 13){
            this.player = player;
            this.numberOfStones=4;
            this.neighbour = new Kalaha(player, ++count, firstBowl);
        }
    }
}
