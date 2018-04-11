package ItemFusionPackage;

import java.util.*;

public class Fusion {

    private Item ResultItem;
    private ArrayList<ArrayList<Item>> inputItemSets = new ArrayList<>();

    public Fusion(Item input1, Item input2, Item result){
        ArrayList<Item> inputItemSet = new ArrayList<>(Arrays.asList(input1,input2));
        setResultItem(result);
        addinputItemSets(inputItemSet);
    }

    public void setResultItem(Item resultItem) {
        ResultItem = resultItem;
    }
    public void addinputItemSets(ArrayList<Item> inputItemSet){
        inputItemSets.add(inputItemSet);
    }
    public Item getResultItem(){
        return ResultItem;
    }
    public ArrayList<ArrayList<Item>> getInputItemSets(){
        return inputItemSets;
    }

    /*private String ResultItem;
    private ArrayList<String> inputItems;

    public Fusion(String input1, String input2, String result){
        setInputItems(input1,input2);
        setResultItem(result);
    }

    public void setInputItems(String inputItem1, String inputItem2) {
        inputItems.add(inputItem1);
        inputItems.add(inputItem2);
    }
    public void setResultItem(String resultItem) {
        ResultItem = resultItem;
    }
    public ArrayList<String> getInputItems() {
        return inputItems;
    }
    public String getResultItem() {
        return ResultItem;
    }*/
}
