package module3;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 3 Practice 2
 * Generating ASCII
 */

import java.util.Scanner;

public class module3Prac2 {

	public static void main(String[] args) 
	{
		for (int n = 0; n <= 70; n++)
		{
			int remainder;
			int original;
			original = n;
			
			int[] array1 = new int[6];
			int index = 0;
			
			do
			{
				n = n / 2;
				remainder = n % 2;
				
				if (n > 0)
				{
					array1[index] = remainder;
					index++;
				}
				else if (n == 0)
				{
					n = -1;
				}
				
			} while(n >= 0);
			
			System.out.print(array1[5]);
			System.out.print(array1[4]);
			System.out.print(array1[3]);
			System.out.print(array1[2]);
			System.out.print(array1[1]);
			System.out.print(array1[0]);
					
			n = original;
			System.out.print(n % 2);
			
			System.out.println();
			
			
			
		}
			
	}
}