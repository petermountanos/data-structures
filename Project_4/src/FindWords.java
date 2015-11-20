import java.io.*;
import java.util.*;
/**
 * Class which provides a user interface for the FindWords program.
 * The program needs to be called with a single command line argument
 * that contains the filename of a dictionary to be used for the program.
 * 
 * The user is then prompted for a set of letters, and the program produces
 * all of the possible words that can be created from those letters, which 
 * also are present in the dictionary.
 * 
 * Note: The AVL tree extra credit *has* been implemented
 * 
 * @author Peter Mountanos (pjm419)
 * @version  December 6th, 2014 
 */
public class FindWords {
	/**
	 * Main Method for FindWords. It is responsible for the
	 * execution of the entire program.
	 * 
	 * @param args an array containing command line arguments
	 * 		  the program expects one command line argument, 
	 *        which contains the name of the file of the dict-
	 *        ionary to be used.
	 */
	public static void main(String[] args) {
		
		// validate command-line argument
		if (args.length < 1){
			System.err.printf("Error:\t invalid number of  arguments\n"
					+ "Usage:\t FindWords <dictionaryFileName>\n\n");
			System.exit(1);
		}
		
		// instantiate file object
		File dictFile = new File(args[0]);;
		
		if (!dictFile.exists()){
			System.err.printf("Error:\t the filename given as command line input could not be found.\n\n");
			System.exit(1);
		}
		else if (!dictFile.canRead()) {
			System.err.printf("Error:\t the filename given as command line input cannot be read.\n\n");
			System.exit(1);
		}
			
		// instantiate DictionaryList object to store vocabulary based on dictFile input
		Dictionary wordDict = new DictionaryAVL(dictFile);
		
		// only run program if there's a dictionary with stuff in it
		if (wordDict.size() > 0){
			// instantiate SetOfLetters object to store user input string
			SetOfLetters letterSet = new SetOfLetters(getUserInput(), wordDict);
			// print sorted list of permutations to console
			letterSet.printPermutations();
		}
		else {
			System.err.println("<"+dictFile+"> is an empty dictionary. Try again with a useful dictionary.");
			System.exit(1);
		}
		
	}
	
	/**
	 * Function that obtains string of letters from user input.
	 * 
	 * This function has certain preconditions. The string given
	 * by the user must contain only letters, and it must be in the
	 * range [2,10]. If these preconditions don't hold true, it will
	 * print a message to the user and exit.
	 * 
	 * @return string representing lower case letters to be permuted
	 */
	public static String getUserInput(){
		// get user input
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a string of 2-10 characters  (letters only, no spaces, commas, or any other characters).");
		String letters = scan.nextLine();
		scan.close();
		
		// validate user input
		if (letters.length() < 2 || letters.length() > 10){
			System.err.printf("Error: \"%s\" has incorrect number of letters\n\n", letters);
			System.exit(1);
		}
		if (!isLetters(letters)){
			System.err.printf("Error: \"%s\" is not filled with only letters\n\n", letters);
			System.exit(1);
		}	
		
		// return String meeting postcondition
		return letters.toLowerCase();
	}
	
	/**
	 * Helper method to determine if a string contains only
	 * letters. 
	 * @param s string to be analyzed
	 * @return true if string contains only letters, false otherwise
	 */
	public static boolean isLetters(String s){		
		char[] charLetters = s.toCharArray();

		// validate each char is a letter
		for (char ch: charLetters){
			if (!Character.isLetter(ch)){
				return false;
			}
		}
		return true;
	}
}
