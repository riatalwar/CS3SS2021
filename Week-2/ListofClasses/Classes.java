import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;

public class Classes
{
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("classes.txt"));
		Set<String> classes = new TreeSet<String>();
		while (file.hasNextLine())
		{
			String line = file.nextLine();
			classes.add(line.substring(line.indexOf(" ") + 1));
		}
		System.out.println(classes);
	}
}
