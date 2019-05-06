package q6trie2array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;


public class T2ATest {

	
    private void testT2A(String[] keys) {
        String[] result = new String[keys.length];
        TrieSet testTrie = new T2ATT(keys, 0, keys.length, 0);
        int keysFound = testTrie.trie2Array(result);
        assertEquals(keys.length, keysFound);
        for (int i = 0; i < keys.length; i++)
            assertEquals(keys[i], result[i]);
    }
    
    

    @Test
    public void sample() {
       String[] data = new String[] {"ANN", "ANNA", "ANNIKA", "BETH", "BETHANY", "CARL"};
       testT2A(data);
    }
    
    @Test
    public void singleton() {
        String[] data = new String[] {"BETH"};
        testT2A(data);
  }

    @Test
    public void noSharedPaths() {
        String[] data = new String[] {"ANN", "BETH", "CARL", "DAHLIA", "EVERGREEN"};
        testT2A(data);
     }   
    
    @Test
    public void deepShare() {
        String[] data = new String[] {"CONSTANS", "CONSTANZE", "CONSTANTINE", "CONSTANTINUS", "CONSTANTIUS", "CONSTANTINOPLE", "CONSTANT", "CONSTANCE", "CONSTANTS", "CONSUBSTANIATION"};
        Arrays.sort(data);
        testT2A(data);        
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
        testT2A(data);

    }
    
	private class T2ATT extends TrieSet {
		T2ATT(String[] items, int start, int stop, int c) {
		       super();
		        if (items[start].length() == c) {
		            terminal = true;
		            start++;
		        }
		        for (char letter = 'A'; letter <= 'Z'; letter++) {
		            int end = start;
		            while (end < stop && items[end].charAt(c) == letter) end++;
		            if (start != end)
		                children[c2i(letter)] = new T2ATT(items, start, end, c+1);
		            start = end;
		        }
		}
	}

}
