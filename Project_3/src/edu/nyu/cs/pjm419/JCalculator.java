package edu.nyu.cs.pjm419;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * JCalculator Class which is the GUI class for the RPN 
 * Calculator. This class extends Swing's JFrame class, and
 * implements the ActionListener and KeyListener interfaces.
 * 
 * This class contains several panels, which contain a label
 * to display the expression, and buttons which represent different
 * numbers and mathematical operators and symbols. These buttons are
 * all "clickable", but this calculator also responds to key strokes.
 * The key mapping is shown below.
 * 
 * Key Mapping
 * - 0 - 9       	   : corresponding numerical digits
 * - "="		 	   : computes and displays the result of the infix exp
 * - "*" (Shift + 8)   : appends a multiplication operator to infix exp
 * - "/" 			   : append a division operator to infix exp
 * - "+" (Shift + "=") : appends an addition operator to infix exp
 * - "-"			   : appends a subtraction operator to infix exp
 * - "(" (Shift + 9)   : appends a left parenthesis to the infix exp
 * - ")" (Shift + 0)   : appends a right parenthesis to the infix exp
 * - *Space*	 	   : enters a single whitespace character
 * - *Backspace* 	   : removes a single digit/operator plus whitespace
 * - *Enter*	 	   : equivalent to "=" key
 * - *Esc*			   : quits the program with exit code 0
 * - "c" or "C"		   : clears the label and resets the infix exp to ""
 * 
 * Note the corresponding buttons on the GUI perform the same actions
 *  
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
@SuppressWarnings("serial")
public class JCalculator extends JFrame implements ActionListener, KeyListener {

	// instance variables 
	/**
	 * CalculatorLabel object which represents the top label
	 * on the GUI which displays the current expression 
	 */
	private CalculatorLabel calcLabel;
	/**
	 * An array of CalculatorButtons objects, which stores
	 * references to all of the buttons on the GUI. This is
	 * used to facilitate checking which button was clicked
	 */
	private CalculatorButton[] buttons;
	
	// class constants
	/**
	 * An array storing the location and values so each
	 * button can be correctly mapped
	 */
	public static final String[] values = {
		
			"clear", "(", ")", "/",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"3", "2", "1", "+",
			   "0"  ,    "="
			
	};
	
	/**
	 * An array storing the allowable symbols to be 
	 * typed and accepted by the GUI as input
	 */
	public static final String SYMBOLS = "()+-*/=";
	
	/**
	 * Main method which instantiates the JCalculator GUI object, and
	 * starts the program. This GUI frame is a set size, and cannot be
	 * expanded.
	 * 
	 * @param args this program takes no command line input
	 */
	public static void main(String[] args) {
		JCalculator dateGUI = new JCalculator("Calculator"); // create frame instance
		dateGUI.setVisible(true); // make it show!
		dateGUI.setResizable(false);// don't let it be resized ever...
	}

	/**
	 * GUI Constructor, which allows for one to instantiate a
	 * JCalculator object. This class extends JFrame, and calls a 
	 * helper method to set up the GUI.
	 * @param title
	 */
	public JCalculator(String title){
		super(title); // call super's constructor
		// number of buttons is equal to number of objects in values array
		this.buttons = new CalculatorButton[values.length];
		createGUI(); // call helper method to set up gui application components
	}

