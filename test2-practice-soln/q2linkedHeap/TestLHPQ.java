package q2linkedHeap;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class TestLHPQ {

    Comparator<Integer> intCompy = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return o1.intValue() - o2.intValue();
        }
    };
    
    @Test
    public void testIncreaseKeyAt() {
        LinkedHeapPriorityQueue<Integer> testpq = 
                new LinkedHeapPriorityQueue<Integer>(new Integer[] { 17, 12, 14, 10, 9, 21, 5, 3, 8, 0},
                        intCompy);
        testpq.increaseKey(21);
        assertEquals("211217109145380", testpq.toString());
    }

    @Test
    public void testDecreaseKeyAt() {
        LinkedHeapPriorityQueue<Integer> testpq = 
                new LinkedHeapPriorityQueue<Integer>(new Integer[] { 21, 8, 17, 12, 9, 14, 5, 3, 10, 0},
                        intCompy);
        testpq.decreaseKey(8);
        assertEquals("211217109145380", testpq.toString());
    }
    
}
