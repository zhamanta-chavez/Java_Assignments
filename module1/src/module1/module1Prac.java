package module1;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 1 Practice
 * Sales Tax
 */

import java.util.*;

public class module1Prac 
{
	//Variables (both input and output)
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in); //Activating keyboard
		
		//Initializing variables
		double retail = 0;
		double taxRate = 0;
		double actualRate = 0;
		double taxOnly = 0;
		double total = 0;
		
		//User prompts
		System.out.println("Enter retail price: ");
		retail = kb.nextInt();
		
		System.out.println("Enter tax rate (ex: 7 for 7%): ");
		taxRate = kb.nextInt();
		
		//Calculations
		actualRate = taxRate * .01;
		taxOnly = actualRate * retail;
		total = taxOnly + retail;
		
		//Results
		System.out.println("Sales tax: " + taxOnly);
		System.out.println("Total: " + total);
		
	}

}
