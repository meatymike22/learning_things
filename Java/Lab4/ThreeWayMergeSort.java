/**
 * @author Michael McElroy
 * Date: 4/27/2021
 * Description: Uses the 3 way merge sort algorithm to sort an array, counts
 * the amount of comparisons, exchanges, and measures the run time of the
 * sorting algorithm.
 */
public class ThreeWayMergeSort {
    int[] data;
    int comparisons, exchanges;
    long runTime;

    /**
     * Constructor for 3 way merge. Copies the argument into the member
     * array for use
     * @param data to be sorted
     */
    public ThreeWayMergeSort(int[] data){
        comparisons = exchanges = 0;
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    /**
     * Runs the 3 way sort algorithm and measures the amount of time to sort
     * the array
     */
    public void threeWayMergeSort(){
        if (data == null) return;

        int[] dup_array = new int[data.length];

        // make a duplicate array
        for (int i = 0; i < data.length; i++){
            dup_array[i] = data[i];
        }

        long start = System.nanoTime();

        MergeSort(dup_array, 0, data.length, data);

        long end = System.nanoTime();

        runTime = end - start;

        // copy all sorted elements back into original array
        for (int i = 0; i < data.length; i++){
            data[i] = dup_array[i];
        }
    }

    /**
     * This is the algorithm that recursively calls itself 3 times to
     * simulate a 3 way merge
     * @param dup_data the array that the sorted data will go into
     * @param low current low index
     * @param high current high index
     * @param data the data source of the original array
     */
    public void MergeSort(int[] dup_data, int low, int high, int[] data){
        if (high - low <= 1) return;

        int mid1 = low + ((high - low)/3);
        int mid2 = low + ((high - low)/3)*2 + 1;

        MergeSort(data, low, mid1, dup_data);
        MergeSort(data, mid1, mid2, dup_data);
        MergeSort(data, mid2, high, dup_data);

        Merge(data, low, mid1, mid2, high, dup_data);
    }

    /**
     * This is the function that takes into 4 indices representing 3
     * partitions and merges each one into an array of 1 size
     * @param dup_data destination array
     * @param low 1 partition index
     * @param mid1 1-2 partition index
     * @param mid2 2-3 partition index
     * @param high 3-4 partition index
     * @param data array with the source data
     */
    public void Merge(int[] dup_data, int low, int mid1, int mid2,
                            int high, int[] data){

        int i = low;
        int j = mid1;
        int k = mid2;
        int dest_index = low;

        // each comparison will result in the smallest of all of the
        // current data sets to be placed into the current object's array
        while ((i < mid1) && (j < mid2) && (k < high)) {
            if (dup_data[i] < dup_data[j]) {
                if (dup_data[i] < dup_data[k]) data[dest_index++] = dup_data[i++];
                else data[dest_index++] = dup_data[k++];
            } else {
                if (dup_data[j] < dup_data[k]) data[dest_index++] = dup_data[j++];
                else data[dest_index++] = dup_data[k++];
            }
            exchanges++;
            comparisons+=2;
        }

        // in the case where the first partition is not fully empty
        while ((i < mid1) && (j < mid2))
        {
            if (dup_data[i] < dup_data[j])
                data[dest_index++] = dup_data[i++];
            else
                data[dest_index++] = dup_data[j++];

            comparisons++;
            exchanges++;
        }

        // in the case where the second partition is not fully empty
        while ((j < mid2) && (k < high))
        {
            if (dup_data[j] < dup_data[k])
                data[dest_index++] = dup_data[j++];
            else
                data[dest_index++] = dup_data[k++];

            comparisons++;
            exchanges++;
        }

        // in the case where the third partition is not fully empty
        while ((i < mid1) && (k < high))
        {
            if (dup_data[i] < dup_data[k])
                data[dest_index++] = dup_data[i++];
            else
                data[dest_index++] = dup_data[k++];

            comparisons++;
            exchanges++;
        }

        // each of the 3 remaining arrays if the elements haven't all been
        // put back into the destination array
        while (i < mid1){
            data[dest_index++] = dup_data[i++];
            exchanges++;
        }

        while (j < mid2) {
            data[dest_index++] = dup_data[j++];
            exchanges++;
        }

        while (k < high) {
            data[dest_index++] = dup_data[k++];
            exchanges++;
        }

    }
}
