package testing;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import edu.nyu.cs.pjm419.DictionaryList;

/**
 * This program compares running time of Dictionary contains
 * methods. One implementation of the dictionary is using an
 * ArrayList, with a binary search (O(log(n)) time complexity) for contains method. 
 * The other implementation is using a Trie tree, which has a O(m) time complexity,
 * where m is the length of the search string.
 * 
 * Previous Output: 
 * 
 * Time to create DictionaryList: 444 milliseconds.
 * Time to create DictionaryTrie: 269 milliseconds.
 * Before many calls to DictionaryList.contains(zebra).
 * DictionaryList.contains(zebra) = true.
 * DictionaryList took 27 milliseconds.
 * Before many calls to DictionaryTrie.contains(zebra).
 * DictionaryTrie.contains(zebra) = true.
 * DictionaryTrie took 12 milliseconds.
 * 
 * @author Peter Mountanos
 * @version Sept 14, 2014 
 *
 */
public class DictionaryCompetition {

	public static void main(String[] args) {

		final int REPEAT = 100000;

		long start, end;
		boolean result = false;
		String searchTerm = "alpha";
		
		start = (new Date()).getTime();
		DictionaryList dl = new DictionaryList(new File("TWL06.txt"));
		end = (new Date()).getTime();
		System.out.printf("Time to create DictionaryList: %d milliseconds.\n", end - start);
		System.out.println("DictionaryList Size: "+dl.size());
		
		start = (new Date()).getTime();
		DictionaryTrie dt = new DictionaryTrie("TWL06.txt");
		end = (new Date()).getTime();
		System.out.printf("Time to create DictionaryTrie: %d milliseconds.\n", end - start);
		System.out.println("DictionaryTrie Size: "+dt.size);
		
		System.out.printf("Before many calls to DictionaryList.contains(%s).\n", searchTerm);
		start = (new Date()).getTime();
		for (int i = 0; i < REPEAT; i++)
			result = dl.contains(searchTerm);
		end = (new Date()).getTime();
		System.out.printf("DictionaryList.contains(%s) = %s.\n", searchTerm, result);
		System.out.printf("DictionaryList took %d milliseconds.\n", end - start);
		
		System.out.printf("Before many calls to DictionaryTrie.contains(%s).\n", searchTerm);
		start = (new Date()).getTime();
		for (int i = 0; i < REPEAT; i++)
			result = dt.contains(searchTerm);
		end = (new Date()).getTime();
		System.out.printf("DictionaryTrie.contains(%s) = %s.\n", searchTerm, result);
		System.out.printf("DictionaryTrie took %d milliseconds.", end - start);
		
	}
}
