package module6;

import java.util.InputMismatchException;
import java.util.Scanner;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 6 Assignment
 * Temperature Class
 */

//Handles the conversion from Fhrenheit to Celsius and Kelvin
public class Temperature {
    private double ftemp; //initializes ftemp which will hold the user's Fahrenheit temperature

    //This constructor stores fahrenheit into ftemp when object temperature is created
    public Temperature(double fahrenheit) {
        ftemp = fahrenheit; //stores fahrenheit in ftemp
    }

    //This setter method stored fahrenheit into stemp in order to be able to alter the temperature
    public void setFahrenheit(double fahrenheit) {
        ftemp = fahrenheit; //stores fahrenheit in ftemp
    }
    
    //Returns the value of the ftemp field in fahrenheit (no conversion)
    public double getFahrenheit() {
        return ftemp;
    }

    //Returns the value of the ftemp field in Celsius
    public double calcCelsius() {
        return (5.0 / 9.0) * (ftemp - 32.0); //Formula to convert from fahrenheit to celsius
    }

    //Returns the value of the ftemp field in Kelvin
    public double calcKelvin() {
        return ((5.0 / 9.0) * (ftemp - 32.0)) + 273.0; //Formula to convert from fahrenheit to kelvin
    }
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in); //Activates keyboard
        boolean validInput = false; //This boolean (defaulted to false) will be used to validate input
        double fahrenheit = 0; //Fahrenheit variable initialized
        
        //Input validation; will loop as long as input is invalid
        while (!validInput) 
    	    {
    	   		 System.out.println("Input Fahrenheit temperature: "); //Prompts user to input fahrenheit temperature
    	   		 try //Loops until valid input is inputted
    	   		 {
    	   			 fahrenheit = scnr.nextDouble(); //Reads input
    	   			 validInput = true; //It is valid is input is a number
    	   		 } catch (InputMismatchException e) //Catches error if input is not a number
    	   		 {
    	   			 System.out.println("Please input a number."); //Error message; prompts user to input temperature again
    	   			 scnr.next(); //Cleans input buffer
    	   		 }
    	    }
        
        Temperature temperature = new Temperature(fahrenheit); //Creates temperature object
        
        System.out.println("Your temperature in..."); //Presentation of results
        System.out.printf("Fahrenheit: %.2f F\n", temperature.getFahrenheit()); //Calls getFahrenheit method to display temperature in fahrenheit
        System.out.printf("Celsius: %.2f C\n", temperature.calcCelsius()); //Calls calcCelsius method to display temperature in celsius
        System.out.printf("Kelvin: %.2f K\n", temperature.calcKelvin()); //Calls calcKelvin method to display temperature in kelvin

    }
}
