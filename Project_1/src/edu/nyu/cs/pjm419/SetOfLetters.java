package edu.nyu.cs.pjm419;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class which represents a set of n letters, and all of the
 * permutations that can be formed from those letters, and
 * all subsets of those letters (where the subset contains
 * at least 2 letters).
 * 
 * An object of type SetOfLetters must be instantiated with a 
 * String representing the n letters, and a vocabulary of type
 * DictionaryList. The vocabulary is used to validate the permu-
 * tations, as the only permutations that are acceptable are those
 * that are also exit in the vocabulary.
 *  
 * @author Peter Mountanos
 * @version September 29, 2014
 */

public class SetOfLetters {

	/*FIELDS*/

	/**A string representing n letters range 2-10 inclusive*/
	private String letters;
	/**An ArrayList storing all of the permutations of <letters>, 
	 * including the letter subsets formed from <letters>*/
	private ArrayList<String> permutations = new ArrayList<String>();
	/**A DictionaryList object that represents the allowable vocab 
	 * of permutations.*/
	private DictionaryList vocab;

	/*GETTERS*/

	public ArrayList<String> getPermutations(){
		return this.permutations;
	}

	public String getLetters(){
		return this.letters;
	}

	public DictionaryList getVocab(){
		return this.vocab;
	}

	/**
	 * SetOfLetters constructor, which instantiates a SetOfLetters
	 * object, consisting on a string of letters (<letters>) and a
	 * vocabulary <vocab> of type DictionaryList. This constructor 
	 * also calls a helper method, which creates all of the possible
	 * permutations of <letters>, that are also contained in <vocab>.
	 * 
	 * @param letters a string of n letters, where n is 2-10 inclusive
	 * @param vocab a DictionaryList that contains a sorted ArrayList of words
	 */
	public SetOfLetters(String letters, DictionaryList vocab){
		this.letters = letters;
		this.vocab = vocab;
		this.makePermutations();
	}

	/*PRIVATE ACCESS METHODS*/

	/**
	 * Access Method to add a permutation to the 
	 * permutations ArrayList
	 * 
	 * @param word String to be added to permutations
	 * 			   ArrayList
	 */
	private void addPerm(String word){
		this.permutations.add(word);
	}


	/*INSTANCE METHODS*/

	/**
	 * Wrapper Method responsible for making all of the permutations
	 * of a given set of n letters, and its subsets [2,10]. This method
	 * then sorts the permutations ArrayList, and removes any duplicates
	 * that might've occurred during the backtracking algorithm (likely).
	 */
	public void makePermutations(){
		// permute set of letters
		char[] brokenUp = this.getLetters().toCharArray();

		// start permutation with no prefix, and the entire char array
		this.permute("",brokenUp, 0, brokenUp.length-1);

		// sort arraylists for final output
		Collections.sort(this.getPermutations());

		// remove duplicates from a sorted list
		// only if there's possible elements to remove
		if (this.getPermutations().size() > 0){
			this.removePermDuplicates();
		}
	}

	/**
	 * Permutation Backtracking Algorithm
	 * 
	 * This is the *main algorithm* required for this
	 * assignment. Given an array of letters, it creates
	 * all of the possible permutations from those letters
	 * (including subsets of the letters).
	 * 
	 * @param prefix the letters that have already been "fixed"
	 * 		  to the beginning of the permutation
	 * @param letters array of letters to permute
	 * @param start starting index in the array
	 * @param end ending index in the array 
	 */
	private void permute(String prefix, char[] letters, int start, int end){
		// base case (basically if remaining string is empty)
		if (start == end){
			String word = prefix + letters[start]; // add remaining letter to prefix
			if (this.getVocab().contains(word)) this.addPerm(word);
			return;
		}

		// for each char in letters ("fix" char and permute remaining string)
		for (int i = start; i < end+1; i++){
			swap(letters,start,i);

			// extra credit (get subset permutations as well)
			String pre = prefix + letters[start];
			if (pre.length() > 1 && this.getVocab().contains(pre)) this.addPerm(pre);

			permute(pre,letters,start+1,letters.length-1); // recursive call
			swap(letters,start,i); // swap back (backtracking...)
		}
	}

	/**
	 * Helper Method to remove duplicates from the
	 * permutations ArrayList after the backtracking
	 * algorithm has completed, and the permutations 
	 * have been sorted.
	 */
	private void removePermDuplicates(){
		String prevElement = this.getPermutations().get(0);
		for (int i = 1; i < this.getPermutations().size(); i++ ){
			if (prevElement.equals(this.getPermutations().get(i))){
				this.getPermutations().remove(i);
				i--;
			}
			else {
				prevElement = this.getPermutations().get(i);
			}
		}
	}

	/**
	 * Helper method to print out the items in
	 * the sorted, duplicate free, permutations
	 * ArrayList
	 */
	public void printPermutations(){
		for (String perm : this.getPermutations()){
			System.out.println(perm);
		}
	}

	/*CLASS METHODS*/
	/**
	 * Swap method for char array
	 *
	 * @param letters char array where the indices to be sorted are stored
	 * @param a one index to be swapped
	 * @param b the other index to be swapped
	 */
	private static void swap(char[] letters, int a, int b){
		char tmp = letters[a];
		letters[a] = letters[b];
		letters[b] = tmp;
	}
}
