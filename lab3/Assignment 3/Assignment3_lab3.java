
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment3_lab3 {

    public static void main(String[] args) throws IOException {
        int distinct = 0, words = 0; // count total and distinct words
        int minlen = Integer.parseInt(args[0]); // get min length of word accepted
        BST<String, Integer> st = new BST<String, Integer>(); // initialize empty BST
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/ID1021/lab3/Assignment 1/98-0.txt")); // read file
        while (words < 1000 && in.hasNext()) { // file reader returns -1 at the EOF read 1000 words
            String word = in.next();
            if (word.length() < minlen) { // skip word if less than desired length
                continue;
            }
            if (st.contains(word)) { // update current value of key if it exists in BST
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1); // add new key to the BST with 1 count
                distinct++; // distinct word/node
            }

            words++;
        }
        in.close();

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        long sum = 0; // initialise some variables to store metrics
        String[] minval = new String[2];
        String[] maxval = new String[2];
        long mn = Integer.MAX_VALUE;
        long mx = Integer.MIN_VALUE;
        int count = 0;
        for (String k : st.keys()) { // iterating all keys
            count++;
            long start = System.nanoTime(); // time to get each key
            int val = st.get(k);
            long end = System.nanoTime();
            sum = (end - start) + sum;
            if ((end - start) > mx) {
                mx = (end - start);
                maxval[0] = k; // storing the max time value
                maxval[1] = Integer.toString(val);
            }
            if ((end - start) < mn) {
                mn = (end - start);
                minval[0] = k; // storing min time value
                minval[1] = Integer.toString(val);
            }
            if (val > st.get(max))
                max = k; // get key with highest value
        }
        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("min time: " + mn / 10 + " key: " + minval[0] + " value: " + minval[1]);
        System.out.println("max time: " + mx / 10 + " key: " + maxval[0] + " value: " + maxval[1]);
        System.out.println("avg time: " + sum / (count * 10)); // avg time for all get operations
    }

}
