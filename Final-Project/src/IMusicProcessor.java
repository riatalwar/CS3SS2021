/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This interface outlines the methods required in ListeningHistory
 */

public interface IMusicProcessor
{	
	/*
	 * Description: Count the number of hours of music played between two dates
	 * 
	 * Parameters: Date starting point, Date end point
	 * Return: Double number of hours listened
	 */
	public double countHours(Date start, Date end);
	
	/*
	 * Description: Find the most played artist/song/album between two dates
	 * 
	 * Parameters: Date starting point, Date end point, String item to find
	 * Return: String most played song/artist/album
	 */
	public String findMostPlayed(Date start, Date end, String type);
	
	/*
	 * Description: Get data for all four categories in a given time period
	 * 
	 * Parameters: Date starting point, Date end point
	 * Return: String [] most played artist, track, album, # hours listened
	 */
	public String [] getAllData(Date start, Date end);
	
	/*
	 * Description: Find the first and last dates with data in the file
	 * 
	 * Parameters: Void
	 * Return: Date [] first, last
	 */
	public Date [] firstLastDate();
}
