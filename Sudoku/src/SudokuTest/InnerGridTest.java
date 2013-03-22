package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.InnerGrid;
import Sudoku.SudokuColumn;

public class InnerGridTest {

	@Test
	public void testInnerGridInitializes() {
		assertNotNull(new InnerGrid(new ArrayList<CellBlock>()));
	}

}
