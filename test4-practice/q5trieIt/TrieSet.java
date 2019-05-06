package q5trieIt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TrieSet
 * 
 * Implementation of the Set ADT for strings using a trie.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * April 22, 2015, revise April 19, 2016
 */
public class TrieSet implements Set<String> {

    //--- Class for nodes ---
    
    private class TrieNode {
        /**
         * Children nodes in this trie
         */
        private TrieNode[] children;

        /**
         * Is the string which would end at this node (not
         * descend into any child node) in this set?
         */
        private boolean terminal;

        private int x;

        /**
         * Constructor for a node initially empty
         * (which, apart from the root, would break the invariant)
         */
        TrieNode() {
            children = new TrieNode[26];
            terminal = false;
        }
        /**
         * Count the number of string (suffixes) in the
         * subtrie rooted at this node.
         * @return
         */
        public int size() {
            int count = terminal ? 1 : 0;
            for (TrieNode child : children)
                if (child != null) count += child.size();
            return count;
        }


        /**
         * Does this set contain the given item?
         */
        public boolean contains(String item) {
            if (item.length() == 0)
                return terminal;
            else {
                int index = c2i(item.charAt(0));
                if (children[index] == null) return false;
                else return children[index].contains(item.substring(1));
            }
        }

        public boolean isEmpty() {
            return size() == 0;
        }

    }

    // --- Utility functions converting between chars and indices
    
    /**
     * Convert a character to an index, according to the mode.
     */
    private static int c2i(char c) {
        if (c >= 'A' && c <= 'Z')
            return c - 'A';
        else
            throw new BadCharException(c);
    }

    /**
     * Convert an index to a character, according to the mode.
     */
    private static char i2c(int i) {
        return (char) ('A' + i);
    }

    
    // --- components of TrieSet class proper ---
    
    
    private TrieNode root;
    
    public int size() { return root.size(); }
    public boolean contains(String key) { return root.contains(key); }
    public boolean isEmpty() { return root.isEmpty(); }
    


    public Iterator<String> iterator() {
         throw new UnsupportedOperationException();
    }

    
    
    // --- Exception classes ---

    public static class BadCharException extends RuntimeException {
        public BadCharException(char c) {
            super("Bad character: " + c);
        }

        private static final long serialVersionUID = -3495608442105421490L;
    }

    public class BadModeException extends RuntimeException {
        private static final long serialVersionUID = -7783643567574205891L;

        public BadModeException(int mode) {
            super("Unknown Trie mode: " + mode);
        }

    }

    // --- Constructor and private factory ---
    
    public TrieSet(String[] items) {
        root = buildTrie(items, 0, items.length, 0);
    }

    
    private TrieNode buildTrie(String[] items, int start, int stop, int c) {
        TrieNode subTrie = new TrieNode();
        if (items[start].length() == c) {
            subTrie.terminal = true;
            start++;
        }
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            int end = start;
            while (end < stop && items[end].charAt(c) == letter) end++;
            if (start != end)
                subTrie.children[c2i(letter)] = buildTrie(items, start, end, c+1);
            start = end;
        }
        return subTrie;
    }
    
}
