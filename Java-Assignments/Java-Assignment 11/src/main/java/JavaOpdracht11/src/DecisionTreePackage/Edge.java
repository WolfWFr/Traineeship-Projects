package DecisionTreePackage;

public class Edge {

    private String StartNodeID;
    private String DestNodeID;
    private String Value;

    public Edge(String startID, String destNodeID, String value){
        setStartNodeID(startID);
        setDestNodeID(destNodeID);
        setValue(value);
    }

    public void setStartNodeID(String startNodeID) {
        StartNodeID = startNodeID;
    }

    public void setDestNodeID(String destNodeID) {
        DestNodeID = destNodeID;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getStartNodeID() {
        return StartNodeID;
    }

    public String getDestNodeID() {
        return DestNodeID;
    }

    public String getValue() {
        return Value;
    }
}
