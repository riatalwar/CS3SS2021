// Name: Ria Talwar
// Date: July 20, 2021

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class AcroRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("acro.dat"));
		Acronyms acro = new Acronyms(file);
		while (file.hasNext())
			System.out.println(acro.convert(file.nextLine()));
	}
}