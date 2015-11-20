package edu.nyu.cs.pjm419;

/**
 * Generic LinkedList implementation of a stack.
 * 
 * This class implements the generic Stack interface, and 
 * has additional methods such as isEmpty(). This Stack uses a
 * singly-linked-list implementation.
 * 
 * @author    Peter Mountanos (pjm419)
 * @version   0.1
 * @param <E> allows LinkedStack to handle any object of type E (generic)
 * @see       Stack
 */
public class LinkedStack <E> implements Stack <E>{
	
	// Instance Variables
	/**
	 * Node reference to the top of the stack
	 */
	private Node<E> top;	
	/**
	 * Integer representing the current size of the stack
	 */
	private int size;	
	
	/**
	 * Default constructor for LinkedStack class
	 * 
	 * This constructor initializes a default LinkedStack
	 * object, which is a generic linked list implementation
	 * of a stack.
	 */
	public LinkedStack(){
		this.top  = null;
		this.size = 0;
	}
	
	/* (non-Javadoc)
	 * @see Stack#push(E)
	 */
	@Override
	public void push(E item){
		// don't want to add a null object onto the stack
		if (item == null) return;
		
		Node<E> n = new Node<E>(item); // make a node object holding item
		
		// if the stack is empty, make it the top
		if (this.top == null){
			this.top = n;
		}
		// otherwise make it the top, and link the old top to it
		else { 
			Node<E> temp = this.top;
			this.top = n;
			this.top.setNext(temp);
		}
		
		this.size++;
	}
	
	/* (non-Javadoc)
	 * @see Stack#pop()
	 */
	@Override
	public E pop() {

		if (this.isEmpty())  return null;
		
		Node<E> retNode = null;
		
		// if the stack is size one, pop the itme and 
		// set top to null
		if (this.top.getNext() == null) {
			retNode  = this.top;
			this.top = null;
		}
		// otherwise pop the top and set the top 
		// to the popped item's next node
		else {
			retNode  = this.top;
			this.top = this.top.getNext();
		}
		
		this.size--;
		return retNode.getData();
	}

	/* (non-Javadoc)
	 * @see Stack#peek()
	 */
	@Override
	public E peek() {
		if (this.isEmpty()) return  null;
		
		return this.top.getData(); 
	}
	
	/* (non-Javadoc)
	 * @see Stack#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Stack: ");
		
		// if there's nothing in the stack, just return default
		if (this.top == null) return sb.toString();
		
		Node<E> current = this.top;
		while (current != null){
			// space separated items
			sb.append(current.getData() + " ");
			current = current.getNext();
		}
		return sb.toString();
	}
	
	/**
	 * Method which lets the user know if the stack object
	 * is empty or not. 
	 * 
	 * @return true if the stack is empty, otherwise false
	 */
	public boolean isEmpty() {
		return this.top == null;
	}
}