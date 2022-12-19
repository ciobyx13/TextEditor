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
		size = 0;
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		if (element == null) throw new NullPointerException();
		LLNode elem = new LLNode(element);
		elem.prev = tail.prev;
		elem.next = tail;
		tail.prev.next = elem;
		tail.prev = elem;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index = "+index+". Size = "+size);
		LLNode node = head.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return (E) node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException
	{
		if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index = "+index+". Size = "+size);
		if (element == null) throw new NullPointerException();
		LLNode elem = new LLNode(element);
		LLNode previousNode = head;
		for (int i = 0; i < index; i++) {
			previousNode = previousNode.next;
		}
		elem.prev = previousNode;
		elem.next = previousNode.next;
		previousNode.next.prev = elem;
		previousNode.next = elem;
		size++;
		return;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index = "+index+". Size = "+size);
		LLNode node = head.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
		return (E) node.data;
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
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index = "+index+". Size = "+size);
		if (element == null) throw new NullPointerException();
		LLNode node = head.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		node.data = element;
		return (E) node.data;
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
	}

}
