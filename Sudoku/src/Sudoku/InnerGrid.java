package Sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void testGetConflictingCells1() {
		ArrayList<CellBlock> cellList = new ArrayList<CellBlock>();
		CellBlock c1 = new CellBlock();
		c1.setAnswer(1);
		cellList.add(c1);
		CellBlock c2 = new CellBlock();
		c2.setAnswer(2);
		cellList.add(c2);
		CellBlock c3 = new CellBlock();
		c3.setAnswer(3);
		cellList.add(c3);
		CellBlock c4 = new CellBlock();
		c4.setAnswer(1);
		cellList.add(c4);
		InnerGrid grid = new InnerGrid(cellList);
		ArrayList<CellBlock> conflicts = grid.getConflictingCells();
		assertTrue(conflicts.size() == 2);
		assertTrue(conflicts.get(0).equals(c1));
		assertTrue(conflicts.get(1).equals(c4));
		
	}

}
