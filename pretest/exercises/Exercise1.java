package exercises;

public class Exercise1 {

    /**
     * 
     * Find the position of an item in a given array, if it is anywhere.
     * PRECONDITION: The array is sorted and contains no null elements.
     * @param array An array sorted by its elements' natural ordering
     * (as expressed by compareTo()).
     * @param item The item for which to search.
     * @return A position in the array which contains the item, or -1 if
     * it does not occur in the array (including if the array is empty or null)
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T item) {
    	if (array == null || array.length == 0)
    		return -1;
         int hInd = array.length;
         int lInd = 0;
         int mInd = array.length / 2;
         int comp;
         while (hInd - lInd > 0) {
        	 //System.out.printf("%s %s %s\n",lInd,mInd,hInd);
        	 comp = item.compareTo(array[mInd]);
        	 if (comp == 0)
        		 return mInd;
        	 else if (comp > 0)
        		 lInd = mInd+1;
        	 else
        		 hInd = mInd;
        	 mInd = (hInd + lInd) / 2;
         }
         return -1;
    }
}