/**
 * @author Michael McElroy
 * Date: 4/27/2021
 * Description: Uses the heap sort algorithm to sort an array, counts the
 * amount of comparisons, exchanges, and measures the run time of the
 * sorting algorithm.
 */
public class HeapSort {
    int[] data;
    int comparisons, exchanges;
    long runTime;

    /**
     * Constructor for heap sort. Copies the argument into the member
     * array for use
     * @param data to be sorted
     */
    public HeapSort(int[] data){
        comparisons = exchanges = 0;
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    /**
     * Runs the heap sort algorithm and measures the amount of time to sort
     * the array
     */
    public void heapsort(){
        int len = data.length;

        long start = System.nanoTime();

        // make the heap
        for (int i = len/2 - 1; i >= 0; i--){
            MaxHeapPercolateDown(i, data, len);
        }

        // swap items until we have a sorted array
        for (int i = len - 1; i > 0; i--){
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            exchanges++;
            MaxHeapPercolateDown(0, data, i);
        }

        long end = System.nanoTime();

        runTime = end - start;
    }

    /**
     * The algorithm that sets an item to the top of a heap and then
     * percolates it down to a leaf
     * @param nodeIndex current index of the node being percolated
     * @param heapArray the max heap array
     * @param arraySize size of current array
     */
    private void MaxHeapPercolateDown(int nodeIndex, int[] heapArray,
                                      int arraySize){
        int childIndex = nodeIndex*2 + 1;

        int value = heapArray[nodeIndex];

        // going down to the farthest leaf
        while (childIndex < arraySize){
            int maxVal = value;
            int maxIndex = -1;

            // finds the maximum of the current node and all of the node's
            // children
            for (int i = 0; i < 2 && (i + childIndex < arraySize); i++){
                if (heapArray[i + childIndex] > maxVal) {
                    comparisons++;
                    maxVal = heapArray[i + childIndex];
                    maxIndex = i + childIndex;
                }
            }

            if (maxVal == value) return;
            else {
                // swap index of the max index down to the next node and
                // update the node and childIndex
                int temp = heapArray[nodeIndex];
                heapArray[nodeIndex] = heapArray[maxIndex];
                heapArray[maxIndex] = temp;

                exchanges++;

                nodeIndex = maxIndex;
                childIndex = nodeIndex*2 + 1;
            }
        }
    }
}
