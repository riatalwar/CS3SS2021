/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class stores the details of a single spotify play
 */

public class Play
{
	private String artist;
	private String track;
	private String album;
	private long lengthMS;
	private Date date;
	
	public Play(String a, String tr, String al, long l, Date d)
	{
		artist = a;
		track = tr;
		album = al;
		lengthMS = l;
		date = d;
	}
	
	// Accessor methods
	public String getArtist()
	{
		return artist;
	}
	
	public String getTrack()
	{
		return track;
	}
	
	public String getAlbum()
	{
		return album;
	}
	
	public long getLength()
	{
		return lengthMS;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String toString()
	{
		// Format Play as a String
		return artist + track + date.toString();
	}
}
