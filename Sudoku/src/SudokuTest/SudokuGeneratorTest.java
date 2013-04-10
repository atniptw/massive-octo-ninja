package SudokuTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
		gameBoard = SudokuGenerator.generateBoard(9, 9);
	}

	@Test
	public void test81NumbersGenerated() {
		assertEquals(81, gameBoard.length);
	}

	@Test
	public void testEachRowIsValid() {
		SudokuStandardRegion region;
		ArrayList<CellBlock> row = null;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
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
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				column.add(new CellBlock(gameBoard[j][i]));
			}
			region = new SudokuStandardRegion(column);
			assertTrue(region.isValid());
		}
	}
}
