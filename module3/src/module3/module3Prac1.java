package module3;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 3 Practice 1
 * Running Total
 */

import java.util.Scanner;

public class module3Prac1 {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		
		int userInput;
		
		do 
		{
			System.out.println("Enter a positive integer: ");
			userInput = scnr.nextInt();
			
		} while (userInput < 0);
		
		int runningTotal = 0;
		
		for (int n = 0; n <= userInput; n++)
		{
			runningTotal = runningTotal + n;	
		}
		
		System.out.println("The sum is: " + runningTotal);
			
	}
}