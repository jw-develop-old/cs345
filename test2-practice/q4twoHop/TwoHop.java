package q4twoHop;

import java.util.HashSet;
import java.util.Set;

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
        
    	Set<Integer> oH = new HashSet<>();
    	boolean[] tH = new boolean[g.numVertices()];
    	
    	for (int i=0;i<tH.length;i++)
    		tH[i] = false;
    	
    	for (Integer v : g.adjacents(s))
    		oH.add(v);
    	
    	for (Integer v : oH)
    		for (Integer d : g.adjacents(v))
    			if (d != s && !oH.contains(d))
    				tH[d] = true;
 
    	return tH;
    	
    }
    
}
