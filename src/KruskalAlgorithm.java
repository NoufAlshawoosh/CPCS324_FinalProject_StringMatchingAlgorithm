/*

 CPCS-324: Algorithms and Data Structure II
 Group Project part 2 - DAR section

 Nouf Alshawoosh - 2105927 - nibrahimalshawoosh@stu.kau.edu.sa
 Reema Almalki - 2005477 - rsalemalmalki@stu.kau.edu.sa
 Jana Faisal - 2006359 - jsaadaltalhi@stu.kau.edu.sa

 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class KruskalAlgorithm {
       public static class Edge {
        int src;
        int dest;
        double weight;

        public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static class Graph {
        int V;
        List<Edge> edges;

        public Graph(int V) {
            this.V = V;
            edges = new ArrayList<>();
        }

        public void addEdge(int src, int dest, double weight) {
            Edge edge = new Edge(src, dest, weight);
            edges.add(edge);
        }
    }

    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX] = rank[rootX] + 1;
            }
        }
    }

    //using Stack for storing the weights of the edge
    public static Stack<Double> kruskalMST(Graph graph) {
        long initialTime = System.nanoTime();
        int V = graph.V;
        List<Edge> edges = graph.edges;
        Stack<Double> weightStack = new Stack<>();

       
        edges.sort(Comparator.comparingDouble(edge -> edge.weight));

        UnionFind unionFind = new UnionFind(V);
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int srcRoot = unionFind.find(edge.src);
            int destRoot = unionFind.find(edge.dest);

            if (srcRoot != destRoot) {
                unionFind.union(srcRoot, destRoot);
                weightStack.push(edge.weight);
            }
        }

        long finalTime = System.nanoTime();
        printMST(weightStack, graph, (finalTime - initialTime));

        return weightStack;
    }

    //print output on screen
    private static void printMST(Stack<Double> weightStack, Graph graph, long time) {
    List<Double> weightList = new ArrayList<>();
    while (!weightStack.isEmpty()) {
        weightList.add(weightStack.pop());
    }
    double totalWeight = 0.0;
    System.out.println("The edges in the MST are:");
    for (int i = weightList.size() - 1; i >= 0; i--) {
        double weight = weightList.get(i);
        Edge edge = findEdgeByWeight(graph, weight);
        System.out.println("Edge from " +  edge.src  + " to " + edge.dest  + " has weight " + edge.weight);
        totalWeight += edge.weight;
    }

    System.out.println("\nTotal weight of MST by Kruskal's algorithm : " + totalWeight);
    System.out.println("Running Time of Kruskal's algorithm using Union-Find approach is " + time + " Nano seconds.");
}

    private static Edge findEdgeByWeight(Graph graph, double weight) {
        for (Edge edge : graph.edges) {
            if (edge.weight == weight) {
                return edge;
            }
        }
        // Handle the case when the edge is not found
        return null;
    }

}