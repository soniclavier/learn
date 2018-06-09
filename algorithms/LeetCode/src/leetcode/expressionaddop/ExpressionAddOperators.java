package leetcode.expressionaddop;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ExpressionAddOperators {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.addOperators(args[0], Integer.parseInt(args[1])));
	}
}

class Solution {
	public List<String> addOperators(String num, int target) {
		//create the graph
		char[] numChars = num.toCharArray();
		char[] ops = {'+','-','*'};

		Node root = new Node(num.charAt(0)+"");
		List<String> result = new ArrayList<String>();
		recursiveCompare(root, Arrays.copyOfRange(numChars, 1, numChars.length), ops, target, result);
		return result;
	}

	public void recursiveCompare(Node root, char[] remaining, char[] ops, int target, List<String> result) {
		if (remaining.length == 0) {
			if (root.evaluate() == target) {
				result.add(root.val);
			}
			return;
		}
		char next = remaining[0];
		for(char ch : ops) {
			Node n = new Node(root.val+ch+next);
			recursiveCompare(n, Arrays.copyOfRange(remaining, 1, remaining.length), ops, target, result);
		}

		Node n = new Node(root.val+""+next);
		recursiveCompare(n, Arrays.copyOfRange(remaining, 1, remaining.length), ops, target, result);

	}
}

class Node {
	String val;

	public Node(String val) {
		this.val = val;
	}

	public int evaluate() {
		int result = evaluate(val);
		System.out.println("evaluating "+val+" = "+result);
		return result;
	}
	private int evaluate(String val) {

		int opIndex = -1;
		char op = '/';
		for (int i = 0;  i < val.length(); i++) {
			char ch = val.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				op = ch;
				opIndex = i;
				break;
			}
		}
		if (opIndex == -1) {
            return Integer.parseInt(val);
        }
        int result;
        switch(op) {
            case '+': result = Integer.parseInt(val.substring(0, opIndex)) + evaluate(val.substring(opIndex + 1));
                    break;
            case '-': result = Integer.parseInt(val.substring(0, opIndex)) - evaluate(val.substring(opIndex + 1));
                    break;
            case '*' : {
            		int nextOpIndex = -1;
					for (int i = opIndex + 1; i < val.length(); i++) {
						char ch = val.charAt(i);
						if (ch == '+' || ch == '-' || ch == '*') {
							nextOpIndex = i;
							break;
						}
					}
					if (nextOpIndex == -1) {
						result = Integer.parseInt(val.substring(0, opIndex)) * Integer.parseInt(val.substring(opIndex + 1));
					} else {
                    	int r = Integer.parseInt(val.substring(0, opIndex)) * Integer.parseInt(val.substring(opIndex + 1, nextOpIndex));
                    	if (val.length() > 3) result = evaluate(r+""+val.substring(nextOpIndex));
                    	else result = r;
                	}
                	break;
                    }
            default: result = 0;
        }
        return result;
	}

	@Override
	public boolean equals(Object other) {
		Node otherNode = (Node) other;
		return this.val.equals(otherNode.val);
	}

	@Override
	public int hashCode() {
		return this.val.hashCode();
	}
}

