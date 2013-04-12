package Sudoku;

import java.util.ArrayList;
import java.util.Iterator;

import SudokuTest.ISudokuBoard;

public class StandardSudokuBoard implements ISudokuBoard {

	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;

	public StandardSudokuBoard(ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {

		throwExceptionForInvalidLengths(rows, columns, innerGrids);
		throwExceptionForCellsNotMatchingInCoorespondingPositions(rows,
				columns, innerGrids);

		this.rows = new ArrayList<SudokuStandardRegion>();
		Iterator<SudokuStandardRegion> iterator = rows.iterator();
		while (iterator.hasNext()) {
			this.rows.add(iterator.next());
		}

		this.columns = new ArrayList<SudokuStandardRegion>();
		iterator = columns.iterator();
		while (iterator.hasNext()) {
			this.columns.add(iterator.next());
		}

		this.innerGrids = new ArrayList<SudokuStandardRegion>();
		iterator = innerGrids.iterator();
		while (iterator.hasNext()) {
			this.innerGrids.add(iterator.next());
		}

	}

	public StandardSudokuBoard(ArrayList<CellBlock> boardCells) {
		if (boardCells.size() != 81) {
			throw new IllegalArgumentException(
					"A StandardSudokuBoard requires 81 cells");
		}

		this.rows = new ArrayList<SudokuStandardRegion>();
		this.columns = new ArrayList<SudokuStandardRegion>();
		this.innerGrids = new ArrayList<SudokuStandardRegion>();

		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> rowTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				rowTemp.add(boardCells.get((i * 9) + j));
			}
			this.rows.add(new SudokuStandardRegion(rowTemp));
		}

		for (int i = 0; i < 9; i++) {
			ArrayList<CellBlock> columnTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < 9; j++) {
				columnTemp.add(boardCells.get(i + (j * 9)));
			}
			this.columns.add(new SudokuStandardRegion(columnTemp));
		}

		ArrayList<CellBlock> gridTemp1 = new ArrayList<CellBlock>();
		ArrayList<CellBlock> gridTemp2 = new ArrayList<CellBlock>();
		ArrayList<CellBlock> gridTemp3 = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				gridTemp1 = new ArrayList<CellBlock>();
				gridTemp2 = new ArrayList<CellBlock>();
				gridTemp3 = new ArrayList<CellBlock>();
			}
			for (int j = 0; j < 9; j++) {
				if (Math.floor(j / 3) == 0) {
					gridTemp1.add(boardCells.get(i*9 + j));
				}
				if (Math.floor(j / 3) == 1) {
					gridTemp2.add(boardCells.get(i*9 + j));
				}
				if (Math.floor(j / 3) == 2) {
					gridTemp3.add(boardCells.get(i*9 + j));
				}
				
			}
			if ((i+1) % 3 == 0) {
				this.innerGrids.add(new SudokuStandardRegion(gridTemp1));
				this.innerGrids.add(new SudokuStandardRegion(gridTemp2));
				this.innerGrids.add(new SudokuStandardRegion(gridTemp3));
			}
		}
		throwExceptionForCellsNotMatchingInCoorespondingPositions(this.rows, this.columns, this.innerGrids);

	}

	private void throwExceptionForInvalidLengths(
			ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {
		if (rows.size() != 9 || columns.size() != 9 || innerGrids.size() != 9) {
			throw new IllegalArgumentException(
					"Sets of rows, columns, or innerGrids in a StandardSudoku Board must have exactly 9 subcomponents");
		}
		IllegalArgumentException exception = new IllegalArgumentException(
				"All regions in a StandardSudokuBoard must contain exactly 9 CellBlocks");
		Iterator<SudokuStandardRegion> iter = rows.iterator();
		while (iter.hasNext()) {
			if (iter.next().region.size() != 9) {
				throw exception;
			}
		}
		iter = columns.iterator();
		while (iter.hasNext()) {
			if (iter.next().region.size() != 9) {
				throw exception;
			}
		}
		iter = innerGrids.iterator();
		while (iter.hasNext()) {
			if (iter.next().region.size() != 9) {
				throw exception;
			}
		}
	}

	private void throwExceptionForCellsNotMatchingInCoorespondingPositions(
			ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (rows.get(i).getCell(j) != columns.get(j).getCell(i)) {
					throw new IllegalArgumentException(
							"Cells in corresponding locations in rows and columns must be the same cell");
				}
				if (rows.get(i).getCell(j) != innerGrids.get(
						(int) (3 * Math.floor(i / 3) + Math.floor(j / 3)))
						.getCell(3 * (i % 3) + (j % 3))) {
					throw new IllegalArgumentException(
							"Cells in corresponding locations in rows and grids must be the same cell");
				}
			}
		}
	}

	public void setAnswer(int row, int column, int answer) {
		this.rows.get(row).getCell(column).setAnswer(answer);
	}

	public int getAnswer(int row, int column) {
		return this.rows.get(row).getCell(column).getAnswer();
	}
	
	public CellBlock getCell(int row, int column) {
		return this.rows.get(row).getCell(column);
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

	public void setConflictingCellsToInvalid() {
		for (SudokuStandardRegion row : this.rows) {
			row.resetConflicts();
		}
		for (SudokuStandardRegion column : this.columns) {
			column.resetConflicts();
		}
		for (SudokuStandardRegion innerGrid : this.innerGrids) {
			innerGrid.resetConflicts();
		}
		for (SudokuStandardRegion row : this.rows) {
			row.setConflictingCellsToInvalid();
		}
		for (SudokuStandardRegion column : this.columns) {
			column.setConflictingCellsToInvalid();
		}
		for (SudokuStandardRegion innerGrid : this.innerGrids) {
			innerGrid.setConflictingCellsToInvalid();
		}
	}

}
