import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment7_lab3 {

    public static void main(String[] args) throws IOException {
        int words = 0; // words counter to limit words read
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab3/Assignment 1/output.txt")); // load
                                                                                                                         // text
        // from file.
        // may need to
        // change path
        // to run
        BST<String, Integer> st = new BST<String, Integer>(); // initialize empty BST
        while (words < 200) { // file reader returns -1 at the EOF
            String word = in.next().toLowerCase(); // lowercase for best comparison
            if (word.length() < 4) { // ignore small words such as and & the
                continue;
            }
            if (st.contains(word)) { // if tree has word then update value for key
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1); // insert new key value pair if doesnt exists
            }
            words++;
        }
        in.close();
        Scanner input = new Scanner(System.in); // input the choice for printing
        System.out.println("Press 0 to print in alphabetic order or press 1 to print in reverse order:");
        String option = input.next();
        if (option.equals("0")) {
            st.printAlphabetic(); // check BST code for function
        } else if (option.equals("1")) {
            st.printReverse(); // check BST code for function
        }
        input.close();
    }
}
