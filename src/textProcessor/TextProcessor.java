package textProcessor;

import java.util.Stack;

public class TextProcessor {
	
	// InFix, Operators and PostFix Stacks
	private Stack<String> inFix = new Stack<String>();
	private Stack<String> operators = new Stack<String>();
	private Stack<String> postFix = new Stack<String>();
	
	// Process operations and return result
	public double process(String text) {
		double result = 0;
		
		// Calculate inFix and postFix Stacks
		calculateInFix(text);
		calcPostFix();

		// Calculate result from inFix Stack
		result = getResult();
		
		// Print process to the terminal
		System.out.println("- Final Result:");
		System.out.println("--> inFix: " + inFix);
		System.out.println("--> operators: " + operators);
		System.out.println("--> postFix: " + postFix);
		System.out.println("--> result: " + result);
		
		return result;
	}
	
	// Takes the expression as argument and makes the InFix Stack
	private void calculateInFix(String exp) {
		char op; // A character in the expression
		
		for(int i = 0; i < exp.length(); i++) {
			
			op = exp.charAt(i);
		
			// First 2 if-statements handle negative numbers
			if(op == '-' && i != 0 && exp.charAt(i-1) != '(') {
				inFix.push("+");
				inFix.push("0");
				inFix.push("-");
			}
			else if(op == '-') {
				inFix.push("0");
				inFix.push("-");
			} // To add numbers to infix and not separate them
			else if(Character.isDigit(op)) {
				String num = String.valueOf(op);
				while(i < exp.length()-1) {
					char nextChar = exp.charAt(i+1);
					if(Character.isDigit(nextChar) || nextChar == '.') {
						num = num + nextChar;
						i = i+1;
					}
					break;
				}
				inFix.push(num);
			}
			else {
				inFix.push(String.valueOf(op));
			}
			
		}
	}
	
	// Calculates postFix Stack
	private void calcPostFix() {
		// For every item in the inFix Stack
		inFix.forEach(op -> {
			if(op.equalsIgnoreCase("(")) {
				operators.push(op);
			}
			else if(op.equalsIgnoreCase(")")) {
				while(!operators.peek().equalsIgnoreCase("(")) {
					postFix.push(operators.pop());
				}
				operators.pop();
			}
			else if(isOperator(op)) {
				while(operators.size() > 0 && getPriority(op) <= getPriority(operators.peek())) {
					postFix.push(operators.pop());
				}
				operators.push(op);
				
			}
			else {
				postFix.push(op);
			}
		});
		
		while(operators.size() > 0) {
			postFix.push(operators.pop());
		}
	}
	
	// Return true if op is an operator, false otherwise
	private boolean isOperator(String op) {
		if("-x÷+^".contains(op)) {
			return true;
		}
		return false;
	}

	// Return the priority of operator
	private int getPriority(String op) {
		int priority = 0;
		
		if("()".contains(op)) {
			priority = -1;
		}
		else if(op.equalsIgnoreCase("^")) {
			priority = 3;
		}
		else if("x÷".contains(op)) {
			priority = 2;
		}
		else {
			priority = 1;
		}
		
		return priority;
	}
	
	// Return the result of the expression
	private double getResult() {
		System.out.println("Calculating...");
		double res = 0;
		
		int i = 0;
		while(postFix.size() > 1) {
			String op = postFix.get(i);
			if(isOperator(op)) {
				double operationRes = 0;
				// Get the two numbers before the operator
				double num1 = Double.parseDouble(postFix.get(i-2));
				double num2 = Double.parseDouble(postFix.get(i-1));
				// Perform the operation according to the 2 previous numbers
				if(op.equalsIgnoreCase("+")) {
					operationRes = num1 + num2;
				}
				else if(op.equalsIgnoreCase("-")) {
					operationRes = num1 - num2;
				}
				else if(op.equalsIgnoreCase("x")) {
					operationRes = num1 * num2;
				}
				else if(op.equalsIgnoreCase("÷")) {
					if(num2 == 0) {
						// Check what to throw in case of an error
						if(num1 == 0) {
							return Double.NaN;
						}
						else if(num1 > 0)
							return Double.POSITIVE_INFINITY;
						else
							return Double.NEGATIVE_INFINITY;
					}
					operationRes = num1 / num2;
				}
				else if(op.equalsIgnoreCase("^")) {
					operationRes = Math.pow(num1, num2);
				}
				
				// Remove the 2 numbers and the operator
				postFix.remove(i);
				postFix.remove(i-1);
				postFix.remove(i-2);
				// Add the result of the operation where the first number was
				postFix.add(i-2, String.valueOf(operationRes));
				// set i to -1 so that it will be set to 0 after
				i = -1;
			}
			i = i + 1;
		}
		
		// Convert String to double and return it
		res = Double.parseDouble(postFix.get(0));
		return res;
	}
}