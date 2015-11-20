/**
 * Dictionary Interface for Find Words Game
 * 
 * This interface delineates the necessary methods a dictionary
 * needs in order to search for permutations of a user inputed
 * word in the dictionary.
 * 
 * @author     Peter Mountanos
 * @version    December 6th, 2014
 */
public interface Dictionary {
	
	/**
	 * Returns a truth value regarding whether there is a word stored in the
	 * dictionary whose value is equal to the value of the parameter.
	 * 
	 * @param  item word to be searching for in the dictionary
	 * @return true if the word is in the dictionary, else false
	 */
	public boolean contains(String item);
	
	/**
	 * This method determines if a certain prefix exists in the dictionary. A 
	 * prefix is defined by any set of letters that make up the first part of a
	 * word. The first part is defined as at least the first letter, to the 
	 * length of the word. 
	 * 
	 * @param  item prefix to be searched for in the dictionary
	 * @return true if prefix exists as a prefix in the dictionary, false 
	 * 		   otherwise
	 */
	public boolean containsPrefix(String item);
	
	/**
	 * Method to determine the number of words stored in the dictionary.
	 * 
	 * @return an integer representing the number of words in the dictionary
	 */
	public int size();
}
