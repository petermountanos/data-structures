package edu.nyu.cs.pjm419;

import java.util.Random;

/**
 * Quick Sort Class
 * 
 * Contains implementation of a generic 
 * quick sort algorithm
 *  
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
public class QuickSort {

	private static Random rand = new Random(); // random number generator used for
											  // generating random pivot for quicksort

	/**
	 * Wrapper for generic implementation of the quick sort algorithm. 
	 * 
	 * @param list array to be sorted
	 */
	public static <E extends Comparable<E>> void sort(E[] list){
		sort(list,0,list.length-1);		// start with whole array
	}

	/**
	 * Generic implementation of the quick sort algorithm.
	 * 
	 * @param list array to be sorted
	 * @param start start index of the array
	 * @param end end index of the array  
	 */
	private static <E extends Comparable<E>> void sort(E[] list, int left, int right){
		
		// if size of sub-array is 1, no more sorting needed
		if (left >= right) return;

		// if size of sub-array is less than threshold
		// use insertion sort (optimization technique)
		if ((right - left) + 1 <= 30) {			
			InsertionSort.sort(list, left, right);
			return;
		}

		int pivot = rand.nextInt(right - left + 1) + left;	    // random pivot
		//int pivot = median(list,left,right);					// median of three pivot
		//int pivot = (left + right) / 2;						// middle pivot
		
			
		swap(list, pivot,right);								// put pivot at end
		// partition sub-array so left sub-array contains values less than (or equal
		// to) the pivot and the right-subarray contains values greater than the pivot
		int split = partition(list, left, right - 1, list[right]);
		swap(list, split, right);								// swap pivot back to right place
		sort(list, left, split - 1);							// sort left sub-array
		sort(list, split + 1, right);							// sort right sub-array
	}

	/**
	 * 
	 * @param list the main array to be partitioned
	 * @param left the start index of the current sub-array being partitioned
	 * @param right the end index of the current sub-array being partitioned
	 * 		  (the pivot value is being stored to the right of this value)
	 * @param pivot the value of the current pivot being sorted on 
	 * @return an integer representing the first index position in the right
	 * 		   sub-array formed by the partition
	 */
	private static <E extends Comparable<E>> int partition(E[] list, int left, int right, E pivot){

		while (left <= right){
			while (list[left].compareTo(pivot) < 0) left++;
			while (right >= left && list[right].compareTo(pivot) >= 0) right--;
			if (right > left) swap(list, left, right);
		}
		return left; // return first position in right partition
	}

	/**
	 * Generic implementation of swap function. This function, given an
	 * array and two indices of the array, swaps the values at those
	 * two indices.
	 * 
	 * @param list array where swapping should occur
	 * @param x index 1 to swap
	 * @param y index 2 to swap
	 */
	private static <E extends Comparable<E>> void swap(E[] list, int x, int y){
		E tmp = list[x];
		list[x] = list[y];
		list[y] = tmp;
	}

	/**
	 * Generic implementation of median of three algorithm. This method
	 * finds the median of the first index, middle index, and end index
	 * of a sub-array of the parameter list. It is used as a possible technique
	 * for picking the pivot in the quick sort algorithm. This method swaps
	 * indices so that the median also always becomes the middle value.
	 * @param list array to be used for median of three
	 * @param left beginning index of sub-array to be used
	 * @param right end index of sub-array to be used
	 * @return the middle index of the sub-array, which now contains the median
	 * 		   of three
	 */
	public static <E extends Comparable<E>> int median(E[] list, int left, int right) {
		int mid = (left + right) / 2;

		if (list[right].compareTo(list[left]) < 0)
			swap(list, left, right);

		if( list[mid].compareTo(list[left]) < 0)
			swap(list, left, mid);

		if( list[right].compareTo(list[mid]) < 0)
			swap(list, right, mid);

		return mid; 
	} 
}