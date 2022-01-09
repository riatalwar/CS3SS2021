// Name: Ria Talwar
// Date: July 21, 2021

import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.IOException;

public class SplitStack
{
	public static Stack<Integer> buildSplitStack (Scanner line)
	{
		Stack<Integer> pos = new Stack<Integer>();
		Stack<Integer> neg = new Stack<Integer>();
		while (line.hasNext())
		{
			int n = Integer.parseInt(line.next());
			if (n < 0) neg.push(n);
			else pos.push(n);
		}
		for (int j = 0, n = pos.size(); j < n; j++)
			neg.push(pos.pop());
		return neg;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("splitStack.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
		{
			Scanner line = new Scanner(file.nextLine());
			Stack<Integer> split = buildSplitStack(line);
			System.out.println(split);
		}
	}
}
