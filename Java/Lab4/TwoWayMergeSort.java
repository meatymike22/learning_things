/**
 * @author Michael McElroy
 * Date: 4/27/2021
 * Description: Uses the 2 way merge sort algorithm to sort an array,
 * counts the amount of comparisons, exchanges, and measures the run time
 * of the sorting algorithm.
 */
public class TwoWayMergeSort {
    int[] data;
    int comparisons, exchanges;
    long runTime;

    /**
     * Constructor for 2 way merge. Copies the argument into the member
     * array for use
     * @param data to be sorted
     */
    public TwoWayMergeSort(int[] data){
        comparisons = exchanges = 0;
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    /**
     * Runs the merge sort algorithm and measures the run time
     */
    public void twoWayMergeSort(){
        long start = System.nanoTime();

        MergeSort(data, 0, data.length-1);

        long end = System.nanoTime();

        runTime = end - start;
    }

/**
 * This method is the merge function that works very similarly to the
 * two way merge function except accounts for 1 less max index by using
 * the ">" instead of the ">=" when comparing rightPos and leftPos to
 * the max indices of each list to merge. Instead of copying everything to
 * an destination array argument like in the natural merge this function
 * uses the member data variable.
 * @param data source data
 * @param leftFirst first index for the first array
 * @param leftLast last index for the first array
 * @param rightLast last index for the last array
 */
    private void Merge(int[] data, int leftFirst, int leftLast,
                       int rightLast){
        int mergedSize = rightLast - leftFirst + 1;

        int[] mergedNumbers = new int[mergedSize];

        int leftPos = 0, rightPos = 0, mergePos = 0;

        leftPos = leftFirst; // left partition position
        rightPos = leftLast + 1; // right partition position

        // While there are still elements in both partitions, this while
        // loop compares each element in each partition position to each
        // other, adds the smaller element to the mergedNumbers list,
        // increments the smaller element list's position, then continues
        while (leftPos <= leftLast && rightPos <= rightLast){
            if (data[leftPos] <= data[rightPos]){
                mergedNumbers[mergePos] = data[leftPos];
                leftPos++;
            }
            else {
                mergedNumbers[mergePos] = data[rightPos];
                rightPos++;
            }
            comparisons++;
            exchanges++;
            mergePos++;
        }

        // if the right partition is completely empty, we copy all of the
        // elements from the left partition into the rest of the merged array
        while (leftPos <= leftLast){
            mergedNumbers[mergePos] = data[leftPos];
            leftPos++;
            mergePos++;
            exchanges++;
        }

        // if the left partition is completely empty, we copy all of the
        // elements from the right partition into the rest of the merged array
        while (rightPos <= rightLast){
            mergedNumbers[mergePos] = data[rightPos];
            rightPos++;
            mergePos++;
            exchanges++;
        }

        // this is for copying all of the elements from the merged
        // partitions into the original member array
        for (mergePos = 0; mergePos < mergedSize; mergePos++){
            this.data[leftFirst + mergePos] = mergedNumbers[mergePos];
        }
    }

    /**
     * Uses the classic merge sort algorithm to subdivide the array into
     * branches of 2 until the base case is met and then merges all
     * branches together.
     * @param data source array
     * @param i low index
     * @param k high index
     */
    private void MergeSort(int[] data, int i, int k){
        int j;

        if (i < k){
            j = (i + k)/2;

            MergeSort(data, i, j);
            MergeSort(data, j+1, k);

            Merge(data, i, j, k);
        }
    }
}
