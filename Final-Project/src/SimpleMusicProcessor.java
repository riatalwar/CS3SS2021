/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class is essentially a dummy class
 * implementing MusicProcessor to test UserInterface
 */

public class SimpleMusicProcessor implements IMusicProcessor
{
	
	public SimpleMusicProcessor()
	{
		/*
		 * The constructor doesn't need to do anything.
		 * All methods return dummy values.
		 * The purpose of this class is to allow
		 * UserInterface to be tested independently
		 * of the SpotifyMusicProcessor class.
		 */
	}
	
	
	public double countHours(Date start, Date end)
	{
		/*
		 * Description: Count the number of hours of music played between two dates
		 * 
		 * Parameters: String date to begin at, String date to end at
		 * Return: Double number of hours listened
		 */
		return 0.0;
	}

	
	public String findMostPlayed(Date start, Date end, String type)
	{
		/*
		 * Description: Find the most played artist/song/album between two dates
		 * 
		 * Parameters: String date to begin at, String date to end at, String item to find
		 * Return: String most played song/artist/album
		 */
		if (type.equals("artist")) return "Billie Eilish";
		else if (type.equals("track")) return "Bad Guy";
		else if (type.equals("album")) return "WHEN WE ALL FALL ASLEEP, WHERE DO WE GO";
		else return "N/A";
	}
	
	
	public String [] getAllData(Date start, Date end)
	{
		/*
		 * Description: Get data for all four categories in a given time period
		 * 
		 * Parameters: Date starting point, Date end point
		 * Return: String [] most played artist, track, album, # hours listened
		 */
		String artist = findMostPlayed(start, end, "artist");
		String track = findMostPlayed(start, end, "track");
		String album = findMostPlayed(start, end, "album");
		String hours = "" + countHours(start, end);
		String [] data = {artist, track, album, hours};
		return data;
	}
	
	
	public Date [] firstLastDate()
	{
		/*
		 * Description: Find the first and last dates with data in the file
		 * 
		 * Parameters: Void
		 * Return: Date [] first, last
		 */
		Date first = new Date(1, 1, 1);
		Date last = new Date(1, 1, 31);
		Date [] firstLast = {first, last};
		return firstLast;
	}
}
