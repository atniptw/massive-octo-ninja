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

	@BeforeClass
	public static void generateGameBoard() {
		gameBoard = SudokuGenerator.generateBoard(9);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	}

	@Test
	public void test81NumbersGenerated() {
		assertEquals(9, gameBoard.length);
	}

	@Test
	public void testEachRowIsValid() {
		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
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
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> column = new ArrayList<CellBlock>();
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
	public void testFindsInvalidNewNumberSecondRow() {
		int[][] testBoard = { { 1, 2, 3, 4 }, { 3, 3 } };
		assertFalse(SudokuGenerator.isValid(testBoard, 2, 2));
	}

	@Test
	public void testAddNextNumberToRowWithOneNumber() {
		int[][] testBoard = { { 1, 0 } };
		SudokuGenerator.addNumber(testBoard, 0, 1, 2);
		assertEquals(2, testBoard[0][1]);
	}

	@Test
	public void testAddNextNumberToRowWithTwoNumbers() {
		int[][] testBoard = { { 1, 3, 0 } };
		SudokuGenerator.addNumber(testBoard, 0, 2, 3);
		assertEquals(2, testBoard[0][2]);
	}

	@Test
	public void testAddNextNumberToRowWithThreeNumbers() {
		int[][] testBoard = { { 1, 3, 4, 0 } };
		SudokuGenerator.addNumber(testBoard, 0, 3, 4);
		assertEquals(2, testBoard[0][3]);
	}

	@Test
	public void testAddNextNumberToRowWithFourNumbers() {
		int[][] testBoard = { { 1, 4, 2, 0 } };
		SudokuGenerator.addNumber(testBoard, 0, 3, 4);
		assertEquals(3, testBoard[0][3]);
	}

	@Test
	public void testAddNextNumberToSecondRowWithThreeNumbers() {
		int[][] testBoard = { { 1, 2, 3, 4 }, { 2, 3, 0 } };
		SudokuGenerator.addNumber(testBoard, 1, 2, 3);
		assertEquals(1, testBoard[1][2]);
	}

	@Test
	public void testAddNextNumberToSecondRowWithTwoNumbers() {
		int[][] testBoard = { { 1, 2, 3, 4 }, { 1, 0 } };
		SudokuGenerator.addNumber(testBoard, 1, 1, 2);
		assertEquals(2, testBoard[1][1]);
	}
}
