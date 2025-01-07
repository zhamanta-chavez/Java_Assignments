import java.util.ArrayList;  
import java.util.Scanner;    


/* Zhamanta 
 * INEW 2338
 * Description: Midterm
 * Counting Characters
 */

//Custom exception for when user does not enter a letter for userChoice
class InvalidInputException extends Exception 
{
    public InvalidInputException(String message) 
    {
        super(message); //Will display error message
    }
}


public class Midterm 
{

    public static void main(String[] args) 
    {
        Scanner scnr = new Scanner(System.in); //Activates input buffer
        char userInput = 'a'; //userInput will store the character the user wants to add to their array
        char userChoice; //Will store the letter the user wants to count
        ArrayList<Character> userCharacters = new ArrayList<>(); //Will store user's letters
        
        //Explanation of program
        System.out.println("This program will determine how many times the character of your choice appeared in your array.");
        System.out.println();
        
        //Prompts user to input characters
        System.out.println("Please input lower-case letters.  Input a capital letter/number/special character to stop inputting letters.");
        
        //Will loop as long as userInput is lower case
        while (Character.isLowerCase(userInput)) 
        {
            userInput = scnr.next().charAt(0);  //Gets user input
            if (Character.isLowerCase(userInput)) 
            {
                userCharacters.add(userInput);  //Adds userInput to userCharacters Array if lower case
            }
        }

        while (true) 
        {
            try {
                System.out.println("What character do you want to check for?"); //Asks user what character they want to count
                userChoice = scnr.next().charAt(0);  //Stores the letter the user wants to count

                //Checks is userChoice is a letter
                if (!Character.isLetter(userChoice)) 
                {
                    throw new InvalidInputException("Input must be a letter!");  //Throw custom exception
                }

                break; //Breaks if userChoice is a letter
            } catch (InvalidInputException e) 
            {
                System.out.println(e.getMessage()); //Catches error and asks for input again
            }
        }
        
        int count = countChar(userCharacters, userChoice, 0); //Calls countChar to count the user's chosen letter
        
        //Results message
        if (count == 1)
        {
        	System.out.println("'" + userChoice + "' appeared " + count + " time."); 
        }
        else
        {
        	System.out.println("'" + userChoice + "' appeared " + count + " times."); 
        }
        
    }

    //This method will count how many times the user's choice appeared in their array
    public static int countChar(ArrayList<Character> userCharacters, char userChoice, int index) 
    {
    	int count = 0; //count is 0 at first
    	
        //This is the base case (if index is out of bounds of userCharacters' size)
        if (index >= userCharacters.size()) 
        {
            return 0;
        }

       
        if (userCharacters.get(index) == userChoice) 
        {
            count = 1; //Count is 1 if the letter in the index matches the userChoice
        } else 
        {
            count = 0;  //Count is 0  if the letter int the index does not match the userChoice
        }
        
        //Running total
        count += countChar(userCharacters, userChoice, index + 1); //This recursive passed index + 1 so that it will move on to the next letter in userCharacters 

        return count; //Returns count
    }
}

