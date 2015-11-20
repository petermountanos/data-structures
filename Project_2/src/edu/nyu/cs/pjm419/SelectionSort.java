package edu.nyu.cs.pjm419;
/**
 * Selection Sort Class
 * 
 * Contains implementation of a generic 
 * selection sort algorithm
 *  
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
public class SelectionSort {
	/**
	 * Generic implementation of the selection sort algorithm.
	 * 
	 * @param list array to be sorted
	 */
	public static <E extends Comparable<E>> void sort(E[] list){
		for (int i = 0; i < list.length - 1; i++){
			// find the minimum in the list[i...list.length-1]
			E minValue = list[i];
			int min = i;
			for (int j = i + 1; j < list.length; j++){
				if (minValue.compareTo(list[j]) > 0){
					min = j;
					minValue = list[j];
				}
			}
			// swap list[i] with list[min] if necessary
			if (min != i) {
				list[min] = list[i];
				list[i] = minValue;
			}
		}
	}
}
