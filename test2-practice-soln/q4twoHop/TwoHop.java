package q4twoHop;

/**
 * TwoHop
 * 
 * Placeholder class for the static method findTwoHop which finds
 * the vertices reachable from a given source in two hops and not
 * less.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * March 30, 2019
 */
public class TwoHop {

    /**
     * Compute the set of vertices in a given graph that are reachable
     * from a given source by following two links (and are NOT adjacent
     * to that source vertex).
     * @param g The given graph
     * @param s The source vertex
     * @return The set of vertices reachable by two links as a boolean 
     * array where position v indicates whether vertex v is reachable
     * from s by two links.
     */
    public static boolean[] findTwoHop(Graph g, int s) {
        //begin solution, replace with:throw new UnsupportedOperationException();
        boolean[] oneHop = new boolean[g.numVertices()];
        for (Integer v : g.adjacents(s))
            if (v != s) oneHop[v] = true;
        boolean[] twoHop = new boolean[g.numVertices()];
        for (int v = 0; v < g.numVertices(); v++)
            if (oneHop[v]) {
                for (Integer u: g.adjacents(v))
                    if (! oneHop[u])
                        twoHop[u] = true;
            }
        return twoHop;
        //end solution
    }
    
}
