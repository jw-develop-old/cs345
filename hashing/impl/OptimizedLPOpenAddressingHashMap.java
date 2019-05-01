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
        
        if (i != -1) {
			table[i] = null;
			fix(i);
		}
    }
    
    private void fix(int i) {

    	int gap = i;
    	
        i = (i+1) % table.length;
        
    	// Can catch a full loop through the array.
    	while(table[i] != null) {
            
    		int ideal = h.hash(table[i].key);
    		
    		// Plugging cases.
            if ((ideal < gap && gap < i) ||
            		(i < ideal && ideal < gap) ||
            		(gap < i && i < ideal)) {
				table[gap] = table[i];
				fix(i);
				return;
			}
            
        	// Walk through the array.
            i = (i+1) % table.length;
    	}
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    