import java.util.Scanner;

class Assignment6_lab2 {

    public static void quickSort(int[] a, String type) {
        quickSort(a, 0, a.length - 1, type);
    }

    private static void quickSort(int[] a, int lo, int hi, String type) {
        if (hi <= lo) // base case
            return;
        int j = partition(a, lo, hi, type); // get new partitioning element and sort with respect to element
        quickSort(a, lo, j - 1, type); // recursively sort right half
        quickSort(a, j + 1, hi, type); // recursively sort left half
    }

    private static int partition(int[] a, int lo, int hi, String type) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        int k = median(a[lo], a[(hi + lo) / 2], a[hi], lo, hi); // store median index for array
        if (type == "median") {
            v = a[k]; // use the median partition element
        }
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

        if (type == "median") {// put partitioning item v at a[j], in case of median the median element index
                               // is used
            int temp = a[k];// the element at j is now fullfing all conditions as partiftion element and can
                            // be replaced safely
            a[k] = a[j];
            a[j] = temp;
        } else {
            int temp = a[lo];
            a[lo] = a[j];// the element at j is now fullfing all conditions as partiftion element and can
                         // be replaced safely
            a[j] = temp;
        }

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static int median(int a, int b, int c, int lo, int hi) { // method to return median index
        int median = a;
        if (a > b) {
            if (a < c)
                median = lo;
            else if (b > c)
                median = (hi + lo) / 2;
            else
                median = hi;
        } else {
            if (a > c)
                median = lo;
            else if (b < c)
                median = (hi + lo) / 2;
            else
                median = hi;
        }
        return median;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner
        System.out.println("Insert size of the desired array");
        int size = in.nextInt(); // scans for the size of the aray
        int[] input = new int[size];
        in.close();
        for (int i = 0; i < input.length; i++) {
            input[i] = (int) (Math.random() * 1000);
        }
        int[] toBeSorted = new int[size];
        for (int i = 0; i < input.length; i++) {
            toBeSorted[i] = input[i];
        }

        long startTime = System.nanoTime();
        quickSort(toBeSorted, "normal");
        long endTime = System.nanoTime();
        System.out.println("quick sort : " + (endTime - startTime) / 100000);
        startTime = System.nanoTime();
        quickSort(toBeSorted, "median");
        endTime = System.nanoTime();
        System.out.println("median of 3 quick sort : " + (endTime - startTime) / 100000);
    }

}