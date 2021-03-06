import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/*
 * Philip Ni (PCN7)
 * pcn7@pitt.edu
 * CS1632 - Deliverable 4
 * JUnit-based Property-based Tests
 * University of Pittsburgh
 */

public class JavaArraysTests {
	int intArr[][];
    
    //Generate 100 random int arrays
    private void createArrays() {
        Random rndm = new Random();
        intArr = new int[100][];	//100 int arrays
        int length;
        for(int i = 0; i < 100; i++) {
            length = rndm.nextInt(10000) + 1;    //length should be between 1 and 10,000
            intArr[i] = new int[length];
            for(int j = 0; j < length; j++) {
                intArr[i][j] = rndm.nextInt(100000) + 1;  //generate a random number between 1 and 100,000
            }
        }
    }

    //Setup randomly generated arrays
    @Before
    public void setup() {
        createArrays();
    }
    
    // Test array sort: size before sorting should be the same
    // as the size after sorting
    @Test
    public void sortSizeTest() {
        for(int i = 0; i < intArr.length; i++) {
        	int originalCopy[] = Arrays.copyOf(intArr[i], intArr[i].length);	//create deep copy of original
        	Arrays.sort(intArr[i]);	//sort array
        	
        	assertEquals(originalCopy.length, intArr[i].length);
        }
    }

    // Test array sort: sorting arrays with the same content (e.g. the same copy) should
    // result in the same sorted array
    @Test
    public void sortConsistencyTest() {
    	for(int i = 0; i < intArr.length; i++) {
    		int originalCopy[] = Arrays.copyOf(intArr[i], intArr[i].length);	//create deep copy of original
    		Arrays.sort(intArr[i]);	//sort original array
    		Arrays.sort(originalCopy);	//sort copy array
    		
    		for(int j = 0; j < originalCopy.length; j++) {	//compare elements in original are same position as copy
    			assertEquals(intArr[i][j], originalCopy[j]);
    		}
    	}
    }
    
    // Test array sort: there should be the same exact content in the sorted array as the unsorted array
    // (although in a different order)
    @Test
    public void sortSameContentTest() {
    	for(int i = 0; i < intArr.length; i++) {
    		int originalCopy[] = Arrays.copyOf(intArr[i], intArr[i].length);	//create deep copy of original
    		Arrays.sort(intArr[i]);	//sort array
    		
    		////keys are unique entries; values are total number of occurrences in the array
    		Map<Integer, Integer> originalHM = new HashMap<Integer, Integer>();
    		Map<Integer, Integer> copyHM = new HashMap<Integer, Integer>();
    		
    		//setting up hashmaps
    		for(int j = 0; j < intArr[i].length; j++) {
    			//generate hashmap for copy
    			if(copyHM.containsKey(originalCopy[j])) {	//if key exists
    				copyHM.put(originalCopy[j], copyHM.get(originalCopy[j]) + 1);	//increment value
    			}
    			else {	
    				copyHM.put(originalCopy[j], 1);	//if key does not exit; init to 1
    			}
    			
    			//generate hashmap for original
    			if(originalHM.containsKey(intArr[i][j])) {
    				originalHM.put(intArr[i][j], originalHM.get(intArr[i][j]) + 1);	//increment value
    			}
    			else {
    				originalHM.put(intArr[i][j], 1);	//if key does not exit; init to 1
    			}
    		}
    		
    		//compare hashmaps; the hashmaps should be the same
    		assertTrue(copyHM.equals(originalHM));
    	}
    }
    
    // Test array sort: sorting a sorted array should not change the sorted array
    @Test
    public void sortTwiceTest() {
    	for(int i = 0; i < intArr.length; i++) {
    		Arrays.sort(intArr[i]);	//first, sort original array
    		int sortedCopy[] = Arrays.copyOf(intArr[i], intArr[i].length);	//create deep copy of sorted original
    		Arrays.sort(intArr[i]);	//sort sorted-original
    		
    		//compare element by element
    		for(int j = 0; j < intArr[i].length; j++) {
    			assertEquals(sortedCopy[j], intArr[i][j]);
    		}
    	}
    }
    
    // Test array sort: sorting gives the correct ascending order
    @Test
    public void sortOrderTest() {
    	for(int i = 0; i < intArr.length; i++) {
    		Arrays.sort(intArr[i]);	//sort array
    		
    		//make sure that an entry is less than its proceeding entry
    		for(int j = 1; j < intArr[i].length; j++) {
    			assertTrue(intArr[i][j] >= intArr[i][j-1]);
    		}
    	}
    }
}
































