import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TestGraph{
  private static Graph<String, Integer> testingGraph;


  public static void main(String[] args) {
    testingGraph = new Graph<String, Integer>();

    Graph<String, Integer>.Node L1 = testingGraph.addNode("Location 1");
    Graph<String, Integer>.Node L2 = testingGraph.addNode("Location 2");
    Graph<String, Integer>.Node L3 = testingGraph.addNode("Location 3");
    Graph<String, Integer>.Node L4 = testingGraph.addNode("Location 4");
    Graph<String, Integer>.Node L5 = testingGraph.addNode("Location 5");

    Graph<String, Integer>.Edge edge1 = testingGraph.addEdge(10, L1, L2);
    Graph<String, Integer>.Edge edge2 = testingGraph.addEdge(3, L2, L3);
    Graph<String, Integer>.Edge edge3 = testingGraph.addEdge(6, L3, L4);
    Graph<String, Integer>.Edge edge4 = testingGraph.addEdge(12, L2, L4);
    Graph<String, Integer>.Edge edge5 = testingGraph.addEdge(7, L4, L5);
    testingGraph.print();
    System.out.println("Number of edges:" + testingGraph.numEdges());
    System.out.println("Number of nodes:" + testingGraph.numNodes());
    System.out.println("");


    //Testing Block 3: removing via method 1
    // System.out.println("Removing some edges...")
    // testingGraph.removeEdge(edge2);
    // testingGraph.removeEdge(edge3);
    // System.out.println("Number of edges:" + testingGraph.numEdges());
    // System.out.println("Number of nodes:" + testingGraph.numNodes());
    // testingGraph.print();

    //Testing Block 4: removing via method 2
    // testingGraph.removeEdge(L1,L2);
    // System.out.println("Number of edges:" + testingGraph.numEdges());
    // System.out.println("Number of nodes:" + testingGraph.numNodes());
    // testingGraph.print();
    //
    // System.out.println("");
    // System.out.println("Removing some nodes...");
    // testingGraph.removeNode(L5);
    // System.out.println("Number of edges:" + testingGraph.numEdges());
    // System.out.println("Number of nodes:" + testingGraph.numNodes());
    // testingGraph.print();

    //Testing Block 5: BFT
    System.out.println("Using BFT:");
    testingGraph.BFT(L1);
    testingGraph.print();
    System.out.println("");

    //Testing Block 6: DFT
    System.out.println("Using DFT:");
    testingGraph.DFT(L3);
    testingGraph.print();

    //Testing Block: shortestPath
    testingGraph.shortestPath(L1);
   //TestGraph.getShortestDistanceNode(notVisited);
   //testingEdgeGraph.print();
   //TestGraph.shortestPath(L1);


    //Testing Block 7: otherNodes
    // System.out.println("using otherNodes");
    // HashSet<Graph<String, Integer>.Node> others = new HashSet<Graph<String, Integer>.Node>();
    // others.add(L1);
    // others.add(L3);
    // others.add(L5);
    // testingGraph.otherNodes(others);
    // testingGraph.print();

  //  testingGraph.check();

  }

}
