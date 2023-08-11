package Question1;
public class MinimumCostClothing {
    public static int getMinimumCost(int N, int[][] price) {
        // Initialize the minimum cost variables for each person
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;

        // Iterate over each clothing set
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                // Compare the price of the clothing set with the current minimum cost for each person
                if (j == 0) {
                    min1 = Math.min(min1, price[i][j]);
                } else if (j == 1) {
                    min2 = Math.min(min2, price[i][j]);
                } else {
                    min3 = Math.min(min3, price[i][j]);
                }
            }
        }

        // Return the total minimum cost
        return min1 + min2 + min3;
    }

    public static void main(String[] args) {
        // Example input
        int[][] price = {{14, 4, 11}, {11, 14, 3}, {14, 2, 10}};
        int N = 3;

        // Call the function to get the minimum cost
        int minimumCost = getMinimumCost(N, price);
        System.out.println(minimumCost);
    }
}
