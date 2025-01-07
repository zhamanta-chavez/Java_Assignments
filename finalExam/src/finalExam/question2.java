package finalExam;

/*Zhamanta 
 * ITSE 2317
 * Description: Final Exam
 * Question 2
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class question2 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in); //Activates keyboard
		
		boolean validInput = false; //Will check for valid input
		int[] userNumbers = new int[10]; //Array of user's 10 numbers
		
		while (!validInput) //Will loop as long as input is invalid
	    {
			for (int i = 0; i < 10; i++) //Will loop 10 times
			{
				System.out.println("Input 10 numbers: "); //Prompts user to input numbers
		   		 try 
		   		 {
		   			 userNumbers[i] = scnr.nextInt(); //Reads input and stores it in the user's array
		   			 validInput = true; //It is valid if input is a whole number
		   		 } catch (InputMismatchException e) //Catches error if input is not a number or if number inputted is a decimal
		   		 {
		   			 System.out.println("Please input whole numbers.  Try again:"); //Error message; prompts user to input number again
		   			 scnr.next(); //Cleans input buffer
		   			 i--; //Makes sure any corrected input is stored in the correct index
		   		 }
		    }
	    }
		
		//Results presentation
		System.out.println("Minimmum value: " + getMin(userNumbers)); //Calls getMin() to display the minimum
		System.out.println("Maximmum value: " + getMax(userNumbers)); //Calls getMax() to display the maximum
	}
	
	//This function will identify the minimum value in the user's array
	public static int getMin(int userArray[])
	{
		int theMin = 0; //Initializing a minimum value of 0
		
		//Will loop for the 10 numbers in the array
		for (int i = 0; i < 10; i++)
		{
			if (i == 0) 
			{
				theMin = userArray[i]; //For the first loop, it will set theMin to the first value of the array
			}
			else if (userArray[i] < theMin)
			{
				theMin = userArray[i]; //If the number in the array is smaller than the current minimum, the array value will become the new minimum
			}
		}
		
		return theMin; //Returns minimum value
	}
	
	//This function will identify the maximum value in the user's array
	public static int getMax(int userArray[])
	{
		int theMax = 0; //Initializing a maximum value of 0
		
		//Will loop for the 10 numbers in the array
		for (int i = 0; i < 10; i++)
		{
			if (i == 0)
			{
				theMax = userArray[i]; //For the first loop, it will set theMax to the first value of the array
			}
			else if (userArray[i] > theMax)
			{
				theMax = userArray[i]; //If the number in the array is bigger than the current maximum, the array value will become the new maximum
			}
		}
		
		return theMax; //Returns the maximum value
	}

}
