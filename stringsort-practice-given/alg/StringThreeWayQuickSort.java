package alg;

import adt.StringSorter;

/**
 * StringMergeSort
 * 
 * Plain old merge sort, using String's compareTo method.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 */
public class StringThreeWayQuickSort implements StringSorter {


    public static final StringSorter sorter = new StringThreeWayQuickSort();
    
    private StringThreeWayQuickSort() {}
    
    public synchronized void sort(String[] array) {
        sortR(array, 0, array.length, 0);
    }

    /**
     * Sort a given sub-array by the given prefix. 
     * PRECONDITION: All the strings in the range [start, stop)
     * are identical up to character pre.
     * @param array The array to sort
     * @param start The inclusive beginning of the range to sort
     * @param stop The exclusive end of the range to sort
     * @param pre The number of characters in this range for which
     * all the characters are identical.
     */
    private void sortR(String[] array, int start, int stop, int pre) {
        
        if (stop-start > 1 && pre < array[0].length()) {
            // the character value being used in the pivot
            char pivVal = array[stop-1].charAt(pre);
            // indices to mark the array sections
            int i = start-1,  // the inclusive end of the pre-pivot section
                j = start-1,  // the inclusive end of the eq-pivot section
                k = start;    // the exclusive end of the post-pivot section and
                              // the position we'll look at next
            // Invariant: 
            //   For all x, start <= x <= i, array[x].charAt(pre) < pivot.charAt(pre).
            //   For all x, i < x <= j, array[x].charAt(pre) == pivot.charAt(pre).
            //   For all x, j < x < k, array[x].charAt(pre) > pivot.charAt(pre).
            //   After x iterations, k = start + x
            
            while (k < stop-1) {
                // The comparison character in the current string being considered
                char currVal = array[k].charAt(pre);
                // Should this current string be moved into the less section or eq section?
                
                if (currVal <= pivVal) {
                	j++;
                   // More specifically, should this current string be moved into the less 
                    // section?
                    if (currVal < pivVal) {
                    	i++;
                    	swap(i,j,array);
                     	swap(i,k,array);
                    }
                    // Otherwise
                    else
                     	swap(j,k,array);
                }
                k++;
            }

            // Put the pivot string in the last position of the eq section
            swap(stop-1,j+1,array);
            j++;

            sortR(array,start,i+1,pre);
            sortR(array,i+1,j+1,pre+1);
            sortR(array,j+1,stop,pre);
        }
    }

    private void swap(int a, int b,String[] array) {
    	if (a > -1) {
    		String temp = array[a];
    		array[a] = array[b];
    		array[b] = temp;
    	}
    }
}
