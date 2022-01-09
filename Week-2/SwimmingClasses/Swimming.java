import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.IOException;
import java.io.File;

public class Swimming
{
	public static Map<String, String> getClasses(Scanner file)
	{
		Map<String, String> classes = new TreeMap<String, String>();
		int numLines = Integer.parseInt(file.nextLine());
		for (int i = 0; i < numLines; i++)
		{
			String teacher = file.next();
			String student = file.next();
			// Check if teacher is already in the map
			if (!classes.containsKey(teacher)) classes.put(teacher, "   " + student + "\n");
			else classes.put(teacher, classes.get(teacher) + "   " + student + "\n");
		}
		return classes;
	}
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("swimming.txt"));
		Map<String, String> classes = getClasses(file);
		// Print classes
		for (Map.Entry<String, String> entry : classes.entrySet())
		{
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
