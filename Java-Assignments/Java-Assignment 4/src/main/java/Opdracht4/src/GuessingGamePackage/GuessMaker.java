package GuessingGamePackage;

public class GuessMaker {

    private int NumToGuess;
    private boolean stopGame;
    private int NumberOfGuesses = 0;

    public int getNumberOfGuesses(){
        return NumberOfGuesses;
    }

    public void stopGame(){
        stopGame=true;
    }

    public void RandomNumber(){
        NumToGuess = (int) (Math.random()*50);
        System.out.println(NumToGuess);
        stopGame=false;
    }

    public String checkUserGuess(int Guess){
        NumberOfGuesses++;
        if(!stopGame){
            if(Guess==NumToGuess){
                return "Guess is correct!\n";
            }else{
                if(Guess>NumToGuess){
                    return "Lower!\n";
                }else{
                    return "Higher!\n";
                }
            }
        }else{
            return "The game has ended.\n";
        }
    }
}
