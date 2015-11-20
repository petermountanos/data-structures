package edu.nyu.cs.pjm419;
import java.io.*;
import java.util.*;
/**
 * Class which provides an ADT for a Dictionary Representation.
 * The Dictionary is implemented used an ArrayList<String>,
 * which stores all of the words in the dictionary. The Dictionary
 * class needs to be initialized with a single argument, which
 * represents the file name/path of the dictionary file.
 * 
 * The class comes with several instance methods to facilitate
 * searching the dictionary. All of the searching algorithms 
 * are implementing via binary search. For example, a user can
 * search to see if a word is in the dictionary. However, a 
 * user can also search to see if a certain prefix exists in words
 * in the dictionary. (i.e., if the dictionary contains the word
 * 'alphabet', the prefix 'alpha' exists in that dictionary).
 * 
 * @author Peter Mountanos
 * @version September 29, 2014
 */
public class DictionaryList {
	
	/*FIELDS*/

	/**String representing path to text file of dictionary*/
	private File dictFile;
	/**ArrayList which stores the words in the dictionary*/
	private ArrayList<String> words = new ArrayList<String>();
	
	/**
	 * Constructor method for DictionaryList class
	 * 
	 * Instantiates a <DictionaryList> object, with a single
	 * parameter of file object. It then calls a helper method
	 * to populate the Dictionary with the words from the given
	 * text file.
	 * 
	 * @param dictFile File object pointing to dictionary text file
	 */
	public DictionaryList(File dictFile) {
		this.dictFile = dictFile;
		this.populateDictionary();
	}
	
	/*WRAPPER METHODS*/

	/**
	 * Contains Wrapper Method 
	 * 
	 * @param searchTerm, string to search for in the dictionary
	 * @return true if dictionary contains searchTerm, false otherwise
	 */
	public boolean contains(String searchTerm){
		return contains(searchTerm, 0, this.size()-1);
	}

	/**
	 * ContainsPrefix Wrapper Method
	 * 
	 * @param searchTerm, prefix to search for in the dictionary
	 * @return true if the dictionary contains a word with prefix
	 * 		   <searchTerm>, false otherwise
	 */
	public boolean containsPrefix(String searchTerm){
		return containsPrefix(searchTerm,0,this.size()-1);
	}
	
	/**
	 * Size Wrapper Method for Dictionary ArrayList
	 * 
	 * @return the size of the field, words, of type ArrayList
	 */
	public int size(){
		return this.words.size();
	}

	/**
	 * Get Wrapper Method for Dictionary ArrayList
	 * 
	 * @param i index of <this.words> to be obtained
	 * @return the value stored at index <i> of <this.words>
	 */
	public String get(int i){
		return this.words.get(i);
	}

	/*INSTANCE METHODS*/
	/**
	 * Contains Method for Dictionary ArrayList
	 * 
	 * Implemented via Binary Search. This method determines
	 * if searchTerm exists as a word in the ArrayList <words>.
	 * This method assumes the list <words> is sorted.
	 * 
	 * @param searchTerm word to be searched for in <words>
	 * @param min, minimum index at current slice of <words>
	 * @param max, maximum index at current slice of <words>
	 * @return true if searchTerm is in <words>, false otherwise
	 */
	public boolean contains(String searchTerm, int min, int max ){
		
		// base case: if the end index is greater than 
		// the beginning index, prefix was not found
		if (max < min) return false;

		// get midpoint of current slice of array
		int mid = (min + max) / 2;
		
		// get value at midpoint
		String midWord = this.get(mid);

		// if prefix would be located before midWord, check from [min,mid)
		if (midWord.compareTo(searchTerm) > 0){
			return this.contains(searchTerm, min, mid-1);
		}
		// if prefix would be located after midWord, check from (mid,max]
		else if (midWord.compareTo(searchTerm) < 0){
			return this.contains(searchTerm, mid+1, max);
		}
		// else (if prefix is midWord), prefix is found
		return true;
	}
	/**
	 * ContainsPrefix Method for Dictionary ArrayList 
	 * 
	 * This method determines if a certain prefix exists
	 * in ArrayList <words>. A prefix is defined by any set
	 * of letters that make up the first part of a word. The
	 * first part is defined as at least the first letter, to
	 * the length of the word. This method assumes the list
	 * <words> is sorted.
	 * 
	 * @param prefix, prefix to be search for in <words>
	 * @param min, minimum index at current slice of <words>
	 * @param max, maximum index at current slice of <words>
	 * @return true if prefix exists as a prefix in <words>,
	 * 		   false otherwise
	 */
	public boolean containsPrefix(String prefix, int min, int max){
		
		// base case: if the end index is greater than 
		// the beginning index, prefix was not found
		if (max < min) return false;

		// get midpoint of current slice of array
		int mid = (min + max) / 2;
		
		// get value at midpoint
		String midWord = this.get(mid);
		
		// slice value at midpoint to size of prefix input (may have
		// duplicate words now, but still sorted, so binary search 
		// still gives accurate results)
		String prefixMidWord = midWord.substring(0,prefix.length());

		// if prefix would be located before midWord, check from [min,mid)
		if (prefixMidWord.compareTo(prefix) > 0){
			return this.containsPrefix(prefix, min, mid-1);
		}
		// if prefix would be located after midWord, check from (mid,max]
		else if (prefixMidWord.compareTo(prefix) < 0){
			return this.containsPrefix(prefix, mid+1, max);
		}
		// else (if prefix is midWord), prefix is found
		return true;
	}
	
	/**
	 * Method which populates the Dictionary ArrayList, <words>,
	 * using a text file path given by the dictFile field.
	 * 
	 * This method scans the text file line by line, and adds words into
	 * the ArrayList. This method assumes that the text file contains a
	 * sorted list of words, one per line.
	 * 
	 * @return void
	 */
	public void populateDictionary(){
		
		Scanner scan = null; // initialize scanner object to null
		
		// need try-catch by constructs of java, but error-handling
		// was already taken care of in the FindWords class when filename
		// was accepted as command line input
		try { 
			scan = new Scanner(this.dictFile);
			while (scan.hasNext()){
				// add words line-by-line, as lower-case (assumes string input
				// is lower-case => facilitates search process)
				this.words.add(scan.next().toLowerCase());
			}
		} catch (FileNotFoundException e) {
		}// error already handled in FindWords;
		finally {
			scan.close();
		}
	}
}

