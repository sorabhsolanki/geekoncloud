package com.innovation.mbean;

import java.util.StringTokenizer;

import com.innovation.grammer.Grammer;

public class Hello implements HelloMBean{

	private String message = null;

	   public Hello() {
	      message = "Hello, world";
	   }

	   public Hello(String message) {
	      this.message = message;
	   }

	   public void setMessage(String message) {
	      this.message = message;
	   }

	   public String getMessage() {
	      return message;
	   }

	   public void sayHello() {
		  StringTokenizer tokenizer = new StringTokenizer(message, " ");
		  
		  System.out.println("===========================================" + message);
		  String nameOfPerson = tokenizer.nextToken();
		  String  word = tokenizer.nextToken();
		  String date = tokenizer.nextToken();
		  
	      System.out.println(word);
	      Grammer.addWord(Grammer.root, word, nameOfPerson + " " +date);
	   }

	@Override
	public void getGrammer(String message) {
		System.out.println(Grammer.searchOne(Grammer.root, message));
	}
	   
	   

}
