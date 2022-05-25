package edu.princeton.cs.algs4.ch4._4_1_undirected_graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixGraph implements Graph{
    private static final String NEWLINE = System.getProperty("line.separator");

    private boolean[][] adjMatrix;

    private int v;
    private int e;

    public AdjMatrixGraph(int v){
        if (v < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
        this.v = v;
        adjMatrix = new boolean[v][v];
    }

    public AdjMatrixGraph(In in){
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.v = in.readInt();
            if (v < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
            adjMatrix = new boolean[v][v];
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
        adjMatrix[v][w] = true;
        adjMatrix[w][v] = true;
        e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        int V = this.v;
        validateVertex(v);
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int w = 0;

                    @Override
                    public boolean hasNext() {
                        while(w<V){
                            if(adjMatrix[v][w]) return true;
                            w++;
                        }
                        return false;
                    }

                    @Override
                    public Integer next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        return w++;
                    }
                };
            }
        };
    }

    @Override
    public int degree(int v) {
        int count = 0;
        for(int w = 0; w< this.v; w++){
            if(adjMatrix[v][w]) count++;
        }
        return count;
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
            for (int adj: adj(v)) {
                s.append(adj + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
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
