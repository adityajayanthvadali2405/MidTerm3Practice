package weighted;

/**
 * The abstract data type (ADT) for a weighted graph.
 * 
 * @author GCCIS Faculty
 */
public interface WGraph<E> {
    /**
     * Adds a new value to the graph.
     * 
     * @param value The value to add to the graph.
     */
    void add(E value);

    /**
     * Returns true if the graph contains the specified value and false 
     * otherwise.
     * 
     * @param value The value.
     * @return True if the value is present in the graph, and false otherwise.
     */
    boolean contains(E value);

    /**
     * Returns the number of values currently stored in the graph.
     * 
     * @return The number of values currently stored in the graph.
     */
    int size();

    /**
     * Creates an edge with the specified weight and uses it to connect the 
     * vertices with an undirected connection.
     * 
     * @param a The first vertex.
     * @param b The second vertex.
     * @param weight The weight of the edge connecting the vertices.
     */
    void connect(E a, E b, double weight);

    /**
     * Returns true if the specified vertices are connected by an edge and 
     * false otherwise.
     * 
     * @param a The first vertex.
     * @param b The second vertex.
     * @return True if the vertices are connected by an edge and false 
     * otherwise.
     */
    boolean connected(E a, E b);

    /**
     * Returns the weight of the edge connecting the specified vertices.
     * 
     * @param a The first vertex.
     * @param b The second vertex.
     * @return The weight of the edge connecting the two vertices.
     */
    double weight(E a, E b);

    default WPath<E> nearestNeighbors (E start, E end){
        throw new UnsupportedOperationException();
    }

    default WPath<E> dijkstrasPath(E start, E end){
        throw new UnsupportedOperationException();
    }
}
