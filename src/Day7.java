import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Karl on 2017-12-20.
 * 
 * 
 * Java solution to Advent of Code 17, day 7
 * https://adventofcode.com/2017/day/7
 * 
 * 
 * The problem in part 1 can be solved by creating a normal tree structure, but the tricky part is that nodes are declared as parents or children out of order
 * The problem in part 2 requires traversing, first post-order for calculating sub tree weights, and then in-order to find the unbalanced node
 */
public class Day7 {
	
	public Day7() {
		String path = "src/input.txt"; // Paste input in this file
		File file = new File(path);

		// If a line contains a Node which has not yet been assigned a parent node, it goes in this list
		HashMap<String, Node> parentlessNodes = new HashMap<>();
		
		// If a line specifies child nodes that are not yet declared on their own line with weights, they go in this list
		HashMap<String, Node> nodelessChildren = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {

				Node newNode;
				String nodeName = line.substring(0, line.indexOf(" "));
				int weight = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")));

				
				// If the node on the current line previously has been specified as a child, it adds the weight to it here and clears it from nodelessChildren
				if (nodelessChildren.containsKey(nodeName)) {
					newNode = nodelessChildren.get(nodeName);
					newNode.setWeigt(weight);
					nodelessChildren.remove(nodeName);
				// If not, it's a new node, but it is so far parentless 
				} else {
					newNode = new Node(nodeName, weight);
					parentlessNodes.put(nodeName, newNode);
				}

				// The current Node has children specified if the line contains ">"
				if (line.contains(">")) {
					String childrenSubString = line.substring(line.indexOf(">") + 2, line.length());
					childrenSubString = childrenSubString.replaceAll("[$,]", "");
					String[] children = childrenSubString.split("\\s+");
					
					
					for (String childName : children) {
						// If current node is parent of previously declared Node, the child node is cleared form parentless nodes
						if (parentlessNodes.containsKey(childName)) {
							newNode.addChild(parentlessNodes.get(childName));
							parentlessNodes.remove(childName);
						
						// If the child is not previously declared, it is a nodeless child 
						} else {
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

		// Part 1 output
		
		// Pull out the only remaining map entry, which is the root node 
		Iterator it = parentlessNodes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("Root node: (Part 1 Output) " + pair.getKey());
			root = (Node) pair.getValue();
		}

		
		// Part 2 output
		
		// Calculate the sub tree weights for each node
		getWeights(root);
		
		// Check which node is unbalanced and output what weight it should have
		findUnbalancedNode(root);
	}

	
	/**
	 * Post-order traversal, first recursively visiting the leafs of the left-most sub tree, and then filling 
	 * the sub tree weights for the parents one depth level at the time
	 * @param node
	 */
	public void getWeights(Node node) {
		for (Node child : node.getChildren()) {
			getWeights(child);
			node.subTreeWeight += child.getSubTreeWeight();
		}
	}
	
	
	/**
	 * Finds the unbalanced node and outputs its required weight to balance the rest of the tree
	 * @param root
	 */
	
	void findUnbalancedNode(Node root) {
		
		Node lastChecked = root;
		Node parentOfLastChecked = root;
		
		// Checks balance for each depth level, if the same ndoe is returned twice it means that the unbalanced node is found
		// Parent is saved in order to check the current nodes siblings 
		for (Node nextNode = checkBalance(root); !lastChecked.equals(nextNode); nextNode = checkBalance(lastChecked)) {
			parentOfLastChecked = lastChecked;
			lastChecked = nextNode;
		}
				
		// Check siblings to find what the weight should be and calculate the difference 
		for (Node sibling : parentOfLastChecked.children) {
			if (sibling != lastChecked) {
				int goalWeight = sibling.subTreeWeight;
				int weightChange = goalWeight - lastChecked.subTreeWeight;
				int requiredWeight = lastChecked.getWeight() + weightChange;
				System.out.println("Required Weight: (Part 2 output) " + requiredWeight);
				
				break;
			}
		}
	
	
	}
	
	
	/**
	 * Finds the one unbalanced node 
	 * @param node
	 * @return
	 */

	public Node checkBalance(Node node) {

		ArrayList<Integer> foundWeights = new ArrayList<>();
		ArrayList<Node> candidates = new ArrayList<>();

		for (Node child : node.getChildren()) {

			// If any other child node at the same depth has the same sub tree weight, it is not part of the
			// unbalanced sub tree, and removed from the list of candidates
			if (foundWeights.contains(child.subTreeWeight)) {
				Iterator<Node> i = candidates.iterator();
				while (i.hasNext()) {
					Node n = i.next();

					if (n.subTreeWeight == child.subTreeWeight) {
						i.remove();
					}
				}

			// If no other child has had the same weight so far, the current child is a candidate for being the unbalanced one.
			// Its weight is also added to foundWeights in order to check future children at same depth for the same sub tree weight, which would 
			// clear both of suspicion
			} else {
				foundWeights.add(child.subTreeWeight);
				candidates.add(child);
			}
		}

		if (candidates.size() == 1) {
			return candidates.get(0);
		}else if (candidates.size() > 1) {
			System.err.println("Error in checkBalance");
		}
		
		// Current node returns if all children of the node are balanced, meaning that the current parent node is unbalanced
		
		return node;
	}

	public static void main(String[] args) throws IOException, IOException {
		new Day7();
	}
	
	
	
	/**
	 * Node class holds name, weight, a list of its children and a subtree weight which eventually 
	 * is set by adding the weights of itself and all its entire subtree
	 * 
	 * Note: for the sub tree weight to work, the variable subTreeWeight has to be the same as its own weight before the subtree weights are
	 * calculated, so its modified both in constructor and in setWeight() method. 
	 * @author karl
	 *
	 */

	class Node {

		private String name;
		private int weight;
		private ArrayList<Node> children;

		private int subTreeWeight;

		protected Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
			children = new ArrayList<>();
			subTreeWeight = weight;
		}

		protected Node(String name) {
			this.name = name;
			children = new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public int getSubTreeWeight() {
			return subTreeWeight;
		}

		public void setSubTreeWeigt(int weight) {
			this.subTreeWeight = weight;
		}

		public void addSubTreeWeigt(int weight) {
			this.subTreeWeight += weight;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeigt(int weight) {
			this.subTreeWeight = weight;
			this.weight = weight;
		}

		public ArrayList<Node> getChildren() {
			return children;
		}

		public void addChild(Node child) {
			this.children.add(child);
		}

		public Node getChild(String name) {
			for (Node child : children) {
				if (child.getName().equals(name)) {
					return child;
				}
			}
			return null;
		}

	}
}
