package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		System.out.println("head gemaakt");
		tail = new LLNode<E>(null);
		System.out.println("tail gemaakt");
		size = 0;
		head.next = tail;
		tail.prev = head;
		System.out.println("Linked List gemaakt met head and tail nodes. ");
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 * @throws NullPointerException
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element.equals(null)) {
			throw new NullPointerException("Adding an element without any data is not allowed");
		}
		LLNode<E> newElement = new LLNode<E>(element);
		newElement.prev = tail.prev; //adding element at the end
		(tail.prev).next = newElement;
		newElement.next = tail;
		tail.prev = newElement;
		/*(head.next).prev = newElement; adding element at the beginning.
		newElement.next = head.next;
		newElement.prev = null;
		head.next = newElement;*/
		size = size + 1;
		System.out.println("Element toegevoegd, size is: " + size + ", Element is: " + element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index >= this.size  || index < 0) {
			throw new IndexOutOfBoundsException("index falls outside size of LinkedList");//return null;
		}
		LLNode<E> search = head;
		for(int i = 0; i < this.size; i++) {
			LLNode<E> getNode = search.next;
			if(i == index) {
				return getNode.getData();
			}
			else {
				search = search.next;
			}
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element.equals(null)) {
			throw new NullPointerException("Adding an element without any data is not allowed");
		}
		System.out.println("test add at index: inserting at index " + index + ", list size was: " + this.size);
		if(index == this.size) {//list empty or insert after current last element
			this.add(element);
		} 			
		else if(index >= 0 && index < this.size) {//index < amount of existing elements AND > 0 (list not empty) 
			LLNode<E> search = head.next;//search initialized with first element
			for(int i = 0; i < this.size; i++) {
				if(i == index) {//index found
					LLNode<E> insert = new LLNode<E>(element);//make new element with data E
					insert.next = search;//link new element.next element previous at index
					insert.prev = search.prev;//link new element.prev to element previously linked at index
					(search.prev).next = insert;//link previous element to new element
					search.prev = insert;
					this.size++;
					System.out.println("Element toegevoegd at index: " + i + ", new size is: " + size + ", Element is: " + element);
				} else {//not the right index
					search = search.next;//search initialized with next element 
				}
			}
		} else {
			throw new IndexOutOfBoundsException("Cannot insert at given index");
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index >= this.size  || index < 0) {
			throw new IndexOutOfBoundsException("index falls outside size of LinkedList");//return null;
		}
		LLNode<E> search = head.next;
		for(int i = 0; i < this.size; i++) {
//			LLNode<E> getNode = search.next;
			if(i == index) {
				System.out.println("remove: Deleting element " + search.getData() + " at index " + i);
				(search.prev).next = search.next;
				(search.next).prev = search.prev;
				size --;
				return search.getData();
			}
			search = search.next;
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> returnSearch = new LLNode<E>(null);//new node in order to return data
		if(element.equals(null)) { //not allowed to add element with no data 
			throw new NullPointerException("Adding an element without any data is not allowed");
		}
		if(index >= 0 && index < this.size) {//index < amount of existing elements AND > 0 (list not empty) 
			LLNode<E> search = head.next;//search initialized with first element
			for(int i = 0; i < this.size; i++) {
				if(i == index) {//index found
					returnSearch.data = search.data; //replace returnSearch with element to return
					System.out.println("Replaced old Element " + search.getData() + " at index: " + i + ", new element is: " + element);
					search.data = element; //save new data in existing element
					return returnSearch.getData();
				} else {//not the right index
					search = search.next;//search initialized with next element 
				}
			}
		} 
		else {
			throw new IndexOutOfBoundsException("Cannot insert at given index");
		}
		return returnSearch.getData();
	}
	
	/**
	 * Prints out all elements of MyLinkedList
	 */
	@Override
	public String toString() {
		if(this.size == 0) {
			return "No Elements in list";
		}
		String s = "Element ";
		for(int i = 0; i < this.size; i++) {
			s = s + i + " = " + this.get(i).toString() + ", ";
		}
		return s;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
		if(e != null) {
			System.out.println("Node gemaakt!, data is:" + e.toString());
		}
	}
	public E getData() {
		return this.data;
	}
	
	public String toString() {
		return (String) data;
	}
	
}
