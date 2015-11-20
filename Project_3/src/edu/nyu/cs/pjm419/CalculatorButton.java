package edu.nyu.cs.pjm419;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 * CalculatorButton Class which extends Java Swing's
 * JButton class to add enhanced functionality's pertaining
 * to the JCalculator GUI. 
 * 
 * This class is meant to represent a calculator button object,
 * which must display a character to identify the button on the 
 * GUI, but must also store a reference to that value to allow
 * for storing and evaluating the infix expression created by the
 * user. These buttons are "clickable", but also one can use the 
 * keyboard to reference certain buttons. See JCaculator for the
 * key mapping.
 *   
 * @author  Peter Mountanos (pjm419)
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CalculatorButton extends JButton {
	
	/**
	 * String storing the value to be displayed on the button, but 
	 * also its computational value in regards to an infix expression
	 */
	private String value;
	/**
	 * Reference to its parent, i.e., where the button is actually displayed.
	 * This is necessary in order to set a proper action listener to the button
	 * on the JCalculator GUI, while still being able to abstract this object away
	 * into its own class
	 */
	private JCalculator parent;
	
	/**
	 * Constructor for an object of type CalculatorButton.
	 * 
	 * This constructor instantiates a child of JButton, which allows one to
	 * to set default values to a button for the JCalculator GUI, as well as
	 * store essential information 
	 * 
	 * @param value reference to the display value of the button, as well as 
	 *        its computational value in regards to an infix expression
	 * @param parent reference to a JFrame (or one of its child classes) to
	 *        represent where the button is being displayed on a GUI
	 */
	public CalculatorButton(String value, JCalculator parent) {
		super(value); // call parent's constructor
		this.value  = value;
		this.parent = parent;

		// set default GUI properties
		this.setPreferredSize(new Dimension(50, 50));
		this.setActionCommand(this.value+"_clicked"); // makes it 'clickable'
		this.addActionListener(this.parent); // JCalculator should be listening for a click
		this.addKeyListener(this.parent); // JCalculator should be listening for a keystroke
		this.setFocusable(false);
	}
	
	/**
	 * Getter for value data field. This method returns the reference to
	 * this buttons value. It is used to get the computational value of the
	 * buttons for evaluating infix expressions.
	 * 
	 * @return the value of the button as a string
	 */
	public String getValue() {
		return this.value;
	}
}
