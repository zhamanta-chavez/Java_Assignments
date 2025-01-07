package finalPractice;

/*Zhamanta 
 * ITSE 2317
 * Description: Final Practice
 * Paint Compnay Practice
 */

import java.util.Scanner;

public class paintCompanyPractice {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		int numRooms;
		double pricePaint;
		double totalFootage = 0.0;
		
		System.out.println("Please enter the number of rooms to be painted?: ");
		numRooms = scnr.nextInt();
		
		System.out.println("What is the price of your favorite gallon of paint?: ");
		pricePaint = scnr.nextDouble();
		
		for (int i = 0; i < numRooms; i++)
		{
			System.out.println("What is the square footage of wall space in room " + i + "?:");
			totalFootage += scnr.nextDouble();
		}
		
		double paintTotal = paintCost(paintGallons(totalFootage), pricePaint);
		int workTotal = laborCharges(laborHours(paintGallons(totalFootage)));
		
		System.out.println("Number of gallons required: " + paintGallons(totalFootage) + " gallons.");
		System.out.println("Hours of labor: " + laborHours(paintGallons(totalFootage)) + " hours.");
		System.out.println("Cost of paint: $" + paintCost(paintGallons(totalFootage), pricePaint));
		System.out.println("Labor charges: $" + laborCharges(laborHours(paintGallons(totalFootage))));
		System.out.println("Total: $" + (paintTotal + workTotal));
	}
	
	public static int paintGallons(double footage)
	{
		double numGallons;
		numGallons = footage / 100;
		int numGallonsRequired = (int) Math.ceil(numGallons);
		return numGallonsRequired;
	}
	
	public static int laborHours(int gallons)
	{
		int numHours;
		numHours = gallons * 8;
		
		return numHours;
	}
	
	public static double paintCost(int gallons, double paintPrice)
	{
		double paintCost;
		paintCost = gallons * paintPrice;
		
		return paintCost;
	}
	
	public static int laborCharges(int hours)
	{
		int laborCost;
		laborCost = hours * 20;
		
		return laborCost;
	}
	
	public static int totalCost(int cost1, int cost2)
	{
		int totalCharges;
		totalCharges = cost1 + cost2;
		
		return totalCharges;
	}

}
