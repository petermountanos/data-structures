package edu.nyu.cs.pjm419;

import java.util.Arrays;
import java.util.Random;

/**
 * Main class to test the three sorting algorithms that were
 * implemented for this assignment (selection, merge, quick).
 *
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
public class TestSorting {
	
	// class variables
	public static TimeIt t = new TimeIt(); 				// method timing object 
	public static final int TEST_RUNS = 1; 				// number of times to repeat sort
	
	// arrays storing array sizes to sort
	public static int[] sizes = {10000,20000,30000,40000,50000,60000,70000,80000,90000,100000};
	public static int[] bigger = {50000,100000,150000,200000,250000,300000,350000,400000,450000,500000,
								  550000,600000,650000,700000,750000,800000,850000,900000,950000,1000000};

	/**
	 * Main method for Sorting Benchmark Analysis
	 */
	public static void main(String[]  args){
		
		double selectionTime, mergeTime, quickTime; 
		
		System.out.println("Running All Sorting Algorithms on smaller arrays...\n");
		System.out.printf("Size\tSelection\t Merge\t Quick\n");
		
		// sort arrays with lengths from *sizes* array (selection, merge & quick)
		for (int num = 0; num < sizes.length; num++){
			// make random array
			Integer[] list     =  makeRandomArray(sizes[num],0,Integer.MAX_VALUE-1);
			
			// sort array copies with each algo TEST_RUN times
			selectionTime 	   =  testSelectionSort(list);
			mergeTime     	   =  testMergeSort(list);
	        quickTime     	   =  testQuickSort(list);
	        
	        // generate output (average time for each algo)
	        System.out.printf("%s\t%.2f\t\t %.2f\t %.2f\n",sizes[num],selectionTime,mergeTime,quickTime);
		}
		
		
		System.out.println("\nRunning Merge & Quick Sort Algorithms on big arrays...\n");
		System.out.printf("Size\tMergeSort\tQuickSort\n");
		
		// sort arrays with lengths from *bigger* array (merge & quick)
		for (int num = 0; num < bigger.length; num++){
			// make random array
			Integer[] list   =  makeRandomArray(bigger[num],0, Integer.MAX_VALUE - 1);
			
			// sort array copies with each algo TEST_RUN times
			mergeTime 		 =  testMergeSort(list);
			quickTime  		 =  testQuickSort(list);
			
			// generate output (average time for each algo)
			System.out.printf("%s\t%.2f\t\t%.2f\n",bigger[num],mergeTime,quickTime);
		}
	}
	
	/**
	 * This method generates a random array of numbers of type Integer.
	 * The range of the random numbers is (min, max). 
	 * 
	 * @param size size of array to be generated
	 * @param min  minimum value of a random number in the array
	 * @param max  maximum value of a random number in the array
	 * @return a random array of length size, with numbers in the range (min, max)
	 */
	public static Integer[] makeRandomArray(int size, int min, int max){
		Random rand = new Random();
		Integer[] list = new Integer[size];

		for (int i = 0; i < list.length; i++){
			list[i] = rand.nextInt((max - min) + 1) + min;
		}	
		
		return list;
	}

	/**
	 * This method tests the selection sort algorithm that has been
	 * implemented. It runs the algorithm TEST_RUNS times on copies
	 * of list, and then returns the average time taken to sort 
	 * the copies.
	 * 
	 * @param list array to be copied and then sort the copy
	 * @return average time taken to sort the array copies
	 */
	public static double testSelectionSort(Integer[] list){
		double totalTime = 0;
		for (int i = 0; i < TEST_RUNS; i++){
			Integer[] tmp = Arrays.copyOf(list, list.length);	// make copy of list
			t.start();											// start clock	
			SelectionSort.sort(tmp);							// sort copy
			totalTime += t.getTime();							// end clock and increment total
			System.gc();
		}
		return totalTime / TEST_RUNS;
	}

	/**
	 * This method tests the merge sort algorithm that has been
	 * implemented. It runs the algorithm TEST_RUNS times on copies
	 * of list, and then returns the average time taken to sort 
	 * the copies.
	 * 
	 * @param list array to be copied and then sort the copy
	 * @return average time taken to sort the array copies
	 */
	public static double testMergeSort(Integer[] list){
		double totalTime = 0;
		for (int i = 0; i < TEST_RUNS; i++){
			Integer[] tmp = Arrays.copyOf(list, list.length);	// make copy of list
			t.start();											// start clock
			MergeSort.sort(tmp);								// sort copy
			totalTime += t.getTime();							// end clock and increment total
		}
		return totalTime / TEST_RUNS;
	}

	/**
	 * This method tests the quick sort algorithm that has been
	 * implemented. It runs the algorithm TEST_RUNS times on copies
	 * of list, and then returns the average time taken to sort 
	 * the copies.
	 * 
	 * @param list array to be copied and then sort the copy
	 * @return average time taken to sort the array copies
	 */
	public static double testQuickSort(Integer[] list){
		double totalTime = 0;
		for (int i = 0; i < TEST_RUNS; i++){
			Integer[] tmp = Arrays.copyOf(list, list.length);	// make copy of list
			t.start();											// start clock
			QuickSort.sort(tmp);								// sort copy
			totalTime += t.getTime();							// end clock and increment total
		}
		return totalTime / TEST_RUNS; 
	}
	
	/**
	 * This method checks to see if a given Integer array is
	 * sorted. 
	 * 
	 * @param list array to be checked for sorted validity
	 * @return true if list is sorted, false otherwise 
	 */
	public static boolean isSorted(Integer[] list){
		for (int i = 0; i < list.length - 1; i++)
			if (list[i] > list[i+1]) return false;
		return true;
	}
}
