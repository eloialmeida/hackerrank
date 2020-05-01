package com.graphtheory.misc.hackerrank;

import java.io.*;
import java.util.*;

public class Solution {

    static class RunningMedian {

        public List<Integer> orderedElements = new ArrayList<>();
        public List<Double> runningMedians = new LinkedList<>();

        public RunningMedian() {

        }

        private void addItem(int aItem) {
            binaryInsert(orderedElements, aItem, 0, orderedElements.size());
            calculateMedian();
        }

        private void calculateMedian() {
            double median = -1;
            if(orderedElements.size() % 2 == 1) {
                median = orderedElements.get((orderedElements.size()/2));
            } else {
                median = (orderedElements.get(orderedElements.size() / 2 - 1 ) +
                        orderedElements.get(orderedElements.size() / 2)) / 2.0;
            }
            runningMedians.add(median);
        }


        private void binaryInsert(List<Integer> bucket, int aItem, int left, int right) {
            if (right == left) {
                bucket.add(right, aItem);
            } else {
                int midElementIndex = left + (right - left) / 2;
                int midElement = bucket.get(midElementIndex);

                if (aItem < midElement) {
                    binaryInsert(bucket, aItem, left, midElementIndex);
                } else {
                    binaryInsert(bucket, aItem, midElementIndex + 1, right);
                }
            }
        }
    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        RunningMedian runningMedian = new RunningMedian();

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            runningMedian.addItem(aItem);
        }

        for(Double m: runningMedian.runningMedians) {
            System.out.println(m);
        }

        System.out.println(" ");
    }
}
