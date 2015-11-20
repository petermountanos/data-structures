/**
 * Generic AVL Node implementation. 
 * 
 * An implementation of an AVL node is slightly more complex than
 * an implementation of a binary tree node. An AVL node also must account
 * for its height. It also needs helper methods to determine its balance 
 * factor, and ability to update its own height. These are necessary to keep
 * the AVL tree balanced.
 *
 * @author    Peter Mountanos
 * @version   December 6th, 2014
 * @param <T> Any type that implements Comparable <T>
 */
public class AVLNode<T extends Comparable<T>> extends Node<T>{

	// instance variables
	/**
	 * Integer representing the current height of the node
	 */
	private int height;
	
	/**
	 * 
	 * @param item
	 */
	public AVLNode(T item) {
		this(item, null, null);
	}
	
	/**
	 * 
	 * @param item
	 * @param left
	 * @param right
	 */
	public AVLNode(T item, AVLNode<T> left, AVLNode<T> right) {
		super(item, left, right);
		this.updateHeight();
	}
	
	// getters and setters
	/**
	 * Returns a reference to the height of the node. 
	 * 
	 * @return reference to the height of the node
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Changes the height information stored in the node to the one 
	 * specified in the parameter.
	 * 
	 * @param height reference to the new height of the node
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Method used to calculate the balance factor of an AVLNode. 
	 * The balance factor is defined as the difference between the
	 * heights of the left and right subtrees of n. 
	 * 
	 * @return the balance factor at node n
	 */
	public int balanceFactor() {
		
		// cast left and right subtrees to avl nodes
		AVLNode<T> left  = (AVLNode<T>) this.getLeft();
		AVLNode<T> right = (AVLNode<T>) this.getRight();
		
		// if either subtree of n is empty, just return n's height
		if (left == null || right == null) 
			return this.getHeight();
		// else return the difference between right/left subtrees
		else
			return right.getHeight() - left.getHeight();
	}
	
	/**
	 * Method used to update the height of an AVLNode instance.
	 * 
	 * This method is utilized after an insertion/removal from an
	 * AVL Tree. The height of a node is based on the amount of sub-nodes
	 * it contains. The height is the max between the left/right sub-nodes
	 * plus one, to account for node n.
	 */
	public void updateHeight() {
		
		// cast left and right subtrees to avl nodes		
		AVLNode<T> left  = (AVLNode<T>) this.getLeft();
		AVLNode<T> right = (AVLNode<T>) this.getRight();
		
		// height of a node with no sub-nodes is 1
		if (this.isLeaf()) 
			this.setHeight(1);
		else if (left == null)
			// get right's height if there's no left sub-node + 1
			this.setHeight(right.getHeight() + 1);
		else if (right == null)
			// get left's height if there's no right sub-node + 1
			this.setHeight(left.getHeight() + 1);
		else
			// get max between left/right height + 1
			this.setHeight(Math.max(right.getHeight(), left.getHeight()) + 1);
	}
}
