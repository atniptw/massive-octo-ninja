package SudokuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.SudokuStandardRegion;


public class SudokuStandardRegionTest {

	@Test
	public void testSudokuColumnInitializes() {
		assertNotNull(new SudokuStandardRegion(new ArrayList<CellBlock>()));
	}
	
	@Test
	public void testSudokuColumnAcceptsFilledColumn() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		ArrayList<CellBlock> columnList = column.getRegion();
		for (int i = 0; i < 9; i++) {
			assertEquals(i+1, columnList.get(i).getAnswer());
		}
	}
	
	@Test
	public void testSudokuColumnRecognizesValidColumn() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		assertTrue(column.isValid());
	}
	
	@Test
	public void testSudokuColumnRecognizesInvalidColumn() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer((i+1) % 4);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		assertFalse(column.isValid());
	}
	
	@Test
	public void testGetConflictingCellsTwoThrees() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c3.setAnswer(3);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c4.setAnswer(3);
		cellList.add(c4);
		SudokuStandardRegion column = new SudokuStandardRegion(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertEquals(2, conflicts.size());	
	}
	
	@Test
	public void testGetConflictingCellsTwoOnes() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c3.setAnswer(3);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c4.setAnswer(1);
		cellList.add(c4);
		SudokuStandardRegion column = new SudokuStandardRegion(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertEquals(2, conflicts.size());	
	}
	
	@Test
	public void testGetConflictingCellsTwoTwos() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c3.setAnswer(3);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c4.setAnswer(2);
		cellList.add(c4);
		SudokuStandardRegion column = new SudokuStandardRegion(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertEquals(2, conflicts.size());	
	}
	
	@Test
	public void testConflictingCellsTwoOnesThreeTwos() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c3.setAnswer(3);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c4.setAnswer(1);
		cellList.add(c4);
		CellBlock c5 = new CellBlock();
		c5.setAnswer(2);
		cellList.add(c5);
		CellBlock c6 = new CellBlock();
		c6.setAnswer(2);
		cellList.add(c6);
		SudokuStandardRegion column = new SudokuStandardRegion(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertEquals(5, conflicts.size());
	}
	
	@Test
	public void testColumnSetValueWithLegalValues() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		column.setAnswer(0, 9);
		assertEquals(column.getCell(0).getAnswer(), 9);
		column.setAnswer(8, 1);
		assertEquals(column.getCell(8).getAnswer(), 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColumnSetValueWithTooLarge() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		column.setAnswer(0, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColumnSetValueWithTooSmall() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		column.setAnswer(0, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColumnSetValueWithPositionTooSmall() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		column.setAnswer(-1, 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColumnSetValueWithPositionTooLarge() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuStandardRegion column = new SudokuStandardRegion(columnCellList);
		column.setAnswer(9, 8);
	}

}
