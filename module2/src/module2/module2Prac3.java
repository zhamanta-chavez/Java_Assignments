package module2;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 2 Practice 3
 * Areas of Rectangles
 */

import java.util.Scanner;

public class module2Prac3 {

	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		
		int width1;
		int width2;
		int length1;
		int length2;
		int area1;
		int area2;
		
		System.out.println("Enter width of first rectangle: ");
		width1 = scnr.nextInt();
		
		System.out.println("Enter length of first rectangle: ");
		length1 = scnr.nextInt();
		
		System.out.println("Enter width of second rectangle: ");
		width2 = scnr.nextInt();
		
		System.out.println("Enter length of second rectangle: ");
		length2 = scnr.nextInt();
		
		area1 = width1 * length1;
		area2 = width2 * length2;
		
		if (area1 > area2)
		{
			System.out.println("Rectangle 1 has a greater area.");
		}
		else if (area1 < area2)
		{
			System.out.println("Rectangle 2 has a greater area.");
		}
		else
		{
			System.out.println("The rectangles have the same area.");
		}
		

	}

}
