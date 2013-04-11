package Sudoku;

import java.util.ArrayList;
import java.util.Iterator;

public class SudokuTable {

	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;

	public SudokuTable(ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {

		throwExceptionForInvalidLengths(rows, columns, innerGrids);
		throwExceptionForCellsNotMatchingInCoorespondingPositions(rows, columns, innerGrids);
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < columns.size(); j++) {
				if (rows.get(i).getCell(j) != columns.get(j).getCell(i)) {
					throw new IllegalArgumentException(
							"Cells in corresponding locations in rows and columns must be the same cell");
				}
			}
		}

		this.rows = rows;
		this.columns = columns;
		this.innerGrids = innerGrids;
	}

	private void throwExceptionForInvalidLengths(
			ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {
		if (rows.size() != columns.size()
				|| columns.size() != innerGrids.size()
				|| rows.size() != innerGrids.size()) {
			throw new IllegalArgumentException(
					"A Sudoku Board must have the same number of rows, columns and inner grids");
		}
		if (rows.size() > 0) {
			Iterator<SudokuStandardRegion> iter = rows.iterator();
			int sizeOfRegions = iter.next().region.size();
			while (iter.hasNext()) {
				if (iter.next().region.size() != sizeOfRegions) {
					throw new IllegalArgumentException(
							"All regions in a Sudoku Board must have the same size");
				}
			}
			iter = columns.iterator();
			while (iter.hasNext()) {
				if (iter.next().region.size() != sizeOfRegions) {
					throw new IllegalArgumentException(
							"All regions in a Sudoku Board must have the same size");
				}
			}
			iter = innerGrids.iterator();
			while (iter.hasNext()) {
				if (iter.next().region.size() != sizeOfRegions) {
					throw new IllegalArgumentException(
							"All regions in a Sudoku Board must have the same size");
				}
			}
		}
	}

	private void throwExceptionForCellsNotMatchingInCoorespondingPositions(
			ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < columns.size(); j++) {
				if (rows.get(i).getCell(j) != columns.get(j).getCell(i)) {
					throw new IllegalArgumentException(
							"Cells in corresponding locations in rows and columns must be the same cell");
				}
			}
		}
	}

	public boolean isValid() {
		for (SudokuStandardRegion row : this.rows) {
			if (!row.isValid()) {
				return false;
			}
		}
		for (SudokuStandardRegion column : this.columns) {
			if (!column.isValid()) {
				return false;
			}
		}
		for (SudokuStandardRegion grids : this.innerGrids) {
			if (!grids.isValid()) {
				return false;
			}
		}
		return true;
	}

}
