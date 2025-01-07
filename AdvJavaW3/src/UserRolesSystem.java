import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/* Zhamanta 
 * INEW 2338
 * Description: Week 3 Assignment
 * User Roles and Permission System
 */

//Abstract (general) class User (super class)
abstract class User
{
	protected String username; 
	//Constructor
	public User(String username) 
	{ 
		this.username = username; 
	}
	public abstract String getPermissions(); //general method (will be adapted to each user type's permission)
}

//Admin sub-class of User class
class Admin extends User 
{
	public Admin(String username) 
	{ 
		super(username); //calls User class constructor
	}
	@Override //Adapts getPermissions() to the Admin's access message
	public String getPermissions() 
	{ 
		return "Admin: Full access";  //Prints Admin's access message
	}
}

//RegularUser sub-class of User class
class RegularUser extends User
{
	public RegularUser(String username) 
	{ 
		super(username); //calls User class constructor
	}
	@Override //Adapts getPermissions() to the RegularUser's access message
	public String getPermissions() 
	{ 
		return "User: Limited Access"; //Prints RegularUser's access message
	}
}

//Guest sub-class of User class
class Guest extends User 
{
	public Guest(String username)
	{
		super(username); //calls User class to constructor
	}
	@Override //Adapts getPermissions() to the Guest's access message
	public String getPermissions()
	{
		return "Guest: View-only access"; //Prints Guest's access message
	}
}

//CUSTOM EXCEPTION FOR UNATHORIZED ACCESS
class UnauthorizedAccessException extends Exception 
{
	public UnauthorizedAccessException(String message) 
	{
		super(message);
	}
}

//UserRolesSystem will initiate program
public class UserRolesSystem {
	private static User currentUser;  // DECLARE CURRENT USER VARIABLE
	
	private static Map<String, User> users = new HashMap<>(); //Stores keys (username and user status pairs)

	//GUI creation
	public static void main(String[] args) {
		//Frame creation
		JFrame frame = new JFrame("User Roles System");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panel creation
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel); //calls placeComponents() to place all buttons, labels, etc., on panel
		
