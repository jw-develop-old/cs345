package q3hybridHash;

public class HybridHashSet implements Set<String> {

	/**
	 * The number of buckets in the primary table
	 * (should be prime)
	 */
	private int m;

	/**
	 * The number of positions in the secondary table
	 * (should be a different prime from m)
	 */
	private int mm;

	/**
	 * Class for the secondary tables
	 */
	private class Bucket implements Set<String> {
		/**
		 * The table itself
		 */
		String[] table;

		/**
		 * Plain old constructor
		 */
		Bucket() {
			table = new String[mm];
		}

		/**
		 * Hash function
		 */
		int hash(String key) {
			return (key.hashCode() & 0x7fffffff) % mm;
		}

		/**
		 * Add an item to the set of this secondary table. 
		 * PRECONDITION: the given item is NOT already in
		 * this (or any other) secondary table
		 * @param item The item to add
		 */
		public void add(String item) {
			int i = hash(item);
			while (table[i] != null)
				i = (i+1) % mm;
			table[i] = item;
		}

		/**
		 * Does the set of this secondary table contain the item?
		 * @param item The item to check
		 * @return True if the item is in the set, false otherwise
		 */
		public boolean contains(String item) {
			int i = hash(item);
			while (table[i] != null && !table[i].equals(item))
				i = (i + 1) % mm;
			return table[i] != null;
		}

		/**
		 * The number of items in this secondary table
		 * @return The number of items.
		 */
		public int size() {
			int size = 0;
			for (String s : table)
				if (s != null)
					size++;
			return size;
		}

		/**
		* Is this secondary table empty?
		*/
		public boolean isEmpty() {
			return size() != 0;
		}

	}

	/**
	 * The primary table, and array of buckets
	 */
	private Bucket[] buckets;

	/**
	 * Plain old constructor
	 * @param min The minimum size of the primary table
	 */
	HybridHashSet(int min) {
		m = PrimeSource.nextOrEqPrime(min);
		mm = PrimeSource.nextOrEqPrime(m + 1);
		buckets = new Bucket[m];
		for (int i = 0; i < m; i++)
			buckets[i] = new Bucket();
	}

	/**
	 * Hash function
	 */
	private int hash(String key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	/**
	 * Add an item to the set. If the given item is already
	 * part of this set, then this does not change the
	 * state of the set.
	 * @param item The item to add
	 */
	public void add(String item) {
		if (!contains(item))
			buckets[hash(item)].add(item);
	}

	/**
	 * Does this set contain the item?
	 * @param item The item to check
	 * @return True if the item is in the set, false otherwise
	 */
	public boolean contains(String item) {
		return buckets[hash(item)].contains(item);
	}

	/**
	 * The number of items in the set
	 * @return The number of items.
	 */
	public int size() {
		int size = 0;
		for (Bucket b : buckets)
			size += b.size();
		return size;
	}

	/**
	 * Is the set empty?
	 * @return True if the set is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
}
