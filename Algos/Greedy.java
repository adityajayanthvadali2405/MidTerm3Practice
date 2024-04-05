package Algos;

import java.util.Arrays;

public class Greedy {

    public static int[] maximizeArraySum(int[] array, int k) {

        // Pseudocode:
        // 1. Sort the array in ascending order
        // 2. Iterate through the first k elements of the sorted array
        //    a. Negate the current element
        //    b. Add the negated value to the sum
        // 3. Return the array with the negated elements

        // Implementation:
        Arrays.sort(array);  // Sort in ascending order

        int sum = 0;
        for (int i = 0; i < k; i++) {
            array[i] = -array[i];  // Negate the element
            sum += array[i];       // Add the negated value to the sum
        }

        // Analysis:
        // Time complexity: O(n log n) due to sorting
        // Optimal solution: Yes, in this case, the greedy approach guarantees optimality
        // Better solution: No, in terms of time complexity, this is optimal for this problem

        return array;
    }
}
