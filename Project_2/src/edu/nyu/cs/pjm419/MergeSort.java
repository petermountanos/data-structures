package edu.nyu.cs.pjm419;

import java.lang.reflect.Array;
/**
 * Merge Sort Class
 * 
 * Contains implementation of a generic 
 * merge sort algorithm
 *  
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
@SuppressWarnings("unchecked")
public class MergeSort {
	
	/**
	 * Wrapper for generic implementation of the merge sort algorithm. 
	 * 
	 * @param list array to be sorted
	 */
	public static <E extends Comparable<E>> void sort(E[] list){
		// start initially with whole array (first index to last index)
		sort(list,0,list.length-1);
	}

	/**
	 * Generic implementation of the merge sort algorithm.
	 * 
	 * @param list array to be sorted
	 * @param start start index of the array
	 * @param end end index of the array  
	 */
	private static <E extends Comparable<E>> void sort(E[] list, int start, int end){
		// base case (if len(sub-array == 1))
		if (start == end) return;

		int mid = (start + end) / 2;		  // find break point for sub-arrays
		sort(list, start, mid); 			  // sort left sub-array
		sort(list, mid+1, end);				  // sort right sub-array
		merge(list, start, mid, mid+1, end);  // merge two sorted sub-arrays
	}

	/**
	 * Merging algorithm to merge two sorted sub-arrays.
	 * 
	 * @param list array containing both sub-arrays to be merged
	 * @param leftStart  start index of the left sorted sub-array
	 * @param leftEnd    end index of the left sorted sub-array
	 * @param rightStart start index of the right sorted sub-array
	 * @param rightEnd   end index of the right sorted sub-array
	 */
	private static <E extends Comparable<E>> void merge(E[] list, int leftStart,
			int leftEnd, int rightStart, int rightEnd){
		
		// len(left sub-array) + len(right-subarray)
		int tempLength = rightEnd - leftStart + 1;
		int i = 0, indexLeft = leftStart, indexRight = rightStart;
		
		// create temporary array to stored merged values
		E[] temp = (E[]) Array.newInstance(list.getClass().getComponentType(), tempLength);

		// while not done merging left and right sub-arrays
		while (indexLeft <= leftEnd && indexRight <= rightEnd){
			
			if (list[indexLeft].compareTo(list[indexRight]) < 0){
				temp[i++] = list[indexLeft++];
			}
			else {
				temp[i++] = list[indexRight++];
			}
		}

		// if right sub-array finished merging, but not left, copy rest
		// of left sub-array to end of temp array
		while (indexLeft <= leftEnd) temp[i++] = list[indexLeft++];

		// if left sub-array finished merging, but not right, copy rest
		// of right sub-array to end of temp array
		while (indexRight <= rightEnd) temp[i++] = list[indexRight++];

		// copy temp to list starting at leftStart
		int j = leftStart, k = 0;
		while (k < temp.length) list[j++] = temp[k++];
	}
}
