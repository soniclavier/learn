import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class BearChairs {

	static int[] result;
	static int k;
	public int[] findPositions(int[] atLeast, int d) {
		result = new int[atLeast.length];
		k = 0;
		Node root = new Node(1,Integer.MAX_VALUE,d,null);

		for(int i =0;i<atLeast.length;i++) {
			root.insert(atLeast[i]);
		}
		return result;
	}

	class Node {
		int start;
		int end;
		int d;
		int val = -1;

		Node left;
		Node right;
		Node parent;
		
		public Node(int start,int end,int d,Node parent) {
			this.start = start;
			this.end = end;
			this.d = d;
			this.parent = parent;
		}

		public void insert(int newVal) {
			if (val == -1) {
				if (newVal <= end) {
					if (newVal > start)
						val = newVal;
					else
						val = start;
					result[k++] = val;
					if (val-d-start >=0)
						this.left = new Node(start, val - d, d,this);
					if (end-(val+d) >=0)
						this.right = new Node(val+d,end,d,this);
				} else {
					System.out.println("This should not print");
				}
			} else {
				if (newVal >= val) {
					if (right != null)
						right.insert(newVal);
					else
						parent.right.insert(newVal);
				}
				else {
					if (left != null)
						left.insert(newVal);
					else {
						if (right != null)
							right.insert(newVal);
						else
							parent.right.insert(newVal);
					}
				}
			}
		}

		@Override
		public String toString() {
			return start+","+end+","+val;
		}
	}



}
