package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.BoardAdjuster;
import Sudoku.CellBlock;
import Sudoku.SudokuGenerator;
import Sudoku.SudokuStandardRegion;

public class BoardAdjusterTest {

	private int[][] testBoard;

	@Test
	public void testVeryEasyAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard,
				BoardAdjuster.Difficulty.VERY_EASY);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(adjustedBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			if (region.isValid() && i < 9) {
				continue;
			} else {
				assertFalse(region.isValid());
			}
		}
	}

	@Test
	public void testEasyAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard,
				BoardAdjuster.Difficulty.EASY);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(adjustedBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			if (region.isValid() && i < 9) {
				continue;
			} else {
				assertFalse(region.isValid());
			}
		}
	}

	@Test
	public void testMediumAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard,
				BoardAdjuster.Difficulty.MEDIUM);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(adjustedBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			if (region.isValid() && i < 9) {
				continue;
			} else {
				assertFalse(region.isValid());
			}
		}
	}

	@Test
	public void testDifficultAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard,
				BoardAdjuster.Difficulty.DIFFICULT);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(adjustedBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			if (region.isValid() && i < 9) {
				continue;
			} else {
				assertFalse(region.isValid());
			}
		}
	}

	@Test
	public void testEvilAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard,
				BoardAdjuster.Difficulty.EVIL);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(adjustedBoard[i][j]));
			}
			region = new SudokuStandardRegion(row);
			if (region.isValid() && i < 9) {
				continue;
			} else {
				assertFalse(region.isValid());
			}
		}
	}

	// @Test
	// public void testVeryEasyHasEnoughGivens() {
	//
	// BoardAdjuster.adjustForDifficulty(testBoard,
	// BoardAdjuster.Difficulty.VERY_EASY);
	// assertTrue(testBoard.length >= 50);
	// }
	//
	// @Test
	// public void testEasyHasEnoughGivens() {
	//
	// BoardAdjuster.adjustForDifficulty(testBoard,
	// BoardAdjuster.Difficulty.EASY);
	// assertTrue(testBoard.length >= 36 && testBoard.length <= 49);
	// }
	//
	// @Test
	// public void testMediumHasEnoughGivens() {
	//
	// BoardAdjuster.adjustForDifficulty(testBoard,
	// BoardAdjuster.Difficulty.MEDIUM);
	// assertTrue(testBoard.length >= 32 && testBoard.length <= 35);
	// }
	//
	// @Test
	// public void testDifficultHasEnoughGivens() {
	//
	// BoardAdjuster.adjustForDifficulty(testBoard,
	// BoardAdjuster.Difficulty.DIFFICULT);
	// assertTrue(testBoard.length >= 28 && testBoard.length <= 31);
	// }
	//
	// @Test
	// public void testEvilHasEnoughGivens() {
	//
	// BoardAdjuster.adjustForDifficulty(testBoard,
	// BoardAdjuster.Difficulty.EVIL);
	// assertTrue(testBoard.length >= 22 && testBoard.length <= 27);
	// }

}
