package finalPractice;

import java.util.Scanner;

public class driverExamPractice {
	
	char[] correctAnswers = new char[] {'B', 'D', 'A', 'A', 'C'};
	
	public int numWrong(char[] student)
	{
		int numIncorrect = 0;
		for (int i = 0; i < 5; i++)
		{
			if (correctAnswers[i] != student[i])
			{
				numIncorrect++;
			}
		}
		
		return numIncorrect;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		char[] studentAnswers = new char[5];
		
		for (int i = 0; i < 5; i++)
		{
			System.out.println("Enter your answer:");
			String input = scnr.nextLine();
			studentAnswers[i] = input.charAt(0);
		}

		driverExamPractice exam = new driverExamPractice();
		
		int numBad = exam.numWrong(studentAnswers);
		
		System.out.print(numBad);
		
	}

}
