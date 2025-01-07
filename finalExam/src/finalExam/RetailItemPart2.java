package finalExam;

/*Zhamanta 
 * ITSE 2317
 * Description: Final Exam
 * Question 3 Part 2
 */

public class RetailItemPart2 {

	public static void main(String[] args) {
		RetailItem item1 = new RetailItem("Jacket", 12, 59.95); //Creates item 1
		RetailItem item2 = new RetailItem("Designer Jeans", 40, 34.95); //Creates item 2
		RetailItem item3 = new RetailItem("Shirt", 20, 24.95); //Creates item 3
		
		//Proof that items have been stored
		
		//Item 1 Display
		System.out.println("Item #1"); //Prints "Item #1"
		System.out.println();
		
		System.out.println(item1.getDescription()); //Gets the description of item 1 from the RetailItem class
		System.out.println(item1.getUnitsOnHand()); //Gets the number of units on hand of item 1 from the RetailItem class
		System.out.println(item1.getPrice()); //Gets the price of item 1 from the RetailItem class
		
		//Item 2 Display
		System.out.println();
		System.out.println("Item #2"); //Prints "Item #2"
		System.out.println();
		
		System.out.println(item2.getDescription()); //Gets the description of item 2 from the RetailItem class
		System.out.println(item2.getUnitsOnHand()); //Gets the number of units on hand of item 2 from the RetailItem class
		System.out.println(item2.getPrice()); //Gets the price of item 2 from the RetailItem class
		
		//Item 3 Display
		System.out.println();
		System.out.println("Item #3"); //Prints "Item #3"
		System.out.println();
		
		System.out.println(item3.getDescription()); //Gets the description of item 3 from the RetailItem class
		System.out.println(item3.getUnitsOnHand()); //Gets the number of units on hand of item 3 from the RetailItem class
		System.out.println(item3.getPrice()); //Gets the price of item 3 from the RetailItem class
	}

}
