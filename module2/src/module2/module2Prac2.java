package module2;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 2 Practice 2
 * Roman Numeral Converter
 */

import java.util.Scanner;

public class module2Prac2 {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		
		int number = -1;
		
		while (number < 1 || number > 10)
		{
			System.out.println("Enter a number from 1-10: ");
			number = scnr.nextInt();
			
			if (number < 1 || number > 10)
			{
				System.out.println("Number must be from 1-10.  Please try again.");
			}
		}
		
		
		switch(number)
		{
			case 1:
				System.out.println("I");
				break;
			case 2:
				System.out.println("II");
				break;
			case 3:
				System.out.println("III");
				break;
			case 4:
				System.out.println("IV");
				break;
			case 5:
				System.out.println("V");
				break;
			case 6:
				System.out.println("VI");
				break;
			case 7:
				System.out.println("VII");
				break;
			case 8:
				System.out.println("VIII");
				break;
			case 9:
				System.out.println("IX");
				break;
			case 10:
				System.out.println("X");
				break;	
		}

	}

}
