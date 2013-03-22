package Sudoku;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class InnerGrid {

	ArrayList<CellBlock> gridList;

	public InnerGrid(ArrayList<CellBlock> gridList) {
		this.gridList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = gridList.iterator();
		while (iterator.hasNext()) {
			this.gridList.add(iterator.next());
		}
	}

	public ArrayList<CellBlock> getGridList() {
		return this.gridList;

	}

	public boolean isValidGrid() {
		for (int i = 0; i < this.gridList.size(); i++) {
			for (int j = i + 1; j < this.gridList.size(); j++) {
				if (this.gridList.get(i).getAnswer() == this.gridList.get(j).getAnswer()){
					return false;
				}
			}
		}
		return true;
	}

}
