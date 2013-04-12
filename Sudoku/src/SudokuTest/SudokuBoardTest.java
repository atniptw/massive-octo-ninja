package SudokuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.StandardSudokuBoard;
import Sudoku.SudokuStandardRegion;

public class SudokuBoardTest {
	public ArrayList<CellBlock> blocks;
	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;

	public int[] solvedBoard = { 5, 2, 9, 1, 7, 6, 3, 4, 8, 1, 4, 3, 5, 8, 2,
			6, 7, 9, 8, 7, 6, 9, 3, 4, 5, 2, 1, 6, 9, 5, 2, 4, 7, 8, 1, 3, 7,
			1, 2, 3, 5, 8, 4, 9, 6, 3, 8, 4, 6, 9, 1, 2, 5, 7, 4, 5, 8, 7, 1,
			3, 9, 6, 2, 2, 3, 7, 4, 6, 9, 1, 8, 5, 9, 6, 1, 8, 2, 5, 7, 3, 4 };

	private void makeRegionsFromBlockArray(ArrayList<CellBlock> boardCells) {
		this.rows = new ArrayList<SudokuStandardRegion>();
		this.columns = new ArrayList<SudokuStandardRegion>();
		this.innerGrids = new ArrayList<SudokuStandardRegion>();
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> rowTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				rowTemp.add(boardCells.get((i * 9) + j));
			}
			this.rows.add(new SudokuStandardRegion(rowTemp));
		}

		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> columnTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				columnTemp.add(boardCells.get(i + (j * 9)));
			}
			this.columns.add(new SudokuStandardRegion(columnTemp));
		}

		ArrayList<CellBlock> gridTemp1 = new ArrayList<CellBlock>();
		ArrayList<CellBlock> gridTemp2 = new ArrayList<CellBlock>();
		ArrayList<CellBlock> gridTemp3 = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				gridTemp1 = new ArrayList<CellBlock>();
				gridTemp2 = new ArrayList<CellBlock>();
				gridTemp3 = new ArrayList<CellBlock>();
			}
			for (int j = 0; j < 9; j++) {
				if (Math.floor(j / 3) == 0) {
					gridTemp1.add(boardCells.get(i * 9 + j));
				}
				if (Math.floor(j / 3) == 1) {
					gridTemp2.add(boardCells.get(i * 9 + j));
				}
				if (Math.floor(j / 3) == 2) {
					gridTemp3.add(boardCells.get(i * 9 + j));
				}

			}
			if ((i + 1) % 3 == 0) {
				this.innerGrids.add(new SudokuStandardRegion(gridTemp1));
				this.innerGrids.add(new SudokuStandardRegion(gridTemp2));
				this.innerGrids.add(new SudokuStandardRegion(gridTemp3));
			}
		}
	}

	@Before
	public void beforeTests() {
		this.blocks = new ArrayList<CellBlock>();
		for (int value : solvedBoard) {
			CellBlock temp = new CellBlock();
			temp.setAnswer(value);
			blocks.add(temp);
		}
		makeRegionsFromBlockArray(this.blocks);
	}

	@Test
	public void testTableInitializesWhenGivenRegions() {

		assertNotNull(new StandardSudokuBoard(rows, columns, this.innerGrids));
	}

	@Test
	public void testTableIsValidReturnsTrueWhenValid() {

		StandardSudokuBoard table = new StandardSudokuBoard(blocks);
		assertTrue(table.isValid());
	}

	@Test
	public void testTableIsValidReturnsFalseWhenInvalid() {
		StandardSudokuBoard table = new StandardSudokuBoard(blocks);
		table.setAnswer(0, 0, 1);
		assertFalse(table.isValid());
	}

	@Test
	public void testTableSetandGetAnswer() {

		StandardSudokuBoard table = new StandardSudokuBoard(blocks);

		int answer = table.getAnswer(0, 0);

		assertEquals(answer, 5);

		table.setAnswer(0, 0, 2);

		answer = table.getAnswer(0, 0);

		assertEquals(answer, 2);
	}

	@Test
	public void testConflictingCellsToInvalidMarksConflictingCellsInvalid() {

		StandardSudokuBoard table = new StandardSudokuBoard(blocks);

		table.setAnswer(0, 0, 4);

		table.setConflictingCellsToInvalid();

		assertFalse(table.getCell(0, 0).getIsValid());

		assertFalse(table.getCell(0, 7).getIsValid());

		assertFalse(table.getCell(6, 0).getIsValid());
		
		assertFalse(table.getCell(1, 1).getIsValid());
	}

}
