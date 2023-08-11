package Question5;
import java.util.function.Function;

public class HillClimbing {
    public static double hillClimbing(Function<Double, Double> function, double initial, double stepSize) {
        double current = initial;

        while (true) {
            double currentValue = function.apply(current);
            double nextValue = currentValue;
            double next = current;

            // Check neighbors to find the best move
            for (double neighbor : new double[]{current - stepSize, current + stepSize}) {
                double neighborValue = function.apply(neighbor);
                if (neighborValue > nextValue) {
                    nextValue = neighborValue;
                    next = neighbor;
                }
            }

            // Check if the best move improves the current value
            if (nextValue > currentValue) {
                current = next;
            } else {
                return current;
            }
        }
    }

    public static void main(String[] args) {
        // Example usage: maximize the function f(x) = -x^2 + 2x + 3
        Function<Double, Double> function = x -> -x * x + 2 * x + 3;
        double initial = 0.0;
        double stepSize = 0.1;

        // Apply hill climbing algorithm
        double result = hillClimbing(function, initial, stepSize);
        System.out.println("Optimal solution: x = " + result);
        System.out.println("Optimal value: f(x) = " + function.apply(result));
    }
}
