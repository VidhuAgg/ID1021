import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment6_lab3 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab3/Assignment 1/output.txt")); // read
                                                                                                                         // file
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(); // initialise empty
                                                                                                    // Hashtable
        while (in.hasNext()) { // file reader returns -1 at the EOF
            String word = in.next();
            if (st.contains(word)) { // update value for existing key in hash table
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1); // insert new key value pair into the hash table if key doesnt exist there
            }
        }
        in.close();
        Scanner input = new Scanner(System.in); // scanner for asking user query for word
        do {

            System.out.println("Enter word to search or type exit to quit: ");
            String query = input.next();
            if (query.equals("exit")) { // if user enters exit, then quit program
                break;
            }
            int count = 0;
            if (st.contains(query)) { // check if querry exists in data, they are case sensitive
                count = st.get(query); // retrieve value from hash table
            }
            System.out.println(query + " has " + count + " occurances in the text");

        } while (true);
        input.close();
    }
}
