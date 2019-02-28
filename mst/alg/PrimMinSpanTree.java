package alg;

import impl.BasicHashSet;
import impl.HeapPriorityQueue;
import adt.PriorityQueue;
import adt.Set;
import adt.WeightedGraph;
import adt.WeightedGraph.WeightedEdge;

/**
 * PrimMinSpanTree
 * 
 * Implementation of Prim's algorithm for computing
 * the minimum spanning tree of a graph.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 24, 2015
 */
public class PrimMinSpanTree implements MinSpanTree {

    /**
     * Compute the minimum spanning tree of a given graph.
     * @param g The given graph
     * @return A set of the edges in the minimum spanning tree
     */
    public Set<WeightedEdge> minSpanTree(WeightedGraph g) {
        Set<WeightedEdge> mstEdges = new BasicHashSet<WeightedEdge>(g.numVertices());
        VertexRecord[] records = new VertexRecord[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            records[i] = new VertexRecord(i, Double.POSITIVE_INFINITY);
        PriorityQueue<VertexRecord> pq = 
                new HeapPriorityQueue<VertexRecord>(records, new VertexRecord.VRComparator());
        
        int[] parents = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = -1;
        
        while (!pq.isEmpty()) {
        	VertexRecord current = pq.extractMax();
        	int parent = parents[current.id];
        	if (parent != -1)
        		mstEdges.add(new WeightedEdge(
        				current.id,parent,g.weight(current.id,parent),false));
        	for (int v : g.adjacents(current.id)) {
        		VertexRecord vr = records[v];
        		double dist = vr.getDistance();
        		double weight = g.weight(current.id,v);
        		if (pq.contains(new VertexRecord(v,dist))
        				&& weight < dist) {
        			parents[v] = current.id;
        			vr.setDistance(weight);
        			pq.increaseKey(vr);
        		}
        	}
        }

        return mstEdges;
    }
}
