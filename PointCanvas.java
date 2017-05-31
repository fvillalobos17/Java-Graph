import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *  Implements a graphical canvas that displays a list of points.
 *
 *  @author  Nicholas R. Howe
 *  @version CSC 212, 20 April 2017
 */
class PointCanvas extends JComponent {
    /** The points */
    LinkedList<Point> points;

    /** instance of the graph class*/
    Graph<Point,Integer> gGraph;



    /** Constructor */
    public PointCanvas() {
        points = new LinkedList<Point>();
        gGraph =  new Graph<Point,Integer>();

    }

    /**
     *  Paints a red circle ten pixels in diameter at each point.
     *
     *  @param g The graphics object to draw with
     */
    public void paintComponent(Graphics g){
      gGraph.print();
      System.out.println("inside paint component");

      if(gGraph.nodes.size() != 0){
        System.out.println("checking size");
      //  gGraph.print();

        for(int p = 0; p < gGraph.nodes.size(); p++){
          System.out.println("filling oval?");
          g.setColor(Color.red);
          System.out.println(gGraph.getNode(p).getClass());
          // if(gGraph.getNode(p).getClass() == Point()){
          g.fillOval((int)gGraph.getNode(p).getData().getX(),(int)gGraph.getNode(p).getData().getY(), 10, 10);

        }//closes for
      }//closes first if
    }

    /**
     *  The component will look bad if it is sized smaller than this
     *
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
        return new Dimension(500,300);
    }

    /**
     *  The component will look best at this size
     *
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
        return new Dimension(500,300);
    }
}
