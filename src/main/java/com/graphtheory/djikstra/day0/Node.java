package com.graphtheory.djikstra.day0;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
class Node {
    private int nodeId;
    private int dist;
    private List<Edge> edges;
}