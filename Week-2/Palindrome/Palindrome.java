// Name: Ria Talwar
// Date: July 21, 2021

import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.IOException;

public class Palindrome
{
	public static boolean isPalindrome (String word)
	{
		Stack<Character> front = new Stack<Character>();
		Stack<Character> back = new Stack<Character>();
		
		// Add front and back of the word to their respective stacks
		for (int j = 0; j < word.length() / 2; j++)
		{
			front.push(word.charAt(j));
			back.push(word.charAt(word.length() - (j + 1)));
		}
		
		// Clears stacks and checks that the front is identical to the back
		boolean isPalindrome = true;
		for (int j = 0; j < word.length() / 2; j++)
			if (front.pop() != back.pop()) isPalindrome = false;
		
		return isPalindrome;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("palindrome.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		
		for (int i = 0; i < numLines; i++)
		{
			String word = file.nextLine();
			// Print results
			if (isPalindrome(word)) System.out.println("Palindrome");
			else System.out.println("Not a palindrome");
		}
	}
}
