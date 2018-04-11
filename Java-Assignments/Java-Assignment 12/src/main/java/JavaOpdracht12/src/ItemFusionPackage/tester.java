package ItemFusionPackage;

public class tester {

    public void test(){
        String test = "blabla ";
        String newtest = test.substring(0,test.length()-1);
        System.out.print(newtest);
        System.out.print("and this");
    }

    public static void main(String[] args){
        tester testy = new tester();
        testy.test();
    }

}
