import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class ReplaceIt
{
	public static ArrayList<Character> replaceChar(String str)
	{
		char orig = str.charAt(str.indexOf(" ") + 1);			// Get character to replace
		char add = str.charAt(str.lastIndexOf(" ") + 1);			// Get character to add
		ArrayList<Character> line = new ArrayList<Character>(); // Create list of characters
		for (int i = 0; i < str.length(); i++)
			line.add(str.charAt(i));
		ListIterator<Character> it = line.listIterator();		// Create iterator to go over chars
		
		while (true)
		{
			char nxt = it.next();			// Get the next character
			if (nxt == orig) it.set(add);	// Check if char needs to be removed
			else if (nxt == ' ') break;		// Check if this is the end of the line
		}
		
		// Remove spaces and chars at the end
		it.remove();
		for (int i = 0; i < 3; i++)
		{
			it.next();
			it.remove();
		}
		return line;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("replaceIt.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
			System.out.println(replaceChar(file.nextLine()));
	}
}