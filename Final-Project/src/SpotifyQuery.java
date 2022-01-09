/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class queries the spotify API
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SpotifyQuery
{
	private static String AUTH = "[SPOTIFY API AUTHORIZATION CODE]";
	private Map<String, String> albums;
	private FileWriter newFile;
	
	public SpotifyQuery() throws IOException, ParseException
	{
		// Places to store keys -- map for easy access and file for long-term storage
		albums = new TreeMap<String, String>();
		newFile = new FileWriter("AlbumKeys2.json");
		
		// Load existing key, album pairs
		JSONParser parser = new JSONParser();
		JSONArray file = (JSONArray) parser.parse(new FileReader("AlbumKeys1.json"));
		for (Object obj : file)
		{
			JSONObject pair = (JSONObject) obj;
			albums.put((String) pair.get("key"), (String) pair.get("album"));
			newFile.write(pair.toJSONString() + ",\n");
		}
	}
	
	public String getAlbum(String track, String artist) throws IOException, ParseException
	{
		// If album is already stored don't query for anything
		if (albums.containsKey(track + artist)) return albums.get(track + artist);
		
		// Format search query
		String trackKey = track.replace(" ", "%20");
		String artistKey = artist.replace(" ", "%20");
		String url_search = "https://api.spotify.com/v1/search?"
		+ "q=track%3A" + trackKey + "%20artist%3A" + artistKey
		+ "&type=track&market=US&limit=1";
		
		// Open connection and set properties
		URL url = new URL(url_search);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + AUTH);
		conn.setRequestMethod("GET");
		
		// If error occurs return - for album
	    if (conn.getResponseCode() != 200)
	    {
	    	return "-";
	    }
	    
	    // Get album and add to map
	    JSONParser parser = new JSONParser();
	    JSONObject input = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader((conn.getInputStream()))));
	    conn.disconnect();
	    JSONArray item = (JSONArray) ((JSONObject)input.get("tracks")).get("items");
	    String name = (String) ((JSONObject)((JSONObject)item.get(0)).get("album")).get("name");
	    albums.put(track + artist, name);
	    
	    // Write key and album to the file
	    JSONObject keyAlbum = new JSONObject();
	    keyAlbum.put("key", track + artist);
	    keyAlbum.put("album", name);
	    newFile.write(keyAlbum.toJSONString() + ",\n");
	    
	    return name;
	}
	
	public void close() throws IOException
	{
		newFile.close();
	}
}
