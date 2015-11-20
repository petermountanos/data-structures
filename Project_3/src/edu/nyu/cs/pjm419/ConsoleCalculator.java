package edu.nyu.cs.pjm419;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to represent the console-based version of this calculator.
 * 
 * This class makes use of several helper methods from both this
 * class and the ExpressionTools class to convert infix expressions
 * to postfix expressions, and then evaluate the postfix expressions.
 * 
 * This class assumes two command-line arguments, the first representing
 * the input file path reference, and the second represents the output file
 * path reference. This class iterates through the input file, which contains
 * an infix mathematical expression on each line, and then tries to generate
 * a conversion to a postfix expression and then its evaluation. It then prints
 * the value to the output file, matching the corresponding line number. If it 
 * is unable to convert or evaluate the expression, it prints "INVALID" on the line
 * of the output file.
 *  
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
public class ConsoleCalculator {

	// class variables
	/**
	 * Scanner object for reading the input file
	 */
	private static Scanner input;		
	/**
	 * PrintWriter object for writing to the output file
	 */
	private static PrintWriter output;	

	/**
	 * Main method which is responsible for calls helper methods to complete
	 * execution. 
	 * 
	 * @param args array of command line arguments, precondition that
	 * 		  array length must be two, where the first arg is the
	 * 		  path to the input file, and the second arg is the path to
	 * 		  the output file
	 */
	public static void main(String[] args){

		validateCLInput(args);		// validate the command-line arguments
		getPostFixExpressions();	// convert expressions to postfix and evaluate  

		// close input and output files
		output.close();
		input.close();
	}


	/**
	 * This method iterates through the input file grabbing one
	 * infix expression per line. It then validates that it is a proper
	 * infix expression using a helper method. Then, it will convert it 
	 * to its corresponding postfix expression, and evaluate that. If it
	 * cannot complete any of the steps, it catches a PostFixException, and
	 * prints INVALID on the corresponding output file line.
	 */
	public static void getPostFixExpressions(){
		while (input.hasNext()) {
			// must remove and leading/trailing whitespace to evaluate properly
			String expression = input.nextLine().trim();
			try {
				// validate infix expression
				String[] exps = validateExpression(expression);
				// convert to postifx
				String postfix = ExpressionTools.infixToPostfix(exps);
				// evaluate the postfix expression
				int val = ExpressionTools.evaluatePostfix(postfix);
				// print the value to its own line in output file
				output.println(val);

			}
			// if at any point it could not complete step, the methods
			// above would throw a PostFixException; this will then
			// print invalid to the output file line
			catch (PostFixException e){
				output.println("INVALID");
			}
		}
	}

	/**
	 * Helper method used to validate an infix expression. 
	 * 
	 * A valid infix expression contains only operands (numbers) and 
	 * symbols (left and right brackets and +-/* operators). Also, the 
	 * number of operands must be one greater than the number of operators.
	 * Further, the number left parentheses must equal the number of right
	 * parentheses. Finally, an expression must begin and end with an operand
	 * or an appropriate parenthesis.
	 * 
	 * @param  expression the infix string to be validated
	 * @return an array of tokens of the infix expression split on whitespace
	 * @throws PostFixException if any part of the expression is not valid
	 */
	public static String[] validateExpression(String expression) throws PostFixException {

		String[] exps = expression.split("\\s+"); // split on whitespace

		// if the expression doesn't start and end with either a parenthesis
		// or a number, throw a postfix exception
		if (!ExpressionTools.isNumber(exps[0]) && !exps[0].equals("(")) {
			throw new PostFixException();
		}
		else if (!ExpressionTools.isNumber(exps[exps.length-1])
				&& !exps[exps.length-1].equals(")")) {
			throw new PostFixException();
		}

		int leftBraces = 0, rightBraces = 0, operands = 0, operators = 0;

		// check each token for validity
		for (int i = 0; i < exps.length; i++){		
			// get number of brackets, operators and operands
			if (exps[i].equals("(")) leftBraces++;
			else if (exps[i].equals(")")) rightBraces++;
			else if (ExpressionTools.isNumber(exps[i])) operands++;
			else if (ExpressionTools.OPERATORS.indexOf(exps[i]) != -1) operators++;
			// if its not a bracket, operator or operand, invalid expression
			else throw new PostFixException();
		}

		// left brackets must match number of right brackets
		if (leftBraces != rightBraces) throw new PostFixException();
		// must be one more operand than operator
		if (operands != (operators + 1)) throw new PostFixException();

		return exps; // return as array split on whitespace if valid
	}

	/**
	 * This helper method validates that the command-line input is valid.
	 * First it checks that there is a reference to both an input and output
	 * file. It the input file doesn't exist, it prints an error and quits. It
	 * the output file doesn't exist, it will create one given the filename and
	 * path. It also checks that the user has permission to read/write to the 
	 * respective files. It then tries to make a Scanner object class variable
	 * for the input file, and also tries to make a PrinterWrite object class
	 * variable for the output file. 
	 * 
	 * @param args an array storing the command-line arguments passed into 
	 * 		  the main method
	 */
	public static void validateCLInput(String[] args) { 
		// if user doesn't provide both input & output file paths
		if (args.length != 2){
			System.err.println("Usage: java ConsoleCalculator <input filename> <output filename>");
			System.exit(1);
		}

		// check if input file exists, and if user has read permissions
		File inputFile = new File(args[0]);
		if (!inputFile.exists()){
			System.err.println("Error: could not find input file \""+args[0]+"\"");
			System.exit(1);
		}
		else if (!inputFile.canRead()) {
			System.err.println("Error: do no have permissions to read input file \""+args[0]+"\"");
			System.exit(1);
		}

		// check if output file exists, and if user has write permissions
		// if output file doesn't exist, the program creates one given the
		// path input
		File outputFile = new File(args[1]);
		if (!outputFile.exists()){
			try {
				System.out.println("creating");
				outputFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Error: could not create output file \""+args[1]+"\"");
			}
		}
		else if (!outputFile.canWrite()) {
			System.err.println("Error: do no have permissions to write output file \""+args[0]+"\"");
			System.exit(1);
		}


		// try to make a scanner object from input file path
		try {
			input = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: could not convert input file \""+args[0]+"\" to Scanner object");
			System.exit(1);
		}

		// try to make a scanner object from output file path
		try {
			output = new PrintWriter(outputFile, "UTF-8");
		} catch (IOException e) {
			System.err.println("Error: could not convert output file \""+args[1]+"\" to PrintWriter object");
			System.exit(1);
		}
	}
}