package edu.nyu.cs.pjm419;
/**
 * TimeIt Class used for timing algorithm speeds
 * 
 * An object of this class should be instantiated
 * to facilitate testing for algorithm timing. It 
 * returns total running time in milliseconds.
 *  
 * @author Peter Mountanos (pjm419)
 * @version October 4, 2014
 */
public class TimeIt {
	// instance variables
	private long start;
	private long end;
	private double total;
	
	/**
	 * Method to start the timer object
	 */
	public void start(){
		this.start = System.nanoTime();
	}
	
	/**
	 * Method to end the timer object, and returns
	 * the total elapsed time in milliseconds.
	 * 
	 * @return the total elapsed time in milliseconds
	 */
	public double getTime(){
		this.end = System.nanoTime();
		this.total = (this.end - this.start) / 1000000.0;
		return this.total;
	}
}
