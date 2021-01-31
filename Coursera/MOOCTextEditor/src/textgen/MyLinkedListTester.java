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
		System.out.println("------------------Setting up the LinkedLists before testing-------------");
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
		//System.out.println("shortList size = " + shortList.size);
		System.out.println("---------------------------End of Setup--------------------------------");
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		System.out.println("------------------Testing to get an element at index-------------");
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
		System.out.println("--------------End of testing to get an element at index-------------");
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		System.out.println("------------------Testing removal of element at index-------------");
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try //test for removal of non-existing element at the beginning
		{
			a = list1.remove(-1);
			fail("check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try //test for removal of non-existing element at the end
		{
			a = list1.remove(list1.size);
			fail("check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		// TODO: Add more tests here - Done
		System.out.println("-------------End of testing removal of element at index-------------");
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	
	{
        System.out.println("------------------Testing to add an element at the end-------------");
        list1.add(99);
        assertEquals("testAddEnd: check new last element ", (Integer)99, list1.get(3));
        assertEquals("testAddEnd: check new size of list1 ", 4, list1.size());
        
        try //test for adding empty node - throws NullPointerException
        {
        	list1.add(null);
        }
        catch (NullPointerException e) {
        }
        // TODO: implement this test - Done
        System.out.println("------------End of testing to add an element at the end-------------");
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		System.out.println("------------------Testing the size of the LinkedList-------------");
		// TODO: implement this test - Done
		assertEquals("testSize: check size is correct ", 10, longerList.size());
		longerList.remove(5);
		assertEquals("testSize: check size is correct after removal ", 9, longerList.size());
		longerList.add(99);
		assertEquals("TestSize: check size is correct after insertion ", 10, longerList.size());
		longerList.add(5, 101);
		assertEquals("testSize: check size is correct after adding at index ", 11, longerList.size());
		
		assertEquals("testSize: check size is correct in empty list ", 0, emptyList.size());
		System.out.println("------------End of testing the size of the LinkedList-------------");
	}
	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		System.out.println("------------------Testing adding an element at index-------------");
		// implement this test - Done
		// test insert at 0		
		shortList.add(0, "AA");
		assertEquals("testAddatIndex: check size is correct ", 3, shortList.size());
		assertEquals("testAddAtIndex: check first element ", "AA", shortList.get(0));
		assertEquals("testAddAtIndex: check if old first is now second ", "A", shortList.get(1));
		// test insert in middle
		shortList.add(2, "BB");
		assertEquals("testAddatIndex: check size is correct ", 4, shortList.size());
		assertEquals("testAddAtIndex: check new element ", "BB", shortList.get(2));
		assertEquals("testAddAtIndex: check if old third is now fourth ", "B", shortList.get(3));
		// test insert at end
		shortList.add(4, "C");
		assertEquals("testAddAtIndex: check size is correct ", 5, shortList.size());
		assertEquals("testAddAtIndex: check new element ", "C", shortList.get(4));
		assertEquals("testAddAtIndex: check if old fourth is still fourth ", "B", shortList.get(3));
		// test insert at invalid location
		try { //adding an element outside the list boundaries should throw an IndexOutOfBoundsException
			shortList.add(7, "Z");
			fail("Check out of bounds");
		} 
		catch (IndexOutOfBoundsException e){
		}
		// test inserting an empty element
		try { //adding an empty element should throw a NullPointerException
			shortList.add(2, null);
			fail("Check adding empty element");
		}
		catch (NullPointerException e) {
		}
		System.out.println("------------End of testing adding an element at index-------------");
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		System.out.println("---------------Testing to change an existing element at index---------");
		// TODO: implement this test
		System.out.println("longerList: " + longerList.toString());
		// test change at beginning
		longerList.set(0, 100);
		assertEquals("testSet: inserting in the middle ", (Integer)100, longerList.get(0));
		System.out.println("longerList: " + longerList.toString());
		// test change element in middle
		longerList.set(5, 99);
		assertEquals("testSet: inserting in the middle ", (Integer)99, longerList.get(5));
		System.out.println("longerList: " + longerList.toString());
		//test change element at the end
		longerList.set(9, 999);
		assertEquals("testSet: inserting in at the end ", (Integer)999, longerList.get(9));
		System.out.println("longerList: " + longerList.toString());
		// test change non existing element
		try {
			longerList.set(12, 12); //inserting at index outside bounds should trigger IndexOutOfBoundsException
			fail("check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			longerList.set(3, null);
			fail("check setting empty element");
		}
		catch (NullPointerException e){
		}
		System.out.println("----------End of testing to change an existing element at index---------");
	}
	
	
	// TODO: Optionally add more test methods.
	
}
