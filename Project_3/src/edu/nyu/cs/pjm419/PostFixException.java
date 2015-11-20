package edu.nyu.cs.pjm419;
/**
 * PostFixException class which is used to represent
 * a postfix exception for the RPN calculator application.
 * 
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PostFixException extends Exception {
	
	/**
	 * Default constructor that does nothing fancy, it
	 * just calls its parent's constructor (Exception).
	 */
	public PostFixException() {
		super();
	}
	
	/**
	 * Constructor with message, that does nothing fancy,
	 * but calls its parent's constructor (Exception) and
	 * passed the message to it.
	 * 
	 * @param message string to represent the error message
	 * 		  to display when the exception is thrown
	 */
	public PostFixException(String message){
		super(message);
	}
}
