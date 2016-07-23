import java.util.HashSet;
import java.util.Set;

/**
 * Created by vishnu on 6/13/16.
 */
public class Graph {

    Set<GNode> nodes;
    Set<GEdge> edges;

    public Graph() {
        nodes = new HashSet<>();
        edges = new HashSet<>();
    }

    public void addNode(GNode n) {
        nodes.add(n);
    }

    public void addEdge(GNode a, GNode b, int weight) {
        if (a != null && b != null && weight > 0) {
            GEdge e = new GEdge(a,b,weight);
            if (nodes.contains(e.a) && nodes.contains(e.b)) {
                System.out.println("edge added");
                edges.add(e);
            }
            else {
                nodes.add(e.a);
                nodes.add(e.b);
                edges.add(e);
                System.out.println("added missing nodes and edge added");
            }

        }
    }

    public void addEdge(GEdge e) {
        if (e.a != null && e.b != null && e.weight > 0) {
            if (nodes.contains(e.a) && nodes.contains(e.b)) {

                edges.add(e);
                System.out.println("edge added");
            }
            else
                System.out.println("cannot add edge between nodes that does not exist in the graph");
        }
    }

    public void print() {
        System.out.println("nodes");
        for(GNode node : nodes) {
            System.out.print(node);
        }
        System.out.println();
        System.out.println("Edges");
        for(GEdge edge: edges) {
            System.out.print(edge);
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        GNode n1 = new GNode(1,10);
        GNode n2 = new GNode(2,5);
        GNode n3 = new GNode(3,9);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addEdge(n1,n2,100);
        g.addEdge(n1,n3,20);
        g.addEdge(n3,n2,67);
        g.addEdge(n2,n3,70);
        g.print();

    }
}

class GNode {
    int id;
    int val;


    public GNode(int id,int val) {
        this.id = id;
        this.val = val;
    }
    @Override
    public boolean equals(Object other) {
        return this.id == ((GNode)other).id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString(){
        return "["+this.id+","+this.val+"]";
    }
}

class GEdge {
    GNode a;
    GNode b;
    int weight;

    public GEdge(GNode a, GNode b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object other) {
        GEdge that = (GEdge) other;
        return (this.a.equals(that.a) && this.b.equals(that.b)) || (this.a.equals(that.b) && this.b.equals(that.a));
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.a.id+""+this.b.id);
    }

    @Override
    public String toString(){
        return "("+this.a.id+"-"+this.b.id+"; "+this.weight+")";
    }
}
