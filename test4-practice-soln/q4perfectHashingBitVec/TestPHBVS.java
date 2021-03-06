package q4perfectHashingBitVec;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TestPHBVS {
    protected Set<String> testSet;
    protected void reset() {testSet = new PerfHashBVSet<String>(data);}
    protected static Random randy = new Random(System.currentTimeMillis());
    
    protected void populate() {
            for (int i = 0; i < data.length; i++)
                    testSet.add(data[i]);
    }

    
    
    protected static String[] data = 
        {"Minnesota", 
        "Texas", 
        "Oregon", 
        "New Jersey", 
        "Pennsylvania", 
        "Massachusetts", 
        "Arizona", 
        "Michigan", 
        "Ohio", 
        "New York", 
        "Florida", 
        "Colorado", 
        "Alabama", 
        "Kentucky", 
        "Kansas", 
        "Alaska"  };
    /*
    public static void main(String[] args) {
        for (String s : data)
            System.out.println((s.hashCode() & 0x7fffffff) % 100);
    }
    */

// let's hope that the implicit static initializer statements
// are executed in the order they appear in the file
protected int[] marks = new int[data.length];

protected void clearMarks() {
        for (int i = 0; i < marks.length; i++)
                marks[i] = 0;
}

protected int indexForDatum(String datum) {
        int index = -1;
        for (int i = 0; i < data.length && index == -1; i++)
                if (data[i].equals(datum))
                        index = i;
        return index;
}
@Test
public void initialEmpty() {
        reset();
        assertTrue(testSet.isEmpty());
}

@Test
public void initialSize() {
        reset();
        assertEquals(0, testSet.size());
}

@Test
public void addNotEmpty() {
        reset();
        testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
}

@Test
public void addSize() {
        reset();
        testSet.add(data[0]);
        assertEquals(1, testSet.size());
}

@Test
public void twoAddsUniqueSize() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        assertFalse(testSet.isEmpty());
        assertEquals(2, testSet.size());
}

@Test
public void twoAddsIdenticalSize() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
}

@Test
public void addsUnique() {
        reset();
        for (int i = 0; i < 8; i++) 
                testSet.add(data[i]);
        assertFalse(testSet.isEmpty());
        assertEquals(8, testSet.size());
}

@Test
public void addsIdentical() {
        reset();
        for (int i = 0; i < 8; i++) 
                testSet.add(data[0]);
        assertFalse(testSet.isEmpty());
        assertEquals(1, testSet.size());
}

@Test
public void addsMix() {
        reset();
        for (int i = 0; i < 50; i++)
                testSet.add(data[randy.nextInt(data.length)]);
        assertFalse(testSet.isEmpty());
        //assertEquals(data.length, testSet.size());
}
@Test
public void addsLots() {
        reset();
        populate();
        assertFalse(testSet.isEmpty());
        assertEquals(data.length, testSet.size());
}

@Test
public void containsInitial() {
        reset();
        assertFalse(testSet.contains(data[0]));
}

@Test
public void containsOne() {
        reset();
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
}

@Test
public void containsTwoUnique() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
}

@Test
public void containsTwoIdentical() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
}


@Test
public void containsLots() {
        reset();
        populate();
        for (int i = 0; i < data.length; i++)
                assertTrue(testSet.contains(data[i]));
}

@Test
public void removeInitial() {
        reset();
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
}

@Test
public void removeSpurious() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.remove(data[2]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertFalse(testSet.contains(data[2]));
        assertEquals(2, testSet.size());
        assertFalse(testSet.isEmpty());
}

@Test
public void removeOnlySingleton() {
        reset();
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
}

@Test
public void removeOnlyMultiple() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[0]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertEquals(0, testSet.size());
        assertTrue(testSet.isEmpty());
}

@Test
public void removeSingletonAmongMany() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
}

@Test
public void removeMultipleAmongMany() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        assertFalse(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(4, testSet.size());
        assertFalse(testSet.isEmpty());
}

@Test
public void addSameAfterRemove() {
        reset();
        testSet.add(data[0]);
        testSet.add(data[1]);
        testSet.add(data[2]);
        testSet.add(data[1]);
        testSet.add(data[0]);
        testSet.add(data[3]);
        testSet.add(data[4]);
        testSet.add(data[1]);
        testSet.add(data[4]);
        testSet.add(data[0]);
        testSet.remove(data[0]);
        testSet.add(data[0]);
        assertTrue(testSet.contains(data[0]));
        assertTrue(testSet.contains(data[1]));
        assertTrue(testSet.contains(data[2]));
        assertTrue(testSet.contains(data[3]));
        assertTrue(testSet.contains(data[4]));
        assertEquals(5, testSet.size());
        assertFalse(testSet.isEmpty());
 }

 @Test
 public void addDifferentAfterRemove() {
         reset();
         testSet.add(data[0]);
         testSet.add(data[1]);
         testSet.add(data[2]);
         testSet.add(data[1]);
         testSet.add(data[0]);
         testSet.add(data[3]);
         testSet.add(data[4]);
         testSet.add(data[1]);
         testSet.add(data[4]);
         testSet.add(data[0]);
         testSet.remove(data[0]);
         testSet.add(data[4]);
         testSet.add(data[5]);
         assertFalse(testSet.contains(data[0]));
         assertTrue(testSet.contains(data[1]));
         assertTrue(testSet.contains(data[2]));
         assertTrue(testSet.contains(data[3]));
         assertTrue(testSet.contains(data[4]));
         assertTrue(testSet.contains(data[5]));
         assertEquals(5, testSet.size());
         assertFalse(testSet.isEmpty());
 }

 @Test 
 public void removeSomeOfMany() {
         reset();
         populate();
         testSet.remove(data[3]);
         testSet.remove(data[4]);
         testSet.remove(data[9]);
         for (int i = 0; i < data.length; i++)
                 if (i == 3 || i == 4 || i == 9)
                         assertFalse(testSet.contains(data[i]));
                 else
                         assertTrue(testSet.contains(data[i]));
         assertEquals(data.length - 3, testSet.size());
 }

 @Test
 public void removeAllOfMany() {
         reset();
         populate();
         for (int i = 0; i < data.length; i++)
                 testSet.remove(data[i]);
         for (int i = 0; i < data.length; i++)
                 assertFalse(testSet.contains(data[i]));
         assertEquals(0, testSet.size());
 }



}
