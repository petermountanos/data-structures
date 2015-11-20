package edu.nyu.cs.pjm419;
/**
 * Generic Node implementation to use for linked-lists.
 * 
 * This generic node can be used to implement a singly linked-
 * list, since it only carries a reference to the next node. An instance
 * of this Node class can store data of type E, making it generic. 
 * 
 * @author    Peter Mountanos (pjm419)
 * @version   0.1
 * @param <E> type of object to be stored in the Node
 */
public class Node <E> {
	// instance variables
	/**
	 * Variable to store the data that node holds. Should 
	 * be of type E (whatever this generic node is set to hold).
	 */
	private E data;
	
	/**
	 * Variable to store a reference to this Node's next
	 * Node object. This is used to facilitate the implementation
	 * of a singly linked-list.
	 */
	private Node<E> next;

	/**
	 * Overloaded constructor for Node object. It instantiates a Node
	 * object with a data field given by the parameter data, and a next
	 * field given by the parameter next.
	 * 
	 * @param next Node object to be stored in this instance's next field
	 * @param data an object of type E to be stored in this instance's data field
	 */
	public Node(Node<E> next, E data) {
		this.data = data;
		this.next  = next;
	}

	/**
	 * Overloaded constructor for Node object. It instantiates a Node
	 * object with a data field given by the parameter data, and a null
	 * reference to its next field.
	 * 
	 * @param data an object of type E to be stored in this instance's data field
	 */
	public Node(E data) {
		// call constructor Node(Node<E> next, E data)
		this(null, data);
	}

	/**
	 * Default Constructor for Node Object. It instantiates a Node
	 * object with no data and no next reference. 
	 */
	public Node() {
		// call constructor Node(Node<E> next, E data)
		this(null, null);
	}

	// GETTERS and SETTERS
	/**
	 * Getter for instance variable data. This will return this 
	 * instance's data field
	 * 
	 * @return object of type E referencing this instance's data field
	 */
	public E getData() { 
		return this.data;
	}

	/**
	 * Setter for instance variable data. This  will set this
	 * instance's data field to data.
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Getter for instance variable next. This returns the
	 * reference to this Node's next Node object.
	 * 
	 * @return a reference to this Node's next Node object
	 */
	public Node<E> getNext() {
		return this.next;
	}

	/**
	 * Setter for instance variable next. This will set
	 * this Node's next field to n.
	 * 
	 * @param n a Node object which represents this instance's
	 *        next Node
	 */
	public void setNext(Node<E> n){
		this.next = n;
	}
}