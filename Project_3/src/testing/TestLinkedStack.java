package testing;

import edu.nyu.cs.pjm419.LinkedStack;

public class TestLinkedStack {
	
	public static void main(String[] args) {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		
		System.out.println(5/0);
		System.out.println("PUSHING 5 ITEMS ONTO THE STACK\n");
		for (int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		
		System.out.println("\nPeaking: "+stack.peek());
		System.out.println("\nPOPPING EVERYTHING OFF OF THE STACK\n");
		for (int i = 0; i < 5; i++) {
			System.out.println("Popping "+stack.pop()+"\t"+stack);
		}
		
		System.out.println("\nIs stack empty?\t"+stack.isEmpty());
	}
}
