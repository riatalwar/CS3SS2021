// Name: Ria Talwar
// Date: July 20, 2021

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class SpanishToEnglish
{
	private Map<String,String> pairs;

	public SpanishToEnglish(Scanner file)
	{
		pairs = new TreeMap<String, String>();				// Initialize map
		int numLines = Integer.parseInt(file.nextLine());	// Get number of lines of data
		for (int i = 0; i < numLines; i++)					// Read and store translation data
			putEntry(file.nextLine());
	}

	public void putEntry(String entry)
	{
		String[] list = entry.split(" ");	// Get [Spanish, English] words
		pairs.put(list[0], list[1]);		// Add word translation to the list
	}

	public String translate(String sent)
	{
		Scanner chop = new Scanner(sent);
		String output = "";
		// For word in chop add translation to output
		while (chop.hasNext())
			output += pairs.get(chop.next()) + " ";
		return output;
	}

	public String toString()
	{
		return pairs.toString().replaceAll("\\,","\n");
	}
}