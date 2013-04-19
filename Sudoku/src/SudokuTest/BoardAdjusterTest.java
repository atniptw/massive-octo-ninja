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
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.SIMPLE);

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
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
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
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
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
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
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
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
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

	@Test
	public void testVeryEasyHasEnoughGivens() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.SIMPLE);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 50);
	}

	@Test
	public void testEasyHasEnoughGivens() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 36
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 49);
	}

	@Test
	public void testMediumHasEnoughGivens() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.MEDIUM);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 32
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 35);
	}

	@Test
	public void testDifficultHasEnoughGivens() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.DIFFICULT);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 28
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 31);
	}

	@Test
	public void testEvilHasEnoughGivens() {

		int[][] adjustedBoard;

		testBoard = SudokuGenerator.generateBoard(9);
		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EVIL);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 22
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 27);
	}

}
