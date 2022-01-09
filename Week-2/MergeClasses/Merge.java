// Name: Ria Talwar
// Date: July 22, 2021

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;

public class Merge
{
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("merge.txt"));
		int numClasses = Integer.parseInt(file.nextLine());
		Map<String, Integer> grades = new TreeMap<String, Integer>();	// Track grade counts
		for (int i = 0; i < numClasses * 4; i++)
		{
			String grade = file.next();
			int count = Integer.parseInt(file.next());
			// Check if grade is already in map, otherwise add it
			if (grades.containsKey(grade)) grades.put(grade, grades.get(grade) + count);
			else grades.put(grade, count);
		}
		// Print results
		for (Map.Entry<String, Integer> entry : grades.entrySet())
			System.out.println(entry.getKey() + ": " + entry.getValue());
	}
}
