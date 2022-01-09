/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class tests the class SpotifyMusicProcessor
 */

import java.io.IOException;
import org.json.simple.parser.ParseException;


public class SpotifyMusicProcessorTester
{
	public static void main(String [] args) throws IOException, ParseException
	{
		//String [] files = {"TestData0.json", "TestData1.json"};
		String [] files = {"StreamingHistory.json"};
		IMusicProcessor mp = new SpotifyMusicProcessor(files);
		testFindMostPlayed(mp);
		testCountHours(mp);
		testFirstLastDate(mp);
	}
	
	public static void testFindMostPlayed(IMusicProcessor mp)
	{
		System.out.println("Find Most Played (Artist)");
		System.out.println(mp.findMostPlayed(new Date(2021, 6, 19), new Date(2021, 7, 19), "artist"));	// Last month of data -- Machine Gun Kelly
		System.out.println(mp.findMostPlayed(new Date(2020, 6, 1), new Date(2020, 7, 1), "artist"));	// All data -- Lauv
		System.out.println("Find Most Played (Song)");
		System.out.println(mp.findMostPlayed(new Date(2021, 6, 19), new Date(2021, 7, 19), "track"));	// Last month of data -- my ex's best friend
		System.out.println(mp.findMostPlayed(new Date(2020, 6, 1), new Date(2020, 7, 1), "track"));		// All data -- f***, i'm lonely
		System.out.println("Find Most Played (Album)");
		System.out.println(mp.findMostPlayed(new Date(2021, 6, 19), new Date(2021, 7, 19), "album"));	// Last month of data -- Tickets To My Downfall
		System.out.println(mp.findMostPlayed(new Date(2020, 6, 1), new Date(2020, 7, 1), "album"));		// All data -- ~how i'm feeling~
	}
	
	public static void testCountHours(IMusicProcessor mp)
	{
		System.out.println("\nCount Hours");
		System.out.println(mp.countHours(new Date(2021, 6, 1, 0, 0), new Date(2021, 7, 1, 0, 0)));
	}
	
	public static void testAllData(IMusicProcessor mp)
	{
		System.out.println("\nAll Data");
		System.out.println(mp.getAllData(new Date(2021, 6, 1, 0, 0), new Date(2021, 7, 1, 0, 0)).toString());
	}

	public static void testFirstLastDate(IMusicProcessor mp)
	{
		System.out.println("\nFirst Last Date");
		System.out.println(mp.firstLastDate()[0].toString() + ", " + mp.firstLastDate()[1].toString()); // 19-7-2020, 19-7-2021
	}
}