package SudokuTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;

public class CellBlockTest {
	@Test
	public void testJUnit() {
		assertTrue("The compiler isn't feeling well today...", true);
	}

	@Test
	public void testCellBlockInitializes() {
		assertNotNull(new CellBlock());
	}

	@Test
	public void testCellStoresGuess() {
		CellBlock cell = new CellBlock();
		cell.guess(1);

		assertEquals(list(1), cell.value());
	}

	@Test
	public void testCellStoresMultipleGuesses() {
		CellBlock cell = new CellBlock();
		cell.guess(1, 2, 3);

		assertEquals(list(1, 2, 3), cell.value());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCellThrowsBadArgumentsExceptionWithDuplicateGuessInCell() {
		CellBlock cell = new CellBlock();
		cell.guess(1, 2, 2);
	}
	
	@Test
	public void testCellHasAnswer(){
		CellBlock cell = new CellBlock();
		cell.setAnswer(1);
		assertEquals(1, cell.getAnswer());
		
	}
	
	@Test
	public void testCellCompareGreater(){
		CellBlock cell1 = new CellBlock();
		CellBlock cell2 = new CellBlock();
		
		cell1.setAnswer(2);
		cell2.setAnswer(1);
		
		assertEquals(1, cell1.compareTo(cell2));
	}
	
	@Test
	public void testCellCompareLess(){
		CellBlock cell1 = new CellBlock();
		CellBlock cell2 = new CellBlock();
		
		cell1.setAnswer(1);
		cell2.setAnswer(2);
		
		assertEquals(-1, cell1.compareTo(cell2));
	}
	
	@Test
	public void testCellCompareEqual(){
		CellBlock cell1 = new CellBlock();
		CellBlock cell2 = new CellBlock();
		
		cell1.setAnswer(1);
		cell2.setAnswer(1);
		
		assertEquals(0, cell1.compareTo(cell2));
	}
	
	@Test
	public void testCellListSorts(){
		assertTrue(false);
	}
	
	@Test
	public void testCellListContain(){
		CellBlock cell1 = new CellBlock();
		
		assertTrue(false);
		
	}

	private ArrayList<Integer> list(int... ints) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i : ints) {
			ret.add(i);
		}

		return ret;
	}
}
