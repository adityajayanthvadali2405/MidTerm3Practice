package weighted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WVertex<E> implements Comparator <WVertex<E>>{
    private E value;
    private Map <WVertex<E>, Double> neighbors;

    public WVertex (E value) {
        this.value = value;
        neighbors = new HashMap<> ();
    }

    public E getValue() {
        return value;
    }

    public Map<WVertex<E>, Double> getNeighbors() {
        return neighbors;
    }

    public void connect (WVertex<E> neighbor, double weight) {
        neighbors.put (neighbor, weight);
    }

    public boolean connected (WVertex<E> neighbor) {
        return neighbors.containsKey (neighbor);
    }

    public double weight (WVertex<E> neighbor) {
        return neighbors.get (neighbor);
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public int compare (WVertex<E> a, WVertex<E> b) {
        Double distanceToA = weight (a);
        Double distanceToB = weight (b);

        if (distanceToA < distanceToB) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public List<WVertex<E>> getNearestNeighbors () {
        List<WVertex<E>> nearest = new ArrayList<> (neighbors.keySet());
        Collections.sort (nearest, this);

        return nearest;
    }
}
