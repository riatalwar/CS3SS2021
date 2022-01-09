// Name: Ria Talwar
// Date: July 24, 2021
// Basic test to ensure json.simple functions

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LikedSongs
{
	public static void main(String [] args)
	{
		try
		{
			JSONParser parser = new JSONParser();
			
			JSONObject file = (JSONObject) parser.parse(new FileReader("MyData/YourLibrary.json"));
			
			JSONArray songs = (JSONArray) file.get("tracks");
			
			for (Object obj : songs)
			{
				JSONObject song = (JSONObject) obj;
				String artist = (String) song.get("artist");
				String album = (String) song.get("album");
				String track = (String) song.get("track");
				System.out.println(track + " by " + artist + " -- " + album);
			}
		}
		catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
	}
}
