import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTests {
	
	/*
	 ********************************************
	 * Testing refactored vs original toString()
	 ********************************************
	 */
	
	// Make sure that setting a cell dead behaves the same
	@Test
	public void toStringDeadTest() {
		Cell c = new Cell();
		c.setAlive(false);
		assertEquals(c.oldToString(), c.toString());
	}
	
	// Make sure that setting a cell alive behaves the same
	@Test
	public void toStringAliveTest() {
		Cell c = new Cell();
		c.setAlive(true);
		assertEquals(c.oldToString(), c.toString());
	}
	
	// Make sure that setting multiple cells works;
	// This case, testing 3x3 grid of Cells
	@Test
	public void toStringMultipleCellTest() {
		MainPanel main_panel = new MainPanel(3);
		
		// set [1,1],[2,3], and [3,2] to alive; all else dead
		main_panel._cells[0][0].setAlive(true);
		main_panel._cells[1][2].setAlive(true);
		main_panel._cells[2][1].setAlive(true);
		
		// make sure layout of every cell is exactly correct for both old and new versions
		Cell curCell = new Cell();
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				curCell = main_panel._cells[row][col];
				if(row == 0 && col == 0) {
					assertEquals("X", curCell.toString());
					assertEquals("X", curCell.oldToString());
				}
				else if(row == 1 && col == 2) {
					assertEquals("X", curCell.toString());
					assertEquals("X", curCell.oldToString());
				}
				else if(row == 2 && col == 1) {
					assertEquals("X", curCell.toString());
					assertEquals("X", curCell.oldToString());
				}
				else {
					assertEquals(".", curCell.toString());
					assertEquals(".", curCell.oldToString());
				}
				
				// old and new should also be the same as each other
				assertEquals(curCell.oldToString(), curCell.toString());
			}
		}
	}
}



























