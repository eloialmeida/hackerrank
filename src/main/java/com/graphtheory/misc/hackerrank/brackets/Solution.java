package com.graphtheory.misc.hackerrank.brackets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        Deque<Character> q = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            if(c == '(' || c== '{' || c== '[')
                q.add(c);
            else if (!q.isEmpty()){
                char head = q.pollLast();
                if (
                    (c == ')' && head!='(') ||
                    (c == '}' && head!='{') ||
                    (c == ']' && head!='[')
                ) {
                    return "NO";
                }
            } else {
                return "NO";
            }
        }
        return q.isEmpty() ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);
            System.out.println(result);
        }


        scanner.close();
    }
}
