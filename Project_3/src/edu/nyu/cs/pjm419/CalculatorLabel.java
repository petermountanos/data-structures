package edu.nyu.cs.pjm419;

import javax.swing.JLabel;

/**
 * CalculatorLabel Class which extends Java Swing's
 * JLabel class to add enhanced functionality's pertaining
 * to the JCalculator GUI. 
 * 
 * This class is meant to represent a calculator label object,
 * which displays the user inputed expressions and results of
 * these expressions. It has several built-in methods to facilitate
 * updating the expressions and values based on user clicked input, as
 * well as keystroke input.  
 *  
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CalculatorLabel extends JLabel {
	// class constants
	/**
	 * String constant used to store the symbols in the calculator buttons. This
	 * is used to facilitate string parsing when appending text to <this.infix>
	 */
	private static final String SYMBOLS = "()*/+-"; 
	
	// instance variables
	/** 
	 * String used to store current expression in the calculator label; must
	 * either be an infix expression entered by the user, or the result of the
	 * infix expression, after the = button has been called 
	 * */
	private String infix;					
	
	/**
	 * CalculatorLabel Constructor used to instantiate a default
	 * CalculatorLabel object. Note, that only a default object
	 * can be instantiated, which represents an empty infix 
	 * expression to begin.
	 */
	public CalculatorLabel() {
		// set expression to empty string initially and make visible
		this.infix = "";
		this.setText(this.infix);
		
		// set default text properties of label
		this.setHorizontalTextPosition(JLabel.RIGHT);
		this.setHorizontalAlignment(JLabel.RIGHT);
		this.setVerticalTextPosition(JLabel.CENTER);
		this.setFont(this.getFont().deriveFont(24.0f));
	}
	
	/**
	 * Helper method used to append text to the infix expression, and
	 * then update the value on the GUI label component. Not all values
	 * passed in are appended to the string however, certain values such
	 * as the "=" sign call another helper method to evaluate the current
	 * infix expression, and the "clear" symbol resets the current infix
	 * string.
	 * 
	 * @param s String to be appended to 
	 */
	public void appendText(String s) {
		
		// if its an operator (not equals) or brackets, surround operator with spaces
		if (CalculatorLabel.SYMBOLS.indexOf(s) != -1) {
			this.infix += " " + s + " ";	
		}
		// if it's the equals operator, evaluate expression
		else if (s.equals("=")) { 
			this.getResult();
			return;
		}
		else if (s.equals("clear")) {
			this.infix = ""; // reset infix expression
		}
		else {
			this.infix += s;
		}
		// display updated infix expression in GUI
		this.setText(this.infix);
	}
	
	/**
	 * Helper method to respond to when a user presses the backspace
	 * key on the GUI. It handles the different scenarios that the 
	 * infix expression can be to prevent errors from being thrown.
	 */
	public void removeOne() {
		
		// if the expression has one or zero characters, then just
		// set the infix to be an empty string
		if (this.infix.length() == 1 || this.infix.length() == 0) {
			this.infix = "";
		}
		// if the expression has a space at the end, you really want to
		// remove the space, the preceding operator/bracket and the space
		// before that, so remove the last three characters
		else if (this.infix.charAt(this.infix.length()-1) == ' ') {
			if (this.infix.length() >= 3)
				this.infix = this.infix.substring(0, this.infix.length()-3);
			else
				this.infix = "";
		}
		// otherwise, just remove the last character from the string
		else {
			this.infix = this.infix.substring(0, this.infix.length()-1);
		}
		
		// update the GUI label to show the correct infix value 
		this.setText(this.infix);
	}
	
	/**
	 * Helper method used to evaluate current infix expression.
	 * 
	 * This method makes use of the ExpressionTools class, which 
	 * provides useful methods for converting infix to postfix, and
	 * for evaluating postfix expressions. If the user enters an invalid
	 * expression, the label will update its text to be INVALID. It then 
	 * automatically resets the infix expression, and the user can start 
	 * typing a new expression.
	 */
	public void getResult() {
		try {
			this.infix = this.infix.trim();
			// validate expression (if not throws a PostFixException)
			String[] exps  = ConsoleCalculator.validateExpression(this.infix.toString());
			// convert from infix to postfix (if error, throws PostFixException)
			String postfix = ExpressionTools.infixToPostfix(exps);
			// compute result of postfix expression (if error, throws PostFixException)
			String result     = Integer.toString(ExpressionTools.evaluatePostfix(postfix));
			
			// update infix expression to be result and display
			this.infix = result;
			this.setText(this.infix);
		}
		catch (PostFixException e) { // if exception was thrown anywhere
			
			// let user know expression was invalid, and start again
			this.setText("INVALID");
			this.infix = "";
		}
	}
}
