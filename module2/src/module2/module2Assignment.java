package module2;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 2 Assignment
 * Math Tutor
 */

import java.util.Scanner;
import java.util.Random;

public class module2Assignment {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in); //activating keyboard
		Random randGen = new Random(); //activating randomness

		int num1; //first number of the operation
		int num2; // second number of the operation
		int answer = 0; //user's answer to the problem
		
		num1 = randGen.nextInt(500)+1; //randomly generates the first number of the operation
		num2 = randGen.nextInt(500)+1; //randomly generates the second number of the operation
		
		int operation = -1; //stores user's choice to do addition or subtraction
		
		while (operation == -1) //prompt will loop until a valid operation is picked
		{
			System.out.print("Would you like to Add (1) or Subtract (2) your numbers? ");
			operation = scnr.nextInt();
			
			if (operation == 1) //user picked addition
			{
				System.out.print("Please answer the following: ");
				System.out.print(num1 + " + " + num2 + " = "); //calculation
				answer = scnr.nextInt(); //stores user's answer
			}
			else if (operation == 2)
			{
				System.out.print("Please answer the following: "); 
				System.out.print(num1 + " - " + num2 + " = "); //calculation
				answer = scnr.nextInt(); //stores user's answer
			}
			else
			{
				System.out.println("Please choose 1 or 2."); //input validation
				operation = -1;
			}
		}
		
		int actualAnswer = 0; //will store correct answer
		
		if (operation == 1)
		{
			actualAnswer = num1 + num2; //performs addition if "1" is picked
		}
		else if (operation == 2)
		{
			actualAnswer = num1 - num2; //performs subtraction is "2" is picked
		}
		
		
		if (answer == actualAnswer)
		{
			System.out.println("Good job!"); //congratulates user if their answer matches the correct answer
		}
		else if (operation == 1 && actualAnswer != answer)
		{
			System.out.println("The sum is: " + actualAnswer + "."); //correction for addition
		}
		else if (operation == 2 && actualAnswer != answer)
		{
			System.out.println("The difference is: " + actualAnswer + "."); //correction for subtraction
		}
		
		
	}

}
