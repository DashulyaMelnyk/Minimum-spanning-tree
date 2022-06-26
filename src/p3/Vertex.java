package p3;

/**
 * Vertex class represents the vertex of graph. It consists of two coordinates X and Y.
 * @author Daria Melnyk
 */

public class Vertex {
    public final int x, y;


    /**
     * Creates the Vertex with the specified coordinates
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the integer of X coordinate
     * @return the vertex's X
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the integer of Y coordinate
     * @return the vertex's Y
     */
    public int getY() {
        return y;
    }
}
