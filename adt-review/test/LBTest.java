package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import adt.Bag;
import impl.ListBag;

public class LBTest extends BagTest {

    
    protected void reset() {
        testBag = new ListBag<String>();
    }
    protected void resetInt() {
        testBagInt = new ListBag<Integer>();
    }
    
    @Test
    public void myTest() {
        reset();
        Bag<String> bag = new ListBag<>();
        bag.add("babs");
        bag.remove("babs");
        for (int i = 0; i < getData().length; i++)
            bag.remove(getData()[i]);
        for (int i = 0; i < getData().length; i++)
            assertEquals(0, bag.count(getData()[i]));
        assertEquals(0, bag.size());
    }

}
