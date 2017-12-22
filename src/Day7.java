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



        // Hanterar inte nodes som forst ar ett nodelesschild och sen deklareras, fixa


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.


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
                        	nodelessChildren.put(childName, new Node(childName));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ParentlessNodes left: " + parentlessNodes.size());

        Iterator it = parentlessNodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }


    }







    public static void main(String[] args) throws IOException, IOException {
        new Day7();
    }


    class Node {

        private String name;
        private int weight;
        private ArrayList<Node> children;

        protected Node (String name, int weight){
            this.name = name;
            this.weight = weight;
            children = new ArrayList<>();
        }

        protected Node (String name){
            this.name = name;
            children = new ArrayList<>();
        }

        public String getName(){
            return name;
        }

        public int getWeight(){
            return weight;
        }

        public void setWeigt(int weight){
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
