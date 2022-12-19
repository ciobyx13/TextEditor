/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		try {
			list1.remove(5);
			fail("Check index out of bounds exception");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.remove(-1);
			fail("Check index out of bounds exception");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail("Check null reference exception");
		}
		catch (NullPointerException e) {
		
		}
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		MyLinkedList<Integer> ListTestSize = new MyLinkedList<Integer>();
		for (int i = 0; i < 10*LONG_LIST_LENGTH; i++)
		{
			ListTestSize.add(i);
		}
		assertEquals("Size test: ", 10*LONG_LIST_LENGTH, ListTestSize.size);
		ListTestSize.add(10);
		assertEquals("Size test: ", 10*LONG_LIST_LENGTH+1, ListTestSize.size);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		MyLinkedList<Integer> ListTestAddAtIndex = new MyLinkedList<Integer>();
		ListTestAddAtIndex.add(0,10);
		assertEquals("Add at index test: ", 10, (int)ListTestAddAtIndex.get(0));
		ListTestAddAtIndex.add(1,20);
		assertEquals("Add at index test: ", 20, (int)ListTestAddAtIndex.get(1));
		ListTestAddAtIndex.add(0,5);
		assertEquals("Add at index test: ", 5, (int)ListTestAddAtIndex.get(0));
		assertEquals("Add at index test: ", 20, (int)ListTestAddAtIndex.get(2));
		ListTestAddAtIndex.add(2,15);
		assertEquals("Add at index test: ", 15, (int)ListTestAddAtIndex.get(2));
		assertEquals("Add at index test: ", 20, (int)ListTestAddAtIndex.get(3));
		try {
			ListTestAddAtIndex.add(5,35);
			fail("Check index out of bounds exception");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			ListTestAddAtIndex.add(-1,2);
			fail("Check index out of bounds exception");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		MyLinkedList<Integer> ListTestSet = new MyLinkedList<Integer>();
		ListTestSet.add(10);
		ListTestSet.add(20);
		ListTestSet.add(30);
		ListTestSet.set(0,11);
		ListTestSet.set(1,21);
		ListTestSet.set(2,31);
		assertEquals("Test Set: ", 11, (int)ListTestSet.get(0));
		assertEquals("Test Set: ", 21, (int)ListTestSet.get(1));
		assertEquals("Test Set: ", 31, (int)ListTestSet.get(2));

		try {
			ListTestSet.set(2,null);
			fail("Check null reference exception");
		}
		catch (NullPointerException e) {
		
		}
		try {
			ListTestSet.set(3,15);
			fail("Check index out of bounds exception");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
