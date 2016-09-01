package lab3;
import java.util.ArrayList;
/**
 * Compute shortest paths in a graph.
 *
 * Your constructor should compute the actual shortest paths and
 * maintain all the information needed to reconstruct them.  The
 * returnPath() function should use this information to return the
 * appropriate path of edge ID's from the start to the given end.
 *
 * Note that the start and end ID's should be mapped to vertices using
 * the graph's get() function.
 */
class ShortestPaths {

    int Inf =(int)  java.lang.Double.POSITIVE_INFINITY;
    ArrayList<Node> vertexlist = new ArrayList<Node>();
    PriorityQueue<Node> priorqueue = new PriorityQueue<Node>();
    /**
     * Constructor
     */
    public ShortestPaths(Multigraph G, int startId) {
       // YOUR CODE HERE
        
        for (int i = 0; i< G.nVertices(); i++){
            Vertex v = G.get(i);
            Node nd = new Node();
            vertexlist.add(nd); //make a arraylist of nodes to modify
        }


        for (int i = 0; i< G.nVertices(); i++){
            Vertex v = G.get(i);

            if (v.id() == startId){
                Node nd = new Node(v);
                nd.distance = 0;
                nd.handle = priorqueue.insert(0, nd);
                int id = v.id();
                vertexlist.set(id, nd);
            } //set starting vertex
            
            else {
                Node nd = new Node(v);
                nd.handle = priorqueue.insert(Inf, nd);
                int id = v.id();
                vertexlist.set(id, nd);
                
            } // set other vertexes

        } //initialize each vertex by class Node


        while (priorqueue.isEmpty() == false){
            Node u = priorqueue.extractMin();

            if (u.distance != Inf){
            Vertex.EdgeIterator adjacent = u.vertex.adj(); //The relax step for each vertex in adjacent

            while (adjacent.hasNext()==true ){
                Edge e = adjacent.next();
                Node v = vertexlist.get(e.to().id());
                
                int cost= u.distance + e.weight();

                if ((v.handle.index == -1) && (cost < v.distance)) {
                    v.distance = cost;
                    v.edgeToHere = e.id();
                    v.predecessor = u;
                } // if v has been visited

                else if ((v.handle.index != -1) && priorqueue.decreaseKey(v.handle,cost)) {
                    v.distance =cost;
                    v.edgeToHere = e.id();
                    v.predecessor = u;
                } // if v hasn't been visited

            }

            }

        }
        
    }
    
    public class Node{
        public Handle handle;
        public Vertex vertex;
        public int distance; 
        public Node predecessor;  
        public int edgeToHere; 

        public Node(){      
        }

        public Node(Vertex v){
            predecessor = null;
            distance = Inf;
            vertex = v;
        }

    }

    /**
     * Calculates the list of edge ID's forming a shortest path from the start
     * vertex to the specified end vertex.
     *
     * @return the array
     */
    
    public int[] returnPath(int endId) { 
       // YOUR CODE HERE
        Node endVertex = vertexlist.get(endId);
        int i = 0;
        ArrayList<Integer> idqueue = new ArrayList<Integer>();
        while (endVertex.predecessor != null){
            idqueue.add(endVertex.edgeToHere);  
            endVertex = endVertex.predecessor;
            i++;
        }
        int path[] = new int[i];
        for (int j = i; j> 0; j--){
            path[j-1] = idqueue.get(i-j);
        }
        return path;
    }

}