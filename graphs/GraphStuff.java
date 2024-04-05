package graphs;

import java.util.List;

import weighted.WAdjacencyGraph;
import weighted.WPath;

public class GraphStuff {

    public static void paths() {
        AdjacencyGraph<String> graph = new AdjacencyGraph<>();
        graph.add("C");
        graph.add("A");
        graph.add("U");
        graph.add("T");
        graph.add("I");
        graph.add("O");
        graph.add("N");
        graph.add("E");
        graph.add("D");
        graph.add("S");
    

        graph.connectDirected("C", "A");
        graph.connectDirected("A", "U");
        graph.connectDirected("U", "T");
        graph.connectDirected("T", "I");
        graph.connectDirected("I", "O");
        graph.connectDirected("O", "N");
        graph.connectDirected("N", "E");
        graph.connectDirected("E", "D");
    

        graph.connectDirected("A", "U");  
        graph.connectDirected("U", "C");
        graph.connectDirected("C", "T");
        graph.connectDirected("T", "I");
        graph.connectDirected("I", "O");
        graph.connectDirected("O", "N");
        graph.connectDirected("N", "D");
        graph.connectDirected("D", "E");
        graph.connectDirected("E", "S");
    
        System.out.println("Graph:");
        System.out.println(graph.toString());
    

        List<String> cautionPath = graph.bfPath("C", "D");
        List<String> auctionPath = graph.bfPath("A", "D");
    
        System.out.println("\nPath from C to D (CAUTIONED): " + cautionPath);
        System.out.println("\nPath from A to D (AUCTIONED): " + auctionPath);
      }


    public static WPath<String> bobsBurgers() {
    WAdjacencyGraph<String> graph = new WAdjacencyGraph<>();
    graph.add("H");
    graph.add("A");
    graph.add("M");
    graph.add("B");
    graph.add("U");
    graph.add("R");
    graph.add("G");
    graph.add("E");
    graph.add("I");

    // Connect with weights (avoiding duplicates)
    graph.connect("H", "A", 8);
    graph.connect("A", "M", 11);
    graph.connect("M", "B", 3);
    graph.connect("B", "U", 4);
    graph.connect("U", "R", 1);
    graph.connect("R", "G", 7);
    graph.connect("G", "E", 9);
    graph.connect("E", "I", 2);
    graph.connect("I", "H", 5);

    // Search for path from H to R (spells HAMBURGER)
    return graph.dijkstrasPath("H", "R");
    }



    



    
    
    public static void main(String[] args) {
      AdjacencyGraph<String> graph = new AdjacencyGraph<>();
      graph.add("A");
      graph.add("B");
      graph.add("C");
      graph.add("D");
      graph.add("E");
      graph.add("F");
  
      graph.connectUndirected("A", "B");
      graph.connectUndirected("A", "C");
      graph.connectUndirected("B", "E");
      graph.connectUndirected("C", "E");
      graph.connectUndirected("C", "F");
      graph.connectUndirected("D", "F");
      graph.connectUndirected("E", "F");
  
      System.out.println(graph.toString());

      paths();

      bobsBurgers();
    }




}


  
  