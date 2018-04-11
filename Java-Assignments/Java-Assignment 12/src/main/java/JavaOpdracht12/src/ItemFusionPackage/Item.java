package ItemFusionPackage;

import java.util.*;

public class Item {
    private String Name;
    private ArrayList<Integer> Price = new ArrayList<Integer>();
    boolean Purchasable;
    private ArrayList<Integer> StoreNumber = new ArrayList<>();

    public Item(String name, boolean purchasable, int price, int storeNumber){
        setName(name);
        setPurchasable(purchasable);
        addPrice(price);
        addStoreNumber(storeNumber);
    }
    public Item(String name, boolean purchasable){
        setName(name);
        setPurchasable(purchasable);
    }

    public void setName(String name) {
        Name = name;
    }
    public void addPrice(int price) {
        Price.add(price);
    }
    public void setPurchasable(boolean purchasable) {
        Purchasable = purchasable;
    }
    public void addStoreNumber(int storeNumber) {
        StoreNumber.add(storeNumber);
    }
    public ArrayList<Integer> getPrice() {
        return Price;
    }
    public String getName() {
        return Name;
    }
    public boolean getPurchasable(){return Purchasable;}
    public ArrayList<Integer> getStoreNumber() {
        return StoreNumber;
    }
}
