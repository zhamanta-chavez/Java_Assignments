package finalExam;

/*Zhamanta 
 * ITSE 2317
 * Description: Final Exam
 * Question 3
 */

public class RetailItem {
	private String description; //Creates description field (private to this class)
	private int unitsOnHand; //Creates unitsOnHand field (private to this class)
	private double price; //Creates price field (private to this class)
	
	//Constructor; performs initialization
	public RetailItem(String userDescription, int userUnitsOnHand, double userPrice)
	{
		description = userDescription; //Initializes description
		unitsOnHand = userUnitsOnHand; //Initializes unitsOnHand
		price = userPrice; //Initializes price
	}
	
	//Mutators; allow for alteration of variables
	public void setDescription(String userDescription)
	{
		description = userDescription; //Sets description equal to the userDescription
	}
	
	public void setUnitsOnHand(int userUnitsOnHand)
	{
		unitsOnHand = userUnitsOnHand; //Sets unitsOnHand equal to the userUnitsOnHand
	}
	
	public void setPrice(double userPrice) 
	{
		price = userPrice; //Sets price equal to the userPrice
	}
	
	//Getters; allow for access to the values of the variables 
	public String getDescription()
	{
		return description; //returns the description of the retail item
	}
	
	public int getUnitsOnHand()
	{
		return unitsOnHand; //returns how many units are on hand for the retail item
	}
	
	public double getPrice() 
	{
		return price; //returns the price of the retail item
	}
}
