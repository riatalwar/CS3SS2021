import java.util.Scanner;
import java.util.ListIterator;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

public class RemoveIt
{
	public static ArrayList<Character> removeChar(String str)
	{
		char c = str.charAt(str.indexOf(" ") + 1);				// Get character to remove
		ArrayList<Character> line = new ArrayList<Character>(); // Create list of characters
		for (int i = 0; i < str.length(); i++)
			line.add(str.charAt(i));
		ListIterator<Character> it = line.listIterator();		// Create iterator to go over chars
		
		while (true)
		{
			char nxt = it.next();		// Get the next character
			if (nxt == c) it.remove();	// Check if char needs to be removed
			else if (nxt == ' ') break; // Check if this is the end of the line
		}
		
		// Remove space and char at the end
		it.remove();
		it.next();
		it.remove();
		return line;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("removeIt.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
			System.out.println(removeChar(file.nextLine()));
	}
}
