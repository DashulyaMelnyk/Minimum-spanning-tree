package p3;

import java.util.*;


/**
 * Represents weighted undirected graph that consists of two sets - of vertices and of edges.
 * @author Daria Melnyk
 */
public class Graph {
    private HashSet<Vertex> setV;
    private HashSet<Edge> setE;
    /**
     * Creates the Graph with no vertices and edges
     */
    public Graph(){
        setV = new HashSet<Vertex>();
        setE = new HashSet<Edge>();
    }
    /**
     * Adds a new edge and its vertices to the graph if they weren't added earlier
     * @param ux - X coordinate of first Vertex
     * @param uy - Y coordinate of first Vertex
     * @param vx - X coordinate of second Vertex
     * @param vy - Y coordinate of second Vertex
     * @param w - weight of Edge
     * @throws IllegalArgumentException if such edge was added earlier
     */
    public void add(int ux, int uy, int vx, int vy, int w) {
        Vertex u = isVertex(ux, uy);
        Vertex v = isVertex(vx, vy);
        if(u == null) {
            u = new Vertex(ux, uy);
            setV.add(u);
        }
        if(v == null) {
            v = new Vertex(vx, vy);
            setV.add(v);
        }
        if (isEdge(ux, uy, vx, vy)) {
            throw new IllegalArgumentException();
        }
        setE.add(new Edge(u,v,w));
    }
    /**
     * Checks if the Edge with such vertices already exists in graph to avoid adding it multiple times
     * @param ux - X coordinate of first Vertex
     * @param uy - Y coordinate of first Vertex
     * @param vx - X coordinate of second Vertex
     * @param vy - Y coordinate of second Vertex
     * @return true if such Edge already exists in graph
     */
    private boolean isEdge(int ux, int uy, int vx, int vy){
        for(Edge e : setE) {
            if (e.getV().getX() == vx && e.getV().getY() == vy && e.getU().getX() == ux && e.getU().getY() == uy) {
                return true;
            }
            if (e.getV().getX() == ux && e.getV().getY() == uy && e.getU().getX() == vx && e.getU().getY() == vy) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the object of Vertex with particular coordinates
     * @param x - X coordinate of Vertex
     * @param y - Y coordinate of Vertex
     * @return the vertex with specific coordinates or null if such doesn't exist in graph
     */
    private Vertex isVertex(int x, int y) {
        for (Vertex v: setV) {
            if (v.getX() == x && v.getY() == y) {
                return v;
            }
        }
        return null;
    }
    /**
     * Returns the set of vertices in graph
     * @return the set of vertices in graph
     */
    public Set<Vertex> vertices(){
        return Collections.unmodifiableSet(setV);
    }
    /**
     * Returns the set of edges in graph
     * @return the set of edges in graph
     */
    public Set<Edge> edges(){
        return Collections.unmodifiableSet(setE);
    }
}
