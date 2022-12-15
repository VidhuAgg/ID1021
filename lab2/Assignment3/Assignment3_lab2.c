#include <stdio.h>

void sort(int array[], int len)
{
    int j = 0;
    int l = len - 1;
    // O(n) time
    // building the array from left to right
    for (int i = 0; i <= l; i++)
    {
        if (array[i] < 0) // if negative put the element leftmost
        {
            int temp = array[i]; // swap the negative element to left index
            array[i] = array[j];
            array[j] = temp;
            j++; // increase left index upon placing a negative element on left
        }
        else if (array[i] >= 0) // if positive put the element rightmost
        {
            int temp = array[i]; // swap the positive element to right index
            array[i] = array[l];
            array[l] = temp;
            l--; // decrease righ index upon placing a positive element on right
            i--; // decrease current element to test for the newly swapped element again
        }
    }
}
int main()
{
    printf("Insert the size of the desired array \n");
    int n;
    scanf("%d", &n); // take the input that is used to declare the size of the array
    int toBeSorted[n];

    printf("insert the elements of the array \n");
    for (int i = 0; i < n; i++) // a loop to take the input of the array
        scanf("%d", &toBeSorted[i]);

    sort(toBeSorted, n);

    for (int i = 0; i < n; i++) // a method to print the elements of the array
    {
        printf("[");
        printf("%d", toBeSorted[i]);
        printf("]");
    }
    return 0;
}
