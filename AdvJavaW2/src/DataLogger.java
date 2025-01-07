import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*Zhamanta 
 * INEW 2338
 * Description: Week 2 Assignment
 * File-Based Data Logger
 */

public class DataLogger {
	static JComboBox comboBox; //Will create drop-down menu

	private static final String FILE_PATH = "Log.txt"; //Converts the text of the file to a String
	
	public static void main(String[] args) {
		//Creates the frame
		JFrame frame = new JFrame("Data logger");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creates the panel
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel); //Calls the placeComponents() method to add the buttons, labels, etc., to the panel
		
		//Makes the frame visible
		frame.setVisible(true);
	}

	//Builds the GUI
	private static void placeComponents(JPanel panel)
	{
		
		panel.setLayout(null); //Disables scrollinh
		
		//Creates nice label for user to read
		JLabel logLabel = new JLabel("Enter log entry:");
		logLabel.setBounds(10, 20, 100, 25);
		panel.add(logLabel);
		
		//Creates text field for user to type desired log
		JTextField logText = new JTextField(20);
		logText.setBounds(120, 20, 160, 25);
		panel.add(logText);
		
		//Creates the button that will be pushed to add log
		JButton addLogButton = new JButton("Add Log");
		addLogButton.setBounds(290, 20, 90, 25);
		addLogButton.addActionListener(e -> {
			//lambda expression for the button's ActionListener
			String logEntry = logText.getText();
			addLog(logEntry);
			JOptionPane.showMessageDialog(panel,  "Log added!"); //Success message for user
		});
		panel.add(addLogButton);
		
		//Creates the button that will be pushed to show all the logs
		JButton showLogsButton = new JButton("Show Logs");
		showLogsButton.setBounds(10, 60, 100, 25);
		showLogsButton.addActionListener(e -> showLogs(panel)); //lambda expression for the button's ActionListener
		panel.add(showLogsButton);
		
		
		//My ComboBox Creation
		
		String[] basicLogs = {"Login", "Sign Out"}; //The options in the drop-down menu are String type
 		comboBox = new JComboBox(basicLogs); //Creates new instance of JComboBox
 		comboBox.setBounds(10, 100, 100, 25); 
 		panel.add(comboBox);
 		
 		//lambda expressionf for the comboBox's ActionListener
 		comboBox.addActionListener(e ->
 		{
 			String selected = (String) comboBox.getSelectedItem(); //Retrieves option selected by user
 			try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) //Creates a writer to be able to add onto file
 			{
 				writer.write(selected); //Writes the selected option on tbe file
 				writer.newLine(); //New line
 				JOptionPane.showMessageDialog(panel, "Log Added!"); //Success message for user
 			} catch (IOException e1)
 			{
 				e1.printStackTrace(); //Catches error
 			}
 		});
 		
	}
	
	//Method for add log button functionality
	private static void addLog(String logEntry)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) //Creates a writer to be able to add onto file
		{
			writer.write(logEntry); //Writes what user typed on log text field on the file
			writer.newLine(); //Creates new line
		} catch (IOException e)
		{
			e.printStackTrace(); //Catches error
		}
	}
	
	private static void showLogs(JPanel panel)
	{
		List<String> logs = new ArrayList<>(); //Creates array where all logs will be stored
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) //Creates reader to be able to retrieve String from the file
		{
			String line; //Will store one line at a time from the file
			while ((line = reader.readLine()) != null) //Will loop as long as the line is not empty
			{
				logs.add(line); //Adds line/log to the array
			}
		} catch (IOException e)
		{
			e.printStackTrace(); //Catches error
		}
		JOptionPane.showMessageDialog(panel,  String.join("\n", logs)); //Displays logs nicely
	}
}
