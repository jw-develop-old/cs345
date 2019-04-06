package q5array2rb;

/**
 * SortedToRB
 * 
 * Class to hold static method to convert a sorted array to
 * a red-black BST.
 * 
 * CSCI 345 
 */

public class SortedToRB {

    /**
     * Convert a 
     * @param sequence
     * @return
     */
    public static RBNode sortedToRB(String[] sequence) {
   	    //begin solution, replace with: throw new UnsupportedOperationException();   	
    	return sortedToRB(sequence, 0, sequence.length);
    	// end solution
    }
	//begin solution, replace with: 

    private static RBNode sortedToRB(String[] sequence, int start, int stop) {
        if (stop - start == 0) return null;
        else if (stop - start == 1) return new RBNode(null, sequence[start], null, false);
        else {
            int mid = (stop + start) / 2;
            RBNode toReturn = new RBNode(sortedToRB(sequence, start, mid), sequence[mid],
                    sortedToRB(sequence, mid+1, stop), false);
            if (twoLessThanPow2(stop-start))
                toReturn.left.isRed = true;
            return toReturn;
        }
    }

    private static boolean twoLessThanPow2(int i) {
        int x = i + 2;
        while (x > 1) {
            if (x % 2 == 1) return false;
            x /= 2;
        }
        return true;
    }
    // end solution
}
