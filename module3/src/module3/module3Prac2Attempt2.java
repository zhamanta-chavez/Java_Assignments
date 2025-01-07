package module3;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 3 Practice 2 Attempt 2
 * Generating ASCII CHARACTERS
 */

public class module3Prac2Attempt2 {

	public static void main(String[] args) 
	{
		for (int i = 0; i <= 127; i++)
		{
			System.out.print((char)i);
			
			if (i % 16 == 0)
			{
				System.out.println();
			}
		}

	}

}