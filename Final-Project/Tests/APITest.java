import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITest
{
    private static String AUTH = "BQCfx65WTiEhHzV2uxrUiT2X9G74PIpgUyAN-"
    		+ "LyGEKQYTaYft8Q_rho7ckYneXf_5XS1IXNXo5XAjYW06mrIU6CSjl9L"
    		+ "isfquFhOSBQGK6_AZEtQQfbnRwV9kREUAJ3CCTDLN5_G-PE36I3tJEG"
    		+ "du_taqiugkMJO7HOQrTq2U84P7aUssDkbhUy_odFx_vN2AWKJLFSYQw"
    		+ "den3SeWo5GWzy1baPa9ffoSKQ4ST7dAgbmO2aVt-xQAnWqg3HdYgvx";
    
	public static void main(String [] args) throws IOException, ParseException
	{
		String url_search = 
		"https://api.spotify.com/v1/search?"
		+ "q=track%3Abad%20guy%20"
		+ "artist%3Abillie%20eilish"
		+ "&type=track"
		+ "&market=US"
		+ "&limit=1";
		System.out.println(url_search);
		URL url = new URL(url_search);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + AUTH);
		conn.setRequestMethod("GET");
		

	    if (conn.getResponseCode() != 200)
	    {
	    	throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	    }
	    JSONParser parser = new JSONParser();
	    JSONObject input = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader((conn.getInputStream()))));
	    conn.disconnect();
	    JSONArray item = (JSONArray) ((JSONObject)input.get("tracks")).get("items");
	    String name = (String) ((JSONObject)((JSONObject)item.get(0)).get("album")).get("name");
	    System.out.println(name);
	    
	    /*BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();*/
	}
}
