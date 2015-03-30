import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class GreetingApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField firstNameField, middleInitialField, lastNameField;
    private final JButton greetingButton;
    private final JLabel resultLabel;
    
    /*
     * Creates a GUI-Based GreetingApp.
     */
    public GreetingApp() {
        super("Greetings");
        firstNameField = new JTextField(10);
        middleInitialField = new JTextField(1);
        lastNameField = new JTextField(10);
        greetingButton = new JButton("Get Greeting");
        greetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                showGreeting();
            }
        });
        resultLabel = new JLabel(" ");
        
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("First name: "));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Middle initial: "));
        inputPanel.add(middleInitialField);
        inputPanel.add(new JLabel("Last name: "));
        inputPanel.add(lastNameField);
        
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(greetingButton);
        
        final Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.PAGE_START);
        mainPanel.add(separator, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);        
        setVisible(true);
    }
    
    /*
     * Gets the full name of the person from the text fields. Throws an 
     * IllegalStateException if either first name or last name is blank, or 
     * the length of the middle initial is greater than 1
     * 
     * @return String the full name of the person
     */
    
    private String getFullName() throws IllegalStateException {
        String firstName, middleInitial, lastName, fullName;
        firstName = firstNameField.getText();
        middleInitial = middleInitialField.getText().trim();
        lastName = lastNameField.getText();
        if (middleInitial.length() > 1) {
            throw new IllegalStateException("Middle initial must not be more " +
            		"than one letter.");
        }
        if (firstName.length() < 1) {
            throw new IllegalStateException("First name cannot be blank.");
        }
        if (lastName.length() < 1) {
            throw new IllegalStateException("Last name cannot be blank.");
        }
        if (middleInitial.length() < 1) {
            fullName = firstName + " " + lastName;
        } else {
            fullName = firstName + " " + middleInitial + ". " + lastName;
        }
        return fullName;
    }
    
    /*
     * Shows the greeting on a JOptionPane message dialog
     */
    
    private void showGreeting() {
        try {
            String greeting = "Hello " + getFullName() + "!";
            resultLabel.setText(String.valueOf(greeting));
            
            JOptionPane.showMessageDialog(this, resultLabel, "Greetings", JOptionPane.INFORMATION_MESSAGE);
        } catch (final IllegalStateException ise){
            JOptionPane.showMessageDialog(this, ise.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getName());
            }
        } catch (Exception e) {
            
        }
        GreetingApp greet = new GreetingApp();
    }
}
