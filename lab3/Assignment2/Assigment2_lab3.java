import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assigment2_lab3 {

    public static void main(String[] args) throws IOException {
        int distinct = 0, words = 0; // count distinct and total words
        int minlen = Integer.parseInt(args[0]); // set min length of accepted words
        ST st = new ST(); // initialise new empty ST
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab3/Assignment 1/98-0.txt"));
        while (words < 1000 && in.hasNext()) { // file reader returns -1 at the EOF
            String word = in.next();

            if (word.length() < minlen) { // skip if length is less than desired word length
                continue;
            }
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1); // update value if key exists
            } else {
                st.put(word, 1); // insert new key value with word count 1
                distinct++; // this is a distinct word
            }
            words++;
        }
        in.close();

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0); // initialise variables for statistics
        long sum = 0;
        String[] minval = new String[2];
        String[] maxval = new String[2];
        long mn = Integer.MAX_VALUE;
        long mx = Integer.MIN_VALUE;
        int count = 0;
        for (String k : st.keys()) { // iterate all present keys
            count++;
            long start = System.nanoTime(); // time every get operation
            int val = st.get(k);
            long end = System.nanoTime();
            sum = (end - start) + sum;
            if ((end - start) > mx) {
                mx = (end - start);
                maxval[0] = k; // store maximum time to access a key
                maxval[1] = Integer.toString(val);
            }
            if ((end - start) < mn) {
                mn = (end - start);
                minval[0] = k; // store minimum time to access a key
                minval[1] = Integer.toString(val);
            }
            if (val > st.get(max))
                max = k; // store the key with highest value(moost word count)
        }
        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("min time: " + mn / 10 + " key: " + minval[0] + " value: " + minval[1]);
        System.out.println("max time: " + mx / 10 + " key: " + maxval[0] + " value: " + maxval[1]);
        System.out.println("avg time: " + sum / (count * 10)); // calculate average time for get operations
    }

}
