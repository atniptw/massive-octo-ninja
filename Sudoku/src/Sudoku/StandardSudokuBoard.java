package Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class StandardSudokuBoard implements ISudokuBoard, Serializable {
	private static final long serialVersionUID = 4133396481533243164L;

	/**
	 * 
	 */
	ArrayList<SudokuStandardRegion> rows;
	ArrayList<SudokuStandardRegion> columns;
	ArrayList<SudokuStandardRegion> innerGrids;
	private int SIZE;
	private int[][] solution;
	private int minutes;
	private int seconds;

	public StandardSudokuBoard(int size) {
		if (checkPerfectSquare(size)) {
			SIZE = size;
		} else {
			throw new IllegalArgumentException(
					"A StandardSudokuBoard requires perfect squares");
		}

		ArrayList<CellBlock> region = new ArrayList<CellBlock>();
		for (int i = 0; i < SIZE * SIZE; i++) {
			region.add(new CellBlock());
		}

		this.minutes = 0;
		this.seconds = 0;
		
		initStandardSudokuBoard(region);
	}
	
	public StandardSudokuBoard(ArrayList<CellBlock> values, int[][] answers) {
		SIZE = answers.length;
		
		ArrayList<CellBlock> region = new ArrayList<CellBlock>();
		for (int i = 0; i < SIZE * SIZE; i++) {
			region.add(new CellBlock());
		}

		initStandardSudokuBoard(region);
	}

	public StandardSudokuBoard(int size, StandardSudokuBoard orig) {
		// Used for cloning, no regular initialization needed
		SIZE = size;

		this.columns = orig.columns;
		this.rows = orig.rows;
		this.innerGrids = orig.innerGrids;
		this.solution = orig.getBoardSolution();
	}

	private boolean checkPerfectSquare(int size) {
		if ((Math.sqrt(size) - Math.floor(Math.sqrt(size))) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void populateBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Random generator = new Random();
				ArrayList<Integer> val = new ArrayList<Integer>();
				for (int k = 0; k < SIZE; k++) {
					val.add(k + 1);
				}
				int rnd = generator.nextInt(val.size());
				this.rows.get(i).getCell(j).setAnswer(val.get(rnd));
				while (!this.isValid()) {
					val.remove(rnd);
					if (val.size() == 0) {
						clearRegion(this.rows.get(i));
						clearRegion(this.rows.get(i - 1));
						i--;
						j = -1;
						break;
					}
					rnd = generator.nextInt(val.size());
					this.rows.get(i).getCell(j).setAnswer(val.get(rnd));
				}
			}
		}

		solution = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				solution[i][j] = this.rows.get(i).getCell(j).getAnswer();
			}
		}
	}

	private void clearRegion(SudokuStandardRegion region) {
		for (int i = 0; i < SIZE; i++) {
			region.getCell(i).setAnswer(0);
		}
	}

	private void initStandardSudokuBoard(ArrayList<CellBlock> boardCells) {
		this.rows = new ArrayList<SudokuStandardRegion>();
		this.columns = new ArrayList<SudokuStandardRegion>();
		this.innerGrids = new ArrayList<SudokuStandardRegion>();

		for (int i = 0; i < SIZE; i++) {
			ArrayList<CellBlock> rowTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < SIZE; j++) {
				rowTemp.add(boardCells.get((i * SIZE) + j));
			}
			this.rows.add(new SudokuStandardRegion(rowTemp));
		}

		for (int i = 0; i < SIZE; i++) {
			ArrayList<CellBlock> columnTemp = new ArrayList<CellBlock>();
			for (int j = 0; j < SIZE; j++) {
				columnTemp.add(boardCells.get(i + (j * SIZE)));
			}
			this.columns.add(new SudokuStandardRegion(columnTemp));
		}

		ArrayList<CellBlock> gridTemp;
		double incr = Math.sqrt(SIZE);
		for (int i = 0; i < SIZE; i += incr) {
			for (int j = 0; j < SIZE; j += incr) {
				gridTemp = new ArrayList<CellBlock>();
				for (int k = 0; k < incr; k++) {
					for (int l = 0; l < incr; l++) {
						gridTemp.add(boardCells.get(((i + k) * SIZE) + (j + l)));
					}
				}
				this.innerGrids.add(new SudokuStandardRegion(gridTemp));
			}
		}

		throwExceptionForCellsNotMatchingInCoorespondingPositions(this.rows,
				this.columns, this.innerGrids);

	}

	private void throwExceptionForCellsNotMatchingInCoorespondingPositions(
			ArrayList<SudokuStandardRegion> rows,
			ArrayList<SudokuStandardRegion> columns,
			ArrayList<SudokuStandardRegion> innerGrids) {
		int incr = (int) Math.sqrt(SIZE);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (rows.get(i).getCell(j) != columns.get(j).getCell(i)) {
					throw new IllegalArgumentException(
							"Cells in corresponding locations in rows and columns must be the same cell");
				}
				if (rows.get(i).getCell(j) != innerGrids.get(
						(int) (incr * Math.floor(i / incr) + Math.floor(j
								/ incr))).getCell(
						incr * (i % incr) + (j % incr))) {
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
	
	public boolean isFull() {
		for (SudokuStandardRegion row : this.rows) {
			if (!row.isFull()) {
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

	public int size() {
		return this.rows.size();
	}

	public int[][] getBoardSolution() {
		return solution;
	}

	public static StandardSudokuBoard boardClone(StandardSudokuBoard orig) {
		StandardSudokuBoard clone = new StandardSudokuBoard(orig.SIZE, orig);

		return clone;
	}

	@Override
	public boolean isComplete() {
		return this.isValid() && this.isFull();
	}
	
	public int getCellSolution(int i, int j) {
		return this.solution[i][j];
	}

	@Override
	public int getMinutesOnBoard() {
		return this.minutes;
	}

	@Override
	public int getSecondsOnBoard() {
		return this.seconds;
	}

	@Override
	public void setMinutesOnBoard(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public void setSecondsOnBoard(int seconds) {
		this.seconds = seconds;
	}

}
