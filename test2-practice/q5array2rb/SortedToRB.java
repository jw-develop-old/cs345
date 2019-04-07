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
	
	private static String[] sequence;

    /**
     * Convert a 
     * @param sequence
     * @return
     */
    public static RBNode sortedToRB(String[] sequence) {
    	SortedToRB.sequence = sequence;
    	int index = sequence.length/2;
    	RBNode root = makeC(0,index,sequence.length);
    	return root;
    }
    
    private static RBNode makeC(int a,int i,int b) {
    	RBNode node = new RBNode(null,sequence[i],null,false);
    	
    	if (b-a <2)
    		return node;
    	
    	node.left = makeC(a,mid(a,i),i);
    	node.right = makeC(i,mid(i,b),b);
    	
    	if (node.left == null && node.right == null)
    		node.isRed = true;
    	
    	return node;
    }
    
    private static int mid(int a,int b) {
    	return (b-a)/2;
    }
	 
}
