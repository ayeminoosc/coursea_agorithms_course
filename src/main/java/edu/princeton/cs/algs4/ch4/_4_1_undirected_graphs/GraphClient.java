package edu.princeton.cs.algs4.ch4._4_1_undirected_graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac GraphClient.java
 *  Execution:    java GraphClient graph.txt
 *  Dependencies: Graph.java
 *
 *  Typical graph-processing code.
 *
 *  % java GraphClient tinyG.txt
 *  13 13
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  vertex of maximum degree = 4
 *  average degree           = 2
 *  number of self loops     = 0
 *  
 ******************************************************************************/

public class GraphClient {

    // maximum degree 
    public static int maxDegree(Graph g) {
        int max = -1;
        for(int v= 0; v<g.v(); v++){
            max = Math.max(max, g.degree(v));
        }
        return max;
    }

    // average degree
    public static double avgDegree(Graph g) {
        return 2 * g.e() / g.v();
    }

    // number of self-loops
    public static int numberOfSelfLoops(Graph g) {
        int count = 0;
        for(int v= 0; v<g.v(); v++){
            for(int adj: g.adj(v)){
                if(v == adj) count++;
            }
        }
        return count;
    } 

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new AdjMatrixGraph(in);
        StdOut.println(G);

        StdOut.println("vertex of maximum degree = " + maxDegree(G));
        StdOut.println("average degree           = " + avgDegree(G));
        StdOut.println("number of self loops     = " + numberOfSelfLoops(G));

    }

}