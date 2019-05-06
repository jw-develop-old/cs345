package impl;

import java.util.Iterator;

import impl.OpenAddressingHashMap.Pair;

/**
 * OptimizedLPOpenAddressingHashMap
 * 
 * An extension to open addressing that avoids using sentinel
 * deleted values when using the linear probing strategy.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * May 18, 2017
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class OptimizedLPOpenAddressingHashMap<K,V> extends OpenAddressingHashMap<K, V> {

    /**
     * Actually unnecessary since the default constructor would
     * have the same effect, but this shows intentionality.
     */
    public OptimizedLPOpenAddressingHashMap() {
        super(1);
    }
    
    
    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    @Override  // now that's a REAL override
    public void remove(K key) {
    	
        int i = find(key);
        
        for (int i1=0;i1<12;i1++)
        	System.out.println(i1+" --- "+table[i1]!=null);
        
        if (i != -1) {
        	
			System.out.println("Removing "+i);
			
        	numPairs--;
        	table[i] = null;
        	
        	System.out.println("Gap is "+i);
        	
        	int gap = i;
            i = (i+1) % table.length;
            
        	// Can catch a full loop through the array.
        	while(table[i] != null) {
        		System.out.println("Inside "+i);
                
        		int ideal = h.hash(table[i].key);
        		
        		// Plugging cases.
                if ((ideal < gap && gap < i) ||
                		(i < ideal && ideal <= gap) ||
                		(gap < i && i < ideal)) {
                	
                	System.out.println("Plugged "+i);
                	
    				table[gap] = table[i];
    				table[i] = null;
    				gap = i;
    				System.out.println("Gap is "+i);
    			}
                
            	// Walk through the array.
                System.out.println("i is now "+i);
                i = (i+1) % table.length;
        	}
        	System.out.println("FINISHED "+i);
        }
        
        for (int i1=0;i1<12;i1++)
        	System.out.println(i1+" --- "+table[i1]!=null);
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    