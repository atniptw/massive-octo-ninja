package SudokuTest;

import java.util.ArrayList;

import Sudoku.CellBlock;
import Sudoku.SudokuStandardRegion;

public class SudokuTableTest {
	
	@Test
	public void testTableInitializesWhenGivenRegions() {
		ArrayList<CellBlock> cellBlocks = new ArrayList<CellBlock>();
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		assertNotNull(new SudokuTable(rows, columns, innerGrids));
	}
	
	@Test
	public void testTableInitializesWhenGivenCellBlocks() {
		ArrayList<CellBlock> cellBlocks = new ArrayList<CellBlock>();
		
		assertNotNull(new SudokuTable(cellBlocks));
	}

}
