package sudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import sudoku.CellBlock;
import sudoku.SudokuColumn;


public class SudokuColumnTest {

	@Test
	public void testSudokuColumnInitializes() {
		assertNotNull(new SudokuColumn(new ArrayList<CellBlock>()));
	}
	
	@Test
	public void testSudokuColumnAcceptsFilledColumn() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuColumn column = new SudokuColumn(columnCellList);
		ArrayList<CellBlock> columnList = column.getColumnList();
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
		SudokuColumn column = new SudokuColumn(columnCellList);
		assertTrue(column.isValidColumn());
	}
	
	@Test
	public void testSudokuColumnRecognizesInvalidColumn() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer((i+1) % 4);
			columnCellList.add(cell);
		}
		SudokuColumn column = new SudokuColumn(columnCellList);
		assertFalse(column.isValidColumn());
	}
	
	@Test
	public void testGetConflictingCells() {
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
		SudokuColumn column = new SudokuColumn(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertTrue(conflicts.size() == 2);
		assertTrue(conflicts.get(0).equals(c1));
		assertTrue(conflicts.get(1).equals(c4));
		
	}
	
	@Test
	public void testMoreConflictingCells() {
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
		SudokuColumn column = new SudokuColumn(cellList);
		ArrayList<CellBlock> conflicts = column.getConflictingCells();
		assertTrue(conflicts.size() == 5);
		assertTrue(conflicts.contains(c1));
		assertTrue(conflicts.contains(c2));
		assertTrue(conflicts.contains(c4));
		assertTrue(conflicts.contains(c5));
		assertTrue(conflicts.contains(c6));
		
	}
	
	@Test
	public void testColumnSetValueWithLegalValues() {
		ArrayList<CellBlock> columnCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i+1);
			columnCellList.add(cell);
		}
		SudokuColumn column = new SudokuColumn(columnCellList);
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
		SudokuColumn column = new SudokuColumn(columnCellList);
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
		SudokuColumn column = new SudokuColumn(columnCellList);
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
		SudokuColumn column = new SudokuColumn(columnCellList);
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
		SudokuColumn column = new SudokuColumn(columnCellList);
		column.setAnswer(9, 8);
	}

}
