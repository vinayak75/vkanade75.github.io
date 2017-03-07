package com.assignment.second_question_and_third_question;

import java.util.Scanner;
/**
 *
 * @author Vinayak Kanade
 *
 */
public class CountMaxWord {
	public int getMaxSize(String str){
		int maxSize=0;
		String[] splitted = str.split("[.!?] |[.!?]");
		for (int i = 0; i < splitted.length; i++) {
			if(maxSize<splitted[i].length()){
				maxSize=splitted[i].length();
			}
		}
		return maxSize;
	}
		public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter string");
		String str=scanner.nextLine();
		CountMaxWord countMaxWord=new CountMaxWord();
		System.out.println("max Size="+countMaxWord.getMaxSize(str));
		
	}

}
