import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MainPanel extends JPanel {

	// Current configuration; changed to public for testing
	public Cell[][] _cells;

	// Backup configuration
	private Cell[][] _backupCells;

	private int _size = 0;

	// ORIGINAL:
	// can remove _maxCount because it is only used is old versions
	// and not used anywhere else in MainPanel;
	// left here to test the old versions;
	// after refactor, new runContinuous() and convertToInt() will not use it 
	private int _maxCount = 10000;

	// ORIGINAL:
	// can remove _r because it is used only in old versions
	// and not used anywhere else in this project;
	// left here to test the old versions;
	// after refactor, new runContinuous() and convertToInt() will not use it
	public int _r = 1000;

	private boolean _running = false;

	public int getCellsSize() {
		return _size;
	}

	public void setCells(Cell[][] cells) {
		_cells = cells;
	}

	public Cell[][] getCells() {
		return _cells;
	}

	// ORIGINAL:
	// convertToInt() can be removed as converting and int to an int is redundant;
	// while loop here is extraneous as padding is parsed to "0" anyways;
	// n is just q = (padding + x) = (0 + x) = x or q = x -> output is the same as input
	private int convertToInt(int x) {
		int c = 0;
		String padding = "0";
		while (c < _r) {
			String l = new String("0");
			padding += l;
			c++;
		}

		String n = padding + String.valueOf(x);
		int q = Integer.parseInt(n);
		return q;
	}

	// ORIGINAL:
	// convertToInt() method call is redundant because numNeighbors is already and int
	public int oldGetNumNeighbors(int x, int y) {
		int size = _size;
		int leftX = (x - 1) % size;
		int rightX = (x + 1) % size;
		int upY = (y - 1) % size;
		int downY = (y + 1) % size;

		if (leftX == -1) { leftX = size - 1; }
		if (rightX == -1) { rightX = size - 1; }
		if (upY == -1) { upY = size - 1; }
		if (downY == -1) { downY = size - 1; }

		int numNeighbors = 0;

		if (_cells[leftX][upY].getAlive())    { numNeighbors++; }
		if (_cells[leftX][downY].getAlive())  { numNeighbors++; }
		if (_cells[leftX][y].getAlive())      { numNeighbors++; }
		if (_cells[rightX][upY].getAlive())   { numNeighbors++; }
		if (_cells[rightX][downY].getAlive()) { numNeighbors++; }
		if (_cells[rightX][y].getAlive())     { numNeighbors++; }
		if (_cells[x][upY].getAlive())        { numNeighbors++; }
		if (_cells[x][downY].getAlive())      { numNeighbors++; }

		return convertToInt(numNeighbors);
	}
	
	// NEW VERSION:
	// does not use convertToInt() at all;
	// simply returns numNeighbors already calculated in this method
	public int getNumNeighbors(int x, int y) {
		int size = _size;
		int leftX = (x - 1) % size;
		int rightX = (x + 1) % size;
		int upY = (y - 1) % size;
		int downY = (y + 1) % size;

		if (leftX == -1) { leftX = size - 1; }
		if (rightX == -1) { rightX = size - 1; }
		if (upY == -1) { upY = size - 1; }
		if (downY == -1) { downY = size - 1; }

		int numNeighbors = 0;

		if (_cells[leftX][upY].getAlive())    { numNeighbors++; }
		if (_cells[leftX][downY].getAlive())  { numNeighbors++; }
		if (_cells[leftX][y].getAlive())      { numNeighbors++; }
		if (_cells[rightX][upY].getAlive())   { numNeighbors++; }
		if (_cells[rightX][downY].getAlive()) { numNeighbors++; }
		if (_cells[rightX][y].getAlive())     { numNeighbors++; }
		if (_cells[x][upY].getAlive())        { numNeighbors++; }
		if (_cells[x][downY].getAlive())      { numNeighbors++; }

		return numNeighbors;
	}

	private boolean iterateCell(int x, int y) {
		boolean toReturn = false;
		boolean alive = _cells[x][y].getAlive();
		int numNeighbors = getNumNeighbors(x, y);
		if (alive) {
			if (numNeighbors < 2 || numNeighbors > 3) {
				toReturn = false;
			} else {
				toReturn = true;
			}
		} else {
			if (numNeighbors == 3) {
				toReturn = true;
			} else {
				toReturn = false;
			}
		}
		return toReturn;

	}

	private void displayIteration(boolean[][] nextIter) {
		System.out.println("\tDisplaying...");
		for (int j = 0; j < _size; j++) {
			for (int k = 0; k < _size;  k++) {
				_cells[j][k].setAlive(nextIter[j][k]);
			}
		}
		setVisible(true);
	}

	/**
	 * For each of the cells, calculate what their
	 * state will be for the next iteration.
	 */

	private void calculateNextIteration() {
		System.out.println("\tCalculating..");
		boolean[][] nextIter = new boolean[_size][_size];
		for (int j = 0; j < _size; j++) {
			for (int k = 0; k < _size; k++) {
				nextIter[j][k] = iterateCell(j, k);
			}
		}

		displayIteration(nextIter);
	}

	/**
	 * Make a copy of the current cells and put
	 * the copy in the backup cells.
	 */

	public void backup() {
		_backupCells = new Cell[_size][_size];
		for (int j = 0; j < _size; j++) {
			for (int k = 0; k < _size; k++) {
				_backupCells[j][k] = new Cell();
				_backupCells[j][k].setAlive(_cells[j][k].getAlive());
			}
		}
	}

	/**
	 * This is for debug use.  It will display
	 * the state of cells in a convenient format.
	 * First it will display backup cells and then
	 * the current cells.  Backup cells are what
	 * you revert to when you press Undo.
	 */

	public void debugPrint() {
		System.out.println("Backup cells");

		try {
			for (int j = 0; j < _size; j++) {
				for (int k = 0; k < _size; k++) {

					if (_backupCells[j][k].getAlive()) {
						System.out.print("X");
					} else {
						System.out.print(".");
					}
				}
				System.out.println("");
			}

			System.out.println("Current cells:");

			for (int j = 0; j < _size; j++) {
				for (int k = 0; k < _size; k++) {

					if (_cells[j][k].getAlive()) {
						System.out.print("X");
					} else {
						System.out.print(".");
					}
				}
				System.out.println("");
			}
		} catch (Exception ex) {
			System.out.println("Nothin' yet");
		}


	}

	/**
	 * Convert the Main Panel into a String
	 * which can be written to a file.
	 */

	public String toString() {

		// Loop through all of the cells, and
		// if they are alive, add an "X" to
		// the String, if dead, a ".".

		String toWrite = "";

		for (int j = 0; j < _size; j++) {
			for(int k = 0; k < _size; k++) {
				if (_cells[j][k].getAlive()) {
					toWrite += _cells[j][k].toString();
				} else {
					toWrite += _cells[j][k].toString();
				}

			}
			toWrite += "\n";
		}
		return toWrite;
	}

	/**
	 * Run one iteration of the Game of Life
	 */

	public void run() {
		backup();
		calculateNextIteration();
	}

	/**
	 * Run the system continuously.
	 */

	// ORIGINAL:
	// for loop is redundant;
	// origR is not manipulated, when _r is in manipulated in the loop;
	// _r is set back to origR, anyways at the end;
	// so all this for loop does is loop 10,000 times setting _r back to its original value
	public void oldRunContinuous() {
		_running = true;
		while (_running) {
			System.out.println("Running...");
			int origR = _r;
			try {
				Thread.sleep(20);
			} catch (InterruptedException iex) { }
			for (int j=0; j < _maxCount; j++) {
				_r += (j % _size) % _maxCount;
				_r += _maxCount;
			}
			_r = origR;
			backup();
			calculateNextIteration();
		}
	}
	
	// NEW VERSION:
	// for loop, _r, _maxCount, and origR are not used here
	public void runContinuous() {
		_running = true;
		while (_running) {
			System.out.println("Running...");
			try {
				Thread.sleep(20);
			} catch (InterruptedException iex) { }
			backup();
			calculateNextIteration();
		}
	}

	/**
	 * Stop a continuously running system.
	 */

	public void stop() {
		_running = false;
	}


	/**
	 * Convert the array of Cell objects into an 
	 * array of booleans.
	 */

	public boolean[][] convertToBoolean(Cell[][] cells) {

		// 2-D array to return.  Remember everything
		// is false by default for boolean arrays!

		boolean[][] toReturn = new boolean[_size][_size];

		for (int j = 0; j < _size; j++) {
			for (int k = 0; k < _size; k++) {
				if (cells[j][k].getAlive()) {
					toReturn[j][k] = true;
				} else {
					// Nothing to do!  Already
					// set to false by default.
					// toReturn[j][k] = false;
				}
			}
		}
		return toReturn;

	}

	/**
	 * Revert back to the previous iteration,
	 * which we have saved in _backupCells.
	 */

	public void undo() {
		displayIteration(convertToBoolean(_backupCells));
	}

	/**
	 * Loop through the entire array and reset
	 * each of the Cells in the MainPanel.
	 */

	public void clear() {
		for (int j = 0; j < _size; j++) {
			for (int k = 0; k < _size; k++) {
				_cells[j][k].reset();
			}
		}
		// Need to call setVisible() since
		// we did not do a displayIteration()
		// call.
		setVisible(true);
	}

	/**
	 * Load in a previously saved Game of Life
	 * configuration.
	 */

	public void load(ArrayList<String> lines) {
		boolean[][] loaded = new boolean[_size][_size];


		for (int j = 0; j < _size; j++) {
			String l = lines.get(j);
			for (int k = 0; k < _size; k++) {

				// Reset the "been alive" count
				_cells[j][k].resetBeenAlive();

				// For each line, get each character.
				// If it's a '.', the cell stays
				// dead.  Otherwise, the cell is alive.
				// We could specifically check for
				// an 'X' for alive and throw an
				// error if we get an unexpected char.
				if (l.charAt(k) == '.') {		    
					_cells[j][k].setAlive(false);
					loaded[j][k] = false;
				} else {
					_cells[j][k].setAlive(true);
					loaded[j][k] = true;
				}
			}
		}

		// Now that we have set the Cells to what
		// we expect, display the iteration.
		displayIteration(loaded);
		// debugPrint();

	}


	public MainPanel(int size) {
		super();
		_size = size;
		setLayout(new GridLayout(size, size));
		_cells = new Cell[size][size];
		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				_cells[j][k] = new Cell();
				this.add(_cells[j][k]);
				_cells[j][k].setAlive(false);
			}
		}
	}

}
