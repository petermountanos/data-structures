import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class which provides an ADT for a Dictionary Representation.
 * 
 * The Dictionary is implemented used an AVLTree which stores all of the words
 * in the dictionary. The Dictionary class needs to be initialized with a 
 * single argument, which represents the file name/path of the dictionary file.
 * 
 * The class implements/inherits several instance methods to facilitate
 * searching the dictionary. For example, a user can search to see if a 
 * word is in the dictionary. However, a user can also search to see if a
 * certain prefix exists in words in the dictionary. (i.e., if the dictionary
 * contains the word 'alphabet', the prefix 'alpha' exists in that dictionary).
 * 
 * @author Peter Mountanos
 * @version December 6th, 2014
 * @see AVLTree<T> for generic AVL tree implementation
 */
public class DictionaryAVL extends AVLTree<String> implements Dictionary {

	/**
	 * Reference to File object which represents the dictionary text file
	 */
	private File src;

	/**
	 * Constructor method for DictionaryAVL class
	 * 
	 * Instantiates a <DictionaryAVL> object, with a single
	 * parameter of file object. It then calls a helper method
	 * to populate the Dictionary with the words from the given
	 * text file. It stores these words in an AVL tree.
	 * 
	 * @param input File object pointing to dictionary text file
	 */
	public DictionaryAVL(File input) {
		super(); // call AVLTree constructor to initialize tree
		this.src = input;
		this.populate(); // populate the tree
	}

	/**
	 * Method which populates the Dictionary AVL Tree using a text file
	 * path given by the src field.
	 * 
	 * This method scans the text file line by line, and inserts words into
	 * the AVLTree. This method assumes that the text file contains a
	 * sorted list of words, one per line.
	 */
	private void populate() {
		// initialize scanner object to null
		Scanner scan = null; 

		// need try-catch by constructs of java, but error-handling
		// was already taken care of in the FindWords class when filename
		// was accepted as command line input
		try { 
			scan = new Scanner(this.src);
			while (scan.hasNext()){
				// add words line-by-line, as lower-case (assumes string input
				// is lower-case => facilitates search process)
				this.insert(scan.next().toLowerCase());
			}
		} catch (FileNotFoundException e) {
		}// error already handled in FindWords;
		finally {
			scan.close();
		}
		//System.out.println(this);
	}

	/*
	 * (non-Javadoc)
	 * @see Dictionary#containsPrefix(java.lang.String)
	 */
	public boolean containsPrefix(String item) {
		// only search if item isn't null
		if (item != null) {
			return recContainsPrefix(item, (AVLNode<String>) this.root);
		}
		return false;
	}

	/**
	 * recContainsPrefix Method for DictionaryAVL 
	 * 
	 * This method determines if a certain prefix exists in the tree. A prefix
	 * is defined by any set of letters that make up the first part of a word.
	 * The first part is defined as at least the first letter, to the length 
	 * of the word. 
	 * 
	 * @param item prefix to be searched for in the tree
	 * @return true if prefix exists as a prefix in the tree, false otherwise
	 */
	private boolean recContainsPrefix(String item, AVLNode<String> current) {
		
		// if reached a null node, prefix wasn't found
		if (current == null) return false;

		// get "prefix" of current word in AVLNode 
		String currentPrefix = current.getData().substring(0, item.length());

		// if less than current word, search left subtree
		if (item.compareTo(currentPrefix) < 0) {
			return this.recContainsPrefix(item, (AVLNode<String>) current.getLeft());
		}
		// if greater than current word, search right subtree
		else if (item.compareTo(currentPrefix) > 0) {
			return this.recContainsPrefix(item, (AVLNode<String>) current.getRight());
		}
		else return true; // found it
	}
}