	/**
	 * Method responsible for setting up all of the GUI components for 
	 * the JCalculator class. It sets up three main panels for the calculator,
	 * where the top panel is the expression label, the middle panel contains 
	 * all of the buttons except the 0 and =, and the bottom panel contains the
	 * 0 and = button. This is so they properly space across the whole bottom row
	 * to make the GUI more aesthetically pleasing.
	 */
	public void createGUI() {

		// set up main panels
		this.setLocationRelativeTo(null); // center it
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit when closed
		this.setLayout(new BorderLayout()); // set layout as a border layout (directional)
		JPanel topPanel = new JPanel(); // make an instance of a JPanel to be the top panel
		JPanel mainPanel = new JPanel(); // make an instance of a JPanel to be the main panel
		JPanel bottomPanel = new JPanel(); // make an instance of a JPanel to be the bottom panel
		topPanel.setLayout(new BorderLayout()); // set top panel as border layout (only one component)
		mainPanel.setLayout(new GridLayout(4,4)); // set main panel as grid layout (4 rows, 4 col)
		bottomPanel.setLayout(new GridLayout(1,2)); // set bottom panel as grid layout (1 row, 2 cols)

		// set up components of the top panel
		calcLabel = new CalculatorLabel(); // set to blank
		calcLabel.setPreferredSize(new Dimension(300, 60)); // set size
		calcLabel.setHorizontalAlignment(JLabel.CENTER); // center the label
		topPanel.add(calcLabel,BorderLayout.NORTH); // add label to the panel

		// set up components of the main and bottom panels
		// the bottom panel contains the 0 and = buttons
		for (int i = 0; i < values.length; i++) {
			buttons[i] = new CalculatorButton(values[i], this);
			// add all but last two buttons to main panel
			if (i < values.length - 2)
				mainPanel.add(buttons[i]);
			else 
				bottomPanel.add(buttons[i]);
		}
		
		this.addKeyListener(this); // make GUI "listen" for keystrokes
		
		// set = button to default enter key
		JRootPane rootPane = this.getRootPane();
		rootPane.setDefaultButton(buttons[buttons.length-1]);
		
		// add panels to the frame and fit it all to the frame appropriately
		this.getContentPane().add(topPanel, BorderLayout.NORTH); //add top panel to frame
		this.getContentPane().add(mainPanel, BorderLayout.CENTER); //add main panel to frame
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH); //add bottom panel to frame
		this.pack(); // pack it all together!
	}

	/**
	 * This method is used to determine if any buttons on the GUI were
	 * clicked. If a button was clicked, it gets the computation value
	 * of the button (CalculatorButton value data field), and appends it
	 * to the infix expression stored in the calcLabel field.
	 * 
	 * @param e ActionEvent which stores a reference to which button was clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// for each button in the gui
		for (CalculatorButton b : buttons) {
			// if the passed in action event is the same as the buttons
			// action command, get the buttons value, and add it to the
			// infix expression
			if (e.getActionCommand().equals(b.getActionCommand())) {
				calcLabel.appendText(b.getValue());
			}
		}
	}
	
	/**
	 * This method is used to determine which key was typed
	 * on the JCalculator GUI, and calls appropriate CalculatorLabel
	 * method with the correct parameter based on the keystroke. See
	 * the key map in the class JavaDoc for further information.
	 *  
	 * @param e KeyEvent object storing a reference to which
	 * 			key was typed
	 * @see KeyListener#keyTypes(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
		// get keystroke in char and string format
		char keyChar = e.getKeyChar();
		String key   = Character.toString(keyChar).toLowerCase();
		
		// check which key was pressed, and do the corresponding action...
		if (key.equals("c")) {
			calcLabel.appendText("clear");
		}
		else if (SYMBOLS.indexOf(key) != -1 || ExpressionTools.isNumber(key)) {
			calcLabel.appendText(key);
		}
		else if (keyChar == KeyEvent.VK_ENTER){
			calcLabel.appendText("=");
		}
		else if (keyChar == KeyEvent.VK_BACK_SPACE){
			calcLabel.removeOne();
		}
		else if (keyChar == KeyEvent.VK_SPACE) {
			calcLabel.appendText(" "); 
		} 
		else if (keyChar == KeyEvent.VK_ESCAPE) {
			System.exit(0); 
		}
	}

	/* (non-Javadoc)
	 * @see KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * 
	 * Needs to be overrided because it is part of the
	 * KeyListener interface, but it is not applicable
	 * to this application
	 */
	@Override
	public void keyPressed(KeyEvent e) { return;}

	/* (non-Javadoc)
	 * @see KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * 
	 * Needs to be overrided because it is part of the 
	 * KeyListener interface, but it is not applicable
	 * to this application
	 */
	@Override
	public void keyReleased(KeyEvent e) { return; }
}
