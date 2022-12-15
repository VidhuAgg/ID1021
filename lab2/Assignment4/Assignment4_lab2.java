
import java.util.Scanner;

public class Assignment4_lab2 {

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) { // merge a[lo .. mid] with a[mid+1 ..hi]
                                                                             // using aux[lo .. hi]
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

    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid); // recursive call to split the array
        mergeSort(a, aux, mid + 1, hi); // sort the smaller array
        merge(a, aux, lo, mid, hi); // merge the smaller sorted array
    }

    public static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length - 1); // call merge sort with inititial array and empty array
    }

    static void insertionSort(int[] arr) {
        // itterate through the given array
        for (int i = 1; i < arr.length; i++) {
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

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int lo, int hi) {
        if (hi <= lo) // base case
            return;
        int j = partition(a, lo, hi); // get new partitioning element and sort with respect to element
        quickSort(a, lo, j - 1); // recursively sort right half
        quickSort(a, j + 1, hi); // recursively sort left half
    }

    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {

            // find item on lo to swap
            while ((a[++i] < v)) { // increment on left side until a element is lesser than partition
                if (i == hi)
                    break; // break if no element found until right limit
            }

            // find item on hi to swap
            while ((v < a[--j])) { // decrement on right side until a element is greater than partition
                if (j == lo)
                    break; // break iff no element found until left limit
            }

            // check if pointers cross
            if (i >= j) // base case
                break;

            int temp = a[i]; // perform a swap between the two elements on i and j and place in respective
                             // left or right side
            a[i] = a[j];
            a[j] = temp;
        }

        int temp = a[lo]; // put partitioning item v at a[j]
        a[lo] = a[j]; // the element at j is now fullfing all conditions as partiftion element and can
                      // be replaced safely
        a[j] = temp;

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner
        System.out.println("Insert size of the desired array");
        int size = in.nextInt(); // scans for the size of the aray
        int[] input = new int[size];
        in.close();
        for (int i = 0; i < input.length; i++) {
            input[i] = (int) (Math.random() * 100);
        }
        int[] toBeSorted = new int[size];
        for (int i = 0; i < input.length; i++) {
            toBeSorted[i] = input[i];
        }
        int[] reverse = new int[size];
        int[] sorted = new int[size];
        for (int i = 0; i < size; i++) {
            reverse[i] = size - i;
        }
        for (int i = 0; i < size; i++) {
            sorted[i] = i;
        }
        long startTime = System.nanoTime();
        mergeSort(toBeSorted);
        long endTime = System.nanoTime();
        System.out.println("merge sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        mergeSort(reverse);
        endTime = System.nanoTime();
        System.out.println("reverse merge sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        mergeSort(sorted);
        endTime = System.nanoTime();
        System.out.println("sorted merge sort : " + (endTime - startTime) / 100000);
        for (int i = 0; i < size; i++) {
            reverse[i] = size - i;
        }
        for (int i = 0; i < size; i++) {
            sorted[i] = i;
        }
        for (int i = 0; i < input.length; i++) {
            toBeSorted[i] = input[i];
        }
        startTime = System.nanoTime();
        insertionSort(toBeSorted);
        endTime = System.nanoTime();
        System.out.println("insertion sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        insertionSort(reverse);
        endTime = System.nanoTime();
        System.out.println("reverse insertion sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        insertionSort(sorted);
        endTime = System.nanoTime();
        System.out.println("sorted insertion sort : " + (endTime - startTime) / 100000);
        for (int i = 0; i < size; i++) {
            reverse[i] = size - i;
        }
        for (int i = 0; i < size; i++) {
            sorted[i] = i;
        }
        for (int i = 0; i < input.length; i++) {
            toBeSorted[i] = input[i];
        }
        startTime = System.nanoTime();
        quickSort(toBeSorted);
        endTime = System.nanoTime();
        System.out.println("quick sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        quickSort(reverse);
        endTime = System.nanoTime();
        System.out.println("reverse quick sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        quickSort(sorted);
        endTime = System.nanoTime();
        System.out.println("sorted quick sort : " + (endTime - startTime) / 100000);
    }
}
