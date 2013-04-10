package SudokuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.SudokuGenerator;
import Sudoku.SudokuStandardRegion;

public class SudokuGeneratorTest {

	private static int[][] gameBoard;

	/*
	 * @BeforeClass public static void generateGameBoard() { gameBoard =
	 * SudokuGenerator.generateBoard(4); for (int i = 0; i < 4; i++) { for (int
	 * j = 0; j < 4; j++) { System.out.print(gameBoard[i][j]); }
	 * System.out.println(); } }
	 */
	
	
	@Test
	public void test81NumbersGenerated() {
		assertEquals(9, gameBoard.length);
	}

	@Test
	public void testEachRowIsValid() {
		SudokuStandardRegion region;
		ArrayList<CellBlock> row = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(gameBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			assertTrue(region.isValid());
		}
	}

	@Test
	public void testEachColumnIsValid() {
		SudokuStandardRegion region;
		ArrayList<CellBlock> column = null;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				column.add(new CellBlock(gameBoard[j][i]));
			}
			region = new SudokuStandardRegion(column);
			assertTrue(region.isValid());
		}
	}

	@Test
	public void testFindsInvalidRowFirstRow() {
		int[][] testBoard = { { 1, 2, 3, 2 }, { 2, 3, 4, 1 }, { 3, 4, 1, 2 },
				{ 4, 1, 2, 3 } };
		assertFalse(SudokuGenerator.isValid(testBoard, 4, 4));
	}
	
	@Test
	public void testFindsInvalidRowSecondRow() {
		int[][] testBoard = { { 1, 2, 3, 4 }, { 3, 3, 4, 1 }, { 3, 4, 1, 2 },
				{ 4, 1, 2, 3 } };
		assertFalse(SudokuGenerator.isValid(testBoard, 4, 4));
	}
	
	@Test
	public void testFindsInvalidNewNumberSecondRow(){
		int[][] testBoard = { { 1, 2, 3, 4 }, { 3, 3 } };
		assertFalse(SudokuGenerator.isValid(testBoard, 2, 2));
	}
}
