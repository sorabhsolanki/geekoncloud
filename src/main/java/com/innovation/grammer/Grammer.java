package com.innovation.grammer;


public class Grammer {
	
	public static Node root;
	
	static{
		init();
	}
	
	public static void init(){
		root = new Node();
	}
	
	public static void addWord(Node root, String word, String symbol){
		
		int index = word.charAt(0) - 97;
		Node temp = root;
		if(temp.children[index] == null)
		   temp.children[index] = new Node(word.charAt(0), false);
		temp = temp.children[index];
		
		for(int i = 1; i < word.length(); i++){
			char data = word.charAt(i);
			index = data - 97;
			if(temp.children[index] == null)
			     temp.children[index] = new Node(data, false);
			temp = temp.children[index];
		}
		
		temp.isTerminal = true;
		temp.symbol = symbol;
	}
	
	/*public static String search(Node root, String word){
		Node temp = root;
		String result = null;
		for(int i = 0; i < word.length(); i++){
			for(int j = 0; j < temp.children.length; j++){
				if(temp.children[j] == null)
					continue;
				else if(temp.children[j].data == word.charAt(i) && temp.children[j].isTerminal)
					return temp.children[j].symbol;
				else if(temp.children[j].data == word.charAt(i)){
					temp = temp.children[j];
					break;
				}
			}
		}
		return result;
	}
	*/
	
	public static String searchOne(Node root, String word){
		Node temp = root;
		String result = null;
		for(int i = 0; i < word.length(); i++){
			char data = word.charAt(i);
			int index = data - 97;
			
			if(temp.children[index] == null)
				return "not found";
			if(temp.children[index].isTerminal && i == (word.length() - 1)){
				return temp.children[index].symbol;
			}else if(temp.children[index].data == data){
				temp = temp.children[index];
			}
		}
		return result;
	}
	
	public static class Node{
		Node[] children = new Node[26];
		char data;
		boolean isTerminal = false;
		String symbol;
		
		public Node(char data, boolean isTerminal) {
			this.data = data;
			this.isTerminal = isTerminal;
		}
		
		public Node(){}
	}
	
	public static void main(String[] args) {
		Grammer grammer = new Grammer();
		grammer.init();
		int data = 'a';
		
		grammer.addWord(grammer.root, "after", "+");
		grammer.addWord(grammer.root, "before", "-");
		
		grammer.addWord(grammer.root, "afte", "today");
		
		
		System.out.println(grammer.searchOne(grammer.root, "before"));
		System.out.println(grammer.searchOne(grammer.root, "after"));
		System.out.println(grammer.searchOne(grammer.root, "afte"));
		
	}

}
