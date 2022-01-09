import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;

public class Exemption
{
	public static void main(String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("final.txt"));
		int num1 = Integer.parseInt(file.next());	// Get the number of lines with grades
		int num2 = Integer.parseInt(file.next());	// Get the number of lines with discipline data

		Set<String> exempt = new TreeSet<String>(); // Tracks students that are exempt from the final
		
		// Find students whose grades fulfill the requirement
		for (int i = 0; i < num1; i++)
		{
			String name = file.next();
			file.next();
			int grade = Integer.parseInt(file.next());
			if (grade >= 89) exempt.add(name);
		}
		
		// Remove students who have had discipline referrals
		for (int i = 0; i < num2; i++)
		{
			String name = file.next();
			file.nextLine();
			exempt.remove(name);
		}
		
		System.out.println(exempt);
	}
}
