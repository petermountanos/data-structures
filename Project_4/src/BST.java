import java.util.Iterator;

/**
 * Generic Binary Search Tree Interface
 * 
 * This interface delineates the necessary methods for a 
 * binary search tree implementation.
 * 
 * @author     Peter Mountanos
 * @version    December 6th, 2014
 * @param <T>  Any type that implements Comparable <T> 
 */
public interface BST  <T extends Comparable <T>> {
	
	/**
	 * Adds an item to the BST.
	 * 
	 * @param item a new item to be added to the tree (ignored if null)
	 */
	public void insert(T item);
	
	/**
	 * Remove the given item from this BST. If item is null or is not 
	 * found in this BST, the tree does not change.
	 * 
	 * @param item an element to be removed
	 */
	public void remove(T item);
	
	/**
	 * Returns a reference to the item stored in the BST whose value is 
	 * equal to the value of the parameter.  
	 * 
	 * @param  item the value whose reference in the BST we are after
	 * @return a reference to the value equal to the item if found, else null
	 */
	public T get(T item);
	
	/**
	 * Returns a truth value regarding whether there is an item stored in the
	 * BST whose value is equal to the value of the parameter.
	 * 
	 * @param  item the value whose reference in the BST we are after
	 * @return true if item is in BST, false otherwise
	 */
	public boolean contains(T item);
	
	/**
	 * Method to return the current size of the BST.
	 * 
	 * @return the number of nodes in the tree
	 */
	public int size();
	
	/**
	 * Method to determine if the BST is empty.
	 * 
	 * @return true if the tree is empty, else false
	 */
	public boolean isEmpty();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString();
	

	/**
	 * Method to generate an iterator of the current instance of BST.
	 * 
	 * @return an Iterator of the given BST
	 */
	public Iterator<T> iterator();
}
