/*

 CPCS-324: Algorithms and Data Structure II
 Group Project part 2 - DAR section

 Nouf Alshawoosh - 2105927 - nibrahimalshawoosh@stu.kau.edu.sa
 Reema Almalki - 2005477 - rsalemalmalki@stu.kau.edu.sa
 Jana Faisal - 2006359 - jsaadaltalhi@stu.kau.edu.sa

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CPCS324_Project_phase2 {

    public static void main(String[] args) throws FileNotFoundException {
        // create a File object for input1.txt and input2.txt
        File input1 = new File("input1.txt");
        File input2 = new File("input2.txt");
        
        // declare and create two Scanner objects for each input file and one for reading from the console
        Scanner userInput = new Scanner(System.in);
        Scanner read_input1 = new Scanner(input1);
        Scanner read_input2 = new Scanner(input2);
        
        int userChoice;
        // ask the user to choose from the menu
        do{
            System.out.println("--------------Welcome to CPCS-324 Algorithm Implementer--------------");
            System.out.println("Please Choose from the following menu");
            System.out.println("1. Finding minimum spanning tree using Prim's algorithm\n" +
                               "2. Finding minimum spanning tree using Kruskal's algorithm\n" +
                               "3. Finding shortest path using Dijkstra's algorithm\n" +
                               "4. Quit");
            
            read_input1 = new Scanner(input1);
            read_input2 = new Scanner(input2);
            
            System.out.print("\nEnter your choice: ");
            userChoice = userInput.nextInt();
            System.out.println("");
            
            // switch statement
            switch(userChoice){
                case 1:
                    
                    // read the numebr of vertices from the file
                    int numberOfVertices = read_input1.nextInt();
                    
                    // create a graph with the specified number of vertices
                    PrimAlgorithm.Graph graph = new PrimAlgorithm.Graph(numberOfVertices);
                    
                    // read the file and add the vertices
                    int numberOfEdges = read_input1.nextInt();
                    for(int i = 0; i < numberOfEdges; i++){
                        graph.addEdge(read_input1.nextInt(), read_input1.nextInt(), read_input1.nextInt());
                    }
                    
                    // call the primMST to find the minimum spanning tree
                    PrimAlgorithm.primMST(graph);
                    break;
                             
                    
                case 2:
                    // read the number of vertices from the file
                    numberOfVertices = read_input1.nextInt();

                    // create a graph with the specified # of vertices
                    KruskalAlgorithm.Graph kruskalGraph = new KruskalAlgorithm.Graph(numberOfVertices);

                    // read the file and add the vertices
                    numberOfEdges = read_input1.nextInt();
                    for(int i = 0; i < numberOfEdges; i++){
                        kruskalGraph.addEdge(read_input1.nextInt(), read_input1.nextInt(), read_input1.nextInt());
                        }

                    // call the kruskalMST to find the minimum spanningtree
                    KruskalAlgorithm.kruskalMST(kruskalGraph);
                    break;
                    
                    
                case 3:
                    // Read the number of vertices and edges
                    numberOfVertices = read_input2.nextInt();
                    numberOfEdges = read_input2.nextInt();
                    
                    // Create a Dijkstra object with the specified number of vertices
                    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(numberOfVertices);
                    
                    // Read edges from the input file and add them to the graph
                    for (int i = 0; i < numberOfEdges; i++) {
                        int source = read_input2.nextInt();
                        int target = read_input2.nextInt();
                        int weight = read_input2.nextInt();
                        dijkstra.addEdge(source, target, weight);
                    }
                    
                    // Print weight matrix and adjacency list
                    dijkstra.printWeightMatrix();
                    dijkstra.printAdjList();
                    
                    // Get the source vertex from the user
                    System.out.print("Enter Source vertex: ");
                    int sourceVertex = userInput.nextInt();
                    
                    // Run Dijkstra's algorithm
                    int[] distances = dijkstra.dijkstra(sourceVertex);
                    
                    // Print shortest paths
                    dijkstra.printShortestPaths(sourceVertex, distances);
                    
                    // Print priority queue time
                    dijkstra.printPriorityQueueTime();
                    break;
                    
                    
                case 4:
                    break;
            }
            
            System.out.println("");
        }while(userChoice != 4);

        System.out.println("Bye Bye ;) ");
        userInput.close();
        read_input1.close();
        read_input2.close();
    }
    
}
