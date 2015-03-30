import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;


public class InterestCalculator extends JFrame{
    private static final long serialVersionUID = 1L;
    private final JTextField investmentAmountField, yearsField, annualInterestRateField;
    private final JLabel futureValue;
    private final JButton calculateButton;
    
    public InterestCalculator() {
        super("Interest Calculator");
        Image globe = new ImageIcon("icon.png").getImage();
        super.setIconImage(globe);
        
        investmentAmountField = new JTextField(4);
        investmentAmountField.setHorizontalAlignment(investmentAmountField.RIGHT);
        yearsField = new JTextField(4);
        yearsField.setHorizontalAlignment(investmentAmountField.RIGHT);
        annualInterestRateField = new JTextField(4);
        annualInterestRateField.setHorizontalAlignment(investmentAmountField.RIGHT);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                doCalculation();
            }
        });
        
        JLabel investmentAmountLabel, yearsLabel, annualInterestRateLabel, futureValueLabel;
        investmentAmountLabel = new JLabel("Investment Amount");
        yearsLabel = new JLabel("Years");
        annualInterestRateLabel = new JLabel("Annual Interest Rate");

        futureValue = new JLabel(" ");
        futureValue.setHorizontalAlignment(futureValue.RIGHT);
        futureValue.setBorder(new LineBorder(Color.LIGHT_GRAY));
        futureValueLabel = new JLabel("Future value");
        
        final JPanel inputPanel = new JPanel(new GridLayout(4, 2, 2, 2));
        inputPanel.add(investmentAmountLabel);
        inputPanel.add(investmentAmountField);
        inputPanel.add(yearsLabel);
        inputPanel.add(yearsField);
        inputPanel.add(annualInterestRateLabel);
        inputPanel.add(annualInterestRateField);
        inputPanel.add(futureValueLabel);
        inputPanel.add(futureValue);
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(calculateButton, BorderLayout.LINE_END);
        
        final Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.PAGE_START);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String calculate() throws IllegalStateException {
        String strInvestmentAmount, strYears, strAnnualInterestRate;
        strInvestmentAmount = investmentAmountField.getText().trim();
        strYears = yearsField.getText().trim();
        strAnnualInterestRate = annualInterestRateField.getText().trim();
        
        double investmentAmount, years, annualInterestRate, finalNum;
        
        if (strInvestmentAmount.length() == 0) {
            throw new IllegalStateException("You must enter an investment amount.");
        }
        if (strYears.length() == 0) {
            throw new IllegalStateException("You must enter a number of years.");
        }
        if (strAnnualInterestRate.length() == 0) {
            throw new IllegalStateException("You must enter an annual interest rate.");
        }
        StringBuilder investmentAmountBuilder = new StringBuilder();
        for (int i = 0; i < strInvestmentAmount.length(); i++) {
            if (strInvestmentAmount.charAt(i) != ',') {
                investmentAmountBuilder.append(strInvestmentAmount.charAt(i));
            }
        }
        strInvestmentAmount = investmentAmountBuilder.toString();
        
        StringBuilder yearsBuilder = new StringBuilder();
        for (int i = 0; i < strYears.length(); i++) {
            if (strYears.charAt(i) != ',') {
                yearsBuilder.append(strYears.charAt(i));
            }
        }
        strYears = yearsBuilder.toString();
        
        StringBuilder annualInterestRateBuilder = new StringBuilder();
        for (int i = 0; i < strAnnualInterestRate.length(); i++) {
            if (strAnnualInterestRate.charAt(i) != ',') {
                annualInterestRateBuilder.append(strAnnualInterestRate.charAt(i));
            }
        }
        strAnnualInterestRate = annualInterestRateBuilder.toString();
        
        try {
            investmentAmount = Double.parseDouble(strInvestmentAmount);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid value '" + strInvestmentAmount + "' for investment amount.");
        }
        
        try {
            years = Double.parseDouble(strYears);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid value '" + strYears + "' for number of years.");
        }
        
        try {
            annualInterestRate = Double.parseDouble(strAnnualInterestRate);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid value '" + strAnnualInterestRate + "' for annual interest rate.");
        }
        
        finalNum = investmentAmount * Math.pow(1 + annualInterestRate / 100 / 12, years * 12);
        return Double.toString(finalNum);
        
        
    }
    private void doCalculation() {
        try {
            final String result = calculate();
            futureValue.setText(result);
        } catch (final IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(
                this,
                iae.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (final IllegalStateException ise) {
            JOptionPane.showMessageDialog(
                    this,
                    ise.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getName());
            }
        } catch (Exception e) {
            
        }
        InterestCalculator calc = new InterestCalculator();
    }
}
