// Name: Ria Talwar
// Date: July 21, 2021

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.IOException;

public class Checker
{
	public static Map<Character, Character> getPairs ()
	{
		Map<Character, Character> pairs = new TreeMap<Character, Character>();
		// Add pairs of open/closing
		pairs.put('(', ')');
		pairs.put('[', ']');
		pairs.put('{', '}');
		pairs.put('<', '>');
		return pairs;
	}
	
	public static boolean isValid (String line)
	{
		Map<Character, Character> pairs = getPairs();
		Stack<Character> opening = new Stack<Character>();
		for (int i = 0; i < line.length(); i++)
		{
			// If pair match and is in order
			if (!opening.isEmpty() && line.charAt(i) == pairs.get(opening.peek()))
				opening.pop();
			// If is opening
			else if (pairs.containsKey(line.charAt(i)))
				opening.push(line.charAt(i));
			// If is invalid close
			else if (pairs.containsValue(line.charAt(i)))
				return false;
		}
		// Return true only if all pairs have been closed
		return opening.isEmpty();
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("expressions.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
		{
			// Print result of each expression
			if (isValid(file.nextLine())) System.out.println("Valid");
			else System.out.println("Invalid");
		}
	}
}
