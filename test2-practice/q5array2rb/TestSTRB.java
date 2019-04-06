package q5array2rb;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestSTRB {

    
    private static void testForGivenArray(String[] keys) {
         RBNode tree = SortedToRB.sortedToRB(keys);
         //System.out.println(tree);
         assertTrue(verifyRB(tree));
         assertTrue(verifyLLRBContents(tree, keys));
     }
     
     private static boolean verifyRB(RBNode root) {
         if (root == null) return true;
         else if (!verifyRB(root.left) || !verifyRB(root.right))
             return false;
         else if (root.right != null && root.right.isRed && root.isRed)
             return false;
         else if (root.left != null && root.left.isRed && root.isRed)
             return false;
         else {
             int lbh = root.left == null ? 0 : root.left.blackHeight,
                 rbh = root.right == null ? 0 : root.right.blackHeight;
             if (lbh != rbh) 
                 return false;
             else {
                 root.blackHeight = (root.isRed ? 0 : 1) + lbh;
                 return true;
             }
         }
     }

     private static class BadContentException extends RuntimeException {
         private static final long serialVersionUID = -7143060813748012904L;
     }
     private static boolean verifyLLRBContents(RBNode root, String[] keys) {
         try {
             return verifyLLRBContents(root, keys, 0) == keys.length;
         } catch (BadContentException bce) {
             return false;
         }
     }

     private static int verifyLLRBContents(RBNode root, String[] keys, int i) {
         if (root == null) return i;
         else {
             i = verifyLLRBContents(root.left, keys, i);
             if (!root.key.equals(keys[i]))
                 throw new BadContentException();
             else
                 return verifyLLRBContents(root.right, keys, i+1);
         }
     }


     @Test
     public void testSize1() {
         testForGivenArray(new String[] {"A"});
     }
     @Test
     public void testSize2() {
         testForGivenArray(new String[] {"A", "B"});
     }
     @Test
     public void testSize3() {
         testForGivenArray(new String[] {"A", "B", "C"});
     }

     @Test
     public void testSize4() {
         testForGivenArray(new String[] {"A", "B", "C", "D"});
     }
     @Test
     public void testSize5() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E"});
     }
     @Test
     public void testSize6() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F"});
     }

     @Test
     public void testSize7() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G"});
     }

     @Test
     public void testSize8() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H"});
     }

     @Test
     public void testSize10() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"});
     }

    @Test
     public void testSize12() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"});
     }

     @Test
     public void testSize11() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"});
     }

  
     @Test
     public void testSize14() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"});
     }

     @Test
     public void testSize15() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"});
     }

     @Test
     public void testSize16() {
         testForGivenArray(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
     }

    
}
