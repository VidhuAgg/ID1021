import java.util.Arrays;
import java.util.Scanner;

public class Assigment1_lab2 {

    static void sort(int[] arr) {
        int count = 0;
        // itterate through the given array
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i]; // store the current element
            int j = i - 1; // index the element previous to the current element

            // inner loop
            while (j >= 0 && arr[j] > curr) { // comparing current element with the elements preceeding it
                int temp = arr[j + 1]; // store the element being swapped temporarily
                arr[j + 1] = arr[j]; // perfrom a swap when preeciding element greater than current element
                arr[j] = temp;
                System.out.println(Arrays.toString(arr)); // print the swap
                count++; // swap counter increase
                j--; // decrement j to check previous element
            }
        }
        System.out.println("Swaps: " + count);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner
        System.out.println("Insert size of the desired array");
        int size = in.nextInt(); // scans for the size of the aray
        int[] toBeSorted = new int[size];

        System.out.println("Insert the elements to be sorted");

        for (int i = 0; i < toBeSorted.length; i++) { // for loop to scan and insert the elements of the list
            toBeSorted[i] = in.nextInt();
        }
        in.close();
        System.out.println(Arrays.toString(toBeSorted)); // print pre sort
        Assigment1_lab2.sort(toBeSorted);
        System.out.println(Arrays.toString(toBeSorted)); // print post sort
    }
}