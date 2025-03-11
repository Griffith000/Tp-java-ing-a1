package Forme;

import java.awt.*;
import javax.swing.*;

public class TextFieldDemo {
    public static void main(String[] args) {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Text Field Demo");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold components
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        // Add a JLabel for instructions
        JLabel label = new JLabel("Enter your name:");
        panel.add(label);

        // Add a JTextField for input
        JTextField textField = new JTextField(20);  // Width of the text field (number of characters)
        panel.add(textField);

        // Create a JButton to retrieve data
        JButton button = new JButton("Submit");

        // Add ActionListener to the button
        button.addActionListener(e -> {
            String input = textField.getText();
            System.out.println("User Input: " + input);
            JOptionPane.showMessageDialog(frame, "Thank you for your input!");
        });

        panel.add(button);

        // Display the window
        frame.setVisible(true);
    }
}