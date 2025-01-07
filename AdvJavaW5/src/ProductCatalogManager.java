import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

//Custom exception for when the catalog reaches maximum capacity
class CatalogLimitExceededException extends Exception {
    public CatalogLimitExceededException(String message) {
        super(message);
    }
}

//Abstract class Product (since furniture, clothing, and electronics have the same description)
abstract class Product 
{
    String name;
    String description;
    double price;
    
    //Product constructor
    public Product(String name, String description, double price) {
        this.name = name; 
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() 
    {
    	//Displayes the details of the products nicely
        return "Name: " + name + ", Description: " + description + ", Price: " + price;
    }
}

//Furniture class inherits from Product
class Furniture extends Product 
{
    public Furniture(String name, String description, double price) 
    {
        super(name, description, price);
    }
}

//Clothing class inherits from Product
class Clothing extends Product 
{
    public Clothing(String name, String description, double price) 
    {
        super(name, description, price);
    }
}

//Electronics class inherits from Product
class Electronics extends Product 
{
    public Electronics(String name, String description, double price) 
    {
        super(name, description, price);
    }
}

//Product Manager
public class ProductCatalogManager 
{

    private static Map<String, List<Product>> catalog = new HashMap<>(); //HashMap will contain product-category pairs

    //Generic method to add product to catalog
    public static <T extends Product> void addProduct(T product, String category) throws CatalogLimitExceededException 
    {
        //Checks if the catalog exceeds the limit of 5 products
        int totalProducts = catalog.values().stream()
                                    .mapToInt(List::size) //Gets size of each category's list
                                    .sum(); //Adds all product counts

        //If the catalog exceeds five items, the error message will be displayed
        if (totalProducts >= 5) 
        {
            throw new CatalogLimitExceededException("Catalog has exceeded the limit of 5 products.");
        }

        catalog.putIfAbsent(category, new ArrayList<>()); //Will add the category array to the HashMap if it does not already exist
        catalog.get(category).add(product); //Will add the product to the appropriate category array
    }

    public static void main(String[] args) 
    {
        //GUI
    	
    	//Creates the frame and panel that user sees and interacts with
        JFrame frame = new JFrame("Product Catalog Manager");
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    //This method places all GUI components on panel
    private static void placeComponents(JPanel panel) 
    {
        panel.setLayout(null); //Deactivates scrolling

        //This area is where all the products (and their details) that have been added to the catalog will be displayed
        JTextArea catalogArea;
        catalogArea = new JTextArea(50, 50);
        catalogArea.setEditable(false);
        catalogArea.setBounds(10, 200, 300, 200);  
        panel.add(catalogArea);

        //Label for the product's name input box
        JLabel productNameLabel = new JLabel("Name:");
        productNameLabel.setBounds(10, 20, 80, 25);
        panel.add(productNameLabel);

        //Text field for user to input product name
        JTextField productNameText = new JTextField(20);
        productNameText.setBounds(100, 20, 160, 25);
        panel.add(productNameText);

        //Label for the product's description input box
        JLabel productDescriptionLabel = new JLabel("Description:");
        productDescriptionLabel.setBounds(10, 50, 80, 25);
        panel.add(productDescriptionLabel);

        //Text field for user to input product description
        JTextField productDescriptionText = new JTextField(20);
        productDescriptionText.setBounds(100, 50, 160, 25);
        panel.add(productDescriptionText);

        //Label for the product's price input box
        JLabel productPriceLabel = new JLabel("Price:");
        productPriceLabel.setBounds(10, 80, 80, 25);
        panel.add(productPriceLabel);

        //Text field for user to input product price
        JTextField productPriceText = new JTextField(20);
        productPriceText.setBounds(100, 80, 160, 25);
        panel.add(productPriceText);

        //Category drop- down menu label 
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 110, 80, 25);
        panel.add(categoryLabel);

        //Category drop-down menu
        JComboBox<String> categoryBox = new JComboBox<>(new String[] { "Furniture", "Clothing", "Electronics" });
        categoryBox.setBounds(100, 110, 160, 25);
        panel.add(categoryBox);

        //Register button to add product to catalog
        JButton registerButton = new JButton("Add Product");
        registerButton.setBounds(10, 160, 120, 25);
        registerButton.addActionListener(e -> {
            String name = productNameText.getText(); //Gets name from user input into text field
            String description = productDescriptionText.getText(); //Gets description from user input into text field
            double price = 0; //initializes double to 0 (for now)
            
            try {
                //Tries to parse the price entered by the user
                price = Double.parseDouble(productPriceText.getText());

                //If the price entered is less than 0, the error message will appear
                if (price < 0) 
                {
                    throw new IllegalArgumentException("Price cannot be negative.");
                }
            } catch (NumberFormatException ex) 
            {
                //If the price is not a valid number
                JOptionPane.showMessageDialog(panel, "Please enter a valid price.");
                return; //Exit the method if the price is invalid
            } catch (IllegalArgumentException ex) 
            {
           
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return; //Exit the method if the price is negative
            }
            
            String category = (String) categoryBox.getSelectedItem(); //The category selected by the user in the drop-down menu will be stores in category variable

            Product product = null; //Creates a null instance of Product
            
            //Determines the product's class depending on the selection of the category drop-down menu
            if (category.equals("Furniture")) 
            {
                product = new Furniture(name, description, price);
            } else if (category.equals("Clothing")) 
            {
                product = new Clothing(name, description, price);
            } else if (category.equals("Electronics")) 
            {
                product = new Electronics(name, description, price);
            }
            
            
            if (product != null) 
            {
                try 
                {
                    addProduct(product, category); //It will call the addProduct method to add the product to the catalog (if not null)
                    productNameText.setText("");
                    productDescriptionText.setText("");
                    productPriceText.setText("");
                } catch (CatalogLimitExceededException ex) 
                {
                    //Shows error message if the catalog limit is exceeded
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(registerButton);

        //View All Products button creation
        JButton viewAllProductButton = new JButton("View All Products");
        viewAllProductButton.setBounds(150, 160, 160, 25);
        viewAllProductButton.addActionListener(e -> { //ActionListener for button
            StringBuilder catalogContent = new StringBuilder(); //Use StringBuilder to append the text

            //Loops through the catalog to create the text that will be displayed
            catalog.forEach((category, products) -> {
                catalogContent.append(category).append(":\n");
                for (Product product : products) {
                    catalogContent.append(product.toString()).append("\n");
                }
                catalogContent.append("\n");
            });

            //Displays the catalog content in the JTextArea
            catalogArea.setText(catalogContent.toString());
        });
        panel.add(viewAllProductButton);
    }
}

