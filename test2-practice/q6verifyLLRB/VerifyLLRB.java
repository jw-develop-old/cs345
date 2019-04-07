package q6verifyLLRB;

/**
 * VerifyLLRB
 * 
 * Placeholder class for the static method verifyLLRB() which
 * takes a tree of red-black nodes and verifies that the tree
 * satisfies the constraints of a left-leaning red-black tree.
 * Do not assume that any red-black properties hold; the point of
 * the method is to check whether they hold.
 * 
 * CSCI 345
 */
public class VerifyLLRB {

    /**
     * Determine whether a given tree made up of
     * RBNodes satisfies the left-learning red-black tree
     * properties (except that it does not check that
     * the absolute root is black). The blackHeight fields
     * of the nodes are not assumed to be correct before this
     * method is called, but, as a side affect, this method
     * sets the blackHeight field of every node in the tree
     * rooted at the given node to the correct blackHeight
     * value. Null references are valid (trivial) left-leaning
     * red-black trees.
     * @param root The root of the (sub)tree being verified
     * @return true if the tree rooted at root is a left-leaning
     * red-black tree, false otherwise.
     * POSTCONDITION: The blackHeight of every node in the
     * tree is set to the right value if the tree verifies as
     * a left-leaning red-black tree
     */
	
	static boolean success = true;
	
    public static boolean verifyLLRB(RBNode root) {
    	fix(root);
    	
    	return success;
    }
    
    private static int fix(RBNode node) {
    	
    	int b = (node.isRed) ? 0 : 1;
    	int l = b,r = b;
    	
    	if (node.left != null) {
    		l += fix(node.left);
    		if (node.left.isRed && node.isRed)
    			success = false;
    	}
    	if (node.right != null) {
    		r += fix(node.right);
    		if (node.right.isRed)
    			success = false;
    	}
    	
    	int bH = Math.max(l, r);
    	
    	if (node.blackHeight != bH)
    		success = false;
    	node.blackHeight = bH;
    	
    	if (node.left != null && node.right != null)
    		if (node.left.blackHeight != node.right.blackHeight)
    			success = false;
    	
    	return bH;
    }
}
