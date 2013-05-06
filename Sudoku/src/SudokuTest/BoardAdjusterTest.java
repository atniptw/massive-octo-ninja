package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import Sudoku.BoardAdjuster;
import Sudoku.CellBlock;
import Sudoku.ISudokuBoard;
import Sudoku.StandardSudokuBoard;
import org.junit.Before;
import Sudoku.SudokuStandardRegion;

public class BoardAdjusterTest {

	private StandardSudokuBoard testBoard;
	private int size;
	public ResourceBundle bundle;

	public static final int STANDARD_SIZE = 9;

	@Before
	public void beforeTests() {
		this.testBoard = new StandardSudokuBoard(STANDARD_SIZE);
		this.testBoard.populateBoard();
		this.size = testBoard.getBoardSolution().length;

		Locale loc = new Locale("en", "US");
		this.bundle = ResourceBundle.getBundle("MessagesBundle", loc);
	}

	@Test
	public void testSimpleAdjustedBoardIsInvalid() {
		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "simple", this.bundle);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(this.testBoard.getAnswer(i, j)));
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
		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "easy", this.bundle);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(this.testBoard.getAnswer(i, j)));
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

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "medium", this.bundle);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(this.testBoard.getAnswer(i, j)));
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

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "difficult",
				this.bundle);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(this.testBoard.getAnswer(i, j)));
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

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "evil", this.bundle);

		SudokuStandardRegion region;
		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> row = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				row.add(new CellBlock(this.testBoard.getAnswer(i, j)));
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
	public void testSimpleHasEnoughGivens() {
		// TODO Adjust magic number to be based on factors

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "simple", this.bundle);

		assertTrue((size * size)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.SIMPLE_MIN_FACTOR);
	}

	@Test
	public void testEasyHasEnoughGivens() {
		// TODO Adjust magic number to be based on factors

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "easy", this.bundle);

		assertTrue((size * size)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.EASY_MIN_FACTOR
				&& (size * size)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.EASY_MAX_FACTOR);
	}

	@Test
	public void testMediumHasEnoughGivens() {
		// TODO Adjust magic number to be based on factors

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "medium", this.bundle);

		assertTrue((size * size)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.MEDIUM_MIN_FACTOR
				&& (size * size)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.MEDIUM_MAX_FACTOR);

	}

	@Test
	public void testDifficultHasEnoughGivens() {

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "difficult",
				this.bundle);

		assertTrue((size * size)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.DIFFICULT_MIN_FACTOR
				* size
				&& (size * size)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.DIFFICULT_MAX_FACTOR
						* size);
	}

	@Test
	public void testEvilHasEnoughGivens() {

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "evil", this.bundle);

		assertTrue((size * size)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.EVIL_MIN_FACTOR
				* size
				&& (size * size)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.EVIL_MAX_FACTOR
						* size);
	}

	@Test
	public void testSimpleHasProperRegionGivensFloor() {

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "simple", this.bundle);

		for (int i = 0; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.SIMPLE_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.SIMPLE_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEasyHasProperRegionGivensFloor() {

		this.testBoard.populateBoard();

		BoardAdjuster.adjustForDifficulty(this.testBoard, "easy", this.bundle);

		for (int i = 0; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testMediumHasProperRegionGivensFloor() {

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "medium", this.bundle);

		for (int i = 0; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testDifficultHasProperRegionGivensFloor() {

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "simple", this.bundle);

		for (int i = 0; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEvilHasProperRegionGivensFloor() {

		this.testBoard.populateBoard();

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "simple", this.bundle);

		for (int i = 0; i < size; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
		}
	}

}
