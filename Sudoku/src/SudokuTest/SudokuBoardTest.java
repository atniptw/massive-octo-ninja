package SudokuTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.StandardSudokuBoard;
import Sudoku.SudokuStandardRegion;

public class SudokuBoardTest {
	public ArrayList<CellBlock> blocks;
	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;

	public static final int STANDARD_SIZE = 9;


	public int[][] solvedBoard = { { 5, 2, 9, 1, 7, 6, 3, 4, 8 },
			{ 1, 4, 3, 5, 8, 2, 6, 7, 9 }, { 8, 7, 6, 9, 3, 4, 5, 2, 1 },
			{ 6, 9, 5, 2, 4, 7, 8, 1, 3 }, { 7, 1, 2, 3, 5, 8, 4, 9, 6 },
			{ 3, 8, 4, 6, 9, 1, 2, 5, 7 }, { 4, 5, 8, 7, 1, 3, 9, 6, 2 },
			{ 2, 3, 7, 4, 6, 9, 1, 8, 5 }, { 9, 6, 1, 8, 2, 5, 7, 3, 4 } };

	@Test
	public void testTableInitializesWhenGivenRegions() {

		assertNotNull(new StandardSudokuBoard(STANDARD_SIZE));
	}


	// @Before
	// public void beforeTests() {
	// this.blocks = new ArrayList<CellBlock>();
	// for (int value : solvedBoard) {
	// CellBlock temp = new CellBlock();
	// temp.setAnswer(value);
	// blocks.add(temp);
	// }
	// makeRegionsFromBlockArray(this.blocks);
	// }

	@Test(expected = IllegalArgumentException.class)
	public void testBoardThrowsExceptionWhenNotPerfectSquareSize() {
		new StandardSudokuBoard(10);
	}


	@Test
	public void testTableIsValidReturnsTrueWhenValid() {


		StandardSudokuBoard game = new StandardSudokuBoard(STANDARD_SIZE);
		for (int i = 0; i < STANDARD_SIZE; i++) {
			for (int j = 0; j < STANDARD_SIZE; j++) {
				game.setAnswer(i, j, solvedBoard[i][j]);
			}
		}
		assertTrue(game.isValid());

	}

	@Test
	public void testTableStoresFullSolution() {
		StandardSudokuBoard game = new StandardSudokuBoard(STANDARD_SIZE);
		game.populateBoard();

		int[][] beforeSolution = game.getBoardSolution();
		
		game.setAnswer(0, 0, 0);


		int[][] afterSolution = game.getBoardSolution();
		
		for (int i = 0; i < STANDARD_SIZE; i++) {
			for (int j = 0; j < STANDARD_SIZE; j++) {
				assertEquals(beforeSolution[i][j], afterSolution[i][j]);
			}
		}

	}

	@Test
	public void testTableIsValidReturnsFalseWhenInvalid() {

		StandardSudokuBoard game = new StandardSudokuBoard(9);
		for (int i = 0; i < STANDARD_SIZE; i++) {
			for (int j = 0; j < STANDARD_SIZE; j++) {
				game.setAnswer(i, j, solvedBoard[i][j]);
			}
		}
		game.setAnswer(0, 0, 1);
		assertFalse(game.isValid());
	}

	@Test
	public void testTableSetandGetAnswer() {

		StandardSudokuBoard game = new StandardSudokuBoard(9);
		for (int i = 0; i < STANDARD_SIZE; i++) {
			for (int j = 0; j < STANDARD_SIZE; j++) {
				game.setAnswer(i, j, solvedBoard[i][j]);
			}
		}

		int answer = game.getAnswer(0, 0);

		assertEquals(answer, 5);

		game.setAnswer(0, 0, 2);

		answer = game.getAnswer(0, 0);

		assertEquals(answer, 2);
	}

	@Test
	public void testConflictingCellsToInvalidMarksConflictingCellsInvalid() {


		StandardSudokuBoard game = new StandardSudokuBoard(STANDARD_SIZE);
		for (int i = 0; i < STANDARD_SIZE; i++) {
			for (int j = 0; j < STANDARD_SIZE; j++) {
				game.setAnswer(i, j, solvedBoard[i][j]);
			}
		}

		game.setAnswer(0, 0, 4);


		game.setConflictingCellsToInvalid();

		assertFalse(game.getCell(0, 0).getIsValid());

		assertFalse(game.getCell(0, 7).getIsValid());

		assertFalse(game.getCell(6, 0).getIsValid());

		assertFalse(game.getCell(1, 1).getIsValid());
	}
	
	@Test
	public void testBoardSize(){
		StandardSudokuBoard game = new StandardSudokuBoard(STANDARD_SIZE);
		assertEquals(STANDARD_SIZE, game.size());
	}

}
