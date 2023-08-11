package Question4;
import java.util.*;

public class TaskCompletionSteps {
    public static int minimumSteps(int N, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[N + 1];

        // Initialize adjacency list and in-degree array
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build the adjacency list and in-degree array based on prerequisites
        for (int[] prerequisite : prerequisites) {
            int taskX = prerequisite[0];
            int taskY = prerequisite[1];

            adjList.get(taskX).add(taskY);
            inDegree[taskY]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Add tasks with in-degree 0 to the queue as starting points
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all tasks in the current step
            for (int i = 0; i < size; i++) {
                int task = queue.poll();
                N--;

                // Decrement the in-degree of the dependent tasks
                for (int dependentTask : adjList.get(task)) {
                    inDegree[dependentTask]--;

                    // If the dependent task has no more prerequisites, add it to the queue
                    if (inDegree[dependentTask] == 0) {
                        queue.offer(dependentTask);
                    }
                }
            }

            steps++;
        }

        // If there are remaining tasks, it means there is a cycle in the prerequisites
        if (N > 0) {
            return -1;
        }

        return steps;
    }

    public static void main(String[] args) {
        // Example input
        int N = 3;
        int[][] prerequisites = {{1, 3}, {2, 3}};

        // Call the function to get the minimum number of steps needed to complete all tasks
        int minSteps = minimumSteps(N, prerequisites);
        System.out.println(minSteps);
    }
}
