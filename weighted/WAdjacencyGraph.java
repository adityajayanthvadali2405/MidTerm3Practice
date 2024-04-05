package weighted;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graphs.Vertex;

/**
 * An implementation of an graph using adjacency lists with weighted edges.
 * 
 * @author GCCIS Faculty
 */
public class WAdjacencyGraph<E> implements WGraph<E> {

    /**
     * The map of vertices in the graph.
     */
    private final Map<E, WVertex<E>> vertices;

    /**
     * Creates a new, empty graph.
     */
    public WAdjacencyGraph() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void add(E value) {
        WVertex<E> vertex = new WVertex<>(value);
        vertices.put(value, vertex);
    }

    @Override
    public boolean contains(E value) {
        return vertices.containsKey(value);
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public void connect(E a, E b, double weight) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);

        vertexA.connect(vertexB, weight);
        vertexB.connect(vertexA, weight);
    }

    @Override
    public boolean connected(E a, E b) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);

        return vertexA.connected(vertexB);
    }

    @Override
    public double weight(E a, E b) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);

        return vertexA.weight(vertexB);
    }

    private WPath<E> visitNearestNeighbor (WVertex<E> v, WVertex<E> e, 
                                          Set<WVertex<E>> visited) {
        if(v == e) {
            WPath<E> path = new WPath<>(e.getValue());
            return path;
        } else {
            for(WVertex<E> neighbor : v.getNearestNeighbors()) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    WPath<E> path = visitNearestNeighbor (neighbor, e, visited);
                    if(path != null) {
                        path.prepend (v.getValue(), v.weight (neighbor));
                        return path;
                    }
                }
            }
            return null;
        }
    }

    public WPath<E> nearestNeighbor (E start, E end) {
        WVertex<E> s = vertices.get(start);
        WVertex<E> e = vertices.get(end);

        Set<WVertex<E>> visited = new HashSet<>();
        visited.add(s);

        return visitNearestNeighbor (s, e, visited);
    }

    @Override
    public WPath<E> dijkstrasPath (E start, E end) {
        WVertex<E> s = vertices.get(start);
        WVertex<E> e = vertices.get(end);

        Map <WVertex<E>, PathTuple<E>> predecessors = new HashMap<> ();
        TupleQueue <E> queue = new TupleQueue<> ();

        for (WVertex<E> vertex : vertices.values ()) {
            PathTuple<E> tuple = new PathTuple<> (vertex);
            predecessors.put (vertex, tuple);
            queue.enqueue (tuple);
        }

        // Set starts distance to 0
        predecessors.get (s).update (null, 0);

        while (queue.size() != 0) {
            PathTuple<E> shortest = queue.dequeue ();
            if (shortest.getDistance () == Double.POSITIVE_INFINITY) {
                break;
            }
            WVertex<E> V = shortest.getVertex ();
            for (WVertex<E> N : V.getNeighbors().keySet()) {
                Double distance = V.weight (N);
                Double DV = shortest.getDistance () + distance;
                PathTuple<E> nTuple = predecessors.get (N);
                nTuple.update (V, DV);
            }
        }

        PathTuple<E> endTuple = predecessors.get (e);
        Double distance = endTuple.getDistance ();
        if (distance == Double.POSITIVE_INFINITY) {
            return null;
        }

        WPath<E> path = new WPath<> (e.getValue (), endTuple.getDistance ());
        WVertex<E> V = endTuple.getPredecessor ();
        while (V != null) {
            path.prepend (V.getValue ());
            PathTuple<E> vTuple = predecessors.get (V);
            V = vTuple.getPredecessor ();
        }

        return path;
    }
}
