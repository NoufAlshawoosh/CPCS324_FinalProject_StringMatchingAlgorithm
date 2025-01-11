/*

 CPCS-324: Algorithms and Data Structure II
 Group Project part 2 - DAR section

 Nouf Alshawoosh - 2105927 - nibrahimalshawoosh@stu.kau.edu.sa
 Reema Almalki - 2005477 - rsalemalmalki@stu.kau.edu.sa
 Jana Faisal - 2006359 - jsaadaltalhi@stu.kau.edu.sa

 */

import java.util.*;

public class PrimAlgorithm {
    private static final int INF = Integer.MAX_VALUE;

    public static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static class Graph {
        int V;
        List<List<Edge>> adjList;

        public Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest, int weight) {
            adjList.get(src).add(new Edge(src, dest, weight));
            adjList.get(dest).add(new Edge(dest, src, weight));
        }
    }

    private static int extractMin(int[] key, boolean[] inMST, int V) {
        int minKey = INF;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!inMST[v] && key[v] < minKey) {
                minKey = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void primMST(Graph graph) {
        int V = graph.V;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];

        Arrays.fill(key, INF);
        Arrays.fill(parent, -1);

        key[0] = 0;  // Start from the first vertex

        for (int count = 0; count < V - 1; count++) {
            int u = extractMin(key, inMST, V);
            inMST[u] = true;

            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                }
            }
        }

        printMST(parent, key, graph);
    }

    private static void printMST(int[] parent, int[] key, Graph graph) {
        System.out.println("The edges in the MST are:");

        // Create a map to store edges for each source vertex
        Map<Integer, List<Edge>> edgesMap = new HashMap<>();
        for (int i = 1; i < graph.V; i++) {
            int src = parent[i];
            int dest = i;
            Edge edge = new Edge(src, dest, key[i]);
            edgesMap.computeIfAbsent(src, k -> new ArrayList<>()).add(edge);
        }

        // Sort edges based on the source vertices
        List<Integer> sortedSources = new ArrayList<>(edgesMap.keySet());
        Collections.sort(sortedSources);
        for (int src : sortedSources) {
            List<Edge> edges = edgesMap.get(src);
            for (Edge edge : edges) {
                System.out.println("Edge from " + edge.src + " to " + edge.dest + " has weight " + edge.weight + ".0");
            }
        }
    }

}
