package q6trie2array;

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

    /**
     * Children nodes in this trie
     */
    protected TrieSet[] children;

    /**
     * Is the string which would end at this node (not
     * descend into any child node) in this set?
     */
    protected boolean terminal;

    
    public int trie2Array(String[] keys) {
        return trie2Array(keys, 0, "");
    }
    
    private int trie2Array(String[] keys, int start, String prefix) {
         throw new UnsupportedOperationException();
    }
    
    
    /**
     * Constructor for a node initially empty
     * (which, apart from the root, would break the invariant)
     */
    @SuppressWarnings("unchecked")
	TrieSet() {
        children = new TrieSet[26];
        terminal = false;
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


    /**
     * Convert a character to an index, according to the mode.
     */
    protected int c2i(char c) {
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

}
