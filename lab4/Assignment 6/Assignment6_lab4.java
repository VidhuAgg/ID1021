import java.io.File;
import java.util.Scanner;

public class Assignment6_lab4 {
    // Based on bfs implementation and logic that shortest path A - B + shortest
    // path B - C = shortest path A -B -C
    // Almost same as assignment 3, only usage of directed graphs however bfs
    // remains same
    public static void main(String[] args) throws Exception {
        int V = 0; // store vertice count
        int E = 0;// store Edges count
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/ID1021/lab4/Assignment 6/NYC.txt")); // read from file
        V = in.nextInt();
        E = in.nextInt();
        DirectedGraph G = new DirectedGraph(V); // build graph with vertice count
        while (in.hasNext()) {
            int v = in.nextInt(); // read two verrtices after one another from file
            int w = in.nextInt();
            G.addEdge(v, w); // add edge in graph
            in.next(); // skip 3rd value
        }
        in.close();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter starting point X: ");
        int start = sc.nextInt(); // record start point for search
        BreadthFirstPaths bfs1 = new BreadthFirstPaths(G, start); // instantiate bfs
        System.out.println("Enter end point Y: ");
        int end = sc.nextInt(); // get end point input
        System.out.println("Enter mid point C: ");
        int mid = sc.nextInt(); // get mid point input
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(G, mid); // instantiate second bfs
        if (!bfs1.hasPathTo(mid) || !bfs2.hasPathTo(end)) { // exception if path doesnt exist
            sc.close();
            throw new Exception(" No path found");
        }
        Iterable<Integer> path1 = bfs1.pathTo(mid); // get path for start -mid
        Iterable<Integer> path2 = bfs2.pathTo(end); // get path for mid -end
        System.out.println("The found path is: ");
        for (int x : path1) { // itterating the int values in path start - mid
            System.out.print(x + " - ");
        }
        for (int x : path2) { // itterating the int values in path mid - end
            if (mid == x) { // skip repeating the mid vertice
                continue;
            }
            System.out.print(x + " - ");
        }
        sc.close();
    }
}
