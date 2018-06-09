package com.vishnu.algorith.ds.graph;

import java.util.*;

/**
 * Created by vviswanath on 3/25/18.
 */
public class BuildOrder {

    public static List<Node> buildOrder(List<Node> projects, Map<Node, Node> dependencies) {
        buildConnection(projects, dependencies);  //O(v+e)
        if (detectCycle(projects)) throw new RuntimeException("Cycle detected");
        List<Node> pOrder = new ArrayList<>();
        for(Node p : projects) {  //O(v)
            if (!p.visited) {
                visit(p, pOrder);
            }
        }

        return pOrder;
    }

    private static void visit(Node n, List<Node> pOrder) {
        n.visited = true;
        for (Node c : n.children) {
            visit(c, pOrder);
        }
        pOrder.add(n);
    }


    private static boolean detectCycle(List<Node> nodes) {
        for(Node n : nodes) {
            if (n.visited) continue;
            Set<Node> visited = new HashSet<>();
            visited.add(n);
            n.visited = true;
            boolean cycle = visitForCycle(n, visited);
            if (cycle) return cycle;
        }
        return false;
    }

    private static boolean visitForCycle(Node n, Set<Node> visited) {
        for(Node child: n.children) {
            if (visited.contains(child)) return true;
            child.visited = true;
            visited.add(child);
            boolean cycle = visitForCycle(child, visited);
            if (cycle) return cycle;
        }
        return false;
    }

    private static void buildConnection(List<Node> projects, Map<Node, Node> dependencies) {
        for(Node k : dependencies.keySet()) {
            Node v = dependencies.get(k);
            v.children.add(k);
        }
    }
    public static void main(String[] args) {
        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');

        List<Node> projects = new ArrayList<>();
        projects.add(a);
        projects.add(b);
        projects.add(c);
        projects.add(d);
        projects.add(e);
        projects.add(f);

        Map<Node, Node> dependencies = new HashMap<>();
        dependencies.put(a, d);
        dependencies.put(f, b);
        dependencies.put(b, d);
        dependencies.put(f, a);
        dependencies.put(d, c);

        List<Node> bOrder = buildOrder(projects, dependencies);
        bOrder.toString();

    }
}


class Node {
    char name;
    boolean visited;
    List<Node> children;

    public Node(char name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public boolean equals(Object other) {
        return this.name == ((Node) other).name;
    }

    public int hashCode() {
        return name;
    }


}
