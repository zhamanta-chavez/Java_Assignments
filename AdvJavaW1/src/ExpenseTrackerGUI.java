import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*Zhamanta 
 * INEW 2338
 * Description: Week 1 Assignment
 * Personal Expense Tracker
 */

//Defining the Expense class
class Expense 
{
	//Initializing private fields
	private double amount;
	private String category;
	private String description;
	
	//Defining the attributes of an instance of Expense
	public Expense(double amount, String category, String description)
	{
		this.amount = amount;
		this.category = category;
		this.description = description;
	}
	
	//This getter method returns the amount (cost of expense)
	public double getAmount()
	{
		return amount;
	}
	
	//This getter method returns the category the expense belongs to
	public String getCategory()
	{
		return category;
	}
	
	//This getter method returns the description of the expense
	public String getDescription()
	{
		return description;
	}
	
	//This method nicely displayes the information stored about the expense
	public String toString()
	{
		return String.format("Category: %s, Amount: $%.2f, Description: %s", category, amount, description);
	}
}

//Creating the Expense Tracker
class ExpenseTracker
{
	private ArrayList<Expense> expenses; //Will store all of the expenses
	
	//This constructor assigns an array to expenses
	public ExpenseTracker()
	{
		expenses = new ArrayList<>();
	}
	
	//Uses the add method to add the expense to the array
	public void addExpense(Expense expense)
	{
		expenses.add(expense);
	}
	
	//This getter returns the array of expenses
	public ArrayList<Expense> getExpenses()
	{
		return expenses;
	}
	
	//This getter returns the running total of the cost of all added expenses
	public double getTotalExpenses()
	{
		double total = 0; //Initializes running total to $0
		for (Expense expense : expenses)
		{
			total += expense.getAmount(); //Loops through the array and adds the elements' individual costs to the total
		}
		return total; //returns running total of expenses
	}	
	
	//This method clears the expenses array
	public void clearExpenses() {
	    expenses.clear();
	}

}

//Handles the user interface
public class ExpenseTrackerGUI extends JFrame
{
	//Creates the fields where the user can type
	private JTextField amountField;
	private JTextField categoryField;
	private JTextField descriptionField;
	private JTextArea expenseArea;
	
	//Creates an instance of ExpenseTracker
	private ExpenseTracker expenseTracker;
	
	public ExpenseTrackerGUI()
	{
		//Sets up window 
		expenseTracker = new ExpenseTracker();
		setTitle("Personal Expense Tracker");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		//Creates labels and buttons
		JLabel amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(10);
		JLabel categoryLabel = new JLabel("Category: ");
		categoryField = new JTextField(10);
		JLabel descriptionLabel = new JLabel("Description");
		descriptionField = new JTextField(10);
		JButton addButton = new JButton("Add Expense"); //creates button that adds expenses
		JButton showButton = new JButton("Show Expenses"); //creates button that displays expenses
		expenseArea = new JTextArea(10, 30);
		expenseArea.setEditable(false); //User cannot edit the area
		
		
		JButton deleteButton = new JButton("Delete All"); //creates delete button
		
		//Adds all the graphics to the window
		add(amountLabel);
		add(amountField);
		add(categoryLabel);
		add(categoryField);
		add(descriptionLabel);
		add(descriptionField);
		add(addButton);
		add(showButton);
		add(new JScrollPane(expenseArea));
		
		add(deleteButton);
		
		//Functionality of addButton
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addExpense(); //When the button is pressed, it calls the addExpense method
			}
		});
		
		//Functionality of showButton
		showButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showExpenses(); //When the button is pressed, it calls the showExpenses method
			}
		});
		
		//Functionality of deleteButton
		deleteButton.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        deleteAllExpenses(); //When the button is pressed, it calls the deleteAllExpenses method
		    }
		});

		
	}
	
	//Will add expense to array of expenses
	private void addExpense()
	{
		try
		{
			//Initialization of variables
			double amount = Double.parseDouble(amountField.getText());
			String category = categoryField.getText();
			String description = descriptionField.getText();
			
			//Input validation
			if (amount < 0)
			{
				throw new NumberFormatException(); //If amount is less than 0, an error message is displayed
			}
			
			//Creates an instance of Expense
			Expense expense = new Expense(amount, category, description);
			expenseTracker.addExpense(expense); //adds the expense to array of expenses
			JOptionPane.showMessageDialog(this, "Expense added successfully!"); //Success message 
			
			//Clears fields for user to type more
			amountField.setText("");
			categoryField.setText("");
			descriptionField.setText("");
			
			//Error message
		} catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Invalid input! Please enter a positive number for amount.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Will show expenses
	private void showExpenses()
	{
		//calls the toString method to display the expenses nicely
		StringBuilder expenseList = new StringBuilder();
		for (Expense expense : expenseTracker.getExpenses())
		{
			expenseList.append(expense.toString()).append("\n");
		}
		expenseArea.setText(expenseList.toString());
		expenseArea.append(String.format("Total Expenses: $%.2f", expenseTracker.getTotalExpenses())); //Displays total of expenses nicely
	}
	
	//Will delete expenses
	private void deleteAllExpenses() {
	    expenseTracker.clearExpenses();  //Clears the expenses in the tracker
	    expenseArea.setText("");  //Clears the display area
	    JOptionPane.showMessageDialog(this, "All expenses have been deleted.");  //Message for the user
	}

	//This main method starts the program
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> {
			ExpenseTrackerGUI gui = new ExpenseTrackerGUI(); //Creates a GUI object
			gui.setVisible(true); //Makes the GUI visible
		});
	}
}
