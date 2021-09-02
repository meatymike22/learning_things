/**
 * @author Michael McElroy
 * Date: 4/27/2021
 * Description: Uses the natural merge sort algorithm to sort an array,
 * counts the amount of comparisons, exchanges, and measures the run time
 * of the sorting algorithm.
 */
public class NaturalMergeSort {
    int[] data;
    int comparisons, exchanges;
    long runTime;

    /**
     * Constructor for natural merge. Copies the argument into the member
     * array for use
     * @param data to be sorted
     */
    public NaturalMergeSort(int[] data){
        comparisons = exchanges = 0;
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    /**
     * Calls the sort function and measures the runtime of the algorithm
     */
    public void naturalMergeSort(){
        long start = System.nanoTime();

        sort(data);

        long end = System.nanoTime();

        runTime = end - start;
    }

    /**
     * This method counts the number of runs depending on the smaller items
     * of the original list, uses these runs as the partitions of the
     * algorithm and then updates each run after each merge.
     * @param data array source
     */
    public void sort(int[] data){

        int len = data.length;

        int[] tempArray = new int[len];
        int[] runs = new int[len+1];


        runs[0] = 0;
        int countRuns = 0;

        // identifying runs that need to be made based on which elements
        // are smaller
        for (int i = 1; i <= len; i++){
            if (i == len || data[i] < data[i-1]){
                if (i == len)runs[++countRuns] = len-1;
                else runs[++countRuns] = i;
            }
        }

        int[] first = data;
        int[] second = tempArray;

        // runs will continuously get smaller as the array continues to merge
        while (countRuns > 1) {
            int changedCountRuns = 0;

            for (int i = 0; i < countRuns - 1; i += 2) {
                Merge(first, runs[i], runs[i + 1], runs[i + 2], second);
                runs[changedCountRuns++] = runs[i];
            }

            if (countRuns % 2 == 1) {
                int lastRun = runs[countRuns - 1];
                System.arraycopy(first, lastRun, second, lastRun, len - lastRun);
                runs[changedCountRuns++] = lastRun;
            }

            runs[changedCountRuns] = len;
            countRuns = changedCountRuns;

            int[] temp = first;
            first = second;
            second = temp;

            if (first != data){
                System.arraycopy(first, 0, data, 0, len);
            }
        }

    }

    /**
     * This method is the merge function that works very similarly to the
     * two way merge function except accounts for 1 less max index by using
     * the ">" instead of the ">=" when comparing rightPos and leftPos to
     * the max indices of each lsit to merge
     * @param data source data
     * @param leftFirst first index for the first array
     * @param leftLast last index for the first array
     * @param rightLast last index for the last array
     * @param dest_data array that the sorted merges will be put into
     */
    public void Merge(int[] data, int leftFirst, int leftLast,
                      int rightLast, int[] dest_data){

        int leftPos = leftFirst;
        int rightPos = leftLast;
        int targetPos = leftFirst;

        // As long as both arrays contain elements continue to compare and
        // merge
        while (leftPos < leftLast && rightPos < rightLast) {
            // Which one is smaller?
            int leftValue = data[leftPos];
            int rightValue = data[rightPos];
            if (leftValue <= rightValue) {
                dest_data[targetPos++] = leftValue;
                leftPos++;
            } else {
                dest_data[targetPos++] = rightValue;
                rightPos++;
            }
            comparisons++;
            exchanges++;
        }

        // Copy the rest
        while (leftPos < leftLast) {
            dest_data[targetPos++] = data[leftPos++];
            exchanges++;
        }
        while (rightPos < rightLast) {
            dest_data[targetPos++] = data[rightPos++];
            exchanges++;
        }
    }
}
