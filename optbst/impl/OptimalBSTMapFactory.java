package impl;

import static impl.OptimalBSTMap.dummy;

import java.util.Arrays;

import impl.OptimalBSTMap.Internal;



/**
 * OptimalBSTMapFactory
 * 
 * Build an optimal BST, given the keys, values, key probabilities
 * and miss probabilities.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * Feb 25, 2015
 */

public class OptimalBSTMapFactory {
	
    /**
     * Exception to throw if the input to building an optimal BST
     * is not right: either the number of keys, values, key probs,
     * and miss probs aren't consistent, or the total probability
     * is not 1.
     */
    public static class BadOptimalBSTInputException extends RuntimeException {
        private static final long serialVersionUID = -444687298513060315L;

        private BadOptimalBSTInputException(String msg) {
            super(msg);
        }
    }
    
    /**
     * Build an optimal BST from given raw data, passed as a single object.
     * A convenient overloading of the other buildOptimalBST().
     * @param rawData The collection of data for building this BST
     * @return A BST with the given keys and values, optimal with the
     * given probabilities.
     */
    public static OptimalBSTMap buildOptimalBST(OptimalBSTData rawData) {
        return buildOptimalBST(rawData.keys, rawData.values, rawData.keyProbs, rawData.missProbs);
    }
    
    /**
     * Build an optimal BST from given raw data, passed as individual arrays.
     * @param rawData The collection of data for building this BST
     * @return A BST with the given keys and values, optimal with the
     * given probabilities.
     */
    public static OptimalBSTMap buildOptimalBST(String[] keys, String[] values, double[] keyProbs,
            double[] missProbs) {
        // keep these checks
        checkLengths(keys, values, keyProbs, missProbs);
        checkProbs(keyProbs, missProbs);
        
        /**
         * Create three tables: optimal (sub-)trees (which would be an two-dimensional array of Internal nodes),
         *  total weighted depth of subtrees, and total probability of subtrees.
			For each diagonal (which really is loop over "intervals")
			For each cell in the diagonal (for each sub-range of keys with that interval)
			Calculate the total probability of that sub-range of keys
			For each key in that range as a candidate root
			Calculate the total weighted depth
			Pick the best one. Put the best (least) total weighted depth in the table for the costs. 
			Instantiate Internal to make a root of the best subtree, 
			finding the children for that internal node from appropriate places in the table.
			Return the best root of the whole tree, 
			which should be at table position [0][n-1] and should have optimal subtrees as its children
         */
        
        // The number of keys (so we don't need to say keys.length every time)
        int n = keys.length;
        
        // Three tables:
        // optimal (sub-)trees (which would be an two-dimensional array of Internal nodes)
        Internal[][] trees = new Internal[n][n];
        
        // total weighted depth of subtrees
        double[][] C = new double[n][n];
        
        // total probability of subtrees.
        double[][] T = new double[n][n];
        
        // Initialize the cells (0,0) through (n-1,n-1)
        for (int i=0;i<n;i++) {
        	trees[i][i] = new Internal(dummy,keys[i],values[i],dummy);
        	T[i][i] = missProbs[i]+keyProbs[i]+missProbs[i+1];
        	C[i][i] = 2*missProbs[i]+keyProbs[i]+2*missProbs[i+1];
        }
        
        // For each interval size from 1 to n-1
        for (int interval = 1;interval<n;interval++) {
        
        	// For each (i,j) in that interval (j=i+interval)
        	for (int i=0;i+interval<n;i++) {
        		
        		int j = i+interval;
//        		System.out.println("-- "+n+" "+interval+" "+ i+" "+j+" --");
        		
        		// Find T[i][j]
        		// "Lower right" box, as cases will handle all lower lefts.
        		T[i][j] = missProbs[i]+keyProbs[i]+T[i+1][j];
        		
        		// Consider each key k_r
        		Internal bestTree = null;
        		double bestDepth = Integer.MAX_VALUE;
        		
        		for (int r=i;r<=j;r++) {
        			
        			double depth;
        			
        			// Init.
        			if (r==i) {
        				depth = missProbs[i]+T[i][j]+C[i+1][j];
        				
        				if (depth < bestDepth) {
        					bestDepth = depth;
        					bestTree = new Internal(dummy,keys[r],values[r],trees[i+1][j]);
        				}
        			}
        			
        			// Last key.
        			else if (r==j) {
        				depth = C[i][j-1]+T[i][j]+missProbs[j+1];
        				
        				if (depth < bestDepth) {
        					bestDepth = depth;
        					bestTree = new Internal(trees[i][j-1],keys[r],values[r],dummy);
        				}
        			}
        			
        			// Common case.
        			else {
        				depth = C[i][r-1]+T[i][j]+C[r+1][j];
        				
        				if (depth < bestDepth) {
        					bestDepth = depth;
        					bestTree = new Internal(trees[i][r-1],keys[r],values[r],trees[r+1][j]);
        				}
        			}
        		}
        		
        		// Enter table entries for (i,j)
        		trees[i][j] = bestTree;
        		C[i][j] = bestDepth;
        	}
        }
        
        // Return tree rooted at cell (0,n-1) in node tree.
        OptimalBSTMap.Node root = trees[0][n-1];
        return new OptimalBSTMap(root);
    }
    
    /**
     * Check that the given probabilities sum to 1, throw an
     * exception if not.
     * @param keyProbs 
     * @param missProbs
     */
    public static void checkProbs(double[] keyProbs, double[] missProbs) {
        double[] allProbs = new double[keyProbs.length + missProbs.length];
        int i = 0;
        for (double keyProb : keyProbs)
            allProbs[i++] = keyProb;
        for (double missProb : missProbs)
            allProbs[i++] = missProb;
        // When summing doubles, sum from smallest to greatest
        // to reduce round-off error.
        Arrays.sort(allProbs);
        double totalProb = 0;
        for (double prob : allProbs)
            totalProb += prob;
        // Don't compare doubles for equality directly. Check that their
        // difference is less than some epsilon.
        if (Math.abs(1.0 - totalProb) > .0001)
            throw new BadOptimalBSTInputException("Probabilities total to " + totalProb);
    }

    /**
     * Check that the arrays have appropriate lengths (keys, values, and
     * keyProbs all the same, missProbs one extra), throw an exception
     * if not.
     * @param keys
     * @param values
     * @param keyProbs
     * @param missProbs
     */
    public static void checkLengths(String[] keys, String[] values,
            double[] keyProbs, double[] missProbs) {
        int n = keys.length;
        if (values.length != n || keyProbs.length != n || missProbs.length != n+1)
            throw new BadOptimalBSTInputException(n + "keys, " + values.length + " values, " +
                    keyProbs.length + " key probs, and " + missProbs.length + " miss probs");
    }
    
}
