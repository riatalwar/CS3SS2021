// Name: Ria Talwar
// Date: July 20, 2021

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class Acronyms
{
	private Map<String,String> acronymTable;

	public Acronyms(Scanner file)
	{
		acronymTable = new TreeMap<String, String>();		// Initialize map
		int numLines = Integer.parseInt(file.nextLine());	// Get number of lines of data
		for (int i = 0; i < numLines; i++)					// Read and store translation data
			putEntry(file.nextLine());
	}

	public void putEntry(String entry)
	{
		String[] list = entry.split(" - ");	// Get [acronym, phrase] words
		acronymTable.put(list[0], list[1]);	// Add acronym translation to the list
	}

	public String convert(String sent)
	{
		Scanner chop = new Scanner(sent);
		String output = "";
		while (chop.hasNext())
		{
			String word = chop.next();
			// Acronym
			if (acronymTable.containsKey(word)) output += acronymTable.get(word) + " ";
			// Punctuation acronym
			else if (acronymTable.containsKey(word.substring(0, word.length() - 1)))
				output += acronymTable.get(word.substring(0, word.length() - 1)) + word.charAt(word.length() - 1) + " ";
			// Normal
			else output += word + " ";
		}
		return output;
	}

	public String toString()
	{
		return "";
	}
}