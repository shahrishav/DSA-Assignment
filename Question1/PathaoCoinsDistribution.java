package Question1;
import java.util.Arrays;

public class PathaoCoinsDistribution {
    public static int getMinimumCoins(int[] ratings) {
        int n = ratings.length;

        // Create an array to store the number of coins for each rider
        int[] coins = new int[n];

        // Initialize the number of coins for each rider as 1
        Arrays.fill(coins, 1);

        // Traverse from left to right and update the number of coins based on the ratings
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // If the current rider has a higher rating than the previous rider, assign one more coin
                coins[i] = coins[i - 1] + 1;
            }
        }

        // Traverse from right to left and update the number of coins based on the ratings
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // If the current rider has a higher rating than the next rider, assign more coins if needed
                coins[i] = Math.max(coins[i], coins[i + 1] + 1);
            }
        }

        // Calculate the total number of coins required
        int totalCoins = 0;
        for (int coin : coins) {
            totalCoins += coin;
        }

        return totalCoins;
    }

    public static void main(String[] args) {
        //  input
        int[] ratings = {1, 0, 2};

        // Call the function to get the minimum number of coins required
        int minimumCoins = getMinimumCoins(ratings);
        System.out.println(minimumCoins);
    }
}
