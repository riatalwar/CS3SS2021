
public class Word implements Comparable <Word>
{
	private String word;
	public Word(String w)
	{
		word = w;
	}
	public int getVowelCount()
	{
		int count = 0;
		for (int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			if (c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i') count++;
		}
		return count;
	}
	public String toString()
	{
		return word;
	}
	public int compareTo(Word w)
	{
		if (getVowelCount() > w.getVowelCount()) return 1;
		if (getVowelCount() < w.getVowelCount()) return -1;
		return 0;
	}
}
