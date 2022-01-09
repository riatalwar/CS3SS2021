import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class SortPeople
{
	public static void main(String[] args) throws IOException
	{
		Scanner file = new Scanner(new File("people.txt"));
		int numLines = Integer.parseInt(file.nextLine());
		Person [] arr = new Person[numLines];
		for (int i = 0; i < numLines; i++)
			arr[i] = new Person(file.nextLine());
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}
}
