package p3;

/**
 * Edge class represents the edge (connection between vertices) of weighted undirected graph. It consists of two coordinates X and Y.
 * @author Daria Melnyk
 */
public class Edge implements Comparable<Edge>{
    int weight; //weighted
    Vertex u; //origin
    Vertex v; //destination
    /**
     * Creates the Edge with the specified vertices and weight
     * @param o - first Vertex object
     * @param d - second Vertex object
     * @param w - weight of Edge
     */
    public Edge(Vertex o, Vertex d, int w){
        this.u = o;
        this.v = d;
        this.weight = w;
    }

    /**
     * Gets the first Vertex of Edge
     * @return the Edge's first Vertex
     */
    public Vertex getU(){ // returns the origin vertex
        return this.u;
    }
    /**
     * Gets the second Vertex of Edge
     * @return the Edge's second Vertex
     */
    public Vertex getV(){ // returns the destination vertex
        return this.v;
    }
    /**
     * Gets the weight of connection
     * @return the weight of Edge
     */
    public int getW(){ // returns the edge weight
        return this.weight;
    }
    /**
     * Compares two edges by its weight
     * @return 1 if the edge of first is bigger
     *         -1 if the edge of second is bigger
     *         0 if the edges are similar
     */
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.getW());
    }
}
