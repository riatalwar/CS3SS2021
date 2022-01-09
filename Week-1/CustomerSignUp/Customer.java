public class Customer
{
	private String first;
	private String last;
	private String password;
	public Customer(String f, String l, String p)
	{
		first = f;
		last = l;
		password = p;
	}
	
	public String getFirst()
	{
		return first;
	}
	public String getLast()
	{
		return last;
	}
	public String getPassword()
	{
		return password;
	}
}
