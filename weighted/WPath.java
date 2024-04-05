package weighted;

import java.util.LinkedList;
import java.util.List;

/**
 * A weighted path. Keeps track of both the values along the path and the
 * total distance (accumulated edge weights). The path can be built forward or
 * backward.
 * 
 * @author GCCIS Faculty
 */
public class WPath<E> {
    /**
     * Holds the values along the path.
     */
    private final List<E> values;

    /**
     * Holds the total distance of the path.
     */
    private double distance;

    /**
     * Creates a new weighted path with a single value and a distance of 0.
     * 
     * @param value The value with which to start the path.
     */
    public WPath(E value) {
        this(value, 0);
    }

    /**
     * Creates a new weighted path with a single value and the specified
     * distance.
     * 
     * @param value The initial value in the path.
     * @param distance The starting distance.
     */
    public WPath(E value, double distance) {
        values = new LinkedList<>();
        values.add(value);
        this.distance = distance;
    }

    /**
     * Adds a value to the end of the path.
     * 
     * @param value The value to add to the end of the path.
     * @param weight The weight of the edge to this value.
     */
    public void append(E value, double weight) {
        values.add(value);
        this.distance += weight;
    }

    /**
     * Appends the specified value to the end of the path but does not add any
     * additional weight to the path.
     * 
     * @param value The value being added to the path.
     */
    public void append(E value) {
        append(value, 0);
    }

    /**
     * Adds the specified value to the front of the path.
     * 
     * @param value The value to add to the front of the path.
     * @param weight The weight of the edge to this value.
     */
    public void prepend(E value, double weight) {
        values.add(0, value);
        this.distance += weight;
    }

    /**
     * 
     * @param value
     */
    public void prepend(E value) {
        prepend(value, 0);
    }

    /**
     * Returns the distance of this path (the total accumulated edge weight).
     * 
     * @return The distance of the path.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the number of values along the path.
     * 
     * @return The number of values along the path.
     */
    public int size() {
        return values.size();
    }

    /**
     * Returns the value at the specified index in the path.
     * 
     * @param index The index of the desired value.
     * @return The value at the specified index.
     */
    public E get(int index) {
        return values.get(index);
    }

    @Override
    public String toString() {
        return values.toString() + ", weight=" + distance;
    }
}
