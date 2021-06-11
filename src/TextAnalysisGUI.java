import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class to provide the front end UI for the TextAnalyzer class
 *
 * @author Bhavyai Gupta
 * @version 1.0
 * @since June 11, 2021
 */
public class TextAnalysisGUI extends JFrame implements ActionListener {
    /**
     * Text area to get input from the user
     */
    private JTextArea userInputArea;

    /**
     * Area to print the output of TextAnalyzer after analyzing the text
     */
    private JTextArea outputArea;

    /**
     * Reset button to reset the user input and the output
     */
    private JButton resetButton;

    /**
     * Analyze button to run the analysis and display to the user
     */
    private JButton analyzeButton;

    /**
     * Object of type TextAnalyzer to call associated methods for text analysis
     */
    private TextAnalyzer textAnalyzer;

    /**
     * String to hold the placeholder text in userInputArea
     */
    private String inputPrompt;

    /**
     * String to hold the prompt for output
     */
    private String outputPrompt;

    /**
     * Constructs the GUI interface to interact with the user
     *
     * @param title The title to show on the title bar of the windows
     */
    public TextAnalysisGUI(String title) {
        this.inputPrompt = "Please enter some text (or copy paste) in this box";
        this.outputPrompt = "Stats will be shown here";

        // define the JTextAreas
        this.userInputArea = new JTextArea(this.inputPrompt, 15, 100);
        this.outputArea = new JTextArea(this.outputPrompt, 5, 100);

        // make stats display area distinguishable from the user input area
        this.outputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // prevent input into the stats display area,
        // but using this property faints the color making text illegible
        // this.outputArea.setEnabled(false);

        this.outputArea.setForeground(Color.BLUE);

        // define the JButton
        this.resetButton = new JButton("RESET");
        this.analyzeButton = new JButton("ANALYZE");

        // add ActionListener to the buttons
        this.resetButton.addActionListener(this);
        this.analyzeButton.addActionListener(this);

        // creating a JPanel for holding buttons together
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));
        buttonPanel.add(this.analyzeButton);
        buttonPanel.add(this.resetButton);

        // creating a ContentPane for holding all elements together
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add("North", this.userInputArea);
        contentPane.add("Center", this.outputArea);
        contentPane.add("South", buttonPanel);

        setSize(600, 400);
        setTitle(title);
        setVisible(true);
    }

    /**
     * Catches the events by the ActionListener as a result of user interaction with
     * the GUI interface
     *
     * @param e The action caught by the GUI
     */
    public void actionPerformed(ActionEvent e) {
        // if user presses the ANALYZE button
        if (e.getSource() == analyzeButton) {
            String userInput = this.userInputArea.getText();

            this.textAnalyzer = new TextAnalyzer(userInput);
            this.outputArea.setText(this.textAnalyzer.toString());
        }

        // if user presses the RESET button
        else if (e.getSource() == resetButton) {
            this.userInputArea.setText(this.inputPrompt);
            this.outputArea.setText(this.outputPrompt);
        }
    }

    /**
     * Method to create the GUI by calling the constructor TextAnalysisGUI
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new TextAnalysisGUI("Text Analysis");
    }
}
