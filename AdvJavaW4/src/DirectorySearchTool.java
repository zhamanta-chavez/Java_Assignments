import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* Zhamanta 
 * INEW 2338
 * Description: Week 4 Assignment
 * Directory Search Tool
 */

public class DirectorySearchTool {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> { //Creates frame/panel
			JFrame frame = new JFrame("Directory Search Tool");
			frame.setSize(500, 450);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			frame.add(panel);
			
			//Components for Directory Path
			JLabel dirLabel = new JLabel("Directory:");
			dirLabel.setBounds(10, 20, 80, 25);
			panel.add(dirLabel);
			
			JTextField dirField = new JTextField(20);
			dirField.setBounds(100, 20, 250, 25);
			panel.add(dirField);
			
			JButton browseButton = new JButton("Browse");
			browseButton.setBounds(360, 20, 100, 25);
			panel.add(browseButton);
			
			//Components for File Extension
			JLabel extLabel = new JLabel("Extension:");
			extLabel.setBounds(10, 60, 80, 25);
			panel.add(extLabel);
			
			JTextField extField = new JTextField(10);
			extField.setBounds(100, 60, 100, 25);
			panel.add(extField);
			
			//COUNT FILES BUTTON
			JLabel countLabel = new JLabel("Total Files and Directories: ");
			countLabel.setBounds(10, 360, 300, 25);
			panel.add(countLabel);

			JButton countButton = new JButton("Count Items");
			countButton.setBounds(320, 360, 140, 25);
			panel.add(countButton);
			
			//COUNT BUTTON ACTIONLISTENER
			countButton.addActionListener(e -> {
			    String dirPath = dirField.getText(); //Retrieves path

			    if (dirPath.isEmpty()) { //Error message if there is no path selected
			        JOptionPane.showMessageDialog(panel, "Please enter a directory path.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    File directory = new File(dirPath); //Creates new File object
			    if (!directory.exists() || !directory.isDirectory()) { //If the directory does not exit, an error message displays
			        JOptionPane.showMessageDialog(panel, "Invalid directory path.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Perform the count
			    int totalCount = countItems(directory); //Calls the countItems() method to count the number of files found
			    countLabel.setText("Total Files and Directories: " + totalCount); //Displays count of files
			});
			
			//Search Button
			JButton searchButton = new JButton("Search");
			searchButton.setBounds(210, 60, 100, 25);
			panel.add(searchButton);
			
			//Text Area for Results
			JTextArea resultArea = new JTextArea();
			resultArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(resultArea);
			scrollPane.setBounds(10, 100, 450, 250);
			panel.add(scrollPane);
			
			//Browse Button Action
			browseButton.addActionListener(e -> {
				JFileChooser fileChooser = new JFileChooser(); //Creates new JFileChooser object
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(frame); //Stores user's option in int variable
				if (option == JFileChooser.APPROVE_OPTION) { //When user selects option, it retrieves path
					File selectedDirectory = fileChooser.getSelectedFile();
					dirField.setText(selectedDirectory.getAbsolutePath());
				}
			});
		
		
			//Search Button Action
			searchButton.addActionListener(e -> {
				String dirPath = dirField.getText(); //Gets path from user input into text field
				String extension = extField.getText(); //Gets entension from user input into text field
			
				if (dirPath.isEmpty() || extension.isEmpty()) { //Error message for no path or extension given
					JOptionPane.showMessageDialog(panel, "Please enter both directory path and file extension.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				File directory = new File(dirPath); //Creates new file object
				if (!directory.exists() || !directory.isDirectory()) { //If the directory does not exit, an error message displays
					JOptionPane.showMessageDialog(panel, "Invalid directory path.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				//Perform search
				List<String> results = new ArrayList<>(); //This array will contain all the found files with specified extension
				searchDirectory(directory, extension, results); //Calls searchDirectory() method
			
				if (results.isEmpty()) { //Displays message for empty results
					resultArea.setText("No files found with the extension: " + extension);
				} else { //Displays results
					StringBuilder resultText = new StringBuilder("Files found:\n");
					for (String filePath : results) {
						resultText.append(filePath).append("\n");
					}
					resultArea.setText(resultText.toString());
				}
			});
		
			frame.setVisible(true); 
		});
		
	}
		
	//COUNT ITEMS METHOD
	private static int countItems(File directory) {
	    int count = 0;
	    File[] files = directory.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            count++; //Count this file/directory
	            if (file.isDirectory()) {
	                count += countItems(file); //Recursive call for subdirectories
	            }
	        }
	    }
	    return count;
	}
	
	// Recursive method to search directory
	private static void searchDirectory(File directory, String extension, List<String> results) {
		File[] files = directory.listFiles();
		if (files != null) { //If files were found, the method will be called again
			for (File file : files) {
				if (file.isDirectory()) {
					searchDirectory(file, extension, results); //Recursive call for subdirectories
				} else if (file.getName().endsWith(extension)) {
					results.add(file.getAbsolutePath());
				}
			}
		}
	}
}

	
