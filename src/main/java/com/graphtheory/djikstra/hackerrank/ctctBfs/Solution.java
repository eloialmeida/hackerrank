package com.graphtheory.djikstra.hackerrank.ctctBfs;

import java.util.*;

public class Solution {

    public static class Graph {

        public Node[] nodes;

        public Graph(int size) {
            nodes = new Node[size];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node();
                nodes[i].dist = Integer.MAX_VALUE;
                nodes[i].nodeId = i;
            }
        }

        public void addEdge(int first, int second) {

            if (nodes[first].edges == null) {
                nodes[first].edges = new LinkedList<>();
            }

            if (nodes[second].edges == null) {
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

            boolean[] minDistanceProcessedNodes = new boolean[nodes.length];

            int remaining = nodes.length;

            while (remaining-- > 0) {

                Node u = findMin(nodes, minDistanceProcessedNodes);

                minDistanceProcessedNodes[u.nodeId] = true;

                if (u.edges != null) {
                    for (Edge edge : u.edges) {
                        Node t = edge.target;
                        if (u.dist != Integer.MAX_VALUE && u.dist + edge.weight < t.dist) {
                            t.dist = (u.dist + edge.weight);
                        }
                    }
                }
            }
        }


        private static Node findMin(Node[] nodes, boolean[] minDistanceProcessedNodes) {
            return Arrays.stream(nodes)
                    .filter(Objects::nonNull)
                    .filter(n->minDistanceProcessedNodes[n.nodeId]==false)
                    .min(
                            (Node a, Node b) -> {
                                return a.dist > b.dist ? 1 : -1;
                            }
                    ).get();
        }

        class Node {
            public int dist = Integer.MAX_VALUE;
            public List<Edge> edges;
            public int nodeId;
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
