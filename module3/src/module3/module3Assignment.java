package module3;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 3 Assignment
 * Random Number Guessing Game
 */

import java.util.Scanner;
import java.util.Random;

public class module3Assignment {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in); //activates keyboard
		Random randGen = new Random(); //activates randomness
		
		int guess; //will store user's guess
		int randomNum; //will store random number
		
		randomNum = randGen.nextInt(100) + 1; //assigns a random number from 1-100 to randomNum
		
		System.out.println("Guess the Random Number (1-100): "); //prompts user to guess the number
		
		do //will loop while user's guess is incorrect
		{
			guess = scnr.nextInt(); //gets user's guess
			
			if (guess < 1 || guess > 100) //input validation
			{
				System.out.println("Make sure your guess is from 1 through 100.");
			}
			else if (guess < randomNum) //tells user their guess is too low if it is
			{
				System.out.println("Your number " + guess + " is too low.  Try again:");
			}
			else if (guess > randomNum) //tells user their guess is too high if it is
			{
				System.out.println("Your number " + guess + " is too high.  Try again:");
			}
		} while (guess != randomNum);
		
		if (guess == randomNum) //prints congratulations message if user's guess is correct
		{
			System.out.println("You Guessed the Random Number!");
		}
			
			
			
			
	}
}