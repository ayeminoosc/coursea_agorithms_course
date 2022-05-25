package edu.princeton.cs.algs4.ch4._4_1_undirected_graphs;

public interface Graph {
    int v();
    int e();
    void addEdge(int v, int w);
    Iterable<Integer> adj(int v);
    String toString();
    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v);
}