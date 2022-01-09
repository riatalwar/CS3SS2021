import java.util.*;
import java.io.*;

public class Vowels
{
	public static void main (String [] args) throws IOException
	{
		// Read file and create array to store words
		Scanner scan = new Scanner(new File("word.dat"));
		int numWords = Integer.parseInt(scan.nextLine());
		Word [] words = new Word[numWords];
		// Fill array
		for (int i = 0; i < numWords; i++)
			words[i] = new Word(scan.nextLine());
		// Sort and print words
		Arrays.sort(words);
		for (int i = 0; i < numWords; i++)
			System.out.println(words[i].toString() + " ");
	}
}
