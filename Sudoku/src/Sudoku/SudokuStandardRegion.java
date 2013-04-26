package Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SudokuStandardRegion implements ISudokuRegion, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9210837826418213561L;
	ArrayList<CellBlock> region;

	public SudokuStandardRegion(ArrayList<CellBlock> regionList) {
		this.region = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = regionList.iterator();
		while (iterator.hasNext()) {
			this.region.add(iterator.next());
		}
	}

	public ArrayList<CellBlock> getRegion() {
		return this.region;
	}

	public boolean isValid() {
		ArrayList<CellBlock> temp = new ArrayList<CellBlock>();
		for (CellBlock cell : this.region)
			temp.add(cell);
		Collections.sort(temp);

		for (int i = 0; i + 1 < temp.size(); i++) {
			if (temp.get(i).equals(temp.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<CellBlock> getConflictingCells() {
		ArrayList<CellBlock> conflicts = new ArrayList<CellBlock>();
		ArrayList<CellBlock> temp = new ArrayList<CellBlock>();
		for (CellBlock cell : this.region)
			temp.add(cell);
		Collections.sort(temp);

		for (int i = 0; i + 1 < temp.size(); i++) {
			if (temp.get(i).equals(temp.get(i + 1))) {
				conflicts.add(temp.get(i));
				for (int j = i + 1; j < temp.size(); j++) {
					if (temp.get(i).equals(temp.get(j))) {
						conflicts.add(temp.get(j));
					} else {
						i = j - 1;
						break;
					}
				}
			}
		}

		return conflicts;
	}

	public void resetConflicts() {
		for (CellBlock cell : this.region) {
			cell.setIsValid(true);
		}
	}

	public void setConflictingCellsToInvalid() {
		ArrayList<CellBlock> temp = new ArrayList<CellBlock>();
		for (CellBlock cell : this.region)
			temp.add(cell);
		Collections.sort(temp);
		for (int i = 0; i + 1 < temp.size(); i++) {
			if (temp.get(i).equals(temp.get(i + 1))) {
				temp.get(i).setIsValid(false);
				for (int j = i + 1; j < temp.size(); j++) {
					if (temp.get(i).equals(temp.get(j))) {
						temp.get(j).setIsValid(false);
					} else {
						i = j - 1;
						break;
					}
				}
			}

		}
	}

	public CellBlock getCell(int pos) {
		if (pos > this.region.size() - 1 || pos < 0) {
			throw new IllegalArgumentException();
		}
		return this.region.get(pos);
	}

	public void setAnswer(int pos, int val) {
		if (pos > this.region.size() - 1 || pos < 0 || val < 1
				|| val > this.region.size()) {
			throw new IllegalArgumentException();
		}
		this.region.get(pos).setAnswer(val);
	}

}
