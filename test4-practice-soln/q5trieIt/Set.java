package q5trieIt;

/**
 * Set
 * 
 * Interface to serve as an example of the set ADT.
 * This is similar to what Sedgewick calls a "bag" (but
 * he's wrong).
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * July 9, 2014
 * @param <E> The base-type of the set
 */

public interface Set<E> {

	
	/**
	 * Does this set contain the item?
	 * @param item The item to check
	 * @return True if the item is in the set, false otherwise
	 */
	boolean contains(E item);


	/**
	 * The number of items in the set
	 * @return The number of items.
	 */
	int size();
	
	/**
	 * Is the set empty?
	 * @return True if the set is empty, false otherwise.
	 */
	boolean isEmpty();
	
	
}
