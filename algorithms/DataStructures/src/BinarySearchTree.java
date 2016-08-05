import java.util.List;

/**
 * Created by vishnu on 2/24/16.
 */
public class BinarySearchTree {

    Node root = null;


    public void insert(int weight) {
        Node node = new Node(weight);
        if (root==null)
            root = node;
        else {
            insertRec(root,node);
        }
    }

    public void delete(int weight) {
        if (root == null)
            return;
        deleteRec(root,null,weight);
    }

    private void deleteRec(Node localRoot,Node parentNode,int weight) {
        if (localRoot == null)
            return;
        if (localRoot.weight == weight) {
            if (parentNode == null)
                localRoot = null;
            else {
                if (parentNode.weight > localRoot.weight) {
                    if (localRoot.right != null) {
                        parentNode.left = localRoot.right;
                        localRoot.right.left = localRoot.left;
                        localRoot = null;
                    } else
                        parentNode.left = localRoot.left;
                } else {
                    if (localRoot.left != null) {
                        parentNode.right = localRoot.left;
                        localRoot.left.right = localRoot.right;
                        localRoot = null;
                    } else
                        parentNode.right = localRoot.right;
                }
            }
        } else {
            if (localRoot.weight > weight)
                deleteRec(localRoot.left,localRoot,weight);
            else
                deleteRec(localRoot.right,localRoot,weight);
        }
    }

    private void insertRec(Node localRoot,Node toInsert) {
        if (toInsert.weight<localRoot.weight) {
            if (localRoot.left == null)
                localRoot.left=toInsert;
            else
                insertRec(localRoot.left,toInsert);
        } else if (toInsert.weight >localRoot.weight){
            if (localRoot.right == null)
                localRoot.right = toInsert;
            else
                insertRec(localRoot.right,toInsert);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(5);
        bst.insert(2);
        bst.insert(10);
        bst.insert(0);
        bst.insert(3);
        bst.insert(20);
        bst.insert(7);

        bst.delete(10);

    }
}

class Node {

    Node left = null;
    Node right = null;
    int weight;

    public Node(int weight) {
        this.weight = weight;
    }

}
