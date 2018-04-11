package FibonacciPackage;

public class Fibonacci {

    public long[] build(int length){
        long[] FibArray = new long[length];
        int i;
        FibArray[0]=0;
        if(length>1){
            FibArray[1]=1;
        }
        for(i=2;i<length;i++){
            FibArray[i] = FibArray[i-1]+FibArray[i-2];
        }
        return FibArray;
    }
    public long check(long[] FibArray, int length){
        return FibArray[length-1];
    }

    public long checkEvenSum(long[] FibArray){
        long evenSum=0;
        int j;

        for(j=0;j<FibArray.length;j++){
            if(FibArray[j]%2==0){
                evenSum += FibArray[j];
            }
        }

        return evenSum;
    }

}
