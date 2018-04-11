package DecisionTreePackage;

public class Node {

    String ID;
    String Value;

    public Node(String ID, String value){
        setID(ID);
        setValue(value);
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getValue() {
        return Value;
    }

    public String getID() {
        return ID;
    }
}
