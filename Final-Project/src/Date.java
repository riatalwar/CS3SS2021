/*
 * Name: Ria Talwar
 * Date: July 29, 2021
 * Description: This class creates a date and implements
 * Comparable to see if it is before or after another Date
 */

public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;
	private int hours;
	private int min;
	
	public Date(String d, String t)
	{
		// Constructor for spotify formatted dates
		String [] date = d.split("-");
		year = Integer.parseInt(date[0]);
		month = Integer.parseInt(date[1]);
		day = Integer.parseInt(date[2]);
		String [] time = t.split(":");
		hours = Integer.parseInt(time[0]);
		min = Integer.parseInt(time[1]);
		makeValid();
	}
	
	public Date(int y, int mo, int d, int h, int mi)
	{
		// Constructor for individual inputs
		year = y;
		month = mo;
		day = d;
		hours = h;
		min = mi;
		makeValid();
	}
	
	public Date(int y, int m, int d)
	{
		// Contructor that auto-sets time to 00:00
		year = y;
		month = m;
		day = d;
		hours = 0;
		min = 0;
		makeValid();
	}
	
	// Accessor methods
	public int getYear()
	{
		return year;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public int getMinutes()
	{
		return min;
	}
	
	
	public void toNextDay()
	{
		/*
		 * Description: Go to the next day
		 * 
		 * Parameters: void
		 * Return: void
		 */
		if (day == daysInMonth()) toNextMonth();
		day = day % daysInMonth() + 1;
	}
	
	public void toNextWeek()
	{
		/*
		 * Description: Go to the next week
		 * 
		 * Parameters: void
		 * Return: void
		 */
		if (day + 7 > daysInMonth()) toNextMonth();
		day = (day + 7) % daysInMonth();
		if (day == 0) day = daysInMonth();
	}
	
	public void toNextMonth()
	{
		/*
		 * Description: Go to the next month
		 * 
		 * Parameters: void
		 * Return: void
		 */
		if (month == 12) year++;
		month = month % 12 + 1;
	}
	
	// Comparison methods
	public int compareTo(Date d)
	{
		/*
		 * Description: Compare two dates
		 * 
		 * Parameters: Date to compare to
		 * Return: Integer this object is before, after, or the same as the other 
		 */
		if (year < d.getYear()) return -1;
		if (year > d.getYear()) return 1;
		if (month < d.getMonth()) return -1;
		if (month > d.getMonth()) return 1;
		if (day < d.getDay()) return -1;
		if (day > d.getDay()) return 1;
		return 0;
	}

	public boolean before(Date d)
	{
		/*
		 * Description: Check id a date comes before another
		 * 
		 * Parameters: Date to compare to
		 * Return: Boolean is before
		 */
		return compareTo(d) == -1;
	}
	
	public boolean after(Date d)
	{
		/*
		 * Description: Check if a date come after another
		 * 
		 * Parameters: Date to compare to
		 * Return: Boolean is after
		 */
		return compareTo(d) == 1;
	}
	
	
	public boolean isLeapYear()
	{
		/*
		 * Description: Determine whether or not a Date is a leap year
		 * 
		 * Parameters: void
		 * Return: Boolean is leap year
		 */
		if (year % 4 == 0 && year % 100 != 0) return true;
		if (year % 400 == 0) return true;
		return false;
	}
	
	
	public int daysInMonth()
	{
		/*
		 * Description: Get the number of days in the current month
		 * 
		 * Parameters: void
		 * Return: Integer number of days in month
		 */
		int [] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if (isLeapYear()) daysPerMonth[1] = 29;
		return daysPerMonth[month - 1];
	}
	
	
	public Date copy()
	{
		/*
		 * Description: Return a copy of the current date
		 * 
		 * Parameters: void
		 * Return: Date copy
		 */
		Date copy = new Date(year, month, day, hours, min);
		return copy;
	}
	
	
	public void makeValid()
	{
		/*
		 * Description: Make the current date valid if it is not already
		 * 
		 * Parameters: void
		 * Return: void
		 */
		if (month > 12) month = 12;
		if (month < 1) month = 1;
		if (day > daysInMonth()) day = daysInMonth();
		if (day < 1) day = 1;
		if (hours > 23) hours = 24;
		if (hours < 0) hours = 0;
		if (min > 59) min = 59;
		if (min < 0) min = 0;
	}
	
	
	public String toString()
	{
		// Returns formatted date
		return day + "-" + month + "-" + year;
	}
}
