package Question5;
import java.util.*;

public class ReorientConnections {
    public static int reorientConnections(int n, int[][] connections) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the adjacency list representing the tree structure
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            adjacencyList.get(from).add(to);
        }

        int[] changes = new int[1];
        dfs(0, -1, adjacencyList, changes);

        return changes[0];
    }

    private static boolean dfs(int node, int parent, List<List<Integer>> adjacencyList, int[] changes) {
        boolean isServerZero = node == 0;

        for (int neighbor : adjacencyList.get(node)) {
            if (neighbor != parent) {
                boolean isNeighborServerZero = dfs(neighbor, node, adjacencyList, changes);

                // If the neighbor is not connected to server 0, reverse the edge
                if (!isNeighborServerZero) {
                    changes[0]++;
                    System.out.println("Change direction of edge: " + neighbor + " -> " + node);
                }

                // Propagate the server 0 connection information
                isServerZero = isServerZero || isNeighborServerZero;
            }
        }

        return isServerZero;
    }

    public static void main(String[] args) {
        // Example input
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        // Call the function to reorient the connections
        int changes = reorientConnections(n, connections);
        System.out.println("Number of changes needed: " + changes);
    }
}
