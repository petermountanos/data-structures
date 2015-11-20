package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Trie Tree Implementation of a dictionary of words.
 * @author pDm
 *
 */
public class DictionaryTrie {

	/*Fields*/
	private Node root;
	private String fileName;
	public int size; 

	public DictionaryTrie(String filename){
		this.root = new Node(Node.ROOT_VALUE); // root node
		this.fileName = filename;
		this.populateDictionary();
		this.size = size(this.root);
	}

	public void insert(String word){

		char[] letters = word.toCharArray();
		Node currentNode = this.root; // initialize node to root node

		// for each character in the word
		for (int i = 0; i < word.length(); i++){
			if (currentNode.getChild(letters[i]) == null){
				currentNode.addChild(letters[i]);
			}
			currentNode = currentNode.getChild(letters[i]);
		}
		currentNode.completesWord = true;
		this.size++;
	}

	public boolean contains(String word){

		char[] letters = word.toCharArray();
		Node currentNode = this.root;
		int depth;

		for (depth = 0; depth < word.length(); depth++){
			if (currentNode == null){
				return false;
			}
			currentNode = currentNode.getChild(letters[depth]);
		}

		if (depth == 1 && currentNode == null){
			return false;
		}
		else if (currentNode != null && currentNode.completesWord == false){
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean containsPrefix(String prefix){
		
		char[] letters = prefix.toCharArray();
		Node currentNode = this.root;
		
		for (int depth = 0; depth < prefix.length(); depth++){
			if (currentNode == null){
				return false;
			}
			currentNode = currentNode.getChild(letters[depth]);
		}
		
		if (currentNode == null){
			return false;
		}
		
		return true;
	}

	public String toString(){
		String trieString = "{\n";
		Node currentNode = this.root;

		for (Node child : currentNode.getChildren()){
			if (child != null){
				trieString += child.getValue() + ":{";
				char[] vals = new char[50];
				trieString += DictionaryTrie.branchString(child.getValue(), child, 0, vals);
				trieString += "}\n";
			}
		}
		trieString += "}";
		System.out.println(trieString);
		return trieString;
	}

	public void populateDictionary(){
		try {
			Scanner scan = new Scanner(new File(this.fileName));
			while (scan.hasNext()){
				this.insert(scan.next().toLowerCase());
			}
			scan.close();
		} catch (FileNotFoundException e) {}
	}

	public static String branchString(char parentLetter, Node n, int depth, char[] vals){

		String retString = "";

		if (n == null) return retString;

		for (int pos = 0; pos < n.getChildren().length; pos++){
			vals[depth] = n.getValue();
			retString += branchString(parentLetter, n.getChild(pos+Node.ASCII_OFFSET),depth+1, vals);
		}

		if (n.completesWord){
			retString += parentLetter;
			for (int i = 1; i <= depth; i++){
				retString += vals[i];
			}
			retString += ",";
		}
	
		return retString;
	}

	private static int size(Node root){
		int trieSize = 0;
		
		if (root == null) return trieSize;
		
		for (int depth = 0; depth < root.getChildren().length; depth++){
			trieSize += size(root.getChild(depth+Node.ASCII_OFFSET));	
		}
		
		if (root.completesWord) trieSize += 1;
		
		return trieSize;
	}
}

class Node {

	/*Constants*/
	public static final char ROOT_VALUE = '0';
	public static final int ASCII_OFFSET = 97;

	/* Fields */
	/**value: variable which stores letter of node*/
	private char value;

	/**children: array which stores links to other nodes
	 * Initialized to 26 null indexes, which represents
	 * an index for each letter in the alphabet */
	private Node[] children = new Node[26];

	/**completesWord: boolean value which represents if
	 * the current node completes a word. This is necessary
	 * when you traverse down branches that contain 'prefixes'
	 * I.e., given the nodes linking r-a-c-e-c-a-r, completesWord
	 * should be true at the 4th node (e) for race, and the 7th 
	 * node (r) for racecar.*/
	public boolean completesWord = false;

	/*Constructor*/
	/**Initializes an instance of a Node
	 * object, which represents a node in a trie tree. 
	 * In this specific case, the nodes are used for a 
	 * trie tree which represents a dictionary of words.
	 * @param value char
	 */
	Node(char value){
		this.value = value;
	}

	/*Getters & Setters*/
	public char getValue(){
		return this.value;
	}

	public Node[] getChildren(){
		return this.children;
	}

	public void addChild(char value){
		this.children[value-Node.ASCII_OFFSET] = new Node(value);
	}

	public Node getChild(int value){
		return this.children[value-Node.ASCII_OFFSET];
	}
}
