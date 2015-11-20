/**
 * Generic Node implementation. 
 * 
 * This implementation of a node can be utilized for any binary tree.
 * Each node instance holds references to its left and right subtrees,
 * as well as a reference to the data it stores.
 *
 * @author    Peter Mountanos
 * @version   December 6th, 2014
 * @param <T> Any type that implements Comparable <T>
 */
public class Node<T extends Comparable <T>> implements Comparable <Node <T>>{
	
	// instance variables
	/**
	 * Node representing left subtree
	 */
	private Node<T> left;
	/**
	 * Node representing left subtree
	 */
	private Node<T> right;
	/**
	 * Data stored in node instance
	 */
	private T data;
	
	/**
	 * Constructs a Node initializing the data part according to
	 * the parameter and setting both references to subtrees to null.
	 * 
	 * @param data data to be stored in the node
	 */
	public Node(T data) {
		this(data, null, null);
	}
	
	/**
	 * Constructs a Node initializing the data part and the 
	 * subtree references according to the parameters.
	 * 
	 * @param data data to be stored in the node
	 * @param left reference to the left subtree
	 * @param right reference to the right subtree
	 */
	public Node(T data, Node<T> left, Node<T> right) {
		this.data  = data;
		this.left  = left;
		this.right = right;
	}
	
	// getters and setters
	/**
	 * Left subtree accessor. 
	 * 
	 * @return reference to the left subtree of a node
	 */
	public Node<T> getLeft() {
		return left;
	}
	
	/**
	 * Changes the reference to the left subtree to the one 
	 * specified in the parameter.
	 * 
	 * @param left reference to the new left subtree of the node.
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	/**
	 * Right subtree accessor. 
	 * 
	 * @return reference to the right subtree of a node
	 */
	public Node<T> getRight() {
		return right;
	}
	
	/**
	 * Changes the reference to the right subtree to the one 
	 * specified in the parameter.
	 * 
	 * @param right reference to the new right subtree of the node.
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	/**
	 * Returns a reference to the data stored in the node. 
	 * 
	 * @return reference to the data stored in the node
	 */
	public T getData() {
		return data;
	}
	/**
	 * Changes the data stored in the node to the one 
	 * specified in the parameter.
	 * 
	 * @param data reference to the new data of the node
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * Method used to determine if the current node is a leaf or not.
	 * A leaf occurs when a node has neither a left subtree nor a right
	 * subtree.
	 * 
	 * @return true if node instance is a leaf, otherwise false
	 */
	public boolean isLeaf() {
		return (this.left == null) && (this.right == null);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Node<T> other) {
		return this.data.compareTo(other.data);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.data.toString();
	}

}
