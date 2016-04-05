import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class MainPanelTests {

	MainPanel main_panel = new MainPanel(9);	// 9x9 grid

	/*
	 ***************************************************
	 * Testing refactored vs original getNumNeighbors()
	 ***************************************************
	 */

	// get number of living neighbors in an empty map; all cells are dead
	@Test
	public void numNeighborsIfAllDeadTest() {
		int new_method;
		int old_method;

		// checking every cell; every cell should be dead by default upon initialization
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				new_method = main_panel.getNumNeighbors(row, col);
				old_method = main_panel.oldGetNumNeighbors(row, col);
				
				assertEquals(0, new_method);
				assertEquals(0, old_method);
				assertEquals(old_method, new_method);
			}
		}
	}
	
	// get number of living neighbors in a full map; all cells are alive
	@Test
	public void numNeighborsIfAllAliveTest() {
		int new_method;
		int old_method;
		
		// set all cells to alive first
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				main_panel._cells[i][j].setAlive(true);
			}
		}

		// checking every cell
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				new_method = main_panel.getNumNeighbors(row, col);
				old_method = main_panel.oldGetNumNeighbors(row, col);

				// all cells should have 8 neighbors;
				// the map wraps around
				assertEquals(8, new_method);
				assertEquals(8, old_method);
				assertEquals(old_method, new_method);
			}
		}
	}
	
	// get number of living neighbors on a map with just one live cell at [0,8];
	// tests for wrapping around
	@Test
	public void neighborsOneLiveCellTest() {
		int new_method;
		int old_method;
		
		// set all cells to alive first
		main_panel._cells[0][8].setAlive(true);

		// checking every cell
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				new_method = main_panel.getNumNeighbors(row, col);
				old_method = main_panel.oldGetNumNeighbors(row, col);

				// only cells adjacent to [0,8] should detect a live neighbor
				if(row == 0) {
					if(col == 7 || col == 0) {
						assertEquals(1, new_method);
						assertEquals(1, old_method);
						assertEquals(old_method, new_method);
					}
					else {
						assertEquals(0, new_method);
						assertEquals(0, old_method);
						assertEquals(old_method, new_method);
					}
				}
				else if(row == 1 || row == 8) {
					if(col == 7 || col == 8 || col == 0) {
						assertEquals(1, new_method);
						assertEquals(1, old_method);
						assertEquals(old_method, new_method);
					}
					else {
						assertEquals(0, new_method);
						assertEquals(0, old_method);
						assertEquals(old_method, new_method);
					}
				}
				else {
					assertEquals(0, new_method);
					assertEquals(0, old_method);
					assertEquals(old_method, new_method);
				}
			}
		}
	}


	/*
	 ******************************************
	 * Testing original vs new runContinuous()
	 ******************************************
	 */
	
	///////////////////////////////////////////////
	// Specific setup steps for runContinuous tests
	MainPanel mp_old = new MainPanel(9);	// 9x9 map
	MainPanel mp_new = new MainPanel(9);	// 9x9 map
	
	class OldRunnable implements Runnable {
		public void run() {
			mp_old.runContinuous();
		}
	}

	class NewRunnable implements Runnable {
		public void run() {
			mp_new.oldRunContinuous();
		}
	}
	///////////////////////////////////////////////

	@Before
	public void setup() {
		main_panel.clear();
	}	
	
	// run continuously on a map that has no living cells;
	// should result in an empty map at the end; all cells are dead
	@Test
	public void runContinuousEmptyTest() throws InterruptedException {
		// clear both maps
		mp_old.clear();
		mp_new.clear();
		
		// start a game to generate a new thread to run continuously original method
		(new Thread(new OldRunnable())).start();
		
		Thread.sleep(2000);	// sleep for 2 seconds; shouldn't need much time
		
		mp_old.stop();
		
		// start a game to generate a new thread to run continuously new method
		(new Thread(new NewRunnable())).start();
		
		Thread.sleep(2000);	// sleep for 2 seconds; shouldn't need much time
		
		mp_new.stop();
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				// toString should return dead (".")
				assertEquals(".", mp_new._cells[row][col].toString());
				assertEquals(".", mp_old._cells[row][col].toString());
				assertEquals(mp_old._cells[row][col].toString(), mp_new._cells[row][col].toString());
				
				// cell color should be gray
				assertEquals(Color.GRAY, mp_new._cells[row][col].getBackground());
				assertEquals(Color.GRAY, mp_old._cells[row][col].getBackground());
			}
		}
	}
	
	// run continuously on a map that has only living cells;
	// should result in an empty map at the end; all cells are dead
	@Test
	public void runContinuousFullTest() throws InterruptedException {
		// clear both maps
		mp_old.clear();
		mp_new.clear();
		
		// change all cells to living
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				mp_old._cells[row][col].setAlive(true);
				mp_new._cells[row][col].setAlive(true);
			}
		}
		
		// start a game to generate a new thread to run continuously original method
		(new Thread(new OldRunnable())).start();
		
		// start a game to generate a new thread to run continuously new method
		(new Thread(new NewRunnable())).start();
		
		Thread.sleep(5000);	// sleep for 5 seconds
		
		// stop threads
		mp_old.stop();
		mp_new.stop();
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				// toString should return dead (".")
				assertEquals(".", mp_new._cells[row][col].toString());
				assertEquals(".", mp_old._cells[row][col].toString());
				assertEquals(mp_old._cells[row][col].toString(), mp_new._cells[row][col].toString());
				
				// cell colors should be green; enough time for all cells to die
				assertEquals(Color.GREEN, mp_new._cells[row][col].getBackground());
				assertEquals(Color.GREEN, mp_old._cells[row][col].getBackground());
			}
		}
	}
	
	// Setup for next test
	MainPanel oldmap = new MainPanel(5);	// 5x5 map
	MainPanel newmap = new MainPanel(5);	// 5x5 map
	
	class OldPatternRunnable implements Runnable {
		public void run() {
			oldmap.runContinuous();
		}
	}

	class NewPatternRunnable implements Runnable {
		public void run() {
			newmap.oldRunContinuous();
		}
	}
	
	// run continuously on a map that has a specific pattern of cells;
	// should result in a map that has all three colors at the end (gray, green, red)
	// testing on a 5x5 map for ease
	@Test
	public void runPattern() throws InterruptedException {
		// clear both maps
		oldmap.clear();
		newmap.clear();

		// create pattern in the 5x5 grid
		oldmap._cells[0][0].setAlive(true);
		oldmap._cells[4][4].setAlive(true);
		oldmap._cells[1][2].setAlive(true);
		oldmap._cells[2][1].setAlive(true);
		oldmap._cells[2][3].setAlive(true);
		oldmap._cells[3][2].setAlive(true);
		
		newmap._cells[0][0].setAlive(true);
		newmap._cells[4][4].setAlive(true);
		newmap._cells[1][2].setAlive(true);
		newmap._cells[2][1].setAlive(true);
		newmap._cells[2][3].setAlive(true);
		newmap._cells[3][2].setAlive(true);

		// start a game to generate a new thread to run continuously original method
		(new Thread(new OldPatternRunnable())).start();

		// start a game to generate a new thread to run continuously new method
		(new Thread(new NewPatternRunnable())).start();

		Thread.sleep(5000);	// sleep for 5 seconds

		// stop threads
		oldmap.stop();
		newmap.stop();

		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				if((r == 0 && c == 0) || (r == 4 && c == 4)) {
					// toString should return dead (".")
					assertEquals(".", oldmap._cells[r][c].toString());
					assertEquals(".", newmap._cells[r][c].toString());
					assertEquals(oldmap._cells[r][c].toString(), newmap._cells[r][c].toString());

					// cell colors should be green; enough time for all cells to die
					assertEquals(Color.GREEN, oldmap._cells[r][c].getBackground());
					assertEquals(Color.GREEN, newmap._cells[r][c].getBackground());
				}
				else if(c == 1 && (r == 1 || r == 2)) {
					// toString should return alive ("X"); cells are in a steady state
					assertEquals("X", oldmap._cells[r][c].toString());
					assertEquals("X", newmap._cells[r][c].toString());
					assertEquals(oldmap._cells[r][c].toString(), newmap._cells[r][c].toString());

					// cell colors should be red
					assertEquals(Color.RED, oldmap._cells[r][c].getBackground());
					assertEquals(Color.RED, newmap._cells[r][c].getBackground());
				}
				else if(c == 2 && (r == 1 || r == 3)) {
					// toString should return alive ("X"); cells are in a steady state
					assertEquals("X", oldmap._cells[r][c].toString());
					assertEquals("X", newmap._cells[r][c].toString());
					assertEquals(oldmap._cells[r][c].toString(), newmap._cells[r][c].toString());

					// cell colors should be red
					assertEquals(Color.RED, oldmap._cells[r][c].getBackground());
					assertEquals(Color.RED, newmap._cells[r][c].getBackground());
				}
				else if(c == 3 && (r == 2 || r == 3)) {
					// toString should return alive ("X"); cells are in a steady state
					assertEquals("X", oldmap._cells[r][c].toString());
					assertEquals("X", newmap._cells[r][c].toString());
					assertEquals(oldmap._cells[r][c].toString(), newmap._cells[r][c].toString());

					// cell colors should be red
					assertEquals(Color.RED, oldmap._cells[r][c].getBackground());
					assertEquals(Color.RED, newmap._cells[r][c].getBackground());
				}
				else {
					// toString should return dead (".")
					assertEquals(".", oldmap._cells[r][c].toString());
					assertEquals(".", newmap._cells[r][c].toString());
					assertEquals(oldmap._cells[r][c].toString(), newmap._cells[r][c].toString());

					// cell colors should be gray
					assertEquals(Color.GRAY, oldmap._cells[r][c].getBackground());
					assertEquals(Color.GRAY, newmap._cells[r][c].getBackground());
				}
			}
		}
	}
}






























