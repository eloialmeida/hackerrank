package com.graphtheory.djikstra.hackerrank.ctctBfs;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static class Graph {

        public Node[] nodes;

        public Graph(int size) {
            nodes = new Node[size];
        }

        public void addEdge(int first, int second) {

            if (nodes[first] == null) {
                nodes[first] = new Node();
                nodes[first].dist = Integer.MAX_VALUE;
                nodes[first].edges = new LinkedList<>();
            }

            if (nodes[second] == null) {
                nodes[second] = new Node();
                nodes[second].dist = Integer.MAX_VALUE;
                nodes[second].edges = new LinkedList<>();
            }

            Edge ftos = new Edge();
            ftos.target = nodes[second];
            ftos.weight = 6;
            nodes[first].edges.add(ftos);

            Edge stof = new Edge();
            stof.target = nodes[first];
            stof.weight = 6;
            nodes[second].edges.add(stof);
        }

        public void shortestReach(int startId) { // 0 indexed
            nodes[startId].dist = 0;
            djisktra(startId);
        }

        private void djisktra(int startId) {

            Set<Node> Q = new HashSet<Node>(Arrays.stream(nodes).filter(Objects::nonNull).collect(Collectors.toList()));

            while (!Q.isEmpty()) {

                Node u = findMin(Q);

                Q.remove(u);

                for (Edge edge : u.edges) {
                    Node t = edge.target;
                    if (u.dist!=Integer.MAX_VALUE && u.dist + edge.weight < t.dist) {
                        t.dist = (u.dist + edge.weight);
                    }
                }
            }
        }


        private static Node findMin(Set<Node> q) {
            return q.stream().filter(Objects::nonNull).min(
                    (Node a, Node b) -> {
                        return a.dist > b.dist ? 1 : -1;
                    }
            ).get();
        }

        class Node {
            public int dist = Integer.MAX_VALUE;
            public List<Edge> edges;
        }

        class Edge {
            public Node target;
            public int weight = 6;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            graph.shortestReach(startId);

            for (int i = 0; i < graph.nodes.length; i++) {
                if (i != startId && graph.nodes[i] != null && graph.nodes[i].dist != Integer.MAX_VALUE) {
                    System.out.print(graph.nodes[i].dist);
                    System.out.print(" ");
                } else if (graph.nodes[i] == null || graph.nodes[i].dist == Integer.MAX_VALUE) {
                    System.out.print("-1 ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
