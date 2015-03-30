import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField inputField;
    private final JButton okButton;
    private final JLabel resultLabel;
    

    public GUI() {
        super("GUI Demo");
        
        inputField = new JTextField(6);//6 characters
        okButton = new JButton("Evaluate");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                doEvaluation();
            }
        });
        resultLabel = new JLabel(" ");
        
        
        final JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Expression: "));
        inputPanel.add(inputField);
        inputPanel.add(okButton);

        final JPanel outputPanel = new JPanel();
        outputPanel.add(resultLabel);
        
        final Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();//packs the components close together so that the components are as small as can be 
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void doEvaluation() {
        try {
            final int result = evaluate(inputField.getText().trim());
            resultLabel.setText(String.valueOf(result));
        } catch (final IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(
                this,
                iae.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    //GUIS in java
    // - AWT (Abstract Windows Toolkit) heavy weight
    //    - older
    // - Swing
    //    - light weight
    

    private int evaluate(String expression) throws IllegalArgumentException {
        expression = expression.replaceAll(" ", "");
        int i = 0, len = expression.length(), operatorPos;

        if (len == 0) {
            throw new IllegalArgumentException("No expression entered.");
        }
        while (i < len && Character.isDigit(expression.charAt(i))) {
            i++;
        }
        operatorPos = i;

        if (operatorPos >= len) {
            throw new IllegalArgumentException("Missing operator.");
        }

        final String operand1Str = expression.substring(0, operatorPos);
        final String operatorStr = expression.substring(operatorPos,
                operatorPos + 1);
        final String operand2Str = expression.substring(operatorPos + 1);

        int operand1, operand2;
        try {
            operand1 = Integer.parseInt(expression.substring(0, operatorPos));
        } catch (final NumberFormatException nfe) {
            throw new IllegalArgumentException(
                "Invalid value '" + operand1Str + "' for operand 1.");
        }
        try {
            operand2 = Integer.parseInt(operand2Str);
        } catch (final NumberFormatException nfe) {
            throw new IllegalArgumentException(
                "Invalid value '" + operand2Str + "' for operand 2.");
        }

        final char operator = operatorStr.charAt(0);
        int result;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Division by zero.");
                }
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator '"
                        + operatorStr + "'.");
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getName());
            }
        } catch (Exception e) {
            
        }
        GUI gui = new GUI();
    }
}
class ButtonListener implements ActionListener {
    private final JFrame parent;
    
    public ButtonListener(final JFrame parent) {
        this.parent = parent;
    }
    
    public void actionPerformed(final ActionEvent e) {
        
    }
}