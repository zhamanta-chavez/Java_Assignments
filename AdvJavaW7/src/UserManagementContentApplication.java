import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Zhamanta 
 * INEW 2338
 * Description: Week 7  Assignment
 * User Management and Content Application
 */

class User 
{
	private String username;
	private String password;
	private String role;
	
	public User(String username, String password, String role)
	{
		this.username = username; //stores the username
		this.password = password; //stores the password
		this.role = role; //stores the role
	}

	public String getUsername()
	{
		return username; //returns the username
	}
	
	public String getPassword()
	{
		return password; //returns the password
	}
	
	public String getRole()
	{
		return role; //returns the role
	}
}

class Product
{
	private String name;
	private double price;
	
	public Product(String name, double price)
	{
		this.name = name; //stores the product name
		this.price = price; //stores the product price
	}
	
	@Override
	public String toString()
	{
		return "Product{name='" + name + "', price=" + price + "}"; //returns the product information as a string
	}
}

public class UserManagementContentApplication 
{
	private static Map<String, User> users = new HashMap<>(); //stores the users in a map with the username as the key
	private static List<String> posts = new ArrayList<>(); //stores the posts
	private static List<Product> products = new ArrayList<>(); //stores the products
	private static final String LOG_FILE = "log.txt"; //specifies the log file name
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Capstone Application");
			frame.setSize(600, 400); //sets the window size
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the close operation
			
			JPanel loginPanel = createLoginPanel(frame); //creates the login panel
			frame.add(loginPanel);
			
