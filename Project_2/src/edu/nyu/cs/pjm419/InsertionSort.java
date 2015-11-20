package edu.nyu.cs.pjm419;
/**
 * Insertion Sort Class
 * 
 * Contains implementation of a generic 
 * insertion sort algorithm
 *  
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
public class InsertionSort {
	/**
	 * Generic implementation of the insertion sort algorithm.
	 * 
	 * @param list array to be sorted
	 */
	public static <E extends Comparable<E>> void sort(E[] list, int start, int end){
		for (int i = start + 1; i <= end; i++) {
			/*
			 * insert list[i] into a sorted sublist list[0..i-1] so that
			 * list[0..i] is sorted.
			 */
			E currentElement = list[i];
			int k;
			for (k = i - 1; k >= 0 && list[k].compareTo(currentElement) > 0; k--) {
				list[k + 1] = list[k];
			}

			// Insert the current element into list[k+1]
			list[k + 1] = currentElement;
		}
	}
}
