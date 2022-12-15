public class DepthFirstPaths {
    // DFS goes to the last possible edge from on neighbour and then checks others
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo; // edgeTo[v] = last edge on s-v path
    private final int s; // source vertex
    private int loopEnd; // variable to store end of loop if it exists

    public DepthFirstPaths(Graph G, int s) {
        this.s = s; // instanstiate variables to store data
        loopEnd = s; // initialising to source itself
        edgeTo = new int[G.V()]; // G.V() returns number of vertices
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s); // initial call with source vertex
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true; // mark visited vertex in array
        for (int w : G.adj(v)) { // itterating adjacent vertex to current vertex
            if (!marked[w]) { // when visiting unmarked vertex, add it in edge list
                edgeTo[w] = v;
                dfs(G, w); // recursive call with edge vertex
            }
        }
    }

    // same constructor reused for directed graphs
    public DepthFirstPaths(DirectedGraph G, int s) {
        this.s = s; // instanstiate variables to store data
        edgeTo = new int[G.V()]; // G.V() returns number of vertices
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s); // initial call with source vertex
    }

    // depth first search from v
    private void dfs(DirectedGraph G, int v) {
        marked[v] = true; // mark visited vertex in array
        for (int w : G.adj(v)) { // itterating adjacent vertex to current vertex
            if (w == s) { // if adjacency contains source
                loopEnd = v; // loop end found success and set it to cuurent vertex
            }
            if (!marked[w]) { // when visiting unmarked vertex, add it in edge list
                edgeTo[w] = v;
                dfs(G, w); // recursive call with edge vertex
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v]; // vertex would be visited if path exists
    }

    public Iterable<Integer> getLoop() {
        if (loopEnd == s) { // no loop found if loopend is same as source
            return null;
        }
        Stack<Integer> path = new Stack<Integer>(); // use Stack to store the vertex in path
        path.push(s); // pushing source to close loop
        for (int x = loopEnd; x != s; x = edgeTo[x]) // starting from loopend node, visting edge to edge until source is
                                                     // found
            path.push(x); // pushing all vertex in the way
        path.push(s); // push source to complete path
        return path;
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v); // check if end vertex exists
        if (!hasPathTo(v)) // check if path exists
            return null;
        Stack<Integer> path = new Stack<Integer>(); // use Stack to store the vertex in path
        for (int x = v; x != s; x = edgeTo[x]) // starting from end node, visting edge to edge until source is found
            path.push(x); // pushing all vertex in the way
        path.push(s); // push source to complete path
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}
