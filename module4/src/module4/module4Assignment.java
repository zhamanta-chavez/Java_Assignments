package module4;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 4 Assignment
 * Paint Company
 */

import java.util.Scanner;

public class module4Assignment {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		
		//initialization of variables
		int rooms;
		double price;
		double feet = 0;
		
		//Prompts for user
		System.out.println("Please enter the number of rooms to be painted: ");
		rooms = scnr.nextInt();
		
		System.out.println("What is the price of your favorite fallon of paint?: ");
		price = scnr.nextDouble();
		
		for (int i = 0; i < rooms; i++) //asks for the square feet in each room
		{
			double roomFeet;
			System.out.println("What is the square footage of wall space in room " + (i + 1) + "?: ");
			roomFeet = scnr.nextDouble();
			
			feet += roomFeet; //running total of feet
		}
		
		//Results
		System.out.println("Number of gallons required: " + gallons(feet) + " gallons");
		System.out.println("Hours of labor: " + hours(feet) + " hours");
		System.out.println("Cost of paint: $" + cost(price, feet));
		System.out.println("Labor charges: $" + charges(feet));
		System.out.println("Total for the paint job: $" + total(feet, price, feet));
	}
	
	//this method calculates amount of gallons of paint required
	public static double gallons(double x)
	{
		double gallonsRequired;
		
		gallonsRequired = x / 100;
		
		return gallonsRequired;
	}
	
	//this method calculates hours of labor required
	public static double hours(double x)
	{
		double hoursRequired;
		
		hoursRequired = (x / 100) * 8; 
		
		return hoursRequired;
	}
	
	//this method calculates the cost of paint
	public static double cost(double z, double x)
	{
		double costOfPaint;
		
		costOfPaint = z * gallons(x); //calls the gallons method to multiply what it returns by the cost per gallon
		
		return costOfPaint;
	}
	
	//this method calculates the labor charges
	public static double charges(double x)
	{
		double laborCharges;
		
		laborCharges = hours(x) * 20; //calls the hours method to multiply what it returns by the cost per hour of labor
		
		return laborCharges;
	}
	
	//this method calculates the total cost of the paint job
	public static double total(double x, double y, double z)
	{
		double jobTotal;
		
		jobTotal = cost(x, y) + charges(z); //calls the cost method and charges method to add what they return and calculates the total
		
		return jobTotal;
	}

}
