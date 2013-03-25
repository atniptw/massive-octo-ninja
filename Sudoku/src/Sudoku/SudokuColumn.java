package sudoku;

import java.util.ArrayList;
import java.util.Iterator;

public class SudokuColumn {

	ArrayList<CellBlock> columnList;
	
	public SudokuColumn(ArrayList<CellBlock> columnList){
		this.columnList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = columnList.iterator();
		while(iterator.hasNext()) {
			this.columnList.add(iterator.next());
		}
	}
	
	public ArrayList<CellBlock> getColumnList() {
		return this.columnList;
	}
	
	public boolean isValidColumn() {
		for (int i = 0; i < this.columnList.size(); i++) {
			for (int j = i + 1; j < this.columnList.size(); j++) {
				if (this.columnList.get(i).getAnswer() == this.columnList.get(j).getAnswer()){
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<CellBlock> getConflictingCells() {
		ArrayList<CellBlock> conflicts = new ArrayList<CellBlock>();
		for (int i = 0; i < this.columnList.size(); i++) {
			for (int j = i+1; j < this.columnList.size(); j++) {
				if (this.columnList.get(i).getAnswer() == this.columnList.get(j).getAnswer()){
					if (!conflicts.contains(this.columnList.get(i))){
						conflicts.add(this.columnList.get(i));
					}
					if (!conflicts.contains(this.columnList.get(j))) {
						conflicts.add(this.columnList.get(j));
					}
				}
			}
		}
		return conflicts;
	}
	
	public CellBlock getCell(int pos) {
		if (pos > this.columnList.size() - 1 || pos < 0){
			throw new IllegalArgumentException();
		}
		return this.columnList.get(pos);
	}
	
	public void setAnswer(int pos, int val) {
		if (pos > this.columnList.size() - 1 || pos < 0 || val < 1 || val > this.columnList.size()){
			throw new IllegalArgumentException();
		}
		this.columnList.get(pos).setAnswer(val);
	}
	
}
