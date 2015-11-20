package edu.nyu.cs.pjm419;

public class Test {
	
	public static void main(String[] args) {
		String[] arr = {"(" , "+" , "-" , "*" , "/" , ")", "0","1","2","3","4","5","6","7","8","9"};
		QuickSort.sort(arr);
		String s = "{";
		for (String st : arr){
			s += "\"" + st + "\" , ";
		}
		System.out.println(s.substring(0,s.length()-3)+ "}");
	}
	
}
