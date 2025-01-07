/*Zhamanta 
 * INEW 2338
 * Description: Final Exam Question 3
 * Generics
 */

public class Generics 

{
    //Generic method that finds the smallest value
    public static <T extends Comparable<T>> T smallest(T x, T y) 
    {
        return (x.compareTo(y) < 0) ? x : y;
    }

    //Generic method that finds the largest value
    public static <T extends Comparable<T>> T largest(T x, T y) 
    {
        return (x.compareTo(y) > 0) ? x : y;
    }

    public static void main(String[] args) 
    {
        //Results for integers
        int int1 = 8, int2 = 9;
        System.out.println("Smallest of " + int1 + " and " + int2 + " is: " + smallest(int1, int2));
        System.out.println("Largest of " + int1 + " and " + int2 + " is: " + largest(int1, int2));

        //Results for doubles
        double double1 = 12.2, double2 = 11.1;
        System.out.println("Smallest of " + double1 + " and " + double2 + " is: " + smallest(double1, double2));
        System.out.println("Largest of " + double1 + " and " + double2 + " is: " + largest(double1, double2));

        //Results for strings
        String str1 = "Zhamanta", str2 = "Helen";
        System.out.println("Smallest of '" + str1 + "' and '" + str2 + "' is: " + smallest(str1, str2));
        System.out.println("Largest of '" + str1 + "' and '" + str2 + "' is: " + largest(str1, str2));
    }
}
