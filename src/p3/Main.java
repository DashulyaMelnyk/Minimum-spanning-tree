package p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

        public static void main(String[] args) {
                Graph g = new Graph(); // creates graph
                String line;
                String[] words;
                try {
                        BufferedReader in = new BufferedReader(new FileReader("graph2.txt")); // reading file
                        while ((line = in.readLine()) != null) {
                                words = line.split(" "); // splitting the line by spaces
                                //adding edge to the graph
                                g.add(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[4]));
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                MinHeap<Edge> heap = new MinHeap<>(g.vertices().size()); // creates minimum heap
                for (Edge e : g.edges()) {
                        heap.addToArray(e); // adds all edges from graph to heap
                }
                heap.sort(); //sorting heap
                Graph tree = kruskal(g, heap); // calling Kruskal algorithm function to obtain minimum spanning tree
                Canvas.paint(g, tree); // painting the graph and minimum spanning tree
        }

        // Kruskal algorithm implementation
        public static Graph kruskal(Graph g, MinHeap h) {
                Graph result = new Graph();
                Edge e;
                Vertex u, v; // vertices of edge
                HashMap<Vertex, HashSet<Vertex>> c = new HashMap<>();
                for (Vertex vi : g.vertices()) {  //creating sets for each vertex in graph
                        c.put(vi, new HashSet<>());
                }
                while (!h.isEmpty()) {
                        e = (Edge) h.removeMin(); //getting and deleting minimum element from heap
                        u = e.getU();
                        v = e.getV();
                        if (!isInSet(c, u, v)) { // checking if the edge won't create the loop
                                result.add(u.getX(), u.getY(), v.getX(), v.getY(), e.getW()); // adding to the resulting graph
                                merge(c, u, v); // merging the sets of vertices
                        }
                }
                return result;
        }

        //function for checking if the vertices are already in the same set
        public static boolean isInSet(HashMap<Vertex, HashSet<Vertex>> c, Vertex u, Vertex v) {
                for (Vertex v1 : c.get(u)) {
                        if (v1.equals(v)) return true;
                }
                for (Vertex v1 : c.get(v)) {
                        if (v1.equals(u)) return true;
                }
                return false;
        }

        // function that merges two sets
        public static void merge(HashMap<Vertex, HashSet<Vertex>> c, Vertex u, Vertex v) {
                HashSet<Vertex> temp = c.get(u); //creating temporary set to union the sets
                temp.addAll(c.get(v));
                c.put(u, temp);//rewriting the value set of first vertex
                c.get(u).add(v);
                c.put(v, temp);//rewriting the value set of second vertex
                c.get(v).add(u);
        }
}