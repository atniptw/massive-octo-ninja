package Sudoku;

import java.util.ArrayList;


public interface ISudokuBoard {
	

	public int getAnswer(int row, int column);
	
	public void setAnswer(int row, int column, int answer);
	
	public CellBlock getCell(int row, int column);
	
	public boolean isValid();
	
	public void setConflictingCellsToInvalid();
	
	public int size();

}
