// Name: Ria Talwar
// Date: July 24, 2021
// Print the names of all playlists

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BillieEilish
{
	public static void main(String [] args)
	{
		try
		{
			JSONParser parser = new JSONParser();
			
			JSONObject file = (JSONObject) parser.parse(new FileReader("MyData/Playlist1.json"));
			
			JSONArray playlists = (JSONArray) file.get("playlists");
			
			JSONObject random = (JSONObject) playlists.get(0);
			JSONArray randomSongs = (JSONArray) random.get("items");
			
			for (Object obj : randomSongs)
			{
				JSONObject item = (JSONObject) obj;
				JSONObject song = (JSONObject) item.get("track");
				if (song.get("artistName").toString().equals("Billie Eilish")) System.out.println(song.get("trackName") + " : " + song.get("albumName"));
			}
		}
		catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
	}
}
