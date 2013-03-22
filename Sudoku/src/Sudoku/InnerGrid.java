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

}
