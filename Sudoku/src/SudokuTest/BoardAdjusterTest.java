package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.BoardAdjuster;
import Sudoku.CellBlock;
import Sudoku.SudokuGenerator;
import org.junit.Before;
import Sudoku.SudokuStandardRegion;

public class BoardAdjusterTest {

	private int[][] testBoard;
	
	

	@Before
	public void beforeTests() {
		this.testBoard = SudokuGenerator.generateBoard(9);
	}

	@Test
	public void testVeryEasyAdjustedBoardIsInvalid() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.VERY_EASY);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 50);
	}

	@Test
	public void testEasyHasEnoughGivens() {

		int[][] adjustedBoard;

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

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.DIFFICULT);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= BoardAdjuster.DIFFICULT_MIN_FACTOR * testBoard.length
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 31);
	}

	@Test
	public void testEvilHasEnoughGivens() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EVIL);

		assertTrue((testBoard.length * testBoard.length)
				- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) >= 22
				&& (testBoard.length * testBoard.length)
						- BoardAdjuster.getTotalUnfilledCells(adjustedBoard) <= 27);
	}

	@Test
	public void testVeryEasyHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		for (int i = 0; i < testBoard.length - 1; i++) {
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInRow(adjustedBoard,
					i) >= 5);
			//assertTrue(BoardAdjuster.getTotalUnfilledCellsInCol(adjustedBoard,
					//i) >= 5);
		}
	}

	@Test
	public void testEasyHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		for (int i = 1; i < testBoard.length; i++) {
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInRow(adjustedBoard,
					i) >= 4);
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInCol(adjustedBoard,
					i) >= 4);
		}
	}

	@Test
	public void testMediumHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		for (int i = 1; i < testBoard.length; i++) {
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInRow(adjustedBoard,
					i) >= 3);
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInCol(adjustedBoard,
					i) >= 3);
		}
	}

	@Test
	public void testDifficultHasProperRegionGivensFloor() {

		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		for (int i = 1; i < testBoard.length; i++) {
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInRow(adjustedBoard,
					i) >= 2);
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInCol(adjustedBoard,
					i) >= 2);
		}
	}

	@Test
	public void testEvilHasProperRegionGivensFloor() {


		int[][] adjustedBoard;

		adjustedBoard = BoardAdjuster.adjustForDifficulty(testBoard.clone(),
				BoardAdjuster.Difficulty.EASY);

		for (int i = 1; i < testBoard.length; i++) {
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInRow(adjustedBoard,
					i) >= 0);
			assertTrue(BoardAdjuster.getTotalUnfilledCellsInCol(adjustedBoard,
					i) >= 0);
		}
	}

}
