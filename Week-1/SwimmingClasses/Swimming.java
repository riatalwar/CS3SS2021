import java.util.*;
import java.io.*;

public class Swimming
{
	public static void main (String [] args) throws IOException
	{
		Scanner scan = new Scanner(new File("swimming.txt"));	// Open file
		int numLines = Integer.parseInt(scan.nextLine());		// Get num lines
		// Store lines in an array
		String [] lines = new String[numLines];
		for (int i = 0; i < numLines; i++)
			lines[i] = scan.nextLine();
		Arrays.sort(lines); // This will cause both teachers and students to be alphabetized
		String teach = "";  // Track current teacher
		for (int i = 0; i < lines.length; i ++)
		{
			// If moving on to new teacher
			if (!lines[i].substring(0, lines[i].indexOf(" ")).equals(teach))
			{
				// Print teacher and store name
				teach = lines[i].substring(0, lines[i].indexOf(" "));
				System.out.println("\n" + teach);
			}
			// Print student
			System.out.println("   " + lines[i].substring(lines[i].indexOf(" ")));
		}
	}
}
