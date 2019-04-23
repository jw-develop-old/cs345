package impl;

public class TradRBBalancer<K extends Comparable<K>, V> extends RBBalancer<K, V>  {
    public BSTMap<K, V, RBInfo<K, V>>.Node putFixup(BSTMap<K, V, RBInfo<K, V>>.Node fix) {
        
    	if (fix.isNull())
    		return fix;
    	
    	fix.getInfo().recompute();
        
        // Red left violation cases.
        if (!fix.getLeft().isNull() && fix.getLeft().getInfo().isRed()) {
	        if ((!fix.getLeft().getLeft().isNull() && fix.getInfo().isRedLL()) || 
	        	(!fix.getLeft().getRight().isNull() && fix.getInfo().isRedLR())) {
	        	
	        	// Red uncle.
	        	if (!fix.getRight().isNull() && fix.getInfo().isRedR()) {
	        		fix.getInfo().redden();
	        		fix.getLeft().getInfo().blacken();
	        		fix.getRight().getInfo().blacken();
	        	}
	        	
	        	// Black uncle.
	        	else {
	        		
	        		// Red left right, black uncle.
	            	if (!fix.getLeft().getRight().isNull() && fix.getInfo().isRedLR()) {
	            		fix.setLeft(fix.getLeft().rotateLeft());
	            	}
	            	
	            	// Fall through to left left, black uncle.
	            	fix = fix.rotateRight();
	            	fix.getInfo().blacken();
	            	fix.getRight().getInfo().redden();
	        	}
	        }
        }
        
        // Red right violation cases.
        if (!fix.getRight().isNull() && fix.getRight().getInfo().isRed()) {
	        if ((!fix.getRight().getRight().isNull() && fix.getInfo().isRedRR()) || 
	        	(!fix.getRight().getLeft().isNull() && fix.getInfo().isRedRL())) {
	        	
	        	// Red uncle.
	        	if (!fix.getLeft().isNull() && fix.getInfo().isRedL()) {
	        		fix.getInfo().redden();
	        		fix.getLeft().getInfo().blacken();
	        		fix.getRight().getInfo().blacken();
	        	}
	        	
	        	// Black uncle.
	        	else {
	        		
	        		// Red left right, black uncle.
	            	if (!fix.getRight().getLeft().isNull() && fix.getInfo().isRedRL()) {
	            		fix.setRight(fix.getRight().rotateRight());
	            	}
	            	
	            	// Fall through to left left, black uncle.
	            	fix = fix.rotateLeft();
	            	fix.getInfo().blacken();
	            	fix.getLeft().getInfo().redden();
	        	}
	        }
        }
                
        // Chain of recomputing. Brute force ...
        if (!fix.getLeft().isNull()) {
        	if (!fix.getLeft().getLeft().isNull())
        		fix.getLeft().getLeft().getInfo().recompute();
        	if (!fix.getLeft().getRight().isNull())
        		fix.getLeft().getRight().getInfo().recompute();
        	fix.getLeft().getInfo().recompute();
        }
        if (!fix.getRight().isNull()) {
        	if (!fix.getRight().getLeft().isNull())
        		fix.getRight().getLeft().getInfo().recompute();
        	if (!fix.getRight().getRight().isNull())
        		fix.getRight().getRight().getInfo().recompute();
        	fix.getRight().getInfo().recompute();
        }
        fix.getInfo().recompute();
        
        return fix;
           
    }
    
    public BSTMap<K, V, RBInfo<K, V>>.Node removeFixup(BSTMap<K, V, RBInfo<K, V>>.Node fix) {

        // The left and right children of fix
        BSTMap<K, V, RBInfo<K, V>>.Node 
            left = fix.getLeft(),
            right = fix.getRight();

        // Objects containing color and black height information
        // for fix and its children
        RBInfo<K, V> 
            fInfo = fix.getInfo(),
            lInfo = left.getInfo(),
            rInfo = right.getInfo();
        int lbh = lInfo.getBlackHeight(),
            rbh = rInfo.getBlackHeight();
        // The node to replace fix, called y in the text
        BSTMap<K, V, RBInfo<K, V>>.Node replace = null;
        
        // If blackheights are consistent, there's nothing to be done
        if (lbh == rbh) replace = fix;

        else if (lbh < rbh) {  // left has deficient blackheight            
            // case 1: left is red
            if (lInfo.isRed()) {
                lInfo.blacken();
                lInfo.recompute();
                replace = fix;
            }
            // case 2: right is red
            else if (rInfo.isRed()) {
                // rotate about fix, replace is the root of the new subtree
                replace = fix.rotateLeft();
                // get the new left child and its info (actually identical to fix) 
                left = replace.getLeft(); // actually same as fix
                lInfo = left.getInfo();
                // recolor and recompute black height
                replace.getInfo().blacken();
                lInfo.redden();
                lInfo.recompute();               
                // step down the tree and apply cases to the left child
                replace.setLeft(removeFixup(left));
                // finally, re-apply cases to the replacement subtree
                replace = removeFixup(replace);
            }

             // ...cases 3, 4, and 5...
            
        }
        else { // right has deficient blackheight  
            assert rbh < lbh;
            
             // mirror-image cases
            
        }

        replace.getInfo().recompute();
       
        return replace;
    }

}
