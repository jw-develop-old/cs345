package impl;

public class AVLBalancer<K extends Comparable<K>,V> implements Balancer<K,V,AVLInfo<K,V>> {

    public BSTMap<K, V, AVLInfo<K, V>>.Node putFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
        
        if (fix.isNull())
        	return fix;
        
        fix.getInfo().recompute();
                        
        if (!fix.getRight().isNull()) {
        	
	        //If balance less than -1.
	        if (fix.getRight().height() - fix.getLeft().height() < -1) {
	        	
	        	//If right-left violation.
	        	if (fix.getRight().getRight().height() > 0) {
	        		fix.getRight().setRight(fix.getRight().getRight().rotateRight());
	        	}
	        	
	        	//Right-right violation.
	        	fix = fix.rotateLeft();
	        }
	        
	        fix.setRight(putFixup(fix.getRight()));
        }
        
        if (!fix.getLeft().isNull()) {
        	
	        //If balance more than 1.
	        if (fix.getRight().height() - fix.getLeft().height() > 1) {
	        	
	        	//If left-right violation.
	        	if (fix.getLeft().getLeft().height() > 0) {
	        		fix.getLeft().setLeft(fix.getLeft().getLeft().rotateLeft());
	        	}
	        	
	        	//Left-left violation.
	        	fix = fix.rotateRight();
	        }
	        
	        fix.setLeft(putFixup(fix.getLeft()));
        }
        
        return fix;
    }

    public BSTMap<K, V, AVLInfo<K, V>>.Node removeFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
        return putFixup(fix);
    }

    public AVLInfo<K, V> newInfo(BSTMap<K, V, AVLInfo<K, V>>.Node node) {
        return new AVLInfo<K,V>(0, 0, node);
    }
    public AVLInfo<K, V> nullInfo(BSTMap<K, V, AVLInfo<K, V>>.Node node) {
        return newInfo(node);
    }

    public void rootFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) { }

}
