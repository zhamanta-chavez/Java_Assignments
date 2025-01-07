import java.util.LinkedList;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class User 
{
    protected String username;

    // Constructor
    public User(String username) 
    {
        this.username = username;
    }

    @Override
    public String toString() 
    {
        // Displays the username in the JComboBox
        return username;
    }
}

// Class representing a Post
class Post 
{
    String name;
    String description;
    Map<User, LinkedList<String>> comments = new HashMap<>(); // Uses a list for multiple comments
    Map<User, Boolean> likes = new HashMap<>(); // Tracks which users have liked the post

    // Post constructor
    public Post(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }

    // Adds a comment from a user
    public void addComment(User user, String comment) 
    {
        comments.putIfAbsent(user, new LinkedList<>()); // Initializes the list if not present
        comments.get(user).add(comment); // Adds the comment to the user's list
    }

    // Adds or removes a like from a user
    public void toggleLike(User user) 
    {
        if (likes.containsKey(user)) 
        {
            likes.remove(user); // Removes the like if the user already liked
        } else 
        {
            likes.put(user, true); // Adds the like
        }
    }

    public int getLikeCount() 
    {
        return likes.size(); // Returns the total number of likes
    }

    @Override
    public String toString() 
    {
        StringBuilder result = new StringBuilder("Title: " + name + "\nDescription: " + description + "\nLikes: " + getLikeCount() + "\nComments:\n");
        for (Map.Entry<User, LinkedList<String>> entry : comments.entrySet()) {
            result.append(entry.getKey().username).append(":\n");
            for (String comment : entry.getValue()) {
                result.append("    ").append(comment).append("\n"); // Indented comment
            }
        }
        return result.toString();
    }
}

// Social Media Feed Simulator
public class SocialMediaFeedSimulator {
    private static User currentUser;  // DECLARE CURRENT USER VARIABLE

    private static LinkedList<Post> posts = new LinkedList<>(); // List to contain posts
    private static LinkedList<User> users = new LinkedList<>(); // List to contain users

    // Method to add a post to the LinkedList
    public static void addPost(Post post) {
        posts.add(post);
    }

