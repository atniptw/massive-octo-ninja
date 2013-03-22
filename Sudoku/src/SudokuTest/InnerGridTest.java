package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.InnerGrid;
import Sudoku.SudokuColumn;

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
			cell.setAnswer(i+1);
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
			cell.setAnswer((i+1) % 4);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		assertFalse(grid.isValidGrid());
	}

}
