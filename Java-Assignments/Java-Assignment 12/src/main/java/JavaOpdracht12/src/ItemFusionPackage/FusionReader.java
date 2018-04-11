package ItemFusionPackage;

import java.io.*;
import java.util.*;

public class FusionReader {

    String[] StoreOrFusionOptions = {"beginning store","second store","third store","4th store","5th store"};
    String StoreOrFusionTracker;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Fusion> fusions = new ArrayList<>();

    public void readFile(){
        try{
            File file = new File("C:\\Repo\\java-opdrachten-code\\src\\main\\resources\\item-fusion-data.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String ReadLine;

            while((ReadLine=reader.readLine())!=null){
                if(ReadLine.contains(":")){
                    setStoreOrFusion(ReadLine);//set the store variable
                }
                if(ReadLine.contains("~")){
                    String[] split = ReadLine.split(" ~ ");
                    split = cleanUp(split);
                    scanAndSetItems(split);
                }
                if(ReadLine.contains("+")){
                    String[] preSplit1 = ReadLine.split(" \\+ ");
                    String[] preSplit2 = preSplit1[1].split(" =");
                    String[] split = {preSplit1[0],preSplit2[0],preSplit2[1]};
                    split = cleanUp(split);
                    scanAndSetItems(split);
                    setFusions(split);
                } } }catch(Exception ex){ex.printStackTrace();}
    }
    public void setStoreOrFusion(String input){
        for(String option:StoreOrFusionOptions){
            if(input.toLowerCase().contains(option)){
                StoreOrFusionTracker=option;
                break;
            }
        }
    }
    public void scanAndSetItems(String[] input){
        int price=0;
        int storeNumber=Arrays.asList(StoreOrFusionOptions).indexOf(StoreOrFusionTracker)+1;
        if(input.length==2){
            if(!input[0].toLowerCase().contains("name")&&!input[1].toLowerCase().contains("cost")){
                //scan if item is already in list
                //items are purchasable by definition at this point
                try{price = Integer.parseInt(input[1]);}catch(Exception ex){}
                if(items.stream().noneMatch(o->o.getName().equals(input[0]))){
                    items.add(new Item(input[0],true,price,storeNumber));
                }else{
                    //add store and price
                    Item existingItem = getItem(input[0]);
                    existingItem.addPrice(price);
                    existingItem.addStoreNumber(storeNumber);
                }
            }
        }else{
            //code for adding items from fusion info
            for(String potentialNewItem:input){
                int Index = Arrays.asList(input).indexOf(potentialNewItem);
                if(items.stream().noneMatch(o->o.getName().equals(input[Index]))){
                    items.add(new Item(input[Index],false));
                }
            }
        }
    }
    public void setFusions(String[] input){
        Item inputItem1 = getItem(input[0]);
        Item inputItem2 = getItem(input[1]);
        Item resultItem = getItem(input[2]);
        if(fusions.stream().noneMatch(o->o.getResultItem()==resultItem)){
            fusions.add(new Fusion(inputItem1, inputItem2, resultItem));
        }else{
            Fusion existingFusion = getFusion(resultItem);
            ArrayList<Item> newItemSet = new ArrayList<>(Arrays.asList(inputItem1,inputItem2));
            existingFusion.addinputItemSets(newItemSet);
        }
    }
    public Item getItem(String name){
        Item output=null;
        for(Item item:items){
            int Index = items.indexOf(item);
            if(item.getName().equals(name)){
                output = items.get(Index);
                break;
            }
        }
        return output;
    }
    public Fusion getFusion(Item resultItem){
        Fusion output=null;
        for(Fusion fusion:fusions){
            int Index = fusions.indexOf(fusion);
            if(fusion.getResultItem().equals(resultItem)){
                output = fusion;
            }
        }
        return output;
    }
    public String[] cleanUp(String[] input){
        String[] output=input;

        for(String token:input){
            int Index = Arrays.asList(input).indexOf(token);
            if(token.contains("*")){
                String[] split = token.split("\\*");
                output[Index]=split[1];
            }
            if(token.startsWith(" ")){
                String corrected = token.substring(1,token.length());
                output[Index]=corrected;
            }
        }
        return output;
    }
    public void test(String input){
        Fusion fusion = getFusion(getItem(input));
        System.out.println(fusion.getResultItem().getName()+", required:");
        for(ArrayList<Item> set:fusion.getInputItemSets()){
            System.out.println(set.get(0).getName()+" and "+set.get(1).getName());
            if(fusion.getInputItemSets().indexOf(set)!=fusion.getInputItemSets().size()-1){
                System.out.println("or:");
            }
        }
        System.out.println("");
    }

    public int checkIfCheaper(Item resultItem,int itemSetIndex){
        int cheapestPrice1=Integer.MAX_VALUE;
        int cheapestPrice2=Integer.MAX_VALUE;
        int cheapestPriceResult;

        if(fusions.stream().anyMatch(o->o.getResultItem().equals(resultItem))){
            Fusion fusionOfInterest = getFusion(resultItem);
            Item requiredItem1 = fusionOfInterest.getInputItemSets().get(itemSetIndex).get(0);
            Item requiredItem2 = fusionOfInterest.getInputItemSets().get(itemSetIndex).get(1);

            cheapestPrice1 = requiredItem1.getPrice().get(0);
            cheapestPrice2 = requiredItem2.getPrice().get(1);

            if(fusions.stream().anyMatch(o->o.getResultItem().equals(requiredItem1))){
                Fusion fusionOfRequiredItem1 = getFusion(requiredItem1);
                if(fusionOfRequiredItem1.getInputItemSets().size()>1){
                    for(int i=0;i<fusionOfRequiredItem1.getInputItemSets().size();i++){
                        int potentiallyCheaperPrice1 = checkIfCheaper(requiredItem1,i);
                        if(cheapestPrice1>potentiallyCheaperPrice1){
                            cheapestPrice1=potentiallyCheaperPrice1;
                        }
                    }
                }else{
                    cheapestPrice1 = checkIfCheaper(requiredItem1,0);
                }
            }

            if(fusions.stream().anyMatch(o->o.getResultItem().equals(requiredItem2))){
                Fusion fusionOfRequiredItem2 = getFusion(requiredItem2);
                if(fusionOfRequiredItem2.getInputItemSets().size()>1){
                    for(int i=0;i<fusionOfRequiredItem2.getInputItemSets().size();i++){
                        int potentiallyCheaperPrice2 = checkIfCheaper(requiredItem2,i);
                        if(cheapestPrice2>potentiallyCheaperPrice2){
                            cheapestPrice2=potentiallyCheaperPrice2;
                        }
                    }
                }else{
                    cheapestPrice2 = checkIfCheaper(requiredItem2,0);
                }
            }

        }

        int initialPriceOfResultItem = resultItem.getPrice().get(0);
        if((cheapestPrice1+cheapestPrice2)<initialPriceOfResultItem){
            cheapestPriceResult=(cheapestPrice1+cheapestPrice2);
        }else{
            cheapestPriceResult=initialPriceOfResultItem;
        }

        return cheapestPriceResult;
    }

    public int checkIfCheaperInit(Item resultItem) {
        int cheapestPriceResult;
        int initialPriceOfResultItem=resultItem.getPrice().get(0);
        int potentiallyCheaperPrice=Integer.MAX_VALUE;
        cheapestPriceResult=initialPriceOfResultItem;

        if(fusions.stream().anyMatch(o -> o.getResultItem().equals(resultItem))) {
            Fusion fusionOfInterest = getFusion(resultItem);
            if(fusionOfInterest.getInputItemSets().size()>1){
                for(int i=0;i<fusionOfInterest.getInputItemSets().size();i++){
                    potentiallyCheaperPrice = checkIfCheaper(resultItem,i);
                }
            }else{
                potentiallyCheaperPrice = checkIfCheaper(resultItem,0);
            }
            if(potentiallyCheaperPrice<initialPriceOfResultItem){
                cheapestPriceResult=potentiallyCheaperPrice;
            }
        }
        return cheapestPriceResult;
    }

    public void go(){
        Item toPass = getItem("Cloth");
        int cheapestPrice = checkIfCheaperInit(toPass);
        System.out.println(cheapestPrice);
    }

    public static void main(String[] args){
        FusionReader read = new FusionReader();
        read.readFile();
        read.test("Cloth");
        read.go();
    }
}
