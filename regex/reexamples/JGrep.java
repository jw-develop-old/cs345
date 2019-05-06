package reexamples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JGrep {

    public static void main(String[] args) {
    	if (args.length != 2) {
    		usage();
    		System.exit(0);
        }
        Scanner source = null;
        if (args[1].startsWith("http")) {
            URI uri;
            try {
                uri = new URI(args[1]);
                URL url = uri.toURL();
                source = new Scanner(url.openStream());
            } catch (Exception e) {
                System.out.println("Interpreted " + args[1] + " as a URL, but it was bad.");
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        else {
            try {
                source = new Scanner(new File(args[1]));
            } catch (FileNotFoundException e) {
                System.out.println("Interpreted " + args[1] + " as a file, but it was bad.");
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        
        //Pattern pat = Pattern.compile("\\b[A-Za-z]*ing\\b");
        Pattern pat = Pattern.compile(args[0]);
        while (source.hasNext()) {
            String line = source.nextLine();
            Matcher mat = pat.matcher(line);
            while (mat.find())
                System.out.println(mat.group());
        }
        
    }
    
    private static void usage() {
    	System.out.println("Usage: java reexamples.JGrep regex resource " 
    			+ "\n\twhere regex is a regular expression as used by the java.util.regex package "
    			+ "\n\tand resource is the name of a file or a URL."
    			+ "\n\texample: java reexamples.JGrep \"\\\\b[A-Za-z]*ing\\\\b\" shakespeare.txt"
    			+ "\n\twill find all the words ending with 'ing' in sheakespeare.txt");
    }
}
