import java.util.Iterator;

/**
 * Generic Binary Search Tree Implementation
 * 
 * This class follows the API outlined in the BST interface. It contains
 * all of the methods necessary to instantiate and follow the preconditions
 * set forth by a binary search tree.
 * 
 * @author    Peter Mountanos
 * @version   December 6th, 2014
 * @param <T> Any type that implements Comparable <T>
 * @see       BST
 */
public class BSTRecursive<T extends Comparable<T>> implements BST<T> {

	// instance variables
	/**
	 * Reference to a Node which represents the root of the BST 
	 */
	protected Node<T> root;
	/**
	 * Variable storing the current size of the dictionary
	 */
	protected int size;

	/**
	 * Constructor to create an empty binary search tree. 
	 */
	public BSTRecursive() {
		this.root = null;
		this.size = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see BST#insert(java.lang.Comparable)
	 */
	@Override
	public void insert(T item) {
		if (item != null) 
			this.root = recInsert(item, root);
		this.size++;
	}

	/**
	 * Recursively add an item to this BST.
	 * 
	 * @param  item item to be added
	 * @param  tree root of the subtree into which the node will be added
	 * @return a reference to this BST after the item was inserted
	 */
	protected Node<T> recInsert(T item, Node<T> tree) {
		// if we've reached the bottom
		if (tree == null) 
			tree = new Node<T>(item); 
		// go left if its less than or equal to current tree
		else if (item.compareTo(tree.getData()) <= 0)
			tree.setLeft(recInsert(item, tree.getLeft()));
		// go right if its greater than current tree
		else
			tree.setRight(recInsert(item, tree.getRight()));
		return tree;
	}

	/*
	 * (non-Javadoc)
	 * @see BST#remove(java.lang.Comparable)
	 */
	@Override
	public void remove(T item) {
		// only search for remove if item isn't null
		if (item != null)
			this.root = recRemove(item, root);
	}

	/**
	 * Recursively remove an item from this BST.
	 * 
	 * @param  item  item to be removed
	 * @param  tree  root of the subtree from which the item will be removed 
	 * @return reference to this BST after the item was removed 
	 */
	protected Node<T> recRemove(T item, Node<T> tree) {
		if (tree == null) 
			;// do nothing, item not in tree
		// go down left subtree
		else if (item.compareTo(tree.getData()) < 0)
			tree.setLeft(recRemove(item, tree.getLeft()));
		// go down right subtree
		else if (item.compareTo(tree.getData()) > 0)
			tree.setRight(recRemove(item, tree.getRight()));
		else // found the node to remove 
			tree = removeNode(tree);

		return tree;
	}

	/**
	 * Remove a particular node - the actual action depends on number of 
	 * children that the node has.
	 *  
	 * @param  tree the node to be removed 
	 * @return reference to this BST after node was removed 
	 */
	protected Node<T> removeNode(Node<T> tree) {
		// if there's only a right child return it
		if (tree.getLeft() == null) {
			this.size--;
			return tree.getRight();
		}
		// if there's only a left child return it
		else if (tree.getRight() == null) {
			this.size--;
			return tree.getLeft();
		}
		// if there's two children
		else {
			// get the predecessor node and set it to current node
			// to be removed, then remove the old predecessor
			T data = getPredecessor(tree.getLeft());
			tree.setData(data);
			// go left once and then go all the way right
			tree.setLeft(recRemove(data, tree.getLeft()));
			return tree;
		}
	}

	/**
	 * Obtains the predecessor of a node (according to BST ordering).
	 *  
	 * @param  tree node whose predecessor we are after
	 * @return the data contained in the predecessor node
	 */
	protected T getPredecessor(Node<T> tree) {
		// go all the way to the right
		while (tree.getRight() != null) 
			tree = tree.getRight();
		return tree.getData();
	}

	/*
	 * (non-Javadoc)
	 * @see BST#get(java.lang.Comparable)
	 */
	@Override
	public T get(T item) {
		return recGet(item, this.root);
	}

	/**
	 * Recursively obtain the reference to the item stored in this BST whose
	 * value is equal to the value of the parameter.
	 *   
	 * @param  item the value whose reference in the BST we are after
	 * @param  tree root of the current subtree in which we are looking for the item
	 * @return null, if the node with value equal to item was not found, or a reference
	 * 		   to that value if found
	 */
	protected T recGet(T item, Node<T> tree) {
		if (tree == null) 
			return null; // element not found
		else if (item.compareTo(tree.getData()) < 0)
			// look in the left subtree
			return recGet(item, tree.getLeft());
		else if (item.compareTo(tree.getData()) > 0)
			// look in the right subtree
			return recGet(item, tree.getRight());
		else
			return tree.getData(); // element is found
	}

	/*
	 * (non-Javadoc)
	 * @see BST#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(T item) {
		// only search if item isn't null
		if (item != null) {
			return recContains(item, this.root);
		}
		return false;
	}

	/**
	 * Recursively obtain the reference to the item stored in this BST whose
	 * value is equal to the value of the parameter.
	 *   
	 * @param  item the value whose reference in the BST we are after
	 * @param  tree root of the current subtree in which we are looking for the item
	 * @return true, if the node with value equal to item was not found, false
	 * 		   otherwise
	 */
	protected boolean recContains(T item, Node<T> tree) {

		if (tree == null) return false; // not found
		// search left subtree if item is less than current
		else if (item.compareTo(tree.getData()) < 0) {
			return recContains(item, tree.getLeft());
		}
		// search right if item is greater than current
		else if (item.compareTo(tree.getData()) > 0) {
			return recContains(item, tree.getRight());
		}
		else return true; // found it
	}

	/*
	 * (non-Javadoc)
	 * @see BST#size()
	 */
	@Override
	public int size() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * @see BST#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		postOrderPrint(this.root, 0, s);
		return s.toString();
	}

	/**
	 * Recursive Method to accumulate a string representation of this BST using the 
	 * parameter output.
	 * 
	 * @param tree   the root of the current subtree
	 * @param level  the depth of the current recursive call in the tree to determine
	 * 				 the indentation of each item
	 * @param output the string that accumulates the string representation of this BST
	 */
	protected void postOrderPrint(Node<T> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.getData());
			postOrderPrint(tree.getLeft(), level + 1, output);
			postOrderPrint(tree.getRight(), level + 1, output);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see BST#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new BSTIterator<T>(this);
	}
}
