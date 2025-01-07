package module2;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 2 Practice 1
 * Maximum/Minimum
 */

import java.util.Scanner;

public class module2Prac1 {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		
		int num1;
		int num2;
		
		System.out.println("Enter number 1: ");
		num1 = scnr.nextInt();
		
		System.out.println("Enter number 2: ");
		num2 = scnr.nextInt();
		
		if (num1 > num2)
		{
			System.out.println("Number 1 is bigger.");
		}
		
		else 
		{
			System.out.println("Number 2 is bigger.");
		}

	}

}
