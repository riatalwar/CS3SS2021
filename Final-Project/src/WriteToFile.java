/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class creates a new file using
 * the spotify data and includes album data as well
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WriteToFile
{
	@SuppressWarnings("unchecked")
	public static void main (String [] args) throws IOException, ParseException
	{
		// Open original spotify files and create new to store new data
		String [] fileNames = {"MyData/StreamingHistory0.json", "MyData/StreamingHistory1.json", "MyData/StreamingHistory2.json"};
		JSONParser parser = new JSONParser();
		FileWriter newFile = new FileWriter("StreamingHistory.json");
		
		SpotifyQuery sq = new SpotifyQuery();	// Use to query spotify API
				
		// Store all the data in the new file
		for (String name : fileNames)
		{
			JSONArray file = (JSONArray) parser.parse(new FileReader(name));
			for (Object obj : file)
			{
				JSONObject play = (JSONObject) obj;	
				String album;
				try
				{
					album = sq.getAlbum((String) play.get("trackName"), (String) play.get("artistName"));
				}
				catch (IndexOutOfBoundsException e)
				{
					album = "-";		// If query has no results
				}
				// Store album in file
				play.put("albumName", album);
				newFile.write(play.toJSONString() + ",\n");
			}
		}
		// Close files
		newFile.close();
		sq.close();
	}
}
