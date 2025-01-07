package midterm;

/*Zhamanta 
 * ITSE 2317
 * Description: Midterm
 * Question 2
 */

import java.util.Scanner;

public class question2 {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in); //activates keyboard
		
		//variables
		double averageIndividual;
		double total = 0;
		double average;
		
		//loops prompt for user 5 times
		for (int i = 0; i < 5; i++)
		{
			System.out.println("Batting average for player " + (i + 1) + ":");
			averageIndividual = scnr.nextDouble();
			total += averageIndividual;
		}
		
		average = total / 5; //calculates average
		
		//displays average result
		System.out.printf("Average: " + "%.3f", average);
		System.out.println();
		
		//results calculation
		if (average >= .303)
		{
			System.out.println("Excellent Average");
		}
		else if (average >= .267)
		{
			System.out.println("Good Average");
		}
		else if (average >= .231)
		{
			System.out.println("Normal");
		}
		else if (average >= .195)
		{
			System.out.println("Below Average");
		}
		else 
		{
			System.out.println("Needs Improvement");
		}
	}

}
