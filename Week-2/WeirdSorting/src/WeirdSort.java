// Name: Ria Talwar
// Date: July 20, 2021

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WeirdSort
{
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("weirdSort.dat"));
		int numLines = Integer.parseInt(file.nextLine());		
		
		Map<String, Integer> words = new TreeMap<String, Integer>
		(
				// Create custom comparator to implement weird sort
                new Comparator<String>()
                {
                    @Override
                    public int compare(String str1, String str2)
                    {
                    	int compare2 = str1.charAt(1) - str2.charAt(1);
                		if (compare2 != 0) return compare2;
                		return str1.charAt(3) - str2.charAt(3);
                    }
                }
         );
		// Add words to map
		for (int i = 0; i < numLines; i++)
			words.put(file.nextLine(), null);
		// Print sorted words
		for (Map.Entry<String, Integer> entry : words.entrySet())
			System.out.println(entry.getKey());
	}
}
