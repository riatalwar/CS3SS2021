import java.util.*;
import java.io.*;

public class SortGrade
{
	public static void main (String [] args) throws IOException
	{
		// Read file and create array to store students
		Scanner scan = new Scanner(new File("studentGrades.txt"));
		int numStudents = Integer.parseInt(scan.nextLine());
		Student [] classroom = new Student[numStudents];
		// Fill array
		for (int i = 0; i < numStudents; i++)
		{
			String [] arr = scan.nextLine().split(" ");
			classroom[i] = new Student(arr[0], arr[1], Integer.parseInt(arr[2]));
		}
		// Sort and print students
		Arrays.sort(classroom);
		for (int i = 0; i < numStudents; i++)
			System.out.println(classroom[i].toString());
	}
}