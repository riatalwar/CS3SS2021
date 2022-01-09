import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;

public class EvenOdd
{
	public static void getEvenOdds(String line)
	{
		System.out.println("EVENS: " + getNums(new Scanner(line), 0));
		System.out.println("ODDS: " + getNums(new Scanner(line), 1));
		System.out.println();
	}
	
	public static Set<Integer> getNums(Scanner line, int x)
	{
		Set<Integer> list = new TreeSet<Integer>();
		while (line.hasNext())
		{
			int n = Integer.parseInt(line.next());
			if (n % 2 == x) list.add(n);
		}
		return list;
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("oddeven.dat"));
		while (file.hasNext())
			getEvenOdds(file.nextLine());			
	}
}
