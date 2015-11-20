import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Generic Binary Search Tree External Iterator
 * 
 * This class constructs iterator objects for a generic
 * Binary Search Tree implementation. The iterator traversal
 * is an in-order traversal, per specification of the assignment. 
 * 
 * @author    Peter Mountanos
 * @version   December 6th, 2014
 * @param <T> Any type that implements Comparable <T>
 */
public class BSTIterator<T extends Comparable<T>> implements Iterator<T> {

	/**
	 * Reference to BST to iterate over
	 */
	private BSTRecursive<T> tree;
	/**
	 * Queue object utilized for a in-order traversal of the BST
	 */
	private Queue<T> queue;

	/**
	 * Constructor which instantiates an iterator for a BST object.
	 * This iterator iterates over the BST via an in-order traversal
	 * 
	 * @param tree reference to BST to iterate over
	 */
	public BSTIterator(BSTRecursive<T> tree) {
		this.tree = tree;
		this.initializeQueue();
	}

	/**
	 * Method to initialize the queue utilized for the in-order 
	 * traversal of the BST. 
	 */
	private void initializeQueue() {
		this.queue = new LinkedList<T>();
		this.inOrder(this.tree.root);
	}

	/**
	 * Recursive method which provides an in-order traversal of
	 * a BST, adding to a queue in order, which is then utilized
	 * by the iterator methods.
	 * 
	 * @param subTree the current sub-tree of the BST during in-order
	 * 		  traversal (reference to a BSTNode)
	 */
	private void inOrder(Node<T> subTree) {
		// don't want to keep adding if the tree is empty
		if (subTree != null) {
			// recursive call to left subtree
			inOrder(subTree.getLeft());
			// add current data to queue
			queue.add(subTree.getData());
			// recursive call to right subtree
			inOrder(subTree.getRight());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// if the queue is null or empty, there's no next item
		if (queue == null || queue.isEmpty()) return false;
		else return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		// pop off the queue if there's something to pop
		if (this.hasNext()) return this.queue.remove();
		else return null; 
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		// said we don't need to implement for project 
		// but believe this is correct (removes in-order next object)
		if (this.hasNext()) tree.remove(this.queue.remove());
	}
}
