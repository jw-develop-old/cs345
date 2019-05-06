package test;

import org.junit.Test;

import impl.*;
import adt.*;

public class MyTest {

	@Test
	public void normal() {
		
		int[] start = {
				3,5,6,4
		};
		
		BArrayNSet set = new BArrayNSet(20);
		
		for (int item : start)
			set.add(item);
			
		for (int item : set)
			System.out.println(item);
		
		assert(true);
	}
	
	@Test
	public void joshTest() {
        BArrayNSet set = new BArrayNSet(10);
        BArrayNSet set2 = new BArrayNSet(10);
        
        for(int i = 0; i<5; i++) {
            set.add(i);
        }
        for(int i = 4; i<7; i++) {
            set2.add(i);
        }
        
        System.out.println("Set1: "+set.atoString());
        System.out.println("Set2: "+set2.atoString());
        NSet inter = set.intersection(set2);
        System.out.println("inter: "+((BArrayNSet)inter).atoString());
        
        NSet union = set.union(set2);
        System.out.println("union: "+((BArrayNSet)union).atoString());
        
        NSet dif = set.difference(set2);
        System.out.println("dif: "+((BArrayNSet)dif).atoString());
        
        NSet comp = set.complement();
        System.out.println("comp: "+((BArrayNSet)comp).atoString());
    }

	@Test
	public void empty() {
		
		int[] start = {
				
		};
		
		Set<Integer> set = new BArrayNSet(20);
		
		for (int item : start)
			set.add(item);
			
		for (int item : set)
			System.out.println(item);
		
		assert(true);
	}

}