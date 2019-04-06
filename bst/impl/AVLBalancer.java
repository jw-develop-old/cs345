package impl;

public class AVLBalancer<K extends Comparable<K>,V> implements Balancer<K,V,AVLInfo<K,V>> {

    public BSTMap<K, V, AVLInfo<K, V>>.Node putFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
        
        if (fix.isNull())
        	return fix;

        //Right-side violation.
        if (balance(fix) < -1) {
        	
        	//If right-left violation.
        	if (balance(fix.getRight()) > 0) {
        		fix.rightRotateRight();
        	}
        	
        	//Right-right violation.
        	fix = fix.rotateLeft();
        }
        
        //Left-side violation.
        if (balance(fix) > 1) {
        	
    		//If left-right violation.
	        if (balance(fix.getLeft()) < 0) {
	        	fix.leftRotateLeft();
	        }
	        
        	//Left-left violation.
        	fix = fix.rotateRight();
        }
        
        fix.getInfo().recompute();
        
		return fix;      
    }
    
    private int balance(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
    	return fix.getInfo().getBalance();
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
