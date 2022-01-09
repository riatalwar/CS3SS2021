public class Student implements Comparable <Student>
{
	private String first;
	private String last;
	private int grade;
	public Student(String f, String l, int g)
	{
		first = f;
		last = l;
		grade = g;
	}
	
	// Accessor methods
	public String getName()
	{
		return first + " " + last;
	}
	public int getGrade()
	{
		return grade;
	}
	
	public int compareTo(Student stu)
	{
		if (grade > stu.getGrade()) return 1;
		if (grade < stu.getGrade()) return -1;
		return 0;
	}
	
	public String toString()
	{
		return last + ", " + first + " " + grade;
	}
}