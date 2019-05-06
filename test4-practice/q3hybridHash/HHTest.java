package q3hybridHash;

import static org.junit.Assert.*;

import org.junit.Test;

public class HHTest {

    static String[] data = { "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
            "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
            "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
            "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
            "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
            "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
            "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
            "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
            "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
            "HELENE" };
    private HybridHashSet testSet;
    

    protected void reset() {
        testSet = new HybridHashSet(8);
    }
    
    
    @Test(timeout=3000)
    public void noCollision() {
        reset();
        testSet.add("JUSTIN");
        testSet.add("JUSTINIAN");
        testSet.add("JUSTINITANUS");
        assertTrue(testSet.contains("JUSTIN"));
        assertTrue(testSet.contains("JUSTIN"));
        assertTrue(testSet.contains("JUSTIN"));
        assertFalse(testSet.contains("JUSTINIANIAN"));       
    }

    @Test(timeout=3000)
    public void oneCollision() {
        reset();
        testSet.add("HELEN");
        testSet.add("HERODIAS");
        assertTrue(testSet.contains("HELEN"));
        assertTrue(testSet.contains("HERODIAS"));
        assertFalse(testSet.contains("CONSTANTIUS"));
               
    }

    @Test(timeout=3000)
    public void nearMiss() {
        reset();
        testSet.add("AUGUSTUS");
        testSet.add("ELLIE");
        testSet.add("HELLEN");
        assertTrue(testSet.contains("HELLEN"));
        assertTrue(testSet.contains("ELLIE"));
        assertTrue(testSet.contains("AUGUSTUS"));
        assertFalse(testSet.contains("CONSTANTIUS"));
    }

    @Test(timeout=3000)
    public void wouldCollide() {
        reset();
        testSet.add("AUGUSTUS");
        testSet.add("ELLIE");
        testSet.add("HELLEN");
        assertFalse(testSet.contains("HERODIAS"));
    }
    
    @Test(timeout=3000)
    public void pileUp() {
        reset();
        String[] localData = { "JUSTIN", "CAESARION",
                "JONATHAN", "HEROD","ANNIE","ANNE",
                "CAESAREA", "ELAINE","ANNETTE","JOHNBAPTIST"};
        for (String x: localData)
            testSet.add(x);
        for (String x: localData)
            assertTrue(testSet.contains(x));
        assertFalse(testSet.contains("OTHELLO"));
    }
 
    @Test(timeout=3000)
    public void stress() {
        reset();
        for (String x: data)
            testSet.add(x);
        for (String x: data)
            assertTrue(testSet.contains(x));
        assertFalse(testSet.contains("OTHELLO"));
    }
}
