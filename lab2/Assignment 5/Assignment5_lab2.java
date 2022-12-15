import java.util.Scanner;

public class Assignment5_lab2 {

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) { // merge a[lo .. mid] with a[mid+1 ..hi]
        // using aux[lo .. hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; // copy to aux[]
        }
        // merge back to a[]
        // assuming here left and right halves are sorted
        int i = lo, j = mid + 1; // i is iterator for right side and j is iterator for right side
        for (int k = lo; k <= hi; k++) { // k is iterator for whole array
            if (i > mid)
                a[k] = aux[j++]; // place the right itterating element on current itteration if left iterator
                                 // exceeds the mid limit and increment on right
            else if (j > hi)
                a[k] = aux[i++]; // place the left itterating element on current itteration if right iterator
                                 // exceeds the high limit and increment on left
            else if (aux[j] < aux[i])
                a[k] = aux[j++]; // place the right itterating element on current position if it is less than the
                                 // left counterpart and increment on right
            else
                a[k] = aux[i++]; // place the left itterating element on current position if is is less than the
                                 // right counterpart and increment on left
        }
    }

    private static void mergeSort(int[] a, int[] aux, int lo, int hi, int cuttoff) {
        if (hi - lo <= cuttoff) {
            insertionSort(a, lo, hi);
            return;
        }
        if (hi <= lo) // base case
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid, cuttoff); // sort left half recursively
        mergeSort(a, aux, mid + 1, hi, cuttoff); // sort right half recursively
        merge(a, aux, lo, mid, hi); // merge left and right halves
    }

    public static void mergeSort(int[] a, int cuttoff) {
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length - 1, cuttoff); // call merge sort with inititial array and empty array
    }

    static void insertionSort(int[] arr, int lo, int hi) {
        // itterate through the given array
        for (int i = 1 + lo; i < hi + 1; i++) {
            int curr = arr[i]; // store the current element
            int j = i - 1; // index the element previous to the current element

            // inner loop
            while (j >= 0 && arr[j] > curr) { // comparing current element with the elements preceeding it
                int temp = arr[j + 1]; // store the element being swapped temporarily
                arr[j + 1] = arr[j]; // perfrom a swap when preeciding element greater than current element
                arr[j] = temp;
                j--; // decrement j to check previous element
            }
        }
    }

    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        long duration = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Insert size of the desired array");
        int size = sc.nextInt();
        int[] toBeSorted = new int[size];

        System.out.println("insert cut-off");
        int cutOff = sc.nextInt();
        sc.close();
        // inserts the elements randomly to the list
        int temp = 0;
        for (int i = 0; i < size; i++) {
            temp = (int) (Math.random() * 100);
            toBeSorted[i] = temp;
        }
        // run the algorithm and measure its execution time // better way to do this?
        startTime = System.currentTimeMillis();
        mergeSort(toBeSorted, cutOff);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(duration + " ms to sort an array of the size: " + size + " with a cut off of: " + cutOff);
    }
}
