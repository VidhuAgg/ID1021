public class DirectedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency list

    public DirectedGraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>(); // instantiate empty Bags as adjacency lists for all vertex
        }
    }

    public int V() {
        return V; // get number of vertices
    }

    public int E() {
        return E; // get number of edges
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v); // check if vertex exists
        validateVertex(w);
        E++; // increment edges
        adj[v].add(w); // add w in adjacancy list of v as it is directed graph
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v]; // get the adjacency matrix
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size(); // returns the connections to a particular vertex
    }

    public String toString() { // print graph, not used by us but a nice to have for tests
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
