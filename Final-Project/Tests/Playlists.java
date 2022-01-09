// Name: Ria Talwar
// Date: July 24, 2021
// Print the names of all playlists

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Playlists
{
	public static void main(String [] args)
	{
		try
		{
			JSONParser parser = new JSONParser();
			
			JSONObject file = (JSONObject) parser.parse(new FileReader("MyData/Playlist1.json"));
			
			JSONArray playlists = (JSONArray) file.get("playlists");
			
			for (Object obj : playlists)
			{
				JSONObject play = (JSONObject) obj;
				System.out.println(play.get("name"));
			}
		}
		catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
	}
}
