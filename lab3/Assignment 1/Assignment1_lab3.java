import java.io.*;

class Assignment1_lab3 {
    public static void main(String[] args) throws IOException {
        File in = new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab3/Assignment 1/98-0.txt"); // read from the
                                                                                                       // file
        File out = new File("/Users/vidhuaggarwal/Documents/GitHub/ID1021/lab3/Assignment 1/output.txt"); // create a
                                                                                                          // file to
                                                                                                          // write to
        FileWriter write = new FileWriter(out); // initialize writer
        FileReader reader = new FileReader(in); // initialize reader
        int c;
        while ((c = reader.read()) != -1) { // file reader returns -1 at the EOF
            if (Character.isLetter(c)) // check if character is letter
                write.write(c); // write to output
            else
                write.write(' '); // replace with blank if not letter
        }
        reader.close();
        write.close();
    }
}