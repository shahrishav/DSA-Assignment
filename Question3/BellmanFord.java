package Question3;
import java.util.*;

public class BellmanFord {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int vertexCount, int source) {
        int[] distances = new int[vertexCount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Relax edges repeatedly |V-1| times
        for (int i = 1; i < vertexCount; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;
            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Negative cycle detected");
                return;
            }
        }

        // Print the shortest distances
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Shortest distance from source to vertex " + i + ": " + distances[i]);
        }
    }

    static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static class MaxHeapComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return b.distance - a.distance;
        }
    }

    public static void priorityQueueUsingMaxHeap(List<Edge>[] graph, int vertexCount, int source) {
        int[] distances = new int[vertexCount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        maxHeap.offer(new Node(source, 0));

        while (!maxHeap.isEmpty()) {
            Node node = maxHeap.poll();
            int u = node.vertex;
            int distance = node.distance;

            if (distance > distances[u]) {
                continue; // Skip outdated entries in the max heap
            }

            for (Edge edge : graph[u]) {
                int v = edge.destination;
                int weight = edge.weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    maxHeap.offer(new Node(v, distances[v]));
                }
            }
        }

        // Print the shortest distances
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Shortest distance from source to vertex " + i + ": " + distances[i]);
        }
    }

    public static void main(String[] args) {
        // Example graph for Bellman-Ford algorithm
        int vertexCount = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(2, 3, -4));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        // Run Bellman-Ford algorithm
        int source = 0;
        bellmanFord(edges, vertexCount, source);

        // Example graph for priority queue using max heap
        List<Edge>[] graph = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 6));
        graph[0].add(new Edge(0, 2, 7));
        graph[1].add(new Edge(1, 3, 5));
        graph[1].add(new Edge(1, 2, 8));
        graph[2].add(new Edge(2, 3, -4));
        graph[2].add(new Edge(2, 4, 9));
        graph[3].add(new Edge(3, 1, -2));
        graph[4].add(new Edge(4, 0, 2));
        graph[4].add(new Edge(4, 3, 7));

        // Run priority queue using max heap
        priorityQueueUsingMaxHeap(graph, vertexCount, source);
    }
}
