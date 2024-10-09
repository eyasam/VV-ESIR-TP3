package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class BinaryHeapTest {
	
    private BinaryHeap<Integer> tas;

    // Initialisation 
    @BeforeEach
    public void setUp() {
        tas = new BinaryHeap<>(Integer::compareTo);
    }
    

    // Test for exception thrown when popping from an empty heap
	@Test
	public void testPopOnEmptyHeap() {

		Exception e = assertThrows(NoSuchElementException.class, tas::pop);
        assertEquals("Heap is empty", e.getMessage());
	}

	
    // Test the pop method on a populated heap
    @Test
    public void testPop() {
        tas.push(0);
        tas.push(14);
        tas.push(2);
        
        assertEquals(0, tas.peek());
        assertEquals(0, tas.pop());
        assertEquals(2, tas.peek());
        assertEquals(2, tas.pop());
        assertEquals(14, tas.pop());
        assertEquals(0, tas.count());
    }
    

    // Test for exception thrown when peeking at an empty heap
	@Test
	public void testPeekOnEmptyHeap() {
		Exception e = assertThrows(NoSuchElementException.class, () -> { tas.peek();});
        assertEquals("Heap is empty", e.getMessage());
	}
	
    // Test the peek method on a non-empty heap
	 @Test
	    public void testPeek() {
	        tas.push(14);
	        tas.push(10);
	        tas.push(2);
	        tas.push(1);
	        tas.push(100);
	        assertEquals(1, tas.peek());

	    }
	    
	 // Verify that count() returns 0 for an empty heap
	 @Test
	 public void testEmptyCount() {
	    assertEquals(0, tas.count()); 
	
	}
	    
	 
	// Test the count() method for a heap with multiple elements
    @Test
    public void testCount() {
        tas.push(0);
        tas.push(14);
        tas.push(10);
        tas.push(2);
        tas.push(100);
        
        assertEquals(5, tas.count()); 
    }
    
    
    // Test adding a single element
    @Test
    public void testPushSingleElement() {
        tas.push(34);
        assertEquals(1, tas.count());
        assertEquals(34, tas.peek());
    }

    // Test to ensure duplicates work correctly
    @Test
    public void testDuplicates() {
        tas.push(4);
        tas.push(4);
        tas.push(4);
        tas.push(4);
        
        assertEquals(4, tas.peek());
        assertEquals(4, tas.pop());
        assertEquals(4, tas.pop());
        assertEquals(4, tas.pop());
        assertEquals(4, tas.pop());
        assertEquals(0, tas.count()); 
    }

    // Check the order of elements during extraction
    @Test
    public void testOrder() {
    	tas.push(0);
        tas.push(14);
        tas.push(10);
        tas.push(0);
        
        assertEquals(0, tas.pop());
        assertEquals(0, tas.pop());
        assertEquals(10, tas.pop());
        assertEquals(14, tas.pop());
    }
    
    // Test with mixed values (negative and positive)
    @Test
    public void testMixedNumbers() {
        tas.push(1);
        tas.push(0);
        tas.push(-3);
        assertEquals(-3, tas.peek());
        assertEquals(-3, tas.pop());
        assertEquals(0, tas.peek());


    }
    
    // Test with extreme values
    @Test
	public void testMaxMinValues() {
	    tas.push(Integer.MAX_VALUE);
	    tas.push(Integer.MIN_VALUE);
	    tas.push(0);
	    
	    assertEquals(Integer.MIN_VALUE,tas.pop());
	    assertEquals(0,tas.pop());
	    assertEquals(Integer.MAX_VALUE,tas.pop());
	}
    

    // Test the order of elements after multiple insertions
    @Test
    public void testOrder_MultipleInserts() {
        int[] tab = {5,3,8,-1,0,9,2,4,-14,2};
        for (int i : tab) {
            tas.push(i);
        }
        
        int[] tab_result = {-14,-1,0,2,2,3,4,5,8,9}; 
        for (int i:tab_result) {
            assertEquals(i, tas.pop());
        }}
    
  }
    

   

    

    
    