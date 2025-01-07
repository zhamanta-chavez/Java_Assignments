package midterm;

/*Zhamanta 
 * ITSE 2317
 * Description: Midterm
 * Question 3
 */

import java.util.Scanner;

public class question3 {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in); //activates keyboard
		
		//variables
		int numYears = -1;
		double individualBill = -1;
		double billTotal = 0;
		int i;
		int j;
		double totalAllYears = 0;
		double average;
		
		//prompts user to enter number of years and performs input validation
		while (numYears < 1)
		{
			System.out.println("For how many years do you want to calculate bills?: ");
			numYears = scnr.nextInt();
			if (numYears < 1)
			{
				System.out.println("Number of years must be greater than 0.");
			}
		}
		
		double [] billsPerYear = new double[numYears]; //array for bills total per year
		
		for (i = 0; i < numYears; i++) //calculates bills per years
		{
			System.out.println("Year " + (i + 1));
			for (j = 0; j < 12; j++) //calculates bills per month
			{
				while (individualBill < 0)
				{
					System.out.println("Water bill for month " + (j + 1) + ": ");
					individualBill = scnr.nextDouble();
					if (individualBill < 0) //input validation
					{
						System.out.println("Bill must be 0 or greater.");
					}
				}
				billTotal += individualBill; //running total of year i
				individualBill = -1;
			}
			billsPerYear[i] = billTotal; //assigns year i with total
			billTotal = 0; //resets bill total for the next year
		}
		
		System.out.println("Number of months: " + (numYears * 12)); //calculates number of months
		
		//calculates bill total for all years
		for (i = 0; i < numYears; i++)
		{
			System.out.println("Year " + (i + 1) + " total: " + billsPerYear[i]);
			totalAllYears += billsPerYear[i];
		}
		
		average = totalAllYears / numYears; //calculates average of all years
		
		System.out.printf("Average water bill: " + "%.2f", average); //outputs average
	}

}
