package q5trieIt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

public class TrieItTest {

    private void testIt(String[] keys) {
        TrieSet testTrie = new TrieSet(keys);//, 0, keys.length, 0);
        int i = 0;
        for (String x : ableToAtor(testTrie.iterator())) 
            assertEquals(keys[i++], x);
        
    }
    
    
    private Iterable<String> ableToAtor(Iterator<String> it) {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return it;
            }
        };
    }


    @Test
    public void sample() {
       String[] data = new String[] {"ANN", "ANNA", "ANNIKA", "BETH", "BETHANY", "CARL"};
       testIt(data);
    }
    
    @Test
    public void singleton() {
        String[] data = new String[] {"BETH"};
        testIt(data);
  }

    @Test
    public void noSharedPaths() {
        String[] data = new String[] {"ANN", "BETH", "CARL", "DAHLIA", "EVERGREEN"};
        testIt(data);
     }   
    
    @Test
    public void deepShare() {
        String[] data = new String[] {"CONSTANS", "CONSTANZE", "CONSTANTINE", "CONSTANTINUS", "CONSTANTIUS", "CONSTANTINOPLE", "CONSTANT", "CONSTANCE", "CONSTANTS", "CONSUBSTANIATION"};
        Arrays.sort(data);
        testIt(data);        
    }
    
    @Test
    public void stress() {
        String[] data = new String[] {
                "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
                "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
                "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
                "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
                "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
                "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
                "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
                "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
                "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
                "HELENE"  
        };
        Arrays.sort(data);
        testIt(data);

    }
}
