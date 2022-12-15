public class BreadthFirstPaths {
    // BFS goes in layer by layer, 1st neighbours, 2nd neighbours and so on..
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s-v path
    private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo; // distTo[v] = number of edges shortest s-v path

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()]; // instantiate all variables to store data
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s); // validate source vertex
        bfs(G, s); // call bfs with source vertex
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>(); // use qeueu as data structure
        for (int v = 0; v < G.V(); v++) // iterating all vertices
            distTo[v] = INFINITY; // set distance at infinity as its unknown
        distTo[s] = 0; // distance to self 0
        marked[s] = true; // mark visites vertex
        q.enqueue(s); // add current node to the qeueu

        while (!q.isEmpty()) { // base case to end while loop
            int v = q.dequeue(); // check vertice on top of qeueu
            for (int w : G.adj(v)) { // itterate all adjacent edges
                if (!marked[w]) {
                    edgeTo[w] = v; // add edge in matrix as path exists
                    distTo[w] = distTo[v] + 1; // set distance from node
                    marked[w] = true; // mark as visited
                    q.enqueue(w); // add the neighbour in qeueu to visit it
                }
            }
        }
    }

    // same implementation for directed graphs
    public BreadthFirstPaths(DirectedGraph G, int s) {
        marked = new boolean[G.V()]; // instantiate all variables to store data
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s); // validate source vertex
        bfs(G, s); // call bfs with source vertex
    }

    private void bfs(DirectedGraph G, int s) {
        Queue<Integer> q = new Queue<Integer>(); // use qeueu as data structure
        for (int v = 0; v < G.V(); v++) // iterating all vertices
            distTo[v] = INFINITY; // set distance at infinity as its unknown
        distTo[s] = 0; // distance to self 0
        marked[s] = true; // mark visites vertex
        q.enqueue(s); // add current node to the qeueu

        while (!q.isEmpty()) { // base case to end while loop
            int v = q.dequeue(); // check vertice on top of qeueu
            for (int w : G.adj(v)) { // itterate all adjacent edges
                if (!marked[w]) {
                    edgeTo[w] = v; // add edge in matrix as path exists
                    distTo[w] = distTo[v] + 1; // set distance from node
                    marked[w] = true; // mark as visited
                    q.enqueue(w); // add the neighbour in qeueu to visit it
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v]; // if path exists node must be marked/visited
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>(); // same as dfs going edge to edge from end until start
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x); // visit until distance to node is 0 which is reaching source node
        path.push(x);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}