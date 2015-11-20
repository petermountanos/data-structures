package testing;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import edu.nyu.cs.pjm419.*;

public class TestExpressionTools {
	
	public static void main(String[] args) {
		String exp = "( ( 15 / ( -7 - ( 1 + 1 ) ) ) - ( 2 + ( 1 + 1 ) )";
		try {
			System.out.println(evaluate("3 2 1 + 5 * + 4 -"));
		} catch (PostFixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int evaluate(String expression) throws PostFixException {
		Stack<Integer> stack = new Stack<Integer>();

		int value;
		String operator;

		int operand1;
		int operand2;

		int result = 0;

		Scanner tokenizer = new Scanner(expression);

		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextInt()) {
				// Process operand.
				value = tokenizer.nextInt();

				stack.push(value);
			} else {
				// Process operator.
				operator = tokenizer.next();

				// Obtain second operand from stack.
				if (stack.isEmpty()) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}
				operand2 = stack.peek();
				stack.pop();

				// Obtain first operand from stack.
				if (stack.isEmpty()) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}
				operand1 = stack.peek();
				stack.pop();

				// Perform operation.
				if (operator.equals("/"))
					result = operand1 / operand2;
				else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				else {
					tokenizer.close();
					throw new PostFixException("Illegal symbol: " + operator);
				}

				// Push result of operation onto stack.
				stack.push(result);
			}
		}

		tokenizer.close();

		// Obtain final result from stack.
		if (stack.isEmpty())
			throw new PostFixException("Not enough operands - stack underflow");
		result = stack.peek();
		stack.pop();

		// Stack should now be empty.
		if (!stack.isEmpty())
			throw new PostFixException("Too many operands - operands left over");

		// Return the final.
		return result;
	}
}
