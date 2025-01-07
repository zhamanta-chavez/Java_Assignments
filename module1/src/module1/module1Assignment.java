package module1;

/*Zhamanta
 * ITSE 2317
 * Description: Stock Commission
 */

import java.util.*;

public class module1Assignment 
{

	public static void main(String[] args) 
	{
		//variables (input and output)
		Scanner kb = new Scanner(System.in); //activates keyboard
		
		//initializing prompt variables
		int numShares = 0;
		double sharePrice = 0;
		double percent = 0;
		int sharesSold = 0;
		double salePrice = 0;
		double sharePercent = 0;
		
		//initializing result variables
		double purchasePrice = 0;
		double purchaseCommission = 0;
		double purchaseTotal = 0;
		double sale = 0;
		double saleCommission = 0;
		double saleTotal = 0;
		double profit = 0;
		
		//printing explanation to user
		System.out.println("This program will ask you a few questions in order");
		System.out.println("to determine your profit.");
		System.out.println("");
		
		//prompting user to enter information
		System.out.print("How many shares did you buy?: "); //prompt
		numShares = kb.nextInt(); //assigning user input to variable
		System.out.println(); //blank line readability
		
		System.out.print("What was the purchase price of the share?: ");
		sharePrice = kb.nextInt();
		System.out.println();
		
		System.out.print("What percent commission did you pay? (For example, type .07 for 7%): ");
		percent = kb.nextDouble(); 
		System.out.println();
		
		System.out.print("How many shares did you sell?: ");
		sharesSold = kb.nextInt();
		System.out.println();
		
		System.out.print("What was the sale price of the shares?: ");
		salePrice = kb.nextInt();
		System.out.println();
		
		System.out.print("What percent commission did you pay to sell the shares? (For example, type .07 for 7%): ");
		sharePercent = kb.nextDouble();
		System.out.println();
		
		System.out.println("=============================================");
		System.out.println();
		
		//calculations
		purchasePrice = numShares * sharePrice;
		purchaseCommission = percent * purchasePrice;
		purchaseTotal = purchasePrice + purchaseCommission;
		sale = sharesSold * salePrice;
		saleCommission = sharePercent * sale;
		saleTotal = sale - saleCommission;
		profit = saleTotal - purchaseTotal;
		
		//formatted output
		System.out.printf("%-21s %-1s %-10.2f\n", "Purchase Price: ", "$", purchasePrice);
		System.out.printf("%-21s %-1s %-10.2f\n", "Purchase Commission: ", "$", purchaseCommission);
		System.out.printf("%-21s %-1s %-10.2f\n", "Purchase Total: ", "$", purchaseTotal);
		System.out.printf("%-21s %-1s %-10.2f\n", "Sale Price: ", "$", sale);
		System.out.printf("%-21s %-1s %-10.2f\n", "Sale Commission: ", "$", saleCommission);
		System.out.printf("%-21s %-1s %-10.2f\n", "Sale Total: ", "$", saleTotal);
		System.out.printf("%-21s %-1s %-10.2f\n", "Profit: ", "$", profit);
		
	
	}

}
