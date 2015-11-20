package edu.nyu.cs.pjm419;

/**
 * The ExpressionTools class contains essential methods for
 * the RPN calculator application. This class contains two main
 * methods; one for converting infix mathematical expressions to
 * postfix mathematical expressions, and the other for evaluating 
 * postfix expressions. This evaluation method only performs integer
 * arithmetic, not floating point arithmetic.  
 * 
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
public class ExpressionTools {

	// class constants
	/**
	 * String constant storing the allowable operators in
	 * the infix and postfix expressions
	 */
	public static final String OPERATORS = "*/+-";

	/**
	 * Method for evaluating postfix expressions.
	 * 
	 * @param expression a string storing the postfix expression
	 * @return the arithmetic value of the postfix expression
	 * @throws PostFixException if method cannot properly evaluate the expression
	 */
	public static int evaluatePostfix(String expression) throws PostFixException {
		// split the expression on whitespace into tokens
		String[] postfix = expression.split("\\s+");
		// stack used to store the operands to facilitate computation
		LinkedStack<Integer> stack = new LinkedStack<Integer>();

		for (String token : postfix) {
			// if the token is an operand
			if (token.matches("^\\d+$")) {
				// push it onto the stack
				stack.push(Integer.parseInt(token));
			}
			// if the token is an operator
			else if (OPERATORS.indexOf(token) != -1) {
				// pop two operators off of the stack and 
				// compute their value based on the operator token
				int op1 = stack.pop();
				int op2 = stack.pop();
				// pass in reverse order because of stack nature
				int result = computeResult(token, op2, op1);
				// push the result back onto the stack
				stack.push(result);
			}
		}
		// the final value is the last value left on the stack
		return stack.pop();
	}

	/**
	 * Converts an infix mathematical expression to a 
	 * postfix mathematical expression.
	 * 
	 * @param tokens a string array representing a valid infix
	 *        expression separated on whitespace
	 * @return a String instance of the postfix expression
	 */
	public static String infixToPostfix(String[] tokens) {
		// string buffer to store current value of postfix expression
		StringBuffer postfixExpression = new StringBuffer();
		// stack used to store operators to facilitate conversion process
		LinkedStack<String> stack = new LinkedStack<String>();

		for (String token : tokens) {
			// if the token is an operand append it to the postfix string
			if (isNumber(token)) {
				postfixExpression.append(token + " ");
			}
			// if the token is a left parenthesis push it onto the stack
			else if (token.equals("(")) {
				stack.push(token);
			}
			// if the token is an operator...
			else if (OPERATORS.indexOf(token) != -1) {
				// pop the stack and append it to the postfix string while the top
				// of the stack has a higher or equal to precedence of the token, after
				// push the token onto the stack
				while (!stack.isEmpty() && isHigherPrecedence(stack.peek(), token)) {
					postfixExpression.append(stack.pop() + " ");
				}
				stack.push(token);
			}
			// if the token is a right parenthesis
			else if (token.equals(")")){
				while (!stack.isEmpty()) {
					// if the top value of the top of the stack is not a 
					// matching left parenthesis, pop it and append it to 
					// the postfix string expression
					if (!stack.peek().equals("(")) {
						postfixExpression.append(stack.pop() + " ");
					}
					// else if it is a matching left parenthesis, pop it and discard
					else {
						stack.pop();
						break;
					}
				}
			}
		}

		// iterate through the stack and pop off the top value
		// and append it to the postfix expression until empty
		while (!stack.isEmpty()) {
			postfixExpression.append(stack.pop() + " ");
		}

		// final string instance of postfix expression
		return postfixExpression.toString();
	}

	
	/**
	 * Wrapper Regex method to determine if a String
	 * object contains only digits.
	 * 
	 * @param n string to be checked
	 * @return true if n contains only digits, else false
	 */
	public static boolean isNumber(String n) {
		return n.matches("^\\d+$");
	}
	
	/**
	 * Computes if op1 has a higher preference compared to op2.
	 * This method assumes that multiplication and division have
	 * the same precedence, which is greater than the precedence 
	 * of both addition and subtraction, which are the same. It also 
	 * assumes precedence by left-associativity, if the two operators
	 * are of the same precedence.
	 *  
	 * @param op1 operator one (left) to be evaluated
	 * @param op2 operator two (right) to be evaluated
	 * @return true if precedence(op1) >= precedence(op2), else false
	 */
	private static boolean isHigherPrecedence(String op1, String op2) {
		return operatorVal(op1) >= operatorVal(op2);
	}

	/**
	 * Method used to determine the precedent value
	 * of an operator. Multiplication and division have
	 * the same value of 1, addition and subtraction both
	 * have the same value of 0, and anything else (usually
	 * a bracket) should have a value of -1.
	 * 
	 * @param op string value of the operator to be determined
	 * @return the precedent value of the operator
	 */
	private static int operatorVal(String op) {
		if (op.equals("*") || op.equals("/"))
			return 1;
		else if (op.equals("+") || op.equals("-"))
			return 0;
		else 
			return -1;
	}

	/**
	 * Method used to compute the integer arithmetic between two
	 * operands and an operator. 
	 * 
	 * @param operator string containing the operator to use
	 * @param op1 integer value of the first operand
	 * @param op2 integer value of the second operand
	 * @return op1 <operator> op2
	 * @throws PostFixException if the computation cannot be carried out
	 */
	private static int computeResult(String operator, int op1, int op2) throws PostFixException {

		try {
			// addition
			if (operator.equals("+")) return op1 + op2;
			// subtraction
			else if (operator.equals("-")) return op1 - op2;
			// division
			else if (operator.equals("/")) return op1 / op2;
			// multiplication
			else return op1 * op2;
		}
		catch (ArithmeticException e) {
			throw new PostFixException("Arithmetic Exception");
		}
	}
}
