package sudoku;

import java.util.ArrayList;

public interface ISudokuRegion {

	public ArrayList<CellBlock> getRegion();

	public boolean isValid();

	public ArrayList<CellBlock> getConflictingCells();

	public CellBlock getCell(int pos);

	public void setAnswer(int pos, int val);

}
