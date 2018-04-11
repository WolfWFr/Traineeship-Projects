package DecisionTreePackage;

import java.io.*;
import java.util.*;

public class TreeReader {

    ArrayList<Edge> EdgeList = new ArrayList<>();
    ArrayList<Node> NodeList = new ArrayList<>();
    String lastNodeID=null;

    public void goRead(){
        String readline;

        try {
            File file = new File("C:\\Repo\\java-opdrachten-code\\src\\main\\resources\\decision-tree-data.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            while((readline=reader.readLine())!=null){
                String[] lineSplit = readline.split(", ");
                if(lineSplit.length==2){
                    NodeList.add(new Node(lineSplit[0],lineSplit[1]));
                }else{
                    EdgeList.add(new Edge(lineSplit[0],lineSplit[1],lineSplit[2]));
                }
            }

        }catch(Exception ex){
            System.out.println("Exception!");
        }
    }

    public void StartTree(){
        boolean done = false;
        Scanner scanner = new Scanner(System.in);

        Node firstNode=findNode("N1");
        lastNodeID=firstNode.getID();

        System.out.println(firstNode.getValue());

        while(!done){
            String UserInput = scanner.next().toLowerCase();
            if(UserInput.equals("ja")||UserInput.equals("nee")){
                Edge currentEdge = findEdge(UserInput);
                Node currentNode = findNode(currentEdge.getDestNodeID());
                lastNodeID=currentNode.getID();
                System.out.println(currentNode.getValue());
                if(findEdge(UserInput)==null){
                    done=true;
                }
            }else{
                System.out.println("Geen valide antwoord. Antwoord met \"ja\" of \"nee\".");
            }
        }
    }

    public Edge findEdge(String UserInput){
        int i;
        Edge currentEdge=null;
        for(i=0;i<EdgeList.size();i++){
            currentEdge = EdgeList.get(i);
            if(currentEdge.getStartNodeID().equals(lastNodeID)&&currentEdge.getValue().toLowerCase().equals(UserInput)){
                break;
            }else{
                currentEdge=null;
            }
        }
        return currentEdge;
    }

    public Node findNode(String NodeID){
        Node currentNode=null;
        int i;
        for(i=0;i<NodeList.size();i++){
            currentNode=NodeList.get(i);
            if(currentNode.getID().equals(NodeID)){
                break;
            }
        }
        return currentNode;
    }

    public void test(){
        String lastnodeID="N1";
        findEdge("ja");
    }

    public static void main(String[] args){
        TreeReader treeReader = new TreeReader();
        treeReader.goRead();
        treeReader.StartTree();
        //treeReader.test();
    }

}
