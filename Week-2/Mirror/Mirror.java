// Name: Ria Talwar
// Date: July 21, 2021

import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.IOException;

public class Mirror
{
	public static String mirror (String list)
	{
		Scanner line = new Scanner(list);
		Stack<String> nums = new Stack<String>();
		String mirrored = "";
		// Add original chars and build stack
		while (line.hasNext())
		{
			String n = line.next();
			mirrored += n + " ";
			nums.push(n);
		}
		// Add reflection to mirrored string
		for (int i = 0; i < nums.size(); i++)
			mirrored += nums.pop() + " ";
		return mirrored;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("Mirror.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
			System.out.println(mirror(file.nextLine()));
	}
}
