package Question2;
import java.util.Arrays;

public class LongestSubsequence {
    public static int longestSubsequence(int[] nums, int k) {
        int n = nums.length;

        // Create an array to store the longest subsequence length ending at each index
        int[] dp = new int[n];

        // Initialize the longest subsequence length for each index as 1
        Arrays.fill(dp, 1);

        int longest = 1;

        // Traverse the array from left to right
        for (int i = 1; i < n; i++) {
            // Compare the current element with the previous elements
            for (int j = 0; j < i; j++) {
                // Check if the current element is smaller than the previous element
                // and the difference between them is at most k
                if (nums[i] < nums[j] && nums[j] - nums[i] <= k) {
                    // Update the longest subsequence length ending at index i
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the overall longest subsequence length
            longest = Math.max(longest, dp[i]);
        }

        return longest;
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15};
        int k = 3;

        // Call the function to get the length of the longest subsequence
        int length = longestSubsequence(nums, k);
        System.out.println(length);
    }
}
