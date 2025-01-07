package module5;

/*Zhamanta 
 * ITSE 2317
 * Description: Module 5 Assignment
 * Driver's License Exam
 */

import java.util.Scanner;

public class DriverExam 
{
    static Scanner scnr = new Scanner(System.in);
    
    static char[] correctAnswers = {
            'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D',
            'B', 'C', 'D', 'A', 'D', 'C', 'C', 'B', 'D', 'A'
        };

    public static void main(String[] args) 
    {
        char[] studentAnswers = Exam(); // Get student answers
        
        int correctCount = totalCorrect(studentAnswers); // Get total correct
        int incorrectCount = totalIncorrect(studentAnswers); // Get total incorrect
        
        System.out.println();
        System.out.println("Total correct answers: " + correctCount);
        System.out.println("Total incorrect answers: " + incorrectCount);
        
        System.out.println();
        // Call passed() method and check if the student passed
        if (passed(correctCount)) 
        {
            System.out.println("The student passed the exam.");
        } 
        else 
        {
            System.out.println("The student failed the exam.");
        }
        
        System.out.println();
     // Call the method to print the questions missed
        questionsMissed(studentAnswers);
    }

    //This method collects student answers
    public static char[] Exam() 
    {
        char[] studentAnswers = new char[20];
        System.out.println("Please enter your answers for your Driver's License exam (only A, B, C, or D are allowed): ");
        
        for (int i = 0; i < 20; i++) 
        {
            char answer;
            boolean isValid = false;
            
            // Keep looping until valid answer is collected
            while (!isValid) 
            {
                System.out.print("Enter answer for question " + (i + 1) + ": ");
                answer = scnr.next().charAt(0);
                answer = Character.toUpperCase(answer);  // Convert to uppercase 
                
                // Input validation
                if (answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D') 
                {
                    studentAnswers[i] = answer;
                    isValid = true;
                } 
                else 
                {
                    System.out.println("Please enter only A, B, C, or D.");
                }
            }
        }
        
        return studentAnswers;
    }

    // This method calculates the total number of correct answers
    public static int totalCorrect(char[] studentAnswers) 
    {
        int totalCorrect = 0;

        for (int i = 0; i < 20; i++) 
        {
            if (studentAnswers[i] == correctAnswers[i]) 
            {
                totalCorrect++;
            }
        }

        return totalCorrect; 
    }

    // This method calculates the total number of incorrect answers
    public static int totalIncorrect(char[] studentAnswers) 
    {
        int totalIncorrect = 0;

        for (int i = 0; i < 20; i++) 
        {
            if (studentAnswers[i] != correctAnswers[i]) 
            {
                totalIncorrect++;
            }
        }

        return totalIncorrect; 
    }

    // This method checks if the student passed
    public static boolean passed(int totalCorrect) 
    {
        // A student passes if they have 15 or more correct answers
        return totalCorrect >= 15; 
    }
    
 // This method collects the question numbers of incorrect questions
    public static void questionsMissed(char[] studentAnswers) 
    {
        System.out.print("Questions missed: ");
        
        for (int i = 0; i < 20; i++) 
        {
            if (studentAnswers[i] != correctAnswers[i]) 
            {
                // Print the question number that the student missed
                System.out.print((i + 1) + " ");
            }
        }
        
        System.out.println(); 
    }

}

	

