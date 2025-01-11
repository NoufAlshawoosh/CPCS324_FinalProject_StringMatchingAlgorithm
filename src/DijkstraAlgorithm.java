/*

 CPCS-324: Algorithms and Data Structure II
 Group Project part 2 - DAR section

 Nouf Alshawoosh - 2105927 - nibrahimalshawoosh@stu.kau.edu.sa
 Reema Almalki - 2005477 - rsalemalmalki@stu.kau.edu.sa
 Jana Faisal - 2006359 - jsaadaltalhi@stu.kau.edu.sa

 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    private int V; // No. of vertices
    private List<List<Node>> adjList; // Adjacency List representation
    private int[][] weightMatrix; // Weight matrix representation
    private long priorityQueueTime; // Time taken by priority queue operations
    private int[] predecessor; // Array to store the path

    public static class Node implements Comparable<Node> {
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public DijkstraAlgorithm(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        weightMatrix = new int[V][V]; // Initialize weight matrix
        for (int i = 0; i < V; ++i) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Node(v, weight));
        weightMatrix[u][v] = weight; // Fill weight matrix
    }

    public int[] dijkstra(int src) {
        long startTime = System.nanoTime(); // Record the start time
        int[] distance = new int[V];
        boolean[] visited = new boolean[V];
        predecessor = new int[V]; // Initialize predecessor array
        PriorityQueue<Node> queue = new PriorityQueue<>(); // Priority queue for Dijkstra

        // Initialize distances, queue, and predecessor
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1; // No predecessor initially
        }
        distance[src] = 0;
        queue.offer(new Node(src, 0)); // Enqueue source node

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // Get node with minimum distance
            int u = node.v;
            if (visited[u]) continue; // Skip if already visited
            visited[u] = true;

            for (Node neighbor : adjList.get(u)) {
                if (!visited[neighbor.v]) {
                    int newDistance = distance[u] + neighbor.weight;
                    if (newDistance < distance[neighbor.v]) {
                        distance[neighbor.v] = newDistance;
                        predecessor[neighbor.v] = u; // Update predecessor
                        queue.offer(new Node(neighbor.v, newDistance)); // Enqueue updated node
                    }
                }
            }
        }
        long endTime = System.nanoTime(); // Record the end time
        priorityQueueTime = endTime - startTime; // Calculate the time taken by priority queue operations
        return distance;
    }

    // Method to print the weight matrix
    public void printWeightMatrix() {
        System.out.println("Weight Matrix:");
        System.out.print(" ");
        for (int i = 0; i < V; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.print(i);
            for (int j = 0; j < V; j++) {
                System.out.print(" " + weightMatrix[i][j]);
            }
            System.out.println();
        }
    }

    // Method to print the adjacency list
    public void printAdjList() {
        System.out.println("# of vertices is: " + V + ", # of edges is: " + countEdges());
        for (int i = 0; i < V; i++) {
            System.out.print(" " + i + ":");
            for (Node node : adjList.get(i)) {
                System.out.print(" " + i + "-" + node.v + " " + node.weight);
            }
            System.out.println();
        }
        
        System.out.println("");
    }

    // Method to count the edges
    private int countEdges() {
        int count = 0;
        for (List<Node> nodes : adjList) {
            count += nodes.size();
        }
        return count;
    }

    // Method to print the shortest paths from the source vertex
    public void printShortestPaths(int src, int[] distances) {
        System.out.println("Shortest paths from vertex " + src + " are:");
        for (int i = 0; i < V; i++) {
            if (i != src) {
                System.out.print("A path from " + src + " to " + i + ":");
                printPath(src, i);
                System.out.println(" (Length: " + distances[i] + ".0)");
            } else {
                System.out.println("A path from " + src + " to " + i + ": " + src + " (Length: 0.0)");
            }
        }
        
        System.out.println();
    }

    // Method to print the path from source to destination vertex
    private void printPath(int src, int dest) {
        if (predecessor[dest] == -1) {
            return; // No path exists
        }
        List<Integer> path = new ArrayList<>();
        int current = dest;
        while (current != -1 && current != src) {
            path.add(0, current);
            current = predecessor[current];
        }
        path.add(0, src); // Add the source at the beginning

        for (int vertex : path) {
            System.out.print(" " + vertex);
        }
    }

    // Method to print the time taken by priority queue operations
    public void printPriorityQueueTime() {
        System.out.println("Running time of Dijkstra using priority queue is: " + priorityQueueTime + " nano seconds");
    }
}
