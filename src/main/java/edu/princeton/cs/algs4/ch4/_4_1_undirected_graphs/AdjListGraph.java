package edu.princeton.cs.algs4.ch4._4_1_undirected_graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class AdjListGraph implements Graph{
    private static final String NEWLINE = System.getProperty("line.separator");
    private Bag<Integer>[] adjList;
    /** number of vertices **/
    private int v;

    /** number of edges **/
    private int e;
    /**
     * create a V-vertex graph with no edge
     * @param v
     */
    public AdjListGraph(int v){
        if (v < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
        this.v = v;
        adjList = (Bag<Integer>[])new Object[v];
        for (v = 0; v < this.v; v++) {
            adjList[v] = new Bag<Integer>();
        }
    }

    public AdjListGraph(In in){
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.v = in.readInt();
            if (v < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
            adjList = (Bag<Integer>[]) new Bag[this.v];
            for (int v = 0; v < this.v; v++) {
                adjList[v] = new Bag<Integer>();
            }
            int e = in.readInt();
            if (e < 0) throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
            for (int i = 0; i < e; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    @Override
    public int v() {
            return this.v;
    }

    @Override
    public int e() {
        return this.e;
    }

    @Override
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adjList[v].add(w);
        adjList[w].add(v);
        e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adjList[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= this.v)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.v-1));
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.v + " vertices, " + this.e + " edges " + NEWLINE);
        for (int v = 0; v < this.v; v++) {
            s.append(v + ": ");
            for (int w : adjList[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    @Override
    public int degree(int v) {
        validateVertex(v);
        return adjList[v].size();
    }


    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new AdjListGraph(in);
        StdOut.println(g);
    }
}