    public static void main(String[] args) {
        // GUI Setup
        JFrame frame = new JFrame("Social Media Feed Simulator");
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    // Method to place all GUI components on the panel
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null); // Deactivates scrolling
        
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        
        // userText text field creation (user will type username here)
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 160, 25);
        panel.add(userText);
        
        JLabel userBoxLabel = new JLabel("Login As:");
        userBoxLabel.setBounds(10, 80, 80, 25);
        panel.add(userBoxLabel);
        
        JComboBox<User> userBox = new JComboBox<>();
        userBox.setBounds(100, 80, 160, 25);
        panel.add(userBox);

        // Label and text field for post name
        JLabel postNameLabel = new JLabel("Post Title:");
        postNameLabel.setBounds(10, 110, 80, 25);
        panel.add(postNameLabel);

        JTextField postNameText = new JTextField(20);
        postNameText.setBounds(100, 110, 160, 25);
        panel.add(postNameText);

        // Label and text field for post description
        JLabel postDescriptionLabel = new JLabel("Post Description:");
        postDescriptionLabel.setBounds(10, 140, 100, 25);
        panel.add(postDescriptionLabel);

        JTextField postDescriptionText = new JTextField(20);
        postDescriptionText.setBounds(120, 140, 160, 25);
        panel.add(postDescriptionText);
        
        // ACTION LISTENER FOR USERS DROP-DOWN MENU
        userBox.addActionListener(e -> {
            currentUser = (User) userBox.getSelectedItem();
            
            JOptionPane.showMessageDialog(panel, "Currently logged in as " + currentUser); // Displays a message with the new current user
        });

        // Button to add posts
        JButton postButton = new JButton("Create Post");
        postButton.setBounds(10, 170, 120, 25);
        postButton.addActionListener(e -> {
            String name = postNameText.getText();
            String description = postDescriptionText.getText();

            if (!name.isEmpty() && !description.isEmpty()) { // Validate input
                Post post = new Post(name, description);
                addPost(post);
                postNameText.setText("");
                postDescriptionText.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(postButton);

        // Button to view all posts
        JButton viewAllPostsButton = new JButton("View All Posts");
        viewAllPostsButton.setBounds(10, 200, 160, 25);
        viewAllPostsButton.addActionListener(e -> {
            JFrame postsFrame = new JFrame("All Posts");
            postsFrame.setSize(400, 300);
            JPanel postsPanel = new JPanel();
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

            for (Post eachPost : posts) 
            {
                // Creates a panel for each post
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createTitledBorder(eachPost.name));

                // Displays post description
                JLabel descriptionLabel = new JLabel("<html>" + eachPost.description.replace("\n", "<br>") + "</html>");
                postPanel.add(descriptionLabel);

                // Displays like count
                JLabel likeCountLabel = new JLabel("Likes: " + eachPost.getLikeCount());
                postPanel.add(likeCountLabel);

                // Displays existing comments
                JTextArea commentsArea = new JTextArea(5, 30);
                commentsArea.setEditable(false);
                StringBuilder commentsContent = new StringBuilder();
                for (Map.Entry<User, LinkedList<String>> entry : eachPost.comments.entrySet()) {
                    commentsContent.append(entry.getKey().username).append(": ").append(entry.getValue()).append("\n");
                }
                commentsArea.setText(commentsContent.toString());
                postPanel.add(new JScrollPane(commentsArea));

                // Input field to add a comment
                JTextField commentField = new JTextField(20);
                postPanel.add(commentField);

                // Button to add a comment
                JButton addCommentButton = new JButton("Add Comment");
                addCommentButton.addActionListener(event -> {
                    if (currentUser == null) 
                    {
                        JOptionPane.showMessageDialog(postsFrame, "Please log in to add a comment.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String comment = commentField.getText();
                    if (!comment.isEmpty()) {
                        eachPost.addComment(currentUser, comment);
                        commentsContent.setLength(0); // Clear existing comments content

                        for (Map.Entry<User, LinkedList<String>> entry : eachPost.comments.entrySet()) {
                            commentsContent.append(entry.getKey().username).append(":\n");
                            for (String userComment : entry.getValue()) 
                            {
                                commentsContent.append("    ").append(userComment).append("\n"); // Indented comment
                            }
                        }

                        commentsArea.setText(commentsContent.toString()); // Update comments display
                        commentField.setText(""); // Clear the input field
                    } else 
                    {
                        JOptionPane.showMessageDialog(postsFrame, "Comment cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                postPanel.add(addCommentButton);

                // Button to like/unlike the post
                JButton likeButton = new JButton("Like");
                likeButton.addActionListener(event -> {
                    if (currentUser == null) 
                    {
                        JOptionPane.showMessageDialog(postsFrame, "Please log in to like a post.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    eachPost.toggleLike(currentUser); // Toggle the like
                    likeCountLabel.setText("Likes: " + eachPost.getLikeCount()); // Update like count
                });

                postPanel.add(likeButton);

                // Adds the post panel to the main panel
                postsPanel.add(postPanel);
            }

            // Adds everything to the frame and display
            postsFrame.add(new JScrollPane(postsPanel));
            postsFrame.setVisible(true);
        });
        panel.add(viewAllPostsButton);

        // Button to add users
        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(10, 50, 120, 25);
        addUserButton.addActionListener(e -> {
            String username = userText.getText();
            if (!username.isEmpty()) {
                // Check if username already exists
                boolean usernameExists = false;
                for (User user : users) {
                    if (user.username.equals(username)) {
                        usernameExists = true;
                        break;
                    }
                }

                if (usernameExists) {
                    JOptionPane.showMessageDialog(panel, "This username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    User newUser = new User(username);
                    users.add(newUser);
                    userBox.addItem(newUser);  // Updates the dropdown list
                    userText.setText("");  // Clears the text field
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(addUserButton);

    }
}
