public class Person implements Comparable<Person>
{
	private String first;
	private String middle;
	private String last;
	
	public Person(String name)
	{
		int spc1 = name.indexOf(" ");
		int spc2 = name.lastIndexOf(" ");
		
		if (spc1 == spc2)
		{
			first = name.substring(0, spc1);
			last = name.substring(spc1 + 1);
		}
		else
		{
			first = name.substring(0, spc1);
			middle = name.substring(spc1 + 1, spc2);
			last = name.substring(spc2 + 1);
		}
	}
	
	public String getFirst()
	{
		return first;
	}
	
	public String getMiddle()
	{
		return middle;
	}
	
	public String getLast()
	{
		return last;
	}
	
	public int compareTo(Person p)
	{
		return (toString()).compareTo(p.toString());
	}
	
	public String toString()
	{
		if (middle != null) return last + ", " + first + " " + middle;
		return last + ", " + first;
	}
}
