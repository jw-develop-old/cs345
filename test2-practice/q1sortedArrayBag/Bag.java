package q1sortedArrayBag;

/**
 * Bag
 * 
 * Interface to serve as an example of the bag (multi-set) ADT.
 * This differs from what Sedgewick calls a "bag" (but
 * he's wrong). I relied somewhat on the Bag interface
 * in org.apache.commons.collections to define what a bag
 * should be (for example, the iterator returns each item
 * the number of times it's in the bag, not necessarily only
 * once for every unique item).
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * July 9, 2014
 * @param <E> The base-type of the bag
 */

public interface Bag<E> extends Iterable<E> {

	/**
	 * Add an item to the bag (increasing its
	 * count if it is already part of the bag).
	 * @param item The item to add
	 */
	void add(E item);
	
	/**
	 * How many times does this bag contain this item?
	 * @param item The item to check
	 * @return The count for the given item
	 */
	int count(E item);

	/**
	 * Remove (all occurrences of) an item from the set, if it's there
	 * (ignore otherwise).
	 * @param item The item to remove
	 */
	void remove(E item);

	/**
	 * The number of items in the bag. This is the sum
	 * of the counts, not the number of unique items.
	 * @return The number of items.
	 */
	int size();
	
	/**
	 * Is the set empty?
	 * @return True if the set is empty, false otherwise.
	 */
	boolean isEmpty();
	

	
}