		frame.setVisible(true);
		
		
		//ADD ZHAMANTA AS ADMIN BY DEFAULT
		users.put("Zhamanta", new Admin("Zhamanta"));
		//MAKE ZHAMANTA THE INITIAL USER
		currentUser = users.get("Zhamanta");

	}
	
	//This method places all GUI components on panel
	private static void placeComponents(JPanel panel)

	{
		panel.setLayout(null); //deactivates scrolling
		
		//Username label creation
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		//userText text field creation (user will type username here)
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 20, 160, 25);
		panel.add(userText);
		
		//Role label creation
		JLabel roleLabel = new JLabel("Role:");
		roleLabel.setBounds(10, 50, 80, 25);
		panel.add(roleLabel);
		
		//Drop-down menu that will offer the roles
		JComboBox<String> roleBox = new JComboBox<>(new String[] {"Admin", "RegularUser", "Guest"});
		roleBox.setBounds(100, 50, 80, 25);
		panel.add(roleBox);
		
		//CURRENT USER LABEL CREATION
		JLabel currentUserLabel = new JLabel("Current User:");
		currentUserLabel.setBounds(10, 120, 80, 25);
		panel.add(currentUserLabel);
		
		//MAKE ZHAMANTA DEFAULT ADMIN USER
		JComboBox<String> userBox = new JComboBox<>(new String[] {"Zhamanta"});
		userBox.setBounds(100, 120, 160, 25);
		panel.add(userBox);
		
		//ACTION LISTENER FOR USERS DROP-DOWN MENU
		userBox.addActionListener(e -> {
		    String selectedUsername = (String) userBox.getSelectedItem();
		    
		    currentUser = users.get(selectedUsername); //Sets currentUser to the selected user
		    
		    JOptionPane.showMessageDialog(panel, "Current user is now " + selectedUsername); //Displays a message with the new current user
		});

		
		//Register button creation
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(10, 90, 120, 25);
		registerButton.addActionListener(e -> { //Lambda expression for register button's ActionListener
			//BUTTON CAN ONLY BE PRESSED BY ADMIN USERS
			try {
		        if (currentUser instanceof Guest) {
		            throw new UnauthorizedAccessException("Guest cannot perform Admin actions."); //If pressed by Guest, error message will pop up
		        }
		        if (currentUser instanceof RegularUser) {
		            throw new UnauthorizedAccessException("Regular User cannot perform Admin actions."); //If pressed by Regular User, error message will pop up
		        }
		        
		        String username = userText.getText(); //Obtains text from userText text field
				String role = (String) roleBox.getSelectedItem(); //Gets role selected from drop-down menu
				User user; //Creates instance of User
				if ("Admin".equals(role))
				{
					user = new Admin(username); //If selected role is Admin, a new Admin object is created
				} else if ("RegularUser".equals(role))
				{
					user = new RegularUser(username); //If selected role is RegularUser, a new RegularUser object is created
				} else 
				{
					user = new Guest(username); //If selected role is Guest, a new Guest object is created
				}
				users.put(username,  user);
				
				userBox.addItem(username); //ADD CREATED USER TO USERS' DROP-DOWM MENU
				
				JOptionPane.showMessageDialog(panel, username + " registered as " + role); //Registration message
				
		    } catch (UnauthorizedAccessException ex) {
		        JOptionPane.showMessageDialog(panel, ex.getMessage()); //Displays error message
		    }
			
		});
		panel.add(registerButton); //Adds register button to panel
		
		// GUEST ACTION BUTTON CREATION
		JButton guestActionButton = new JButton("Guest Action");
		guestActionButton.setBounds(10, 160, 150, 25);
		guestActionButton.addActionListener(e -> { //Lambda expression for guestActionButton's ActionListener
		    try {
		        if (currentUser instanceof Admin) {
		            throw new UnauthorizedAccessException("Admin cannot perform Guest actions."); //If Admin presses button, error message displays
		        }
		        if (currentUser instanceof RegularUser) {
		            throw new UnauthorizedAccessException("Regular User cannot perform Guest actions."); //If Regular User presses button, error message displays
		        }
		        //If currentUser is a Guest, perform the action
		        JOptionPane.showMessageDialog(panel, "Guest Action performed."); //Success message
		    } catch (UnauthorizedAccessException ex) {
		        JOptionPane.showMessageDialog(panel, ex.getMessage()); //Displays error message
		    }
		});
		panel.add(guestActionButton);

		//REGULAR USER ACTION BUTTON CREATION
		JButton regularUserActionButton = new JButton("Regular User Action");
		regularUserActionButton.setBounds(170, 160, 150, 25);
		regularUserActionButton.addActionListener(e -> { //Lambda expression for regularUserActionButton's ActionListener
		    try {
		        if (currentUser instanceof Admin) {
		            throw new UnauthorizedAccessException("Admin cannot perform RegularUser actions."); //If Admin presses button, error message displays
		        }
		        if (currentUser instanceof Guest) {
		            throw new UnauthorizedAccessException("Guest cannot perform RegularUser actions."); //If Guest presses button, error message displays
		        }
		        // If currentUser is a RegularUser, perform the action
		        JOptionPane.showMessageDialog(panel, "Regular User Action performed."); //Success message
		    } catch (UnauthorizedAccessException ex) {
		        JOptionPane.showMessageDialog(panel, ex.getMessage()); //Displays error message
		    }
		});
		panel.add(regularUserActionButton);

		//View Permissions button creation
		JButton viewPermissionsButton = new JButton("View Permissions");
		viewPermissionsButton.setBounds(150, 90, 140, 25);
		viewPermissionsButton.addActionListener(e -> { //Lambda expression for view permissions button's ActionListener
			String username = userText.getText(); //Obtains text from userText text field
			User user = users.get(username);
			if (user != null)
			{
				JOptionPane.showMessageDialog(panel,  user.getPermissions()); //If user exists, it will show its permissions
			} else
			{
				JOptionPane.showMessageDialog(panel, "User not found."); //If user does not exist, it will show message
			}
		});
		panel.add(viewPermissionsButton); //Adds permissions button to panel
	}
}
