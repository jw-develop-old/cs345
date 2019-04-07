package q1sortedArrayBag;

import java.util.Iterator;

/**
 * SortedArrayBag
 * 
 * An implementation of a Bag whose keys are known 
 * ahead of time and whose main operations (add, count,
 * and remove) operate in logarithmic time.
 * 
 * The keys are given to the constructor; they all initially
 * have count 0. Behavior is undefined if add or remove is
 * called using a key besides those given to the constructor.
 * 
 * CSCI 345
 * Test 2 Practice Problem 1.
 */

public class SortedArrayBag implements Bag<String> {

    private String[] keys;
    private int[] counts;

    /**
     * Constructor that takes an iterator that gives
     * the keys in sorted order and the number of keys.
     * Behavior is undefined if the number of items returned
     * by the iterator differs from numKeys.
     * @param keys An iterator that gives the potential keys
     * in sorted order.
     * @param numKeys The number of keys
     */
    public SortedArrayBag(Iterator<String> keys, int numKeys) {
    	
    	this.keys = new String[numKeys];
    	this.counts = new int[numKeys];
    	
    	String c;
		for (int i=0;i<numKeys;i++) {
			c = keys.next();
			this.keys[i] = c;
			this.counts[i] = 0;
		}
    }
    
    private int findIndex(String item) {
    	
    	int i=0;
    	int j=keys.length-1;
    	int index = (j+i)/2;
    	
    	while (!keys[index].equals(item)) {
    		if (j-i < 2)
				return (index+1);
    		if (keys[index].compareTo(item) < 0) {
    			i = index;
    		}
    		else {
    			j = index;
    		}
    		index = (j+i)/2;
    	}
    	
    	return index;
    }
	

    /**
     * Add an item to the bag, increasing its count by 1.
     * Behavior undefined if the given item is not one of the
     * keys supplied to the constructor.
     * @param item The item to add
     */
    public void add(String item) {
        int i = findIndex(item);
        counts[i]++;
    }

    /**
     * How many times does this bag contain this item?
     * Items supplied to the constructor but either have never been
     * added or have been delete have count 0.
     * Behavior is undefined for items not supplied to the
     * constructor.
     * @param item The item to check
     * @return The count for the given item.
     */
    public int count(String item) {
    	return counts[findIndex(item)];
    }

    /**
     * Remove the given item, resetting its count to 0.
     * Behavior is undefined for items not supplied to the
     * constructor.
     * @param The item to remove
     */
    public void remove(String item) {
        counts[findIndex(item)] = 0;
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
    	int tR = 0;
        for (int c : counts)
        	tR += c;
        return tR;
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        for (int c : counts)
        	if (c > 0)
        		return false;
        return true;
    }

    /**
     * Make an iterator over the items in this bag which will
     * return each item the number times indicated by its count.
     * @return An iterator over the bag
     */
    public Iterator<String> iterator() {
    	Iterator<String> it = new Iterator<String>() {
        	
			int current = 0;
			
			public boolean hasNext() {
				return current < counts.length;
			}

			public String next() {
				int ind = current;
				
				current++;
				
				while (hasNext() && counts[current] == 0)
					current++;
				
				return keys[ind];
			}
        	
        };
        
        if (it.hasNext() && counts[0] == 0)
        	it.next();
        return it;
    }

}
