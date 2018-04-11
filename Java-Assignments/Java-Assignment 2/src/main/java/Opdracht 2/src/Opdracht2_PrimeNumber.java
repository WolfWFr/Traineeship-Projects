import java.util.ArrayList;

public class Opdracht2_PrimeNumber {

    public static void main(String[] args){
        int primeBuilderLength = 10001;
        Opdracht2_PrimeNumber primeTester = new Opdracht2_PrimeNumber();

        ArrayList<Integer> PrimeList = primeTester.BuildPrime(primeBuilderLength);

        int PrimeNumber = primeTester.PrimeNumberAtIndex(PrimeList, primeBuilderLength);
        System.out.println(PrimeNumber);
    }

    private ArrayList<Integer> BuildPrime(int length){
        ArrayList<Integer> PrimeList = new ArrayList<>();
        int i;//=2;
        int j;
        int k;
        int index;
        boolean isPrime = true;
            for(i=2;i==i;i++){
                int lstDgt = i%10;
                if(i>11){
                    if(lstDgt==1||lstDgt==3||lstDgt==7||lstDgt==9){
                    }else{
                        continue;
                    }
                }
                k = (int) Math.rint(Math.sqrt((double) i));
                index = PrimeList.indexOf(k);
                for(j=k;j>=0;j--){
                    index = PrimeList.indexOf(j);
                    if(index>-1){
                        break;
                    }
                }
                for (j=0;j<=index;j++){
                    if(i%PrimeList.get(j)==0){
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime){
                    PrimeList.add(i);
                }
                isPrime = true;
                if(PrimeList.size()==length){
                    break;
                }
            }

        return PrimeList;
    }

    private int PrimeNumberAtIndex(ArrayList<Integer> PrimeList, int index){
        return PrimeList.get(index-1);
    }

}
