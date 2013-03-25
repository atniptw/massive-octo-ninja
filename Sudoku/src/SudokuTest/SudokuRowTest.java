package sudokuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import sudoku.CellBlock;
import sudoku.SudokuRow;

@Deprecated
public class SudokuRowTest {

	@Test
	public void testSudokuRowInitializes() {
		assertNotNull(new SudokuRow(new ArrayList<CellBlock>()));
	}

	@Test
	public void testSudokuRowAcceptsFilledRow() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		ArrayList<CellBlock> rowList = row.getRowList();
		for (int i = 0; i < 9; i++) {
			assertEquals(i + 1, rowList.get(i).getAnswer());
		}
	}

	@Test
	public void testSudokuRowRecognizesValidRow() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		assertTrue(row.isValidRow());
	}

	@Test
	public void testSudokuRowRecognizesInvalidRow() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer((i + 1) % 4);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		assertFalse(row.isValidRow());
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
		SudokuRow row = new SudokuRow(cellList);
		ArrayList<CellBlock> conflicts = row.getConflictingCells();
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
		SudokuRow row = new SudokuRow(cellList);
		ArrayList<CellBlock> conflicts = row.getConflictingCells();
		assertTrue(conflicts.size() == 5);
		assertTrue(conflicts.contains(c1));
		assertTrue(conflicts.contains(c2));
		assertTrue(conflicts.contains(c4));
		assertTrue(conflicts.contains(c5));
		assertTrue(conflicts.contains(c6));

	}

	@Test
	public void testRowSetValueWithLegalValues() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		row.setAnswer(0, 9);
		assertEquals(row.getCell(0).getAnswer(), 9);
		row.setAnswer(8, 1);
		assertEquals(row.getCell(8).getAnswer(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRowSetValueWithTooLarge() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		row.setAnswer(0, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRowSetValueWithTooSmall() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		row.setAnswer(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRowSetValueWithPositionTooSmall() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		row.setAnswer(-1, 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRowSetValueWithPositionTooLarge() {
		ArrayList<CellBlock> rowCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			rowCellList.add(cell);
		}
		SudokuRow row = new SudokuRow(rowCellList);
		row.setAnswer(9, 8);
	}
}
