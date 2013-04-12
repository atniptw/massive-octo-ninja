package SudokuTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Sudoku.BoardAdjuster;
import Sudoku.SudokuGenerator;



public class BoardAdjusterTest {
	
	private static int[][] testBoard;

	@Test
	public void testVeryEasyHasEnoughGivens() {
		testBoard = SudokuGenerator.generateBoard(9);
		BoardAdjuster.adjustForDifficulty(testBoard, BoardAdjuster.Difficulty.VERY_EASY);
		assertTrue(testBoard.length >= 50);
	}
	
	@Test
	public void testEasyHasEnoughGivens() {
		testBoard = SudokuGenerator.generateBoard(9);
		BoardAdjuster.adjustForDifficulty(testBoard, BoardAdjuster.Difficulty.EASY);
		assertTrue(testBoard.length >= 36 && testBoard.length <= 49);
	}
	
	@Test
	public void testMediumHasEnoughGivens() {
		testBoard = SudokuGenerator.generateBoard(9);
		BoardAdjuster.adjustForDifficulty(testBoard, BoardAdjuster.Difficulty.MEDIUM);
		assertTrue(testBoard.length >= 32 && testBoard.length <= 35);
	}
	
	@Test
	public void testDifficultHasEnoughGivens() {
		testBoard = SudokuGenerator.generateBoard(9);
		BoardAdjuster.adjustForDifficulty(testBoard, BoardAdjuster.Difficulty.DIFFICULT);
		assertTrue(testBoard.length >= 28 && testBoard.length <= 31);
	}
	
	@Test
	public void testEvilHasEnoughGivens() {
		testBoard = SudokuGenerator.generateBoard(9);
		BoardAdjuster.adjustForDifficulty(testBoard, BoardAdjuster.Difficulty.EVIL);
		assertTrue(testBoard.length >= 22 && testBoard.length <= 27);
	}

}
