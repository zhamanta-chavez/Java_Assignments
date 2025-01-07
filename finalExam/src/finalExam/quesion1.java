package finalExam;

/*Zhamanta 
 * ITSE 2317
 * Description: Final Exam
 * Question 1
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class quesion1 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in); //Activate keyboard
		
		int userNum = 0; //Will hold user's number
		boolean validInput = false; //Will be used for inpur validation
		
		System.out.println("This program will let you know if your number is prime."); //Explains purpose of program to user
		System.out.println("Please enter a number: "); //Prompts user to input a number
		
		while (!validInput) //Loops as long as validInput is false
	    {
	   		 try //Loops until valid input is inputted
	   		 {
	   			 userNum = scnr.nextInt(); //Reads input
	   			 if (userNum > 0)
	   			 {
	   				validInput = true; //It is valid only if input is a positive number
	   			 }
	   			 else
	   			 {
	   				 System.out.println("Make sure your input is a positive.  Try again:"); //Error message; prompts user to input number again
	   			 }
	   			
	   		 }
	   		 catch (InputMismatchException e) //Catches error if input is a decimal
	   		 {
	   			 System.out.println("Make sure your input is a whole number.  Try again:"); //Error message; prompts user to input number again
	   			 scnr.next(); //Cleans input buffer
	   		 }
	    }
		
		System.out.println();
		
		boolean result = (isPrime(userNum)); //Stores true or false in result
		System.out.println(isPrime(userNum)); //Calls isPrime for the final result
		
		//Results presentation
		if (result == true)
		{
			System.out.println("Your number is prime."); //Prints message for prime number
		}
		else
		{
			System.out.println("Your number is not prime."); //Prints message for a number that is not prime
		}
	}

	//This method checks if the user's number is prime or not
	public static boolean isPrime(int number)
	{	
		if (number <= 1)
		{
			return false; //0 and 1 are not prime
		}
		for (int i = 2; i <= Math.sqrt(number); i++) //Checks divisibility of user's number up until its square root
		{
			if (number % i == 0)
			{
				return false; //If it is divisible by any of the i values, the number is not prime
			}	
		}
		
		return true; //Otherwise, the number is prime
	}
}
