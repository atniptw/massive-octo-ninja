package SudokuTest;

import java.util.ArrayList;

import Sudoku.CellBlock;

public interface ISudokuBoard {
	

	public int getAnswer(int row, int column);
	
	public void setAnswer(int row, int column, int answer);
	
	public boolean isValid();
	
	public void setConflictingCellsToInvalid();

}
