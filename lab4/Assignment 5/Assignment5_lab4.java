import java.io.File;
import java.util.Scanner;

public class Assignment5_lab4 {

    // Updated DFS with new method get loop. while building the dfs it is stored if
    // any vertex has source in its adjacancy list. Later getloop can be called to
    // get a path to such a node. returns null if no path exists
    public static void main(String[] args) throws Exception {
        int V = 0; // store vertice count
        ST st = new ST(); // ST to store int to string conversion for data codes
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab4/contiguous-usa.dat.txt")); // read
                                                                                                                        // from
                                                                                                                        // file
        while (in.hasNext()) {
            String vertice = in.next(); // read all codes one y one
            if (st.contains(vertice)) {
                continue; // ignore duplicates
            }
            st.put(vertice, V); // Add new vertice with distinct vertice key
            V++; // increment vertice count
        }
        in.close();
        String[] vertices = new String[V]; // make array to get int to string conversion
        for (String key : st.keys()) {
            vertices[st.get(key)] = key; // store the string keys on respective array index, used later
        }
        DirectedGraph G = new DirectedGraph(V); // build graph with vertice count
        Scanner input = new Scanner(
                new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab4/contiguous-usa.dat.txt")); // read from
        // file
        while (input.hasNext()) {
            String v = input.next(); // read two verrtices after one another from file
            String w = input.next();
            G.addEdge(st.get(v), st.get(w)); // get int key from ST for given string and add edge on graph
        }
        input.close();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter starting point X: ");
        String start = sc.next(); // record start point for search
        if (!st.contains(start)) {
            sc.close();
            throw new Exception("Vertex not in database");
        }
        DepthFirstPaths dfs = new DepthFirstPaths(G, st.get(start)); // get int from ST for start and instantiate dfs
                                                                     // object
        Iterable<Integer> path = dfs.getLoop(); // get loop path for source if it exists
        if (path == null) {
            System.out.println("No loops found");
        } else {
            System.out.println("The found path is: ");
            for (int x : path) { // itterating the int values in path
                System.out.print(vertices[x] + " - "); // converting int values to readable strings from array
            }
        }
        sc.close();
    }
}
