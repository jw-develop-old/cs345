package impl;

import java.util.Iterator;
import adt.Map;

/**
 * ArrayMap
 * 
 * Class to implement the Map ADT using an array. * 
 * 
 * (Unlike Stack and Queue, Map is not specified to
 * throw NoSuchElementException when get() or remove()
 * are called with non-existent keys. Instead get()
 * returns null and remove() does nothing. The only
 * reason for this decision is that that's what the tests
 * for Maps that I already had assumed. Similarly put() doesn't
 * throw a FullContainerException.)
 * 
 * CSCI 345, Wheaton College
 * Spring 2016
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class ArrayMap<K, V> implements Map<K, V> {

    /**
     * Class for key-value pairs. This map implementation
     * is essentially an array of these.
     */
    private static class Association<K, V> {
        K key;
        V val;
        Association(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
        	return "key"+"="+val;
        }
    }

    /**
     * An array of key-value associations, the internal
     * representation of this map.
     */
    private Association<K,V>[] internal;
    private int lastAssoc = -1;

    /**
     * Plain constructor. 
     */
    @SuppressWarnings("unchecked")
    public ArrayMap() {
        // 100 as length of the initial array is an arbitrary choice.
        internal = (Association<K,V>[]) new Association[100];
         
    }

    /**
     * Cause the internal array to double in size.
     */
    private void grow() {
        @SuppressWarnings("unchecked")
        Association<K,V>[] temp = (Association<K,V>[]) new Association[internal.length * 2];
        for (int i = 0; i < internal.length; i++)
            temp[i] = internal[i];
        internal = temp;
    }

    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<K> iterator() {
        return new Iterator<K>() {
        	private int index = 0;
			public boolean hasNext() {
				return internal[index] != null;
			}
			public K next() {
				if (hasNext()) {
					index++;
					return internal[index-1].key;
				}
				return null;
			}
        };
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) {
    	
    	//If key already exists
    	for (Association<K,V> a : internal)
        	if (a != null && key.equals(a.key)) {
        		a.val = val;
        		return;
        	}
    	
    	lastAssoc++;
    	if (lastAssoc > internal.length - 1)
    		grow();
    	
    	internal[lastAssoc] = new Association<K, V>(key,val);
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
    	for (Association<K,V> a : internal)
        	if (a != null && key.equals(a.key))
        		return a.val;
    	return null;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
        for (Association<K,V> a : internal)
        	if (a != null && key.equals(a.key))
        		return true;
        return false;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
    	for (int i=0;i<internal.length;i++) {
    		Association<K,V> a = internal[i];
        	if (a != null && key.equals(a.key)) {
        		internal[i] = internal[lastAssoc];
        		internal[lastAssoc] = null;
        		lastAssoc--;
        	}
    	}
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (Association<K,V> item : internal) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
