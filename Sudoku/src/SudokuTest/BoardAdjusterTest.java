package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.BoardAdjuster;
import Sudoku.CellBlock;
import Sudoku.StandardSudokuBoard;
import org.junit.Before;
import Sudoku.SudokuStandardRegion;
import Sudoku.BoardAdjuster.Difficulty;

public class BoardAdjusterTest {

	private StandardSudokuBoard testBoard;
	private int size;

	public static final int STANDARD_SIZE = 9;

	@Before
	public void beforeTests() {
		this.testBoard = new StandardSudokuBoard(STANDARD_SIZE);
		this.testBoard.populateBoard();
		this.size = testBoard.getBoardAnswer().length;
	}

	@Test
	public void testVeryEasyAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.VERY_EASY);

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EASY);

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.MEDIUM);

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.DIFFICULT);

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EVIL);

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.VERY_EASY);

		assertTrue((size * testBoard
				.getBoardAnswer().length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 50);
	}

	@Test
	public void testEasyHasEnoughGivens() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EASY);

		assertTrue((size * testBoard
				.getBoardAnswer().length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 36
				&& (size * testBoard
						.getBoardAnswer().length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 49);
	}

	@Test
	public void testMediumHasEnoughGivens() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.MEDIUM);

		assertTrue((size * testBoard
				.getBoardAnswer().length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 32
				&& (size * testBoard
						.getBoardAnswer().length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 35);
	}

	@Test
	public void testDifficultHasEnoughGivens() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.DIFFICULT);

		assertTrue((size * testBoard
				.getBoardAnswer().length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= BoardAdjuster.DIFFICULT_MIN_FACTOR
				* size
				&& (size * testBoard
						.getBoardAnswer().length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 31);
	}

	@Test
	public void testEvilHasEnoughGivens() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EVIL);

		assertTrue((size * testBoard
				.getBoardAnswer().length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 22
				&& (size * testBoard
						.getBoardAnswer().length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 27);
	}

	@Test
	public void testVeryEasyHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.VERY_EASY);

		for (int i = 0; i < size - 1; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(adjustedBoard, i,
					 size) >= BoardAdjuster.VERY_EASY_STANDARD_FILL_FLOOR);
			// assertTrue(BoardAdjuster.getTotalGivensCellsInCol(adjustedBoard,
			// i, size) >= BoardAdjuster.VERY_EASY_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEasyHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EASY);

		for (int i = 1; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(adjustedBoard, i, size) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(adjustedBoard, i, size) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testMediumHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.MEDIUM);

		for (int i = 1; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(adjustedBoard, i, size) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(adjustedBoard, i, size) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testDifficultHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.DIFFICULT);

		for (int i = 1; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(adjustedBoard, i, size) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(adjustedBoard, i, size) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEvilHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(
				testBoard.getBoardAnswer(), BoardAdjuster.Difficulty.EVIL);

		for (int i = 1; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(adjustedBoard, i, size) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(adjustedBoard, i, size) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
		}
	}

}
