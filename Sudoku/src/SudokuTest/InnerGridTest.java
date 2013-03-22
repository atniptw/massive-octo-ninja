package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.InnerGrid;
import Sudoku.InnerGrid;

public class InnerGridTest {

	@Test
	public void testInnerGridInitializes() {
		assertNotNull(new InnerGrid(new ArrayList<CellBlock>()));
	}

	@Test
	public void testInnerGridAcceptsFilledGrid() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		ArrayList<CellBlock> gridList = grid.getGridList();
		for (int i = 0; i < 9; i++) {
			assertEquals(i + 1, gridList.get(i).getAnswer());
		}
	}

	@Test
	public void testInnerGridRecognizesValidGrid() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		assertTrue(grid.isValidGrid());
	}

	@Test
	public void testInnerGridRecognizesInvalidGrid() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer((i + 1) % 4);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		assertFalse(grid.isValidGrid());
	}

	@Test
	public void testGetConflictingCells1() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c2);
		InnerGrid grid = new InnerGrid(cellList);
		ArrayList<CellBlock> conflicts = grid.getConflictingCells();
		assertTrue(conflicts.size() == 2);
		assertTrue(conflicts.get(0).equals(c1));
		assertTrue(conflicts.get(1).equals(c2));

	}

	@Test
	public void testGetConflictingCells2() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c4);
		CellBlock c5 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c5);
		CellBlock c6 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c6);
		CellBlock c7 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c7);
		CellBlock c8 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c8);
		CellBlock c9 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c9);
		InnerGrid grid = new InnerGrid(cellList);
		ArrayList<CellBlock> conflicts = grid.getConflictingCells();
		assertTrue(conflicts.size() == 9);
		assertTrue(conflicts.get(0).equals(c1));
		assertTrue(conflicts.get(3).equals(c4));
		assertTrue(conflicts.get(5).equals(c6));
		assertTrue(conflicts.get(8).equals(c9));

	}

	@Test
	public void testGetConflictingCells3() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c4);
		CellBlock c5 = new CellBlock();
		c2.setAnswer(3);
		cellList.add(c5);
		CellBlock c6 = new CellBlock();
		c2.setAnswer(3);
		cellList.add(c6);
		CellBlock c7 = new CellBlock();
		c2.setAnswer(4);
		cellList.add(c7);
		CellBlock c8 = new CellBlock();
		c2.setAnswer(4);
		cellList.add(c8);
		CellBlock c9 = new CellBlock();
		c2.setAnswer(1);
		cellList.add(c9);
		InnerGrid grid = new InnerGrid(cellList);
		ArrayList<CellBlock> conflicts = grid.getConflictingCells();
		assertTrue(conflicts.size() == 9);
		assertTrue(conflicts.get(0).equals(c1));
		assertTrue(conflicts.get(1).equals(c2));
		assertTrue(conflicts.get(2).equals(c3));
		assertTrue(conflicts.get(3).equals(c4));
		assertTrue(conflicts.get(4).equals(c5));
		assertTrue(conflicts.get(5).equals(c6));
		assertTrue(conflicts.get(6).equals(c7));
		assertTrue(conflicts.get(7).equals(c8));
		assertTrue(conflicts.get(8).equals(c9));

	}

	@Test
	public void testGetConflictingCells4() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		InnerGrid grid = new InnerGrid(cellList);
		ArrayList<CellBlock> conflicts = grid.getConflictingCells();
		assertTrue(conflicts.size() == 0);

	}
	
	@Test
	public void testGridSetValueWithLegalValues() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		grid.setAnswer(0, 9);
		assertEquals(grid.getCell(0).getAnswer(), 9);
		grid.setAnswer(8, 1);
		assertEquals(grid.getCell(8).getAnswer(), 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridSetValueWithTooLarge() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		grid.setAnswer(0, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridSetValueWithTooSmall() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		grid.setAnswer(0, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridSetValueWithPositionTooSmall() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		grid.setAnswer(-1, 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridSetValueWithPositionTooLarge() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		grid.setAnswer(9, 8);
	}

}