			frame.setVisible(true); //makes the frame visible
		});
	}
	
	private static JPanel createLoginPanel(JFrame frame)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null); //sets the layout to null for absolute positioning
		
		JLabel userLabel = new JLabel("Username:"); //creates a label for username
		userLabel.setBounds(10, 20, 100, 25); //sets the position of the label
		panel.add(userLabel);
		
		JTextField userField = new JTextField(20); //creates a text field for the username
		userField.setBounds(120, 20, 200, 25); //sets the position of the text field
		panel.add(userField);
		
		JLabel passwordLabel = new JLabel("Password:"); //creates a label for password
		passwordLabel.setBounds(10, 60, 100, 25); //sets the position of the label
		panel.add(passwordLabel);
		
		JPasswordField passwordField = new JPasswordField(20); //creates a password field
		passwordField.setBounds(120, 60, 200, 25); //sets the position of the password field
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Login"); //creates a login button
		loginButton.setBounds(10, 100, 150, 25); //sets the position of the button
		panel.add(loginButton);
		
		JButton registerButton = new JButton("Register"); //creates a register button
		registerButton.setBounds(200, 100, 150, 25); //sets the position of the button
		panel.add(registerButton);
		
		loginButton.addActionListener(e -> {
			String username = userField.getText(); //gets the username from the text field
			String password = new String(passwordField.getPassword()); //gets the password from the password field
			if (users.containsKey(username) && users.get(username).getPassword().equals(password)) //checks if the username exists and the password matches
			{
				JOptionPane.showMessageDialog(panel, "Login successful"); //shows login success message
				frame.getContentPane().removeAll(); //removes all components from the frame
				frame.add(createDashboardPanel(frame, users.get(username))); //adds the dashboard panel
				frame.revalidate(); //revalidates the frame to update the layout
				frame.repaint(); //repaints the frame to reflect the changes
			} else
			{
				JOptionPane.showMessageDialog(panel,  "invalid credentials!"); //shows invalid login message
				logAction("Failed login attempt for username: " + username); //logs the failed login attempt
			}
		});
		
		registerButton.addActionListener(e -> {
			String username = userField.getText(); //gets the username from the text field
			String password = new String(passwordField.getPassword()); //gets the password from the password field
			if (!users.containsKey(username)) //checks if the username is already taken
			{
				users.put(username,  new User(username, password, "RegularUser")); //adds the new user to the map
				JOptionPane.showMessageDialog(panel,  "User registered successfully!"); //shows registration success message
				logAction("User " + username + " registered."); //logs the user registration
			} else
			{
				JOptionPane.showMessageDialog(panel, "Username already exists!"); //shows error if username already exists
			}
		});
		
		return panel; //returns the login panel
	}
	
	private static JPanel createDashboardPanel(JFrame frame, User user)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3, 10, 10)); //sets the layout to grid layout with 2 rows and 3 columns
		
		JButton productButton = new JButton("Product Catalog"); //creates the product catalog button
		JButton postsButton = new JButton("Social Feed"); //creates the social feed button
		JButton searchButton = new JButton("Directory Search"); //creates the directory search button
		JButton logButton = new JButton("View Logs"); //creates the view logs button
		JButton logoutButton = new JButton("Logout"); //creates the logout button
		
		productButton.addActionListener(e -> {
			JFrame productFrame = createProductCatalogFrame(); //creates the product catalog frame
			productFrame.setVisible(true); //makes the product catalog frame visible
		});
		
		postsButton.addActionListener(e -> {
			JFrame postsFrame = createSocialFeedFrame(); //creates the social feed frame
			postsFrame.setVisible(true); //makes the social feed frame visible
		});
		
		searchButton.addActionListener(e -> {
			JFrame searchFrame = createDirectorySearchFrame(); //creates the directory search frame
			searchFrame.setVisible(true); //makes the directory search frame visible
		});
		
		logButton.addActionListener(e -> {
			try
			{
				List<String> logs = readLogs(); //reads the logs
				JTextArea logArea = new JTextArea(String.join("\n", logs)); //creates a text area with the log content
				JScrollPane scrollPane = new JScrollPane(logArea); //wraps the text area in a scroll pane
				scrollPane.setPreferredSize(new Dimension(400, 300)); //sets the preferred size of the scroll pane
				JOptionPane.showMessageDialog(frame, scrollPane, "Application Logs", JOptionPane.INFORMATION_MESSAGE); //displays the logs in a message dialog
			} catch (IOException ex) 
			{
				JOptionPane.showMessageDialog(frame, "Error reading log file!"); //shows error message if logs cannot be read
			}
		});
		
		logoutButton.addActionListener(e -> {
			frame.getContentPane().removeAll(); //removes all components from the frame
			frame.add(createLoginPanel(frame)); //adds the login panel back to the frame
			frame.revalidate(); //revalidates the frame to update the layout
			frame.repaint(); //repaints the frame to reflect the changes
		});
		
		panel.add(productButton); //adds the product button to the panel
		panel.add(postsButton); //adds the posts button to the panel
		panel.add(searchButton); //adds the search button to the panel
		panel.add(logButton); //adds the log button to the panel
		panel.add(logoutButton); //adds the logout button to the panel
		
		return panel; //returns the dashboard panel
	}
	
	private static JFrame createProductCatalogFrame()
	{
		JFrame frame = new JFrame("Product Catalog");
		frame.setSize(400, 300); //sets the product catalog frame size
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); //sets the layout to border layout
		
		JTextArea productArea = new JTextArea(); //creates a text area to display products
		productArea.setEditable(false); //sets the text area to non-editable
		refreshProductArea(productArea); //refreshes the product area to display products
		
		JPanel inputPanel = new JPanel();
		JTextField nameField = new JTextField(10); //creates a text field for product name
		JTextField priceField = new JTextField(5); //creates a text field for product price
		JButton addButton = new JButton("Add Product"); //creates an add product button
		
		inputPanel.add(new JLabel("Name:")); //adds label for product name
		inputPanel.add(nameField); //adds name field to input panel
		inputPanel.add(new JLabel("Price:")); //adds label for product price
		inputPanel.add(priceField); //adds price field to input panel
		inputPanel.add(addButton); //adds add product button to input panel
		
		addButton.addActionListener(e -> {
			try
			{
				String name = nameField.getText(); //gets the product name
				double price = Double.parseDouble(priceField.getText()); //gets and parses the product price
				products.add(new Product(name, price)); //adds the new product to the product list
			} catch (NumberFormatException ex) 
			{
				JOptionPane.showMessageDialog(frame, "Invalid price!"); //shows error if price is invalid
			}
		});
		
		panel.add(new JScrollPane(productArea), BorderLayout.CENTER); //adds the product area to the center of the panel
		panel.add(inputPanel, BorderLayout.SOUTH); //adds the input panel to the south of the panel
		
		frame.add(panel); //adds the panel to the frame
		return frame; //returns the frame
	}
	
	private static JFrame createSocialFeedFrame()
	{
		JFrame frame = new JFrame("Social Feed");
		frame.setSize(400, 300); //sets the social feed frame size
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); //sets the layout to border layout
		
		JTextArea postArea = new JTextArea(); //creates a text area to display posts
		postArea.setEditable(false); //sets the text area to non-editable
		refreshPostArea(postArea); //refreshes the post area to display posts
		
		JPanel inputPanel = new JPanel();
		JTextField postField = new JTextField(20); //creates a text field for new posts
		JButton postButton = new JButton("Post"); //creates a post button
		
		inputPanel.add(new JLabel("Post:")); //adds label for post
		inputPanel.add(postField); //adds post field to input panel
		inputPanel.add(postButton); //adds post button to input panel
		
		postButton.addActionListener(e -> {
			String post = postField.getText(); //gets the post text
			posts.add(post); //adds the post to the list of posts
			refreshPostArea(postArea); //refreshes the post area to display updated posts
		});
		
		panel.add(new JScrollPane(postArea), BorderLayout.CENTER); //adds the post area to the center of the panel
		panel.add(inputPanel, BorderLayout.SOUTH); //adds the input panel to the south of the panel
		
		frame.add(panel); //adds the panel to the frame
		return frame; //returns the frame
	}
	
	private static JFrame createDirectorySearchFrame()
	{
		JFrame frame = new JFrame("Directory Search");
		frame.setSize(400, 300); //sets the directory search frame size
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); //sets the layout to border layout
		
		JTextArea directoryArea = new JTextArea(); //creates a text area to display directory
		directoryArea.setEditable(false); //sets the text area to non-editable
		refreshDirectoryArea(directoryArea); //refreshes the directory area to display directory information
		
		JPanel searchPanel = new JPanel();
		JTextField searchField = new JTextField(20); //creates a text field for searching
		JButton searchButton = new JButton("Search"); //creates a search button
		
		searchPanel.add(new JLabel("Search:")); //adds label for search
		searchPanel.add(searchField); //adds search field to search panel
		searchPanel.add(searchButton); //adds search button to search panel
		
		searchButton.addActionListener(e -> {
			String searchTerm = searchField.getText(); //gets the search term
			searchDirectory(searchTerm, directoryArea); //searches the directory for the term
		});
		
		panel.add(new JScrollPane(directoryArea), BorderLayout.CENTER); //adds the directory area to the center of the panel
		panel.add(searchPanel, BorderLayout.SOUTH); //adds the search panel to the south of the panel
		
		frame.add(panel); //adds the panel to the frame
		return frame; //returns the frame
	}
	
	private static void refreshProductArea(JTextArea productArea)
	{
		productArea.setText(""); //clears the text area
		for (Product product : products) //loops through the products
		{
			productArea.append(product.toString() + "\n"); //appends the product information to the text area
		}
	}
	
	private static void refreshPostArea(JTextArea postArea)
	{
		postArea.setText(""); //clears the text area
		for (String post : posts) //loops through the posts
		{
			postArea.append(post + "\n"); //appends the post to the text area
		}
	}
	
	private static void refreshDirectoryArea(JTextArea directoryArea)
	{
		directoryArea.setText(""); //clears the text area
		for (User user : users.values()) //loops through the users
		{
			directoryArea.append(user.getUsername() + " (" + user.getRole() + ")\n"); //appends the user information to the text area
		}
	}
	
	private static void searchDirectory(String searchTerm, JTextArea directoryArea)
	{
		directoryArea.setText(""); //clears the text area
		for (User user : users.values()) //loops through the users
		{
			if (user.getUsername().contains(searchTerm)) //checks if the username contains the search term
			{
				directoryArea.append(user.getUsername() + " (" + user.getRole() + ")\n"); //appends the matching user to the text area
			}
		}
	}
	
	private static List<String> readLogs() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE)); //reads the log file
		List<String> logs = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) //reads each line in the log file
		{
			logs.add(line); //adds the line to the logs list
		}
		reader.close(); //closes the reader
		return logs; //returns the logs
	}
	
	private static void logAction(String action) 
	{
		try
		{
			FileWriter writer = new FileWriter(LOG_FILE, true); //opens the log file for appending
			writer.write(action + "\n"); //writes the action to the log file
			writer.close(); //closes the writer
		} catch (IOException e)
		{
			e.printStackTrace(); //prints the stack trace if an error occurs
		}
	}
}

