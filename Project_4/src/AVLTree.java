/**
 * Generic AVL Tree Implementation
 * 
 * This class follows the API outlined in the BST interface. It contains
 * all of the methods necessary to instantiate and follow the preconditions
 * set forth by an AVL tree. Note, the remove method for the AVL tree is not
 * implemented, but it is implemented for the BST class (which it used when 
 * called).
 * 
 * @author    Peter Mountanos
 * @version   December 6th, 2014
 * @param <T> Any type that implements Comparable <T>
 * @see       BST
 */
public class AVLTree<T extends Comparable<T>> extends BSTRecursive<T>  {
	

	/*
	 * (non-Javadoc)
	 * @see BST#insert(java.lang.Comparable)
	 */
	@Override
	public void insert(T item) {
		if (item != null) 
			this.root = avlInsert(item, (AVLNode<T>) this.root);

		this.size++;
	}

	/**
	 * Method used to insert a node into this instance of 
	 * an AVL tree, which maintains the pre/post conditions
	 * of an AVL tree. Meaning, it handles balancing the
	 * tree after an insertion, if necessary.
	 * 
	 * @param  item item of type T to be inserted into the tree
	 * @param  tree the current subtree to be considered for insertion
	 * @return a reference to the subtree that contains the inserted item
	 */
	private AVLNode<T> avlInsert(T item, AVLNode<T> tree) {
		// if the subtree is empty, make it the root
		if (tree == null) 
			tree = new AVLNode<T>(item);
		// if the item should be in the left subtree (lte root)
		else if (item.compareTo(tree.getData()) <= 0) {
			// set left subtree to recursive avl insert value
			tree.setLeft(avlInsert(item, (AVLNode<T>) tree.getLeft()));
			
			// if the tree is off balance after insertion, balance it
			// (left subtree has two more levels than right subtree)
			if (tree.balanceFactor() == -2) {
				if (item.compareTo(tree.getLeft().getData()) < 0) {
					tree = balanceLL(tree);
				}
				else {
					tree = balanceLR(tree);
				}
			}
		}
		// if the item should be in the right subtree (gt root)
		else {
			// set right subtree to recursive avl insert value
			tree.setRight(avlInsert(item, (AVLNode<T>) tree.getRight()));
			
			// if the tree is off balance after insertion, balance it
			// (right subtree has two more levels than its left subtree) 
			if (tree.balanceFactor() == 2) { 
				if (item.compareTo(tree.getRight().getData()) > 0) {
					tree = balanceRR(tree);
				}
				else {
					tree = balanceRL(tree);
				}
			}
		}
		// update height of current subtree and then return it
		tree.updateHeight();
		return tree;
	}
	
	/**
	 * Method to perform an LL rotation of an AVL (subtree) A.
	 * 
	 * The imbalance occurs at a node A (note that node A is not the root of 
	 * the whole tree, it is just a node at which imbalance occurs, there might
	 * be a huge tree above it): its left subtree has two more levels than its 
	 * right tree. In the left subtree of A (i.e. subtree rooted at B) either 
	 * both subtrees of B have the same height, or the left subtree of B has 
	 * height one larger than the right subtree. Fix: rotate the subtree to the
	 * right. The right subtree of B becomes the left subtree of A.

	 * @param A AVL (sub)tree to be rotated according to an LL imbalance
	 * @return a balanced subtree based on A with an original LL imbalance
	 */
	private AVLNode<T> balanceLL(AVLNode<T> A) {
		// set references to subtrees needed to balance
		AVLNode<T> B = (AVLNode<T>) A.getLeft();

		A.setLeft(B.getRight());
		B.setRight(A);

		// update heights of rotated trees
		A.updateHeight();
		B.updateHeight();

		return B;
	}

	/**
	 * Method to perform an RR rotation of an AVL (subtree) A.
	 * 
	 * The imbalance occurs at a node A: its right subtree has two more levels
	 * than its right subtree. In the right subtree of A (i.e. subtree rooted
	 * at B) either both subtrees of B have the same height, or the right 
	 * subtree of B has height one larger than the left subtree. Fix: rotate the
	 * subtree to the left. The left subtree of B becomes the right subtree of A.

	 * @param A AVL (sub)tree to be rotated according to an RR imbalance
	 * @return a balanced subtree based on A with an original RR imbalance
	 */
	private AVLNode<T> balanceRR(AVLNode<T> A) {
		// set references to subtrees needed to balance
		AVLNode<T> B = (AVLNode<T>) A.getRight();

		A.setRight(B.getLeft());
		B.setLeft(A);

		// update heights of rotated trees
		A.updateHeight();
		B.updateHeight();

		return B;
	}

	/**
	 * Method to perform an LR rotation of an AVL (sub)tree A.
	 * 
	 * The imbalance occurs at a node A: its left subtree has two more levels 
	 * than its right subtree. In the left subtree of A (i.e. subtree rooted
	 * at B) the right subtrees of B (whose root is C) has height one larger 
	 * than the left subtree of B. Fix: rotate to the left at node B and then
	 * rotate to the right at node A.
	 * 
	 * @param A AVL (sub)tree to be rotated according to an LR imbalance
	 * @return a balanced subtree based on A with an original LR imbalance
	 */
	
	private AVLNode<T> balanceLR(AVLNode<T> A) { 
		// set references to subtrees needed to balance
		AVLNode<T> B = (AVLNode<T>) A.getLeft();
		AVLNode<T> C = (AVLNode<T>) B.getRight();

		A.setLeft(C.getRight());
		B.setRight(C.getLeft());
		C.setLeft(B);
		C.setRight(A);

		A.updateHeight();
		B.updateHeight();
		C.updateHeight();

		return C;
	}

	/**
	 * Method to perform an RL rotation of an AVL (sub)tree A.
	 * 
	 * The imbalance occurs at a node A: its right subtree has two more levels 
	 * than its left subtree. In the right subtree of A (i.e. subtree rooted 
	 * at B) the left subtrees of B (whose root is C) has height one larger 
	 * than the left subtree of B. Fix: rotate to the right at node B and then
	 * rotate to the left at node A.
	 * 
	 * @param A AVL (sub)tree to be rotated according to an RL imbalance
	 * @return a balanced subtree based on A with an original RL imbalance
	 */
	private AVLNode<T> balanceRL(AVLNode<T> A) {
		// set references to subtrees needed to balance
		AVLNode<T> B = (AVLNode<T>) A.getRight();
		AVLNode<T> C = (AVLNode<T>) B.getLeft();
		
		A.setRight(C.getLeft());
		B.setLeft(C.getRight());
		C.setLeft(A);
		C.setRight(B);
		
		A.updateHeight();
		B.updateHeight();
		C.updateHeight();
		
		return C;
	}
}
