package leetcode.aliendictionary;

import java.util.*;

public class AlienDictionary {

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
		//String[] words = {"wr", "wf"}; //incomplete info
		//String[] words = {}; //empty input
		//String[] words = {"wwwwwwww"};
		System.out.println(sol.order(words));
	}
}

class Solution {
	
	public String order(String[] alienWords) {
		Node root = null;
		Map<Character, Node> allNodes = new HashMap<>();

		//construct the graph
		Node prev = null;
		for(String word: alienWords) {
			for (char letter: word.toCharArray()) {
			Node curr = allNodes.get(letter);
            if (curr == null) { 
               curr = new Node(letter);
               allNodes.put(letter, curr); 
            }
            
				if (root == null) {
					//first letter
					prev = curr;
					root = curr;
				} else {
					//Add the curr as an edge from prev, if one does not already exist.
					if (!prev.hasNeighbour(letter) && prev.w != curr.w) {
						prev.neighbors.put(letter, curr);
					}
					prev = curr;
				}
			}
			prev = allNodes.get(word.charAt(0));
		}

		//assign scores to each node
		Queue<Node> queue = new ArrayDeque<Node>();
		if (root == null) return "";
		queue.add(root);
		while (queue.peek() != null) {
			Node curr = queue.poll();
			int core = curr.score;
			for(Character ch: curr.neighbors.keySet()) {
				Node next = curr.neighbors.get(ch);
				next.score += 1;
				queue.add(next);
			}
		}

		for(Node n: allNodes.values()) {
			System.out.println(n.toString());
		}

		//find the path
		Node curr = root;
		StringBuffer result = new StringBuffer();
		result.append(curr.w);
		while(curr != null && curr.neighbors != null) {
			curr = curr.getShortestNeighbor();
			if (curr != null) {
				result.append(curr.w);
			}
		}
		return result.toString();
	}

}


class Node {
	char w;
	Map<Character, Node> neighbors;
	int score = 0;

	public Node(char w) {
		this.w = w;
		neighbors = new HashMap<>();
	}

	public boolean hasNeighbour(Character ch) {
		return neighbors.keySet().contains(ch);
	}

	public Node getShortestNeighbor() {
		Node shortest = null;
		for(Node curr: neighbors.values()) {
			if (shortest == null || shortest.score > curr.score) {
				shortest = curr;
			}		
		}
		return shortest;
	}

	@Override
	public String toString() {
		String ns = "";
		for(Node n: neighbors.values()) {
			ns += n.w+", ";
		}
		return w + " : "+ns;
	}

	@Override
	public boolean equals(Object other) {
		return this.w == ((Node)other).w;
	}

	@Override 
	public int hashCode() {
		return w;
	}

}