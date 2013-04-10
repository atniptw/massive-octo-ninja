package Sudoku;

import java.util.ArrayList;
import java.util.Iterator;

public class SudokuTable {
	
	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;
	
	public SudokuTable(ArrayList<SudokuStandardRegion> rows, ArrayList<SudokuStandardRegion> columns, ArrayList<SudokuStandardRegion> innerGrids) {
		if (rows.size() != columns.size() || columns.size() != innerGrids.size() || rows.size() != innerGrids.size()) {
			throw new IllegalArgumentException("A Sudoku Board must have the same number of rows, columns and inner grids");
		}
		if(rows.size() > 0) {
		Iterator<SudokuStandardRegion> iter = rows.iterator();
		int sizeOfRegions = iter.next().region.size();
		while(iter.hasNext()) {
			if (iter.next().region.size() != sizeOfRegions) {
				throw new IllegalArgumentException("All regions in a Sudoku Board must have the same size");
			}
		}
		iter = columns.iterator();
		while(iter.hasNext()) {
			if (iter.next().region.size() != sizeOfRegions) {
				throw new IllegalArgumentException("All regions in a Sudoku Board must have the same size");
			}
		}
		iter = innerGrids.iterator();
		while(iter.hasNext()) {
			if (iter.next().region.size() != sizeOfRegions) {
				throw new IllegalArgumentException("All regions in a Sudoku Board must have the same size");
			}
		}
		}
		this.rows = rows;
		this.columns = columns;
		this.innerGrids = innerGrids;
	}
	
}
