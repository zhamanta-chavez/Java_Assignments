package midterm;

/*Zhamanta 
 * ITSE 2317
 * Description: Midterm
 * Question 1
 */

import java.util.Scanner;

public class question1 {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in); //activating keyboard
		
		//variables
		int dimes = 1;
		int days;
		double money;
		double moneyTotal = 0;
		
		//user prompt
		System.out.println("For how many days do you want to calculate your salary?: ");
		days = scnr.nextInt(); //stores number of days
		
		//loop for dimes
		for (int i = 0; i < days; i++)
		{
			money = dimes * .1; //converts number of dimes to dollars
			System.out.printf("Day " + (i + 1) + ": $" + "%.2f", money);
			System.out.println();
			dimes +=1;
			moneyTotal += money; //running total of money in dollars
		}
		
		//total results
		System.out.printf("Total: $" + "%.2f", moneyTotal);
	}

}
