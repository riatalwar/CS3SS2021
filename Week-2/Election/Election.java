import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.IOException;
import java.io.File;

public class Election
{
	public static String findWinner(Scanner file)
	{
		Map<String, Integer> voteCount = new TreeMap<String, Integer>();
		int numVotes = Integer.parseInt(file.nextLine());
		// Tally votes
		for (int i = 0; i < numVotes; i++)
		{
			String name = file.nextLine();
			if (!voteCount.containsKey(name)) voteCount.put(name, 1);
			else voteCount.put(name, voteCount.get(name) + 1);
		}
		// Find winner
		for (Map.Entry<String, Integer> entry : voteCount.entrySet())
			if (entry.getValue() > numVotes / 2.0) return entry.getKey();
		return "No winner. A special runoff election will be held.";
	}
	
	public static void main (String [] args) throws IOException
	{
		Scanner file = new Scanner(new File("election.txt"));
		int numElections = Integer.parseInt(file.nextLine());
		// Print winner for each election
		for (int i = 0; i < numElections; i++)
		{
			String winner = findWinner(file);
			System.out.println(winner);
		}
	}
}
