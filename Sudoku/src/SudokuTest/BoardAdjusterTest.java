package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import Sudoku.BoardAdjuster;
import Sudoku.CellBlock;
import Sudoku.StandardSudokuBoard;
import org.junit.Before;
import Sudoku.SudokuStandardRegion;

public class BoardAdjusterTest {

	private StandardSudokuBoard testBoard;
	public ResourceBundle bundle;

	public static final int STANDARD_SIZE = 9;

	@Before
	public void beforeEachTest() {
		this.testBoard = new StandardSudokuBoard(STANDARD_SIZE);
		this.bundle = ResourceBundle.getBundle("MessagesBundle", new Locale(
				"en", "US"));
		this.testBoard.populateBoard();

	}

	@Test
	public void testSimpleAdjustedBoardIsInvalid() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Simple", this.bundle);

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

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Easy", this.bundle);

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

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Medium", this.bundle);

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

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Difficult",
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

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Evil", this.bundle);

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

		System.out.println(BoardAdjuster.getTotalUnfilledCells(this.testBoard)
				+ "Before");
		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Simple", this.bundle);
		System.out.println(BoardAdjuster.getTotalUnfilledCells(this.testBoard)
				+ "after");

		assertTrue((STANDARD_SIZE * STANDARD_SIZE)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.SIMPLE_MIN_FACTOR);
	}

	@Test
	public void testEasyHasEnoughGivens() {

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Easy", this.bundle);

		assertTrue((STANDARD_SIZE * STANDARD_SIZE)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.EASY_MIN_FACTOR
				* STANDARD_SIZE
				&& (STANDARD_SIZE * STANDARD_SIZE)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.EASY_MAX_FACTOR
						* STANDARD_SIZE);
	}

	@Test
	public void testMediumHasEnoughGivens() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Medium", this.bundle);

		assertTrue((STANDARD_SIZE * STANDARD_SIZE)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.MEDIUM_MIN_FACTOR
				* STANDARD_SIZE
				&& (STANDARD_SIZE * STANDARD_SIZE)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.MEDIUM_MAX_FACTOR
						* STANDARD_SIZE);

	}

	@Test
	public void testDifficultHasEnoughGivens() {

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Difficult",
				this.bundle);

		assertTrue((STANDARD_SIZE * STANDARD_SIZE)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.DIFFICULT_MIN_FACTOR
				* STANDARD_SIZE
				&& (STANDARD_SIZE * STANDARD_SIZE)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.DIFFICULT_MAX_FACTOR
						* STANDARD_SIZE);
	}

	@Test
	public void testEvilHasEnoughGivens() {

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Evil", this.bundle);

		assertTrue((STANDARD_SIZE * STANDARD_SIZE)
				- BoardAdjuster.getTotalUnfilledCells(this.testBoard) >= BoardAdjuster.EVIL_MIN_FACTOR
				* STANDARD_SIZE
				&& (STANDARD_SIZE * STANDARD_SIZE)
						- BoardAdjuster.getTotalUnfilledCells(this.testBoard) <= BoardAdjuster.EVIL_MAX_FACTOR
						* STANDARD_SIZE);
	}

	@Test
	public void testSimpleHasProperRegionGivensFloor() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Simple", this.bundle);

		for (int i = 0; i < STANDARD_SIZE; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.SIMPLE_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.SIMPLE_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEasyHasProperRegionGivensFloor() {

		BoardAdjuster.adjustForDifficulty(this.testBoard, "Easy", this.bundle);

		for (int i = 0; i < STANDARD_SIZE; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.EASY_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testMediumHasProperRegionGivensFloor() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Medium", this.bundle);

		for (int i = 0; i < STANDARD_SIZE; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.MEDIUM_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testDifficultHasProperRegionGivensFloor() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Simple", this.bundle);

		for (int i = 0; i < STANDARD_SIZE; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.DIFFICULT_STANDARD_FILL_FLOOR);
		}
	}

	@Test
	public void testEvilHasProperRegionGivensFloor() {

		BoardAdjuster
				.adjustForDifficulty(this.testBoard, "Simple", this.bundle);

		for (int i = 0; i < STANDARD_SIZE; i++) {
			assertTrue(BoardAdjuster.getTotalGivensInRow(testBoard, i) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
			assertTrue(BoardAdjuster.getTotalGivensInCol(testBoard, i) >= BoardAdjuster.EVIL_STANDARD_FILL_FLOOR);
		}
	}

}
