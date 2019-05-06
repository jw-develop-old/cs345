package q4perfectHashingBitVec;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class PerfHashBVSet<K> implements Set<K> {

    /**
     * The buckets, each int a 16-bit bit vector
     */
    private short[] buckets;

    /**
     * The primary hash function
     */
    private HashFunction<Object> h;

    /**
     * The secondary hash functions
     */
    private HashFunction<Object>[] hs;

    private int p, m;

    /**
     * The number of items currently in the set
     */
    private int count;
    
    @SuppressWarnings("unchecked")
	public PerfHashBVSet(K[] keys) {
        m = keys.length;

        // Find a hash function such that no bucket has more than
        // four keys; temporarily use the buckets array to 
        // store the number of keys in each bucket
        //begin solution, replace with://PART A
        int max = 0;
        for (K key : keys) {
            int hash = (key.hashCode() & 0x7fffffff) % 100;
            if (hash > max) max = hash;
        }        
        p = PrimeSource.nextOrEqPrime(max);
        boolean moreThanFour;
        do {
            buckets = new short[m];
            h = UniversalHashFactory.makeHashFunction(p, m, 100);
            moreThanFour = false;
            for (K key : keys) {
                int hash = h.hash(key);
                buckets[hash]++;
                if (buckets[hash] > 4) moreThanFour = true;
            }
        } while(moreThanFour);
        //end solution
        // on exit, the buckets array temporarily holds the number of 
        // keys in each bucket
        
        // sort the keys by their hash, so the keys for a given
        // bucket are all together
        Arrays.sort(keys, new Comparator<K>() {
            public int compare(K o1, K o2) {
                return h.hash(o1) - h.hash(o2);
            }
        });

        // Find a hash function for each bucket so that there are no
        // collisions in the bit vector
        hs = new HashFunction[m];
        
        // j is an index into keys
        int j = 0;
        // loop over the buckets
        for (int i = 0; i < m; i++) {
            // how many items are in the current bucket?
            int itemsHere = buckets[i];
            // zero out this bucket. From here on it is used as a bit vector
            buckets[i] = 0;
            // Find a hash function for the ith bucket. The keys in range
            // [j, j + itemsHere) will go in this bucket

            //begin solution, replace with:// --- PART B ---
            boolean noCollisions;
            do {
               noCollisions = false;
                hs[i] = UniversalHashFactory.makeHashFunction(p, 16, 100);
                for (int k = j; k < j + itemsHere; k++) {
                    int hash = hs[i].hash(keys[k]);
                    if ((buckets[i] & (1 << hash)) == 0)
                        buckets[i] |= (1 << hash);
                    else
                        noCollisions = true;
                }
                buckets[i] = 0;
            } while (noCollisions); 
            //end solution
            // move j ahead to the next bucket's range of keys
            j += itemsHere;
        }
        count = 0;
     }

     /**
      * Determine whether bit b in bucket i is set (to true).
      */
     private boolean testBit(int i, int b) {
         return (buckets[i] & (1 << b)) != 0;
     }

     /**
      * Set bit b in bucket i (to true).
      */
     private void setBit(int i, int b) {
         buckets[i] |= (1 << b);
     }

     /**
      * Clear bit b in bucket i (ie, set it to false).
      */
     private void clearBit(int i, int b) {
          buckets[i] &= (~(1<<b));
     }

     /**
      * Add a key to the set
      */
     public void add(K item) {
         int i = h.hash(item);
         if (! testBit(i, hs[i].hash(item))) {
             setBit(i, hs[i].hash(item));
             count++;
         }
     }

     /**
      * Test whether a key is in the set---assume that
      * the key is one of those given in the constructor.
      */
     public boolean contains(K item) {
         int i = h.hash(item);
         return testBit(i, hs[i].hash(item));
     }

     /**
      * Remove a key from the set
      */
     public void remove(K item) {
         int i = h.hash(item);
         if (testBit(i, hs[i].hash(item))) {            
             clearBit(i, hs[i].hash(item)); 
             count--;
         }
     }

     /**
      * How many keys are currently in the set?
      */
     public int size() { return count; }

     /**
      * Is the set empty?
      */
     public boolean isEmpty() { return count == 0; }

     /**
      * Alas, this is impossible---we don't store the keys.
      */
     public Iterator<K> iterator() {
         throw new UnsupportedOperationException();
     }

    
}
