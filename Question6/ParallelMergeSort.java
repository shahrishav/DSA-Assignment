package Question6;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMergeSort {
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static void parallelMergeSort(int[] array) {
        parallelMergeSort(array, 0, array.length - 1);
    }

    private static void parallelMergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Create a thread pool for parallel execution
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            // Sort the left and right subarrays in parallel
            executor.submit(() -> parallelMergeSort(array, left, mid));
            executor.submit(() -> parallelMergeSort(array, mid + 1, right));

            // Wait for all tasks to complete
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Merge the sorted subarrays
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // Merge the two subarrays into the temporary array
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // Copy the remaining elements from the left subarray, if any
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // Copy the remaining elements from the right subarray, if any
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // Copy the merged elements back to the original array
        System.arraycopy(temp, 0, array, left, temp.length);
    }

    public static void main(String[] args) {
        // Example input
        int[] array = {5, 3, 8, 6, 2, 7, 1, 4};

        System.out.println("Input array: " + Arrays.toString(array));

        // Apply parallel merge sort
        parallelMergeSort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
