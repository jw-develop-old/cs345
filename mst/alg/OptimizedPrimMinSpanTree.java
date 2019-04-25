package alg;

import adt.PriorityQueue;
import adt.Set;
import adt.WeightedGraph;
import adt.WeightedGraph.WeightedEdge;
import impl.BasicHashSet;
import impl.OptimizedHeapPriorityQueue;

/**
 * OptimizedPrimMinSpanTree
 * 
 * Implementation of Prim's algorithm for computing
 * the minimum spanning tree of a graph, using a
 * more heavily optimized priority queue.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 25, 2015
 */
public class OptimizedPrimMinSpanTree implements MinSpanTree {


    public Set<WeightedEdge> minSpanTree(WeightedGraph g) {
        HPAVertexRecord[] records = new HPAVertexRecord[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            records[i] = new HPAVertexRecord(i, Double.POSITIVE_INFINITY);
        PriorityQueue<HPAVertexRecord> pq = 
                new OptimizedHeapPriorityQueue<HPAVertexRecord>(records, new HPAVertexRecord.VRComparator());
        Set<WeightedEdge> mstEdges = new BasicHashSet<WeightedEdge>(g.numVertices());
        int[] parents = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = -1;
        
        while (!pq.isEmpty()) {
        	HPAVertexRecord current = pq.extractMax();
        	int parent = parents[current.id];
        	
        	if (parent != -1)
        		mstEdges.add(new WeightedEdge(
        				current.id,parent,g.weight(current.id,parent),false));
        	
        	for (int v : g.adjacents(current.id)) {
        		HPAVertexRecord vr = records[v];
        		double dist = vr.getDistance();
        		double weight = g.weight(current.id,v);
        		if (pq.contains(new HPAVertexRecord(v,dist))
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
