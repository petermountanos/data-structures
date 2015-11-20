package edu.nyu.cs.pjm419;

/**
 * Stack Interface used to be a template for a generic
 * stack implementation. Has the general push, pop, peek
 * and toString methods. 
 * 
 * @author    Peter Mountanos (pjm419)
 * @version   0.1
 * @param <E> allows Stack to handle any object of type E (generic)
 */
public interface Stack <E> {
	/**
	 * Add an object to the stop of the stack
	 * @param item to be added to the stack
	 */
	public void push(E item);
	
	/**
	 * Remove and return an object from the top of
	 * the stack.
	 * @return an object from the top of the stack is
	 * 		returned and removed from the stack. If the
	 * 		stack is empty,  null is returned.
	 */
	public E pop();
	
	/**
	 * Return an object from the top of the stack.
	 * @return an object from the top of the stack is
	 * 		returned. If stack is empty, null is returned.
	 */
	public E peek();
	
	/**
	 * Produces string representation of the stack.
	 * @return returns a String object that contains all
	 * 		elements stored on the stack. The elements are
	 * 		separated by spaces. The top of the stack is the
	 * 		rightmost character in the returned string.
	 */
	public String toString();
}
