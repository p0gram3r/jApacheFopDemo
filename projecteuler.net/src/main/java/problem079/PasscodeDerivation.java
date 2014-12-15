package problem;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PasscodeDerivation
{

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();

		List<String> keylog = parseKeylog();

		for (int i = 1; i < Integer.MAX_VALUE; i++)
		{
			String passcode = Integer.toString(i);
			boolean canGenerateAllTans = true;

			for (String tan : keylog)
			{
				canGenerateAllTans &= canGenerateTan(passcode, tan);
				if (!canGenerateAllTans)
				{
					break;
				}
			}

			if (canGenerateAllTans)
			{
				System.out.println("possible passcode: " + passcode);
				break;
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("duration in ms:" + (end - start));
		System.out.println("done");

	}



	static boolean canGenerateTan(String key, String passcode)
	{
		String remainingKey = key;
		char[] passcodeChars = passcode.toCharArray();

		for (int i = 0; i < passcodeChars.length; i++)
		{
			int index = remainingKey.indexOf(passcodeChars[i]);
			if (index == -1)
			{
				return false;
			}

			remainingKey = remainingKey.substring(index + 1);
		}

		return true;
	}



	private static List<String> parseKeylog()
	{
		InputStream inStream = PasscodeDerivation.class.getClassLoader().getResourceAsStream("problem79.txt");

		List<String> result = new LinkedList<String>();

		Scanner scanner = new Scanner(inStream);
		while (scanner.hasNext())
		{
			result.add(scanner.next());
		}

		return result;
	}

}
