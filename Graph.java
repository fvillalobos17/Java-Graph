import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Graph<V,E> {
  public E data;
  public LinkedList<Edge> edge; // master list of edges
  public LinkedList<Node> nodes; //master list of Nodes

  public Graph(){
    edge = new LinkedList<Edge>();
    nodes = new LinkedList<Node>();
  }
  /**
  *Edge addEdge()
  *@param E data,
  *@param Node head,
  *@param Node tail
  *this method adds an edge to the graph by updating the master
  *list of edges as well as the edge lists of the head and tail
  *returns a new edge
  */
  public Edge addEdge(E data, Node head, Node tail){
    Edge anotherEdge = new Edge(data, head, tail);
    anotherEdge.setData(data);
    edge.add(anotherEdge);
    Node addH = anotherEdge.getHead();
    Node addT = anotherEdge.getTail();
    addH.addEdgeRef(anotherEdge);
    addT.addEdgeRef(anotherEdge);
    return anotherEdge;
  }
  /**
  *Node addNode()
  *@param V data,
  *this method adds a node to the graph by updating the master
  *list of nodes
  */
  public Node addNode(V data){
    Node anotherNode = new Node(data);
    anotherNode.setData(data);
    nodes.add(anotherNode);
    return anotherNode;
  }
  /**
  *Edge getEdge()
  *@param int i
  *Accessor method for an edge
  * returns the egdge requested
  */
  public Edge getEdge(int i){
    return edge.get(i);
  }
  /**
  *Node getNode()
  *@param int i
  *Accessor method for a node
  *returns the requested node
  */
  public Node getNode(int i){
    return nodes.get(i);
  }
  public int numEdges(){
    return edge.size();
  }
  public int numNodes(){
    return nodes.size();
  }
  public Edge getEdgeRef(Node head, Node tail){
    //Edge result = null;
    for(Edge e: edge){
      Node eHead = e.getHead();
      Node eTail = e.getTail();

      if(eHead.equals(head) && eTail.equals(tail) && eHead.equals(tail) && eTail.equals(head)){
        return e;
      }
    }
    return null;
  }

  /**
  *Edge removeEdge()
  *@param Edge edge
  *this method removes an edge from the graph by updating the master
  *list of edges and removing the edge from all of the nodes it
  *associated with
  */
  public void removeEdge(Edge edge){
    Node removingH = edge.getHead();
    Node removingT = edge.getTail();
    removingH.removeEdgeRef(edge);
    removingT.removeEdgeRef(edge);
    this.edge.remove(edge);
  }

  public void removeEdge(Node head, Node tail){
    Edge edgetoRemove = this.getEdgeRef(head, tail);
    edge.remove(edgetoRemove); //remove this from master list
    Node getH = edgetoRemove.getHead();
    Node getT = edgetoRemove.getTail();
    //remove edge from mini lists of the head and tail
    getH.removeEdgeRef(edgetoRemove);
    getT.removeEdgeRef(edgetoRemove);
  }
  /**
  *void removeNode()
  *@param Node node
  * removes given node from master list
  */
  public void removeNode(Node node){
    this.nodes.remove(node);
  }

  public void check(){
    //check that every edge has a head and an attached tail
    for(Edge f:edge){
      System.out.println("made it into check first for");
      Node isHead = f.getHead();
      Node isTail = f.getTail();
      for(Node d: nodes){
        System.out.println(d.getData());
        Edge checkingEH = isHead.edgeTo(d);
        Edge checkingET = isTail.edgeTo(d);
        System.out.println("H: " + checkingEH.getData());
        System.out.println("T: " + checkingET.getData());
        // if(edge.contains(){
        //   //do stuff?
        //   System.out.println("Consistent!");
        // }

      }
    }
  }

  public java.util.HashSet<Node> otherNodes(HashSet<Node> group){
    HashSet<Node> diff = new HashSet<Node>();
    for(Node x:nodes){
        if(!group.contains(x)){
          System.out.println("adding to diffList:" + x.getData());
          diff.add(x);
        }
    }
    return diff;
  }
  // public java.util.HashSet<Node> endpoints(HashSet<Edge> edges){
  //   //returns nodes that are endpoints of a list of edges
  //   //edgeRef
  // HELP
  // }

  //second BFT after suggestion
  public LinkedList<Node> BFT(Node start){
    LinkedList<Node> seen = new LinkedList<Node>();
    Queue<Node> qBFT = new LinkedList<Node>();
    //mark current node as seen & add to the queue
    qBFT.add(start);
    seen.add(start);
    while(qBFT.size() != 0){
      Node testNode = qBFT.poll();
      //System.out.println("node from q:" + testNode.getData());
      LinkedList<Node> testNeighbors = testNode.getNeighbors();
      for(Node x : testNeighbors){
        if(!seen.contains(x)){
          //System.out.println("not been seen yet?" + x.getData());
          qBFT.add(x);
          seen.add(x);
          for(Node s : seen){
          //System.out.println("seen list:" + s.getData());
          }
        }
      }
    }
    System.out.println("BFT result:");
    for(Node s: seen){
      System.out.println(s.getData());
    }
    return seen;
  }

  public LinkedList<Node> DFT(Node start){
    LinkedList<Node> visitedDFT = new LinkedList<Node>();
    DFTUtil(start, visitedDFT);
    System.out.println("DFT result:");
    for(Node d: visitedDFT){
      System.out.println(d.getData());
    }
    System.out.println("");
    return visitedDFT;
  }
 private void DFTUtil(Node start, LinkedList<Node> visitedDFT){
   //System.out.println("Start:" + start.getData());
   visitedDFT.add(start);
   LinkedList<Node> dftList = start.getNeighbors();
   System.out.println("visitedDFT:" + visitedDFT.size());
   System.out.println("DFTlist:" + dftList.size());
   System.out.println("");
   for(Node y: dftList){
     if(!visitedDFT.contains(y)){
        DFTUtil(y, visitedDFT);
   }
 }
}
 public LinkedList<Edge> getEdges(Node test){
   LinkedList<Edge> nodesEdges = new LinkedList<Edge>();
   for(Node g : nodes){
     Edge edgeofNode = test.edgeTo(g);
     if(edgeofNode != null){
       //System.out.println("adding to edges:" + edgeofNode.getData());
      nodesEdges.add(edgeofNode);

     }
   }

   return nodesEdges;
 }
public HashSet<Node> shortestPath(Node start){
  HashSet<Integer> costs = new HashSet<Integer>();
  HashSet<Node> visited = new HashSet<>(); //node and node for getE
  HashSet<Node> notVisited = new HashSet<>();
  HashMap<Node, Integer> costFromStart = new HashMap<Node, Integer>();
  //initialize to infinity
  for(Node init : nodes){
    notVisited.add(init);
    costFromStart.put(init, Integer.MAX_VALUE);

  }
  //make the start cost 0
  costFromStart.put(start, 0);
  notVisited.add(start);

  //while not visited is not empty
  while(notVisited.size() != 0){
    //find node with smallest cost
    Node shortestNode = getShortestDistanceNode(costFromStart);
  //  System.out.println("shortestNode:" + shortestNode.getData());
    //mark node as visited
    visited.add(shortestNode);

  //  costs.add(shortestNode.edgeTo(start).getData());
    System.out.println("updating cost" + costs);
    //take node with smallest cost out
    notVisited.remove(shortestNode);
    //get neighbors
    LinkedList<Node> unvisitedNeigh = shortestNode.getNeighbors();
    //for all unvisited neighbors of node- loop and make sure unvisted
    for(Node un : unvisitedNeigh){
      if(!visited.contains(un)){
      //see if it is possible to reduce cost from start to neighbor using the current node
      // System.out.println("Node tests before first call");
      // System.out.println("start:" + start.getData()   +   "unvisitedNeigh:" + un.getData());
      // System.out.println("shortest:" + shortestNode.getData());
      int firstMethod = checkCost(start, un);
    //  System.out.println("");

      // System.out.println("Node tests after first call");
      // System.out.println("start:" + start.getData()   +   "unvisitedNeigh:" + un.getData());
      // System.out.println("shortest:" + shortestNode.getData());
      // int secondMethod = checkCost(start, shortestNode) + checkCost(shortestNode, un);

      // System.out.println("first:"  +  firstMethod);
      // System.out.println("second:" + secondMethod);
      }
    }
  }
  return visited;
}
  public int checkCost(Node a, Node b){
    System.out.println("checkCost was called! ");
    int costOf = 0;
    if(a.isNeighbor(b) == true){
      Edge gettingE = getEdgeRef(a,b);
      System.out.println(gettingE);
      //System.out.println("edge data:" +  gettingE.getData());
      costOf = (Integer) gettingE.getData();
      System.out.println("cost:"  +  costOf);
    }
    return costOf;
  }


public Node getShortestDistanceNode(HashMap<Node, Integer> costFromStart){
  Node shortestDistanceNode = null;
  int shortestDistance = Integer.MAX_VALUE;
  for(Node node : costFromStart.keySet()){
    int eDistance = costFromStart.get(node);
      if(eDistance < shortestDistance){
        shortestDistanceNode = node;
        shortestDistance = eDistance;
      }
    }
    //System.out.println("shortestD:" + shortestDistanceNode.getData());
    return shortestDistanceNode;
  }



  /**
  *void print()
  *this method prints the nodes and edges as well as all of the
  *data in them and displays them for the user
  */
  public void print(){
    //iterate over linked list
    System.out.println("How many Edges:" + edge.size());
    for(int j = 0; j < edge.size(); j++){
      Edge printEdge = edge.get(j);
      System.out.println("Head:" + printEdge.getHead().getData());
      System.out.println("Tail:" + printEdge.getTail().getData());
      System.out.println("Connected by:" + printEdge.getData());
      System.out.println("");
    }
    System.out.println("How many nodes:" + nodes.size());
    for(int k = 0; k < nodes.size(); k++){
      Node printNode = nodes.get(k);
    //  System.out.println("Neighbors:" + printNode.getNeighbors());
      System.out.println("Data:" + printNode.getData());
    }

  }
  public class Edge{
    private E data;
    private Node head;
    private Node tail;

    public Edge(E data, Node head, Node tail){
      this.data = data;
      this.head = head;
      this.tail = tail;
    }
    /**
    *boolean equals()
    *@param Object o
    *this method overrides the standard .equals method from java.lang
    *it compares if the head = tail and tail = head or vice versa (since this is an
    * undirected graph)
    *it basically rewrites what it means for two edges to be equal.
    */
    public boolean equals(Object o){
      boolean result = false;
      if(getClass() == o.getClass()){
        @SuppressWarnings("unchecked")
        Edge edgeIn = (Edge) o;
        if((this.getHead() == edgeIn.getTail() && this.getTail() == edgeIn.getHead()) || (this.getHead() == edgeIn.getHead() && this.getTail() == edgeIn.getTail())){
          result = true;
        }
      }
      return result;
    }
    /**
    *E getData()
    *Accessor method for the edge data
    */
    public E getData(){
    //  System.out.println("getting data from edge");
      this.data = data;
      return data;
    }
    /**
    *Node getHead()
    *Accessor method for the node head of an edge
    */
    public Node getHead(){
      this.head = head;
      return head;
    }
    /**
    *Node getTail
    *Accessor method for the node tail of an edge
    */
    public Node getTail(){
      this.tail = tail;
      return tail;
    }

    /**
    *Node oppositeTo()
    *@param Node node
    *returns the node opposite to the node given to the method, if given
    *node is a head, it returns the tail and vice versa
    */
    public Node oppositeTo(Node node){
      if(node.equals(head)){
        return this.tail;
      }
      else{
        return this.head;
      }
    }
    /**
    *void setData()
    *@param E data
    *Manipulator method for the data of an edge
    */
    public void setData(E data){
      this.data = data;
    }
  }//closes graphedge
  public class Node{
    private V data;
    private LinkedList<Edge> miniEdges;
    public Node(V data){
      this.data = data;
      miniEdges = new LinkedList<Edge>();
    }
    /**
    *V getData()
    *Accessor method for the data of a node
    */
    public V getData(){
      return data;
    }
    /**
    *void setData()
    *@param V data
    *Manipulator method for the data of a node
    */
    public void setData(V data){
      this.data = data;
    }
    /**
    * void removeEdgeRef()
    *@param Edge edge
    *removes the instance of a given edge from the list of
    *edges of the node it is called on
    */
    public void removeEdgeRef(Edge edge){
      for(int z = 0; z < miniEdges.size(); z++){
        Edge miniER = miniEdges.get(z);
        if(miniER.equals(edge)){
          miniEdges.remove(miniER);
        }
      }
    }
    /**
    * void addEdgeRef()
    *@param Edge edge
    *adds an instance of a given edge from the list of
    *edges of the node it is called on
    */
    public void addEdgeRef(Edge edge){
      miniEdges.add(edge);
    }
    /**
    * Edge edgeTo()
    *@param Node neighbor
    *uses a nodes list of associated edges to determine if
    *there exists an edge from current node to given node
    */
    public Edge edgeTo(Node neighbor){
    //  System.out.println("inside edgeTo");
      for(int a = 0; a < miniEdges.size(); a++){
        Edge isEdgeTo = miniEdges.get(a);
        if(isEdgeTo.getHead().equals(neighbor) || isEdgeTo.getTail().equals(neighbor)){
          return isEdgeTo;
        }
      }
      return null;
    }
    /**
    * boolean isNeighbor()
    *@param Node g
    *uses the edge to method to see if there is an edge between current
    *node and given node, since this is an undirected graph, only one
    *direction check needed
    */
    public boolean isNeighbor(Node g){
      boolean neighbor = false;
      if(this.edgeTo(g) != null){
        neighbor = true;
        return neighbor;
      }
      else{
        return neighbor;
      }
    }
    /**
    * LinkedList<Node> getNeighbors()
    *uses the adds the nodes from the edge list of the current node
    *to a linked list of neighbors for given node.
    */
    public LinkedList<Node> getNeighbors(){
      //System.out.println("inside getNeighbors");
      LinkedList<Node> listofN = new LinkedList<Node>();
      for(Edge e : miniEdges){
        //System.out.println("inside the for loop");
        Node neigh = e.oppositeTo(this);
        System.out.println(neigh.getData());
        listofN.add(neigh);
      }
      return listofN;
    }

  }//close node

}//close graph
