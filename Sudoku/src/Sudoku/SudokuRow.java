package Sudoku;

import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public class SudokuRow {

	ArrayList<CellBlock> rowList;
	
	public SudokuRow(ArrayList<CellBlock> rowList){
		this.rowList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = rowList.iterator();
		while(iterator.hasNext()) {
			this.rowList.add(iterator.next());
		}
	}
	
	public ArrayList<CellBlock> getRowList() {
		return this.rowList;
	}
	
	public boolean isValidRow() {
		for (int i = 0; i < this.rowList.size(); i++) {
			for (int j = i + 1; j < this.rowList.size(); j++) {
				if (this.rowList.get(i).getAnswer() == this.rowList.get(j).getAnswer()){
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<CellBlock> getConflictingCells() {
		ArrayList<CellBlock> conflicts = new ArrayList<CellBlock>();
		for (int i = 0; i < this.rowList.size(); i++) {
			for (int j = i+1; j < this.rowList.size(); j++) {
				if (this.rowList.get(i).getAnswer() == this.rowList.get(j).getAnswer()){
					if (!conflicts.contains(this.rowList.get(i))){
						conflicts.add(this.rowList.get(i));
					}
					if (!conflicts.contains(this.rowList.get(j))) {
						conflicts.add(this.rowList.get(j));
					}
				}
			}
		}
		return conflicts;
	}
	
	public CellBlock getCell(int pos) {
		if (pos > this.rowList.size() - 1 || pos < 0){
			throw new IllegalArgumentException();
		}
		return this.rowList.get(pos);
	}
	
	public void setAnswer(int pos, int val) {
		if (pos > this.rowList.size() - 1 || pos < 0 || val < 1 || val > this.rowList.size()){
			throw new IllegalArgumentException();
		}
		this.rowList.get(pos).setAnswer(val);
	}
	
}
