package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AdjacencyGraph <E> implements Graph <E> {
    private Map <E, Vertex<E>> verticies;

    public AdjacencyGraph () {
        verticies = new HashMap<> ();
    }

    public void add (E value) {
        verticies.put (value, new Vertex<> (value));
    }

    public boolean contains (E value) {
        return verticies.containsKey (value);
    }

    @Override
    public int size () {
        return verticies.size ();
    }

    @Override
    public void connectDirected (E a, E b) {
        Vertex<E> vA = verticies.get (a);
        Vertex<E> vB = verticies.get (b);
        vA.connect (vB);
    }

    @Override
    public void connectUndirected(E a, E b) {
        Vertex<E> vA = verticies.get (a);
        Vertex<E> vB = verticies.get (b);
        vA.connect (vB);     
        vB.connect (vA);   
    }

    @Override
    public boolean connected (E a, E b) {
        Vertex<E> vA = verticies.get (a);
        Vertex<E> vB = verticies.get (b);
        return vA.connected (vB);    
    }

    @Override
    public boolean bfSearch (E start, E end) {
        Vertex<E> vA = verticies.get (start);
        Vertex<E> vB = verticies.get (end);
        Queue <Vertex <E>> queue = new LinkedList<> ();
        Set <Vertex <E>> set = new HashSet<> ();
        
        queue.add (vA);
        set.add (vA);

        while (!queue.isEmpty ()) {
            Vertex<E> V = queue.remove ();
            if (V == vB) {
                return true;
            }
            for (Vertex<E> neighbor : V.getNeighbors ()) {
                if (!set.contains(neighbor)) {
                    queue.add (neighbor);
                    set.add (neighbor);
                }
            }
        }
        
        return false;
    }

    private List<E> makePath (Map <Vertex<E>, Vertex<E>> predecessors, Vertex<E> end) {
        if (!predecessors.containsKey (end)) {
            return null;
        }
        List <E> path = new LinkedList<> ();
        Vertex <E> current = end;
        while (current != null) {
            path.add (0, current.getValue ());
            current = predecessors.get (current);
        }
        return path;
    }

    @Override
    public List<E> bfPath(E start, E end) {
        Vertex<E> vStart = verticies.get (start);
        Vertex<E> vEnd = verticies.get (end);
        Queue <Vertex <E>> queue = new LinkedList<> ();
        Map <Vertex <E>, Vertex<E>> predecessors = new HashMap<> ();

        queue.add (vStart);
        predecessors.put (vStart, null);
        while (!queue.isEmpty ()) {
            Vertex<E> V = queue.remove ();
            if (V == vEnd) {
                break;
            }
            for (Vertex<E> neighbor : V.getNeighbors()) {
                if (!predecessors.containsKey (neighbor)) {
                    queue.add (neighbor);
                    predecessors.put (neighbor, V);
                }
            }
        }

        return makePath (predecessors, vEnd);
    }

    private void visitDFS (Vertex<E> vertex, Set <Vertex<E>> visited) {
        for (Vertex <E> neighbor : vertex.getNeighbors ()) {
            if (!visited.contains (neighbor)) {
                visited.add (neighbor);
                visitDFS (neighbor, visited);
            }
        }
    }

    @Override
    public boolean dfSearch (E start, E end) {
        Vertex<E> vStart = verticies.get (start);
        Vertex<E> vEnd = verticies.get (end);
        Set <Vertex<E>> visted = new HashSet<> ();

        visted.add (vStart);
        visitDFS (vStart, visted);

        return visted.contains (vEnd);
    }

    private List <E> visitDFPath (Vertex<E> vertex, Vertex<E> end,  Set <Vertex<E>> visited) {
        if (vertex == end) {
            List <E> path = new LinkedList<> ();
            path.add (vertex.getValue ());
            return path;
        }
        for (Vertex <E> neighbor : vertex.getNeighbors ()) {
            if (!visited.contains (neighbor)) {
                visited.add (neighbor);
                List<E> path = visitDFPath (neighbor, end, visited);
                if (path != null) {
                    path.add (0, vertex.getValue());
                    return path;
                }
            }
        }
        return null;
    }

    @Override
    public List<E> dfPath(E start, E end) {
        Vertex<E> vStart = verticies.get (start);
        Vertex<E> vEnd = verticies.get (end);
        Set <Vertex<E>> visted = new HashSet<> ();

        visted.add (vStart);

        return visitDFPath (vStart, vEnd, visted);
    }

    @Override
    public String toString() {
      String output = "";
      for (E vertex : verticies.keySet()) {
        output += vertex + ": ";
        Vertex<E> v = verticies.get(vertex);
        for (Vertex<E> neighbor : v.getNeighbors()) {
          output += neighbor.getValue() + " ";
        }
        output += "\n";
      }
      return output;
    }
    
}
