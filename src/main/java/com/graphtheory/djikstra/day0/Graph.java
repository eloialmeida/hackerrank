package com.graphtheory.djikstra.day0;

import lombok.*;
import java.util.Map;

@Getter
@Setter
@Builder
public class Graph {
    private int size;
    private Node root;
    private Map<Integer, Node> nodeMap;
}