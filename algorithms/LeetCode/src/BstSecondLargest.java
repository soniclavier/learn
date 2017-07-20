public class BstSecondLargest {
	public static void main(String[] args) {
		/*
		Node n1 = new Node(10);
		Node n2 = new Node(15);
		Node n3 = new Node(20);
		n1.right = n2;
		n2.right = n3;
		System.out.println(findSecondLargest(n1).value);
		*/
		
		/*
		Node n1 = new Node(10);
		Node n2 = new Node(15);
		Node n3 = new Node(20);
		Node n4 = new Node(16);
		Node n5 = new Node(17);
		n1.right = n2;
		n2.right = n3;
		n3.left = n4;
		n4.right = n5;
		System.out.println(findSecondLargest(n1).value);
		*/
		
		/*
		Node n1 = new Node(10);
		Node sLargest = findSecondLargest(n1);
		System.out.println(sLargest);
		*/

		
		Node n1 = new Node(10);
		Node n2 = new Node(15);
		Node n3 = new Node(20);
		Node n4 = new Node(16);
		Node n5 = new Node(17);
		n3.left = n5;
		n5.left = n4;
		n4.left = n2;
		n2.left = n1;

		System.out.println(findSecondLargest(n5).value);
		



	}

	public static Node findSecondLargest(Node root) {
		if (root == null) return null;
		NodeTuple largest = findLargest(root);
		if (largest.t1.left == null) {
			//parent could be null incase there is only one node in the tree
			//in that case, there is no second largest Node.
			return largest.t2; 
		} else {
			//we don't care about parent node here.
			return findLargest(largest.t1.left).t1;
		}
	}

	public static NodeTuple findLargest(Node node) {
		Node parent = null;
		while(node.right != null) {
			System.out.println("parent is "+parent);
			parent = node;
			node = node.right;
		}
		System.out.println("largest Node is "+node.toString()+", parent is "+parent);
		return new NodeTuple(node, parent);
	}

	
}

class NodeTuple {
	Node t1;
	Node t2;
	NodeTuple(Node t1, Node t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
}

class Node {
	Node left;
	Node right;
	int value;
	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node("+value+")";
	}
}