package adt;

/**
 * StringSorter
 * 
 * Interface for any class that has a method to sort
 * an array of strings
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 */
public interface StringSorter {

    /**
     * Sort the given array of strings in lexicographical order
     * @param array The array of strings to sort.
     * POSTCONDITION: The given array has been sorted.
     */
    void sort(String[] array);
    
}
