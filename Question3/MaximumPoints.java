package Question3;
public class MaximumPoints {
    public static int getMaxPoints(int[] targets) {
        int n = targets.length;

        // Create a padded array with additional 1s at the beginning and end
        int[] paddedTargets = new int[n + 2];
        paddedTargets[0] = 1;
        paddedTargets[n + 1] = 1;
        System.arraycopy(targets, 0, paddedTargets, 1, n);

        // Create a 2D array to store the maximum points at each position
        int[][] dp = new int[n + 2][n + 2];

        // Iterate over the positions in the paddedTargets array
        for (int len = 1; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                // Iterate over each possible partition of the subarray
                for (int k = i; k <= j; k++) {
                    // Calculate the points for the current partition
                    int points = paddedTargets[i - 1] * paddedTargets[k] * paddedTargets[j + 1];
                    // Update the maximum points for the current subarray
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + points + dp[k + 1][j]);
                }
            }
        }

        // Return the maximum points for the entire range
        return dp[1][n];
    }

    public static void main(String[] args) {
        // Example input
        int[] targets = {3, 1, 5, 8};

        // Call the function to get the maximum points
        int maxPoints = getMaxPoints(targets);
        System.out.println(maxPoints);
    }
}
