import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment5_lab3 {

    public static double calculateStandardDeviation(double[] array) {

        // get the sum of array
        double sum = 0.0;
        for (double i : array) {
            sum += i; // sum all hashes
        }

        // get the mean of array
        int length = array.length;
        double mean = sum / length; // calculate average hash value

        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2); // formula for standard deviation in dataset
        }
        System.out.println("Mean of hashes is: " + mean);
        return Math.sqrt(standardDeviation / length);
    }

    public static void main(String[] args) throws IOException {
        int words = 0; // word counter
        int minlen = Integer.parseInt(args[0]); // set min accepted word length
        int wordlimit = 1000; // set wordlimit to check
        double[] hashes = new double[wordlimit];
        Scanner in = new Scanner(new File("/Users/vidhuaggarwal/ID1021/lab3/Assignment 1/98-0.txt")); // read from file
        while (words < wordlimit && in.hasNext()) { // file reader returns -1 at the EOF
            String word = in.next();
            if (word.length() < minlen) { // skip if word shorter than limit
                continue;
            }
            int hash = word.hashCode(); // use inbuilt java function to get hashcode
            int simlplehash = (hash / 1000000); // divide hash by large number to make it more readable/usable
            hashes[words] = simlplehash; // store all hashes in array

            words++;
        }
        double standardDeviation = calculateStandardDeviation(hashes); // calculate standard deviation in hashes
        // Standard deviation only allows ananlysis to see how values are generally
        // spread in data
        System.out.println("standard Deviation in hashes is: " + standardDeviation);
        // plotting a normalised graph shows us how evenly distributed are the hash
        // values among a vast range and are alot uniform too
        in.close();
    }
}
