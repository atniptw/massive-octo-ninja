package Sudoku;

public interface ISudokuBoard {

	public int getAnswer(int row, int column);

	public void setAnswer(int row, int column, int answer);

	public CellBlock getCell(int row, int column);

	public boolean isValid();

	public void setConflictingCellsToInvalid();

	public int size();

	public void populateBoard();

	public boolean isComplete();
	
	public int getCellSolution(int i, int j);
	
	public int getMinutesOnBoard();
	
	public int getSecondsOnBoard();
	
	public void setMinutesOnBoard(int minutes);
	
	public void setSecondsOnBoard(int seconds);

}
