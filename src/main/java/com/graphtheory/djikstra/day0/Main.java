package com.graphtheory.djikstra.day0;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Graph g = Graph.builder()
                .nodeMap(new TreeMap<>())
                .build();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("src\\main\\resources\\test1.txt")))) {
            String line;
            int lineCounter = 0;
            while ((line = br.readLine()) != null) {
                processLine(line, lineCounter++, g);
            }
        }

        runDjisktra(g);
    }

    private static void processLine(String line, int i, Graph g) {

        int vertexIndex = i;
        Integer[] nodeEdges = parseLine(line);
        if (!g.getNodeMap().containsKey(vertexIndex)) {
            g.getNodeMap().put(vertexIndex, Node.builder()
                    .nodeId(vertexIndex)
                    .edges(new LinkedList<>())
                    .dist(MAX_VALUE)
                    .build());
        }
        Node node = g.getNodeMap().get(vertexIndex);
        if (g.getRoot() == null) {
            g.setRoot(node);
            node.setDist(0);
        }
        int destEdgeIndex = 0;
        for (int destEdgeWeight : nodeEdges) {
            if (destEdgeWeight != 0) {
                // check if node vertex exists
                if (!g.getNodeMap().containsKey(destEdgeIndex)) {
                    Node destinationNode = Node.builder()
                            .nodeId(destEdgeIndex)
                            .edges(new LinkedList<>())
                            .dist(MAX_VALUE)
                            .build();
                    g.getNodeMap().put(destEdgeIndex, destinationNode);
                }
                Edge edge = Edge.builder()
                        .weight(destEdgeWeight)
                        .target(g.getNodeMap().get(destEdgeIndex))
                        .build();

                node.getEdges().add(edge);
            }
            destEdgeIndex += 1;
        }
        g.setSize(vertexIndex+1);
    }

    private static Integer[] parseLine(String line) {
        return Collections.list(new StringTokenizer(line, ",")).stream()
                .map(token -> Integer.valueOf(((String) token).trim()))
                .collect(Collectors.toList()).toArray(new Integer[0]);
    }

    private static void runDjisktra(Graph g) {

        Set<Node> Q = new HashSet<>(g.getNodeMap().values());

        while (!Q.isEmpty()) {

            Node u = findMin(Q);

            Q.remove(u);

            for(Edge edge: u.getEdges()) {
                Node t = edge.getTarget();
                if (u.getDist() + edge.getWeight() < t.getDist()) {
                    t.setDist(u.getDist() + edge.getWeight());
                }
            }
        }

        g.getNodeMap().values().forEach(n->System.out.println(n.getNodeId() + " " + n.getDist()));
    }

    private static Node findMin(Set<Node> q) {
        return q.stream().min(Comparator.comparing(Node::getDist)).get();
    }
}
