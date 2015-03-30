import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class FoodQuiz extends JFrame{
    private static final long serialVersionUID = 1L;
    private ButtonGroup options;
    private JRadioButton option1, option2, option3, option4;
    private Question questionOne, questionTwo, questionThree, questionFour, questionFive;
    private JLabel imageLabel, questionLabel, answerLabel;
    private JButton button1, button2, button3, button4, button5;
    private String answer;
    private int count;
    
    public FoodQuiz(String filename) {
        super("Food Quiz");
        final Container mainPanel = getContentPane();
        
        final JPanel imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(imageLabel);
        
        options = new ButtonGroup();
        option1= new JRadioButton();
        option2= new JRadioButton();
        option3= new JRadioButton();
        option4= new JRadioButton();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        
        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);
        
        final JPanel questionPanel = new JPanel();
        questionLabel = new JLabel();
        answerLabel = new JLabel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(questionLabel, BorderLayout.PAGE_START);
        questionPanel.add(optionsPanel, BorderLayout.CENTER);
        questionPanel.add(answerLabel, BorderLayout.PAGE_END);
        
        final JPanel buttonPanel = new JPanel();
        button1 = new JButton();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(button1);

        button2 = new JButton("Answer");
        button3 = new JButton("Answer");
        button4 = new JButton("Answer");
        button5 = new JButton("Answer");
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.PAGE_START);
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        mainPanel.setPreferredSize(new Dimension(500, 400));
        
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 5) {
            set.add(getRandomInt(0, 19));
        }
        int[] numOfQuestions = new int[set.size()];
        int index = 0;
        for( Integer i : set ) {
            numOfQuestions[index++] = i;
        }
        System.out.println(Arrays.toString(numOfQuestions));
        
        File file = new File(filename);
        Scanner csvFile = null;
        try {
            csvFile = new Scanner(file);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(
                    this,
                    "File '" + filename + "' does not exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(
                    this,
                    "File '" + filename + "' was not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);        
        }
        ArrayList<String> linesList = new ArrayList<String>();
        while (csvFile.hasNext()) {
            linesList.add(csvFile.nextLine());
        }
        csvFile.close();
        String[] lines = linesList.toArray(new String[linesList.size()]);
        String[] q1values = lines[numOfQuestions[0]].split(",");
        String[] q2values = lines[numOfQuestions[1]].split(",");
        String[] q3values = lines[numOfQuestions[2]].split(",");
        String[] q4values = lines[numOfQuestions[3]].split(",");
        String[] q5values = lines[numOfQuestions[4]].split(",");
        questionOne = new Question(q1values[0], q1values[1], q1values[2], q1values[3], q1values[4], q1values[5], q1values[6]);
        questionTwo = new Question(q2values[0], q2values[1], q2values[2], q2values[3], q2values[4], q2values[5], q2values[6]);
        questionThree = new Question(q3values[0], q3values[1], q3values[2], q3values[3], q3values[4], q3values[5], q3values[6]);
        questionFour = new Question(q4values[0], q4values[1], q4values[2], q4values[3], q4values[4], q4values[5], q4values[6]);
        questionFive = new Question(q5values[0], q5values[1], q5values[2], q5values[3], q5values[4], q5values[5], q5values[6]);
        System.out.println(questionOne.getQuestion());

        questionSetter(questionOne, 1);
        button1.setText("Answer");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            	if (button1.getText().equals("Answer")) {
            		try {
	            		buttonAction(questionOne);
            		} catch (NullPointerException npe) {
            			exception();
            			buttonAction(questionOne);
            		}
	            	button1.setText("Next");
            	} else {
            		answerLabel.setText("");
            		questionSetter(questionTwo, 2);
    		        buttonPanel.remove(button1);
    		        buttonPanel.add(button2);
    		        options.clearSelection();
            	}
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            	if (button2.getText().equals("Answer")) {
            		try {
	            		buttonAction(questionTwo);
            		} catch (NullPointerException npe) {
            			exception();
            			buttonAction(questionTwo);
            		}
	            	button2.setText("Next");
            	} else {
            		answerLabel.setText("");
            		questionSetter(questionThree, 3);
    		        buttonPanel.remove(button2);
    		        buttonPanel.add(button3);
    		        options.clearSelection();
            	}
            }
        });
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            	if (button3.getText().equals("Answer")) {
            		try {
	            		buttonAction(questionThree);
            		} catch (NullPointerException npe) {
            			exception();
            			buttonAction(questionThree);
            		}
	            	button3.setText("Next");
            	} else {
            		answerLabel.setText("");
            		questionSetter(questionFour, 4);
    		        buttonPanel.remove(button3);
    		        buttonPanel.add(button4);
    		        options.clearSelection();
            	}
            }
        });
        
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            	if (button4.getText().equals("Answer")) {
            		try {
	            		buttonAction(questionFour);
            		} catch (NullPointerException npe) {
            			exception();
            			buttonAction(questionFour);
            		}
	            	button4.setText("Next");
            	} else {
            		answerLabel.setText("");
            		questionSetter(questionFive, 5);
    		        buttonPanel.remove(button4);
    		        buttonPanel.add(button5);
    		        options.clearSelection();
            	}
            }
        });
        
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            	if (button5.getText().equals("Answer")) {
            		try {
	            		buttonAction(questionFive);
            		} catch (NullPointerException npe) {
            			exception();
            			buttonAction(questionFive);
            		}
	            	button5.setText("Finish");
            	} else {
            		answerLabel.setText("");
    		        buttonPanel.remove(button5);
    		        finishWindow();
            	}
            }
        });
        
        setIconImage(new ImageIcon("utensils.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void exception() {
    	answer = "Invalid response.";
    	JOptionPane.showMessageDialog(
                this,
                "Sorry, you must select a response.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    private void finishWindow() {
    	String message = "Congratulations! You got " + count + " out of 5 questions correct!";
    	ImageIcon icon = new ImageIcon("utensils.png");
    	JOptionPane.showMessageDialog(
        this,
        message,
        "Score",
        JOptionPane.INFORMATION_MESSAGE,
        icon);
    }
    
    
    private void buttonAction(Question q) throws NullPointerException {
    	System.out.println(getSelectedButtonText(options));
    	System.out.println(q.getAnswer());
    	System.out.println(q.getQuestion());
    	q.setIsCorrect(getSelectedButtonText(options));
    	if (getSelectedButtonText(options) == null) {
        	throw new NullPointerException("Sorry, that's an invalid response. Please choose an answer.");
        }
    	if (q.getIsCorrect()) {
            answer = "Good job, your choice was correct.";
            count++;
        } else if (!q.getIsCorrect()) {
            answer = "Sorry, your choice was wrong.";
        } 
        answerLabel.setText(answer);
    }
    
    public void questionSetter(Question question, int j) {
        questionLabel.setText(j + ". " + question.getQuestion());
        option1.setText(question.getOption1());
        option2.setText(question.getOption2());
        option3.setText(question.getOption3());
        option4.setText(question.getOption4());
        imageLabel.setIcon(new ImageIcon("images" + File.separatorChar + 
        		question.getNum() + ".png"));
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public static int getRandomInt(int a, int b) {
        return (a + (int)(Math.random()*(b- a +1)));
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
        FoodQuiz food = new FoodQuiz("Book1.csv");
        
    }
}
