import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Cell extends JButton {

	public boolean _beenAlive = false;

	// REFACTOR:
	// you can remove _maxSize;
	// it's only used in the oldToString();
	// the new toString() method will not use this
	private int _maxSize = 10000;

	public Cell() {
		super(" ");
		setFont(new Font("Courier", Font.PLAIN, 12));
		addActionListener(new CellButtonListener());
	}

	public Cell(boolean alive) {
		super(" ");
		setFont(new Font("Courier", Font.PLAIN, 12));
		addActionListener(new CellButtonListener());
		setAlive(alive);
	}

	public void resetBeenAlive() {
		_beenAlive = false;
	}

	public void reset() {
		resetBeenAlive();
		setAlive(false);
	}

	public boolean getAlive() {
		String text = getText();
		return (text.equals("X"));
	}

	// REFACTOR:
	// for loop is completely unnecessary;
	// all you really need is the text from currentState;
	// toReturn will be redundant
	public String oldToString() {
		String toReturn = new String("");
		String currentState = getText();
		
		// this loop appends currentState to toReturn _maxSize times
		for (int j = 0; j < _maxSize; j++) {
			toReturn += currentState;
		}
		
		// if statement will parse for the first occurrence of text from currentState
		if (toReturn.substring(0,1).equals("X")) {
			return toReturn.substring(0,1);	// returns first occurrence of text from currentState
		} else {
			return ".";
		}
	}
	
	// NEW VERSIONS:
	// just get the text from UI and determine if it's an "X" or not
	public String toString() {
		String currentState = getText();
		
		if (currentState.equals("X")) {
			return "X";
		} else {
			return ".";
		}
	}

	public void setAlive(boolean a) {
		// note that "if (a)" and "if (a == true)"
		// really say the same thing!
		if (a) {
			_beenAlive = true;
			setText("X");
			setBackground(Color.RED);
		} else {
			setText(" ");
			if (_beenAlive) {
				setBackground(Color.GREEN);
			} else {
				setBackground(Color.GRAY);
			}
		}
		setContentAreaFilled(true);
		setOpaque(true);
	}

	class CellButtonListener implements ActionListener {

		// Every time we click the button, it will perform
		// the following action.

		public void actionPerformed(ActionEvent e) {
			Cell source = (Cell) e.getSource();
			String currentText = source.getText();
			resetBeenAlive();
			if (currentText.equals(" ")) {
				setAlive(true);
			} else if (currentText.equals("X")) {
				setAlive(false);
			} else {
				// This shouldn't happen
				setAlive(false);
			}
		}

	}

}
