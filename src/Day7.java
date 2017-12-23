import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Karl on 2017-12-20.
 */
public class Day7 {


    public Day7(){
        String path = "src/input.txt";
        File file = new File(path);


        HashMap<String, Node> parentlessNodes = new HashMap<>();
        HashMap<String, Node> nodelessChildren = new HashMap<>();



        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {


                Node newNode;
                String nodeName = line.substring(0, line.indexOf(" "));
                int weight = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(")")));


                if (nodelessChildren.containsKey(nodeName)){
                    newNode = nodelessChildren.get(nodeName);
                    newNode.setWeigt(weight);
                    nodelessChildren.remove(nodeName);
                }
                else{
                    newNode = new Node(nodeName, weight);
                    parentlessNodes.put(nodeName, newNode);
                }

                if (line.contains(">")){
                    String childrenSubString = line.substring(line.indexOf(">")+2, line.length());
                    childrenSubString = childrenSubString.replaceAll("[$,]", "");

                    String [] children = childrenSubString.split("\\s+");
                    
                    for (String childName : children){
                        if (parentlessNodes.containsKey(childName)){
                            newNode.addChild(parentlessNodes.get(childName));
                            parentlessNodes.remove(childName);
                        }else {
                        	
                        	Node newChild = new Node(childName);
                        	nodelessChildren.put(childName, newChild);
                        	newNode.addChild(newChild);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        Node root = null;
        
        Iterator it = parentlessNodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("Root node: " + pair.getKey());
            
            root = (Node) pair.getValue();
        }
        
        
        for (Node child : root.getChildren()) {
        	System.out.println(child.getName() + " (" + child.getWeight() + ")");
        }        
        
        getWeights(root);
        
        for (Node child : root.getChildren()) {
        	System.out.println(child.getName() + " (" + child.subTreeWeight + ")");
        }      
        
        System.out.println(root.subTreeWeight);
    }
    
    
    public void getWeights(Node node) {
    	for (Node child : node.getChildren()) {
    		getWeights(child);
    		node.subTreeWeight += child.getSubTreeWeight();
    	}    	
    }
    
    
    
    
    
    







    public static void main(String[] args) throws IOException, IOException {
        new Day7();
    }


    class Node {

        private String name;
        private int weight;
        private ArrayList<Node> children;
        
        private int subTreeWeight;

        protected Node (String name, int weight){
            this.name = name;
            this.weight = weight;
            children = new ArrayList<>();
            subTreeWeight = weight;
        }

        protected Node (String name){
            this.name = name;
            children = new ArrayList<>();
        }

        public String getName(){
            return name;
        }
        
        public int getSubTreeWeight(){
            return subTreeWeight;
        }

        public void setSubTreeWeigt(int weight){
            this.subTreeWeight = weight;
        }
        
        public void addSubTreeWeigt(int weight){
            this.subTreeWeight += weight;
        }

        public int getWeight(){
            return weight;
        }

        public void setWeigt(int weight){
        	this.subTreeWeight = weight;
            this.weight = weight;
        }

        public ArrayList<Node> getChildren(){
            return children;
        }

        public void addChild(Node child){
            this.children.add(child);
        }

        public Node getChild(String name){
            for (Node child : children){
                if (child.getName().equals(name)){
                    return child;
                }
            }
            return null;
        }

    }
}
