package com.graphtheory.djikstra.day0;

import lombok.*;

@Getter
@Setter
@Builder
class Edge {
    private Node target;
    private int weight;
}
