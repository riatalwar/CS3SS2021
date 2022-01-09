/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class implements the MusicProcessor interface
 * so that it can analyze spotify listening history
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SpotifyMusicProcessor implements IMusicProcessor
{
	private ArrayList<Play> history;	
	
	public SpotifyMusicProcessor(String [] fileNames) throws IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		history = new ArrayList<Play>();
				
		// Copy all the data into an ArrayList
		for (String name : fileNames)
		{
			JSONArray file = (JSONArray) parser.parse(new FileReader(name));
			for (Object obj : file)
			{
				JSONObject play = (JSONObject) obj;
				String artist = (String) play.get("artistName");
				String track = (String) play.get("trackName");
				String album = (String) play.get("albumName");
				long length = (long) play.get("msPlayed");
				String dt = (String) play.get("endTime");
				String [] d = dt.split(" ");
				history.add(new Play(artist, track, album, length, new Date(d[0], d[1])));
			}
		}
	}
	
	public int findDate(Date d)
	{
		/*
		 * Description: Locates the index of the first occurence 
		 * of a specified date using binary search
		 * 
		 * Parameters: String date to locate
		 * Return: Integer index of date
		 */
		int iStart = 0;
		int iEnd = history.size() - 1;
		while (iStart <= iEnd)
		{
			int iMid = (iStart + iEnd) / 2;
			int comp = history.get(iMid).getDate().compareTo(d);
			if (comp == -1) iStart = iMid;
			else if (comp == 1) iEnd = iMid;
			else return findStartOfDay(iMid);
		}
		return -1;
	}
	
	
	public int findStartOfDay(int index)
	{
		/*
		 * Description: Find the first Play on the same day as the given Play
		 * 
		 * Parameters: Play on day to find beginning of
		 * Return: Integer index of beginning of date
		 */
		Date d = history.get(index).getDate();
		int i = index;
		while (i > 0 && history.get(i - 1).getDate().compareTo(d) == 0)
			i--;
		return i;
	}
	
	
	public boolean dateWithinRange(Date d)
	{
		/*
		 * Description: Determine whether or not a date
		 * is within the range of listening history
		 * 
		 * Parameters: String date to check
		 * Return: Boolean is within range
		 */
		Date [] firstLast = firstLastDate();
		return (d.after(firstLast[0]) && d.before(firstLast[1]));
	}
	
	
	public Date nextValidDate(Date d)
	{
		/*
		 * Description: Find the next date with data
		 * 
		 * Parameters: Date to find closest to
		 * Return: Date closest
		 */
		if (!dateWithinRange(d)) return firstLastDate()[0];
		Date closest = d;
		while (findDate(closest) == -1)
			closest.toNextDay();
		return d;
	}

	
	public double countHours(Date start, Date end)
	{
		/*
		 * Description: Count the number of hours of music played between two dates
		 * 
		 * Parameters: String date to begin at, String date to end at
		 * Return: Double number of hours listened
		 */
		
		// If start or end date is out of range change to beginning/end of history
		if (!dateWithinRange(start)) start = firstLastDate()[0];
		if (!dateWithinRange(end)) end = firstLastDate()[1];
		// If there is no data on start date find the next valid date
		if (findDate(start) == -1)
			start = nextValidDate(start);
		
		int i = findDate(start);		// Tracks location in history
		double hrs = 0;					// Tracks the total number of hours played
		while (i < history.size() && end.after(history.get(i).getDate()))
		{
			// While current play is before end date add the length and go to next index
			hrs += history.get(i).getLength() / 3600000.0;
			i++;
		}
		return hrs;
	}

	
	public String findMostPlayed(Date start, Date end, String type)
	{
		/*
		 * Description: Find the most played artist/song/album between two dates
		 * 
		 * Parameters: String date to begin at, String date to end at, String item to find
		 * Return: String most played song/artist/album
		 */
		
		// If start or end date is out of range change to beginning/end of history
		if (!dateWithinRange(start)) start = firstLastDate()[0];
		if (!dateWithinRange(end)) end = firstLastDate()[1];
		// If there is no data on start date find the next valid date
		if (findDate(start) == -1)
			start = nextValidDate(start);
				
		Map<String, Integer> tracker = new TreeMap<String, Integer>();	// Stores the number of times each key occurs
		int i = findDate(start);		// Tracks location in history
		while (i < history.size() && end.after(history.get(i).getDate()))
		{
			// Checks if key is already in the map and modifies the value
			if (type.equals("artist"))
			{
				if (tracker.containsKey(history.get(i).getArtist()))
					tracker.put(history.get(i).getArtist(), tracker.get(history.get(i).getArtist()) + 1);
				else
					tracker.put(history.get(i).getArtist(), 1);
			}
			else if (type.equals("track"))
			{
				if (tracker.containsKey(history.get(i).getTrack()))
					tracker.put(history.get(i).getTrack(), tracker.get(history.get(i).getTrack()) + 1);
				else
					tracker.put(history.get(i).getTrack(), 1);
			}
			else if (type.equals("album"))
			{
				// Filter out null albums
				if (tracker.containsKey(history.get(i).getAlbum()) && !history.get(i).getAlbum().equals("-"))
					tracker.put(history.get(i).getAlbum(), tracker.get(history.get(i).getAlbum()) + 1);
				else if (!history.get(i).getAlbum().equals("-"))
					tracker.put(history.get(i).getAlbum(), 1);
			}
			else return "-";	// Invalid type
			i++;
		}
		
		// Find the most played __
		String mostPlayed = "";		// Tracks most played
		int maxPlays = 0;			// Tracks the number of times the item was played
		for (Map.Entry<String, Integer> entry : tracker.entrySet())
		{
			if (entry.getValue() > maxPlays)
			{
				mostPlayed = entry.getKey();
				maxPlays = entry.getValue();
			}
		}
		return mostPlayed;
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
		double hrs = countHours(start, end);
		String hours = Math.round(hrs) + "";
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
		Date first = history.get(0).getDate();
		Date last = history.get(history.size() - 1).getDate();
		Date [] firstLast = {first, last};
		return firstLast;
	}
	
}
