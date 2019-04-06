package q5array2rb;

/**
 * RBNode
 * 
 * Class to support a minimal implementation of a red-black
 * BST.
 */
public class RBNode {

    /**
     * Children
     */
    public RBNode left, right;

    /**
     * The key
     */
    public String key;

    /**
     * Is this node red or not?
     */
    public boolean isRed;

    /**
     * The computed blackheight of this node---for
     * verification purposes. This variable will be
     * set by the code to verify and is assumed valid only
     * during verification. It does NOT need to be maintained
     * by the rest of the code, including code that constructs
     * the tree.
     */
    public int blackHeight;

    /**
     * Constructor, given children, the key, and redness.
     */
    public RBNode(RBNode left, String key, 
                  RBNode right, boolean isRed) {
        this.left = left;
        this.key = key;
        this.right = right;
        this.isRed = isRed;
    }
    
    public String toString() {
        String toReturn = "(";
        if (left == null) toReturn += "*";
        else toReturn += left.toString();
        if (isRed) toReturn += "[" + key + "]";
        else toReturn += key;
        if (right == null) toReturn += "*";
        else toReturn += right.toString();
        toReturn += ")";
        return toReturn;
    }

    

}
